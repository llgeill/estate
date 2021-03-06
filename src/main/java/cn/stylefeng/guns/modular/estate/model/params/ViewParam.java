package cn.stylefeng.guns.modular.estate.model.params;

import lombok.Data;
import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 视图
 * </p>
 *
 * @author 李利光
 * @since 2019-07-11
 */
@Data
public class ViewParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;


    /**
     * 主键id
     */
    private Long viewId;

    /**
     * 来源id
     */
    private Long resourceId;

    /**
     * 视图路径
     */
    private String viewPath;

    /**
     * 视图类型
     */
    private String viewType;

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
