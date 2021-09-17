package com.socialuni.social.sdk.utils;

import com.socialuni.social.model.model.QO.user.SocialProviderLoginQO;
import com.socialuni.social.sdk.constant.platform.PlatformType;
import com.socialuni.social.sdk.constant.platform.WxUrl;
import com.socialuni.social.exception.UniSdkException;
import com.socialuni.social.sdk.model.UniUnionIdRO;
import com.socialuni.social.sdk.platform.WxDecode;
import com.socialuni.social.utils.JsonUtil;
import com.socialuni.social.sdk.utils.common.RestUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.text.MessageFormat;
import java.util.Map;
import java.util.Objects;

@Component
@Slf4j
public class WxProviderUtil {
    private static String wxMpId;
    private static String wxMpSecret;

    @Value("${socialuni.provider.wx.wx-mp-id}")
    public void setWxMpId(String wxMpId) {
        WxProviderUtil.wxMpId = wxMpId;
    }

    @Value("${socialuni.provider.wx.wx-mp-secret}")
    public void setWxMpSecret(String wxMpSecret) {
        WxProviderUtil.wxMpSecret = wxMpSecret;
    }

    public static UniUnionIdRO getWxUnionIdRO(SocialProviderLoginQO unionIdData) {
        String platform = unionIdData.getPlatform();
        String code = unionIdData.getCode();

        if (PlatformType.mp.equals(platform)) {
            /*String wxMpId = unionIdData.getAppId();
            String wxMpSecret = unionIdData.getSecret();
            if (StringUtils.isEmpty(wxMpId)) {
                wxMpId = WxProviderUtil.wxMpId;
            }
            if (StringUtils.isEmpty(wxMpSecret)) {
                wxMpSecret = WxProviderUtil.wxMpSecret;
            }*/
            String url = WxProviderUtil.getUnionIdUrl(platform, code, wxMpId, wxMpSecret);
            ResponseEntity<UniUnionIdRO> responseEntity = RestUtil.restTemplate().getForEntity(url, UniUnionIdRO.class);
            UniUnionIdRO uniUnionIdRO = Objects.requireNonNull(responseEntity.getBody());
            // 微信需要单独解析
            return WxProviderUtil.decodeUnionId(unionIdData, uniUnionIdRO);

        } else if (PlatformType.app.equals(platform)) {
            // 微信需要单独解析
            UniUnionIdRO uniUnionIdRO = new UniUnionIdRO();
            uniUnionIdRO.setOpenid(unionIdData.getOpenId());
            uniUnionIdRO.setUnionid(unionIdData.getUnionId());
            return uniUnionIdRO;
        }

        throw new UniSdkException(PlatformType.notSupportTypeErrorMsg + ":" + platform);
    }

    private static String getUnionIdUrl(String platform, @Valid @NotBlank String code, @Valid @NotBlank String wxMpId, @Valid @NotBlank String wxMpSecret) {
        if (PlatformType.mp.equals(platform)) {
            return MessageFormat.format(WxUrl.wx_mp_unionId_url, wxMpId, wxMpSecret, code);
        }
        throw new UniSdkException(PlatformType.notSupportTypeErrorMsg + ":" + platform);
    }

    private static UniUnionIdRO decodeUnionId(SocialProviderLoginQO loginData, UniUnionIdRO uniUnionIdRO) {
        //只有unionid为空才解析，什么时候会为空，需要确认
        if (StringUtils.isEmpty(uniUnionIdRO.getUnionid())) {
            String sessionKey = uniUnionIdRO.getSession_key();
            if (StringUtils.isEmpty(sessionKey)) {
                log.info("sessionKey为空，不解析unionId");
                return uniUnionIdRO;
            }
            String enData = loginData.getEncryptedData();
            String enIv = loginData.getIv();
            //都不为空才解析unionid
            String userInfoJson = WxDecode.decrypt(sessionKey, enData, enIv);
            //不为空
            if (StringUtils.isNotEmpty(userInfoJson)) {
                try {
                    Map map = JsonUtil.objectMapper.readValue(userInfoJson, Map.class);
                    String enUnionId = (String) map.get("unionId");
                    uniUnionIdRO.setUnionid(enUnionId);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return uniUnionIdRO;
    }
}
