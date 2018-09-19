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
package me.zhyd.music.business.enums;

/**
 * @author yadong.zhang email:yadong.zhang0415(a)gmail.com
 * @version 1.0
 * @date 2018/3/7 13:58
 * @since 1.0
 */
public enum MusicStatusEnum {

    DISABLE(-1),
    DEFAULT(1),
    NORMAL(0);
    private int code;

    MusicStatusEnum(int code) {
        this.code = code;
    }

    public static MusicStatusEnum getStatus(Integer code) {
        if (null == code) {
            return null;
        }
        MusicStatusEnum[] statusArr = MusicStatusEnum.values();
        for (MusicStatusEnum musicStatusEnum : statusArr) {
            if (musicStatusEnum.getCode() == code) {
                return musicStatusEnum;
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }
}
