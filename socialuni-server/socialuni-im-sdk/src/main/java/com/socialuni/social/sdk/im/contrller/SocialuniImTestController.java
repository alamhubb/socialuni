package com.socialuni.social.sdk.im.contrller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.socialuni.social.common.api.constant.SocialuniContentType;
import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.api.utils.JsonUtil;
import com.socialuni.social.sdk.im.feign.SocialuniOpenImUserFeign;
import com.socialuni.social.sdk.im.logic.domain.SocialBindUserOpenImAccountDomain;
import com.socialuni.social.sdk.im.model.SocialuniImUserModel;
import com.socialuni.social.tance.sdk.facade.SocialuniUnionIdFacede;
import com.socialuni.social.user.sdk.constant.GenderTypeNumEnum;
import com.socialuni.social.user.sdk.constant.SocialuniAccountProviderType;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.common.sdk.dao.repository.SocialUserAccountRepository;
import com.socialuni.social.common.sdk.dao.repository.SocialuniUserRepository;
import com.socialuni.social.report.sdk.utils.BirthdayAgeUtil;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.*;
import java.util.*;
import java.util.concurrent.CompletableFuture;

/**
 * @author qinkaiyuan
 * @date 2019-09-28 11:09
 * 前端初始化内容
 */
@RestController
@Slf4j
@RequestMapping("socialuni/imtest")
public class SocialuniImTestController {

    @Resource
    SocialUserAccountRepository socialUserAccountRepository;
    @Resource
    SocialBindUserOpenImAccountDomain socialBindUserOpenImAccountDomain;
    @Resource
    SocialuniOpenImUserFeign socialuniOpenImUserFeign;

    @Resource
    SocialuniUserRepository socialuniUserRepository;


    //    @GetMapping("getUserImTokenAll")
    public ResultRO<String> getUserImToken(Integer index) throws JsonProcessingException {

        log.info(String.valueOf(System.currentTimeMillis() / 1000));


//        List<Integer> userIds = socialuniUserRepository.findAllUserIds();
        //获取所有的uuid
        List<Integer> allUserIds = SocialuniUnionIdFacede.findAllIdsByContentType(SocialuniContentType.user);
        log.info(String.valueOf(System.currentTimeMillis() / 1000));


        log.info("uuids:{}", allUserIds.size());

//        List<String> subIds = uuids.subList(index, index + 50000);

//        String json = readJsonFile("data/imIds.json");
//        List<String> imIds = (List<String>) JsonUtil.objectMapper.readValue(json, List.class);
        List<Integer> imUserIds = socialUserAccountRepository.findAllUserIdsAllByProvider(SocialuniAccountProviderType.openIm);
        log.info(String.valueOf(System.currentTimeMillis() / 1000));
        //获取一个imidMap
        Map<Integer, Integer> imIdsMap = new HashMap<>();
        for (Integer uuid : imUserIds) {
            imIdsMap.put(uuid, uuid);
        }
        log.info(String.valueOf(System.currentTimeMillis() / 1000));
        log.info("imIds:{}", imUserIds.size());

        List<Integer> imNotHasIds = new ArrayList<>();
        //遍历uuid
        for (Integer uuid : allUserIds) {
            //如果im里面不包含则加入一个数组中
            if (!imIdsMap.containsKey(uuid)) {
                imNotHasIds.add(uuid);
            }
        }
        log.info(String.valueOf(System.currentTimeMillis() / 1000));
        log.info("imNotHasIds:{}", imNotHasIds.size());

        //获取缺少的uuid数量
//        log.info("缺少的uuid数量：{}", imNotHasIds.size());

        Integer i = 0;
        //遍历未注册的id
        for (Integer imNotHasId : imNotHasIds) {
            Integer finalI = i;
            CompletableFuture.supplyAsync(() -> {
                SocialuniUserDo mineUser = SocialuniUserUtil.getAndCheckUserNotNull(imNotHasId);
                //数据库存在编码问题，统一名称消除编码问题
//                mineUser.setNickname("名称被重置");

                // 注册到Im
                SocialuniImUserModel imUserModel = new SocialuniImUserModel();

                String mineUserUid = SocialuniUnionIdFacede.getUuidByUnionIdNotNull(mineUser.getUserId());

                imUserModel.setUserID(mineUserUid);
                imUserModel.setNickname("名称被重置");
                imUserModel.setFaceURL(mineUser.getAvatar());
                imUserModel.setGender(GenderTypeNumEnum.getValueByName(mineUser.getGender()));
//        imUserModel.setPhoneNumber(mineUser.getPhoneNum());
                imUserModel.setBirth(946656000);
                imUserModel.setCreateTime(new Date());

//            mineUser = socialuniUserRepository.savePut(mineUser);
                this.getUserImToken(mineUser, imUserModel);
                if (finalI % 1000 == 0) {
                    log.info("当前索引位置：{}", finalI);
                    log.info(String.valueOf(System.currentTimeMillis() / 1000));
                }
                return null;
            }).exceptionally(e -> {
                e.printStackTrace();
                log.info(e.getMessage());
                return null;
            });
            i++;
        }

        /*Integer count = 50000;


        Integer endIndex = startIndex + count;
        if (endIndex > userIds.size()) {
            endIndex = userIds.size();
        }

        List<Integer> newIds = userIds.subList(startIndex, endIndex);
        for (int i = 0; i < newIds.size() - 1; i++) {
            Integer newId = newIds.get(i);
            if (i % 100 == 0) {
                log.info("index:{}", i);
                log.info(String.valueOf(System.currentTimeMillis()));
            }
            SocialuniUserDo mineUser = SocialuniUserUtil.getUserNotNull(newId);
            CompletableFuture.supplyAsync(() -> this.getUserImToken(mineUser)).exceptionally(e -> {
                e.printStackTrace();
                log.info(e.getMessage());
                return null;
            });
        }*/

//        SocialuniUserDo mineUser = SocialuniUserUtil.getMineUserNotNull();

        return null;

    }

    private ResultRO<String> getUserImToken(SocialuniUserDo mineUser, SocialuniImUserModel socialuniImUserModel) {

       /* String imToken = null;
        try {
            //存在脏数据，所以特殊处理
            imToken = socialuniOpenImUserFeign.getAndRefreshToken(socialuniImUserModel.getUserID());
        } catch (RuntimeException e) {
//            log.info("正常逻辑未注册");
        }

        //设置openIm的key
        *//*SocialUserAccountDO socialUserAccountDO = socialUserAccountRepository.findByProviderAndUserId(SocialuniAccountProviderType.openIm, mineUser.getUserId());

        if (socialUserAccountDO == null) {
            if (StringUtils.isEmpty(imToken)) {
                imToken = socialuniOpenImUserFeign.userLogin(socialuniImUserModel);
            }
        } else {
            try {
                //如果为登录，则刷新token
                imToken = socialuniOpenImUserFeign.getAndRefreshToken(socialuniImUserModel.getUserID());
            } catch (RuntimeException e) {
                imToken = socialuniOpenImUserFeign.userLogin(socialuniImUserModel);
                ResultRO<String> resultRO = this.getUserImToken(mineUser);
                imToken = resultRO.getData();
            }

        }*//*
        try {
            //如果为登录，则刷新token
            imToken = socialuniOpenImUserFeign.userLogin(socialuniImUserModel);
        } catch (RuntimeException e) {
            e.printStackTrace();
//            log.info("用户的生日：{}", mineUser.getBirthday());
//            log.info("用户的生日：{}", BirthdayAgeUtil.getBirthDayByBirthString(mineUser.getBirthday()));
            imToken = socialuniOpenImUserFeign.getAndRefreshToken(socialuniImUserModel.getUserID());
//            ResultRO<String> resultRO = this.getUserImToken(mineUser);
//            imToken = resultRO.getData();
        }
        SocialUserAccountDO socialUserAccountDO = socialBindUserOpenImAccountDomain.bindOrUpdateUserOpenImAccount(mineUser, socialuniImUserModel.getUserID(), imToken);
        return ResultRO.success(socialUserAccountDO.getSessionKey());*/
        return null;
    }


    public static SocialuniImUserModel toImUserModel(SocialuniUserDo mineUser) {
        // 注册到Im
        SocialuniImUserModel imUserModel = new SocialuniImUserModel();

        String mineUserUid = SocialuniUnionIdFacede.getUuidByUnionIdNotNull(mineUser.getUserId());

        imUserModel.setUserID(mineUserUid);
        imUserModel.setNickname(mineUser.getNickname());
        imUserModel.setFaceURL(mineUser.getAvatar());
        imUserModel.setGender(GenderTypeNumEnum.getValueByName(mineUser.getGender()));
//        imUserModel.setPhoneNumber(mineUser.getPhoneNum());
        imUserModel.setBirth((int) (BirthdayAgeUtil.getBirthDayByBirthString(mineUser.getBirthday()).getTime() / 1000));
        imUserModel.setCreateTime(new Date());
        return imUserModel;
    }

    public static void main(String[] args) throws JsonProcessingException {
        String json = readJsonFile("data/imIds.json");
        System.out.println(JsonUtil.objectMapper.readValue(json, List.class));
    }

    /**
     * 读取  JSON 配置文件
     */
    public static String readJsonFile(String fileName) {
        FileReader fileReader = null;
        Reader reader = null;
        try {
            File jsonFile = ResourceUtils.getFile("classpath:" + fileName);
            fileReader = new FileReader(jsonFile);
            reader = new InputStreamReader(new FileInputStream(jsonFile), "utf-8");
            int ch;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            String jsonStr = sb.toString();
            return jsonStr;
        } catch (IOException e) {
            e.printStackTrace();
            //logger.error("读取文件报错", e);
            System.out.println("读取文件报错!" + e);
        } finally {
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
