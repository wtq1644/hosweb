<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=Edge">
		<title>健康国医馆</title>
		<link type="text/css" rel="stylesheet" href="${staticPath}/${jsRoot}/css/style.css" />
	</head>
	<body>
		<div class="admin_header">
			<div class="container clearfix">
				<h1 class="logo">
					<a href="">
						<img src="${staticPath}/${jsRoot}/images/logo.png" />
					</a>
				</h1>
				<span class="logo_text">健康国医管理后台</span>
				<div class="heder_right">
					<a href="" class=""><i class="iconfont icon-gerenzhongxin"></i>hdjksfjfsd</a>
					<a href="" class=""><i class="iconfont icon-tuichu"></i>退出</a>
				</div>
			</div>
		</div>
		<div class="main">
			<div class="main_pad">
				<div class="account_box clearfix">
					<div class="account_slide">
						<div class="account_slide_list">
							<a href="reservation_form.ftl" class="account_slide_title"><i class="iconfont icon-icon77"></i>预约单</a>
						</div>
						<div class="account_slide_list">
							<a href="hospital_management.ftl" class="account_slide_title"><i class="iconfont icon-souyiyuan"></i>医馆管理</a>
						</div>
						<div class="account_slide_list">
							<a href="doctors_management.ftl" class="account_slide_title active"><i class="iconfont icon-yisheng"></i>医师管理</a>
						</div>
					</div>
					<div class="account_main">
						<div class="account_main_block">
							<h3 class="account_main_title clearfix">
								<span>修改医师</span>
							</h3>
							<div class="account_main_pad">
								<form>
									<div class="input_group">
										<label class="input_text">医生姓名：</label>
										<div class="input_control">
											<input type="text" class="input_long error_border" />
											<p class="error_text">错误提示</p>
										</div>
									</div>
									<div class="input_group">
										<label class="input_text">职称：</label>
										<div class="input_control">
											<select class="input_long">
												<option>请选择</option>
											</select>
										</div>
									</div>
									<div class="input_group">
										<label class="input_text">所属医院：</label>
										<div class="input_control">
											<select class="input_long">
												<option>请选择</option>
											</select>
										</div>
									</div>
									<div class="input_group">
										<label class="input_text">所在科室：</label>
										<div class="input_control">
											<select class="input_long">
												<option>请选择</option>
											</select>
										</div>
									</div>
									<div class="input_group">
										<label class="input_text">预约费用：</label>
										<div class="input_control">
											<input type="text" class="input_long" />&nbsp;次
										</div>
									</div>
									<div class="input_group">
										<label class="input_text">擅长：</label>
										<div class="input_control">
											<textarea class="input_long" placeholder="请填写备注信息"></textarea>
										</div>
									</div>
									<div class="input_group">
										<label class="input_text">备注：</label>
										<div class="input_control">
											<textarea class="input_long" placeholder="请填写备注信息"></textarea>
										</div>
									</div>
									<div class="input_group">
										<label class="input_text"></label>
										<div class="input_control">
											<button type="button" class="btn_block100 btn_default" >提 交</button>
											<button type="button" class="btn_block100 btn_cancel">重 置</button>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="footer">
			<div class="container">
				<div class="footer_link">
					<p>2007-2016  All Rights Reserved.  xxxxxxx有限公司 版权所有 沪ICP备xxxxxx号</p>
				</div>
			</div>
		</div>

		<!--引用js-->
		<script type="text/javascript" src="${staticPath}/${jsRoot}/js/jquery.min.js"></script>
		<script type="text/javascript" src="${staticPath}/${jsRoot}/js/layer/layer.js"></script>
	</body>
</html>
