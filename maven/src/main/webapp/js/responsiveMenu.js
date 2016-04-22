(function() {
    $("#header_box .nav_right_mobile .toggle").click(function(event) {
        var operator = '+';
        if (!$(this).hasClass('icon-double-angle-left')) {
            operator = '-';
        }
        $(this).toggleClass('icon-double-angle-left');
        $(this).toggleClass('icon-double-angle-right');
        $(this).animate({
                right: operator + '=100px'
            },
            500);
        $("#header_box .nav_right_mobile .sign").animate({
                // right: operator + '=100px',
                width: operator + '=100px',
                opacity: operator + '=1'
            },
            500);

    });
})();
