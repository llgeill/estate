layui.use(['table', 'admin', 'ax'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;

    /**
     * 房源信息管理
     */
    var HouseResource = {
        tableId: "houseResourceTable"
    };

    /**
     * 初始化表格的列
     */
    HouseResource.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'houseResourceId', hide: true, title: '主键id'},
            {field: 'buildingId', sort: true, title: '城区id'},
            {field: 'buildingBlockId', sort: true, title: '栋座id'},
            {field: 'roomNumber', sort: true, title: '房号'},
            {field: 'floor', sort: true, title: '楼层'},
            {field: 'roomTotal', sort: true, title: '房'},
            {field: 'hallTotal', sort: true, title: '厅'},
            {field: 'toiletTotal', sort: true, title: '卫'},
            {field: 'balconyTotal', sort: true, title: '阳台'},
            {field: 'purpose', sort: true, title: '用途'},
            {field: 'houseResourceType', sort: true, title: '房源类型'},
            {field: 'area', sort: true, title: '面积'},
            {field: 'practicalArea', sort: true, title: '实用面积'},
            {field: 'orientation', sort: true, title: '朝向'},
            {field: 'houseType', sort: true, title: '房屋类型'},
            {field: 'buildingTime', sort: true, title: '建房年代'},
            {field: 'transaction', sort: true, title: '交易'},
            {field: 'state', sort: true, title: '状态'},
            {field: 'price', sort: true, title: '售价'},
            {field: 'rental', sort: true, title: '租价'},
            {field: 'priceFloor', sort: true, title: '出售底价'},
            {field: 'rentalFloor', sort: true, title: '租价底价'},
            {field: 'taxes', sort: true, title: '包税费'},
            {field: 'resource', sort: true, title: '来源'},
            {field: 'statusQuo', sort: true, title: '现状'},
            {field: 'decorate', sort: true, title: '装修情况'},
            {field: 'matchState', sort: true, title: '配套情况'},
            {field: 'furnitureState', sort: true, title: '家具情况'},
            {field: 'householdElectricalState', sort: true, title: '家电情况'},
            {field: 'propertyRight', sort: true, title: '产权'},
            {field: 'certificates', sort: true, title: '证件'},
            {field: 'payment', sort: true, title: '付款'},
            {field: 'payCommission', sort: true, title: '付佣'},
            {field: 'houseInspection', sort: true, title: '看房'},
            {field: 'keyNumber', sort: true, title: '钥匙号'},
            {field: 'entrust', sort: true, title: '委托方式'},
            {field: 'staff', sort: true, title: '员工'},
            {field: 'ownerName', sort: true, title: '业主姓名'},
            {field: 'ownerPhone', sort: true, title: '业主手机号'},
            {field: 'contactsContent', sort: true, title: '联系人内容'},
            {field: 'manageExpense', sort: true, title: '管理费'},
            {field: 'remark', sort: true, title: '备注'},
            {field: 'nationality', sort: true, title: '国籍'},
            {field: 'belongToId', sort: true, title: '属主用户id'},
            {field: 'createTime', sort: true, title: '创建时间'},
            {field: 'updateTime', sort: true, title: '修改时间'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    HouseResource.search = function () {
        var queryData = {};
        queryData['condition'] = $("#condition").val();
        table.reload(HouseResource.tableId, {where: queryData});
    };

    /**
     * 弹出添加对话框
     */
    HouseResource.openAddDlg = function () {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
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
            title: '修改房源信息',
            content: Feng.ctxPath + '/houseResource/edit?houseResourceId=' + data.houseResourceId,
            end: function () {
                admin.getTempData('formOk') && table.reload(HouseResource.tableId);
            }
        });
    };

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

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + HouseResource.tableId,
        url: Feng.ctxPath + '/houseResource/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: HouseResource.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        HouseResource.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        HouseResource.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        HouseResource.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + HouseResource.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            HouseResource.openEditDlg(data);
        } else if (layEvent === 'delete') {
            HouseResource.onDeleteItem(data);
        }
    });
});
layui.use(['element', 'layer'], function(){
    var element = layui.element
        ,layer = layui.layer;

    element.on('tab(docDemoTabBrief)', function(data){
        layer.msg('切到到了'+ data.index + '：' + this.innerHTML);
    });
});
layui.use('element', function(){
    var element = layui.element;
});

// layer.msg('hello');
//
layui.use('laydate', function(){
    var laydate = layui.laydate;

    //执行一个laydate实例
    laydate.render({
        elem: '#dateRange',
        range: true
    });
    laydate.render({
        elem: '#test6'
        ,range: true
    });
});

//选项卡 - 主页面的选项卡
layui.use('carousel', function() {
    var carousel = layui.carousel;
    var ins = carousel.render({
        elem: '#test1',
        width: '100%', //设置容器宽度
        height: '100%',
        arrow: 'always', //始终显示箭头
        //,anim: 'updown' //切换动画方式
        autoplay: false
    });
    //重置轮播
    ins.reload({
        elem: '#test1',
        width: '100%', //设置容器宽度
        height: '100%',
        arrow: 'always', //始终显示箭头
        //,anim: 'updown' //切换动画方式
        autoplay: false
    });

    var ins2 = carousel.render({
        elem: '#test2',
        width: '100%', //设置容器宽度
        height: '100%',
        arrow: 'always', //始终显示箭头
        //,anim: 'updown' //切换动画方式
        autoplay: false
    });
    ins2.reload({
        elem: '#test2',
        width: '100%', //设置容器宽度
        height: '100%',
        arrow: 'always', //始终显示箭头
        //,anim: 'updown' //切换动画方式
        autoplay: false
    });

});
layui.use('layer', function(){
    var layer = layui.layer;

    layer.tab({
        area: ['900px', '400px'],
        tab: [{
            title: '<li class="layui-this"><i class="layui-icon">&#xe68e;</i> 详细属性</li>',
            content: '<table  class="layui-table"  lay-skin="nob" >\n' +
            '\n' +
            '                                    <tbody>\n' +
            '                                    <tr>\n' +
            '                                        <td>2房</td>\n' +
            '                                        <td>出售</td>\n' +
            '                                        <td>出售</td>\n' +
            '                                        <td>一卫</td>\n' +
            '                                        <td>一卫</td>\n' +
            '                                    </tr>\n' +
            '                                    <tr>\n' +
            '                                        <td>1200元/月</td>\n' +
            '                                        <td>合景天峻</td>\n' +
            '                                        <td>合景天峻</td>\n' +
            '                                        <td>有钥匙</td>\n' +
            '                                        <td>有钥匙</td>\n' +
            '                                    </tr>\n' +
            '                                    </tbody>\n' +
            '                                </table>\n'
        },
            {
                title: '<li><i class="layui-icon">&#xe66e;</i> 跟进情况</li>',
                content: '<ul class="layui-timeline" >\n' +
                '                                    <li class="layui-timeline-item">\n' +
                '                                        <i class="layui-icon layui-timeline-axis">&#xe63f;</i>\n' +
                '                                        <div class="layui-timeline-content layui-text">\n' +
                '                                            <h3 class="layui-timeline-title">8月18日</h3>\n' +
                '                                            <p>\n' +
                '                                                layui 2.0 的一切准备工作似乎都已到位。发布之弦，一触即发。\n' +
                '                                                <br>不枉近百个日日夜夜与之为伴。因小而大，因弱而强。\n' +
                '                                                <br>无论它能走多远，抑或如何支撑？至少我曾倾注全心，无怨无悔 <i class="layui-icon"></i>\n' +
                '                                            </p>\n' +
                '                                        </div>\n' +
                '                                    </li>\n' +
                '                                    <li class="layui-timeline-item">\n' +
                '                                        <i class="layui-icon layui-timeline-axis">&#xe63f;</i>\n' +
                '                                        <div class="layui-timeline-content layui-text">\n' +
                '                                            <h3 class="layui-timeline-title">8月16日</h3>\n' +
                '                                            <p>杜甫的思想核心是儒家的仁政思想，他有“<em>致君尧舜上，再使风俗淳</em>”的宏伟抱负。个人最爱的名篇有：</p>\n' +
                '                                            <ul>\n' +
                '                                                <li>《登高》</li>\n' +
                '                                                <li>《茅屋为秋风所破歌》</li>\n' +
                '                                            </ul>\n' +
                '                                        </div>\n' +
                '                                    </li>\n' +
                '                                    <li class="layui-timeline-item">\n' +
                '                                        <i class="layui-icon layui-timeline-axis">&#xe63f;</i>\n' +
                '                                        <div class="layui-timeline-content layui-text">\n' +
                '                                            <h3 class="layui-timeline-title">8月15日</h3>\n' +
                '                                            <p>\n' +
                '                                                中国人民抗日战争胜利72周年\n' +
                '                                                <br>常常在想，尽管对这个国家有这样那样的抱怨，但我们的确生在了最好的时代\n' +
                '                                                <br>铭记、感恩\n' +
                '                                                <br>所有为中华民族浴血奋战的英雄将士\n' +
                '                                                <br>永垂不朽\n' +
                '                                            </p>\n' +
                '                                        </div>\n' +
                '                                    </li>\n' +
                '                                    <li class="layui-timeline-item">\n' +
                '                                        <i class="layui-icon layui-timeline-axis">&#xe63f;</i>\n' +
                '                                        <div class="layui-timeline-content layui-text">\n' +
                '                                            <div class="layui-timeline-title">过去</div>\n' +
                '                                        </div>\n' +
                '                                    </li>\n' +
                '                                </ul>'

            },

            {
                title: '<li><i class="layui-icon">&#xe60d;</i> 房源照片</li>',
                content: '  <div style="height: 360px"><div class="layui-carousel" id="test1" >\n' +
                '                                    <div carousel-item style="height: 316px">\n' +
                '                                        <div>条目1</div>\n' +
                '                                        <div>条目2</div>\n' +
                '                                        <div>条目3</div>\n' +
                '                                        <div>条目4</div>\n' +
                '                                        <div>条目5</div>\n' +
                '                                    </div>\n' +
                '                                </div>\n' +
                '</div>'
            }
            ,
            {
                title: '<li><i class="layui-icon">&#xe6b2;</i> 添加跟进信息</li>',
                content: '<div class="layui-form-item layui-form-text" style="padding-top: 20px">\n' +
                '                                <label class="layui-form-label" style="font-weight: normal;text-align: left;width: 140px;">\n' +
                '                                                        <span>编号：10110</span><br> <span>合景天峻:两房一厅</span><br>\n' +
                '                                            <span>租1500/月</span><br>' +
                '<span> 业务员： 李利光</span><br>\n' +
                '                                            </label>\n' +
                '                                       <div class="layui-input-block">\n' +
                '                                                <textarea placeholder="请输入内容" class="layui-textarea" style="width: 91%"></textarea>\n' +
                '                                               </div>\n' +
                '                                          </div>\n' +
                '\n' +
                '                                    <div class="layui-form-item">\n' +
                '                                     <div class="layui-input-block" style=" margin-left: 169px;">\n' +
                '                                           <button class="layui-btn" lay-submit="" lay-filter="demo1">立即添加</button>\n' +
                '                                            <button type="reset" class="layui-btn layui-btn-primary">重置</button>\n' +
                '                                            </div>\n' +
                '                                      </div> '


            }

        ]

    });

});
