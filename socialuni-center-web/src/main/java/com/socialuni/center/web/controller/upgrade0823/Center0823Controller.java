package com.socialuni.center.web.controller.upgrade0823;

import com.socialuni.center.web.factory.DO.UnionIdDOFactory;
import com.socialuni.center.web.model.DO.UnionIdDO;
import com.socialuni.center.web.repository.UnionIdRepository;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.constant.ContentType;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.model.model.RO.app.SocialAppLaunchDataRO;
import com.socialuni.social.sdk.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2019-09-28 11:09
 * 前端初始化内容
 */
@RestController
@RequestMapping("0823")
public class Center0823Controller {
    @Resource
    TestUnionIdService testUnionIdService;

    @GetMapping("unionid")
    public ResultRO<Void> unionid() {
//        testUnionIdService.unionid();
        return ResultRO.success();
    }
}
