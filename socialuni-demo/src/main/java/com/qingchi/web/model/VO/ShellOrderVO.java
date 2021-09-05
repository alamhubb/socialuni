package com.qingchi.web.model.VO;

import com.qingchi.web.model.DO.shell.ShellOrderDO;
import lombok.Data;

import java.util.Date;

@Data
public class ShellOrderVO {
    //必有
    private Integer id;

    //本单贝壳数量，可能是正值，也可能是负值
    private Integer shell;

    private Date createTime;

    private String type;

    public ShellOrderVO(ShellOrderDO shellOrderDO) {
        this.id = shellOrderDO.getId();
        this.shell = shellOrderDO.getShell();
        this.createTime = shellOrderDO.getCreateTime();
        this.type = shellOrderDO.getType();
    }
}