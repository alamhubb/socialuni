package com.socialuni.sdk.feignAPI;

import com.socialuni.sdk.model.QO.community.talk.SocialuniTalkPostQO;
import com.socialuni.sdk.model.QO.talk.SocialuniHomeTabTalkQueryQO;
import com.socialuni.sdk.model.QO.talk.SocialuniTalkIdQO;
import com.socialuni.sdk.model.QO.talk.SocialuniUserTalkQueryQO;
import com.socialuni.sdk.model.RO.talk.SocialuniTalkRO;
import com.socialuni.social.web.sdk.model.ResultRO;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("socialuni/talk")
@OpenAPIDefinition(
        info = @Info(
                title = "社交联盟接口",
                version = "0.3.0",
                description = "社交联盟接口"
        ),
        tags = {
                @Tag(name = "社区模块/动态模块"),
                @Tag(name = "社区模块/评论模块"),
                @Tag(name = "社区模块/圈子模块"),
                @Tag(name = "社区模块/话题模块"),
                @Tag(name = "社区模块/关注模块"),
                @Tag(name = "社区模块/点赞模块"),
                @Tag(name = "社区模块/举报模块"),
                @Tag(name = "社区模块/通知模块"),
                @Tag(name = "开放数据/地理位置"),
                @Tag(name = "开放数据/开放用户数据"),
                @Tag(name = "用户模块/用户信息模块"),
                @Tag(name = "用户模块/用户认证模块"),
        },
        servers = {
                @Server(
                        description = "社交联盟中心服务器地址",
                        url = "https://api.socialuni.cn"
                )
        }
)
@Tag(name = "社区模块/动态模块")
@FeignClient(name = "talk", url = "${socialuni.central-server-url:https://api.socialuni.cn}")
public interface SocialuniTalkAPI {
    @GetMapping("queryTalks")
    @Operation(summary = "查询动态列表，无需参数")
    ResultRO<List<SocialuniTalkRO>> queryTalks();

    @GetMapping("queryStickTalks")
    @Operation(summary = "查询制定动态列表，无需参数")
    ResultRO<List<SocialuniTalkRO>> queryStickTalks();

    @PostMapping("queryTalks")
    @Operation(summary = "查询动态列表")
    ResultRO<List<SocialuniTalkRO>> queryTalks(@RequestBody(required = false) SocialuniHomeTabTalkQueryQO queryQO);

    /**
     * 返回talk是因为三方需要Id
     *
     * @param talkPostQO
     * @return
     */
    @PostMapping("postTalk")
    @Operation(summary = "发表动态")
    ResultRO<SocialuniTalkRO> postTalk(@RequestBody @Valid SocialuniTalkPostQO talkPostQO);

    @PostMapping("deleteTalk")
    @Operation(summary = "删除动态")
    ResultRO<Void> deleteTalk(@RequestBody @Valid SocialuniTalkIdQO talkIdQO);

    @GetMapping("queryTalkDetail/{talkId}")
    @Operation(summary = "查询动态详情")
    ResultRO<SocialuniTalkRO> queryTalkDetail(@PathVariable("talkId") String talkId);

    @PostMapping("queryUserTalks")
    @Operation(summary = "查询用户动态列表")
    ResultRO<List<SocialuniTalkRO>> queryUserTalks(@RequestBody @Valid SocialuniUserTalkQueryQO queryQO);
}