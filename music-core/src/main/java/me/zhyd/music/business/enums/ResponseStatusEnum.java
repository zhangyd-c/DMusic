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
 * 公共的状态枚举类
 *
 * @author yadong.zhang email:yadong.zhang0415(a)gmail.com
 * @version 1.0
 * @date 2018/01/09 17:40
 * @since 1.0
 */
public enum ResponseStatusEnum {

    SUCCESS(200, "操作成功！"),
    ERROR(500, "服务器未知错误！"),
    UNAUTHORIZED(401, "尚未登录！"),
    FORBIDDEN(403, "您没有操作权限！"),
    NOT_FOUND(404, "资源不存在！"),
    LOGIN_ERROR(10001, "账号或密码错误！"),
    USER_EXIST(10002, "已存在的用户！"),
    INVALID_AUTHCODE(10003, "手机验证码无效！"),
    INVALID_TOKEN(10004, "无效的TOKEN，您没有操作权限！"),
    INVALID_ACCESS(10005, "无效的请求，该请求已过期！");

    private Integer code;
    private String message;

    ResponseStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
