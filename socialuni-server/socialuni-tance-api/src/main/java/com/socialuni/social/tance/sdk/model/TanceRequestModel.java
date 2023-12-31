package com.socialuni.social.tance.sdk.model;


import lombok.Data;

/**
 * @author wulinghui
 * @version 1.0
 * @module api-factory
 * @date 2022/9/1 10:29
 * @since 1.0
 */
@Data
public class TanceRequestModel  {
//    private TanceModel tanceModel;
    /**
     * 开启租户的操作。
     */
    private boolean enable = true;

//    public static TanceRequestModel getInstance(){
//        return (TanceRequestModel) THREAD_LOCAL.get();
//    }
    //
    public void buildTanceModel(Long tanceId,Long appId,Long circleId){
        if(enable){
        }
    }

    public static boolean enable(){
//        TanceRequestModel instance = TanceRequestModel.getInstance();
        // junit测试  java.lang.NullPointerException。
        return false;
//        if(instance != null){
////            return instance.isEnable();
//            return false;
//        }else{
//            return false;
//        }
    }

    public static Long tanceId(){
        if(enable()){
//            return TanceRequestModel.getInstance().getTanceId();
        }else {

        }
        return null;
    }

    /////////////////////
    ///   get
    //////////////////////

    public Long getTanceId() {
        return 1l;
    }

    public Long getAppId() {
        return 1l;
    }

    public Long getCircleId() {
        return 1l;
    }
}

