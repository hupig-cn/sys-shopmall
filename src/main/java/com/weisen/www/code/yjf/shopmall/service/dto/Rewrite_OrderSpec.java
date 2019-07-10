package com.weisen.www.code.yjf.shopmall.service.dto;

import java.util.List;

public class Rewrite_OrderSpec {

    private List<SpecificationsDTO> spec;

    private String num;

    public List<SpecificationsDTO> getSpec() {
        return spec;
    }

    public void setSpec(List<SpecificationsDTO> spec) {
        this.spec = spec;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

}
