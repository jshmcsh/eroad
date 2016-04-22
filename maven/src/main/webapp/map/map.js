$(document).ready(function() {});

/**
 * 初始化地图
 * @return {[type]} [description]
 */
function initBMap() {
    map = new BMap.Map("container"); // 创建地图实例  
    var point = new BMap.Point(116.404, 39.915); // 创建点坐标  
    var point = new BMap.Point(116.404, 39.915); // 创建点坐标  
    map.centerAndZoom(point, 15); //初始化地图，设置中心点坐标和地图级别
    //添加地图类型控件
    map.addControl(new BMap.MapTypeControl());
    // 添加带有定位的导航控件
    var navigationControl = new BMap.NavigationControl({
        // 靠左上角位置
        anchor: BMAP_ANCHOR_TOP_LEFT,
        // LARGE类型
        type: BMAP_NAVIGATION_CONTROL_LARGE,
        // 启用显示定位
        enableGeolocation: true
    });
    map.addControl(navigationControl);
    // 添加定位控件
    var geolocationControl = new BMap.GeolocationControl();
    geolocationControl.addEventListener("locationSuccess", function(e) {
        // 定位成功事件
        var address = '';
        address += e.addressComponent.province;
        address += e.addressComponent.city;
        address += e.addressComponent.district;
        address += e.addressComponent.street;
        address += e.addressComponent.streetNumber;
        alert("当前定位地址为：" + address);
    });
    geolocationControl.addEventListener("locationError", function(e) {
        // 定位失败事件
        alert(e.message);
    });
    map.addControl(geolocationControl);
    //显示地图实时位置
    map.addEventListener("moveend", function() {
        var currentCenter = map.getCenter();
        $("#currentCoordinate").html("longitude：" + currentCenter.lng + " latitude：" + currentCenter.lat);
    });

    return map;
}
/**
 * 生成一个坐标对象
 * @param  {[type]} longitude [经度]
 * @param  {[type]} latitude  [维度]
 * @return {[type]}           [坐标对象]
 */
function pos(longitude, latitude) {
    var coor = new BMap.Point(longitude, latitude);
    return coor;
}
/**
 * 根据坐标显示位置
 * @param  {[type]} map  [地图对象]
 * @param  {[type]} coor [坐标]
 * @return {[type]}      [description]
 */
function showPosByCoordinate(map, coor, type) {
    // console.log(coor.x+" "+coor.y);
    // removeOverlay(map);
    var internType = (type != undefined && type != null) ? type : 0;
    map.enableScrollWheelZoom(true);
    var new_point = new BMap.Point(coor.lng, coor.lat);
    var marker = customMarker(new_point, internType); //获取自定义marker
    map.addOverlay(marker); // 将标注添加到地图中
    if (internType === 3) {
        marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
    }
    // map.panTo(new_point); //panTo()方法将让地图平滑移动至新中心点，如果移动距离超过了当前地图区域大小，则地图会直跳到该点。
}
/**
 * 自定义marker
 * @param  {[type]} point [description]
 * @return {[type]}       [description]
 */
function customMarker(point, type) {
    var internType = (type != undefined && type != null) ? type : 0;
    var iconPath = null;
    if (type === 0) {
        var marker = new BMap.Marker(point);
        return marker; //默认图标
    }

    switch (type) {
        case 1:
            iconPath = "../img/icon_redcar.png";
            break; //车图标
        case 2:
            iconPath = "../img/icon_st.png";
            break; //起点;
        case 3:
            iconPath = "../img/icon_en.png";
            break; //终点
        case 4:
            iconPath = "../img/icon36_point_hover.png";
            break; //途经点;
        default:
            break;
    }
    var myIcon = new BMap.Icon(iconPath, new BMap.Size(50, 50));
    var marker = new BMap.Marker(point, { icon: myIcon }); // 创建标注
    return marker;
}
/**
 * 给定一组坐标显示折线
 * @param  {[type]} map   [description]
 * @param  {[type]} coors [一组坐标]
 * @return {[type]}       [description]
 */
function showPathByCoordinates(map, coors) {
    removeOverlay(map);
    var polyline = new BMap.Polyline(coors, { strokeColor: "blue", strokeWeight: 2, strokeOpacity: 0.5 }); //创建折线
    map.addOverlay(polyline); //增加折线
    //根据轨迹调整地图显示
    map.setViewport(coors); //调整到最佳视野
}
/**
 * 根据坐标点组显示一组车
 * @param  {[type]} map   [description]
 * @param  {[type]} coors [description]
 * @return {[type]}       [description]
 */
function showCarsByCoordinates(map, coors) {
    removeOverlay(map);
    for (var i = 0; i < coors.length; i++) {
        showPosByCoordinate(map, coors[i], 1);
    }
    map.setViewport(coors); //调整到最佳视野
}
/**
 * 绘制一条轨迹
 * @param  {[type]} map   [description]
 * @param  {[type]} coors [description]
 * @return {[type]}       [description]
 */
function showTrack(map, coors) {
    removeOverlay(map);
    map.enableScrollWheelZoom();
    var driving = new BMap.DrivingRoute(map); //创建驾车实例


    for (var i = 1; i < coors.length; i++) {
        driving.search(coors[i - 1], coors[i]); //每一个驾车搜索
    }
    var times = 0;
    driving.setSearchCompleteCallback(function() {
        if (++times < coors.length-1)//只有最后一次搜索才开始绘制整条路线
            return;
        var pts = driving.getResults().getPlan(0).getRoute(0).getPath(); //通过驾车实例，获得一系列点的数组
        var polyline = new BMap.Polyline(pts);
        polyline.setStrokeColor("#FF0033");
        polyline.setStrokeOpacity(0);
        map.addOverlay(polyline);
        //绘制起点终点
        showPosByCoordinate(map, driving.getResults().getStart().point, 2);
        showPosByCoordinate(map, driving.getResults().getEnd().point, 3);
        setTimeout(function() {
            map.setViewport(coors); //调整到最佳视野
        }, 1000);
    });

}
/**
 * 根据ip定位
 * @param  {[type]} map [description]
 * @return {[type]}     [description]
 */
function showPosByIp(map) {
    var myCity = new BMap.LocalCity();
    myCity.get(function(result) {
        var cityName = result.name;
        map.setCenter(cityName);
        console.log("当前定位城市:" + cityName);
    });
}
/**
 * 清除覆盖物
 * @param  {[type]} map [description]
 * @return {[type]}     [description]
 */
function removeOverlay(map) {
    map.clearOverlays();
}
