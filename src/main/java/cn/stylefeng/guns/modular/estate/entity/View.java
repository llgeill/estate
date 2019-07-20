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
 * 视图
 * </p>
 *
 * @author 李利光
 * @since 2019-07-11
 */
@TableName("estate_view")
public class View implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "view_id", type = IdType.ID_WORKER)
    private Long viewId;

    /**
     * 来源id
     */
    @TableField("resource_id")
    private Integer resourceId;

    /**
     * 视图路径
     */
    @TableField("view_path")
    private String viewPath;

    /**
     * 视图类型
     */
    @TableField("view_type")
    private String viewType;

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


    public Long getViewId() {
        return viewId;
    }

    public void setViewId(Long viewId) {
        this.viewId = viewId;
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public String getViewPath() {
        return viewPath;
    }

    public void setViewPath(String viewPath) {
        this.viewPath = viewPath;
    }

    public String getViewType() {
        return viewType;
    }

    public void setViewType(String viewType) {
        this.viewType = viewType;
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
        return "View{" +
        "viewId=" + viewId +
        ", resourceId=" + resourceId +
        ", viewPath=" + viewPath +
        ", viewType=" + viewType +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
