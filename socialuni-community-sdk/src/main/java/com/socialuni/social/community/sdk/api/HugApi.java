package com.socialuni.social.community.sdk.api;


import com.socialuni.social.community.sdk.model.HugModel;

public interface HugApi  {

    HugModel findByTalkIdAndUserId(Integer talkId, Integer userId);

    HugModel save(HugModel hugModel);
}