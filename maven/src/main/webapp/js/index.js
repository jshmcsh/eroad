$(document).ready(function() {
    pageInit();
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
function listViewAnimation(){
    // $(".listView ul li.li_car").hover(function() {
    //     /* Stuff to do when the mouse enters the element */
    //     $(this).css('background', 'rgba(0,204,0,.2)');
    // }, function() {
    //     /* Stuff to do when the mouse leaves the element */
    //     $(this).css('background', 'rgb(255,255,255)');
    // });
}
