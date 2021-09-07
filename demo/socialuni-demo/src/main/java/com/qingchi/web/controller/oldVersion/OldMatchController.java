package com.qingchi.web.controller.oldVersion;

import com.qingchi.web.model.RO.user.UserDetailRO;
import com.socialuni.social.api.model.ResultRO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 查询用户详情
 *
 * @author qinkaiyuan
 * @since 1.0.0
 */
@RestController
@RequestMapping("match")
public class OldMatchController {
    @PostMapping("queryMatchUsers")
    public ResultRO<List<UserDetailRO>> mine() {
        return ResultRO.success(new ArrayList<>());
    }
}