
<@p.form action="userQuery.aj"  refreshId="list-page"  class="ajaxfrm"  method="post" id ="queryForm" stopRefresh=false >
<div class="right_top">
<table width="768" border="0" align="center" cellpadding="0" cellspacing="0" class="inquiry">
<tbody>
	<tr>
		<td height="43">
        	<table border="0" cellspacing="0" cellpadding="0">
				<tbody>
                	<tr>
						userName:<input name="f[userName]" type="text" value="${pager.f.userName!}"/>
						email:<input name="f[email]" type="text" value="${pager.f.email!}"/>
						phone:<input name="f[phone]" type="text" value="${pager.f.phone!}"/>
						address:<input name="f[address]" type="text" value="${pager.f.address!}"/>
						age:<input name="f[age]" type="text" value="${pager.f.age!}"/>
                        <input type="button" value="查询" onclick="doSubmit();">
                        <input type="button" value="批量删除" onClick="del();">
                         <@p.linkpage value=pager uri='showUserAdd.aj'>添加用户</@p.linkpage>
					</tr>
				</tbody>
			</table>
        </td>
    </tr>
</tbody>
</table>
</div>
<br />
<div class="right_botoom" id="list-page">
    <table width="768" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td>
        	<input name="checkAll" id="checkAll"  type="checkbox" title="全选" onclick="$.checkByName('ids',this.checked);">
        </td>
        <td><@p.pagesort field='user_id' value=pager uri='userQuery.aj'>用户id</@p.pagesort></td>
        <td>用户名称</td>
        <td>邮箱</td>
        <td>手机号 </td>
        <td>地址</td>
        <td><@p.pagesort field='age' value=pager uri='userQuery.aj'>年龄</@p.pagesort></td>
      </tr>
      <#if pager.resultList??>
		<#list pager.resultList as userPo>
			<tr>
			<td>
				<input name="ids" id="ids"  type="checkbox" value="${userPo.userId}">
			</td>
			<td>${userPo.userId}</td>
			<td>${userPo.userName}</td>
			<td>${userPo.email}</td>
			<td>${userPo.phone}</td>
			<td>${userPo.address}</td>
			<td>${userPo.age}</td>
			<td>
			 <@p.linkpage value=pager uri='showUserModify.aj' param="${userPo.userId}">修改</@p.linkpage>
             <@p.linkpage value=pager uri='userLook.aj'   param='${userPo.userId!}' >查看</@p.linkpage>
            </td>
			</tr>
		</#list>
	</#if>				
    </table>
    <p style="margin-top:15px;text-align:center;"><@p.pagination  value=pager uri="userQuery.aj"/></p>
  </div>
 </@p.form>
      <script type="text/javascript">
      
       //显示html 页面
	 function doSubmit(){
	     $('#queryForm').submit();
	 }
     //删除
        function del(){
             var chk=$('input[name="ids"]:checked');
              //验证空删除
              if(chk.length==0){
                  App.showError({content:"请选择要删除的用户!"});
                  return false;
              }
          //删除
          App.delData('queryForm','userDelete.aj','list-page')
        }

</script>          