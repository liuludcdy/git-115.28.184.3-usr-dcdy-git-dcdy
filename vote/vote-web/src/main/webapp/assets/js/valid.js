/*
 * 2016-7-27 
 *author：changbin
 * 各种验证
*/

var validate = {
	
	//验证对象值是否为空
	is_empty : function(form_obj,tip_obj){
		var is_empty = true;
		if(form_obj.val() == undefined || form_obj.val() == ""){
			is_empty = true;
			form_obj.data("vali",0).css('border-color','#ee2546');
			tip_obj.html()==''?tip_obj.html('此项目为必填选项').removeClass('showg').addClass('show'):tip_obj.removeClass('showg').addClass('show');
		}else{
			is_empty = false;
			form_obj.css('border-color','#CCCCCC');
			tip_obj.removeClass('show')
		}
		return is_empty;
	},
	
	//验证用户名是否符合标准
	is_name : function(obj,tip_obj){
		var reg = /^[a-zA-Z0-9_]{4,20}$/;
		var obj_val = obj.val();
		var is_ok = 0;
		if(!obj_val.match(reg)){
			tip_obj.stop().html("用户名格式不正确").removeClass('showg').addClass('show');
			obj.data("vali",0).css('border-color','#ee2546');
		}else{
			is_ok = 1;
			tip_obj.html("请填写用户名以用于登录，请使用英文、数字或组合，长度为4-20个字符").removeClass('show');
			obj.css('border-color','#CCCCCC');
		}
		return is_ok;
	},

	//验证手机号是否符合标准
	is_tel : function(obj,tip_obj){
		var reg = /^(0|86|17951)?(13[0-9]|15[012356789]|17[0678]|18[0-9]|14[57])[0-9]{8}$/;
		var obj_val = obj.val();
		var is_ok = 0;
		if(!obj_val.match(reg)){
			tip_obj.stop().html("手机号码格式不正确").removeClass('showg').addClass('show');
			obj.data("vali",0).css('border-color','#ee2546');
		}else{
			is_ok = 1;
			tip_obj.html("请填写联系人手机号码").removeClass('show');
			obj.css('border-color','#CCCCCC');
		}
		return is_ok;
	},

	//验证邮箱是否符合标准
	is_email : function(obj,tip_obj){
		var reg = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
		var obj_val = obj.val();
		var is_ok = 0;
		if(!obj_val.match(reg)){
			tip_obj.stop().html("邮箱格式不正确").removeClass('showg').addClass('show');
			obj.data("vali",0).css('border-color','#ee2546');
		}else{
			is_ok = 1;
			tip_obj.html("请填写联系邮箱").removeClass('show');
			obj.css('border-color','#CCCCCC');
		}
		return is_ok;
	},
	
	//验证密码是否符合标准
	is_password : function(obj,tip_obj){
		var psd_reg = /^[a-zA-Z0-9]+$/;
		var obj_val = obj.val();
		var is_password = false;
		if( obj_val.length > 16 || obj_val.length < 6 || (!obj_val.match(psd_reg)) ){
			tip_obj.html("请输入6-16位以内的大小写字母或数字").removeClass('showg').addClass('show');
			obj.data("vali",0).css('border-color','#ee2546');
		}else{
			// if (obj.hasClass('password')) tip_obj.hide();
			// tip_obj.html("请填写密码").removeClass('show');
			is_password = true;
		}
		return is_password;
	},
	
	//验证登录时是否有重复项目
	is_repeat : function(obj){ 
		$_data=getdata(obj);
		if ($_data.retCode == 0000){
			// obj.next().removeClass('show')
			return 1;
		}else{
			obj.next().text($_data.retMsg).removeClass('showg').addClass('show')
			return 0;
		}
	},

	// 是否为数字
	is_number : function(val) {
		var is_ok = 0;
		!isNaN(val)?is_ok=1:is_ok=0;
	},

	// 是否是座机电话/手机
	is_phone: function(obj,tip_obj){
		var isPhone = /^([0-9]{3,4}-)?[0-9]{7,8}$/;
	    var isMob=/^((\+?86)|(\(\+86\)))?(13[012356789][0-9]{8}|15[012356789][0-9]{8}|18[02356789][0-9]{8}|147[0-9]{8}|1349[0-9]{7})$/;
	    var value=obj.val();
	    var is_ok = 0;
	    if( isMob.test(value) ){
			is_ok = 1;
			tip_obj.html("请填写手机号码").removeClass('show');
			obj.attr('data-vali',1).css('border-color','#CCCCCC');
	    }else if( isPhone.test(value) ){
			is_ok = 1;
			tip_obj.html("请填写联系电话号码").removeClass('show');
			obj.attr('data-vali',1).css('border-color','#CCCCCC');
	    }else{
	    	is_ok = 0;
	        tip_obj.stop().html("号码格式不正确,请输入正确号码,固定电话请在区号后添加 '-' 分开").addClass('show');
			obj.attr('data-vali',0).css('border-color','#ee2546');
	    }
	    return is_ok;
	},
	
	
	//验证登录时密码、验证码是否正确
	is_same : function(obj){
		
	},
	
	//执行函数
	init:function(obj){
		
	}
	
};

function valid(list,aBtn,num){
	var tip_obj,form_obj,$_val;
	// 验证
	list.blur(function(){
		form_obj = $(this);
		tip_obj = form_obj.siblings('.info-error');
		form_obj.prev('span').removeAttr('style');
		$_val = form_obj.val();
		if( validate.is_empty(form_obj,tip_obj) ){
			form_obj.attr("data-vali",0);
			tip_obj.html()!=''?tip_obj.addClass('show'):tip_obj.addClass('show').html('此项目为必填选项');
			form_obj.css('border-color','#f05067');
		}else{
			if(form_obj.hasClass("tell")){
				if( validate.is_tel(form_obj,tip_obj) ) by()
			}else{
				by()
			};
		}
	});

	list.focus(function() {
		$(this).css('border-color','#83ffff').prev('span').css('color', '#83ffff');
	});
	// 提交
	aBtn.bind('click',function(){
		console.log($(this).html())
		$_this=$(this);
		var j=0;
		for (var i=0;i<list.length;i++) {
			if (list.eq(i).attr('data-vali')==1) {
				j++
			}else{
				list.eq(i).next().addClass('show');
			}
		};
		if(j==list.length){
			// out
			if(num==0){
				alert(0)
			}
		}
	});
	function by(){
		num==9?tip_obj.removeClass('showg').addClass('show').html('<img src="/assets/images/icon/register/pass.png">'):tip_obj.removeClass('show');
		form_obj.attr("data-vali",1);
	}
}
function saveEmail(str){
	var email=str,securityCode=$("#securityC").val(),validateCode=$('.pop7 input[name="validateCode"]').val();
	$.ajax({
        type: "POST",
        dataType: "json",
        url: "/company/updateEmail",
        data: {email: email,validateCode: validateCode,securityCode: securityCode},
        cache: false,
        success: function(data) {
        	if(data.retCode=="0000"){
        		$('.wn1 .youx .p1>span').html(data.body);
        		$('.bg_fixed').fadeOut(150,function(){
					$('.bg_fixed').remove()
				});
        	}else{
        		alertFrame.small_s2('提示',1,data.retMsg)
       		 	// alert(data.retMsg)
       		}
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
        	alertFrame.small_s2('提示',1,'更换邮箱失败')
            // alert("更换邮箱失败");
        }
    });
}

function getdata(obj){
	var data,is_ok;
	inputName = obj.attr("name");
	flag = false;
	if('loginName'==inputName) {
		data = '{loginName: "'+obj.val()+'"}';
		flag = true;
	} else if('contactMobile'==inputName) {
		data = '{mobileNo: "'+obj.val()+'"}';
		flag = true;
	} else if('email'==inputName) {
		data = '{email: "'+obj.val()+'"}';
		flag = true;
	} else if('companyName'==inputName) {
		data = '{companyName: "'+obj.val()+'"}';
		flag = true;
	}
	console.log(data);
	if(flag) {
		data = eval("(" + data + ")");
        $.ajax({
            type: "POST",
            dataType: "json",
            url: "/register/check",
            //data: {loginName: 11},
            data: data,
            cache: false,
            async : false, 
            success: function(data) {
				if('0000'!=data.retCode){
					is_ok=data;
				}
				is_ok=data;
				console.log(is_ok);
            },
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                // alert("发送短信验证码失败");
                alertFrame.small_s2('提示',1,'发送短信验证码失败')
            }
        });
	}
	return is_ok;
}

































