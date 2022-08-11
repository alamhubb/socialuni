package com.socialuni.sdk.model.DO.keywords;


import com.socialuni.social.constant.ReportStatus;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 关键字触发记录
 */@Entity
@Table(name = "keywords_trigger_detail")
@Data
public class KeywordsTriggerDetailDO implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer reportId;

    //要显示具体的各种比例所以不是懒加载
    private Integer keywordsId;
    private String contentType;
    private Integer contentId;

//将所有单词的变种文本 也写在关键词上，以后每次直接读取，无需再格式化

//遍历keyword表，所有给变种，和变种展示赋值，都需要转为大写和去除中间空格

//查询展示内容违规时，如果文本违规，展示文本，变种违规，展示本文，变种展示，变种，变种匹配的内容，违规的文本


    //能尽量在写入的时候处理的逻辑，就不要在读取的时候处理

    //违规内容整体
    private String content;

    private String keywordsText;
    //主要违规部分，内容未处理前，违规的部分
    private String matchText;

    private String keywordsPinyin;
    //主要违规变种部分，内容处理转变为拼音之后，违规的部分
    private String matchPinyin;

    private String auditResult;
    //有变种
    private Boolean usePinyin;
    private Date createTime;
    private Date updateTime;

    public KeywordsTriggerDetailDO() {
    }

    public KeywordsTriggerDetailDO(
            String content,
            Integer contentId,
            String contentType,
            Integer keywordsId,
            String keywordsText,
            String matchText,
            Boolean usePinyin
    ) {
        this.content = content;
        this.contentId = contentId;
        this.contentType = contentType;
        this.keywordsId = keywordsId;
        this.keywordsText = keywordsText;
        this.matchText = matchText;
        this.usePinyin = usePinyin;
        this.createTime = new Date();
        this.updateTime = new Date();
        this.auditResult = ReportStatus.preAudit;
    }

    //变种匹配构建
    public KeywordsTriggerDetailDO(
            String content,
            Integer contentId,
            String contentType,
            Integer keywordsId,
            String keywordsText,
            String matchText,
            String keywordsPinyin,
            String matchPinyin
    ) {
        this(content, contentId, contentType, keywordsId, keywordsText, matchText, true);
        this.keywordsPinyin = keywordsPinyin;
        this.matchPinyin = matchPinyin;
    }
}
