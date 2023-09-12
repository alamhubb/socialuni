
import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.peiwan.sdk.logic.SocialuniPeiwanService;
import com.socialuni.social.peiwan.sdk.model.QO.SocialuniPeiwanInfoRO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RequestMapping("socialuni/music")
@RestController
@Slf4j
public class SocialuniMusicController {

    @Resource
    SocialuniPeiwanService socialuniPeiwanService;

    @GetMapping("queryMusicList")
    public ResultRO<List<SocialuniPeiwanInfoRO>> queryPeiwanInfoList() {
        return socialuniPeiwanService.queryPeiwanInfoList();
    }

}
