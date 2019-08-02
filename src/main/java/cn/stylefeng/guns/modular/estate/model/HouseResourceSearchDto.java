package cn.stylefeng.guns.modular.estate.model;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("houseResourceSearchDto")
public class HouseResourceSearchDto {
    private Long HouseResourceId;
    private String condition;
    private String buildingBlock;
    private Long buildingBlockId;
    private String roomTotalCondition;
    private String roomNumber;
    private String roomTotal;
    private Integer roomTotalStart;
    private Integer roomTotalEnd;

    private String transaction;
    private String state;
    private String allState;
    private String allRender;
    private String allSell;

    private String rental;
    private Double rentalStart;
    private Double rentalEnd;

    private String price;
    private Double priceStart;
    private Double priceEnd;


    private String area;
    private Double areaStart;
    private Double areaEnd;

    private String houseType;
    private String orientation;
    private String entrustBetweenTime;
    private String beginTime;
    private String endTime;
    private String purpose;
    private String hallToilet;
    private String hall;
    private String toilet;
    private Integer hallTotal;
    private Integer hallToiletTotal;
    private Integer toiletTotal;
    private String houseResourceType;
    private Integer  entrustDateScope;

    private Long staffId;
    private String ownerPhone;
    private Long belongId;
    private String stateSlave;

    private Integer quickTime;

    private String myFollowInfoHouseResource;
    private Long myId;


 

}
