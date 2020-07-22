<#include 'layout/header.ftl'/>

	<#--我的预约-->
	<body ontouchstart>
		<!--向下拉刷新页面 样式-->
		<div class="weui-pull-to-refresh__layer">
	      <div class='weui-pull-to-refresh__arrow'></div>
	      <div class='weui-pull-to-refresh__preloader'></div>
	      <div class="down">下拉刷新</div>
	      <div class="up">释放刷新</div>
	      <div class="refresh">正在刷新</div>
	    </div>
	    <!--向下拉刷新页面 样式  end-->
		<!--content-->
		<div class="content">
			<div class="appointment_list" id="appointmentlist">
				<div class="weui-cells">
		          <a class="weui-cell weui-cell_access" href="my_appointment_details.ftl">
		            <div class="weui-cell__bd">
		              <p>小明</p>
		            </div>
		            <div class="weui-cell__ft">详细信息</div>
		          </a>
		          <div class="weui-cell">
				        <div class="weui-cell__bd custorm_info">
				             <h3>杨洪艳 主任医师</h3>
				             <p>诺康国医馆前山店</p>
				             <div class="weui-flex flex_height">
				       			<div class="weui-flex__item">
				       				<span class="red">¥20.00</span> / 次
				       			</div>
				       			<div class="weui-flex__item align_right">
				       				2017-04-10 09:00   周一
				       			</div>
				       		 </div>
				        </div>
				   </div>
		        </div>
		        <div class="weui-cells">
		          <a class="weui-cell weui-cell_access" href="my_appointment_details.ftl">
		            <div class="weui-cell__bd">
		              <p>小明</p>
		            </div>
		            <div class="weui-cell__ft">详细信息</div>
		          </a>
		          <div class="weui-cell">
				        <div class="weui-cell__bd custorm_info">
				             <h3>杨洪艳 主任医师</h3>
				             <p>诺康国医馆前山店</p>
				             <div class="weui-flex flex_height">
				       			<div class="weui-flex__item">
				       				<span class="red">¥20.00</span> / 次
				       			</div>
				       			<div class="weui-flex__item align_right">
				       				2017-04-10 09:00   周一
				       			</div>
				       		 </div>
				        </div>
				   </div>
		        </div>
	    		</div>
	    		<!--向下滚动加载样式-->
			<div class="weui-loadmore">
		      <i class="weui-loading"></i>
		      <span class="weui-loadmore__tips">正在加载</span>
		    </div>
		    <!--向下滚动加载样式 end-->
		</div>

        <!--导航栏-->
		<#include 'layout/nav.ftl'/>

        <!--引用 js-->
		<#include 'layout/footer.ftl'/>

		<script>
			//下拉刷新
			$(document.body).pullToRefresh().on("pull-to-refresh", function() {
		        setTimeout(function() {
		          $("#appointmentlist").innerhtml();
		          $(document.body).pullToRefreshDone();
		        }, 2000);
		    });
			//医生列表 滚动加载
			var loading = false;
		    $(document.body).infinite().on("infinite", function() {
		        if(loading) return;
		        loading = true;
		        var cells = document.body.querySelectorAll('.weui-panel__bd');
		        setTimeout(function() {
			        	for (var i = cells.length, len = i + 4; i < len; i++) {
			          $("#appointmentlist").append('<div class="weui-cells">'
			          +'<a class="weui-cell weui-cell_access" href="my_appointment_details.ftl">'
			          +'<div class="weui-cell__bd"><p>小明</p></div>'
			          +'<div class="weui-cell__ft">详细信息</div>'
			          +'</a>'
			          +'<div class="weui-cell">'
			          +'<div class="weui-cell__bd custorm_info">'
			          +'<h3>杨洪艳 主任医师</h3>'
			          +'<p>诺康国医馆前山店</p>'
			          +'<div class="weui-flex flex_height">'
				      +'<div class="weui-flex__item"><span class="red">¥20.00</span> / 次</div>'
			          +'<div class="weui-flex__item align_right">2017-04-10 09:00   周一</div>'
			          +'</div>'
			          +'</div>'
			          +'</div>'
			          +'</div>');
			          loading = false;
			        }
		        }, 2000);
		     });
		</script>
	</body>

</html>