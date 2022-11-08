package com.socialuni.social.common.sdk.event.ddd;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;

import java.util.Map;

/**
 * 抽象的json实现父类。
 * 用于领域隔离的数据传输。（实体不共通，也就是说object （编译阶段）转化不了的）
 * @author wulinghui
 * @version 1.0
 * @module Socialuni
 * @date 2022/11/4 10:27
 * @since 1.0
 */
public abstract class AbstractJsonEventConsum implements EventConsum{
    @Override
    public boolean canConsum(String topicName, Object event) {
        String matchTopicName = getMatchTopicName();
        if(matchTopicName == null){
            return StrUtil.equals(getMatchClassName(), ClassUtil.getClassName(event,false));
        }else{
            return StrUtil.equals(matchTopicName,topicName);
        }
    }

    @Override
    public final void consumEvent(Object event) {
        Map<String, Object> stringObjectMap = BeanUtil.beanToMap(event);
        this.consumEvent(stringObjectMap);
    }
    public abstract void consumEvent(Map<String, Object> stringObjectMap);
    /**
     * 匹配优先最高
     * @return
     * @see #canConsum(String, Object)
     */
    public String getMatchTopicName(){
        return null;
    }

    /**
     * 最后按照类完全限定名匹配、
     * @return
     * @see #canConsum(String, Object)
     */
    public abstract String getMatchClassName();
}
