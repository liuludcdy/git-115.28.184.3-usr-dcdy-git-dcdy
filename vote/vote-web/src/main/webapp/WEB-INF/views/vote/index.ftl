<#setting classic_compatible=true>
<!DOCTYPE html>
<html class="index_page">
	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=0">
		<meta name="format-detection" content="telephone=no"/>
		<title>北京建筑装饰协会</title>
		<link rel="icon" href="/assets/images/icon.png" type="image/png">
		<link rel="stylesheet" type="text/css" href="/assets/style/style.css"/>
	</head>
	<body>
		<img src="/assets/images/bg.jpg" width="0" height="0"/>
		<div class="bg relative">
			<div class="c">
				<span>投票总人数：</span><i>${totalVote}</i>
				<p>投票已结束</p>
			</div>
			<div class="btm">
				<h5><b>--------------------------------------------------------</b>选择投票分类<b>--------------------------------------------------------</b></h5>
				<#list vtCategorys as vtCategory>
				<div class="pick">
					<img src="${vtCategory.categoryImage}" alt="">
					<div class="info">${vtCategory.categoryName}</div>
					<a href="/queryCompany?categoryId=${vtCategory.id}">去投票</a>
				</div>
				</#list>
			</div>
			<a class="join" href="/join">我要参选</a>
			<a class="tel" type="tel" href="tel:400-818-8800"></a>
		</div>
	</body>
</html>