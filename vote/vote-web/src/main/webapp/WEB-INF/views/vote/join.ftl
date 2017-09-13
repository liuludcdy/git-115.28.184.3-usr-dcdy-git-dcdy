<#setting classic_compatible=true>
<!DOCTYPE html>
<html class="joinpage">
	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=0">
		<meta name="format-detection" content="telephone=no"/>
		<title>北京建筑装饰协会</title>
		<link rel="icon" href="/assets/images/icon.png" type="image/png">
		<link rel="stylesheet" type="text/css" href="/assets/style/style.css"/>
	</head>
	<body>
		<form id="form1" method="post">
		<input type="hidden" name="categoryId">
		<ul>
			<li><span>公司名称：</span><input type="text" name="companyName"><div class="info-error">请填写您的公司名称</div></li>
			<li><span>参选分类：</span>
				<input class="selinput" type="text">
				<div class="info-error">请填写您的参选分类</div>
				<div class="list-item">
					<#list vtCategorys as vtCategory>
					<a href="javascript:;" categoryId=${vtCategory.id} >${vtCategory.categoryName}</a>
					</#list>
				</div>
			</li>
			<li><span>联系人姓名：</span><input type="text" name="concatName"><div class="info-error">请填写您的姓名</div></li>
			<li><span>联系人电话：</span><input class="tell" type="tel" name="concatMobile"><div class="info-error">请填写您的电话</div></li>
			<li class="a-btn"><a href="javascript:;">提交申请信息</a></li>
		</ul>
		</form>
		<script type="text/javascript" src="/assets/js/jquery.js"></script>
		<script type="text/javascript" src="/assets/js/valid.js"></script>
		<script>
		    var inputList=$('.joinpage input'),btn=$('.a-btn>a'),sel=$('.selinput'),item=$('.list-item');
			valid(inputList,btn,0);
			sel.bind('click',function(){
				item.slideDown()
				item.find('a').bind('click',function() {
					sel.val($(this).text()).blur();
					item.slideUp();
					$('input[name=categoryId]').val($(this).attr("categoryId"));
				});
			});
			btn.bind('click',function(){
				v = $('input[name=companyName]').val();
				if(null==v ||''==v) {
					alert('公司名称不能为空!');
					return false;
				}
				v = $('input[name=categoryId]').val();
				if(null==v ||''==v) {
					alert('参选分类不能为空!');
					return false;
				}
				v = $('input[name=concatName]').val();
				if(null==v ||''==v) {
					alert('联系人姓名不能为空!');
					return false;
				}
				v = $('input[name=concatMobile]').val();
				if(null==v ||''==v) {
					alert('联系人电话不能为空!');
					return false;
				}
			    $.ajax({
					type: "post",
					url: "/join",
					data: $("#form1").serialize(), 
					success:function(data) {
					console.log(JSON.stringify(data));
			            if(data.retCode=="0000"){
							location="/joinOk";		                 
			            } else {
							alert(data.retMsg);
			            }
					}
				});	
			});			
		</script>
	</body>
</html>
