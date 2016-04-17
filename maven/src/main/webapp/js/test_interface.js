$(document).ready(function() {
    var prefix = "http://219.219.117.164/eroad-1.0/";
    var localPrefix = "http://localhost:8080/eroad-1.0/";
    prefix = localPrefix;
    //登录
    $("#btn_login_company").off('click').click(function(event) {
        var url = prefix + 'company/login.erd';
        var o = {};
        o.username = "aaa";
        o.password = "fdsa";

        ajaxPost(url, o, function(data, textStatus, xhr) {
            console.log(data);
        });
    });

    //注册
    $("#btn_reg_company").off('click').click(function(event) {
        // var url = prefix + 'company/regist.erd';
        // var o = {};
        // o.username = "aaa";
        // o.password = "fdsa";

        // ajaxPost(url, o, function(data, textStatus, xhr) {
        //     console.log(data);
        // });
    });

    //注销
    $("#btn_logout_company").off('click').click(function(event) {
        var url = prefix + 'company/logout.erd';

        ajaxPost(url, null, function(data, textStatus, xhr) {
            console.log(data);
        });
    });

    //显示周围50公里范围的车
    $("#btn_show50kilometerCars").off('click').click(function(event) {
        var url = prefix + 'company/show_car_aroud.erd';

        ajaxPost(url, null, function(data, textStatus, xhr) {
            console.log(data);
        });
    });
    // 发布订单
    $("#btn_releaseOrder").off('click').click(function(event) {
        var url = prefix + 'company/send_order.erd';
        var o = {};
        o.order_number = "123456";
        o.start_time = "2016-02-15";
        o.expect_end_time = "2016-03-30";
        o.expect_fare = "20000";
        o.description = "description";
        o.start_address = "some words about address";
        o.destination = "nanjing";
        o.sketch = "6";
        o.company_id = "1";

        ajaxPost(url, o, function(data, textStatus, xhr) {
            console.log(data);
        });
    });
    // 查看发布中的订单
    $("#btn_showReleaseOrders").off('click').click(function(event) {
        var url = prefix + 'company/get_bidding_order_list.erd';
        var o = {};
        o.id = "1";

        ajaxPost(url, o, function(data, textStatus, xhr) {
            console.log(data);
        });
    });
    // 订单成交
    $("#btn_deal").off('click').click(function(event) {
        var url = prefix + 'company/deal_order.erd';
        var o = {};

        o.order_id = "";
        o.car_id = "";
        o.company_id = "";
        o.price = "";

        ajaxPost(url, o, function(data, textStatus, xhr) {
            console.log(data);
        });
    });
    // 显示正在运行的订单信息列表
    $("#btn_showTransferOrders").off('click').click(function(event) {
        var url = prefix + 'company/get_executing_order.erd';
        var o = {};
        o.id = "1";

        ajaxPost(url, o, function(data, textStatus, xhr) {
            console.log(data);
        });
    });
    // 显示正在运行订单详细信息
    $("#btn_showTransferOrderDetail").off('click').click(function(event) {
        var url = prefix + 'company/get_executing_order_detail.erd';
        var o = {};
        o.id = "1";

        ajaxPost(url, o, function(data, textStatus, xhr) {
            console.log(data);
        });
    });
    // 订单结束
    $("#btn_endOrder").off('click').click(function(event) {
        var url = prefix + 'company/finish_order.erd';
        var o = {};
        o.order_id = "";
        o.car_id = "";
        o.evaluate = "";
        o.remark = "";
        o.exact_end_time = "";
        o.exact_start_time = "";

        ajaxPost(url, o, function(data, textStatus, xhr) {
            console.log(data);
        });
    });
    // 取消发布中的订单
    $("#btn_cancelReleasedOrder").off('click').click(function(event) {
        var url = prefix + 'company/cancel_launching_order.erd';
        var o = {};
        o.order_id = "";
        o.company_id = "1";

        ajaxPost(url, o, function(data, textStatus, xhr) {
            console.log(data);
        });
    });
    // 取消运输中的订单
    $("#btn_cancelTransferOrder").off('click').click(function(event) {
        var url = prefix + 'company/cancel_executing_order.erd';
        var o = {};
        o.order_id = "";
        o.car_id = "";
        o.reason = "";
        o.evaluate = "";

        ajaxPost(url, o, function(data, textStatus, xhr) {
            console.log(data);
        });
    });
    // 查看历史订单列表
    $("#btn_showHistoryOrders").off('click').click(function(event) {
        var url = prefix + 'company/get_history_order_list.erd';
        var o = {};
        o.id="1";

        ajaxPost(url, o, function(data, textStatus, xhr) {
            console.log(data);
        });
    });
    // 查看历史订单详细信息
    $("#btn_showHistoryOrderDetail").off('click').click(function(event) {
        var url = prefix + 'company/get_history_order_detail.erd';
        var o = {};
        o.order_number = "";

        ajaxPost(url, o, function(data, textStatus, xhr) {
            console.log(data);
        });
    });
    // 查询某车评价
    $("#btn_queryCarComment").off('click').click(function(event) {
        var url = prefix + 'company/get_car_remark.erd';
        var o = {};
        o.id = "";

        ajaxPost(url, o, function(data, textStatus, xhr) {
            console.log(data);
        });
    });

});
