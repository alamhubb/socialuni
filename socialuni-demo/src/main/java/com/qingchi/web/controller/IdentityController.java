/*
package com.qingchi.web.controller;


import com.qingchi.web.repsotiory.IdentityImgRepository;
import com.socialuni.api.model.RO.ResultRO;
import com.socialuni.entity.model.DO.user.UserDO;
import com.socialuni.sdk.model.RO.UserImgVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("identity")
public class IdentityController {
    @Resource
    private IdentityImgRepository identityImgRepository;
    @Value("${config.qq.cos.imgUrl}")
    private String imgUrl;

    @PostMapping("auth")
    public ResultRO<?> identityAuth(UserDO user, @RequestBody @Valid UserImgVO img) {
        //第一版，只支持机器认证，3次每人只能认证4次,3次系统审核1次人工审核，0,1,2,3最大数是3
        */
/*if (user.getAuthNum() < 3) {
            IdentityImgDO identityImgDO = new UserImgBO(img).toIdentityImgDO(user, imgUrl);
            String identityImgUrl = identityImgDO.getSrc() + "!normal";
            //toDO 初始需要所有用户的改为0
            user.setAuthNum(user.getAuthNum() + 1);
            //系统自动审核
            List<UserImgDO> userImgDOS = UserImgUtils.getImgs(user.getId());
            //如果有一个验证成功则改为true
            boolean authFlag = false;
            Date curDate = new Date();

            //如果用户没有上传过，则直接使用认证照片自行认证
            if (ObjectUtils.isEmpty(userImgDOS)) {
                authFlag = TencentCloud.imgAuth(identityImgUrl, identityImgUrl);
            } else {
                //循环对比，
                for (UserImgDO userImgDO : userImgDOS) {
                    boolean isAuth = TencentCloud.imgAuth(userImgDO.getSrc(), identityImgUrl);
                    userImgDO.setIsSelfAuth(isAuth);
                    userImgDO.setUpdateTime(curDate);
                    //如果有一个验证成功则改为true
                    if (isAuth) {
                        authFlag = true;
                    }
                }
            }
            //认证失败也记录但是不做处理，用户认证次数加1,用户只能认证3次，3次以后需要联系客服
            //更新用户状态为已认证。是否记录失败的认证照片呢，记录，先不做处理，认证成功的
            //循环认证，有一张认证成功了，就认证成功了，需要把未认证成功的照片删除
            if (authFlag) {
                //设置为已认证
                user.setIsSelfAuth(true);
                identityImgDO.setStatus(BaseStatus.enable);
                user.setUpdateTime(curDate);

                //userimg不为空
                if (!ObjectUtils.isEmpty(userImgDOS)) {
                    for (UserImgDO userImgDO : userImgDOS) {
                        //如果未认证，将非本人照片删除。
                        if (!userImgDO.getIsSelfAuth()) {
                            userImgDO.setStatus(CommonStatus.authFail);
                            userImgDO.setDeleteReason("认证失败");
                        }
                    }
                }
            }
            identityImgDO = identityImgRepository.save(identityImgDO);
            //认证成功
            if (authFlag) {
                //前台提示认证成功，
                return new ResultRO<>(new UserDetailBO(UserUtils.get(identityImgDO.getUserId()),  true).toVO());
            } else {
                return new ResultRO<>("认证失败，请在个人信息照片墙中上传本人清晰露脸的真实照片后再进行认证");
            }
            //            if (user.getAuthNum() < 3) {
            *//*
*/
/*} else {
                //改为审核中，由人工审核
                identityImgDO.setStatus(CommonStatus.authFail);
                identityImgDO = identityImgRepository.save(identityImgDO);
            }*//*
*/
/*
        } else {
            return new ResultRO<>("已达到审认证次数上线，" + ErrorMsg.CONTACT_SERVICE);
        }*//*

        return null;
    }
}
*/
