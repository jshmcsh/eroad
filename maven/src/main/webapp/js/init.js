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
            xhrFields: { withCredentials: true },
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
            // console.log("complete");
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

});

var Prompt = (function() {
    // //////////////////////客户端调用方式///////////
    // //确认框
    // var dfd = Prompt.confirm("sss");
    // $.when(dfd).done(function() {
    //     console.log(Prompt.getRet());
    // });
    // //提示框
    // Prompt.alert("sss");
    // ///////////////////////////////////////////////
    var ret = false;
    var getToggle = function(promptType) {
        if (promptType === "confirm" || promptType === "alert") {

        }
        return {
            show: function(eleArr) {
                for (index in eleArr) {
                    $(eleArr[index]).removeClass('display_none');
                }
            },
            hidden: function(eleArr) {
                for (index in eleArr) {
                    $(eleArr[index]).addClass('display_none');
                }
            }
        }
    }
    return {
        init: function() {
            domStr = "";
            domStr += '<div id="container_prompt" class="display_none" style="position: fixed; top: 0; left: 0; z-index: 1000; width: 100%; height: 100%; background: rgba(0, 0, 0, .5);">';
            domStr += '    <div class="height_35"></div>';
            domStr += '    <div style="position:relative;width:40%;max-height:50%; margin:0 auto;background:#fff; border-radius:15px; border-top:15px solid #eee; box-shadow:5px 5px 5px #333,-5px 5px 5px #333">';
            domStr += '        <i class="icon-remove-sign" style="position:absolute;right:.5rem;top:-1rem; font-size:1rem;"></i>';
            domStr += '        <div style="width:100%;height:100%;line-height:100%;text-align:center;">';
            domStr += '            <br><br>';
            domStr += '            <div id="top-prompt" style="font-size:2rem;">提示信息</div>';
            domStr += '            <br><br><br><br>';
            domStr += '        </div>';
            domStr += '        <div id="container_confirm" class="container_confirm display_none" style="position:absolute;bottom:0;width:100%;height:2rem;text-align:center;">';
            domStr += '            <span style="width:70%;height:100%;">';
            domStr += '              <div style="width:50%;height:100%;float:left;">';
            domStr += '                  <button id="btn-sure-confirm" style="width:100%; background:#fff;color:#000;">确定</button>';
            domStr += '              </div>';
            domStr += '              <div style="width:50%;height:100%;float:right;">';
            domStr += '                  <button id="btn-cancel-confirm" style="width:100%;background:#fff;color:#000;">取消</button>';
            domStr += '              </div>';
            domStr += '            </span>';
            domStr += '        </div>';
            domStr += '        <div id="container_alert" class="container_confirm display_none" style="position:absolute;bottom:0;width:100%;height:2rem;text-align:center;">';
            domStr += '              <div style="width:100%;height:2rem;">';
            domStr += '                  <button id="btn-sure-alert" style="width:100%; background:#fff;color:#000;">确定</button>';
            domStr += '              </div>';
            domStr += '        </div>';
            domStr += '    </div>';
            domStr += '</div>';
            $("body").append(domStr);
        },
        confirm: function(string) {
            //显示确认框
            $("#top-prompt").html(string);

            var eleArr = ["#container_prompt", "#container_confirm"];
            var toggle = getToggle("confrim");
            toggle.show(eleArr);
            var dfd = $.Deferred();
            $("#btn-sure-confirm").off('click').click(function() {
                ret = true;
                toggle.hidden(eleArr);
                dfd.resolve();

            });
            $("#btn-cancel-confirm").off('click').click(function(event) {
                ret = false;
                toggle.hidden(eleArr);
                dfd.resolve();

            });
            return dfd.promise();
        },
        alert: function(string) {
            $("#top-prompt").html(string);

            var eleArr = ["#container_prompt", "#container_alert"];
            var toggle = getToggle("alert");
            toggle.show(eleArr);
            $("#btn-sure-alert").click(function(event) {
                toggle.hidden(eleArr);
            });
        },
        getRet: function() {
            return ret;
        }
    }
})();
