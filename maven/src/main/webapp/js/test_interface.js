$(document).ready(function() {
	var prefix = "http://172.20.10.13:8080/eroad-1.0/";
	$("#btn_showCarInfo").off("click").click(function(event) {
		/* Act on the event */
		var o = {}
		o.id="1";
		$.post(prefix+'company/get_car_remark.erd', { json:JSON.stringify(o)}, function(data, textStatus, xhr) {
			console.log(data);
		});
	});
});