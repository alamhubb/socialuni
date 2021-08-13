package com.socialuni.social.sdk.url.community;

import com.socialuni.social.model.model.QO.community.SocialHugAddQO;
import com.socialuni.social.api.model.model.ResultRO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping("hug")
public interface SocialuniHugUrl {
    @PostMapping("addHug")
    ResultRO<Void> addHug(@RequestBody @Valid SocialHugAddQO addVO);
}