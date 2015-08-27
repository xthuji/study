<#macro form action id='' class='' method='post' name='' id='' refreshId='list-page' stopRefresh=false >
<form action='${action}' class='${class}' method='${method}' name='${name}' id='${id}' refreshId="${refreshId}">
<#if stopRefresh>
<input type="hidden" value="${tookenBean.genTooken(action)}" name="_form_uniq_id" />
</#if>
<#nested>
 </form>
</#macro>

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