package com.socialuni.social.music.sdk.controller;

import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.music.sdk.model.RO.SocialuniMusicInfoRO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RequestMapping("socialuni/music")
@RestController
@Slf4j
public class SocialuniMusicController {


    @GetMapping("queryMusicList")
    public ResultRO<List<SocialuniMusicInfoRO>> queryMusicList() {
        SocialuniMusicInfoRO socialuniMusicInfoRO = new SocialuniMusicInfoRO();

        socialuniMusicInfoRO.setSrc("https://cdxapp-1257733245.file.myqcloud.com/opentest/M800000puzgO0yRX1o.mp3");
        socialuniMusicInfoRO.setMusicName("入秋");
        socialuniMusicInfoRO.setMusicAuthor("江南");

        return ResultRO.success(Collections.singletonList(socialuniMusicInfoRO));
    }

}
