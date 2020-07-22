<#include 'layout/header.ftl'/>

	<body ontouchstart>
		<!--底部固定按钮-->
		<div class="function_block_btn">
			<a href="javascript:;" class="weui-btn weui-btn_primary" id="appoint_btn">预约</a>
		</div>
		<!--content-->
		<div class="content">
			<div class="doctors_info">
				<div class="doctors_pic">
					<img src="${staticPath}/${jsRoot}/images/${doctor.docIcon!}">
				</div>
				<h2 class="doctors_name"><span>${doctor.docName!}</span>&nbsp;&nbsp;<font>${doctor.docTitle!}</font></h2>
			</div>
			<div class="weui-cells">
	          <div class="weui-cell">
	            <div class="weui-cell__bd">
	              <p>擅长</p>
	            </div>
	            <a href="javascript:;" class="weui-cell__ft" id="advantageBtn">
	            		<i class="iconfont icon-iconfontunfold"></i>
	            </a>
	          </div>
	          <div class="doctors_advantage">
	          	<p>${doctor.introduce!}${doctor.expertDesc!}</p>
	          </div>
	        </div>
	        <div class="weui-form-preview visiting_info">
	        		<div class="weui-form-preview__bd">
			        <div class="weui-form-preview__item">
			          <label class="weui-form-preview__label">就诊单位</label>
			          <span class="weui-form-preview__value">${hospital.hosName!}</span>
			        </div>
			        <div class="weui-form-preview__item">
			          <label class="weui-form-preview__label">就诊地点</label>
			          <span class="weui-form-preview__value">${hospital.areaName!}${hospital.hosAddr!}</span>
			        </div>
			        <div class="weui-form-preview__item">
			          <label class="weui-form-preview__label">就诊时间</label>
			          <span class="weui-form-preview__value">${planDate}   ${planWeek!}</span>
			        </div>
			        <div class="weui-form-preview__item">
			          <label class="weui-form-preview__label">就诊类型</label>
			          <span class="weui-form-preview__value">专家门诊</span>
			        </div>
			        <div class="weui-form-preview__item">
			          <label class="weui-form-preview__label">预约费用</label>
			          <span class="weui-form-preview__value">¥ ${doctor.orderPrice!} / 次</span>
			        </div>
			   </div>
			</div>
			<div class="weui-cells weui-cells_form">
	      		<a class="weui-cell weui-cell_access" href="javascript:;">
					<div class="weui-cell__hd">
			          <label class="weui-label">就诊人</label>
					</div>
					<div class="weui-cell__ft flex_block">
						<input type="text" class="hidden_input align_left" placeholder="未填写" id="addpatientsBtn" />
					</div>
				</a>
				<a class="weui-cell weui-cell_access" href="javascript:;">
					<div class="weui-cell__hd">
			          <label class="weui-label">初/复诊</label>
			        </div>
			        <div class="weui-cell__ft flex_block">
						<input type="text" class="hidden_input align_left" value="初诊"  id="diagnosis" />
					</div>
				</a>
				<div class="weui-cell form_cell">
			        <div class="weui-cell__hd">
			          <label class="weui-label">就诊描述</label>
			        </div>
			        <div class="weui-cell__bd">
			          <textarea class="weui-textarea" placeholder="请输入就诊描述，可不填" rows="2"></textarea>
			        </div>
			    </div>
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

        <!--引用 js-->
		<#include 'layout/footer.ftl'/>

		<script>
			//医生擅长
			$(function() {
				$("#advantageBtn").click(function() {
					$(".doctors_advantage").toggleClass("auto_height");
				})
			})
			//初/复诊
			$("#diagnosis").picker({
				title: "请选择",
				cols: [{
					textAlign: 'center',
					values: ['初诊', '复诊']
				}],
				onChange: function(p, v, dv) {
					console.log(p, v, dv);
				},
				onClose: function(p, v, d) {
					console.log("close");
				}
			});
			//就诊人弹框
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
				//点击预约弹框
				$("#appoint_btn").click(function() {
					$.toast("预约成功", 2000 , function(){
						$.showLoading("跳转至我的预约");
				   	    setTimeout(function() {
				          $.hideLoading();
				          window.location.href="my_appointment.ftl";
				        }, 2000)
					});
				});
		</script>
	</body>

</html>