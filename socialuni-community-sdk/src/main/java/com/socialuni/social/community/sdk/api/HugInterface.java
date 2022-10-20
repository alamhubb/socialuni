package com.socialuni.social.community.sdk.api;


import com.socialuni.social.community.sdk.model.HugModel;

public interface HugInterface {

    HugModel findByTalkIdAndUserId(Integer talkId, Integer userId);

    HugModel savePut(HugModel hugModel);
}