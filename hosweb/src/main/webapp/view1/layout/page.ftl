<#if pageTools??>
<div class="centen_page">

    <#if (pageTools.pageNo>1)>
        <a href='javascript:;' onclick="exec(this,'startPage')">首页</a>
        <a href='javascript:;' onclick="exec(this,'prePage')">上一页</a>
    <#elseif pageTools.pageNo <= 1>
        <a class='disabled' href='javascript:;'>首页</a>
        <a class='disabled' href='javascript:;'>上一页</a>
    </#if>

    <a href="" class="page_on">${pageTools.pageNo!1}</a>

    <#if pageTools.pageNo < pageTools.pageCount>
        <a href="javascript:;" onclick="exec(this,'nextPage')">下一页</a>
        <a href="javascript:;" onclick="exec(this,'endPage')">最后一页</a>
    <#elseif (pageTools.pageNo >= pageTools.pageCount)>
        <a class='disabled' href="javascript:;">下一页</a>
        <a class='disabled' href="javascript:;">最后一页</a>
    </#if>

    <span>共<strong>${pageTools.recordCount}</strong>条记录&nbsp;&nbsp;	<strong>${pageTools.pageNo}</strong>/${pageTools.pageCount}页</span>
</div>
</#if>
<input type='hidden' name='page' />
<input type='hidden' name='rows' />

<script>
    //分页公共函数
    function exec(obj,type){
        if(type == null || type == ''){
            return false;
        }

        //非常关键
        var current_form=$(obj).closest("form");

        var form;
        if(current_form){
            var formId = current_form.attr("id");
            if(!formId){
                var formName = current_form.attr("name");
                form = $("form[name="+formName+"]");
            }else{
                form = $("#"+formId);
            }
        }
        if(form){
            if(type == 'startPage'){
                startPage(form);
            }else if(type == 'prePage'){
                prePage(form);
            }else if(type == 'nextPage'){
                nextPage(form);
            }else if(type == 'endPage'){
                endPage(form);
            }
        }
    }

    prePage = function(form) {
        var page = ${pageTools.pageNo-1};
        _submit(form,page);
    }

    nextPage = function(form) {
        var page = ${pageTools.pageNo+1};
        _submit(form,page);
    }

    endPage = function(form) {
        var page = ${pageTools.pageCount};
        _submit(form,page);
    }

    startPage = function(form) {
        var page = 1;
        _submit(form,page);
    }

    function _submit(form,page){
        $("input[name='page']",form).val(page);
        $("input[name='rows']",form).val(${pageTools.pageSize});
        form.submit();
    }
</script>