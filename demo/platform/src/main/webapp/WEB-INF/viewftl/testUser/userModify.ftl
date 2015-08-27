
<form  action="userModify.aj"  refreshId="list-page" class="ajaxfrmwin" method="post" stopRefresh=true>
<input type="hidden" name="userId" id="userId" value="${userPo.userId}">
	userName:<input name="userName" type="text" value="${userPo.userName}"/><br>
	email:<input name="email" type="text" value="${userPo.email}"/><br>
	phone:<input name="phone" type="text" value="${userPo.phone}"/><br>
	address:<input name="address" type="text" value="${userPo.address}"/><br>
	age:<input name="age" type="text" value="${userPo.age}"/><br><br>
	 <input type="submit" name="button6" id="button6" value="确定" />
</form>
