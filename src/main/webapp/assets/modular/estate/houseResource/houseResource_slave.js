/**
 * 折叠
 * @param data
 */
var ins2;
//行數據
var globalData=null;
//记录是否收缩bottomInfo
var flag=true;
var viewer = new Viewer(document.getElementById('viewInfo'));

var globalFlag=true;

layui.use(['table','form','upload', 'admin', 'ax','laydate','element'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var laydate = layui.laydate;
    var form =layui.form;
    var upload = layui.upload;
    var element = layui.element;
    var layer = layui.layer;




    /**
     * 房源信息管理
     */
    var HouseResource = {
        tableId: "houseResourceTable"
    };


    /**
     * 跟进信息管理
     */
    var FollowInfo = {
        tableId: "followInfoTable"
    };

    /**
     * 城区管理
     */
    var Building = {
        tableId: "buildingTable"
    };

    /**
     * 初始化表格的列
     */
    HouseResource.initColumn = function () {
        return [[
            {type: 'checkbox'},
            // {field: 'houseResourceId', hide: true, title: '主键id'},
            {field: 'buildingName', sort: true, title: '城区'},
            {
                field: 'transaction', sort: true, title: '交易', templet: function (d) {
                    if (d.transaction == '出租') {
                        return "<span style='color: red;font-weight: bold''>出租</span>";
                    }else if(d.transaction == '出售'){
                        return "<span style='color: green;font-weight: bold''>出售</span>";
                    }else{
                        return "<span style='color: deepskyblue;font-weight: bold'>"+d.transaction+"</span>"
                    }
                }
            },
            // {field: 'state', sort: true, title: '状态'},
            {
                field: 'state', sort: true, title: '状态',templet: function (d) {
                    if (d.staffId == d.myId) {
                        if(d.state=='我租'){
                            return  "<span style='color: red;font-weight: bold''>我租</span>";
                        }else if(d.state=="我售"){
                            return "<span style='color: green;font-weight: bold''>我售</span>";
                        }else{
                            return d.state;
                        }
                    }else{
                        if(d.state=='我租'){
                            return "已租"
                        }else if(d.state=="我售"){
                            return "已售"
                        }else{
                            return d.state;
                        }
                    }
                }
            },
            {field: 'buildingBlockName', sort: true, title: '栋座'},
            {field: 'roomNumber', sort: true, title: '房号'},
            {field: 'floor', sort: true, title: '楼层'},
            // {field: 'roomTotal', sort: true, title: '房'},
            {
                field: 'roomTotal', sort: true, title: '房型', templet: function (d) {
                    return d.roomTotal+"-"+d.hallTotal+"-"+d.toiletTotal+"-"+d.balconyTotal;
                }
            },
            // {field: 'hallTotal', sort: true, title: '厅'},
            // {field: 'toiletTotal', sort: true, title: '卫'},
            // {field: 'balconyTotal' , sort: true, title: '阳台'},
            // {field: 'purpose',hide: true, sort: true, title: '用途'},
            // {field: 'houseResourceType',hide: true, sort: true, title: '房源类型'},
            {field: 'area', sort: true, title: '面积'},
            // {field: 'practicalArea',hide: true, sort: true, title: '实用面积'},
            {field: 'orientation', sort: true, title: '朝向'},
            // {field: 'houseType', hide: true,sort: true, title: '房屋类型'},
            // {field: 'buildingTime',hide: true, sort: true, title: '建房年代'},
            // {field: 'transaction',hide: true, sort: true, title: '交易'},




            // {field: 'price', hide: true,sort: true, title: '售价'},
            // {field: 'rental',hide: true, sort: true, title: '租价'},
            // {field: 'priceFloor', hide: true,sort: true, title: '出售底价'},
            // {field: 'rentalFloor', hide: true,sort: true, title: '租价底价'},
            // {field: 'taxes', hide: true,sort: true, title: '包税费'},
            // {field: 'resource',hide: true, sort: true, title: '来源'},
            // {field: 'statusQuo',hide: true, sort: true, title: '现状'},
            // {field: 'decorate',hide: true, sort: true, title: '装修情况'},
            // {field: 'matchState',hide: true, sort: true, title: '配套情况'},
            // {field: 'furnitureState',hide: true, sort: true, title: '家具情况'},
            // {field: 'householdElectricalState',hide: true, sort: true, title: '家电情况'},
            // {field: 'propertyRight', hide: true,sort: true, title: '产权'},
            // {field: 'certificates',hide: true, sort: true, title: '证件'},
            // {field: 'payment',hide: true, sort: true, title: '付款'},
            // {field: 'payCommission',hide: true, sort: true, title: '付佣'},
            // {field: 'houseInspection',hide: true, sort: true, title: '看房'},
            // {field: 'keyNumber',hide: true, sort: true, title: '钥匙号'},
            // {field: 'entrust',hide: true, sort: true, title: '委托方式'},
            // {field: 'staff_id', hide: true, sort: true, title: '员工id'},
            {field: 'staff', sort: true, title: '员工'},
            // {field: 'ownerName', hide: true,sort: true, title: '业主姓名'},
            // {field: 'ownerPhone',hide: true, sort: true, title: '业主手机号'},
            // {field: 'contactsContent',hide: true, sort: true, title: '联系人内容'},
            // {field: 'manageExpense',hide: true, sort: true, title: '管理费'},
            // {field: 'remark',hide: true, sort: true, title: '备注'},
            // {field: 'nationality',hide: true, sort: true, title: '国籍'},
            // {field: 'belongToId', sort: true, title: '属主用户id'},
            {field: 'createTime', sort: true, title: '委托时间'},
            {field: 'updateTime', hide: false,sort: true, title: '修改时间'},
            {align: 'center', toolbar: '#tableBar', title: '操作',fixed: 'right', width:120}
        ]];
    };

    //日期时间范围
    laydate.render({
        elem: '#entrustBetweenTime'
        ,range: true
        ,done: function(value, date, endDate){
            getAllSearchValue(value);
        }
    });


    /**
     * 点击查询按钮
     */
    HouseResource.search = function () {
        // initTable(-1);
        var queryData = {};
        queryData['condition'] = $("#condition").val();

         table.reload(HouseResource.tableId, {where: queryData});

    };

    // 关闭页面
    $('#btnBack').click(function () {
        window.location.href = Feng.ctxPath + "/system/console2";
    });

    /**
     * 弹出用户跟進對話框
     */
    HouseResource.openFollowInfo = function (userId) {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '用户跟进信息',
            area: ['400px', '400px'],
            content: Feng.ctxPath + '/mgr/followInfoUserList?userId='+userId,
            end: function () {
                admin.getTempData('formOk') && table.reload(MgrUser.tableId);
            }
        });
    };




    /**
     * 弹出添加对话框
     */
    HouseResource.openAddDlg = function () {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            area: ['950px', '700px'],
            title: '添加房源信息',
            content: Feng.ctxPath + '/houseResource/add',
            end: function () {
                admin.getTempData('formOk') && table.reload(HouseResource.tableId);
            }
        });
    };

    /**
     * 导出excel按钮
     */
    HouseResource.exportExcel = function () {
        var checkRows = table.checkStatus(HouseResource.tableId);

        if (checkRows.data.length === 0) {
            Feng.error("请选择要导出的数据");
        } else {
            table.exportFile(tableResult.config.id, checkRows.data, 'xls');
        }
    };

    /**
     * 点击编辑
     *
     * @param data 点击按钮时候的行数据
     */
    HouseResource.openEditDlg = function (data) {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            area: ['950px', '700px'],
            title: '修改房源信息',
            content: Feng.ctxPath + '/houseResource/edit?houseResourceId=' + data.houseResourceId,
            end: function () {
                followInfo(globalData.houseResourceId);
                admin.getTempData('formOk') && table.reload(HouseResource.tableId);
            }
        });
    };




    /**
     * 点击我租
     *
     * @param data 点击按钮时候的行数据
     */
    HouseResource.openRenderDlg = function (data) {
        if(data.state=='我租'){
            Feng.error('房源已经出租，请联系店长！')
        }else if(data.state=='我售'){
            Feng.error('房源已经出售，请联系店长！')
        } else{
            Feng.confirm('确认更改房源状态为（本人租出）?',renderAndSellState,data,'我租');
        }

    };

    /**
     * 点击我售
     *
     * @param data 点击按钮时候的行数据
     */
    HouseResource.openSellDlg = function (data) {
        if(data.state=='我售'){
            Feng.error('房源已经出售，请联系店长！')
        }else if(data.state=='出租'){
            Feng.error('房源已经出租，请联系店长！')
        }else{
            Feng.confirm('确认更改房源状态为（本人售出）?',renderAndSellState,data,'我售');
        }
    };


    function initBtnPhoneEvent() {
        $('#btnPhone').on("click",function () {
            if(globalData==null)return;
            var data=globalData;
            $("#phoneValue").html('&nbsp'+data.ownerName+'&nbsp'+data.ownerPhone+'<span id="btnPhone" style="margin-left: 15px;margin-bottom: 10px" class="layui-btn layui-btn-radius layui-btn-sm"><i class="layui-icon"></i>查看号码</span>');
            // var ajax = new $ax(Feng.ctxPath + "/followInfo/addItem", function (data) {
            //
            // }, function (data) {
            //     Feng.error("添加失败！" + data.responseJSON.message)
            // });
            // ajax.set("content","***********正在查看业主号码***********");
            // ajax.set("houseResourceId",data.houseResourceId);
            // ajax.start();
            // FollowInfo.openAddForceDlg();
        });
    }


    /**
     * 出租和出售
     * @param data
     * @param state
     */
    function renderAndSellState(data,state){

        var ajaxX = new $ax(Feng.ctxPath + "/houseResource/updateState", function (data) {
            Feng.success("状态修改成功")
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        data.state=state;
        ajaxX.set("houseResourceId", data.houseResourceId);
        ajaxX.set("state", state);
        ajaxX.start();
    }




    /**
     * 点击删除
     *
     * @param data 点击按钮时候的行数据
     */
    HouseResource.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/houseResource/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(HouseResource.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("houseResourceId", data.houseResourceId);
            ajax.start();
        };
        Feng.confirm("是否删除?", operation);
    };

    // table.render({
    //     elem: '#' + HouseResource.tableId,
    //     url: Feng.ctxPath + '/houseResource/list',
    //     page: true,
    //     limit:90,
    //     height: "full-158",
    //     height: 472,
    //     cols: HouseResource.initColumn(),
    // });
    // 渲染表格
    var tableResult = initTable(-1,15);


    function initTable(height,limit){
        if(height==-1){
            $('#bottomInfo').hide();
            flag=true;
            return table.render({
                elem: '#' + HouseResource.tableId,
                url: Feng.ctxPath + '/houseResource/list?belongId='+$("#belongId").val(),
                page: true,
                height: "full-158",
                limit:limit,
                cols: HouseResource.initColumn(),
            });
        }else{
            if(!flag)return;
            $('#bottomInfo').show();
            flag=false;

            return table.render({
                elem: '#' + HouseResource.tableId,
                url: Feng.ctxPath + '/houseResource/list?belongId='+$("#belongId").val(),
                page: true,
                limit:limit,
                height: "full-158",
                height: 472,
                cols: HouseResource.initColumn(),
            });
        }

    }


    // 搜索按钮点击事件
    $('#btnHouseSearch').click(function (event) {

        HouseResource.search();

    });

    // 搜索按钮点击事件
    $('#btnFollowInfo').click(function (event) {

        // HouseResource.openFollowInfo($("#belongId").val());

    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        HouseResource.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        HouseResource.exportExcel();
    });
    // 展开与折叠
    $('#foldAll').click(function () {
        if($("#showAndHideDiv").attr("shval")==1){
            $("#showAndHideDiv").hide();
            $("#foldAll").html('<i class="layui-icon"></i>展开</button>');
            $("#showAndHideDiv").attr("shval",2);
        }else{
            $("#showAndHideDiv").show();
            $("#foldAll").html('<i class="layui-icon"></i>折叠</button>');
            $("#showAndHideDiv").attr("shval",1);
        }
        return false;
    });

    // 工具条点击事件
    table.on('tool(' + HouseResource.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            HouseResource.openEditDlg(data);
        } else if (layEvent === 'delete') {
            HouseResource.onDeleteItem(data);
        } else if(layEvent==='render'){
            HouseResource.openRenderDlg(data);
        }else if(layEvent==='sell'){
            HouseResource.openSellDlg(data);
        }
    });

    //下拉事件
    form.on('select()', function(data){
        getAllSearchValue();
        form.render();
    });
    //选中交易后
    form.on('radio()', function(data){
        getAllSearchValue();
        form.render();
    });
    function getAllSearchValue(date,height) {
        var queryData = {};
        queryData['condition'] = $("#condition").val();
        queryData['roomTotal'] = $("#roomTotal").val();
        queryData['transaction'] = $("#transaction").val();
        queryData['state'] = $("#state").val();
        queryData['rental'] = $("input[name='rental']:checked").val();
        queryData['price'] = $("input[name='price']:checked").val();
        queryData['area'] = $("input[name='area']:checked").val();
        queryData['houseType'] = $("#houseType").val();
        queryData['orientation'] = $("#orientation").val();
        if(date!=null){
            queryData['entrustBetweenTime'] = date;
        }else{
            queryData['entrustBetweenTime'] = $("#entrustBetweenTime").val();
        }
        queryData['purpose'] = $("#purpose").val();
        queryData['hallToilet'] = $("#hallToilet").val();
        queryData['hallToiletTotal'] = $("#hallToiletTotal").val();
        queryData['houseResourceType'] = $("#houseResourceType").val();
        queryData['myFollowInfoHouseResource'] = $("#myFollowInfoHouseResource").val();
        if($("#quickTime").val()=="0") queryData['quickTime']=0;
        else queryData['quickTime'] = $("#quickTime").val();
        if(height!=null)table.reload(HouseResource.tableId, {where: queryData,page: true,limit:10,height:height});
        else table.reload(HouseResource.tableId, {where: queryData});
        if(!flag)return;
        $('#bottomInfo').show();
        flag=false;
    }
    //监听行单击事件（单击事件为：rowDouble）
    var ttFlag=true;
    table.on('row(houseResourceTable)', function(obj){
        if(ttFlag) getAllSearchValue(null,472);
        ttFlag=false;
        //詳細信息
        globalData=obj.data;
        var data = obj.data;
        houseResourceInfo(data);
        leftInfo(data);
        followInfo(data.houseResourceId);
        viewInfo(data.houseResourceId);
        $('#bottomInfo').show();
        //标注选中样式
        obj.tr.addClass('layui-table-click').siblings().removeClass('layui-table-click');
    });
    //填充详细信息
    function houseResourceInfo(data) {
        //填充信息
        var houseResourceInfo=
            '<table  class="layui-table"  lay-skin="nob" >\n' +
            '\n' +
            '                                    <tbody>\n' +
            '                                    <tr>\n' +
            '                                        <td>'+data.roomTotal+'房'+data.hallTotal+'厅'+data.toiletTotal+'卫'+data.balconyTotal+'阳台'+'</td>\n' +
            '                                        <td>'+data.purpose+'</td>\n' +
            '                                        <td>'+data.area+'m²</td>\n' +
            '                                        <td>'+data.orientation+'</td>\n' +
            '                                        <td>'+data.houseType+'</td>\n' +
            '                                    </tr>\n' +
            '                                    <tr>\n';

        if(data.rental!=null&&data.rental!=""){
            houseResourceInfo+='<td>'+data.rental+'元/月</td>\n'
        }else{
            houseResourceInfo+='<td>'+"不出租"+'</td>\n'
        }
        if(data.price!=null&&data.price!=""){
            houseResourceInfo+='<td>'+data.price+'万</td>\n'
        }else{
            houseResourceInfo+='<td>'+"不出售"+'</td>\n'
        }
        houseResourceInfo+= '<td>'+data.buildingTime+'年</td>\n' +
            '                                        <td>'+data.resource+'</td>\n' +
            '                                        <td>'+data.statusQuo+'</td>\n' +
            '                                    </tr>\n' +
            '                                    <tr>\n' +
            '                                        <td>'+data.matchState+'</td>\n' +
            '                                        <td>'+data.furnitureState+'</td>\n' +
            '                                        <td>'+data.householdElectricalState+'</td>\n' +
            '                                        <td>'+data.propertyRight+'</td>\n' +
            '                                        <td>'+data.certificates+'</td>\n' +
            '                                    </tr>\n' +
            '                                    <tr>\n' +
            '                                        <td>'+data.payment+'</td>\n' +
            '                                        <td>'+data.payCommission+'</td>\n' +
            '                                        <td>'+data.houseInspection+'</td>\n';
        if(data.keyNumber!=null&&data.keyNumber!=""){
            houseResourceInfo+= '<td>'+data.keyNumber+'</td>\n'
        }else{
            houseResourceInfo+= '<td>'+"无钥匙号"+'</td>\n'

        }
        houseResourceInfo+='<td>'+data.entrust+'</td>\n' +
            '</tr>\n' +
            '<tr><td>'+data.decorate+'</td></tr>'+
            '</tbody>\n' +
            '</table>\n'
        $("#houseResourceDetail").html(houseResourceInfo);
    }
    //填充左边信息
    function leftInfo(data) {
        //多媒体信息
        if(data==null){
            var ajaxX = new $ax(Feng.ctxPath + "/houseResource/detail" ,function (data) {
                //添加左边信息
                $("#leftInfo").html(leftInfoData(data.data));
                //添加号码事件
                initBtnPhoneEvent()
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajaxX.set("houseResourceId", globalData.houseResourceId);
            ajaxX.start();
        }else {
            $("#leftInfo").html(leftInfoData(data));
            initBtnPhoneEvent()
        }
    }

    /**
     * 弹出添加对话框
     */
    FollowInfo.openAddForceDlg = function () {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '添加跟进信息',
            area: ['400px', '500px'],
            closeBtn :0,
            content: Feng.ctxPath + '/followInfo/forceAdd?houseResourceId='+globalData.houseResourceId,
            end: function () {
                followInfo(globalData.houseResourceId);
            }
        });
    };



    function leftInfoData(data) {
        // #009688
        //     f2f2f2
        var numberPhone=data.ownerPhone.substring(0,3)+"****"+data.ownerPhone.substring(7,11);
        var leftInfo='';
        leftInfo+=
            '<div class="layui-card  layui-tab-card" style="width: 95%;height: 362px">\n' +
            '<div class="layui-card-header " style="background-color: #009688;height:41px;">\n' +
            '<p   style="color:white;font-size: 15px;font-weight: bold;overflow: hidden;\n' +
            'text-overflow: ellipsis;\n' +
            'white-space: nowrap;"> \n' +
            '&nbsp'+data.buildingName+' \n' +
            '&nbsp'+data.roomTotal+'房'+data.hallTotal+'厅'+data.toiletTotal+'卫\n';
        if(data.rental!=null&&data.rental!=""){
            leftInfo+='&nbsp租'+data.rental+'元/月';
        }
        if(data.price!=null&&data.price!=""){
            leftInfo+='&nbsp售'+data.price+'万';
        }
        leftInfo+='</p></div>';
        leftInfo+='<div class="layui-card-header layui-tab-card" style="margin-top:15px;height: 10%;border-style: none">\n' +
            '                                            <p id="phoneValue" style="font-size: 15px;padding-bottom: 10px;  overflow: hidden;\n' +
            '    text-overflow: ellipsis;\n' +
            '    white-space: nowrap;">&nbsp'+data.ownerName+'&nbsp'+numberPhone+'<span id="btnPhone" style="margin-left: 15px;margin-bottom: 10px" class="layui-btn layui-btn-radius layui-btn-sm"><i class="layui-icon"></i>查看号码\n' +
            '                                    </span></p>\n' +
            '                                        </div>\n' +
            '                                        <div class="layui-card-header layui-tab-card" style="margin-top:20px;height: 50%;border-style:none none ;">\n' +
            '                                            <p style="font-size: 15px;padding-bottom: 10px;overflow: hidden;text-overflow: ellipsis;">&nbsp'+data.remark+'</p>\n' +
            '                                        </div>\n' +
            '                                        <div class="layui-card-header " style="margin-top:20px;height: 10%">\n' +
            '                                            <p style="overflow: hidden;text-overflow: ellipsis;white-space: nowrap;">&nbsp'+data.deptName+' &nbsp'+data.staff+'&nbsp['+data.createTime+'委托]</p>\n' +
            '                                        </div>';
        return  leftInfo
    }
    //填充跟进信息
    function followInfo(houseResourceId) {
        //跟进信息
        var ajax = new $ax(Feng.ctxPath + "/followInfo/followInfoList", function (data) {
            var content="";
            if(data!=null&&data.length>0){
                for(var i=0;i<data.length;i++){
                    var temp='<li class="layui-timeline-item">\n' +
                        '<i class="layui-icon layui-timeline-axis">&#xe63f;</i>\n' +
                        ' <div class="layui-timeline-content layui-text">\n' +
                        '<span style="font-size: 16px;font-weight: bolder" class="layui-timeline-title">'+data[i].createTime+'&nbsp&nbsp'+data[i].staffName+'</span>\n' +
                        '<p>'+data[i].content+'</p>' +
                        '</div>\n' +
                        '</li>\n' ;
                    content+=temp;
                }
                $("#followInfoDetail").html(content);
            }else {
                $("#followInfoDetail").html('');
            }

        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("houseResourceId",houseResourceId);
        ajax.start();
    }
    //填充多媒体信息
    function viewInfo(houseResourceId) {
        //多媒体信息
        var ajaxX = new $ax(Feng.ctxPath + "/view/viewList", function (data) {
            var content=" <div class=\"layui-inline\" style=\"width: 160px\"  >\n" +
                "                                                    <i  id=\"addImage\" class=\"layui-icon layui-icon-add-1\" style=\"font-size:150px;line-height: 150px;color: #e6e6e6;\"></i>\n" +
                "                                                </div>";
            if(data!=null&&data.length>0){
                for(var i=0;i<data.length;i++){
                    var temp='<img width="160px" height="140px" id='+data[i].viewId+' class="layui-upload-img" src=http://localhost/'+data[i].viewPath+' ">\n';
                    content+=temp;
                }
                $("#viewInfo").html(content);
                viewer.update();
            }else {
                $("#viewInfo").html(content);
            }
            picupload("#addImage");

        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajaxX.set("resourceId ", houseResourceId);
        ajaxX.start();
    }
    //填充图片
    function picupload(id) {
        //图片上传
        upload.render({
            elem: id
            ,url: '/view/upload/'
            ,multiple: true
            ,accept: 'jpg'
            ,before: function(obj){
                if(globalData!=null){
                    this.data={'resourceId':globalData.houseResourceId};
                }else{
                    return;
                }
            }
            ,done: function(res){
                viewInfo(globalData.houseResourceId);
            }
        });

    }
    // 添加跟进点击事件
    $('#btnInfo').click(function () {
        FollowInfo.openAddDlg();
    });

    /**
     * 弹出添加对话框
     */
    FollowInfo.openAddDlg = function () {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '添加跟进信息',
            area: ['400px', '500px'],
            content: Feng.ctxPath + '/followInfo/add?houseResourceId='+globalData.houseResourceId,
            end: function () {
                followInfo(globalData.houseResourceId);
            }
        });
    };

    // 添加跟进点击事件
    $('#btnBuilding').click(function () {
        if(globalData==null)return;

        Building.openEditDlg(globalData);
    });

    /**
     * 点击编辑
     *
     * @param data 点击按钮时候的行数据
     */
    Building.openEditDlg = function (data) {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '查看城区',
            area: ['745px', '600px'],
            content: Feng.ctxPath + '/building/info?buildingId=' + data.buildingId,
            end: function () {

                admin.getTempData('formOk') && table.reload(Building.tableId);
            }
        });
    };
    element.on('tab(testLUBOO)', function(data){
        if(data.index==0){
            houseResourceInfo(globalData);
        }else if(data.index==1){
            followInfo(globalData.houseResourceId);
        }
    });
});


