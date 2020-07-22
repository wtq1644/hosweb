<#include 'layout/header.ftl'/>
<#--就诊人列表-->

	<body ontouchstart>
		<!--底部固定按钮-->
		<div class="function_block_btn">
			<a href="" class="weui-btn weui-btn_primary">确定</a>
		</div>
		<!--content-->
		<div class="content">
			<div class="content_title weui-flex">
				<div class="weui-flex__item">就诊人</div>
				<div class="weui-flex__item align_right">
					<a href="visitpatients_management02.ftl" class="shopcart_edit">管理</a>
				</div>
			</div>
			<div class="weui-cells weui-cells_radio">
				<div class="weui-cell weui-check__label">
			        <label class="label_radio_box" for="x11">
			          <div class="label_radio_left">
			            <input type="radio" name="radio1" class="weui-check" id="x11" checked="checked">
			            <span class="weui-icon-checked"></span>
			          </div>
			          <div class="weui-cell__bd custorm_info">
			             <h3>小明  135*****512</h3>
						 <p>身份证 360222********56524</p>
						 <span class="weui-badge">默认就诊人</span>
			          </div>
			        </label>
			        <a href="javascript:;" class="label_right" >
			           <i class="iconfont icon-bianji"></i>
			        </a>
			    </div>
			    <div class="weui-cell weui-check__label">
			        <label class="label_radio_box" for="x12">
			          <div class="label_radio_left">
			            <input type="radio" name="radio1" class="weui-check" id="x12">
			            <span class="weui-icon-checked"></span>
			          </div>
			          <div class="weui-cell__bd custorm_info">
			             <h3>小明  135*****512</h3>
						 <p>身份证 360222********56524</p>
			          </div>
			        </label>
			        <a href="javascript:;" class="label_right">
			            <i class="iconfont icon-bianji"></i>
			        </a>
			    </div>
		        <a class="weui-cell weui-cell_access" href="javascript:;" id="addpatientsBtn">
		        		<div class="label_radio_left">
			            <b class="iconfont icon-jiahao2 green"></b>
			        </div>
		            <div class="weui-cell__bd">
		              <p>添加就诊人</p>
		            </div>
		            <div class="weui-cell__ft">
		            </div>
		        </a>
		     </div>
		</div>
		<!--添加就诊人弹框-->
		<div id="addpatients" class="weui-popup__container">
		  <div class="weui-popup__overlay"></div>
		  <div class="weui-popup__modal">
		      <header class="popup_header weui-flex">
		      	<a href="javascript:;" class="close-popup">取消</a>
		      	<h1 class="weui-flex__item"> 添加就诊人</h1>
		      	<a href="javascript:;" class="close-popup">保存</a>
		      </header>
		      <div class="content">
		      	<div class="weui-cells weui-cells_form">
		      		<div class="weui-cell">
				        <div class="weui-cell__hd">
				          <label class="weui-label">姓名</label>
				        </div>
				        <div class="weui-cell__bd">
				          <input class="weui-input" type="text" placeholder="请填写真实的姓名">
				        </div>
				    </div>
				    <div class="weui-cell">
				        <div class="weui-cell__hd">
				          <label class="weui-label">身份证号码</label>
				        </div>
				        <div class="weui-cell__bd">
				          <input class="weui-input" type="text" placeholder="一经填写无法修改">
				        </div>
				    </div>
				    <div class="weui-cell weui-cell_access">
				        <div class="weui-cell__hd">
				          <label class="weui-label">性别</label>
				        </div>
				        <div class="weui-cell__ft flex_block">
							<input type="text" class="hidden_input align_left" placeholder="必填"  id="sexPicker" />
						</div>
				    </div>
				    <div class="weui-cell weui-cell_access">
				        <div class="weui-cell__hd">
				          <label class="weui-label">出生日期</label>
				        </div>
				        <div class="weui-cell__ft flex_block">
							<input type="text" class="hidden_input align_left" placeholder="必填"  id="birthdayPicker" />
						</div>
				    </div>
			    		<!--下面这个加了错误提示的样式-->
			    		<div class="weui-cell weui-cell_warn">
				        <div class="weui-cell__hd">
				          <label class="weui-label">手机号</label>
				        </div>
				        <div class="weui-cell__bd">
				          <input class="weui-input" type="tel" placeholder="请输入手机号">
				        </div>
				        <div class="weui-cell__ft">
					      <i class="weui-icon-warn"></i>
					    </div>
				    </div>
				    <div class="weui-cell weui-cell_vcode">
				    		<div class="weui-cell__hd">
				          <label class="weui-label">验证码</label>
				        </div>
				        <div class="weui-cell__bd">
				          <input class="weui-input" type="number" placeholder="请输入验证码">
				        </div>
				        <div class="weui-cell__ft">
				          <button class="weui-vcode-btn" id="setphonecode">获取验证码</button>
				        </div>
				    </div>
			    </div>
		      </div>
		  </div>
		</div>
		<!--新增就诊人弹框  end-->
        <!--导航栏-->
		<#include 'layout/nav.ftl'/>

        <!--引用 js-->
		<#include 'layout/footer.ftl'/>
		<script>
			$(function() {
				//新增就诊人弹框
				$("#addpatientsBtn").click(function() {
					$("#addpatients").popup();
				})
				//性别
				$("#sexPicker").picker({
					title: "请选择",
					cols: [{
						textAlign: 'center',
						values: ['男', '女']
					}],
					onChange: function(p, v, dv) {
						console.log(p, v, dv);
					},
					onClose: function(p, v, d) {
						console.log("close");
					}
				});
				//生日弹框
				$("#birthdayPicker").calendar({
			        onChange: function (p, values, displayValues) {
			          console.log(values, displayValues);
			        }
			    });
			    //发送验证码
			    $("#setphonecode").click(function() {
					$.toast("发送验证码成功", "text");
				});
			})
		</script>
	</body>

</html>