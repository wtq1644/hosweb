
	<#--抽取出的医师列表加载数据底部，因index.ftl和dept.ftl都需要加载数据，故抽取出相同的部分-->

		<!--此处接content-->

			<#include 'layout/page.ftl'/>
		</div>
		<!--content end-->

		<!--tabbbar-->
		<!--导航栏-->
		<#include 'layout/nav.ftl'/>

		<!--引用 js-->
		<#include 'layout/footer.ftl'/>

		<script>
            //从0页开始，因为参数进去时会自动加1，所以是自动加载第一页的数据
			loadQueryData(0 , 3);
			//获取后台数据

			//医生列表 滚动加载
			var loading = false;
		    $(document.body).infinite().on("infinite", function() {

				//获取分页页码
				var currentPageNo = $("#pageNo").val();
				var pageCount = $("#pageCount").val();
				if(currentPageNo == null || pageCount == null){
					return;
				}
				if (pageCount <= currentPageNo){
					return;
				}

				//下拉加载效果控制
				$(".weui-loadmore").css({"display":"block"});

				//防止短时间内多次重复加载
		        if(loading) return;
		        loading = true;

				//下拉后设置2秒后调接口
				setTimeout(function() {
					loadQueryData(currentPageNo , pageCount);
					//接口回调后重置下拉刷新状态
					setTimeout(function() {
						loading = false;
						$(".weui-loadmore").fadeOut(300);
					}, 1000);
				}, 2000);
		     });

		    //获取后台数据
		    function loadQueryData(currentPageNo , rows){
				//将获取数据的页码增大1
				currentPageNo = (parseInt(currentPageNo)+1);
		    	//默认医馆的id为1
		    	var hosId = 1;
				var param  = "hosId=" + hosId  + "&page=" + currentPageNo;
		    	var deptId = $("#deptId").val();//该值只有dept.ftl中才存在
				if(deptId != null){
					param += "&deptId=" + deptId;
				}

				//科室的医师列表界面可以根据日期进行筛选，该dateStr由点击时触发赋值
				var dateStr = $("#dateStr").val();
                if(dateStr != null){
                    param += "&dateStr=" + dateStr;
                }
				$.ajax({
					url:"${ctx}/h5/doc/doctors?" + param,
					contentType: "application/json; charset=utf-8",
					dataType: "json",
					type:"POST",
					success : function(result){
						if(result.sysRecode == 'success'){
							//获取回调后的数据
							var rows = result.dataObj.rows;
							//解析返回的医师数据，并且追加
							appendDoctorData(rows);

							//追加后更新当前数据所在页码
							var pageCount = result.dataObj.pageTools.pageCount;
							$("#pageNo").val(currentPageNo);
							$("#pageCount").val(pageCount);

						}else{
							$.toptip("获取数据失败，" + result.msg , 'warning');
						}
					},
					error : function(){
						$.toptip("获取更多数据失败，" + result.msg , 'error');
					}
				});
			}

			//处理数据追加的函数
			function appendDoctorData(rows){
		    	var html = "";
		    	$.each(rows ,function(i, item){
		    		var docId = item.docId;
		    		var docName = item.docName;
		    		var docTitle = item.docTitle;
		    		var deptName = item.deptName;
		    		var orderPrice = item.orderPrice;
		    		var expertDescStr = item.expertDesc;

					var expertDescs = expertDescStr.split(",");
					html += '<div class="weui-panel__bd">'
							+'<a href="javascript:;" class="weui-media-box weui-media-box_appmsg">'
							+'<div class="weui-media-box__hd"><img class="weui-media-box__thumb" src="${staticPath}/${jsRoot}/images/doctor01.jpeg" alt="诺康国医馆" /></div>'
							+'<div class="weui-media-box__bd">'
							+'<h4 class="weui-media-box__title">' + docName + '&nbsp;<font class="f14">' + docTitle + '</font></h4>'
							+'<p class="gary f14">' + deptName + '</p>'
							+'<p class="doctor_money">￥' + orderPrice + '</p>'
							+'</div>'
							+'<div class="appoint_btn"><button class="weui-btn weui-btn_mini weui-btn_primary" onclick="yuyue(' + docId + ')">预约</button></div>'
							+'</a>'
							+'<div class="goodat_div">擅长：';

						for(var x in expertDescs){
							html += '<span class="goodat_tag">' + expertDescs[x] + '</span>';
						}

					html += '</div></div>';
				});
				$("#doctorslist").append(html);
			}



			function yuyue(docId , dateStr){
		    	var url = "${ctx}/h5/doc/yuyueDoctor?docId=" + docId;
		    	var dateStr = $("#dateStr").val();
		    	if(dateStr != null){
					url += "&dateStr=" + dateStr
				}
		    	window.location.href = url;
			}
		</script>
	</body>

</html>