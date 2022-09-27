package com.socialuni.social.common.component;

import cn.hutool.core.util.ClassUtil;
import com.socialuni.social.common.facade.SocialuniRepositoryFacade;
import lombok.Builder;
import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 用于多数据源的数据同步方式。 <br/>
 * 用于发布所有需要存储的业务数据的变动入口。<br/>
 * 要求所有的增删改查都使用该类，才能实现。
 * @author wulinghui
 * @version 1.0
 * @module common
 * @date 2022/9/27 14:46
 * @since 1.0
 */
@Scope("request")
@Repository
@Data
public class SocialuniPublishDataRepository {
    private final List<PublishDataModel> publishDataModelList = new ArrayList<>();
    @Resource
    private SocialuniCommonRepository commonRepository;
    private boolean isSimple = true;
    /**
     * 通过id删除的端点。
     * @see #deleteById
     */
    public static final String ENDPOINT_DELETE_BY_ID = "deleteById";

    /**
     * 保存: 新增或者更新。
     * @param t
     * @param <T>
     * @return
     */
    public <T> T save(T t) {
        publishDataOfRDMS(t,RequestMethod.PUT);
        return commonRepository.save(t);
    }
    /**
     * 删除
     * @param t
     * @param <T>
     */
    public <T>  void delete(T t) {
        publishDataOfRDMS(t,RequestMethod.DELETE);
        commonRepository.delete(t);
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
        commonRepository.deleteById(id,tClass);
    }

    /**
     * 放入到集合中
     * @see #getPublishDataModelOfRDMS(RequestMethod)
     * @param data
     * @param method
     */
    private void publishDataOfRDMS(Object data,RequestMethod method){
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

    /**
     * 类别：属于RDMS、mongo、等等不同类型的数据库。
     */
    public enum PublishDataType{
        RDMS,MONGO
    }

}
