package com.socialuni.sdk.dao.DO.base;

//为什么弄这么个类，因为有些contentDO是没有这些属性的，比如talkImg和user
public interface SocialuniTextCheckDO {
    String getContent();

    void setContent(String content);

    Boolean getHasUnderageContent();

    void setHasUnderageContent(Boolean hasUnderageContent);

    Boolean getHasContactInfo();

    void setHasContactInfo(Boolean hasContactInfo);
}
