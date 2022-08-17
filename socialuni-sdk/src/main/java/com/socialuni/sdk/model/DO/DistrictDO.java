package com.socialuni.sdk.model.DO;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2019-10-30 14:08
 */
@Data
@Entity
@Table(name = "s_district",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"adCode"})}
        , indexes = {
        @Index(columnList = "count"),
        @Index(columnList = "talkCount"),
        @Index(columnList = "status"),
        @Index(columnList = "provinceCode"),
        @Index(columnList = "cityCode"),
        @Index(columnList = "districtCode"),
        @Index(columnList = "parentAdCode")
}
)
public class DistrictDO implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //省
    private String provinceCode;
    private String provinceName;
    //市
    private String cityCode;
    private String cityName;
    //区县
    private String districtCode;
    private String districtName;
    //统一标识
    private String adCode;
    private String adName;
    //总访问量，共有多少次访问，和使用
    private Integer count;
    //本地区共有多少帖子
    private Integer talkCount;
    private String status;
    private String parentAdCode;

    @Transient
    private List<DistrictDO> childs = new ArrayList<>();

    @Override
    public String toString() {
        return "DistrictDO{" +
                "id=" + id +
                ", provinceCode='" + provinceCode + '\'' +
                ", provinceName='" + provinceName + '\'' +
                ", cityCode='" + cityCode + '\'' +
                ", cityName='" + cityName + '\'' +
                ", districtCode='" + districtCode + '\'' +
                ", districtName='" + districtName + '\'' +
                ", adCode='" + adCode + '\'' +
                ", adName='" + adName + '\'' +
                ", count=" + count +
                ", talkCount=" + talkCount +
                ", status='" + status +
                '}';
    }
}
