/**
 * Copyright 2018-2020 stylefeng & fengshuonan (https://gitee.com/stylefeng)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.stylefeng.guns.core.common.constant.dictmap;

import cn.stylefeng.guns.core.common.constant.dictmap.base.AbstractDictMap;

/**
 * 用户的字典
 *
 * @author fengshuonan
 * @date 2017-05-06 15:01
 */
public class HouseResourceDict extends AbstractDictMap {

    @Override
    public void init() {
        put("houseResourceId", "主键id");
        put("buildingBlockId", "栋座id");
        put("buildingId", "城区id");
        put("roomNumber", "房号");
        put("floor", "楼层");
        put("roomTotal", "房");
        put("hallTotal", "厅");
        put("toiletTotal", "卫");
        put("balconyTotal", "阳台");
        put("purpose", "用途");
        put("houseResourceType", "房源类型");

        put("area", "面积");
        put("practicalArea", "实用面积");
        put("orientation", "朝向");
        put("houseType", "房屋类型");
        put("buildingTime", "建房年代");
        put("transaction", "交易");
        put("state", "状态");
        put("price", "售价");
        put("rental", "租价");
        put("priceFloor", "出售底价");
        put("rentalFloor", "租价底价");

        put("taxes", "包税费");
        put("resource", "来源");
        put("statusQuo", "现状");
        put("decorate", "装修情况");
        put("matchState", "配套情况");
        put("furnitureState", "家具情况");
        put("householdElectricalState", "家电情况");
        put("propertyRight", "产权");
        put("certificates", "证件");
        put("payment", "付款");
        put("payCommission", "付佣");

        put("houseInspection", "看房");
        put("keyNumber", "钥匙号");
        put("entrust", "委托方式");
        put("staffId", "员工id");
        put("staff", "员工");
        put("ownerName", "业主姓名");
        put("ownerPhone", "业主手机号");
        put("contactsContent", "联系人内容");
        put("manageExpense", "管理费");
        put("remark", "备注");
        put("nationality", "国籍");

        put("belongToId", "属主用户id");
        put("createTime", "委托时间");
        put("update_time", "修改时间");



    }

    @Override
    protected void initBeWrapped() {
        putFieldWrapperMethodName("buildingBlockId", "getBuildingBlockName");
        putFieldWrapperMethodName("buildingId", "getBuildingName");

    }
}
