package com.socialuni.social.entity.model.DO.base;

import lombok.Data;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

//发表内容的基类，比如，图片，动态，评论，消息、等用户输入，上传的内容通过前台确认，用户上传，输入框输入的内容，还有tag是谁创建的，圈子谁创建的。
//修改昵称的记录。
@MappedSuperclass
@Data
public class SocialPostContentBaseDO extends CommonContentBaseDO implements Serializable {
    String ip;
    Integer devId;
}