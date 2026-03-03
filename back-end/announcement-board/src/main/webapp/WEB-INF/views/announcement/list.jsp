<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-TW">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>公告列表 — 公告欄管理系統</title>
<link rel="stylesheet" href="${ctx}/static/css/bootstrap.min.css">
</head>
<body>

	<nav class="navbar navbar-dark bg-primary">
		<div class="container-fluid">
			<span class="navbar-brand">公告欄管理系統</span>
			<div class="d-flex align-items-center">
				<span class="text-white me-3">${currentUser.displayName}</span>
				<button type="button" class="btn btn-outline-light btn-sm"
					data-action="logout">登出</button>
			</div>
		</div>
	</nav>

	<div class="container mt-4">

		<div class="d-flex justify-content-between align-items-center mb-3">
			<h4 class="mb-0">公告列表</h4>
			<a href="${ctx}/announcement/add" class="btn btn-primary">+ 新增公告</a>
		</div>

		<table class="table table-bordered table-hover">
			<thead class="table-light">
				<tr>
					<th>#</th>
					<th>標題</th>
					<th>公布者</th>
					<th>發佈日期</th>
					<th>截止日期</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${pageInfo.items}" var="item">
					<tr>
						<td>${item.id}</td>
						<td>${item.title}</td>
						<td>${item.publisher}</td>
						<td>${item.publishDate}</td>
						<td>${item.expiryDate}</td>
						<td><a href="${ctx}/announcement/edit/${item.id}"
							class="btn btn-sm btn-warning">編輯</a>
							<form method="post"
								action="${ctx}/announcement/delete/${item.id}" class="d-inline"
								onsubmit="return confirm('確定要刪除此公告？')">
								<button type="submit" class="btn btn-sm btn-danger">刪除</button>
							</form></td>
					</tr>
				</c:forEach>
				<c:if test="${empty pageInfo.items}">
					<tr>
						<td colspan="6" class="text-center text-muted">目前沒有公告</td>
					</tr>
				</c:if>
			</tbody>
		</table>

		<!-- 分頁 -->
		<nav>
			<ul class="pagination justify-content-center">
				<li
					class="page-item <c:if test="${!pageInfo.hasPrev}">disabled</c:if>">
					<a class="page-link"
					href="${ctx}/announcement/list?page=${pageInfo.currentPage - 1}">
						上一頁 </a>
				</li>
				<c:forEach begin="1" end="${pageInfo.totalPages}" var="i">
					<li
						class="page-item <c:if test="${i == pageInfo.currentPage}">active</c:if>">
						<a class="page-link" href="${ctx}/announcement/list?page=${i}">
							${i} </a>
					</li>
				</c:forEach>
				<li
					class="page-item <c:if test="${!pageInfo.hasNext}">disabled</c:if>">
					<a class="page-link"
					href="${ctx}/announcement/list?page=${pageInfo.currentPage + 1}">
						下一頁 </a>
				</li>
			</ul>
		</nav>

		<p class="text-center text-muted small">共 ${pageInfo.totalItems}
			筆，第 ${pageInfo.currentPage} / ${pageInfo.totalPages} 頁</p>

	</div>

	<script src="${ctx}/static/js/bootstrap.bundle.min.js"></script>
	<script>
		var APP_CTX = '${ctx}';
	</script>
	<script src="${ctx}/static/js/auth.js"></script>
</body>
</html>