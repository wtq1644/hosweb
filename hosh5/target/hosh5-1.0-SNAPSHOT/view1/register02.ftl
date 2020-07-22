<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
		<title>设置密码</title>
		<link rel="stylesheet" href="${staticPath}/${jsRoot}/css/login.css" />
	</head>

	<body>
		<div class="register_main">
			<form>
				<label class="reginput">
                    	<input type="password" placeholder="输入密码" />
                    	<i class="login_icon password2_icon"></i>
                </label>
                <label class="reginput">
                    	<input type="password" placeholder="确认密码" />
                    	<i class="login_icon passwor3_icon"></i>
                </label>
                <p class="alter_text">密码由6-20位字母、数字和特殊字符两种以上组合</p>
				<div class="btn_div">
					<a href="register03.ftl" class="btn_block">确定</a>
				</div>
			</form>
		</div>
	</body>

</html>