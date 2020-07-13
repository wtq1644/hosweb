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
					<a href=""><i class="iconfont icon-gerenzhongxin"></i>hdjksfjfsd</a>
					<a href=""><i class="iconfont icon-tuichu"></i>退出</a>
				</div>
			</div>
		</div>
		<div class="main">
			<div class="main_pad">
				<div class="account_box clearfix">
					<div class="account_slide">
						<div class="account_slide_list">
							<a href="reservation_form.ftl" class="account_slide_title active"><i class="iconfont icon-icon77"></i>预约单</a>
						</div>
						<div class="account_slide_list">
							<a href="hospital_management.ftl" class="account_slide_title"><i class="iconfont icon-souyiyuan"></i>医馆管理</a>
						</div>
						<div class="account_slide_list">
							<a href="doctors_management.ftl" class="account_slide_title"><i class="iconfont icon-yisheng"></i>医师管理</a>
						</div>
					</div>
					<div class="account_main">
						<div class="account_main_block">
							<h3 class="account_main_title">
								<span>预约单</span>
							</h3>
							<div class="account_main_pad">
								<div class="search_table">
									<table width="100%">
										<colgroup>
			                                <col class="search_col01">
			                                	<col class="search_col02">
			                                	<col class="search_col03">
			                                	<col class="search_col04">
			                                	<col class="search_col05">
			                                	<col class="search_col06">
			                            </colgroup>
										<tr>
											<td class="text_right">预约单：</td>
											<td><input type="text" class="input_common input_middle" /></td>
											<td class="text_right">预约手机号：</td>
											<td><input type="text" class="input_common input_middle"></td>
											<td class="text_right">预约时间：</td>
											<td><input type="text" class="input_common input_short">&nbsp;-&nbsp;<input type="text" class="input_common input_short"></td>
										</tr>
										<tr>
											<td class="text_right">状态：</td>
											<td>
												<select class="input_common input_middle">
													<option selected="">请选择</option>
													<option>待就诊</option>
													<option>就诊成功</option>
													<option>就诊失败</option>
													<option>预约取消</option>
												</select>
											</td>
											<td colspan="4">
												<button type="button" class="btn btn_default">搜  索</button>
											</td>
										</tr>
									</table>
								</div>
								<div class="table_warp">
									<table class="alt" cellpadding="0" cellspacing="0">
										<thead>
											<tr>
												<th>预约单号</th>
												<th>预约医生信息</th>
												<th>预约联系人信息</th>
												<th>类型</th>
												<th>费用</th>
												<th>初/复诊</th>
												<th>预约时间</th>
												<th>就诊时间</th>
												<th>备注</th>
												<th>状态</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>
													<div class="col01 over_hidden">2017050400011</div>
												</td>
												<td>
													<p class="table_list">
														<label class="table_list_title">医生：</label>
														<span class="table_list_info">王娟</span>
													</p>
													<p class="table_list">
														<label class="table_list_title">科室：</label>
														<span class="table_list_info">内科</span>
													</p>
													<p class="table_list">
														<label class="table_list_title">医馆：</label>
														<span class="table_list_info">健康国医</span>
													</p>
												</td>
												<td class="text_left">
													<p class="table_list">
														<label class="table_list_title">姓名：</label>
														<span class="table_list_info">李丽</span>
													</p>
													<p class="table_list">
														<label class="table_list_title">手机号：</label>
														<span class="table_list_info">18987776890</span>
													</p>
													<p class="table_list">
														<label class="table_list_title">预约描述：</label>
														<span class="table_list_info">描述描述</span>
													</p>
												</td>
												<td>
													<div class="col04">专家门诊</div>
												</td>
												<td>
													<div class="col05">￥120.00</div>
												</td>
												<td>
													<div class="col06">初诊</div>
												</td>
												<td>
													<div class="col07">2017-05-09 09:00:34</div>
												</td>
												<td>
													<div class="col08">2017-05-09 09:00:34</div>
												</td>
												<td>
													<div class="col09">备注信息备注信息备注信息</div>
												</td>
												<td>
													<div class="col10">待就诊</div>
												</td>
												<td>
													<div class="col11">
														<a href="javascript:;" class="blue visiting_state">就诊失败</a>
														<a href="javascript:;" class="blue visiting_state">就诊成功</a>
													</div>
												</td>
											</tr>
										</tbody>
									</table>
								</div>
								<div class="centen_page">
									<a class="disabled">上一页</a>
									<a href="" class="page_on">1</a>
									<a href="">2</a>
									<a href="">3</a>
									<a href="">4</a>
									<a href="">5</a>
									<a href="">下一页</a>
									<a href="">最后一页</a>
									<span>共<strong>100</strong>条记录&nbsp;&nbsp;<strong>1</strong>/10页</span>
								</div>
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
		
		<!--  layer弹框-->
		<div id="visitingLayer" class="hidden">
			<div class="layerDiv">
				<form>
					<div class="input_group clearfix">
						<label class="input_text">备注：</label>
						<div class="input_control">
							<textarea class="input_long" placeholder="请填写备注信息"></textarea>
						</div>
					</div>
					<div class="button_div">
						<button type="button" class="btn_block100 btn_default" >提 交</button>
						<button type="button" class="btn_block100 btn_cancel closeLayer">关 闭</button>
					</div>
				</form>
			</div>
		</div>
		<!-- layer弹框 end-->
		
		<!--引用js-->
		<script type="text/javascript" src="${staticPath}/${jsRoot}/js/jquery.min.js"></script>
		<script type="text/javascript" src="${staticPath}/${jsRoot}/js/layer/layer.js"></script>
		<script>
			$(document).ready(function() {
				$('.visiting_state').on('click', function() {
					var index = layer.open({
						type: 1,
						skin: 'demo-class',
						title: '就诊失败',
						area: ['500px', '350px'],
						shadeClose: true, //点击遮罩关闭
						content: $('#visitingLayer')
					});
					$('.closeLayer').on('click', function() {
						layer.close(index);
					});
				});
			});
		</script>
	</body>
</html>
