package com.socialuni.sdk.model.BO;


import lombok.Data;

/**
 * TODO〈一句话功能简述〉
 * TODO〈功能详细描述〉
 *
 * @author qinkaiyuan
 * @since TODO[起始版本号]
 */
@Data
public class HugAddBO {
    private String talkId;
    private Integer commentId;

    /*public HugAddBO(HugAddVO hugAddVO) {
        this.talkId = hugAddVO.getTalkId();
        this.commentId = hugAddVO.getCommentId();
    }

    public HugDO toDO(TalkDO talkDO) {
        HugDO hugDO = new HugDO();
        hugDO.setCreateTime(new Date());
        hugDO.setTalkId(talkDO.getId());
        hugDO.setUserId(CenterUserUtil.getMineUserId());
        return hugDO;
    }

    public HugDO toDO(CommentDO commentDO) {
        HugDO hugDO = new HugDO();
        hugDO.setCreateTime(new Date());
        hugDO.setCommentId(commentDO.getId());
        hugDO.setUserId(CenterUserUtil.getMineUserId());
        return hugDO;
    }*/

}
