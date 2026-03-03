<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html lang="zh-TW">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>登入 — 公告欄管理系統</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/css/bootstrap.min.css">
</head>
<body class="bg-light">

	<div class="container">
		<div class="row justify-content-center mt-5">
			<div class="col-md-4">
				<div class="card shadow-sm">
					<div class="card-body p-4">
						<h4 class="card-title text-center mb-4">公告欄管理系統</h4>

						<div id="errorMsg" class="alert alert-danger d-none"></div>

						<form id="loginForm">
							<div class="mb-3">
								<label class="form-label">帳號</label> <input type="text"
									id="username" class="form-control" placeholder="請輸入帳號">
							</div>
							<div class="mb-3">
								<label class="form-label">密碼</label> <input type="password"
									id="password" class="form-control" placeholder="請輸入密碼">
							</div>
							<button type="submit" class="btn btn-primary w-100">登入</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script
		src="${pageContext.request.contextPath}/static/js/bootstrap.bundle.min.js"></script>
	<script>
	      document.getElementById('loginForm').addEventListener('submit', function(e) {
	          e.preventDefault();
	
	          const username = document.getElementById('username').value.trim();
	          const password = document.getElementById('password').value.trim();
	          const errorMsg = document.getElementById('errorMsg');
	
	          errorMsg.classList.add('d-none');
	          errorMsg.textContent = '';
	
	          const formData = new URLSearchParams();
	          formData.append('username', username);
	          formData.append('password', password);

	          fetch('${pageContext.request.contextPath}/login', {
	              method: 'POST',
	              headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
	              body: formData
	          })
	          .then(res => res.json())
	          .then(data => {
	              if (data.success) {
	                  sessionStorage.setItem('tabToken', data.data.tabToken);
	                  window.location.href = '${pageContext.request.contextPath}/announcement/list?tabToken=' + data.data.tabToken;
	              } else {
	                  errorMsg.textContent = data.message || '帳號或密碼錯誤';
	                  errorMsg.classList.remove('d-none');
	              }
	          })
	          .catch(() => {
	              errorMsg.textContent = '連線失敗，請稍後再試';
	              errorMsg.classList.remove('d-none');
	          });
	      });
	  </script>

</body>
</html>