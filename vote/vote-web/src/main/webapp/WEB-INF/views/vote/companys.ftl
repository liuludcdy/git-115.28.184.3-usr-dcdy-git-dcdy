<#setting classic_compatible=true>
<html class="index_page">
	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=0">
		<meta name="format-detection" content="telephone=no"/>
		<title>北京建筑装饰协会</title>
		<link rel="icon" href="/assets/images/icon.png" type="image/png">
		<link rel="stylesheet" type="text/css" href="/assets/style/style.css"/>
		<script type="text/javascript" src="/assets/js/jquery.js"></script>
	</head>
	<body>
		<div class="bg relative">
			<div class="c">
				<h4>${vtCategory.categoryName}</h4>
				<a href="/ranking?categoryId=${vtCategory.id}">查看<span>${vtCategory.categoryName}</span>分类排行榜>></a>
				<p>投票已结束</p>
			</div>
			<div class="btm">
				<h5><b>--------------------------------------------------------</b>选择投票公司<b>--------------------------------------------------------</b></h5>
				<#list vtCompanys as vtCompany>
				<div class="pick big">
					<img class="s" src="${vtCompany.companyImage}" alt="">
					<div>${vtCompany.companyName}</div>
					<span><i id="num${vtCompany.id}">${vtCompany.voteNum}</i>票</span>
					<a class="s <#if vtCompany.status==1>o</#if>" companyId="${vtCompany.id}" href="javascript:;"><#if vtCompany.status==1>已投票<#else>投票</#if></a>
				</div>
				</#list>
			</div>
			<a class="join" href="/join">我要参选</a>
			<a class="tel" type="tel" href="tel:400-818-8800"></a>
		</div>
	</body>
<script type="text/javascript">
$(function(){
	$('.pick>a').bind('click',function(e){
		var $_this=$(this),v=$_this.attr("companyId");
	    $.ajax({
			type: "post",
			url: "/vote",
			data: {companyId: v}, 
			success:function(data) {
	            if(data.retCode=="0000"){
	            	$_this.addClass('o').text('已投票');
	            	$('#num'+v).text(data.body);
	            } else {
					alert(data.retMsg);
	            }
			}
		});
	});
});
</script>
</html>