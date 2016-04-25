(function() {
    Modernizr.load({
        test: Modernizr.mq('only all'),
        nope: ['js/vendor/respond.min.js']
    });
    // Modernizr.load({
    //     // 如果 max-width 等于 600px，则加载菜单转换脚本
    //     test: Modernizr.mq('only screen and (max-width: 600px)'),
    //     yep: ['js/vendor/jquery.mobilemenu.min.js'],
    //     callback: function() {
    //         // 资源完全加载后运行
    //         $(document).ready(function() {
    //             alert("s");
    //             $('#mainNav').mobileMenu({
    //                 switchWidth: 600, //转换宽度 (用 px 表示)
    //                 topOptionText: 'Select a page', //第一个选项的内容
    //                 indentString: '&nbsp;&nbsp;&nbsp;' //缩进嵌套选项的字符串
    //             });
    //         });
    //     }
    // });
	
	// //响应式菜单
 //    Modernizr.load({
 //        test: Modernizr.mq('only screen and (max-device-width: 320px) and (orientation:portrait)'),
 //        yep: ['js/responsiveMenu.js']
 //    });
    //480px 菜单
    Modernizr.load({
        test: Modernizr.mq('only screen and (max-device-width: 480px) and (orientation:portrait)'),
        yep: ['js/responsiveMenu.js']
    });
})();
