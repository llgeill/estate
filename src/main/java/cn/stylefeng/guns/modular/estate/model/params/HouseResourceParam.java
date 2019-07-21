package cn.stylefeng.guns.modular.estate.model.params;

import lombok.Data;
import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 房源信息
 * </p>
 *
 * @author 李利光
 * @since 2019-07-11
 */
@Data
public class HouseResourceParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;


    /**
     * 主键id
     */
    private Long houseResourceId;

    /**
     * 城区id
     */
    private Long buildingId;

    /**
     * 栋座id
     */
    private Long buildingBlockId;

    /**
     * 房号
     */
    private Integer roomNumber;

    /**
     * 楼层
     */
    private Integer floor;

    /**
     * 房
     */
    private Integer roomTotal;

    /**
     * 厅
     */
    private Integer hallTotal;

    /**
     * 卫
     */
    private Integer toiletTotal;

    /**
     * 阳台
     */
    private String balconyTotal;

    /**
     * 用途
     */
    private String purpose;

    /**
     * 房源类型
     */
    private String houseResourceType;

    /**
     * 面积
     */
    private String area;

    /**
     * 实用面积
     */
    private String practicalArea;

    /**
     * 朝向
     */
    private String orientation;

    /**
     * 房屋类型
     */
    private String houseType;

    /**
     * 建房年代
     */
    private Integer buildingTime;

    /**
     * 交易
     */
    private String transaction;

    /**
     * 状态
     */
    private String state;

    /**
     * 售价
     */
    private String price;

    /**
     * 租价
     */
    private String rental;

    /**
     * 出售底价
     */
    private String priceFloor;

    /**
     * 租价底价
     */
    private String rentalFloor;

    /**
     * 包税费
     */
    private String taxes;

    /**
     * 来源
     */
    private String resource;

    /**
     * 现状
     */
    private String statusQuo;

    /**
     * 装修情况
     */
    private String decorate;

    /**
     * 配套情况
     */
    private String matchState;

    /**
     * 家具情况
     */
    private String furnitureState;

    /**
     * 家电情况
     */
    private String householdElectricalState;

    /**
     * 产权
     */
    private String propertyRight;

    /**
     * 证件
     */
    private String certificates;

    /**
     * 付款
     */
    private String payment;

    /**
     * 付佣
     */
    private String payCommission;

    /**
     * 看房
     */
    private String houseInspection;

    /**
     * 钥匙号
     */
    private Integer keyNumber;

    /**
     * 委托方式
     */
    private String entrust;

    /**
     * 员工
     */
    private String staff;

    /**
     * 业主姓名
     */
    private String ownerName;

    /**
     * 业主手机号
     */
    private String ownerPhone;

    /**
     * 联系人内容
     */
    private String contactsContent;

    /**
     * 管理费
     */
    private String manageExpense;

    /**
     * 备注
     */
    private String remark;

    /**
     * 国籍
     */
    private String nationality;

    /**
     * 属主用户id
     */
    private Integer belongToId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    @Override
    public String checkParam() {
        return null;
    }

}
