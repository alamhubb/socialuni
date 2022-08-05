package com.socialuni.center.web.model;

import java.util.List;

public class IpResult {
    public String status;
    public String info;
    public Object rectangle;
    public Object adcode;

    /*public String infocode;
    public Object province;
    public Object city;
    */

    public boolean hasError() {
        //不为1，为数组，则为错误
        return !"1".equals(this.status) || this.adcode instanceof List || this.rectangle instanceof List;
    }
}
