package com.socialuni.sdk.model.DO;

import com.socialuni.sdk.model.DO.base.CommonContentBaseDO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.io.Serializable;

@Entity
@Table(name = "hug", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"userId", "talkId"}),
        @UniqueConstraint(columnNames = {"userId", "commentId"})
})
@Data
@NoArgsConstructor
public class HugDO extends CommonContentBaseDO implements Serializable {
    /**
     * 哪个评论的
     */
    private Integer userId;
    /**
     * 赞的说说
     */
    private Integer talkId;

    /**
     * 赞的说说
     */
    private Integer commentId;

    public HugDO(Integer userId, Integer talkId, Integer commentId) {
        this.userId = userId;
        this.talkId = talkId;
        this.commentId = commentId;
    }
}
