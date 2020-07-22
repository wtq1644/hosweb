
<#--分页属性-->
<input type="hidden" id="pageNo" value="${(pageTools.pageNo)!1}"/>
<input type="hidden" id="pageCount" value="${(pageTools.pageCount)!3}"/>

<!--向下滚动加载样式-->
<div class="weui-loadmore" style="display: none;">
    <i class="weui-loading"></i>
    <span class="weui-loadmore__tips">正在加载</span>
</div>
<!--向下滚动加载样式 end-->