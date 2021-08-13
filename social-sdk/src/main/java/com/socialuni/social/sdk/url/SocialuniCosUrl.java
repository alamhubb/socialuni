package com.socialuni.social.sdk.url;

import com.socialuni.social.model.model.RO.app.SocialCosAuthRO;
import com.socialuni.social.model.model.RO.ResultRO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("cos")
public interface SocialuniCosUrl {
    @PostMapping("getCosAuth")
    ResultRO<SocialCosAuthRO> getCosAuth();
}