package com.socialuni.sdk.model.DO.keywords;

import com.github.promeg.pinyinhelper.Pinyin;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author qinkaiyuan
 * @date 2019-12-22 18:46
 */
@Data
@Entity
/**
 * 违规单词表
 */
@Table(name = "keywords_copy", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"text"})
})
public class KeywordsCopyDO implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //关键词主体，会变为大写
    private String text;
    //仅展示，保存录入的内容
    private String textShow;
    //会去除空格和变为大写
    private String pinyinText;
    //仅用来展示
    private String pinyinTextShow;

    private String status;

    @Column(columnDefinition = "bit default true")
    private Boolean openText;

    @Column(columnDefinition = "bit default true")
    private Boolean openPinyin;

    //关键词变种
    private String cause;

    private String deleteCause;
    private String reopenCause;
    private String closeTextCause;
    private String reopenTextCause;
    private String closePinyinCause;
    private String reopenPinyinCause;

    @Column(columnDefinition = "int default 0")
    private Integer totalNum;
    @Column(columnDefinition = "int default 0")
    private Integer violateNum;
    @Column(columnDefinition = "int default 0")
    private Integer normalNum;
    @Column(columnDefinition = "double default 0")
    private Double violateRatio;
    @Column(columnDefinition = "double default 0")
    private Double normalRatio;

    @Column(columnDefinition = "int default 0")
    private Integer textTotalNum;
    @Column(columnDefinition = "int default 0")
    private Integer textViolateNum;
    @Column(columnDefinition = "double default 0")
    private Double textViolateRatio;
    @Column(columnDefinition = "int default 0")
    private Integer textNormalNum;
    @Column(columnDefinition = "double default 0")
    private Double textNormalRatio;

    @Column(columnDefinition = "int default 0")
    private Integer pinyinTotalNum;
    @Column(columnDefinition = "int default 0")
    private Integer pinyinViolateNum;
    @Column(columnDefinition = "double default 0")
    private Double pinyinViolateRatio;
    @Column(columnDefinition = "int default 0")
    private Integer pinyinNormalNum;
    @Column(columnDefinition = "double default 0")
    private Double pinyinNormalRatio;

    //do必须有空的构造函数
    public KeywordsCopyDO() {
    }

    public KeywordsCopyDO(String content, String cause) {
        this.setTextShow(content);
        this.setText(content.toUpperCase());

        String contentPinyin = Pinyin.toPinyin(content, " ");
        this.setPinyinTextShow(contentPinyin);
        this.setPinyinText(contentPinyin.replaceAll(" ", "").toUpperCase());

        this.setCause(cause);

        this.setOpenText(true);
        this.setOpenPinyin(true);

        this.setTotalNum(0);
        this.setNormalNum(0);
        this.setNormalRatio(0.0);
        this.setViolateRatio(0.0);
        this.setViolateNum(0);

        this.setTextTotalNum(0);
        this.setTextNormalNum(0);
        this.setTextNormalRatio(0.0);
        this.setTextViolateRatio(0.0);
        this.setTextViolateNum(0);

        /*this.setPinyinTotalNum(0);
        this.setPinyinNormalNum(0);
        this.setPinyinNormalRatio(0.0);
        this.setPinyinViolateRatio(0.0);
        this.setPinyinViolateNum(0);*/

        this.setPinyinTotalNum(0);
        this.setPinyinNormalNum(0);
        this.setPinyinNormalRatio(0.0);
        this.setPinyinViolateRatio(0.0);
        this.setPinyinViolateNum(0);
    }
}
