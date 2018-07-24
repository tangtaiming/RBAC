<html>
<body>
<h2>Hello World <br/>
    <ul>
        <#if fieldErrors??>
            <#list fieldErrors?keys as field>
                <li>${field}:${fieldErrors[field]?default("你请求的页面出错了！")?html?replace("\r\n","<br>")}</li>
            </#list>
        </#if>
    </ul>
</h2>
</body>
</html>
