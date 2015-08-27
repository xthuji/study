<#--
*********************************************
*业务说明： 分页宏定义
*创建者：  齐百超
*创建日期：2013.9.10
*更新者：  
*更新日期：
*更新说明：
*********************************************
-->
<#macro pagination value uri=uri refreshId='list-page' >
<#if value?exists>
共有${value.totalCount}记录,${value.totalPages}页
<a href="${uri}?${value.fullUrl}&pageNo=1&pageRows=${value.pageRows}" class="ajax" refreshId='${refreshId}' >首页</a>
<#if value.isHasPre()>
<a href="${uri}?${value.fullUrl}&pageNo=${value.pageNo-1}&pageRows=${value.pageRows}" class="ajax" refreshId='${refreshId}' >上一页</a>
<#else>
<a href="javascript:;">上一页</a>
</#if>
<#if value.isHasNext()>
<a href="${uri}?${value.fullUrl}&pageNo=${value.pageNo+1}&pageRows=${value.pageRows}"  class="ajax" refreshId='${refreshId}'  >下一页</a><#else>
<a href="javascript:;">下一页</a>
</#if>
<a href="${uri}?${value.fullUrl}&pageNo=${value.totalPages}&pageRows=${value.pageRows}" class="ajax" refreshId='${refreshId}' >末页</a>
 当前是：<input type="text"  onchange="changePageNo(this)" value="${value.pageNo}"/>页
每页
	<select name="pageRows" id="pageRows" onChange="changePageRows(this);">
		<option value="10" <#if '${(value.pageRows)!?string}' == '10' > selected=selected </#if>>10</option>
		<option value="20" <#if '${(value.pageRows)!?string}' == '20' > selected=selected </#if>>20</option>
		<option value="30" <#if '${(value.pageRows)!?string}' == '30' > selected=selected </#if>>50</option>
	</select>
条记录	
<a href="${uri}?${value.fullUrl}" class="ajax refresh" refreshId='${refreshId}' ></a>
<#else>
Controller 中未定义page对象
</#if>
</#macro>

<#--
*********************************************
*业务说明： 弹出框
*创建者：  齐百超
*创建日期：2013.9.10
*更新者：  
*更新日期：
*更新说明：
*********************************************
-->
<#macro linkpage value=page uri=uri param='' class="colorbox" refreshId='list-page' >
<#if value?exists>
 <a href="${uri}?${value.fullUrl}&pageNo=${value.pageNo}&param=${param}"  class="${class}" refreshId='${refreshId}'  ><#nested></a>
</#if>
</#macro>

<#--
*********************************************
*业务说明： 排序
*创建者：  齐百超
*创建日期：2013.9.10
*更新者：  
*更新日期：
*更新说明：
*********************************************
-->
<#macro pagesort field value=pager  sort='desc' uri=uri refreshId='list-page' >
<#if value?exists>
 <a href="${uri}?${value.url}&pageNo=${value.pageNo}&pageRows=${value.pageRows}&o.field=${field}&o.sort=${value.o.togger}"  class="ajax" refreshId='${refreshId}'  ><#nested>
  <#if field==value.o.field!><img src="../res/images/${value.o.sort}.gif" /></#if>
 </a>
</#if>
</#macro>


<#--
*********************************************
*业务说明： 表单宏
*创建者：  齐百超
*创建日期：2013.9.10
*更新者：  
*更新日期：
*更新说明：
*********************************************
-->
<#macro form action id='' class='' method='post' name='' id='' refreshId='list-page' stopRefresh=false >
<form action='${action}' class='${class}' method='${method}' name='${name}' id='${id}' refreshId="${refreshId}">
<#if stopRefresh>
<input type="hidden" value="${tookenBean.genTooken(action)}" name="_form_uniq_id" />
</#if>
<#nested>
 </form>
</#macro>
<#--
*********************************************
*业务说明： 下拉框
*创建者：  齐百超
*创建日期：2013.9.10
*更新者：  
*更新日期：
*更新说明：
*********************************************
-->
<#macro select source name='' id='' class='' style='' value='' emptyStr='' emptyVal='' labelField='' valueField=''>
<select name="${name}" id="${id}" class="${class}" style="${style}" >
<#nested>
<#if emptyStr!=''><option value='${emptyVal}' >${emptyStr}</option></#if>
<#list source as rowsource>
<#local label=("rowsource."+labelField)?eval val=("rowsource."+valueField)?eval>
<#local  optionselected=val?string==value?string >
<option value="${val}"  <#if optionselected>selected</#if>  >${label}</option>
</#list>
</select>
</#macro >
	
  <script type="text/javascript">
  //页码跳转&&改变页面显示记录
  	function changePageNo(pageNo){
  	
	  	var uri = $(".refresh").attr("href");
	  	var pageNo = pageNo.value;
	  	var pageRows = "${pager.pageRows}";
	  	var href = uri+"&pageNo="+pageNo+"&pageRows="+pageRows;
	  	 $(".refresh").attr("href",href);
	  	$('#list-page').load($(".refresh").attr("href"));
  	}
  	
  	 //页码跳转&&改变页面显示记录
  	function changePageRows(pageRows){
  	
	  	var uri = $(".refresh").attr("href");
	  	var pageNo = "${pager.pageNo}";
	  	var pageRows = pageRows.value;
	  	var href = uri+"&pageNo="+pageNo+"&pageRows="+pageRows;
	  	 $(".refresh").attr("href",href);
	  	 $('#list-page').load($(".refresh").attr("href"));
  	}
  </script>   
