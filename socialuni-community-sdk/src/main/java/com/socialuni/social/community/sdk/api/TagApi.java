package com.socialuni.social.community.sdk.api;

import com.socialuni.social.community.sdk.model.TagModel;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface TagApi  {
    TagModel findByIdAndStatus(Integer tagId, String status);

    TagModel save(TagModel tagModel);

    TagModel findFirstByName(String name);

    TagModel findFirstByDevId(Integer devId);

    List<Integer> findTagIdsByTalkIdAndStatusAndShowFront(Integer talkId, String status, Boolean showFront);

    List<?  extends TagModel> findByStatusAndVisibleGenderOrderByCountDesc(String status, String gender);

    //获取tagTYpe所有子tag
    List<?  extends TagModel> findByTagTypeIdAndStatusOrderByCountDesc(Integer tagTypeId, String status);

    List<?  extends TagModel> findByTagTypeIdAndStatusAndVisibleGenderOrderByCountDesc(Integer tagTypeId, String status, String gender);


    //查询热门前10tag
    List<?  extends TagModel> findByStatusOrderByCountDesc(String status, Pageable pageable);

    List<?  extends TagModel> findByStatusAndVisibleGenderOrderByCountDesc(String status, String gender, Pageable pageable);


    //查询所有tag
    List<?  extends TagModel> findAllByStatusOrderByCountDesc(String status);

    List<?  extends TagModel> findAllByStatusAndVisibleGenderOrderByCountDesc(String status, String gender);

    List<TagModel> savePutAll(List<TagModel> tagModels);

}
