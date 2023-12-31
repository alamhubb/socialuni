package com.socialuni.social.common.api.model;


import com.socialuni.social.common.api.enumeration.PublishDataType;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 该设置满足<a href='https://www.jianshu.com/p/8b769356ee67'>restful接口设计规范</a>
 */
@Data
public class PublishDataModel{
    /**
     * 类别
     * @see PublishDataType
     */
    private PublishDataType type;
    /**
     * 资源名称
     */
    private String resource;
    /**
     * 行为方式 post为增加，put为修改或者增加，delete为删除。
     * @see
     * @see RequestMethod
     */
    private RequestMethod method;
    /**
     * 目标终点/站点。 <br/>
     * 用于:  如删除中有 delete、deleteById、deleteAll。等不同的名词操作。
     */
    private String endpoint;
    /**
     * 数据
     */
    private Object data;   //对象
}
