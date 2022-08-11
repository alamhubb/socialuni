package com.socialuni.sdk.model.VO;

import lombok.Data;

import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2019-08-21 20:47
 */
@Data
public class UserMatchQueryVO {
    private List<String> userIds;
    private String matchType;

    private Integer adCode;
    private Double lon;
    private Double lat;
    private List<Integer> tagIds;
    private Integer minAge;
    private Integer maxAge;
    private String gender;
}
