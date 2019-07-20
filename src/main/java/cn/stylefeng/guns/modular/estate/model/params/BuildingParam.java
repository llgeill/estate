package cn.stylefeng.guns.modular.estate.model.params;

import lombok.Data;
import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 城区
 * </p>
 *
 * @author 李利光
 * @since 2019-07-11
 */
@Data
public class BuildingParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;


    /**
     * 主键id
     */
    private Long buildingId;

    /**
     * 城区id
     */
    private Long cityId;

    /**
     * 拼音缩写
     */
    private String pinyinInitials;

    /**
     * 物业用途
     */
    private String propertyUse;

    /**
     * 物业类型
     */
    private String propertyType;

    /**
     * 建房年代
     */
    private Integer buildingTime;

    /**
     * 概要地址
     */
    private String profileAddress;

    /**
     * 详细地址
     */
    private String detailedAddress;

    /**
     * 售均价
     */
    private Double saleAveragePrice;

    /**
     * 管理费
     */
    private Double manageFee;

    /**
     * 备注
     */
    private String note;

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
