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
 * [pos 生成一个坐标对象]
 * @param  {[type]} longitude [经度]
 * @param  {[type]} latitude  [维度]
 * @return {[type]}           [坐标对象]
 */
function pos(longitude, latitude) {
    var coor = new BMap.Point(longitude, latitude);
    return coor;
}
/**
 * [showPosByCoordinate 根据坐标显示位置]
 * @param  {[type]} map  [地图对象]
 * @param  {[type]} coor [坐标]
 * @return {[type]}      [description]
 */
function showPosByCoordinate(map, coor) {
    // console.log(coor.x+" "+coor.y);
    removeOverlay(map);
    map.enableScrollWheelZoom(true);
    var new_point = new BMap.Point(coor.lng, coor.lat);
    var marker = new BMap.Marker(new_point); // 创建标注
    map.addOverlay(marker); // 将标注添加到地图中
    map.panTo(new_point); //panTo()方法将让地图平滑移动至新中心点，如果移动距离超过了当前地图区域大小，则地图会直跳到该点。
}
/**
 * [showPathByCoordinates 给定一组坐标显示轨迹]
 * @param  {[type]} map   [description]
 * @param  {[type]} coors [一组坐标]
 * @return {[type]}       [description]
 */
function showPathByCoordinates(map, coors) {
    removeOverlay(map);
    var polyline = new BMap.Polyline(coors, { strokeColor: "blue", strokeWeight: 2, strokeOpacity: 0.5 }); //创建折线
    map.addOverlay(polyline); //增加折线
    //根据轨迹调整地图显示
    map.panTo(coors.pop());
}
/**
 * [showPosByIp 根据ip定位]
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
 * [removeOverlay 清楚覆盖物]
 * @param  {[type]} map [description]
 * @return {[type]}     [description]
 */
function removeOverlay(map) {
    map.clearOverlays();
}
