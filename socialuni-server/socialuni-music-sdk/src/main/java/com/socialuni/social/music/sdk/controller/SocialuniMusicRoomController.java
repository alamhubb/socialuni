package com.socialuni.social.music.sdk.controller;

import cn.hutool.core.net.URLDecoder;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.socialuni.social.common.api.constant.DateTimeType;
import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.api.utils.JsonUtil;
import com.socialuni.social.music.sdk.model.QO.AgoraPlayMusicQO;
import com.socialuni.social.music.sdk.model.QO.AgoraUpdateMusicQO;
import com.socialuni.social.music.sdk.model.QO.SocialuniPlayMusicQO;
import com.socialuni.social.music.sdk.model.RO.AgoraPlayMusicRO;
import com.socialuni.social.music.sdk.model.RO.SocialuniMusicInfoRO;
import com.socialuni.social.music.sdk.model.RO.SocialuniMusicInitDataRO;
import com.socialuni.social.tance.sdk.facade.SocialuniUnionIdFacede;
import io.agora.media.RtcTokenBuilder2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.Base64;
import java.util.Collections;
import java.util.List;

import static com.socialuni.social.common.sdk.utils.RestUtil.getDefaultRestTemplate;

@RequestMapping("socialuni/musicRoom")
@RestController
@Slf4j
public class SocialuniMusicRoomController {



}
