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
    $.ajax({
        url: url,
        type: 'post',
        dataType: 'jsonp',
        crossDomain: true,
        jsonpCallback: 'loginCallback',
        data: { json: params },
        success: function(data, textStatus, xhr) {
            console.log("success");
            console.log(data + " " + textStatus + " " + xhr);
        },
        error: function(xhr, textStatus, errorThrown) {
            console.log("error");
            console.log(xhr + " " + textStatus + " " + errorThrown);
        },
        complete:function(){
        	console.log("complete");
        }
    });
    // .done(function(data, textStatus, xhr) {
    // 	console.log("success");
    // })
    // .fail(function(data, textStatus, xhr) {
    //     console.log("error");
    //     console.log(data+" "+textStatus+" "+xhr);
    // })
    // .always(function(data, textStatus, xhr) {
    //     console.log("complete");
    //     console.log(data.status+" "+textStatus+" "+xhr);
    // });
}
$(document).ready(function() {});
