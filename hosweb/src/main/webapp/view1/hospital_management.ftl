<#--引入头部ftl-->
<#include 'layout/header.ftl'/>
		<div class="main">
            <div class="main_pad">
                <div class="account_box clearfix">
                    <div class="account_slide">
                        <div class="account_slide_list">
                            <a href="reservation_form.ftl" class="account_slide_title"><i class="iconfont icon-icon77"></i>预约单</a>
                        </div>
                        <div class="account_slide_list">
                            <a href="hospital_management.ftl" class="account_slide_title active"><i class="iconfont icon-souyiyuan"></i>医馆管理</a>
                        </div>
                        <div class="account_slide_list">
                            <a href="doctors_management.ftl" class="account_slide_title"><i class="iconfont icon-yisheng"></i>医师管理</a>
                        </div>
                    </div>
                    <div class="account_main">
                        <div class="account_main_block">
                            <h3 class="account_main_title clearfix">
                                <span class="fl">医馆管理</span>
                                <a href="javascript:;" class="add_hospital"><i class="iconfont icon-jiahao"></i>添加医馆</a>
                            </h3>
                            <form name="hosFrm" method="post" action="${ctx}/web/hos/list">
                            <div class="account_main_pad">
                                <div class="search_table">
                                    <table width="100%">
                                        <colgroup>
                                            <col class="search_col01">
                                            <col class="search_col02">
                                            <col class="search_col03">
                                            <col class="search_col04">
                                            <col class="search_col05">
                                            <col class="search_col06">
                                        </colgroup>
                                        <tr>
                                            <td class="text_right">医馆名称：</td>
                                            <td><input type="text" name="hosName" value="${hosName!}" class="input_common input_middle" /></td>
                                            <td class="text_right">医馆地址：</td>
                                            <td><input type="text" name="hosAddr" value="${hosAddr!}" class="input_common input_middle" /></td>
                                            <td colspan="2">
                                                <button type="button" id="searchBtn" class="btn btn_default">搜  索</button>
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                                <table class="alt" cellpadding="0" cellspacing="0">
                                    <colgroup>
                                        <col class="hospital_col01">
                                        <col class="hospital_col02">
                                        <col class="hospital_col03">
                                        <col class="hospital_col04">
                                        <col class="hospital_col05">
                                        <col class="hospital_col06">
                                    </colgroup>
                                    <thead>
                                    <tr>
                                        <th>医馆名称</th>
                                        <th>医馆地址</th>
                                        <th>医师总数</th>
                                        <th>科室总数</th>
                                        <th>备注</th>
                                        <th>操作</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <#if hospitalList??>
                                    <#list hospitalList as hospital>
                                        <tr>
                                            <td>${hospital.hosName!}</td>
                                            <td>
                                                <p class="hospital_adress">深圳市南山区东滨路407</p>
                                            </td>
                                            <td>100</td>
                                            <td>20</td>
                                            <td>备注信息</td>
                                            <td>
                                                <a href="hospital_edit.ftl" class="blue">编辑</a>&nbsp;&nbsp;
                                                <a href="javascript:;" class="blue">删除</a>
                                            </td>
                                        </tr>
                                    </#list>

                                    <#else>
                                        <#--没有数据情况-->
                                    </#if>

                                    </tbody>
                                </table>
                                <#include 'layout/page.ftl'/>
                            </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

		<!--  layer弹框-->
		<div id="addhospitalLayer" class="hidden">
            <div class="layerDiv">
                <form name="addHospitalFrm" method="post" action="${ctx}/web/hos/add">
                    <div class="input_group clearfix">
                        <label class="input_text">医馆名称：</label>
                        <div class="input_control">
                            <input type="text" name="hosName" class="input_long error_border" />
                            <p class="error_text">错误提示</p>
                        </div>
                    </div>
                    <div class="input_group clearfix">
                        <label class="input_text">所在区域：</label>
                        <div class="input_control">
                            <select class="input_short" name="provinceCode" onchange="getCity(this)">
                                <option>请选择省</option>
                                <#list provinces as province>
                                <option value="${province.provinceCode}">${province.provinceName}</option>
                                </#list>
                            </select>
                            &nbsp;&nbsp;
                            <select class="input_short" name="cityCode" id="cityCode" onchange="getArea(this)">
                                <option>请选择市</option>
                            </select>
                            &nbsp;&nbsp;
                            <select class="input_short" name="areaCode"  id="areaCode">
                                <option>请选择区</option>
                            </select>
                        </div>
                    </div>
                    <div class="input_group clearfix">
                        <label class="input_text">详细地址：</label>
                        <div class="input_control">
                            <textarea class="input_long" name="hosAddr" placeholder="请填写详细信息"></textarea>
                        </div>
                    </div>
                    <div class="input_group clearfix">
                        <label class="input_text">科室选择：</label>
                        <div class="input_control">
                            <div class="department_box">
                                <div class="department_seleted">

                                </div>
                                <a href="javascript:;" class="department_add">新建科室</a>
                            </div>
                            <div class="new_department">
                                <div class="input_group clearfix">
                                    <label class="input_text">科室名称</label>
                                    <div class="input_control">
                                        <input type="text" />
                                    </div>
                                </div>
                                <div class="input_group clearfix">
                                    <label class="input_text">科室图片</label>
                                    <div class="input_control">
                                        <div class="img_box">
                                            <div class="file_box">
                                                <input type="file" class="file_input" />
                                            </div>
                                            <!--有图片的情况-->
                                            <!--<div class="file_box">
                                                <input type="file" class="file_input" />
                                                <img src="images/logo.png" />
                                                <a href="javascript:;" class="file_delete" title="删除"></a>
                                            </div>-->
                                        </div>
                                    </div>
                                </div>
                                <div class="input_group clearfix">
                                    <label class="input_text"></label>
                                    <div class="input_control">
                                        <button type="button" class="btn btn_cancel department_cancel">关闭</button>&nbsp;&nbsp;
                                        <button type="button" class="btn btn_default">提交</button>
                                    </div>
                                </div>
                            </div>
                            <div class="department_div">
                                <#--<a href="javascript:;" class="selected">内科</a>-->
                                <#list depts as dept>
                                    <a href="javascript:;" id="un_${dept.deptId}" onclick="selectDept(this)">${dept.deptName}</a>
                                </#list>
                            </div>
                        </div>
                    </div>
                    <div class="input_group clearfix">
                        <label class="input_text">备注：</label>
                        <div class="input_control">
                            <textarea class="input_long" placeholder="请填写备注信息"></textarea>
                        </div>
                    </div>
                    <div class="button_div">
                        <button type="button" onclick="hospitalCommit()" class="btn_block100 btn_default closeLayer">提 交</button>
                        <button type="button" class="btn_block100 btn_cancel">重 置</button>
                    </div>
                    <input type="hidden" name="depts" id="depts"/>
                </form>
            </div>
        </div>
		<!-- layer弹框 end-->

<#--引入脚部ftl-->
<#include 'layout/footer.ftl'/>
		<script>
            $(document).ready(function() {
                $('.add_hospital').on('click', function() {
                    var index = layer.open({
                        type: 1,
                        skin: 'demo-class',
                        title: '添加医馆',
                        area: ['600px', '620px'],
                        shadeClose: false, //点击遮罩关闭
                        content: $('#addhospitalLayer')
                    });
                    $('.closeLayer').on('click', function() {
                        layer.close(index);
                    });
                });
                $('.department_add').on('click', function() {
                    $(".new_department").slideToggle();
                })
                $('.department_cancel').on('click', function() {
                    $(".new_department").slideUp();
                })
                $("#searchBtn").click(function(){
                    hosFrm.submit();
                });
            });

            //根据省获取市，选择省份时触发
            function getCity(obj){
                //取出obj对象中的value值
                var provinceCode = obj.value;
                $.ajax({
                    type : "GET",
                    url: "${ctx}/web/hos/getCityByProvince?provinceCode="+provinceCode,
                    async : false,
                    dataType : "json",
                    success : function(data) {
                        $("#cityCode").empty();
                        $(data.dataObj).each(function(i,item){
                            $("#cityCode").append("<option value='" + item.cityCode + "'>" + item.cityName + "</option>")
                        });
                        //触发city下拉框的onChange事件
                        $("#cityCode").trigger("change");
                    },
                    error:function(e){
                        console.log(e);
                    }
                });
            }

            //根据市获取区，选择城市时触发
            function getArea(obj){
                //取出obj对象中的value值
                var cityCode = obj.value;
                $.ajax({
                    type : "GET",
                    url: "${ctx}/web/hos/getAreaByCity?cityCode="+cityCode,
                    async : false,
                    dataType : "json",
                    success : function(data) {
                        $("#hosArea").empty();
                        $(data.dataObj).each(function(i,item){
                            $("#areaCode").append("<option value='" + item.areaCode + "'>" + item.areaName + "</option>")
                        });
                    },
                    error:function(e){
                        console.log(e);
                    }
                });
            }

            //选择科室
            function selectDept(obj){
                var unselecta = $(obj).text();
                $(".department_seleted").append("<a href=\"javascript:;\" id=\"select_" + obj.id + "\" onclick=\"removeDept(this)\">" + unselecta + "</a>")
                $(obj).addClass("selected")
            }

            //移除科室
            function removeDept(obj){
                $(obj).remove();
                var oid = obj.id;
                oid = oid.replace(/select_/ , "");
                $("#"+oid).removeClass("selected");
            }

            function hospitalCommit(){
                var deptIdStr = "";
                $(".department_seleted").children().each(function(i,item){
                    var deptId = $(this).attr("id");
                    deptId = deptId.replace(/select_un_/ , "");
                    deptIdStr = deptIdStr + "," + deptId;
                });
                $("input[name='depts']").val(deptIdStr);
                addHospitalFrm.submit();
            }
        </script>
	</body>
</html>
