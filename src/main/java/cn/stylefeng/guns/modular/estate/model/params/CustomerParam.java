package cn.stylefeng.guns.modular.estate.model.params;

import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
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
public class CustomerParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;


    /**
     * 主键ID
     */
    private Long customerId;

    /**
     * 姓名
     */
    private String name;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 租价
     */
    private Double rental;

    /**
     * 售价
     */
    private Double price;

    /**
     * 售房时间
     */
    private Date priceStart;

    /**
     * 租房起始时间
     */
    private Date rentalStart;

    /**
     * 租房结束时间
     */
    private Date rentalEnd;

    /**
     * 售价税费
     */
    private Double priceTax;

    /**
     * 户型
     */
    private String houseType;

    /**
     * 方向
     */
    private String direction;

    /**
     * 栋座名称
     */
    private Long buildingBlock;

    /**
     * 楼层
     */
    private String floor;

    /**
     * 房号
     */
    private Integer roomNumber;

    /**
     * 备注
     */
    private String remark;

    /**
     * 客户类型
     */
    private String customerType;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    @Override
    public String checkParam() {
        return null;
    }

}
