<#setting classic_compatible=true>
<!DOCTYPE html>
<html class="list">
	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=0">
		<meta name="format-detection" content="telephone=no"/>
		<title>北京建筑装饰协会</title>
		<link rel="icon" href="/assets/images/icon.png" type="image/png">
		<link rel="stylesheet" type="text/css" href="/assets/style/style.css"/>
	</head>
	<body>
		<img src="/assets/images/h5-02.png" alt="">
		<p>${vtCategory.categoryName}</p>
		<ul>
		<#list vtCompanys as vtCompany>
			<li><i <#if vtCompany_index==0>class="l01"<#elseif vtCompany_index==1>class="l02"<#elseif vtCompany_index==2>class="l03"</#if>> ${vtCompany_index+1}</i><span>${vtCompany.companyName}</span><em>${vtCompany.voteNum}票</em></li>
		</#list>
		</ul>
	</body>
</html>