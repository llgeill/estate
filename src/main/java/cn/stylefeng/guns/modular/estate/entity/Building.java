package cn.stylefeng.guns.modular.estate.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 城区
 * </p>
 *
 * @author 李利光
 * @since 2019-07-11
 */
@TableName("estate_building")
public class Building implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "building_id", type = IdType.ID_WORKER)
    private Long buildingId;

    /**
     * 城区id
     */
    @TableField("city_id")
    private Long cityId;

    /**
     * 拼音缩写
     */
    @TableField("pinyin_initials")
    private String pinyinInitials;

    /**
     * 物业用途
     */
    @TableField("property_use")
    private String propertyUse;

    /**
     * 物业类型
     */
    @TableField("property_type")
    private String propertyType;

    /**
     * 建房年代
     */
    @TableField("building_time")
    private Integer buildingTime;

    /**
     * 概要地址
     */
    @TableField("profile_address")
    private String profileAddress;

    /**
     * 详细地址
     */
    @TableField("detailed_address")
    private String detailedAddress;

    /**
     * 售均价
     */
    @TableField("sale_average_price")
    private Double saleAveragePrice;

    /**
     * 管理费
     */
    @TableField("manage_fee")
    private Double manageFee;

    /**
     * 备注
     */
    @TableField("note")
    private String note;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    private Date updateTime;


    public Long getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getPinyinInitials() {
        return pinyinInitials;
    }

    public void setPinyinInitials(String pinyinInitials) {
        this.pinyinInitials = pinyinInitials;
    }

    public String getPropertyUse() {
        return propertyUse;
    }

    public void setPropertyUse(String propertyUse) {
        this.propertyUse = propertyUse;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public Integer getBuildingTime() {
        return buildingTime;
    }

    public void setBuildingTime(Integer buildingTime) {
        this.buildingTime = buildingTime;
    }

    public String getProfileAddress() {
        return profileAddress;
    }

    public void setProfileAddress(String profileAddress) {
        this.profileAddress = profileAddress;
    }

    public String getDetailedAddress() {
        return detailedAddress;
    }

    public void setDetailedAddress(String detailedAddress) {
        this.detailedAddress = detailedAddress;
    }

    public Double getSaleAveragePrice() {
        return saleAveragePrice;
    }

    public void setSaleAveragePrice(Double saleAveragePrice) {
        this.saleAveragePrice = saleAveragePrice;
    }

    public Double getManageFee() {
        return manageFee;
    }

    public void setManageFee(Double manageFee) {
        this.manageFee = manageFee;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Building{" +
        "buildingId=" + buildingId +
        ", cityId=" + cityId +
        ", pinyinInitials=" + pinyinInitials +
        ", propertyUse=" + propertyUse +
        ", propertyType=" + propertyType +
        ", buildingTime=" + buildingTime +
        ", profileAddress=" + profileAddress +
        ", detailedAddress=" + detailedAddress +
        ", saleAveragePrice=" + saleAveragePrice +
        ", manageFee=" + manageFee +
        ", note=" + note +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
