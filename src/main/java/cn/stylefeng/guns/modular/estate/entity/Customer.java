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
 * 客户信息
 * </p>
 *
 * @author 
 * @since 2019-08-03
 */
@TableName("estate_customer")
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "customer_id", type = IdType.ID_WORKER)
    private Long customerId;

    /**
     * 姓名
     */
    @TableField("name")
    private String name;

    /**
     * 手机号
     */
    @TableField("phone")
    private String phone;

    /**
     * 租价
     */
    @TableField("rental")
    private Double rental;

    /**
     * 售价
     */
    @TableField("price")
    private Double price;

    /**
     * 售房时间
     */
    @TableField("price_start")
    private Date priceStart;

    /**
     * 租房起始时间
     */
    @TableField("rental_start")
    private Date rentalStart;

    /**
     * 租房结束时间
     */
    @TableField("rental_end")
    private Date rentalEnd;

    /**
     * 售价税费
     */
    @TableField("price_tax")
    private Double priceTax;

    /**
     * 户型
     */
    @TableField("house_type")
    private String houseType;

    /**
     * 方向
     */
    @TableField("direction")
    private String direction;

    /**
     * 栋座名称
     */
    @TableField("building_block")
    private Long buildingBlock;

    /**
     * 楼层
     */
    @TableField("floor")
    private String floor;

    /**
     * 房号
     */
    @TableField("room_number")
    private Integer roomNumber;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;

    /**
     * 客户类型
     */
    @TableField("customer_type")
    private String customerType;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    private Date updateTime;


    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Double getRental() {
        return rental;
    }

    public void setRental(Double rental) {
        this.rental = rental;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getPriceStart() {
        return priceStart;
    }

    public void setPriceStart(Date priceStart) {
        this.priceStart = priceStart;
    }

    public Date getRentalStart() {
        return rentalStart;
    }

    public void setRentalStart(Date rentalStart) {
        this.rentalStart = rentalStart;
    }

    public Date getRentalEnd() {
        return rentalEnd;
    }

    public void setRentalEnd(Date rentalEnd) {
        this.rentalEnd = rentalEnd;
    }

    public Double getPriceTax() {
        return priceTax;
    }

    public void setPriceTax(Double priceTax) {
        this.priceTax = priceTax;
    }

    public String getHouseType() {
        return houseType;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Long getBuildingBlock() {
        return buildingBlock;
    }

    public void setBuildingBlock(Long buildingBlock) {
        this.buildingBlock = buildingBlock;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
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
        return "Customer{" +
        "customerId=" + customerId +
        ", name=" + name +
        ", phone=" + phone +
        ", rental=" + rental +
        ", price=" + price +
        ", priceStart=" + priceStart +
        ", rentalStart=" + rentalStart +
        ", rentalEnd=" + rentalEnd +
        ", priceTax=" + priceTax +
        ", houseType=" + houseType +
        ", direction=" + direction +
        ", buildingBlock=" + buildingBlock +
        ", floor=" + floor +
        ", roomNumber=" + roomNumber +
        ", remark=" + remark +
        ", customerType=" + customerType +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
