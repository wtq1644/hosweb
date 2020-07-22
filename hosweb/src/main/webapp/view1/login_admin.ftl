<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
		<title>登录</title>
		<link type="text/css" rel="stylesheet" href="${staticPath}/${jsRoot}/css/login.css" />
	</head>

	<body>
		<div class="login_admincontain">
			<div class="over_bg"></div>
			<div class="login_adminbox">
				<div class="login_title">
					<h2><a href="index.html"><img src="${staticPath}/${jsRoot}/images/logo.png"></a></h2>
				</div>
				<div class="login_area">
					<form id="loginFrm">
						<label class="minput">
		                    	<input type="text" name="userName" id="userName" placeholder="用户名" />
		                    	<i class="login_icon user_icon"></i>
	                    </label>
						<label class="minput">
		                    	<input type="password" name="password" id="password" placeholder="密码" />
		                    	<i class="login_icon password_icon"></i>
	                    </label>
						<div class="btn_div">
							<a href="javascript:void(0);" class="btn_block">登录</a>
						</div>
					</form>
				</div>
			</div>
		</div>
        <div class="warn_text" id="error_text">&nbsp;</div>
	<script type="text/javascript" src="${staticPath}/${jsRoot}/js/jquery.min.js"></script>
	<script>

		/*登录为什么要做ajax方式，这就相当于我们写了一个登录接口，APP都可以直接调用*/
		$(function(){
		    $(".btn_div").click(function(){
				var loginParam = $("#loginFrm").serialize();
                $.ajax({
                    url:"${ctx}/sys/login",
                    type:"POST",
                    data : loginParam,
                    success : function(result){
                        if(result.sysRecode == 'success'){
                            //登录成功，跳转首页
							window.location.href = "${ctx}/web/hos/list";
						}else{
                            _hint_("warn" , result.msg , "block");
						}
                    },
                    error : function(){

                    }
                });
            });

		});

        function _hint_(addClazz, hint, stylz){
            $("#error_text").removeClass().addClass(addClazz+"_text").html("").append(hint).attr("style","display:"+stylz);
            setTimeout(function () {
                $("#error_text").attr("style","display:none");
                return true;
            }, 5000);
        }
	</script>
	</body>
</html>