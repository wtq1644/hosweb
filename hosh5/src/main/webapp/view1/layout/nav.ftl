<div class="weui-tabbar">
    <#if activeSy??>
        <#assign syon=' weui-bar__item--on'/>
    </#if>
    <#if activeYy??>
        <#assign yyon=' weui-bar__item--on'/>
    </#if>
    <#if activeWd??>
        <#assign wdon=' weui-bar__item--on'/>
    </#if>
    <a href="${ctx}/h5/hos/index" class="weui-tabbar__item${syon!}">
        <div class="weui-tabbar__icon">
            <img src="${staticPath}/${jsRoot}/images/tabbar_index${activeSy!}.png" alt="首页">
        </div>
        <p class="weui-tabbar__label">首页</p>
    </a>
    <a href="my_appointment.ftl" class="weui-tabbar__item${yyon!}">
        <div class="weui-tabbar__icon">
            <img src="${staticPath}/${jsRoot}/images/tabbar_classify${activeYy!}.png" alt="预约">
        </div>
        <p class="weui-tabbar__label">预约</p>
    </a>
    <a href="visitpatients_management.ftl" class="weui-tabbar__item${wdon!}">
        <div class="weui-tabbar__icon">
            <img src="${staticPath}/${jsRoot}/images/tabbar_user${activeWd!}.png" alt="我的">
        </div>
        <p class="weui-tabbar__label">我的</p>
    </a>
</div>