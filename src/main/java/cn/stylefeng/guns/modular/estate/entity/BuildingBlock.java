package cn.stylefeng.guns.modular.estate.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 楼盘
 * </p>
 *
 * @author 李利光
 * @since 2019-07-11
 */
@TableName("estate_building_block")
public class BuildingBlock implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "building_block_id", type = IdType.ID_WORKER)
    private Long buildingBlockId;

    /**
     * 楼盘id
     */
    @TableField("building_id")
    private Long buildingId;

    /**
     * 名称
     */
    @TableField("name")
    private String name;

    /**
     * 总层数
     */
    @TableField("layer_total")
    private Double layerTotal;

    /**
     * 总面积
     */
    @TableField("area_total")
    private Double areaTotal;

    /**
     * 总电梯数
     */
    @TableField("elevator_total")
    private Double elevatorTotal;


    public Long getBuildingBlockId() {
        return buildingBlockId;
    }

    public void setBuildingBlockId(Long buildingBlockId) {
        this.buildingBlockId = buildingBlockId;
    }

    public Long getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLayerTotal() {
        return layerTotal;
    }

    public void setLayerTotal(Double layerTotal) {
        this.layerTotal = layerTotal;
    }

    public Double getAreaTotal() {
        return areaTotal;
    }

    public void setAreaTotal(Double areaTotal) {
        this.areaTotal = areaTotal;
    }

    public Double getElevatorTotal() {
        return elevatorTotal;
    }

    public void setElevatorTotal(Double elevatorTotal) {
        this.elevatorTotal = elevatorTotal;
    }

    @Override
    public String toString() {
        return "BuildingBlock{" +
        "buildingBlockId=" + buildingBlockId +
        ", buildingId=" + buildingId +
        ", name=" + name +
        ", layerTotal=" + layerTotal +
        ", areaTotal=" + areaTotal +
        ", elevatorTotal=" + elevatorTotal +
        "}";
    }
}
