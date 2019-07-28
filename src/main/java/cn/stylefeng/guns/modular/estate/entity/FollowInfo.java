package cn.stylefeng.guns.modular.estate.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;

/**
 * <p>
 * 跟进信息
 * </p>
 *
 * @author 李利光
 * @since 2019-07-11
 */
public class FollowInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "follow_info_id", type = IdType.ID_WORKER)
    private Long followInfoId;

    /**
     * 房源id
     */
    @TableField("house_resource_id")
    private Long houseResourceId;

    /**
     * 员工id
     */
    @TableField("staff_id")
    private Long staffId;

    /**
     * 员工姓名
     */
    @TableField("staff_name")
    private String staffName;

    /**
     * 部门id
     */
    @TableField("dept_id")
    private Long deptId;

    /**
     * 内容
     */
    @TableField("content")
    private String content;

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


    public Long getFollowInfoId() {
        return followInfoId;
    }

    public void setFollowInfoId(Long followInfoId) {
        this.followInfoId = followInfoId;
    }

    public Long getHouseResourceId() {
        return houseResourceId;
    }

    public void setHouseResourceId(Long houseResourceId) {
        this.houseResourceId = houseResourceId;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    @Override
    public String toString() {
        return "FollowInfo{" +
        "followInfoId=" + followInfoId +
        ", houseResourceId=" + houseResourceId +
        ", staffName=" + staffName +
        ", deptId=" + deptId +
        ", content=" + content +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
