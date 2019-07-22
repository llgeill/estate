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
 * 房源信息
 * </p>
 *
 * @author 李利光
 * @since 2019-07-11
 */
@TableName("estate_house_resource")
public class HouseResource implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "house_resource_id", type = IdType.ID_WORKER)
    private Long houseResourceId;

    /**
     * 栋座id
     */
    @TableField("building_block_id")
    private Long buildingBlockId;


    /**
     * 城区id
     */
    @TableField("building_id")
    private Long buildingId;


    /**
     * 房号
     */
    @TableField("room_number")
    private Integer roomNumber;

    /**
     * 楼层
     */
    @TableField("floor")
    private Integer floor;

    /**
     * 房
     */
    @TableField("room_total")
    private Integer roomTotal;

    /**
     * 厅
     */
    @TableField("hall_total")
    private Integer hallTotal;

    /**
     * 卫
     */
    @TableField("toilet_total")
    private Integer toiletTotal;

    /**
     * 阳台
     */
    @TableField("balcony_total")
    private String balconyTotal;

    /**
     * 用途
     */
    @TableField("purpose")
    private String purpose;

    /**
     * 房源类型
     */
    @TableField("house_resource_type")
    private String houseResourceType;

    /**
     * 面积
     */
    @TableField("area")
    private String area;

    /**
     * 实用面积
     */
    @TableField("practical_area")
    private String practicalArea;

    /**
     * 朝向
     */
    @TableField("orientation")
    private String orientation;

    /**
     * 房屋类型
     */
    @TableField("house_type")
    private String houseType;

    /**
     * 建房年代
     */
    @TableField("building_time")
    private Integer buildingTime;

    /**
     * 交易
     */
    @TableField("transaction")
    private String transaction;

    /**
     * 状态
     */
    @TableField("state")
    private String state;

    /**
     * 售价
     */
    @TableField("price")
    private String price;

    /**
     * 租价
     */
    @TableField("rental")
    private String rental;

    /**
     * 出售底价
     */
    @TableField("price_floor")
    private String priceFloor;

    /**
     * 租价底价
     */
    @TableField("rental_floor")
    private String rentalFloor;

    /**
     * 包税费
     */
    @TableField("taxes")
    private String taxes;

    /**
     * 来源
     */
    @TableField("resource")
    private String resource;

    /**
     * 现状
     */
    @TableField("status_quo")
    private String statusQuo;

    /**
     * 装修情况
     */
    @TableField("decorate")
    private String decorate;

    /**
     * 配套情况
     */
    @TableField("match_state")
    private String matchState;

    /**
     * 家具情况
     */
    @TableField("furniture_state")
    private String furnitureState;

    /**
     * 家电情况
     */
    @TableField("household_electrical_state")
    private String householdElectricalState;

    /**
     * 产权
     */
    @TableField("property_right")
    private String propertyRight;

    /**
     * 证件
     */
    @TableField("certificates")
    private String certificates;

    /**
     * 付款
     */
    @TableField("payment")
    private String payment;

    /**
     * 付佣
     */
    @TableField("pay_commission")
    private String payCommission;

    /**
     * 看房
     */
    @TableField("house_inspection")
    private String houseInspection;

    /**
     * 钥匙号
     */
    @TableField("key_number")
    private Integer keyNumber;

    /**
     * 委托方式
     */
    @TableField("entrust")
    private String entrust;

    /**
     * 员工id
     */
    @TableField("staff_id")
    private Long staffId;

    /**
     * 员工
     */
    @TableField("staff")
    private String staff;

    /**
     * 业主姓名
     */
    @TableField("owner_name")
    private String ownerName;

    /**
     * 业主手机号
     */
    @TableField("owner_phone")
    private String ownerPhone;

    /**
     * 联系人内容
     */
    @TableField("contacts_content")
    private String contactsContent;

    /**
     * 管理费
     */
    @TableField("manage_expense")
    private String manageExpense;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;

    /**
     * 国籍
     */
    @TableField("nationality")
    private String nationality;

    /**
     * 属主用户id
     */
    @TableField("belong_to_id")
    private Integer belongToId;

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


    public Long getHouseResourceId() {
        return houseResourceId;
    }

    public void setHouseResourceId(Long houseResourceId) {
        this.houseResourceId = houseResourceId;
    }

    public Long getBuildingBlockId() {
        return buildingBlockId;
    }

    public void setBuildingBlockId(Long buildingBlockId) {
        this.buildingBlockId = buildingBlockId;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Integer getRoomTotal() {
        return roomTotal;
    }

    public void setRoomTotal(Integer roomTotal) {
        this.roomTotal = roomTotal;
    }

    public Integer getHallTotal() {
        return hallTotal;
    }

    public void setHallTotal(Integer hallTotal) {
        this.hallTotal = hallTotal;
    }

    public Integer getToiletTotal() {
        return toiletTotal;
    }

    public void setToiletTotal(Integer toiletTotal) {
        this.toiletTotal = toiletTotal;
    }

    public String getBalconyTotal() {
        return balconyTotal;
    }

    public void setBalconyTotal(String balconyTotal) {
        this.balconyTotal = balconyTotal;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getHouseResourceType() {
        return houseResourceType;
    }

    public void setHouseResourceType(String houseResourceType) {
        this.houseResourceType = houseResourceType;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPracticalArea() {
        return practicalArea;
    }

    public void setPracticalArea(String practicalArea) {
        this.practicalArea = practicalArea;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public String getHouseType() {
        return houseType;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }

    public Integer getBuildingTime() {
        return buildingTime;
    }

    public void setBuildingTime(Integer buildingTime) {
        this.buildingTime = buildingTime;
    }

    public String getTransaction() {
        return transaction;
    }

    public void setTransaction(String transaction) {
        this.transaction = transaction;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRental() {
        return rental;
    }

    public void setRental(String rental) {
        this.rental = rental;
    }

    public String getPriceFloor() {
        return priceFloor;
    }

    public void setPriceFloor(String priceFloor) {
        this.priceFloor = priceFloor;
    }

    public String getRentalFloor() {
        return rentalFloor;
    }

    public void setRentalFloor(String rentalFloor) {
        this.rentalFloor = rentalFloor;
    }

    public String getTaxes() {
        return taxes;
    }

    public void setTaxes(String taxes) {
        this.taxes = taxes;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getStatusQuo() {
        return statusQuo;
    }

    public void setStatusQuo(String statusQuo) {
        this.statusQuo = statusQuo;
    }

    public String getDecorate() {
        return decorate;
    }

    public void setDecorate(String decorate) {
        this.decorate = decorate;
    }

    public String getMatchState() {
        return matchState;
    }

    public void setMatchState(String matchState) {
        this.matchState = matchState;
    }

    public String getFurnitureState() {
        return furnitureState;
    }

    public void setFurnitureState(String furnitureState) {
        this.furnitureState = furnitureState;
    }

    public String getHouseholdElectricalState() {
        return householdElectricalState;
    }

    public void setHouseholdElectricalState(String householdElectricalState) {
        this.householdElectricalState = householdElectricalState;
    }

    public String getPropertyRight() {
        return propertyRight;
    }

    public void setPropertyRight(String propertyRight) {
        this.propertyRight = propertyRight;
    }

    public String getCertificates() {
        return certificates;
    }

    public void setCertificates(String certificates) {
        this.certificates = certificates;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getPayCommission() {
        return payCommission;
    }

    public void setPayCommission(String payCommission) {
        this.payCommission = payCommission;
    }

    public String getHouseInspection() {
        return houseInspection;
    }

    public void setHouseInspection(String houseInspection) {
        this.houseInspection = houseInspection;
    }

    public Integer getKeyNumber() {
        return keyNumber;
    }

    public void setKeyNumber(Integer keyNumber) {
        this.keyNumber = keyNumber;
    }

    public String getEntrust() {
        return entrust;
    }

    public void setEntrust(String entrust) {
        this.entrust = entrust;
    }

    public String getStaff() {
        return staff;
    }

    public void setStaff(String staff) {
        this.staff = staff;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerPhone() {
        return ownerPhone;
    }

    public void setOwnerPhone(String ownerPhone) {
        this.ownerPhone = ownerPhone;
    }

    public String getContactsContent() {
        return contactsContent;
    }

    public void setContactsContent(String contactsContent) {
        this.contactsContent = contactsContent;
    }

    public String getManageExpense() {
        return manageExpense;
    }

    public void setManageExpense(String manageExpense) {
        this.manageExpense = manageExpense;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Integer getBelongToId() {
        return belongToId;
    }

    public void setBelongToId(Integer belongToId) {
        this.belongToId = belongToId;
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

    public Long getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }



    @Override
    public String toString() {
        return "HouseResource{" +
        "houseResourceId=" + houseResourceId +
        ", buildingBlockId=" + buildingBlockId +
        ", roomNumber=" + roomNumber +
        ", floor=" + floor +
        ", roomTotal=" + roomTotal +
        ", hallTotal=" + hallTotal +
        ", toiletTotal=" + toiletTotal +
        ", balconyTotal=" + balconyTotal +
        ", purpose=" + purpose +
        ", houseResourceType=" + houseResourceType +
        ", area=" + area +
        ", practicalArea=" + practicalArea +
        ", orientation=" + orientation +
        ", houseType=" + houseType +
        ", buildingTime=" + buildingTime +
        ", transaction=" + transaction +
        ", state=" + state +
        ", price=" + price +
        ", rental=" + rental +
        ", priceFloor=" + priceFloor +
        ", rentalFloor=" + rentalFloor +
        ", taxes=" + taxes +
        ", resource=" + resource +
        ", statusQuo=" + statusQuo +
        ", decorate=" + decorate +
        ", matchState=" + matchState +
        ", furnitureState=" + furnitureState +
        ", householdElectricalState=" + householdElectricalState +
        ", propertyRight=" + propertyRight +
        ", certificates=" + certificates +
        ", payment=" + payment +
        ", payCommission=" + payCommission +
        ", houseInspection=" + houseInspection +
        ", keyNumber=" + keyNumber +
        ", entrust=" + entrust +
        ", staff=" + staff +
        ", ownerName=" + ownerName +
        ", ownerPhone=" + ownerPhone +
        ", contactsContent=" + contactsContent +
        ", manageExpense=" + manageExpense +
        ", remark=" + remark +
        ", nationality=" + nationality +
        ", belongToId=" + belongToId +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
