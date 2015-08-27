//swf模板路径
var lineTemplatePath="./swf/FCF_MSLine.swf";
var pieTemplatePath="./swf/FCF_Pie3D.swf";
var PillarTemplatePath="./swf/FCF_MSColumn3D.swf";
//颜色
var color = new Array("AFD8F8", "F6BD0F", "8BBA00", "FF8E46", "008E8E", "D64646", 
		"8E468E", "588526", "B3AA00", "008ED6", "9D080D", "A186BE");
//报表名称
var reportName = "";
//X轴含义
var xAxisName = "";
//Y轴含义 
var yAxisName = "";
//X轴各列名字
var XPartName = new Array();
//数据
var datas = new Array();
//单位
var unit = "";

/**
 * 构建折线图xml
 * @returns {String}
 */
function generateLineXML(){         
    var strXML="";
    strXML = "<graph caption='"+reportName+"' xAxisName='"+xAxisName+"' yAxisName='"+yAxisName+"' numVDivLines='"+(XPartName.length-2)+"' numberSuffix='"+unit+"' rotateNames='0' decimalPrecision='0' formatNumberScale='0' showNames='1' showValues='0'  showAlternateHGridColor='1' AlternateHGridColor='ff5904' divLineColor='ff5904' alternateHGridAlpha='5' >";
    //列名
    var categoryXML = "<categories>";
    for (var i=0; i<XPartName.length; i++){
    	categoryXML = categoryXML + "<category Name='" + XPartName[i] + "' />";
	}
    categoryXML = categoryXML + "</categories>";
    //数据
    var datasetXML = "";
    for (var index=0; index<datas.length; index++){
    	var dataset = datas[index];
    	//dataset 中第一个元素：一条数据的名称描述；第二元素：跳转链接；其余元素：图表中的数值数据
    	datasetXML = datasetXML + "<dataset seriesName='"+dataset[0]+"' color='"+color[index]+"' anchorBorderColor='"+color[index]+"' anchorRadius='4'> ";
    	var setXML = "";
    	for (var i=2; i<dataset.length; i++){
    		setXML = setXML + "<set value='" + dataset[i] + "' link='" + dataset[1] + "'/>";
		}
    	datasetXML = datasetXML + setXML + "</dataset>";
	}
    strXML = strXML + categoryXML + datasetXML + "</graph>";
    //alert(strXML);
    return strXML;
}

/**
 * 构建饼图xml
 * @returns {String}
 */
function generatePieXML(){			
	var strXML="";
	strXML = strXML + "<graph showNames='1'  decimalPrecision='0' numberSuffix='"+unit+"'>";
	var setXML="";
	for (var i=0; i<XPartName.length; i++){
		setXML = setXML + "<set name='"+XPartName[i]+"' value='"+datas[i]+"' color='"+color[i]+"' />";
	}
	strXML = strXML + setXML + "</graph>";
	return strXML;
}

/**
 * 构建柱状图xml
 * @returns {String}
 */
function generatePillarXML(){			
	var strXML="";
	strXML = "<graph caption='"+reportName+"' xAxisName='"+xAxisName+"' yAxisName='"+yAxisName+"' numberSuffix='"+unit+"' decimalPrecision='0' animation='1' showValues='0' rotateValues='1' >";
	//列名
    var categoryXML = "<categories>";
	for (var i=0; i<XPartName.length; i++){
		categoryXML = categoryXML + "<category Name='" + XPartName[i] + "' />";
	}
	categoryXML = categoryXML + "</categories>";
	//数据
	var datasetXML = "";
    for (var index=0; index<datas.length; index++){
    	var dataset = datas[index];
    	//dataset 中第一个元素：一条数据的名称描述；第二元素：跳转链接；其余元素：图表中的数值数据
    	datasetXML = datasetXML + "<dataset seriesName='"+dataset[0]+"' color='"+color[index]+"' >";
    	var setXML = "";
    	for (var i=2; i<dataset.length; i++){
    		setXML = setXML + "<set value='" + dataset[i] + "' link='" + dataset[1] + "'/>";
		}
    	datasetXML = datasetXML + setXML + "</dataset>";
	}
	strXML = strXML + categoryXML + datasetXML + "</graph>";
	return strXML;
}
/**
 * 渲染图表
 * @param templatePath 模板路径
 */
function report (templatePath){
    var chart1 = new FusionCharts(templatePath, "chart1Id", "800", "600");         
    var strXML= "";
    if(templatePath==lineTemplatePath){//折线图
    	strXML = generateLineXML();
    }else if(templatePath==pieTemplatePath){//饼图
    	strXML = generatePieXML();
    }else if(templatePath==PillarTemplatePath){//柱状图
    	strXML = generatePillarXML();
    }
    chart1.setDataXML(strXML);
    chart1.render("chart1div");
    
}

/**
 * 解析单条数据的json
 * @param msg
 */
function parseSingleJSON(msg){
	datas = new Array(); 
	$(msg).each(function (index, value) {
	    var vdate = eval(msg[index]);
//	    XPartName.push(vdate.name);  
//	    datas.push(vdate.value);  
	    XPartName.push(vdate.YearMonth);  
	    datas.push(vdate.Consume);  
    });   
}
/**
 * 解析多条数据的json
 * @param msg
 */
function parseMultitermJSON(msg){
	datas = new Array(); 
    XPartName = new Array(); 

    $(msg).each(function (index, value) {
        var dataValue = new Array(); 
        var tick = new Array(); //每列的名字
        var vdate = eval(msg[index]);
        //dataValue 中第一个元素：一条数据的名称描述；第二元素：跳转链接；其余元素：图表中的数值数据
    	dataValue.push("类别"+index);  
    	dataValue.push("www.baidu.com");  
        $(vdate).each(function (index, value) { 
            var value = new Array(); 
//            tick.push(vdate[index].name);
//            value.push(vdate[index].value);  
            value.push(vdate[index].Consume);  
            tick.push(vdate[index].YearMonth);
            dataValue.push(value);  
        });
        XPartName = tick;  
        datas.push(dataValue);  
    });   
}
/**
 * 折线图
 * @param msg
 */
function toLine(msg){
	reportName = "折线图";
	xAxisName = "x轴";
	yAxisName = "y轴";
	unit = "Gb";
	parseMultitermJSON(msg);
	report(lineTemplatePath);  
}
/**
 * 饼图
 * @param msg
 */
function toPie(msg){
	reportName = "折线图";
	unit = "Gb";
	parseSingleJSON(msg);
	report(pieTemplatePath);  
}
/**
 * 柱状图
 * @param msg
 */
function toPillar(msg){
	reportName = "折线图";
	xAxisName = "x轴";
	yAxisName = "y轴";
	unit = "Gb";
	parseMultitermJSON(msg);
    report(PillarTemplatePath);  
}