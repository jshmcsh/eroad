/**
 * 页面回退
 * @return {[type]} [description]
 */
function goback() {
    window.history.go(-1);

}
/**
 * [ajaxPost json方式发起一个Post类型的ajax请求]
 * @param  {[type]} url         [请求地址]
 * @param  {[type]} params      [json->{param1: 'value1'}]
 * @param  {[type]} callback    [回调函数]
 * @return {[type]}             [description]
 */
function ajaxPost(url, params, callback) {
    $.ajax({
            url: url,
            type: 'post',
            dataType: 'json',
            data: { json: JSON.stringify(params) },
        })
        .done(function(data, textStatus, xhr) {
            // console.log(data);
            callback(data, textStatus, xhr);
        })
        .fail(function(xhr, textStatus, errorThrown) {
            console.log("error");
            console.log(xhr + " " + textStatus + " " + errorThrown);
        })
        .always(function() {
            console.log("complete");
        });
}
/**
 * 设置cookie
 * @param {[type]} name  名字
 * @param {[type]} value 值
 * @param {[type]} days  存在天数
 */
function setCookie(name, value, days) {
    var exp = new Date();
    exp.setTime(exp.getTime() + days * 24 * 60 * 60 * 1000);
    document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
}
/**
 * 读取cookie
 * @param  {[type]} name [description]
 * @return {[type]}      [description]
 */
function getCookie(name) {
    var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
    if (arr = document.cookie.match(reg))
        return unescape(arr[2]);
    else
        return null;
}
/**
 * 
 * @param  {[type]} name [description]
 * @return {[type]}      [description]
 */
function delCookie(name) {

}

$(document).ready(function() {
    setCookie("noneExpires", "noneExpires");
    var cookie = getCookie("noneExpires");
    console.log(cookie);
});
