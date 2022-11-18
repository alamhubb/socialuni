/*
package com.socialuni.admin.web.controller.oldadmin;

import com.qingchi.admin.modelvo.FaceQueryVO;
import com.qingchi.admin.modelvo.IdentityVO;
import com.socialuni.social.model.common.ResultRO;
import com.qingchi.base.model.user.IdentityImgDO;
import com.qingchi.base.repository.user.IdentityImgRepository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

*/
/**
 * @author qinkaiyuan
 * @date 2020-05-02 16:42
 *//*

@RestController
@RequestMapping("user")
public class IdentityController {
    @Resource
    private IdentityImgRepository identityImgRepository;

    @RequestMapping("getIdentities")
    public ResultRO<List<IdentityVO>> getIdentity() {
        List<IdentityImgDO> identityImgDOS = identityImgRepository.findFirst20ByOrderByIdDesc();
        return new ResultRO<>(IdentityVO.DOTOVOS(identityImgDOS));
    }

    @RequestMapping("getIdentityPage")
    public ResultRO<List<IdentityVO>> getIdentityPage(@RequestBody FaceQueryVO queryVO) {
//        List<IdentityImgDO> identityImgDOS = identityImgRepository.findFirst20ByUserGenderInOrderByUserFaceValueDesc(genders, pageable);
        List<IdentityImgDO> identityImgDOS = new ArrayList<>();
        return new ResultRO<>(IdentityVO.DOTOVOS(identityImgDOS));
    }
}
*/
