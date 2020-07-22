<#include 'layout/header.ftl'/>

	<body ontouchstart>
		<!--底部固定按钮-->
		<div class="function_block_btn">
			<a href="javascript:;" class="weui-btn weui-btn_primary" id="appoint_cancelbtn">取消预约</a>
		</div>
		<!--content-->
		<div class="content">
			<div class="appointment_alter">
				预约单状态：<span>待就诊 （3天后就诊）</span>
			</div>
			<div class="weui-form-preview visiting_info">
	        		<div class="weui-form-preview__bd">
			        <div class="weui-form-preview__item">
			          <label class="weui-form-preview__label">医馆</label>
			          <span class="weui-form-preview__value">诺康国医前山店</span>
			        </div>
			        <div class="weui-form-preview__item">
			          <label class="weui-form-preview__label">科室</label>
			          <span class="weui-form-preview__value">妇科</span>
			        </div>
			        <div class="weui-form-preview__item">
			          <label class="weui-form-preview__label">医生</label>
			          <span class="weui-form-preview__value">杨洪艳  主任医师</span>
			        </div>
			        <div class="weui-form-preview__item">
			          <label class="weui-form-preview__label">就诊类型</label>
			          <span class="weui-form-preview__value">专家门诊</span>
			        </div>
			        <div class="weui-form-preview__item">
			          <label class="weui-form-preview__label">预约费用</label>
			          <span class="weui-form-preview__value">¥ 20.00 / 次</span>
			        </div>
			        <div class="weui-form-preview__item">
			          <label class="weui-form-preview__label">就诊时间</label>
			          <span class="weui-form-preview__value">2017-04-10 09:00  周一</span>
			        </div>
			        <div class="weui-form-preview__item">
			          <label class="weui-form-preview__label">就诊地点</label>
			          <span class="weui-form-preview__value">珠海市香洲区景晖路公安村33栋1层商铺</span>
			        </div>
			        <div class="weui-form-preview__item">
			          <label class="weui-form-preview__label">就诊描述</label>
			          <span class="weui-form-preview__value">***************************</span>
			        </div>
			   </div>
			</div>
		</div>

        <!--导航栏-->
		<#include 'layout/nav.ftl'/>

        <!--引用 js-->
		<#include 'layout/footer.ftl'/>

		<script>
			$(function() {
			    //取消预约弹出确认框
				$("#appoint_cancelbtn").click(function() {
					$.confirm("过多的取消预约可能会导致您一段时间内无法再次预约挂号，请谨慎选择！", function() {
					  //点击确认后的回调函数
					   $.showLoading();
				   	   setTimeout(function() {
				          $.hideLoading();
				          $.toast("取消成功", 2000);
				       }, 2000)
					}, function() {
					  //点击取消后的回调函数
					});
				});
			});
		</script>
	</body>

</html>