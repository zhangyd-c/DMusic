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
package me.zhyd.music.util;

import me.zhyd.music.business.consts.CommonConst;

import java.text.MessageFormat;

/**
 * @author yadong.zhang email:yadong.zhang0415(a)gmail.com
 * @version 1.0
 * @date 2015年12月14日 上午10:17:14
 * @since 1.0
 */
public class UrlBuildUtils {
    /**
     * 获取网易云音乐歌单列表的链接
     */
    private static final String GET_WANGYI_MUSIC_PLAYLIST_URL = "{0}?id={1}&updateTime=-1";
    /**
     * 获取网易云音乐歌词的链接
     */
    private static final String GET_WANGYI_LRC_URL = "{0}?os=pc&id={1}&lv=-1&kv=-1&tv=-1";
    /**
     * 获取网易云音乐歌曲的链接
     */
    private static final String GET_WANGYI_MUSIC_URL = "{0}?id={1}.mp3";
    /**
     * 获取网易云音乐封面图片的链接
     */
    private static final String GET_WANGYI_PIC_URL = "{0}?id={1}&ids=[{2}]";

    /**
     * 获取网易云音乐歌单列表的链接
     *
     * @param playId
     *         歌单id
     * @return string
     */
    public static String getWangyiMusicPlayListUrl(String playId) {
        return MessageFormat.format(GET_WANGYI_MUSIC_PLAYLIST_URL, CommonConst.WANGYI_MUSIC_PLAYLIST_URL, playId);
    }

    /**
     * 获取网易云音乐歌词的链接
     *
     * @param id
     *         歌曲id
     * @return string
     */
    public static String getWangyiLrcUrl(String id) {
        return MessageFormat.format(GET_WANGYI_LRC_URL, CommonConst.WANGYI_LRC_URL, id);
    }

    /**
     * 获取网易云音乐歌曲的链接
     *
     * @param id
     *         歌曲id
     * @return string
     */
    public static String getWangyiMusicUrl(String id) {
        return MessageFormat.format(GET_WANGYI_MUSIC_URL, CommonConst.WANGYI_MUSIC_URL, id);
    }

    /**
     * 获取网易云音乐封面图片的链接
     *
     * @param id
     *         歌曲id
     * @return string
     */
    public static String getWangyiPicUrl(String id) {
        return MessageFormat.format(GET_WANGYI_PIC_URL, CommonConst.WANGYI_PIC_URL, id, id);
    }


}
