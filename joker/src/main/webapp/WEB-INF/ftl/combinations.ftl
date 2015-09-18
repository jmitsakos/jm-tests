<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="utf-8">
    <style type="text/css">
        table.jm{
            width: 50%;
            border: 1px solid green;
        }
        body{
            font-size: 13px;
        }
    </style>
</head>
<body>
<table>
    <tr>
        <td valign="top">
            Combinations of 5 size: <#if comb5??>${comb5?size}<#else>0</#if><br>
        <#if comb5?has_content>
            <table class="jm">
                <tr bgcolor="#d3d3d3"><td>Number</td><td>Frequency</td><td>Last time</td></tr>
                <#list comb5 as c5>
                    <tr>
                        <td bgcolor="#7fffd4">${c5.combination}</td>
                        <td bgcolor="#00ffff">${c5.frequency}</td>
                        <td bgcolor="#00ffff">${c5.lastDrawDate?string["dd/MM/yyyy"]}</td>
                    <#--<td bgcolor="#7fff00">${c4[2]?string["dd/MM/yyyy"]}</td>-->
                    </tr>
                </#list>
            </table>
        </#if>
        </td>
        <td valign="top">
            Combinations of 4 size:  <#if comb4??>${comb4?size}<#else>0</#if><br>
        <#if comb4?has_content>
            <table class="jm">
                <tr bgcolor="#d3d3d3"><td>Number</td><td>Frequency</td><td>Last time</td></tr>
                <#list comb4 as c4>
                    <tr>
                        <td bgcolor="#7fffd4">${c4.combination}</td>
                        <td bgcolor="#00ffff">${c4.frequency}</td>
                        <td bgcolor="#00ffff">${c4.lastDrawDate?string["dd/MM/yyyy"]}</td>
                    </tr>
                </#list>
            </table>
        </#if>
        </td>
        <td valign="top">
            Combinations of 3 size:  <#if comb3??>${comb3?size}<#else>0</#if><br>
        <#if comb3?has_content>
            <table class="jm">
                <tr bgcolor="#d3d3d3"><td>Number</td><td>Frequency</td><td>Last time</td></tr>
                <#list comb3 as c3>
                    <tr>
                        <td bgcolor="#7fffd4">${c3.combination}</td>
                        <td bgcolor="#00ffff">${c3.frequency}</td>
                        <td bgcolor="#00ffff">${c3.lastDrawDate?string["dd/MM/yyyy"]}</td>
                    </tr>
                </#list>
            </table>
        </#if>
        </td>
    </tr>
</table>
</body>

</html>