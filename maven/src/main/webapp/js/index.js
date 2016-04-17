//页面全局对象
var AllGlobal = null;
/**
 * 脚本入口
 * @param  {[type]}   [description]
 * @return {[type]}   [description]
 */
$(document).ready(function() {
    AllGlobal = (function global() {
        //请求地址
        var prefix = "";
        var remotePrefix = "http://219.219.117.164/eroad-1.0/";
        var localPrefix = "http://localhost:8080/eroad/";
        //全局延迟对象
        var globalDfd = $.Deferred();
        //当前用户信息
        var userInfo = null;
        //坐标点数组
        var pointArr = null;
        //0未登录，1登录
        var pageStatus = 0;
        //绑定监听器，通过change事件的触发检测页面状态
        var pageCheckHelper = null;

        /*不同页面dom映射数组，偶数值为容器，偶数的右相邻奇数值为dom内容*/
        var unloginArray = null; //未登录
        var loginedArray = null; //登录初始页
        var loginedMapArray = null; //登录地图页

        return {
            init: function() {
                unloginArray = [];
                loginedArray = [];
                loginedMapArray = [];
                //保存未登录页面dom
                var str = "";
                str += '<div class="col_2 noRmargin box_left">';
                str += '    <div class="selector">';
                str += '        <select class="" name="" id="">';
                str += '            <option value="">车长</option>';
                str += '            <option value="">5m</option>';
                str += '            <option value="">6m</option>';
                str += '        </select>';
                str += '        <select class="" name="" id="">';
                str += '            <option value="">载重</option>';
                str += '            <option value="">1t</option>';
                str += '            <option value="">2t</option>';
                str += '        </select>';
                str += '        <select class="" name="" id="">';
                str += '            <option value="">状态</option>';
                str += '            <option value="">busy</option>';
                str += '            <option value="">free</option>';
                str += '        </select>';
                str += '    </div>';
                str += '    <div class="listView">';
                str += '        <ul>';
                str += '        </ul>';
                str += '    </div>';
                str += '</div>';
                str += '<div class="col_10 omega box_right">';
                str += '    <iframe id="iframe_map" src="./map/map.html"></iframe>';
                str += '</div>';
                unloginArray.push($("#content_box"));
                unloginArray.push(str);
                //保存登录页面dom
                str = "";
                str += '<div class="logined_top">';
                str += '    <span id="text_company_name">UR_d0623e8e（货代公司bbd64c67），欢迎您！</span>';
                str += '    <a id="btn_map" href="javascript:void(0);">地图</a>';
                str += '    <a id="btn_notification" href="javascript:void(0);">通知(<span class="count">0</span>)</a>';
                str += '    <a id="btn_logout" href="javascript:void(0);">退出</a>';
                str += '</div>';
                str += '<div class="logined_bottom">';
                str += '    <div id="btn_kzt" class="active ctrl"></div>';
                str += '    <div id="btn_yd" class="ctrl"></div>';
                str += '    <div id="btn_gs" class="ctrl"></div>';
                str += '</div>';
                loginedArray.push($("#header_box .nav_right"));
                loginedArray.push(str);
                str = "";
                str += '<div id="loginedContent" class="col_12">';
                str += '<iframe src="./l_kzt.html"></iframe>';
                str += '</div>';
                loginedArray.push($("#content_box"));
                loginedArray.push(str);
                //保存登录后进入地图的dom
                str = "";
                str += '<div class="logined_top">';
                str += '    <a id="btn_system" href="javascript:void(0);">进入系统</a>';
                str += '    <a id="btn_logout" href="javascript:void(0);">退出</a>';
                str += '</div>';
                str += '<div class="logined_bottom">';
                str += '    <div id="btn_kzt" class="active ctrl"></div>';
                str += '    <div id="btn_yd" class="ctrl"></div>';
                str += '    <div id="btn_gs" class="ctrl"></div>';
                str += '</div>';
                loginedMapArray.push($("#header_box .nav_right"));
                loginedMapArray.push(str);
                str = "";
                str += '<div class="col_2 noRmargin box_left">';
                str += '    <div class="selector">';
                str += '        <select class="" name="" id="">';
                str += '            <option value="">车长</option>';
                str += '            <option value="">5m</option>';
                str += '            <option value="">6m</option>';
                str += '        </select>';
                str += '        <select class="" name="" id="">';
                str += '            <option value="">载重</option>';
                str += '            <option value="">1t</option>';
                str += '            <option value="">2t</option>';
                str += '        </select>';
                str += '        <select class="" name="" id="">';
                str += '            <option value="">状态</option>';
                str += '            <option value="">busy</option>';
                str += '            <option value="">free</option>';
                str += '        </select>';
                str += '    </div>';
                str += '    <div class="listView">';
                str += '        <ul>';
                str += '        </ul>';
                str += '    </div>';
                str += '</div>';
                str += '<div class="col_10 omega box_right">';
                str += '    <iframe id="iframe_map" src="./map/map.html"></iframe>';
                str += '</div>';
                loginedMapArray.push($("#content_box"));
                loginedMapArray.push(str);
            },
            getPrefix: function() {
                prefix = localPrefix;
                return prefix;
            },
            setPageStatus: function(newStatus) {
                var contentArr = new Array();
                /*保存原内容*/
                var content_header = $("#header_box .nav_right").children();
                contentArr.push($("#header_box .nav_right"));
                contentArr.push(content_header);
                var content_main = $("#content_box").children();
                contentArr.push($("#content_box"));
                contentArr.push(content_main);
                /*清除原内容*/
                content_header.remove();
                content_main.remove();
                //切换页面状态前将旧页面保存的内容分配到对应的数组
                switch (pageStatus) {
                    case 0:
                        if (unloginArray != null) {
                            delete unloginArray;
                        }
                        unloginArray = contentArr;
                        break;
                    case 1:
                        if (loginedArray != null) {
                            delete loginedArray;
                        }
                        loginedArray = contentArr;
                        break;
                    case 2:
                        if (loginedMapArray != null) {
                            delete loginedMapArray;
                        }
                        loginedMapArray = contentArr;
                        break;
                    default:
                        break;
                }
                pageStatus = newStatus;
                pageCheckHelper.change();
            },
            //获取全局延迟对象
            getGlobalDfd:function(){
                return globalDfd.promise();
            },
            //改变延迟对象状态
            resolve:function(){
                globalDfd.resolve();
            },
            getPageStatus: function() {
                return pageStatus;
            },
            setPageCheckHelper: function(newHelper) {
                pageCheckHelper = newHelper;
            },
            getPageCheckHelper: function() {
                return pageCheckHelper;
            },
            setPointArr: function(newPointArr) {
                pointArr = newPointArr;
            },
            getPointArr: function() {
                return pointArr;
            },
            setContentArray: function(newArray) {
                //设置dom保存到数组
                unloginArray = newArray;
            },
            getContentArray: function() {
                var contentArr = new Array();
                switch (pageStatus) {
                    case 0:
                        contentArr = unloginArray;
                        break;
                    case 1:
                        contentArr = loginedArray;
                        break;
                    case 2:
                        contentArr = loginedMapArray;
                        break;
                    default:
                        break;
                }
                return contentArr;
            },
            //装载dom
            loadContentArray: function() {
                var contentArr = new Array();
                switch (pageStatus) {
                    case 0:
                        contentArr = unloginArray;
                        break;
                    case 1:
                        contentArr = loginedArray;
                        break;
                    case 2:
                        contentArr = loginedMapArray;
                        break;
                    default:
                        break;
                }
                //从数组还原到dom
                for (var i = 0; i < contentArr.length; i += 2) {
                    contentArr[i].html(contentArr[i + 1]);
                }
                contentArr = null;
            },
            setUserInfo: function(data) {
                var dfd = $.Deferred();
                userInfo = data;
                dfd.resolve();
                return dfd.promise();
            },
            getUserInfo: function() {
                return userInfo;
            }
        };
    })();
    //页面初始化
    pageInit();
});
/**
 * 页面初始化
 * @return {[type]} [description]
 */
function pageInit() {
    //全局初始化
    AllGlobal.init(); //初始化全局对象
    logic_nav(); //导航条效果
    $(window).resize(function(event) {
        $("nav ul li.checked").click();
    });

    //实时监测页面状态
    var pageCheckHelper = $("#hidden_check");
    pageCheckHelper.change(function(event) {
        //装载新的dom
        AllGlobal.loadContentArray();
        switch (AllGlobal.getPageStatus()) {
            case 1:
                /*登录后页面*/
                init_LoginedPage();
                break;
            case 2:
                /*进入登录地图状态*/
                init_LoginedMapPage();
                break;
            case 0:
                /*未登录页内容*/
                init_UnloginedPage();
                break;
            default:
                break;
        }
    });
    //绑定页面状态监测对象到全局对象
    AllGlobal.setPageCheckHelper(pageCheckHelper);
    //检测权限并绘制正确页面
    logic_detectsLogin();
}

/**
 * 未登录页面监听  pageStatus = 0;
 * @return {[type]} [description]
 */
function init_UnloginedPage() {
    var cars = logic_showCarAround();
    $.when(cars[0]).done(function() {
        var str = "";
        var pointArr = [];
        for (var i = 1; i < cars.length; i++) {
            str += '<li class="li_car">';
            str += '    <div class="img_car">';
            str += '        <img src="img/icon_greencar.png" height="48" width="48" alt="car">';
            str += '    </div>';
            str += '    <div class="labels">';
            str += '        <label class="title" for="" title="' + i + ' ' + cars[i].car_number + '(空闲)">' + i + ' ' + cars[i].car_number + '(空闲)</label>';
            str += '        <label for="" title="司机:' + cars[i].username + '">司机:' + cars[i].username + '</label>';
            str += '        <label for="" title="车辆情况：13.5米/35吨">车辆情况：13.5米/35吨</label>';
            str += '        <label for="" title="信用等级：未选">信用等级：</label>';
            str += '        <label for="" title="更新时间：2016/3/4 17:28:16">更新时间：2016/3/4 17:28:16</label>';
            str += '    </div>';
            str += '</li>';
            var o = {};
            o.latitude = cars[i].latitude;
            o.longtitude = cars[i].longtitude;
            pointArr.push(o);
        }
        $.when(AllGlobal.getGlobalDfd()).done(function(){
            AllGlobal.setPointArr(pointArr);
        });
            
        $("#content_box .box_left .listView ul").html(str);
    });

    //显示注册快
    $("#btn_signupbox").off('click').click(function(event) {
        $("#signup_box").toggleClass('display_none');
    });
    //注册块内效果
    logic_signupBox();
    // 关闭注册
    $("#btn_closeSignup").off('click').click(function(event) {
        $("#signup_box").toggleClass('display_none');
    });
    // 显示登录块
    $("#btn_signinbox").off('click').click(function(event) {
        $("#signin_box").toggleClass('display_none');
    });
    // 登录逻辑
    logic_signinBox();
    // 关闭登录块
    $("#btn_closeSignin").off('click').on('click', function(event) {
        event.preventDefault();
        if (!$("#signin_box").hasClass('display_none')) {
            $("#signin_box").toggleClass('display_none');
        }
    });
}
/**
 * 登录页面监听  pageStatus = 1;
 * @return {[type]} [description]
 */
function init_LoginedPage() {
    $("#text_company_name").html(AllGlobal.getUserInfo().company_name+"，欢迎您！");
    //隐藏登录框
    if (!$("#signin_box").hasClass('display_none'))
        $("#signin_box").addClass('display_none');
    // 注销按钮
    $("#btn_logout").off('click').click(function(event) {
        logic_logout();
    });
    // 进入地图按钮
    $("#btn_map").off('click').click(function(event) {
        AllGlobal.setPageStatus(2);
    });
    // 通知按钮
    $("#btn_notification").off('click').click(function(event) {
        $('#loginedContent iframe').attr('src', './l_notification.html');
    });
    ////所有控制按钮样式变化
    $('.logined_bottom .ctrl').off('click').click(function(event) {
        $('.logined_bottom .ctrl').removeClass('active');
        $(this).toggleClass('active');
        
    });
    // 控制台按钮
    $("#btn_kzt").click(function(event) {
        $('#loginedContent iframe').attr('src', './l_kzt.html');
    });
    // 运单按钮
    $("#btn_yd").click(function(event) {
        $('#loginedContent iframe').attr('src', './l_yd.html');
    });
    // 公司按钮
    $("#btn_gs").click(function(event) {
        $('#loginedContent iframe').attr('src', './l_gs.html');
    });
}

/**
 * 登录后
 * 地图页面监听 pageStatus = 2;
 * @return {[type]} [description]
 */
function init_LoginedMapPage() {
    var cars = logic_showCarAround();
    $.when(cars[0]).done(function() {
        var str = "";
        var pointArr = [];
        for (var i = 1; i < cars.length; i++) {
            str += '<li class="li_car">';
            str += '    <div class="img_car">';
            str += '        <img src="img/icon_greencar.png" height="48" width="48" alt="car">';
            str += '    </div>';
            str += '    <div class="labels">';
            str += '        <label class="title" for="" title="' + i + ' ' + cars[i].car_number + '(空闲)">' + i + ' ' + cars[i].car_number + '(空闲)</label>';
            str += '        <label for="" title="司机:' + cars[i].username + '">司机:' + cars[i].username + '</label>';
            str += '        <label for="" title="车辆情况：13.5米/35吨">车辆情况：13.5米/35吨</label>';
            str += '        <label for="" title="信用等级：未选">信用等级：</label>';
            str += '        <label for="" title="更新时间：2016/3/4 17:28:16">更新时间：2016/3/4 17:28:16</label>';
            str += '    </div>';
            str += '</li>';
            var o = {};
            o.latitude = cars[i].latitude;
            o.longtitude = cars[i].longtitude;
            pointArr.push(o);
        }
        $.when(AllGlobal.getGlobalDfd()).done(function(){
            AllGlobal.setPointArr(pointArr);
        });
            
        
        $("#content_box .box_left .listView ul").html(str);
    });
    // 注销按钮
    $("#btn_logout").off('click').click(function(event) {
        logic_logout();
    });
    //进入系统按钮
    $("#btn_system").off('click').click(function(event) {
        AllGlobal.setPageStatus(1);
    });
    ////所有控制按钮样式变化
    $('.logined_bottom .ctrl').off('click').click(function(event) {
        $('.logined_bottom .ctrl').removeClass('active');
        $(this).toggleClass('active');
    });
    // 控制台按钮
    $("#btn_kzt").click(function(event) {
        $('#loginedContent iframe').attr('src', './l_kzt.html');
    });
    // 运单按钮
    $("#btn_yd").click(function(event) {
        $('#loginedContent iframe').attr('src', './l_yd.html');
    });
    // 公司按钮
    $("#btn_gs").click(function(event) {
        $('#loginedContent iframe').attr('src', './l_gs.html');
    });
}




///////////////////////////////////////////////////////////////////////////////
/**
 * 导航逻辑
 * @return {[type]} [description]
 */
function logic_nav() {
    //每个导航按钮点击的效果
    $("nav ul li").click(function(e) {

        if ($(this).hasClass('slider')) {
            return;
        }

        $(".checked").removeClass('checked');
        $($(this).addClass('checked'));

        var whatTab = $(this).index();

        var howFar = $(this).width() * whatTab;

        $(".slider").css({
            left: howFar + "px"
        });

        $(".ripple").remove();

        var posX = $(this).offset().left,
            posY = $(this).offset().top,
            buttonWidth = $(this).width(),
            buttonHeight = $(this).height();

        $(this).prepend("<span class='ripple'></span>");

        if (buttonWidth >= buttonHeight) {
            buttonHeight = buttonWidth;
        } else {
            buttonWidth = buttonHeight;
        }

        var x = e.pageX - posX - buttonWidth / 2;
        var y = e.pageY - posY - buttonHeight / 2;

        $(".ripple").css({
            width: buttonWidth,
            height: buttonHeight,
            top: y + 'px',
            left: x + 'px'
        }).addClass("rippleEffect");

    });
}

/**
 * 注册块内逻辑
 * @return {[type]} [description]
 */
function logic_signupBox() {
    //注册步骤缩略图切换
    var prevStep = "";
    var img = $(".step img");
    var hoverDone = false; //控制hover与Click顺序
    $(".step span").hover(function() {
        prevStep = img.attr('src').split(".png")[0].split("step")[1];

        img.attr('src', "img/step" + $(this).attr('class') + ".png");
    }, function() {
        if (!hoverDone)
            img.attr('src', "img/step" + prevStep + ".png");
        else
            hoverDone = false;
    });
    $(".step span").click(function(event) {
        prevStep = parseInt(prevStep);
        var crtStep = parseInt($(this).attr('class'));

        img.attr('src', "img/step" + crtStep + ".png");
        hoverDone = true;
        // 触发左右切换
        var changeStep = crtStep - prevStep;
        var changeNumber = Math.abs(changeStep);
        console.log(changeStep + " " + changeNumber);
        if (changeStep > 0) { //右跳
            while (changeNumber-- != 0) {
                $(".switch .right").click();
            }
        } else { //左跳
            while (changeNumber-- != 0) {
                $(".switch .left").click();
            }
        }
    });
    //注册步骤切换
    $(".switch .left,.right").click(function(event) {
        console.log();
        if ($(this).hasClass('left') && $(".signupblock .active").hasClass("signup_0"))
            return;

        if ($(this).hasClass('right') && $(".signupblock .active").hasClass("signup_3"))
            return;
        var crt = $(".signupblock .active");
        var prevAll = crt.prevAll();
        var nextAll = crt.nextAll();
        var active = $(this).hasClass('left') ? crt.prev() : crt.next();
        var changeStr = $(this).hasClass('left') ? "+=100%" : "-=100%";
        var Fun = function(element) {
            element.animate({
                    left: changeStr
                },
                200,
                function() {});
        }
        Fun(prevAll);
        Fun(crt);
        Fun(nextAll);

        active.css('opacity', '1');
        crt.toggleClass('active');
        active.toggleClass('active');
    });
}

/**
 * 登录块内逻辑
 * @return {[type]} [description]
 */
function logic_signinBox() {
    var prefix = AllGlobal.getPrefix();
    $("#btn_signin").off('click').click(function(event) {
        var url = prefix + 'company/login.erd';
        var o = {};
        o.username = $("#input_un").val();
        o.password = $("#input_pw").val();

        ajaxPost(url, o, function(data, textStatus, xhr) {
            var ret = data[0];
            if (ret.status === "ok") {
                var dfd = AllGlobal.setUserInfo(data[1]);
                $.when(dfd).done(function() {
                    AllGlobal.setPageStatus(1);
                });
            } else {
                alert(ret.message);
            }
        });
    });
}

/**
 * 注销
 * @return {[type]} [description]
 */
function logic_logout() {
    var prefix = AllGlobal.getPrefix();
    var url = prefix + 'company/logout.erd';
    ajaxPost(url, null, function(data, textStatus, xhr) {
        if (data[0].status == "ok") {
            AllGlobal.setUserInfo(null);
            AllGlobal.setPageStatus(0);
        }
    });
}
/**
 * 检测用户是否已经登录
 * @return {[type]} [description]
 */
function logic_detectsLogin() {
    var prefix = AllGlobal.getPrefix();
    var url = prefix + 'company/test_cookie.erd';
    ajaxPost(url, null, function(data, textStatus, xhr) {
        var ret = data[0];
        if (ret.status === "ok") {
            var dfd = AllGlobal.setUserInfo(data[1]);
            $.when(dfd).done(function() {
                AllGlobal.setPageStatus(1);
            }); //通过登录检测
        } else {
            AllGlobal.setUserInfo(null);
            AllGlobal.setPageStatus(0); //初始化未登录页面
        }
    });
}
/**
 * 获取附近车辆
 * @return {[type]} [description]
 */
function logic_showCarAround() {
    var ret = [];
    var dfd = $.Deferred();
    ret.push(dfd.promise());
    //获取IP地址周围50公里的车
    var prefix = AllGlobal.getPrefix();
    var url = prefix + 'company/show_car_around.erd';
    ajaxPost(url, null, function(data, textStatus, xhr) {
        if (data[0].status === "ok") {
            for (var i = 1; i < data.length; i++)
                ret.push(data[i]);
        }
        dfd.resolve();
    });
    return ret;
}

//iframe跨域
// function crossDomainPost() {
//     //Add the iframe with a unique name
//     var uniqueString = "loginifm";
//     var iframe = document.createElement("iframe");
//     document.body.appendChild(iframe);
//     iframe.style.display = "none";
//     iframe.contentWindow.name = uniqueString;
//     var flag = 0;

//     iframe.onload = function() {
//         if (flag === 1) {
//             var data = iframe.contentWindow.document;
//             console.log(data);
//         } else if (flag === 0) {
//             flag = 1;
//             var data = iframe.contentWindow.document;
//             console.log(data);
//             iframe.contentWindow.location = "./crossDomain.html";
//         }

//     }


//     //construct a form with hidden inputs, targeting the iframe
//     var form = document.createElement("form");
//     form.target = uniqueString;
//     form.action = "http://219.219.117.164/eroad-1.0/company/login.erd";
//     form.method = "post";

//     var o = {};
//     o.username = $("#input_un").val();
//     o.password = $("#input_pw").val();

//     //repeat for each parameter
//     var input_un = document.createElement("input");
//     input_un.type = "hidden";
//     input_un.name = "json";
//     input_un.value = JSON.stringify(o);
//     form.appendChild(input_un);

//     document.body.appendChild(form);
//     form.submit();
//     form.remove();
// }
