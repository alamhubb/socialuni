package com.socialuni.social.user.sdk.logic.manage;

import com.socialuni.social.common.api.exception.exception.SocialSystemException;
import com.socialuni.social.common.sdk.dao.facede.SocialuniUserRepositoryFacede;
import com.socialuni.social.user.sdk.dao.DO.SocialuniUserOpenIdDO;
import com.socialuni.social.user.sdk.dao.repository.SocialuniUserOpenIdRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

@Component
@Slf4j
public class SocialuniUserOpenIdManager {

    @Resource
    SocialuniUserOpenIdRepository socialuniUserOpenIdRepository;

    public SocialuniUserOpenIdDO create(Integer userId) {
        SocialuniUserOpenIdDO socialuniUserOpenIdDO = SocialuniUserRepositoryFacede.findByUserId(userId, SocialuniUserOpenIdDO.class);
        if (socialuniUserOpenIdDO != null) {
            throw new SocialSystemException("重复创建用户");
        }
        String userOpenId = getCheckNumStr();

        socialuniUserOpenIdDO = new SocialuniUserOpenIdDO();
        socialuniUserOpenIdDO.setUserId(userId);
        socialuniUserOpenIdDO.setUserOpenId(userOpenId);

        socialuniUserOpenIdDO = socialuniUserOpenIdRepository.save(socialuniUserOpenIdDO);

        return socialuniUserOpenIdDO;
    }


    public String getCheckNumStr() {
        String userOpenId = generateRandomNumber();

        Boolean cantUse = checkStrContainSameNumbers(userOpenId);

        if (cantUse) {
            log.info("生成了不允许使用的id:{}", userOpenId);
            return getCheckNumStr();
        }

        SocialuniUserOpenIdDO socialuniUserOpenIdDO = socialuniUserOpenIdRepository.findByUserOpenId(userOpenId);

        if (socialuniUserOpenIdDO != null) {
            log.info("生成了已存在的id:{}", userOpenId);
            return getCheckNumStr();
        }

        return userOpenId;
    }

    public static String generateRandomNumber() {
        Random rand = new Random();
        int min = 10000;
        int max = 99999;
        return "8" + (rand.nextInt(max - min + 1) + min);
    }

    public static Boolean checkStrContainSameNumbers(String fullNumStr) {
        return hasFiveSameDigits(fullNumStr) || hasFiveConsecutiveDigits(fullNumStr) || hasFourConsecutiveSameDigits(fullNumStr);
    }

    //java判断6位字符串是否包含5个相同的数字
    public static boolean hasFiveSameDigits(String input) {
        Map<Character, Integer> digitCountMap = new HashMap<>();

        for (char digit : input.toCharArray()) {
            digitCountMap.put(digit, digitCountMap.getOrDefault(digit, 0) + 1);
            if (digitCountMap.get(digit) >= 5) {
                return true;
            }
        }

        return false;
    }


    public static boolean hasFourSameDigits(String input) {
        Map<Character, Integer> digitCountMap = new HashMap<>();

        for (char digit : input.toCharArray()) {
            digitCountMap.put(digit, digitCountMap.getOrDefault(digit, 0) + 1);
            if (digitCountMap.get(digit) >= 4) {
                return true;
            }
        }

        return false;
    }

    //java 判断 一个6数字位字符串是否包含5个连续的数字
    public static boolean hasFiveConsecutiveDigits(String input) {
        if (input.length() < 5) {
            return false;
        }

        for (int i = 0; i <= input.length() - 5; i++) {
            int digit1 = Character.getNumericValue(input.charAt(i));
            int digit2 = Character.getNumericValue(input.charAt(i + 1));
            int digit3 = Character.getNumericValue(input.charAt(i + 2));
            int digit4 = Character.getNumericValue(input.charAt(i + 3));
            int digit5 = Character.getNumericValue(input.charAt(i + 4));

            if (digit2 == digit1 + 1 && digit3 == digit2 + 1 && digit4 == digit3 + 1 && digit5 == digit4 + 1) {
                return true;
            }
        }

        return false;
    }

    public static boolean hasFourConsecutiveSameDigits(String input) {
        if (input.length() < 4) {
            return false;
        }

        for (int i = 0; i <= input.length() - 4; i++) {
            if (input.charAt(i) == input.charAt(i + 1) &&
                    input.charAt(i + 1) == input.charAt(i + 2) &&
                    input.charAt(i + 2) == input.charAt(i + 3)) {
                return true;
            }
        }

        return false;
    }

    //java 判断 一个6数字位字符串是否包含4个连续的数字
    public static boolean hasFourConsecutiveDigits(String input) {
        if (input.length() < 4) {
            return false;
        }

        for (int i = 0; i <= input.length() - 4; i++) {
            int digit1 = Character.getNumericValue(input.charAt(i));
            int digit2 = Character.getNumericValue(input.charAt(i + 1));
            int digit3 = Character.getNumericValue(input.charAt(i + 2));
            int digit4 = Character.getNumericValue(input.charAt(i + 3));

            if (digit2 == digit1 + 1 && digit3 == digit2 + 1 && digit4 == digit3 + 1) {
                return true;
            }
        }

        return false;
    }


    //java判断6位数字字符串是否包含3个连续相同的数字
    public static boolean hasConsecutiveSameDigits(String input) {
        if (input.length() < 3) {
            return false;
        }

        for (int i = 0; i <= input.length() - 3; i++) {
            if (input.charAt(i) == input.charAt(i + 1) && input.charAt(i + 1) == input.charAt(i + 2)) {
                return true;
            }
        }

        return false;
    }

    //java判断6位数字字符串是否包含2对2个连续相同的数字
    public static boolean hasThreePairs(String input) {
        int pairCount = 0;
        char prevDigit = 0;

        for (char digit : input.toCharArray()) {
            if (digit == prevDigit) {
                pairCount++;
                if (pairCount >= 3) {
                    return true;
                }
            } else {
                pairCount = 1; // Reset pair count
            }
            prevDigit = digit;
        }

        return false;
    }
}