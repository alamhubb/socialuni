package com.socialuni.social.user.sdk.platform.baidu;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.socialuni.social.user.sdk.platform.TokenDTO;
import com.socialuni.social.sdk.utils.common.RestUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author qinkaiyuan
 * @date 2019-10-24 13:30
 */
@Component
public class BaiduUtil {
    private static ObjectMapper objectMapper;
    private static String tokenUrl;

    public void setTokenUrl(String tokenUrl) {
        BaiduUtil.tokenUrl = tokenUrl;
    }


    @Resource
    public void setObjectMapper(ObjectMapper objectMapper) {
        BaiduUtil.objectMapper = objectMapper;
    }

    /**
     * 获取百度token
     *
     * @return
     */
    public static String getAccessToken() {
        ResponseEntity<TokenDTO> responseEntity = RestUtil.getXmlRestTemplate().getForEntity(tokenUrl + "getBdSession", TokenDTO.class);
        return Objects.requireNonNull(responseEntity.getBody()).getAccessToken();
    }

    public static String refreshAccessToken() {
        ResponseEntity<TokenDTO> responseEntity = RestUtil.getXmlRestTemplate().getForEntity(tokenUrl + "refreshBdSession", TokenDTO.class);
        return Objects.requireNonNull(responseEntity.getBody()).getAccessToken();
    }
}
