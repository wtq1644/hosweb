<#include 'layout/header.ftl'/>

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
			<div class="weui-cells" id="newslist">
				<a class="weui-cell weui-cell_access news_list_item" href="news_content.ftl">
					<div class="weui-cell__bd">
						<h3 class="text_overflow">这些原因可以造成你的大棚放风机零部件过热！</h3>
						<p class="news_desc">这些原因可以造成你的大棚放风机零部件过热！</p>
						<p>2017-04-05</p>
					</div>
					<div class="weui-cell__ft"></div>
				</a>
				<a class="weui-cell weui-cell_access news_list_item" href="news_content.ftl">
					<div class="weui-cell__bd">
						<h3 class="text_overflow">这些原因可以造成你的大棚放风机零部件过热！</h3>
						<p class="news_desc">这些原因可以造成你的大棚放风机零部件过热！</p>
						<p>2017-04-02</p>
					</div>
					<div class="weui-cell__ft"></div>
				</a>
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
		          $("#newslist").innerhtml();
		          $(document.body).pullToRefreshDone();
		        }, 2000);
		    });
			//新闻列表 滚动加载
		      var loading = false;
		      $(document.body).infinite().on("infinite", function() {
		        if(loading) return;
		        loading = true;
		        var cells = document.body.querySelectorAll('.wallet_list');
		        setTimeout(function() {
		          	for (var i = cells.length, len = i + 4; i < len; i++) {
			          $("#newslist").append('<a class="weui-cell weui-cell_access news_list_item" href="news_content.ftl">'
			          +'<div class="weui-cell__bd">'
			          +'<h3 class="text_overflow">这些原因可以造成你的大棚放风机零部件过热！</h3>'
			          +'<p class="news_desc">这些原因可以造成你的大棚放风机零部件过热！</p>'
			          +'<p>2017-04-02</p>'
			          +'</div>'
			          +'<div class="weui-cell__ft"></div>'
			          +'</div>'
			          +'</a>');
			          loading = false;
			        }
		        }, 2000);
		      });
		</script>
	</body>

</html>