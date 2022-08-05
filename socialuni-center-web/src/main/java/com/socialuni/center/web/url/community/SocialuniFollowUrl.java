package com.socialuni.center.web.url.community;

import com.socialuni.social.model.model.QO.community.SocialFollowAddQO;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.model.model.RO.user.base.SocialUserFollowDetailRO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RequestMapping("follow")
public interface SocialuniFollowUrl {

    @PostMapping("queryUserFollows")
    ResultRO<Map<String, List<SocialUserFollowDetailRO>>> queryUserFollows();

    @PostMapping("addFollow")
    ResultRO<Void> addFollow(@RequestBody @Valid SocialFollowAddQO addVO);

    @PostMapping("cancelFollow")
    ResultRO<Void> cancelFollow(@RequestBody @Valid SocialFollowAddQO addVO);

}