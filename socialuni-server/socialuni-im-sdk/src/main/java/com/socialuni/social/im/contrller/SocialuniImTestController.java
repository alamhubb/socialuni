package com.socialuni.social.im.contrller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.socialuni.social.common.api.constant.SocialuniContentType;
import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.api.utils.JsonUtil;
import com.socialuni.social.im.feign.SocialuniOpenImUserFeign;
import com.socialuni.social.im.logic.domain.SocialBindUserOpenImAccountDomain;
import com.socialuni.social.im.model.SocialuniImUserModel;
import com.socialuni.social.tance.sdk.facade.SocialuniUnionIdFacede;
import com.socialuni.social.user.sdk.constant.GenderTypeNumEnum;
import com.socialuni.social.user.sdk.constant.SocialuniAccountProviderType;
import com.socialuni.social.user.sdk.model.DO.SocialUserAccountDO;
import com.socialuni.social.user.sdk.model.DO.SocialuniUserDo;
import com.socialuni.social.user.sdk.repository.SocialUserAccountRepository;
import com.socialuni.social.user.sdk.repository.SocialuniUserRepository;
import com.socialuni.social.user.sdk.utils.BirthdayAgeUtil;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
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


    @GetMapping("getUserImTokenAll")
    public ResultRO<String> getUserImToken(Integer index) throws JsonProcessingException {

        log.info(String.valueOf(System.currentTimeMillis() / 1000));

//        List<String> imNotHasIds = new ArrayList<>();
//        List<Integer> userIds = socialuniUserRepository.findAllUserIds();
        log.info(String.valueOf(System.currentTimeMillis() / 1000));
        //获取所有的uuid
        List<String> uuids = SocialuniUnionIdFacede.findUuidAllByContentType(SocialuniContentType.user);

        log.info("uuids:{}", uuids.size());

        List<String> subIds = uuids.subList(index, index + 50000);

//        String json = readJsonFile("data/imIds.json");
//        List<String> imIds = (List<String>) JsonUtil.objectMapper.readValue(json, List.class);
        log.info(String.valueOf(System.currentTimeMillis() / 1000));
        //获取一个imidMap
        /*Map<String, String> imIdsMap = new HashMap<>();
        for (String uuid : imIds) {
            imIdsMap.put(uuid, uuid);
        }

        log.info("imIds:{}", imIds.size());*/

        //遍历uuid
        /*for (String uuid : uuids) {
            //如果im里面不包含则加入一个数组中
            if (!imIdsMap.containsKey(uuid)) {
                imNotHasIds.add(uuid);
            }
        }*/

        //获取缺少的uuid数量
//        log.info("缺少的uuid数量：{}", imNotHasIds.size());

        Integer i = 0;
        //遍历未注册的id
        for (String imNotHasId : subIds) {
            Integer finalI = i;
            CompletableFuture.supplyAsync(() -> {
                SocialuniUserDo mineUser = SocialuniUserUtil.getUserByUuid(imNotHasId);
//            mineUser.setNickname("名称被重置");
//            mineUser = socialuniUserRepository.savePut(mineUser);
                this.getUserImToken(mineUser);
                if (finalI % 1000 == 0) {
                    log.info("当前索引位置：{}", index + finalI);
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

    private ResultRO<String> getUserImToken(SocialuniUserDo mineUser) {
        SocialuniImUserModel socialuniImUserModel = toImUserModel(mineUser);

        String imToken = null;
        try {
            //存在脏数据，所以特殊处理
            imToken = socialuniOpenImUserFeign.getAndRefreshToken(socialuniImUserModel.getUserID());
        } catch (RuntimeException e) {
            log.info("正常逻辑未注册");
        }

        //设置openIm的key
        SocialUserAccountDO socialUserAccountDO = socialUserAccountRepository.findByProviderAndUserId(SocialuniAccountProviderType.openIm, mineUser.getUserId());

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

        }
        socialUserAccountDO = socialBindUserOpenImAccountDomain.bindOrUpdateUserOpenImAccount(mineUser, socialuniImUserModel.getUserID(), imToken);
        return ResultRO.success(socialUserAccountDO.getSessionKey());
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
