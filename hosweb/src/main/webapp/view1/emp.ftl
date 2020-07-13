<html>
<head>
    <title>Emp</title>
</head>
<body>

<table>
    <tr>
        <td>类型</td>
        <td>取值</td>
    </tr>
    <tr>
        <td>整数</td>
        <td><font color="red">${intVar!}</font></td>
    </tr>
    <tr>
        <td>长整数</td>
        <td><font color="red">${longVar!}</font></td>
    </tr>
    <tr>
        <td>字符串</td>
        <td><font color="red">${stringVar!}</font></td>
    </tr>
    <tr>
        <td>双精度</td>
        <td><font color="red">${doubleVar!}</font></td>
    </tr>
    <tr>
        <td>对象属性</td>
        <td><font color="red">${pojoVar.ename!}</font></td>
    </tr>
    <tr>
        <td>SQLDate</td>
        <!--freemarker.properties文件中的日期格式化时针对java.sql.Date类型的-->
        <td><font color="red">${dateSqlVar!}</font></td>
    </tr>
    <tr>
        <td>UtilDate</td>
        <!--使用java.util.Date时需要注意：一定要手动进行格式化-->
        <td><font color="red">${dateUtilVar?string('yyyy/MM/dd HH:mm:ss')}</font></td>
    </tr>
    <tr>
        <td>boolean</td>
        <!--一般情况，我们不建议直接输出boolean值，如果要输出，请在properties文件中配置-->
        <td><font color="red">${booleanVar!}</font></td>
    </tr>
    <tr>
        <td>空处理</td>
        <#--在freemarker中单引号和双引号效果一样-->
        <td><font color="red">${nullVar!'默认值出现'}</font></td>
    </tr>
    <tr>
        <td>空处理2</td>
        <#--在freemarker中单引号和双引号效果一样-->
        <td><font color="red">${nullVar??}</font></td>
    </tr>

    <tr>
        <td>对象为空</td>
        <#--这种整个括起来的情况，系统会首先判断pojoVar，在判断ename-->
        <td><font color="red">${(pojoVar.ename)!'空对象'}</font></td>
    </tr>

    <tr>
        <td>字符串为html</td>
        <#--默认是直接渲染成html，那可以加上?html来阻止渲染，直接输出为txt-->
        <td>${strHtmlVar?html}</td>
    </tr>

    <tr>
        <td>自定义数据</td>
        <#assign dinyi="定义的数据"/>
        <td>${dinyi!}</td>
    </tr>

</table>

<br/>

<label>循环处理</label>
<table border="1">
    <tr>
        <td>编号</td>
        <td>姓名</td>
    </tr>
    <#list emps as emp>
        <tr>
            <td>${emp.eid!}</td>
            <td>${emp.ename!}</td>
        </tr>
    </#list>
</table>

<br/>
<label>Map处理</label>
<table border="1">
    <tr>
        <td>取值</td>
    </tr>
    <#list empMap?keys as key>
        <tr>
            <td>${key} : ${empMap[key]}</td>
        </tr>
    </#list>
</table>
</body>
</html>