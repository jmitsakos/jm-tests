<!DOCTYPE html>

<html lang="en">

<body>
Suggested five numbers:<br>
<table border="1">
    <tr bgcolor="#d3d3d3"><td>Number</td><td>Frequency</td><td>Last time</td></tr>
<#list infive as fn>
<tr>
    <#assign i = 0>
    <#list fn?split(",") as x>
        <#assign i = i + 1>
        <#if i == 1>
        <td bgcolor="#7fffd4">${x}</td>
        <#else>
        <td bgcolor="#00ffff">${x}</td>
        </#if>
    </#list>
</tr>
</#list>
</table>

<br>Suggested joker:<br>
<table border="1">
    <tr bgcolor="#d3d3d3"><td>Number</td><td>Frequency</td><td>Last time</td></tr>
<#list joker as jn>
<tr>
    <#assign i = 0>
    <#list jn?split(",") as x>
        <#assign i = i + 1>
        <#if i % 2 == 0>
            <td bgcolor="#7fffd4">${x}</td>
        <#else>
            <td bgcolor="#00ffff">${x}</td>
        </#if>
    </#list>
</tr>
</#list>
</table>

</body>

</html>