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
package me.zhyd.music.framework.object;

/**
 * 公共业务接口类
 *
 * @author yadong.zhang email:yadong.zhang0415(a)gmail.com
 * @version 1.0
 * @date 2018/3/5 18:46
 * @since 1.0
 */
public interface BaseService<T, PK> {

    /**
     * 保存一个实体，null的属性不会保存，会使用数据库默认值
     *
     * @param entity
     *         实体业务对象
     * @return 当前实体业务对象
     */
    T insert(T entity);

    /**
     * 根据主键字段进行删除，方法参数必须包含完整的主键属性
     *
     * @param primaryKey
     *         主键
     * @return true: 删除成功 false: 删除失败
     */
    boolean removeByPrimaryKey(PK primaryKey);

    /**
     * 根据主键更新属性不为null的值
     *
     * @param entity
     *         实体业务对象
     * @return true: 更新成功 false: 更新失败
     */
    boolean updateSelective(T entity);

    /**
     * 根据主键字段进行查询，方法参数必须包含完整的主键属性，查询条件使用等号
     *
     * @param primaryKey
     *         主键
     * @return 业务对象
     */
    T getByPrimaryKey(PK primaryKey);
}