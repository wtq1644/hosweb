<div class="footer">
    <div class="container">
        <div class="footer_link">
            <p>2007-2019  All Rights Reserved.  xxxxxxx有限公司 版权所有 沪ICP备xxxxxx号</p>
        </div>
    </div>
</div>

<#--弹出提示div-->
<div class="warn_text" id="error_text">&nbsp;</div>

<!--引用js-->
<script type="text/javascript" src="${staticPath}/${jsRoot}/js/jquery.min.js"></script>
<script type="text/javascript" src="${staticPath}/${jsRoot}/js/layer/layer.js"></script>

<script>
    /*顶部弹出提示*/
    function _hint_(addClazz, hint, stylz){
        $("#error_text").removeClass().addClass(addClazz+"_text").html("").append(hint).attr("style","display:"+stylz);
        setTimeout(function () {
            $("#error_text").attr("style","display:none");
            return true;
        }, 5000);
    }

    /*弹出提示使用方式如下
    *
    *   //错误
        _hint_("error",result.msg,"block");
        //警告
        _hint_("warn",result.msg,"block");
        //成功
        _hint_("success",result.msg,"block");
    * */
</script>
