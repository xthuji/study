var App = $;
App.showMsg=function (conf){
	$.modaldialog.success(conf.content);
}

App.showWarn=function (conf){
	$.modaldialog.warning(conf.content);
}

App.showError=function (conf){
	$.modaldialog.error(conf.content);
}
App.listReload=function(refreshId){
	$('#'+refreshId).find('.refresh').click();
}

App.removeTip=function(frm){
	frm.find('.qtip').removeData('qtip');
};

App.checkRet=function(obj,frm){
	if(obj.tooken!=null){
		frm.find('[name=_form_uniq_id]').val(obj.tooken);
	}
	if(obj.status=='0'){
		return true;
	}else if("11"==obj.status){//验证错误
		for(var i=0;i<obj.errorList.length;i++){
			var error = obj.errorList[i];
			frm.find('[name=\"'+error.field+'\"]').removeData('qtip') 
			.qtip({
				content: {
					text: error.defaultMessage, 
					title: {
						text: '错误 ',
						button: true
					}
				},
				position: {
					my: 'left bottom', 
					at:'right bottom'
				},
				show: {
					event: 'focus', 
					ready: true
				},
				hide: {
					fixed: true
				},
				style: {
					classes: 'qtip-shadow qtip-red' 
				}
			});
		}
		return false;
	}
	else{
		App.showError({content:obj.message});
		return false;
	}
}

App.delData=function(formId,url,refreshId){
	if(confirm('确定删除么？')){
		$.post(url, $("#"+formId).serializeArray(),function (data, textStatus){
			if(App.checkRet(data)){
				App.listReload(refreshId);
			}
		}, "json");
	}
};

App.checkByName=function (name,checked){
	$(':checkbox[name='+name+']').attr('checked',checked);
}

//{frmId,s,f }   s: 成功后的回调函数，f:失败的回调函数
App.submitAjaxForm=function(conf){
	App.post($(this).attr('action'), $(this).serializeArray(),function (data, textStatus){
		if(App.checkRet(data)){
			if(conf.s){
				//call
			}
			else {
				var refreshId = $(this).attr("refreshId");
				if(refreshId!=undefined )
					App.listReload(refreshId);
				App.colorbox.close();
			}
		}
	}, "json");
}

App.init=function (){
	$.ajaxSetup ({ 
	    cache: false //关闭AJAX相应的缓存 
	}); 
	$(document).ajaxStart(function(){
		if($('#AjaxStatus').size()==0)
			$(document).append("<div style=\"position:fixed !important; position:absolute;z-index:300;display:none;padding-left:5px;top:20px;right:20px;height:20px;text-align:right;background:#c00;z-index:2;overflow:hidden;color:#fff;\" id=\"AjaxStatus\"><b>请求处理中...</b></div>");
		$('#AjaxStatus').show();
	});
	$(document).ajaxComplete(function(){
		$('#AjaxStatus').hide();
	});
	$(document).ajaxError(function(e,xhr,opt){
		$.modaldialog.error("Error requesting " + opt.url + ": " + xhr.status + " " + xhr.statusText);
	  });
	
	$(".ajax").live('click',function(e){
		e.preventDefault();
		var refreshId = $(this).attr("refreshId");
		//var pageRows = $("#pageRows").val();
		//var href = $(this).attr('href')+"&pageRows="+pageRows;
		$('#'+refreshId).load($(this).attr('href'));
	});
	
	$(".ajaxfrm").live('submit',function(e){
		e.preventDefault();
		var refreshId = $(this).attr("refreshId");
		var action = $(this).attr('action')+"?"+$(this).serialize();
		$('#'+refreshId).load(action);//TODO//
	});
	
	
	$(".ajaxfrmwin").live('submit',function(e){
		e.preventDefault();
		var refreshId = $(this).attr("refreshId");
		var frm= $(this);
		App.post($(this).attr('action'), $(this).serializeArray(),function (data, textStatus){
			if(App.checkRet(data,frm)){
				if(refreshId!=undefined )
					App.listReload(refreshId);
				App.colorbox.close();
			}
		}, "json");
	});
	
	
	
	$(".colorbox").live('click',function (e){//ajax 弹出框
		e.preventDefault();
		App.colorbox({href:$(this).attr('href'),overlayClose:false,close:'取消'});
	});
	
	$('.auto_ajax').each(function(){
		$(this).load($(this).attr('href'));
	});
	
}

$(document).ready(function(){
	App.init();
	
});