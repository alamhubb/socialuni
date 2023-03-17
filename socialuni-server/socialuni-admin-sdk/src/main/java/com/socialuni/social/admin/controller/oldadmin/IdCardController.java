package com.socialuni.social.admin.controller.oldadmin;/*
package com.socialuni.admin.web.controller.oldadmin;

import com.qingchi.admin.modelvo.UserIdCardVO;
import com.socialuni.social.model.common.ResultRO;
import com.socialuni.social.constant.status.ContentStatus;
import com.socialuni.social.model.DO.user.SocialSocialUserDO;
import com.qingchi.base.repository.user.UserRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

*/
/**
 * 查询用户详情
 *
 * @author qinkaiyuan
 * @since 1.0.0
 *//*

@RestController
@RequestMapping("idCard")
public class IdCardController {
    @Resource
    private UserRepository userRepository;

    */
/*@Resource
    private UserService userService;*//*


    @PostMapping("queryIdCards")
    public ResultRO<List<UserIdCardVO>> queryIdCards() {
        //todo 这里有问题，第一个次审核过，目前等于永久审核过，存在问题，每次上传照片都应该审核
//        List<SocialSocialUserDO> userDOS = userRepository.findByIdCardStatus(ContentStatus.audit);
//        return new ResultRO<>(UserIdCardVO.userDOToVOS(userDOS));
        return null;
    }

    */
/*@PostMapping("audit")
    public ResultVO idCardAuditPass(@RequestBody IdCardAuditVO auditVO) {
        *//*
*/
/*审核痛过之后，用户状态变为已审核，颜值增加20，以后每次上传照片都需要审核，只审核通过的照片，未通过的删除
         * 审核通过的照片，变为已审核，其他变为禁用
         *
         * 现在有50个人，新注册5个，你没获取到有关系吗，没有呀
         * 新注册的就是0
         *
         * *//*
*/
/*
        List<UserIdCardVO> userIdCardVOS = auditVO.getIdCards();
        List<AdminAuditRecordDO> auditRecordDOS = new ArrayList<>();
        List<SocialUserDO> users = new ArrayList<>();
        for (UserIdCardVO user : userIdCardVOS) {
            SocialUserDO userDO = userRepository.getOne(user.getUserId());
            AdminAuditRecordDO auditRecordDO = new AdminAuditRecordDO();
            //认证通过的图片改为已认证，未通过的改为未认证，查询的时候只查询enabled和已认证的
            //记录审核操作
            //todo 这里应该判断前台传的值是否正确
            if (auditVO.getAuditResultType().equals(CommonStatus.certified)
                    || auditVO.getAuditResultType().equals(CommonStatus.notCertified)
            ) {
                auditRecordDO.setAuditResultType(auditVO.getAuditResultType());
            } else {
                return new ResultVO("系统异常，错误的审核结果类型");
            }
            if (userDO.getIdCards() != null && userDO.getIdCards().size() > 0) {
                auditRecordDO.setIdCard(userDO.getIdCards().get(0));
            } else {
                Logger.logger.warn("系统异常，用户不存在idcard却进入了审核");
            }
            auditRecordDO.setAdminUser(new AdminSocialUserDO(1));
            auditRecordDO.setUser(userDO);
            List<AdminAuditRecordImgDO> auditRecordImgDOS = new ArrayList<>();
            auditRecordDO.setAuditRecordImgs(auditRecordImgDOS);

            if (auditVO.getAuditResultType().equals(CommonStatus.certified)) {
                //获取用户图片
                List<UserImgDO> userImgDOS = userDO.getImgs();
                List<UserImgVO> userImgVOS = user.getImgs();
                for (UserImgDO userImgDO : userImgDOS) {
                    //通过的数组中如果包含当前图片
                    if (userImgVOS.stream().anyMatch(item -> item.getId().equals(userImgDO.getId()))) {
                        //设置为认证通过，并更新时间
                        userImgDO.setStatus(CommonStatus.certified);
                    } else {
                        userImgDO.setStatus(CommonStatus.notCertified);
                    }
                    userImgDO.setUpdateDate(SystemUtil.getCurrentDate());

                    //照片审核记录
                    AdminAuditRecordImgDO auditRecordImgDO = new AdminAuditRecordImgDO();
                    //审核状态
                    auditRecordImgDO.setAuditResultType(userImgDO.getStatus());
                    //审核的哪张照片
                    auditRecordImgDO.setUserImg(userImgDO);
                    auditRecordImgDO.setAdminAuditRecord(auditRecordDO);
                    auditRecordImgDOS.add(auditRecordImgDO);
                }
            }
            //idCard状态改为已认证
            userDO.setIdCardStatus(auditVO.getAuditResultType());
            userDO.setUpdateTime(SystemUtil.getCurrentDate());
            //用户添加颜值
            //todo 有bug用户已认证应该是用户状态可以为audit的还有 ,认证后再次上传照片目前不认证，以后也要认证
            //颜值什么时候会变，刚注册初始，别人看你会变，别人喜欢你会变，开会员会变，认证后会变
            //更新用户颜值
            auditRecordDOS.add(auditRecordDO);
            users.add(userDO);
        }
        userService.updateUserListFaceContent(users);
        auditRecordRepository.saveAll(auditRecordDOS);
        //记录审核通过或者不通过
        //审核人、被审核用户、idCard，通过不通过，关联表，图片，通过不通过，关联idcard
        return new ResultVO<>();
    }*//*

}*/
