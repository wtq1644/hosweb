<#include 'layout/header.ftl'/>

	<body ontouchstart>
	    <!--navbar-->
		<div class="navbar">
			<!--搜索框-->
		    <div class="weui-search-bar">
			  <div class="index_search" onclick="window.location.href='${ctx}/h5/hos/search'">
			    <label class="index_search_label">
			      <i class="weui-icon-search"></i>
			      <span>请输入医生姓名</span>
			    </label>
			  </div>
			</div>
			<!--搜索框  end-->
		</div>
		<!--navbar  end-->
		<!--content-->
		<div class="content">
			<div class="appointment_grids">
				<h2 class="index_title">
					<span>按科室预约</span>
				</h2>
				<div class="weui-grids four_grids">

				<#if depts??>
				<#list depts as dept>
					<a href="${ctx}/h5/doc/doctors?deptId=${dept.deptId!}&deptCode=${dept.deptCode!}" class="weui-grid js_grid">
						<div class="weui-grid__icon">
							<img src="${staticPath}/${jsRoot}/images/${dept.imgUrl!}" alt="">
						</div>
						<p class="weui-grid__label">
							${dept.deptName!}
						</p>
					</a>
				</#list>
				</#if>
			    </div>
			</div>
			<div class="index_doctors">
				<h2 class="index_title">
					<span>按医生预约</span>
				</h2>
				<div class="weui-panel weui-panel_access doctors_list" id="doctorslist">

				</div>
			</div>

			<#include 'doctors_footer.ftl'/>