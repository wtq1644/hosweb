<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
		<title>注册</title>
		<link rel="stylesheet" href="${staticPath}/${jsRoot}/css/login.css" />
	</head>

	<body>
		<div class="register_main">
			<form>
				<label class="reginput">
                    	<input type="number" placeholder="手机号" />
                    	<i class="login_icon phone_icon"></i>
                </label>
				<label class="reginput testinput">
                    	<input type="text" placeholder="验证码" />
                    	<i class="login_icon code_icon"></i>
                    	<a href="" class="btn_message">获取验证码</a>
                    	<!--<a href="" class="btn_message btn_disabled">60s重新获取</a>-->
               </label>
				<div class="btn_div">
					<a href="register02.ftl" class="btn_block">注册</a>
					<p>
						<a href="login.ftl"> 已有账号，马上登录</a>
					</p>
				</div>
			</form>
		</div>
	</body>

</html>