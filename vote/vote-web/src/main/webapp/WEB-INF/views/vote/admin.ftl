<#setting classic_compatible=true>
<html class="index_page">
	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=0">
		<meta name="format-detection" content="telephone=no"/>
		<title>北京建筑装饰协会</title>
		<script type="text/javascript" src="/assets/js/jquery.js"></script>
	</head>

	<#assign categoryId = RequestParameters["categoryId"]>
	<#assign companyId = RequestParameters["companyId"]>
	<#assign act = RequestParameters["act"]>

	<body>
		<form name="form1" action="/admin">
			<table width="50%" border=0>
				<tr>
					<td>
					<select name="categoryId" style="width:150px">
						<#list vtCategorys as vtCategory>
						<option value="${vtCategory.id}" <#if categoryId==vtCategory.id>selected</#if> >${vtCategory.categoryName}</option>
						</#list>
					</select>
					<input type="submit" value="查询">
					</td>
				</tr>
			</table>
		</form>
		-------------------------------------------------------------------------------------------------------------------
		<form name="form2" action=<#if act=='modify'>"/admin/modify"<#else>"/admin/add"</#if> method="post" enctype="multipart/form-data">
			<input type="hidden" name="companyId" value="${companyId}">
			<table width="80%" border=0>
				<tr>
					<td>公司名称：<input type="text" name="companyName" value="${svtCompany.companyName}" style="width:150px"></td>
					<td>类目：
					<select name="categoryId" style="width:150px">
						<#list vtCategorys as vtCategory>
						<option <#if svtCompany.categoryId==vtCategory.id>selected</#if> value="${vtCategory.id}">${vtCategory.categoryName}</option>
						</#list>
					</select>
					</td>
					<td>投票数：<input type="text" name="voteNum" value="${svtCompany.voteNum}" style="width:150px"></td>
					<td>图片：<input type="file" name="companyImage" style="width:150px"><#if svtCompany.companyImage??><a href="${svtCompany.companyImage}" target="_blank"><img src="${svtCompany.companyImage}" style="width:20px;height:20px"></a></#if></td>
					<td><input type="submit" value=<#if act=='modify'>"修改"<#else>"新增"</#if>></td>
				</tr>
			</table>
		</form>
		<br/>
		-------------------------------------------------------------------------------------------------------------------
		<br/>
		<table width="100%">
		<tr>
			<td>单位名称</td>
			<td>所属类目</td>
			<td>点击数</td>
			<td>状态</td>
			<td>联系人</td>
			<td>联系电话</td>
			<td>图片</td>
			<td>操作</td>
		</tr>
		<#if vtCompanys??>
		<#list vtCompanys as vtCompany>
		<tr>
			<td>${vtCompany.companyName}</td>
			<td>${vtCompany.categoryId}</td>
			<td>${vtCompany.voteNum}</td>
			<td><#if vtCompany.status==0>无效<#else>有效</#if></td>
			<td>${vtCompany.concatName}</td>
			<td>${vtCompany.concatMobile}</td>
			<td><a href="${vtCompany.companyImage}" target="_blank"><img src="${vtCompany.companyImage}" style="width:20px;height:20px"></a></td>
			<td>
				<a href="/admin/delete?companyId=${vtCompany.id}">删除</a>
				<a href="/admin?companyId=${vtCompany.id}&act=modify">修改</a>
				<#if vtCompany.status==1>
				<a href="/admin/offLine?companyId=${vtCompany.id}">上架</a>
				<#else>
				<a href="/admin/onLine?companyId=${vtCompany.id}">上架</a>
				</#if>
			</td>
		</tr>
		</#list>
		</#if>
		</table>
	</body>
</html>