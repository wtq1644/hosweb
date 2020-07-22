<#include 'layout/header.ftl'/>

	<body ontouchstart>
		<!--navbar-->
		<div class="navbar">
			<!--搜索框-->
		    <div class="weui-search-bar weui-search-bar_focusing" id="searchBar">
			  <form class="weui-search-bar__form">
			    <div class="weui-search-bar__box">
			      <i class="weui-icon-search"></i>
			      <input type="search" class="weui-search-bar__input" id="searchInput" placeholder="搜索" required="">
			      <a href="javascript:" class="weui-icon-clear" id="searchClear"></a>
			    </div>
			    <label class="weui-search-bar__label" id="searchText">
			      <i class="weui-icon-search"></i>
			      <span>请输入医生姓名</span>
			    </label>
			  </form>
			  <a href="javascript:" class="weui-search-bar__cancel-btn" id="searchCancel">取消</a>
			</div>
			<!--搜索框  end-->
		</div>
		<!--navbar  end-->
		<!--content-->
		<div class="content">
			<div class="search_result">
				<i class="iconfont icon-sousuo1"></i>
				<h3>请在搜索框输入关键字</h3>
			</div>
		</div>

        <!--导航栏-->
		<#include 'layout/nav.ftl'/>

        <!--引用 js-->
		<#include 'layout/footer.ftl'/>

		<script>
			$(function() {
				$("#searchInput").keyup(function(){
					$('#searchCancel').text('搜 索');
				})
			})
		</script>
	</body>

</html>