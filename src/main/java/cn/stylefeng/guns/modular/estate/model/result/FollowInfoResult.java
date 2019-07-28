package cn.stylefeng.guns.modular.estate.model.result;

import lombok.Data;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 跟进信息
 * </p>
 *
 * @author 李利光
 * @since 2019-07-11
 */
@Data
public class FollowInfoResult implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 主键id
     */
    private Integer followInfoId;

    /**
     * 房源id
     */
    private Integer houseResourceId;


    /**
     *员工id
     */
    private Long staffId;


    /**
     * 员工姓名
     */
    private String staffName;

    /**
     * 部门id
     */
    private Integer deptId;

    /**
     * 内容
     */
    private String content;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

}
