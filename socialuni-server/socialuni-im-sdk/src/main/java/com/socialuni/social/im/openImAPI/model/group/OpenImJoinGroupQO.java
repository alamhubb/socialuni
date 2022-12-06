package com.socialuni.social.im.openImAPI.model.group;

import lombok.Data;

@Data
public class OpenImJoinGroupQO {
    String groupID;
    String inviterUserID;
    Integer joinSource;
    String operationID;
    String reqMessage;
}