package cn.stylefeng.guns.modular.estate.model.result;

import lombok.Data;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 城市
 * </p>
 *
 * @author 李利光
 * @since 2019-07-11
 */
@Data
public class CityResult implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 主键id
     */
    private Long cityId;

    /**
     * 父城区id
     */
    private Long pid;

    /**
     *  名称
     */
    private String name;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

}
