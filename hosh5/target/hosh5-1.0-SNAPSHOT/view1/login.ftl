<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
		<title>登录</title>
		<link rel="stylesheet" href="${staticPath}/${jsRoot}/css/login.css" />
	</head>

	<body>
		<div class="login_contain">
			<div class="login_box">
				<div class="login_title">
					<h1><a href="index.ftl"><img src="${staticPath}/${jsRoot}/images/andrology.png"></a></h1>
				</div>
				<div class="login_area">
					<form>
						<label class="minput">
		                    	<input type="text" placeholder="用户名" />
		                    	<i class="login_icon user_icon"></i>
	                    </label>
						<label class="minput">
		                    	<input type="password" placeholder="密码" />
		                    	<i class="login_icon password_icon"></i>
	                    </label>
						<div class="btn_div">
							<a href="" class="btn_block">登录</a>
							<p>
								<a href="register.ftl"> 马上注册</a>
								<span>&nbsp;&nbsp;|&nbsp;&nbsp;</span>
								<a href="find_password.ftl"> 忘记密码？ </a>
							</p>
						</div>
					</form>
				</div>
			</div>
		</div>
	</body>

</html>