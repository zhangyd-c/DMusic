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
package me.zhyd.music.business.model;

import lombok.Data;

/**
 * 网易云音乐实体类
 *
 * @author yadong.zhang email:yadong.zhang0415(a)gmail.com
 * @version 1.0
 * @date 2016年11月13日 上午10:17:14
 * @since 1.0
 */
@Data
public class WangyiMusic {
    /**
     * 歌曲id
     */
    private String id;
    /**
     * 歌曲名
     */
    private String name;
    /**
     * 演唱者
     */
    private String author;
    /**
     * 演唱者封面
     */
    private String authorImg;
    /**
     * 专辑
     */
    private String albumName;
    /**
     * 专辑封面
     */
    private String albumImg;
    /**
     * 歌曲地址
     */
    private String url;

    public WangyiMusic() {

    }

    public WangyiMusic(String id, String name) {
        this.id = id;
        this.name = name;
    }

}
