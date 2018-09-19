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
package me.zhyd.music.business.service;

import com.github.pagehelper.PageInfo;
import me.zhyd.music.business.model.WangyiPlay;
import me.zhyd.music.business.vo.WangyiPlayConditionVO;
import me.zhyd.music.framework.object.BaseService;

/**
 * @author yadong.zhang email:yadong.zhang0415(a)gmail.com
 * @version 1.0
 * @date 2018/3/5 19:04
 * @since 1.0
 */
public interface WangyiPlayService extends BaseService<WangyiPlay, Integer> {

    /**
     * 分页查询
     *
     * @param vo
     * @return
     */
    PageInfo<WangyiPlay> findPageBreakByCondition(WangyiPlayConditionVO vo);

    /**
     * 获取默认的歌单
     *
     * @return
     */
    WangyiPlay getDefault();
}
