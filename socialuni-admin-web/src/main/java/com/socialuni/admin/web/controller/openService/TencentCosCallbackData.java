package com.socialuni.admin.web.controller.openService;

import lombok.Data;

@Data
public class TencentCosCallbackData {
    private Integer forbidden_status;
    private String event;
    private TencentCosCallbackDataPornInfo porn_info;
    private Integer result;
    private String trace_id;
    private String url;
}
