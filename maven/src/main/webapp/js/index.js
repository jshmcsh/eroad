//页面全局对象
AllGlobal = (function global() {
    //0未登录，1登录
    var pageStatus = 0;
    //绑定一个html元素，通过change事件的触发检测页面状态
    var pageCheckHelper = null;
    //dom映射数组，偶数值为容器，偶数的右相邻奇数值为dom内容
    var unloginArray = null;
    return {
        setPageStatus: function(newStatus) {
            pageStatus = newStatus;
            pageCheckHelper.change();
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
        setUnloginArray: function(newArray) {
            //设置dom保存到数组
            unloginArray = newArray;
        },
        getUnloginArray: function() {
            return unloginArray;
        },
        loadUnloginArray: function() {
            if (unloginArray === null)
                return;
            //从数组还原到dom
            for (var i = 0; i < unloginArray.length; i += 2) {
                unloginArray[i].html(unloginArray[i + 1]);
            }
            unloginArray = null;
        }
    };
})();
$(document).ready(function() {
    //页面初始化
    pageInit();
});
/**
 * 页面初始化
 * @return {[type]} [description]
 */
function pageInit() {
    //固定dom初始化
    effect_nav();
    $(window).resize(function(event) {
        $("nav ul li.checked").click();
    });

    //实时监测页面状态
    var pageCheckHelper = $("#hidden_check");
    pageCheckHelper.change(function(event) {
        //登录页面初始化
        if (AllGlobal.getPageStatus() === 1) {
            /*保存原内容*/
            $("#btn_closeSignin").click();
            var domArray = new Array();
            var content_unlogin = $("#header_box .nav_right").children();
            domArray.push($("#header_box .nav_right"));
            domArray.push(content_unlogin);
            var content_map = $("#content_box").children();
            domArray.push($("#content_box"));
            domArray.push(content_map);
            //保存未登录时的dom
            AllGlobal.setUnloginArray(domArray);
            /*清除原内容*/
            content_unlogin.remove();
            content_map.remove();
            /*更新当前内容*/
            var domStr = '';
            domStr += '<div class="logined_top">';
            domStr += '    <span>UR_d0623e8e（货代公司bbd64c67），欢迎您！</span>';
            domStr += '    <a id="btn_notification" href="javascript:void(0);">通知(<span class="count">0</span>)</a>';
            domStr += '    <a id="btn_logout" href="javascript:void(0);">退出</a>';
            domStr += '</div>';
            domStr += '<div class="logined_bottom">';
            domStr += '    <div id="btn_kzt" class="ctrl_kzt"><img src="img/kzt_01.png" height="83" width="235" alt=""></div>';
            domStr += '    <div id="btn_hy" class="ctrl_hy"><img src="img/hy_01.png" height="82" width="209" alt=""></div>';
            domStr += '    <div id="btn_yd" class="ctrl_yd"><img src="img/yd_01.png" height="83" width="209" alt=""></div>';
            domStr += '    <div id="btn_gs" class="ctrl_gs"><img src="img/gs_01.png" height="82" width="209" alt=""></div>';
            domStr += '    <div id="btn_sj" class="ctrl_sj"><img src="img/si_01.png" height="82" width="209" alt=""></div>';
            domStr += '</div>';
            $("#header_box .nav_right").html(domStr);
            /*绑定新内容监听*/
            loginedPageTogglesInit();
            //插入新的内容框
            domStr = '';
            domStr += '<div id="loginedContent" class="col_12">';
            domStr += '<iframe src="./l_kzt.html"></iframe>';
            domStr += '</div>';
            $("#content_box").html(domStr);
        } else { //未登录页面初始化
            /*还原原有内容*/
            AllGlobal.loadUnloginArray();
            //未登录页面监听
            pageTogglesInit();
        }
    });
    //绑定页面状态监测对象到全局对象
    AllGlobal.setPageCheckHelper(pageCheckHelper);
    AllGlobal.setPageStatus(0); //初始化未登录页面
}
/**
 * 未登录页面监听
 * @return {[type]} [description]
 */
function pageTogglesInit() {
    //注册
    $("#btn_signupbox").off('click').click(function(event) {
        $("#signup_box").toggleClass('display_none');
    });
    //注册块内效果
    effect_signupBox();
    // 关闭注册
    $("#btn_closeSignup").off('click').click(function(event) {
        $("#signup_box").toggleClass('display_none');
    });
    // 登录
    $("#btn_signinbox").off('click').click(function(event) {
        $("#signin_box").toggleClass('display_none');
    });
    //登录块内效果
    effect_signinBox();
    // 登录逻辑
    logic_signinBox();
    // 关闭登录块
    $("#btn_closeSignin").off('click').on('click', function(event) {
        event.preventDefault();
        $("#signin_box").toggleClass('display_none');
    });
}
/**
 * 登录页面监听
 * @return {[type]} [description]
 */
function loginedPageTogglesInit() {
    // 注销按钮
    $("#btn_logout").click(function(event) {
        AllGlobal.setPageStatus(0);
    });
    // 通知按钮
    $("#btn_notification").click(function(event) {
        $('#loginedContent iframe').attr('src', './l_notification.html');
    });
    // 控制台按钮
    $("#btn_kzt").click(function(event) {
        $('#loginedContent iframe').attr('src', './l_kzt.html');

    });
    // 货源按钮
    $("#btn_hy").click(function(event) {
        $('#loginedContent iframe').attr('src', './l_hy.html');

    });
    // 运单按钮
    $("#btn_yd").click(function(event) {
        $('#loginedContent iframe').attr('src', './l_yd.html');

    });
    // 公司按钮
    $("#btn_gs").click(function(event) {
        $('#loginedContent iframe').attr('src', './l_gs.html');

    });
    // 司机按钮
    $("#btn_sj").click(function(event) {
        $('#loginedContent iframe').attr('src', './l_sj.html');

    });
}
/**
 * 导航效果
 * @return {[type]} [description]
 */
function effect_nav() {

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
 * 注册块内效果
 * @return {[type]} [description]
 */
function effect_signupBox() {
    //注册步骤指示切换
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
 * 注册块内逻辑
 * @return {[type]} [description]
 */
function logic_signupBox() {

}
/**
 * 登录块内效果
 * @return {[type]} [description]
 */
function effect_signinBox() {

}
/**
 * 登录块内逻辑
 * @return {[type]} [description]
 */
function logic_signinBox() {
    $("#btn_signin").off('click').click(function(event) {
        AllGlobal.setPageStatus(1);
    });
}