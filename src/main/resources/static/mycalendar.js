
Date.prototype.Format = function(fmt)
{
    var o = {
        "M+" : this.getMonth()+1,                 //月份
        "d+" : this.getDate(),                    //日
        "h+" : this.getHours(),                   //小时
        "m+" : this.getMinutes(),                 //分
        "s+" : this.getSeconds(),                 //秒
        "q+" : Math.floor((this.getMonth()+3)/3), //季度
        "S"  : this.getMilliseconds()             //毫秒
    };
    if(/(y+)/.test(fmt))
        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
    for(var k in o)
        if(new RegExp("("+ k +")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
    return fmt;
}

function initWeek(year,id){
    var currentYear = year;
    var thisyearbeginday = new Date(currentYear,0,1);
    var thisyearendday  = new Date(currentYear,11,31);
    var thisWeekBegin = new Date();
    var thisWeekEnd = new Date();
    if(thisyearbeginday.getDay() == 0){

        thisyearbeginday.setDate(thisyearbeginday.getDate()+1);

    }else if(thisyearbeginday.getDay() != 1){

        thisyearbeginday.setDate(thisyearbeginday.getDate() + 8 - thisyearbeginday.getDay());
    }

    thisWeekBegin = thisyearbeginday;

    if(thisyearendday.getDay() != 0){

        thisyearendday.setDate(thisyearendday.getDate() + 7 - thisyearendday.getDay());
    }
    thisWeekEnd = thisyearendday;
    //alert(thisWeekBegin.getDate()+"年头周");
    //alert(thisWeekEnd.getDate()+"年尾周");
    var week1Obj = $("#"+id);
    var weekHtml = "";
    var myweek = 1;
    var nowweekValue = 0;
    //循环迭代出所需的周数
    var json = [];
    while(thisWeekBegin.getTime() <= thisWeekEnd.getTime()){
        var beginTimes = thisWeekBegin.getTime();
        var begin = thisWeekBegin.Format("yyyy-MM-dd");
        thisWeekBegin.setDate(thisWeekBegin.getDate()+6);
        var end = thisWeekBegin.Format("yyyy-MM-dd");
        var todayTimes = new Date().getTime();

        thisWeekBegin.setDate(thisWeekBegin.getDate()+1);
        var endTimes = thisWeekBegin.getTime();
        if(todayTimes>=beginTimes&&todayTimes<=endTimes){
            nowweekValue = myweek;
        }
        json.push({"id":myweek,"text":myweek+"("+begin+"~"+end+")"});
        myweek++;
    }
    $("#"+id).combobox({
        valueField: 'id',
        textField: 'text',
        value:"",
        height: 30,
        width:190,
        panelHeight: json.length >= 7 ? "135px" : "auto"}).combobox("loadData", json);
    if(nowweekValue >= 2){
        $("#"+id).combobox("setValue",nowweekValue-1);
    }
}