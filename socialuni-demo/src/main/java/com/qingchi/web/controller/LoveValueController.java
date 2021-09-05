/*
package com.qingchi.web.controller;


import com.qingchi.web.model.DO.user.LoveValueOrderDO;
import com.qingchi.web.repsotiory.LoveValueOrderRepository;
import com.socialuni.api.model.RO.ResultRO;
import com.socialuni.sdk.constant.AppConfigConst;
import com.socialuni.constant.CommonStatus;
import com.socialuni.constant.DateTimeType;
import com.socialuni.entity.model.DO.user.UserDO;
import com.socialuni.sdk.utils.common.DateUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


*/
/**
 * 查询用户详情
 *
 * @author qinkaiyuan
 * @since 1.0.0
 *//*

@RestController
@RequestMapping("loveValue")
public class LoveValueController {
    @Resource
    private LoveValueOrderRepository loveValueOrderRepository;

    //进入这个页面要显示今天已获得奖励多少，观看次数多少，已达到上线，观看视频不再获得奖励

    @PostMapping("queryTodayLoveValue")
    public ResultRO<Integer> queryTodayLoveValue(UserDO user) {
        Integer todayLoveValue = getTodayWatchCount(user) * 10;
        ResultRO<Integer> resultRO = new ResultRO<>();
        resultRO.setData(todayLoveValue);
        return resultRO;
    }

    @PostMapping("watchVideoAd")
    public ResultRO<Integer> watchVideoAd(UserDO user, @Valid @NotNull Boolean success) {
        LoveValueOrderDO loveValueOrder = new LoveValueOrderDO();
        loveValueOrder.setEnable(success);
        loveValueOrder.setUserId(user.getId());
        loveValueOrder.setCreateTime(new Date());
        loveValueOrder.setStatus(CommonStatus.enable);
        //没有成功观看，则只记录一下用户看了视频，没看完，方便今后统计数据
        if (!success) {
            loveValueOrder.setAward(false);
            loveValueOrder.setLoveValue(0);
            loveValueOrderRepository.save(loveValueOrder);
            return new ResultRO<>();
        }
        Integer watchCount = getTodayWatchCount(user);
        Integer rewardedAdLimitCount = (Integer) AppConfigConst.appConfigMap.get(AppConfigConst.rewardedAdLimit);
        //今天已到达奖励次数上线
        if (watchCount >= rewardedAdLimitCount) {
            loveValueOrder.setAward(false);
            loveValueOrder.setLoveValue(0);
            loveValueOrderRepository.save(loveValueOrder);
            return new ResultRO<>();
        }
        //如果未达到奖励次数上限
        loveValueOrder.setAward(true);
        loveValueOrder.setLoveValue(10);
//        user.setLoveValue(user.getLoveValue() + 10);
        loveValueOrderRepository.save(loveValueOrder);
        int todayWatchCount = watchCount + 1;
        int todayLoveValue = todayWatchCount * 10;
        ResultRO<Integer> resultRO = new ResultRO<>();
        resultRO.setData(todayLoveValue);
        return resultRO;
    }

    //正在使用的
    @PostMapping("watchVideoAd2")
    public ResultRO<Map> watchVideoAd2(UserDO user, @Valid @NotNull Boolean success) {
        LoveValueOrderDO loveValueOrder = new LoveValueOrderDO();
        loveValueOrder.setEnable(success);
        loveValueOrder.setUserId(user.getId());
        loveValueOrder.setCreateTime(new Date());
        loveValueOrder.setStatus(CommonStatus.enable);
        //没有成功观看，则只记录一下用户看了视频，没看完，方便今后统计数据
        if (!success) {
            loveValueOrder.setAward(false);
            loveValueOrder.setLoveValue(0);
            loveValueOrderRepository.save(loveValueOrder);
            return new ResultRO<>();
        }
        Integer watchCount = getTodayWatchCount(user);
        Integer rewardedAdLimitCount = (Integer) AppConfigConst.appConfigMap.get(AppConfigConst.rewardedAdLimit);
        //今天已到达奖励次数上线
        if (watchCount >= rewardedAdLimitCount) {
            loveValueOrder.setAward(false);
            loveValueOrder.setLoveValue(0);
            loveValueOrderRepository.save(loveValueOrder);
            return new ResultRO<>();
        }
        //如果未达到奖励次数上限
        loveValueOrder.setAward(true);
        loveValueOrder.setLoveValue(10);
//        user.setLoveValue(user.getLoveValue() + 10);
        loveValueOrderRepository.save(loveValueOrder);
        int todayWatchCount = watchCount + 1;
        int todayLoveValue = todayWatchCount * 10;
        Map<String, Object> map = new HashMap<>();
        map.put("todayLoveValue", todayLoveValue);
//        map.put("user", new UserDetailBO(user, true).toVO());
        return new ResultRO<>(map);
    }

    private Integer getTodayWatchCount(UserDO user) {
        //获取当天0点
        Date zero = DateUtils.getTodayZeroDate();
        Date todayEnd = new Date(zero.getTime() + DateTimeType.day);
        return loveValueOrderRepository.countByUserIdAndStatusAndEnableAndAwardAndCreateTimeBetween(user.getId(), CommonStatus.enable, true, true, zero, todayEnd);
    }
}*/
