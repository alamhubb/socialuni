package com.socialuni.center.web.url.community;

import com.socialuni.center.web.model.QO.community.SocialHugAddQO;
import com.socialuni.social.api.model.ResultRO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping("hug")
public interface SocialuniHugUrl {
    @PostMapping("addHug")
    ResultRO<Void> addHug(@RequestBody @Valid SocialHugAddQO addVO);
}