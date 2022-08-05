package com.socialuni.center.web.url;

import com.socialuni.center.web.model.RO.app.SocialCosAuthRO;
import com.socialuni.social.api.model.ResultRO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("cos")
public interface SocialuniCosUrl {
    @PostMapping("getCosAuth")
    ResultRO<SocialCosAuthRO> getCosAuth();
}