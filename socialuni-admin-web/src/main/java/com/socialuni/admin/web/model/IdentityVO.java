/*
package com.socialuni.admin.web.model;

import com.qingchi.base.model.user.IdentityImgDO;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

*/
/**
 * @author qinkaiyuan
 * @date 2020-05-02 16:43
 *//*

@Data
public class IdentityVO {
    private UserVO user;

    private String src;

    private String status;

    public IdentityVO(IdentityImgDO identityImgDO) {
//        this.user = new UserVO(identityImgDO.getUser());
        this.src = identityImgDO.getSrc();
        this.status = identityImgDO.getStatus();
    }

    public static List<IdentityVO> DOTOVOS(List<IdentityImgDO> identityImgDOS) {
        return identityImgDOS.stream().map(IdentityVO::new).collect(Collectors.toList());
    }
}
*/
