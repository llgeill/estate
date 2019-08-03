package cn.stylefeng.guns.modular.estate.model;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 客户信息
 * </p>
 *
 * @author 
 * @since 2019-08-03
 */

@Data
public class CustomerDto implements Serializable {

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

    private String condition;



}
