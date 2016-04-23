var responsiveMenu = (function() {
    return {
        //右上角按钮菜单
        res_nav_right_mobile: function() {
            $("#header_box .nav_right_mobile .toggle").off("click").click(function(event) {
                var operator = '+';
                if (!$(this).hasClass('icon-double-angle-left')) {
                    operator = '-';
                }
                $(this).toggleClass('icon-double-angle-left');
                $(this).toggleClass('icon-double-angle-right');
                $(this).animate({
                        right: operator + '=110px'
                    },
                    500);
                $("#header_box .nav_right_mobile ul").animate({
                        // right: operator + '=100px',
                        width: operator + '=120px',
                        opacity: operator + '=1'
                    },
                    500);
            });
        },
        //侧边栏
        res_left_listview: function() {
            $(".left_toggle").off("click").click(function(event) {
                var operator = '+';
                if (!$(".left_toggle i").hasClass('icon-chevron-up')) {
                    operator = '-';
                }
                $(".left_toggle i").toggleClass('icon-chevron-up');
                $(".left_toggle i").toggleClass('icon-chevron-down');
                $(this).animate({
                        bottom: operator + '=80%'
                    },
                    500);
                $("#content_box .box_left").animate({
                        // right: operator + '=100px',
                        height: operator + '=80%',
                        opacity: operator + '=1'
                    },
                    500);
            });
        }
    }
})();
responsiveMenu.res_nav_right_mobile();
responsiveMenu.res_left_listview();
