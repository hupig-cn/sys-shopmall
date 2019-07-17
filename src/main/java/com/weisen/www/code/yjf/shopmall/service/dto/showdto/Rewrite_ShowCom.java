package com.weisen.www.code.yjf.shopmall.service.dto.showdto;

import com.weisen.www.code.yjf.shopmall.domain.Commodity;
import com.weisen.www.code.yjf.shopmall.service.dto.CommodityDTO;
import com.weisen.www.code.yjf.shopmall.service.dto.SpecificationsDTO;

import java.util.List;
import java.util.Objects;

public class Rewrite_ShowCom {

    private Long id;

    private String name;

    private String brandid;

    private String classificationid;

    private String commoditystate;

    private String postage;

    private String salevalue;

    private String weight;

    private String creator;

    private String createdate;

    private String modifier;

    private String modifierdate;

    private Long modifiernum;

    private Boolean logicdelete;

    private String other;

    private String price;

    private Long specificationsId;

    private List<SpecificationsDTO> specificationsDTO;

    public  Rewrite_ShowCom(Commodity com,List<SpecificationsDTO> list){
        this.id = com.getId();
        this.name = com.getName();
        this.brandid = com.getBrandid();
        this.classificationid = com.getClassificationid();
        this.commoditystate = com.getCommoditystate();
        this.postage = com.getPostage();
        this.salevalue = com.getSalevalue();
        this.weight = com.getWeight();
        this.creator = com.getCreator();
        this.createdate = com.getCreatedate();
        this.modifier = com.getModifier();
        this.modifierdate = com.getModifierdate();
        this.modifiernum = com.getModifiernum();
        this.logicdelete = com.isLogicdelete();
        this.other = com.getOther();
        this.specificationsDTO = list;
        this.price = list.get(0).getPrice();
        this.specificationsId = list.get(0).getId();
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Long getSpecificationsId() {
        return specificationsId;
    }

    public void setSpecificationsId(Long specificationsId) {
        this.specificationsId = specificationsId;
    }

    public List<SpecificationsDTO> getSpecificationsDTO() {
        return specificationsDTO;
    }

    public void setSpecificationsDTO(List<SpecificationsDTO> specificationsDTO) {
        this.specificationsDTO = specificationsDTO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrandid() {
        return brandid;
    }

    public void setBrandid(String brandid) {
        this.brandid = brandid;
    }

    public String getClassificationid() {
        return classificationid;
    }

    public void setClassificationid(String classificationid) {
        this.classificationid = classificationid;
    }

    public String getCommoditystate() {
        return commoditystate;
    }

    public void setCommoditystate(String commoditystate) {
        this.commoditystate = commoditystate;
    }

    public String getPostage() {
        return postage;
    }

    public void setPostage(String postage) {
        this.postage = postage;
    }

    public String getSalevalue() {
        return salevalue;
    }

    public void setSalevalue(String salevalue) {
        this.salevalue = salevalue;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public String getModifierdate() {
        return modifierdate;
    }

    public void setModifierdate(String modifierdate) {
        this.modifierdate = modifierdate;
    }

    public Long getModifiernum() {
        return modifiernum;
    }

    public void setModifiernum(Long modifiernum) {
        this.modifiernum = modifiernum;
    }

    public Boolean isLogicdelete() {
        return logicdelete;
    }

    public void setLogicdelete(Boolean logicdelete) {
        this.logicdelete = logicdelete;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CommodityDTO commodityDTO = (CommodityDTO) o;
        if (commodityDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), commodityDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

}

