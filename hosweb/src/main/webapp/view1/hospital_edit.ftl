<#--引入头部ftl-->
<#include 'layout/header.ftl'/>
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
								<span>修改医馆</span>
							</h3>
							<div class="account_main_pad">
								<form>
									<div class="input_group">
										<label class="input_text">医馆名称：</label>
										<div class="input_control">
											<input type="text" class="input_long error_border" />
											<p class="error_text">错误提示</p>
										</div>
									</div>
									<div class="input_group">
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
									<div class="input_group">
										<label class="input_text">详细地址：</label>
										<div class="input_control">
											<textarea class="input_long" placeholder="请填写详细信息"></textarea>
										</div>
									</div>
									<div class="input_group">
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
												<div class="input_group">
													<label class="input_text">科室名称</label>
													<div class="input_control">
														<input type="text" />
													</div>
												</div>
												<div class="input_group">
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
												<div class="input_group">
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
									<div class="input_group">
										<label class="input_text">备注：</label>
										<div class="input_control">
											<textarea class="input_long" placeholder="请填写备注信息"></textarea>
										</div>
									</div>
									<div class="input_group">
										<label class="input_text"></label>
										<div class="input_control">
											<button type="button" class="btn_block100 btn_default">提 交</button>
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

		<#--引入脚部ftl-->
		<#include 'layout/footer.ftl'/>

		<script>
			$(document).ready(function() {
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
