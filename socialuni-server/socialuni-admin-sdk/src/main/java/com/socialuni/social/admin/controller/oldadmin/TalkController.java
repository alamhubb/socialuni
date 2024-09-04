package com.socialuni.social.admin.controller.oldadmin;/*
package com.socialuni.admin.web.controller.oldadmin;

import com.socialuni.social.model.common.ResultRO;
import com.qingchi.base.repository.talk.TalkRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

*/
/**
 * @author qinkaiyuan
 * @date 2020-03-15 22:05
 *//*

@RestController
@RequestMapping("talk")
public class TalkController {
    @Resource
    private TalkRepository talkRepository;

    */
/*@PostMapping("queryTalk")
    public ResultVO<ReportContentVO> queryTalk(Long talkId) {
        SocialTalkDO talkDO = talkRepository.findById(talkId).get();
        return new ResultVO<>(new ReportContentVO(talkDO));
    }

    @PostMapping("queryUserTalks")
    public ResultVO<List<ReportContentVO>> queryUserTalks(Long userId) {
        SocialUserDO userDO = new SocialUserDO();
        userDO.setId(userId);
        List<SocialTalkDO> talks = talkRepository.findTop20ByUserIdOrderByIdDesc(userDO.getId());
        List<ReportContentVO> talkVOS = talks.stream().map(ReportContentVO::new).collect(Collectors.toList());
        return new ResultVO<>(talkVOS);
    }*//*


    @RequestMapping("queryTalkStatistics")
    public ResultRO<List<Object>> queryTalkStatistics() {
        List<Object> talkStatisticsDOS = talkRepository.countAndCreateTimeByCreateTimeBetween();
        return new ResultRO<>(talkStatisticsDOS);
    }
}
*/
