package com.qingchi.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 查询用户详情
 *
 * @author qinkaiyuan
 * @since 1.0.0
 */
@RestController
@RequestMapping("socialuni/user")
public class UserDetailCopyController {
/*

    @PostMapping("queryUserDetail")
    public ResultRO<CenterUserDetailRO> queryUserDetail(@RequestBody @Valid UserQueryVO queryVO) {
        UserDO user = CenterUserUtil.getMineUser();
        Integer userId = UnionIdDbUtil.getUserIdByUid(queryVO.getUserId(), user);


        if (user != null && userId.equals(user.getId())) {
            return new ResultRO<>(CenterMineUserDetailROFactory.getMineUserDetail(user));
        } else {
            Optional<UserDO> userOptional = userRepository.findById(userId);
            if (userOptional.isPresent()) {
                UserDO userDO = userOptional.get();
                return new ResultRO<>(CenterUserDetailROFactory.getUserDetailRO(userDO));
            }
        }

        throw new SocialBusinessException("无法查看不存在的用户");
    }


    @PostMapping("addImg")
    public ResultRO<CenterMineUserDetailRO> addImg(@RequestBody @Valid SocialUserImgAddQO img) {
        UserDO mineUser = CenterUserUtil.getMineUser();
        if (!UserStatus.enable.equals(mineUser.getStatus())) {
            throw new SocialBusinessException(ErrorMsg.userMaybeViolation);
        }
        List<UserImgDO> userImgDOS = UserImgDOUtils.getImgs(mineUser.getId());
        if (userImgDOS.size() > 2) {
            throw new SocialBusinessException("最多上传3张照片，请删除后继续！");
        }

        UserImgDO userImgDO = img.toUserImgDO(mineUser, imgUrl);
        //如果用户已认证，则上传的照片必须为已认证的
        */
/*if (user.getIsSelfAuth()) {
            Optional<IdentityImgDO> optionalIdentityImgDO = identityImgRepository.findFirstByUserIdAndStatusOrderByIdDesc(user.getId(), UserStatus.enable);
            if (optionalIdentityImgDO.isPresent()) {
                IdentityImgDO identityImgDO = optionalIdentityImgDO.get();
                boolean isAuth = TencentCloud.imgAuth(userImgDO.getSrc(), identityImgDO.getSrc());
                //不为本人，则提示，为本人则继续下面的逻辑
                if (!isAuth) {
                    return new ResultRO<>("用户认证后，只能上传与认证信息相符的本人清晰露脸的真实照片");
                }
            } else {
                QingLogger.logger.error("系统异常，不应该出现已认证却没有记录的情况");
                return new ResultRO<>(ErrorCode.SYSTEM_ERROR);
            }
        }*//*

        mineUser = userRepository.save(mineUser);
        userImgRepository.save(userImgDO);

        CenterMineUserDetailRO centerMineUserDetailRO = CenterMineUserDetailROFactory.getMineUserDetail(mineUser);
        return new ResultRO<>(centerMineUserDetailRO);
    }

    @PostMapping("idCard/add")
    public ResultRO<CenterMineUserDetailRO> addIdCard(UserDO user, @RequestBody IdCardVO idCard) {
        */
/*if (StringUtils.isEmpty(user.getIdCardStatus()) || user.getIdCardStatus().equals(CommonStatus.init)) {
            UserDO userDO = userService.addIdCard(user, idCard.toDO());
            return new ResultRO<>(new UserDetailBO(userDO, true).toVO());
        }*//*

        throw new SocialBusinessException("已经提交过认证申请，暂无法再次提交!");
    }

    @PostMapping("deleteImg")
    public ResultRO<CenterMineUserDetailRO> deleteImg(@RequestBody SocialUserImgDeleteQO img) {

        UserDO user = CenterUserUtil.getMineUser();
        if (!UserStatus.enable.equals(user.getStatus())) {
            throw new SocialBusinessException(ErrorMsg.userMaybeViolation);
        }
        List<UserImgDO> userImgDOS = UserImgDOUtils.getImgs(user.getId());
        if (userImgDOS.size() > 1) {
            Integer userImgIdResult = UnionIdDbUtil.getUserImgIdByUid(img.getId(), user);
            Optional<UserImgDO> userImgDOOptional = userImgRepository.getUserImgByUserIdAndId(user.getId(), userImgIdResult);
            if (userImgDOOptional.isPresent()) {
                UserImgDO userImg = userImgDOOptional.get();
                userImg.setStatus(ContentStatus.delete);
                userImg.setUpdateTime(new Date());
                userImgRepository.save(userImg);
            }

            CenterMineUserDetailRO centerMineUserDetailRO = CenterMineUserDetailROFactory.getMineUserDetail(user);
            return new ResultRO<>(centerMineUserDetailRO);
        } else {
            throw new SocialBusinessException("请至少保留一张照片");
        }
    }

    @Resource
    private ShellOrderService shellOrderService;
    @Resource
    private UserContactRepository userContactRepository;

    @PostMapping("getUserContact")
    public ResultRO<String> getUserContact(UserDO user, @RequestBody @Valid @NotNull UserQueryVO queryVO) {
        */
/*Integer userShell = user.getShell();
        if (userShell < 10) {
            QingLogger.logger.error("系统被攻击，不该触发这里，用户不够10贝壳");
            return new ResultRO<>(ErrorCode.SYSTEM_ERROR);
        }

        ResultRO<Integer> userIdResult = UnionIdDbUtil.getUserIdByUid(queryVO.getUserId(),  user);
        if (userIdResult.hasError()) {
            return new ResultRO(userIdResult);
        }
        Integer userId = userIdResult.getData();

        Optional<UserDO> userDOOptional = userRepository.findById(userId);
        if (userDOOptional.isPresent()) {
            UserDO beUser = userDOOptional.get();
            Optional<UserContactDO> userContactDOOptional = userContactRepository.findFirstByUserIdAndBeUserIdAndStatusAndType(user.getId(), beUser.getId(), BaseStatus.enable, ExpenseType.contact);
            //已经获取过了，不应该还能获取
            if (userContactDOOptional.isPresent()) {
                QingLogger.logger.error("已经获取过用户联系方式了，不应该还能获取");
                return new ResultRO<>(ErrorCode.SYSTEM_ERROR);
            }
            return shellOrderService.createAndSaveContactAndShellOrders(user, beUser, ExpenseType.contact);
        }*//*

        throw new SocialSystemException("获取联系方式");
    }

    @PostMapping("switchUserContact")
    public ResultRO<String> switchUserContact(UserDO user, Boolean openContact) {
        if (openContact == null) {
            throw new SocialParamsException("切换查看");
        }
        Optional<UserDetailDO> userDetailDOOptional = userDetailRepository.findFirstOneByUserId(user.getId());
        if (userDetailDOOptional.isPresent()) {
            UserDetailDO userDetail = userDetailDOOptional.get();
            userDetail.setOpenContact(openContact);
            userDetailRepository.save(userDetail);
            return new ResultRO<>();
        }
        throw new SocialParamsException("切换查看");
    }

    @Resource
    private DestroyAccountRepository destroyAccountRepository;

    @PostMapping("destroyAccount")
    public ResultRO<String> destroyAccount(UserDO user) {
        if (user == null) {
            throw new SocialNullUserException();
        }
        DestroyAccountDO destroyAccount = new DestroyAccountDO();
        Date cur = new Date();
        destroyAccount.setCreateTime(cur);
        //七天后的时间
        destroyAccount.setEndTime(new Date(cur.getTime() + DateTimeType.day * 7));
        destroyAccount.setStatus(UserStatus.enable);
        destroyAccount.setUserId(user.getId());
        destroyAccountRepository.save(destroyAccount);
        return new ResultRO<>();
    }
*/


    /*@PostMapping("setFilter")
    public ResultVO<?> setFilter(@RequestBody SetUserFilterVO setVO, UserDO user) {
        Optional<UserDetailDO> userDetailDOOptional = userDetailRepository.findFirstOneByUser(user);
        if (userDetailDOOptional.isPresent()) {
            UserDetailDO userDetailDO = userDetailDOOptional.get();
            String gender = setVO.getGender();
            if (GenderType.allGenders.contains(gender)) {
                userDetailDO.setGender(gender);
            } else {
                Logger.logger.error("有人传入了错误的参数");
                return new ResultVO<>(ErrorCode.SYSTEM_ERROR);
            }
            userDetailDO.setMinAge(setVO.getMinAge());
            userDetailDO.setMaxAge(setVO.getMaxAge());
            userDetailRepository.save(userDetailDO);
            return new ResultVO<>();
        }
        return new ResultVO<>(ErrorCode.SYSTEM_ERROR);
    }*/

}