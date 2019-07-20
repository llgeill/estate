package cn.stylefeng.guns.modular.estate.model.result;

import lombok.Data;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 楼盘
 * </p>
 *
 * @author 李利光
 * @since 2019-07-11
 */
@Data
public class BuildingBlockResult implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 主键id
     */
    private Long buildingBlockId;

    /**
     * 楼盘id
     */
    private Long buildingId;

    /**
     * 名称
     */
    private String name;

    /**
     * 总层数
     */
    private Double layerTotal;

    /**
     * 总面积
     */
    private Double areaTotal;

    /**
     * 总电梯数
     */
    private Double elevatorTotal;

}
