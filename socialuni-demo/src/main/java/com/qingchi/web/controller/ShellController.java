package com.qingchi.web.controller;

import com.qingchi.web.model.DO.shell.ShellOrderDO;
import com.qingchi.web.model.VO.ShellOrderVO;
import com.qingchi.web.repsotiory.ShellOrderRepository;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.constant.CommonStatus;
import com.socialuni.social.entity.model.DO.user.UserDO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("shell")
public class ShellController {
    @Resource
    private ShellOrderRepository shellOrderRepository;

    @PostMapping("queryShells")
    public ResultRO<List<ShellOrderVO>> queryShells(UserDO user) {
        List<ShellOrderDO> shellOrderDOS = shellOrderRepository.findAllByUserIdAndStatusOrderByCreateTimeDesc(user.getId(), CommonStatus.enable);
        return new ResultRO<>(shellOrderDOS.stream().map(ShellOrderVO::new).collect(Collectors.toList()));
    }
}
