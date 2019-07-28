package cn.stylefeng.guns.modular.estate.model.params;

import lombok.Data;
import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
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
public class FollowInfoParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;


    /**
     * 主键id
     */
    private Long followInfoId;

    /**
     * 房源id
     */
    private Long houseResourceId;


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
    private Long deptId;

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

    @Override
    public String checkParam() {
        return null;
    }

}
