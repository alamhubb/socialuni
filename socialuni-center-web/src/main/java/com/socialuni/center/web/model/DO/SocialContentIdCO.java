package com.socialuni.center.web.model.DO;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author qinkaiyuan 查询结果可以没有set和空构造，前台传值可以没有get
 * @date 2019-08-13 23:34
 */
@MappedSuperclass
@Data
@NoArgsConstructor
public class SocialContentIdCO implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, updatable = false)
    private String contentType;

    public SocialContentIdCO(String contentType) {
        this.contentType = contentType;
    }

    public SocialContentIdCO(Integer id, String contentType) {
        this.id = id;
        this.contentType = contentType;
    }

    public SocialContentIdCO(SocialContentIdCO socialuniContentIdRO) {
        this.id = socialuniContentIdRO.getId();
        this.contentType = socialuniContentIdRO.getContentType();
    }
}