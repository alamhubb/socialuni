package com.socialuni.social.common.sdk.component;

import cn.hutool.core.util.ClassUtil;
import com.socialuni.social.common.api.enumeration.PublishDataType;
import com.socialuni.social.common.sdk.model.PublishDataModel;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于多数据源的数据同步方式。 <br/>
 * 用于发布所有需要存储的业务数据的变动入口。<br/>
 * 该类只做记录。
 * @author wulinghui
 * @version 1.0
 * @module common
 * @date 2022/9/27 14:46
 * @since 1.0
 */
// 起不来，用ThreadLocal
//@Scope("request")
//@Repository
@Data
@Slf4j
public class SocialuniPublishDataComponent {
    public static final String UUID_NAME = "$$$Socialuni$$$SocialuniPublishDataComponent$$$";
    private final List<PublishDataModel> publishDataModelList = new ArrayList<>();

    private boolean isSimple = true;
    /**
     * 通过id删除的端点。
     * @see #deleteById
     */
    public static final String ENDPOINT_DELETE_BY_ID = "deleteById";

    /**
     * 保存: 新增或者更新。
     * @param t
     * @return
     */
    public void save(Object t) {
        publishDataOfRDMS(t,RequestMethod.PUT);
    }
    /**
     * 删除
     * @param t
     * @param <T>
     */
    public <T>  void delete(T t) {
        publishDataOfRDMS(t,RequestMethod.DELETE);
    }

    /**
     * 通过id删除
     * @param id
     * @param tClass
     * @param <T>
     */
    public <T> void deleteById(Integer id,Class<T> tClass) {
        PublishDataModel publishDataModelOfRDMS = getPublishDataModelOfRDMS(RequestMethod.DELETE);
        publishDataModelOfRDMS.setData(id);
        publishDataModelOfRDMS.setResource(ClassUtil.getClassName(tClass,this.isSimple));
        publishDataModelOfRDMS.setEndpoint(ENDPOINT_DELETE_BY_ID);
    }

    /**
     * 放入到集合中
     * @see #getPublishDataModelOfRDMS(RequestMethod)
     * @param data
     * @param method
     */
    public void publishDataOfRDMS(Object data,RequestMethod method){
        PublishDataModel publishDataModel = getPublishDataModelOfRDMS(method);
        publishDataModel.setData(data);
        publishDataModel.setResource(ClassUtil.getClassName(data,this.isSimple));
        publishDataModelList.add(publishDataModel);
    }

    /**
     * 获得属于RDMS类型的PublishDataModel
     * @param method
     * @return
     */
    private PublishDataModel getPublishDataModelOfRDMS(RequestMethod method) {
        PublishDataModel publishDataModel = new PublishDataModel();
        publishDataModel.setType(PublishDataType.RDMS);
        publishDataModel.setMethod(method);
        return publishDataModel;
    }

}
