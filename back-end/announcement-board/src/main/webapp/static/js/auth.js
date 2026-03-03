(() => {
	const tabToken = sessionStorage.getItem('tabToken');

	// 1. 無 tabToken → 跳回登入頁
	if (!tabToken) {
		window.location.replace(`${APP_CTX}/login`);
		return;
	}

	// 2. 所有 <a href> 加上 ?tabToken=xxxx
	document.querySelectorAll('a[href]').forEach(link => {
		const href = link.getAttribute('href');
		if (!href || href === '#' || href.startsWith('javascript:')) return;
		if (href.includes('tabToken')) return;
		link.href = `${href}${href.includes('?') ? '&' : '?'}tabToken=${tabToken}`;
	});

	// 3. 所有 <form> 插入 hidden tabToken input
	document.querySelectorAll('form').forEach(form => {
		if (form.querySelector('input[name="tabToken"]')) return;
		const input = document.createElement('input');
		input.type = 'hidden';
		input.name = 'tabToken';
		input.value = tabToken;
		form.appendChild(input);
	});

	// 4. 登出處理
	const logout = () => {
		fetch(`${APP_CTX}/logout`, {
			method: 'POST',
			headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
			body: `tabToken=${tabToken}`
		})
			.finally(() => {
				sessionStorage.clear();
				window.location.replace(`${APP_CTX}/login`);
			});
	};

	document.querySelectorAll('[data-action="logout"]').forEach(btn => {
		btn.addEventListener('click', logout);
	});

})();