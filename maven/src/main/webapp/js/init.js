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
            data: params === null ? {} : { json: JSON.stringify(params) },
            crossDomain: true,
            beforeSend: function(xhr) {
                xhr.withCredentials = true;
            }
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
    document.cookie = name + "=" + escape(value) + ";expires=" + exp.toGMTString();
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
 * 删除cookie并重新演示
 * @param  {[type]} name [description]
 * @return {[type]}      [description]
 */
function delCookie(name) {
    // 该函数检查下cookie是否设置，如果设置了则将过期时间调到过去的时间;
    // 剩下就交给操作系统适当时间清理cookie啦
    if (getCookie(name)) {
        document.cookie = name + "=" + "; expires=Thu, 01-Jan-70 00:00:01 GMT";
    }
}

$(document).ready(function() {
    // console.log(getCookie("JSESSIONID"));
});
