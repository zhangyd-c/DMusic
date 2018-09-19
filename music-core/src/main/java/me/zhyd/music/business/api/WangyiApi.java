/**
 * Copyright [2016-2018] [yadong.zhang]
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package me.zhyd.music.business.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import me.zhyd.music.business.consts.CommonConst;
import me.zhyd.music.business.enums.ResponseStatusEnum;
import me.zhyd.music.business.model.WangyiMusic;
import me.zhyd.music.business.vo.ResponseVO;
import me.zhyd.music.framework.cache.Cache;
import me.zhyd.music.util.RestClientUtil;
import me.zhyd.music.util.ResultUtil;
import me.zhyd.music.util.UrlBuildUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 网易云音乐API接口工具
 *
 * @author yadong.zhang email:yadong.zhang0415(a)gmail.com
 * @version 1.0
 * @date 2016年11月13日 上午10:17:14
 * @since 1.0
 */
@Component
public class WangyiApi {

    /**
     * 默认歌单ID
     */
    private static final String DEFAULT_PLAYID = "448927692";

    /**
     * 获取默认歌单信息
     */
    public List<WangyiMusic> getDefaultPlaylist() {
        return getPlaylist(DEFAULT_PLAYID);
    }

    /**
     * 获取指定歌单信息
     *
     * @param playId
     *         歌单id
     */
    @Cache(expire = 24, unit = TimeUnit.HOURS)
    public List<WangyiMusic> getPlaylist(String playId) {
        if (StringUtils.isEmpty(playId)) {
            return getDefaultPlaylist();
        }
        List<WangyiMusic> musicList = new ArrayList<>();
        String musicListJson = RestClientUtil.get(UrlBuildUtils.getWangyiMusicPlayListUrl(playId));
        if (StringUtils.isEmpty(musicListJson)) {
            return musicList;
        }
        JSONObject obj = JSONObject.parseObject(musicListJson);
        int code = obj.getIntValue("code");
        try {
            if (code == CommonConst.HTTP_OK) {
                JSONArray tracks = obj.getJSONObject("result").getJSONArray("tracks");
                WangyiMusic music = null;
                for (int i = 0, len = tracks.size(); i < len; i++) {
                    music = new WangyiMusic();
                    obj = tracks.getJSONObject(i);
                    music.setId(obj.getString("id"));
                    music.setName(obj.getString("name"));
                    music.setUrl(UrlBuildUtils.getWangyiMusicUrl(music.getId()));

                    // 加载演唱者信息
                    loadArtists(obj, music);

                    // 加载专辑信息
                    loadAlbum(obj, music);

                    musicList.add(music);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return musicList;
    }

    /**
     * 加载演唱者信息
     *
     * @param obj
     *         歌单列表JSON
     * @param music
     *         网易云音乐对象
     */
    private static void loadArtists(JSONObject obj, WangyiMusic music) {
        //演唱者
        JSONArray artistsArray = obj.getJSONArray("artists");
        if (artistsArray != null && !artistsArray.isEmpty()) {
            JSONObject object = artistsArray.getJSONObject(0);
            music.setAuthor(object.getString("name"));
            music.setAuthorImg(object.getString("picUrl"));
        }
    }

    /**
     * 加载专辑信息
     *
     * @param obj
     *         歌单列表JSON
     * @param music
     *         网易云音乐对象
     */
    private static void loadAlbum(JSONObject obj, WangyiMusic music) {
        //所属专辑
        JSONObject albumObj = obj.getJSONObject("album");
        if (albumObj != null) {
            music.setAlbumName(albumObj.getString("name"));
            music.setAlbumImg(albumObj.getString("picUrl"));
        }
    }

    /**
     * 获取歌曲歌词
     *
     * @param musicId
     *         歌曲id
     */
    @Cache(expire = 30, unit = TimeUnit.DAYS)
    public ResponseVO getLyric(String musicId) {
        String lyricJson = RestClientUtil.get(UrlBuildUtils.getWangyiLrcUrl(musicId));
        if (StringUtils.isEmpty(lyricJson)) {
            return ResultUtil.error(ResponseStatusEnum.ERROR);
        }
        JSONObject obj = JSONObject.parseObject(lyricJson);
        int code = obj.getIntValue("code");
        if (code != CommonConst.HTTP_OK) {
            return ResultUtil.error(ResponseStatusEnum.ERROR);
        }
        Boolean uncollected = obj.getBoolean("uncollected");
        if (uncollected != null && uncollected) {
            return ResultUtil.error(ResponseStatusEnum.NOT_FOUND);
        }
        obj = obj.getJSONObject("lrc");
        if (obj == null) {
            return ResultUtil.error(ResponseStatusEnum.NOT_FOUND);
        }
        String lyric = obj.getString("lyric");
        if (lyric != null) {
            lyric = lyric.replaceAll("(\\.[0-9]*)", "");
            lyric = lyric.replaceAll("\n", "\n.");
        }
        return ResultUtil.success(ResponseStatusEnum.SUCCESS, lyric);
    }
}
