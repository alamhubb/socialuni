package com.socialuni.social.community.sdk.dao.DO;

import com.socialuni.social.common.api.entity.SocialuniContentBaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "s_community_hug", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"userId", "talkId"}),
        @UniqueConstraint(columnNames = {"userId", "commentId"})
})
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SocialuniHugDO extends SocialuniContentBaseDO {
    /**
     * 哪个评论的
     */
    private Long userId;
    /**
     * 赞的说说
     */
    private Long talkId;

    /**
     * 赞的说说
     */
    private Long commentId;

    public SocialuniHugDO(Long userId, Long talkId, Long commentId) {
        this.userId = userId;
        this.talkId = talkId;
        this.commentId = commentId;
    }
}
