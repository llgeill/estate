package cn.stylefeng.guns.modular.estate.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 跟进信息
 * </p>
 *
 * @author 李利光
 * @since 2019-07-11
 */
@Data
public class FollowInfoDto implements Serializable {

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
     *员工id
     */
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
     * 部门名称
     */
    private String pName;

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





}
