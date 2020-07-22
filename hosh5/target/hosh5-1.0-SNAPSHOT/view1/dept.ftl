<#include 'layout/header.ftl'/>
<#--由首页点击科室进入的医师列表-->
	<body ontouchstart>
		<!--content-->
		<div class="content">
			<div class="date_filter">
				<div class="weui-cells weui-cells_form">
					<div class="weui-cell weui-cell_switch">
				        <div class="weui-cell__bd">只显示有号的日期</div>
				        <div class="weui-cell__ft">
				          <label for="switchCP" class="weui-switch-cp">
				            <input id="switchCP" class="weui-switch-cp__input" type="checkbox" checked="checked">
				            <div class="weui-switch-cp__box"></div>
				          </label>
				        </div>
				    </div>
				</div>

                <#--显示排号-->
                <#if planDateMap??>
                    <div class="date_box">
                        <#--左滑动箭头，未实现-->
                        <a href="javascript:;" class="date_reduce iconfont icon-reduce"></a>
                        <div class="weui-flex date_list">
                            <#list planDateMap?keys as key>
                                <div class="weui-flex__item date_list_item <#if currentDate! == key>date_list_active</#if>">
                                    <#--点击该日期时可进行筛选， 该function写在doctors_footer.ftl中-->
                                    <a href="javascript:;" onclick="queryDoctorsByDate(this , '${key}')">
                                        <span>${key}</span>
                                        <p>${planDateMap[key]!}</p>
                                    </a>
                                </div>
                            </#list>
                        </div>
                        <#--右滑动箭头，未实现-->
                        <a href="javascript:;" class="date_plus iconfont icon-plus"></a>
                    </div>
                </#if>

			</div>

            <#-- 科室筛选条件 -->
			<input type="hidden" id="deptId" value="${deptId!}">
            <#-- 日期筛选条件 -->
			<input type="hidden" id="dateStr" value="${currentDate!}">

			<div class="weui-panel weui-panel_access doctors_list" id="doctorslist">

			</div>

			<#--因医师列表在dept.ftl和index.ftl中都存在，故将追加代码抽取至doctors_footer.ftl中-->
			<#include 'doctors_footer.ftl'/>


            <script>
                function queryDoctorsByDate(obj , dateStr){
                    $("#dateStr").val(dateStr);

                    //清空现有医师列表
                    $("#doctorslist").empty();

                    //日期选中状态改变，先移除所有，然后给当前对象新增选中
                    $(".date_list_item").removeClass("date_list_active");
                    $(obj).parent().addClass("date_list_active")

                    //加载数据
                    loadQueryData(0 , 3);
                }


                $(function(){
                    //有号日期列表开关，请大家自行实现
                    $("#switchCP").change(function(){
                        if($(this).is(':checked')){
                            //选中，开启有号日期列表
                            //...
                        }else{
                            //未选中，移除有号日期列表
                            //...
                        }
                    });
                })
            </script>