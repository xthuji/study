
<body>
<font color="red">${msg!}</font><br>
<form  action="userAdd.aj"  refreshId="list-page" class="ajaxfrmwin" method="post" stopRefresh=true>
	userName:<input name="userName" type="text"/><br>
	email:<input name="email" type="text"/><br>
	phone:<input name="phone" type="text"/><br>
	address:<input name="address" type="text"/><br>
	age:<input name="age" type="text"/><br><br>
	<button type="submit">确认</button>
</form>
</body>