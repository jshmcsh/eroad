/**
 * 页面回退
 * @return {[type]} [description]
 */
function goback() {
    window.history.go(-1);

}
/**
 * [ajaxPost json方式发起一个Post类型的ajax请求]
 * @param  {[type]} url    		[请求地址]
 * @param  {[type]} params 		[json->{param1: 'value1'}]
 * @param  {[type]} callback    [回调函数]
 * @return {[type]}        		[description]
 */
function ajaxPost(url, params, callback) {
    $.post(url, params, function(data, textStatus, xhr) {
        callback(data, textStatus, xhr);
    }, "json");
}
$(document).ready(function() {

});
