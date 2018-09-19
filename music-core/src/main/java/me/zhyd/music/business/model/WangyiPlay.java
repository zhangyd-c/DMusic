/**
 * Copyright [2016-2017] [yadong.zhang]
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

import com.fasterxml.jackson.annotation.JsonIgnore;
import me.zhyd.music.business.enums.MusicStatusEnum;
import me.zhyd.music.persistence.beans.BizWangyiPlay;

import java.util.Date;

/**
 * @author yadong.zhang email:yadong.zhang0415(a)gmail.com
 * @version 1.0
 * @date 2018/3/5 19:00
 * @since 1.0
 */
public class WangyiPlay {
    private BizWangyiPlay bizWangyiPlay;

    public WangyiPlay() {
        this.bizWangyiPlay = new BizWangyiPlay();
    }

    public WangyiPlay(BizWangyiPlay bizWangyiPlay) {
        this.bizWangyiPlay = bizWangyiPlay;
    }

    @JsonIgnore
    public BizWangyiPlay getBizWangyiPlay() {
        return bizWangyiPlay;
    }

    public void setBizWangyiPlay(BizWangyiPlay bizWangyiPlay) {
        this.bizWangyiPlay = bizWangyiPlay;
    }

    public Integer getId() {
        return this.bizWangyiPlay.getId();
    }

    public void setId(Integer id) {
        this.bizWangyiPlay.setId(id);
    }

    public String getPlayId() {
        return this.bizWangyiPlay.getPlayId();
    }

    public void setPlayId(String name) {
        this.bizWangyiPlay.setPlayId(name);
    }

    public String getPlayName() {
        return this.bizWangyiPlay.getPlayName();
    }

    public void setPlayName(String name) {
        this.bizWangyiPlay.setPlayName(name);
    }

    public Integer getStatus() {
        return this.bizWangyiPlay.getStatus();
    }

    public void setStatus(MusicStatusEnum status) {
        if (null == status) {
            return;
        }
        this.bizWangyiPlay.setStatus(status.getCode());
    }

    public void setStatus(Integer status) {
        this.bizWangyiPlay.setStatus(status);
    }

    public Date getCreateTime() {
        return this.bizWangyiPlay.getCreateTime();
    }

    public void setCreateTime(Date createTime) {
        this.bizWangyiPlay.setCreateTime(createTime);
    }

    public Date getUpdateTime() {
        return this.bizWangyiPlay.getUpdateTime();
    }

    public void setUpdateTime(Date updateTime) {
        this.bizWangyiPlay.setUpdateTime(updateTime);
    }
}
