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
package me.zhyd.music.business.consts;

/**
 * @author yadong.zhang email:yadong.zhang0415(a)gmail.com
 * @version 1.0
 * @date 2017年12月15日 上午10:17:14
 * @since 1.0
 */
public class CommonConst {

    /**
     * 网易云音乐歌单url
     */
    public static final String WANGYI_MUSIC_PLAYLIST_URL = "http://music.163.com/api/playlist/detail";
    /**
     * 网易云音乐获取歌词
     */
    public static final String WANGYI_LRC_URL = "https://music.163.com/api/song/lyric";
    /**
     * 网易云音乐获取歌曲链接
     */
    public static final String WANGYI_MUSIC_URL = "http://music.163.com/song/media/outer/url";
    /**
     * 网易云音乐封面图片
     */
    public static final String WANGYI_PIC_URL = "http://music.163.com/api/song/detail/";
    /**
     * http请求成功响应码
     */
    public static final int HTTP_OK = 200;
    /**
     * 默认的接口成功响应状态码
     */
    public static final int DEFAULT_RESPONSE_OK = 200;

    /**
     * 默认的接口失败响应状态码
     */
    public static final int DEFAULT_RESPONSE_ERROR = 500;

}
