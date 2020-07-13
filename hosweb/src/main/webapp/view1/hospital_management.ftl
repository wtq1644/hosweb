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
							<a href="hospital_management.ftl" class="account_slide_title active"><i class="iconfont icon-souyiyuan"></i>医馆管理</a>
						</div>
						<div class="account_slide_list">
							<a href="doctors_management.ftl" class="account_slide_title"><i class="iconfont icon-yisheng"></i>医师管理</a>
						</div>
					</div>
					<div class="account_main">
						<div class="account_main_block">
							<h3 class="account_main_title clearfix">
								<span class="fl">医馆管理</span>
								<a href="javascript:;" class="add_hospital"><i class="iconfont icon-jiahao"></i>添加医馆</a>
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
											<td class="text_right">医馆名称：</td>
											<td><input type="text" class="input_common input_middle" /></td>
											<td class="text_right">医馆地址：</td>
											<td><input type="text" class="input_common input_middle" /></td>
											<td colspan="2">
												<button type="button" class="btn btn_default">搜  索</button>
											</td>
										</tr>
									</table>
								</div>
								<table class="alt" cellpadding="0" cellspacing="0">
										<colgroup>
			                                <col class="hospital_col01">
			                                	<col class="hospital_col02">
			                                	<col class="hospital_col03">
			                                	<col class="hospital_col04">
			                                	<col class="hospital_col05">
			                                	<col class="hospital_col06">
			                            </colgroup>
										<thead>
											<tr>
												<th>医馆名称</th>
												<th>医馆地址</th>
												<th>医师总数</th>
												<th>科室总数</th>
												<th>备注</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>健康国医馆</td>
												<td>
													<p class="hospital_adress">深圳市南山区东滨路4078号永新时代广场2号楼11、12层</p>
												</td>
												<td>100</td>
												<td>20</td>
												<td>备注信息</td>
												<td>
													<a href="hospital_edit.ftl" class="blue">编辑</a>&nbsp;&nbsp;
													<a href="javascript:;" class="blue">删除</a>
												</td>
											</tr>
										</tbody>
									</table>
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
		<div id="addhospitalLayer" class="hidden">
			<div class="layerDiv">
				<form>
					<div class="input_group clearfix">
						<label class="input_text">医馆名称：</label>
						<div class="input_control">
							<input type="text" class="input_long error_border" />
							<p class="error_text">错误提示</p>
						</div>
					</div>
					<div class="input_group clearfix">
						<label class="input_text">所在区域：</label>
						<div class="input_control">
							<select class="input_short">
								<option>请选择省</option>
							</select>
							&nbsp;&nbsp;
							<select class="input_short">
								<option>请选择市</option>
							</select>
							&nbsp;&nbsp;
							<select class="input_short">
								<option>请选择区</option>
							</select>
						</div>
					</div>
					<div class="input_group clearfix">
						<label class="input_text">详细地址：</label>
						<div class="input_control">
							<textarea class="input_long" placeholder="请填写详细信息"></textarea>
						</div>
					</div>
					<div class="input_group clearfix">
						<label class="input_text">科室选择：</label>
						<div class="input_control">
							<div class="department_box">
								<div class="department_seleted">
									<a href="javascript:;">内科</a>
									<a href="javascript:;">外科</a>
								</div>
								<a href="javascript:;" class="department_add">新建科室</a>
							</div>
							<div class="new_department">
								<div class="input_group clearfix">
									<label class="input_text">科室名称</label>
									<div class="input_control">
										<input type="text" />
									</div>
								</div>
								<div class="input_group clearfix">
									<label class="input_text">科室图片</label>
									<div class="input_control">
										<div class="img_box">
											<div class="file_box">
												<input type="file" class="file_input" />
											</div>
											<!--有图片的情况-->
											<!--<div class="file_box">
												<input type="file" class="file_input" />
												<img src="images/logo.png" />
												<a href="javascript:;" class="file_delete" title="删除"></a>
											</div>-->
										</div>
									</div>
								</div>
								<div class="input_group clearfix">
									<label class="input_text"></label>
									<div class="input_control">
										<button type="button" class="btn btn_cancel department_cancel">关闭</button>&nbsp;&nbsp;
										<button type="button" class="btn btn_default">提交</button>
									</div>
								</div>
							</div>
							<div class="department_div">
								<a href="javascript:;" class="selected">内科</a>
								<a href="javascript:;" class="selected">外科</a>
								<a href="javascript:;">五官科</a>
								<a href="javascript:;">妇科</a>
								<a href="javascript:;">儿科</a>
								<a href="javascript:;">内科</a>
								<a href="javascript:;">外科</a>
								<a href="javascript:;">五官科</a>
								<a href="javascript:;">妇科</a>
								<a href="javascript:;">儿科</a>
							</div>
						</div>
					</div>
					<div class="input_group clearfix">
						<label class="input_text">备注：</label>
						<div class="input_control">
							<textarea class="input_long" placeholder="请填写备注信息"></textarea>
						</div>
					</div>
					<div class="button_div">
						<button type="button" class="btn_block100 btn_default closeLayer">提 交</button>
						<button type="button" class="btn_block100 btn_cancel">重 置</button>
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
				$('.add_hospital').on('click', function() {
					var index = layer.open({
						type: 1,
						skin: 'demo-class',
						title: '添加医馆',
						area: ['600px', '620px'],
						shadeClose: true, //点击遮罩关闭
						content: $('#addhospitalLayer')
					});
					$('.closeLayer').on('click', function() {
						layer.close(index);
					});
				});
				$('.department_add').on('click', function() {
					$(".new_department").slideToggle();
				})
				$('.department_cancel').on('click', function() {
					$(".new_department").slideUp();
				})
			});
		</script>
	</body>
</html>
