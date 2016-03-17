$(document).ready(function() {
    pageInit();
    pageListener();
});
/**
 * 页面初始化
 * @return {[type]} [description]
 */
function pageInit() {
    navAnimation();
    listViewAnimation();
    $(window).resize(function(event) {
        $("nav ul li.checked").click();
    });
}
/**
 * 页面事件监听
 * @return {[type]} [description]
 */
function pageListener() {
    $("#btn_signup").click(function(event) {
        $("#signup_box").toggleClass('display_none');
    });
    $("#btn_closeSignup").click(function(event) {
        $("#signup_box").toggleClass('display_none');
    });
    //注册步骤指示切换
    var prevStep = "";
    var img = $(".step img");
    var hoverDone = false; //控制hover与Click顺序
    $(".step span").hover(function() {
        prevStep = img.attr('src').split(".png")[0].split("step")[1];
        // if (parseInt(prevStep) + 1 != parseInt($(this).attr('class')))
        //     return;
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
        // //禁止向前越步
        // console.log(prevStep + " " + crtStep+" "+(crtStep - prevStep > 1 || (crtStep - prevStep !=1 &&crtStep - prevStep >=0)));
        // if (crtStep - prevStep > 1 || (crtStep - prevStep !=1 &&crtStep - prevStep >=0))
        //      return;
        //  console.log(prevStep + " " + crtStep);
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
    $("#btn_signin").click(function(event) {});
}
/**
 * 导航效果
 * @return {[type]} [description]
 */
function navAnimation() {
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
 * 列表效果
 * @return {[type]} [description]
 */
function listViewAnimation() {
    // $(".listView ul li.li_car").hover(function() {
    //     /* Stuff to do when the mouse enters the element */
    //     $(this).css('background', 'rgba(0,204,0,.2)');
    // }, function() {
    //     /* Stuff to do when the mouse leaves the element */
    //     $(this).css('background', 'rgb(255,255,255)');
    // });
}
