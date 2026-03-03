<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-TW">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>${empty dto.id ? '新增公告' : '編輯公告'}—公告欄管理系統</title>
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
			<h4 class="mb-0">${empty dto.id ? '新增公告' : '編輯公告'}</h4>
			<a href="${ctx}/announcement/list" class="btn btn-secondary">←
				返回列表</a>
		</div>

		<div class="card">
			<div class="card-body">
				<form method="post"
					action="${ctx}/announcement/${empty dto.id ? 'save' : 'update'}">

					<c:if test="${not empty dto.id}">
						<input type="hidden" name="id" value="${dto.id}">
					</c:if>

					<div class="mb-3">
						<label class="form-label">標題 <span class="text-danger">*</span></label>
						<input type="text" name="title" class="form-control"
							value="${dto.title}" required>
					</div>

					<div class="mb-3">
						<label class="form-label">公布者</label> <input type="text"
							class="form-control" value="${currentUser.displayName}" readonly>
					</div>

					<div class="row">
						<div class="col-md-6 mb-3">
							<label class="form-label">發佈日期 <span class="text-danger">*</span></label>
							<input type="date" name="publishDate" class="form-control"
								value="${dto.publishDate}" required>
						</div>
						<div class="col-md-6 mb-3">
							<label class="form-label">截止日期 <span class="text-danger">*</span></label>
							<input type="date" name="expiryDate" class="form-control"
								value="${dto.expiryDate}" required>
						</div>
					</div>

					<div class="mb-3">
						<label class="form-label">公告內容 <span class="text-danger">*</span></label>
						<textarea name="content" class="form-control" rows="8" required>${dto.content}</textarea>
					</div>

					<div class="d-flex gap-2 justify-content-end">
						<a href="${ctx}/announcement/list" class="btn btn-secondary">取消</a>
						<button type="submit" class="btn btn-primary">${empty dto.id ? '新增' : '儲存'}
						</button>
					</div>

				</form>
			</div>
		</div>
	</div>

	<script src="${ctx}/static/js/bootstrap.bundle.min.js"></script>
	<script>
		var APP_CTX = '${ctx}';
	</script>
	<script src="${ctx}/static/js/auth.js"></script>
</body>
</html>