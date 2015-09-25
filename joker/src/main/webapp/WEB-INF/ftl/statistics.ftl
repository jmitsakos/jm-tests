<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="utf-8">
    <style type="text/css">
        table.jm{
            width: 50%;
            border: 1px solid green;
            /*border-collapse: collapse;*/
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
            Least recent numbers in five set:<br>
            <table class="jm">
                <tr bgcolor="#d3d3d3"><td>Number</td><td>Frequency</td><td>Last time</td></tr>
            <#list infive as fn>
                <tr>
                    <td bgcolor="#7fffd4">${fn.number}</td>
                    <td bgcolor="#00ffff">${fn.frequency}</td>
                    <td bgcolor="#7fff00">${fn.drawDate?string["dd/MM/yyyy"]}</td>
                </tr>
            </#list>
            </table>
        </td>
        <td valign="top">
            Least recent joker numbers:<br>
            <table class="jm">
                <tr bgcolor="#d3d3d3"><td>Number</td><td>Frequency</td><td>Last time</td></tr>
            <#list joker as j>
                <tr>
                    <td bgcolor="#7fffd4">${j.number}</td>
                    <td bgcolor="#00ffff">${j.frequency}</td>
                    <td bgcolor="#7fff00">${j.drawDate?string["dd/MM/yyyy"]}</td>
                </tr>
            </#list>
            </table>
        </td>
        <td valign="top">
            Least frequent numbers in five set:<br>
            <table class="jm">
                <tr bgcolor="#d3d3d3"><td>Number</td><td>Frequency</td><td>Last time</td></tr>
            <#list infivef as fn>
                <tr>
                    <td bgcolor="#7fffd4">${fn.number}</td>
                    <td bgcolor="#00ffff">${fn.frequency}</td>
                    <td bgcolor="#7fff00">${fn.drawDate?string["dd/MM/yyyy"]}</td>
                </tr>
            </#list>
            </table>
        </td>
        <td valign="top">
            Least frequent joker numbers:<br>
            <table class="jm">
                <tr bgcolor="#d3d3d3"><td>Number</td><td>Frequency</td><td>Last time</td></tr>
            <#list jokerf as j>
                <tr>
                    <td bgcolor="#7fffd4">${j.number}</td>
                    <td bgcolor="#00ffff">${j.frequency}</td>
                    <td bgcolor="#7fff00">${j.drawDate?string["dd/MM/yyyy"]}</td>
                </tr>
            </#list>
            </table>
        </td>
    </tr>
</table>
<table>
    <tr>
        <td valign="top">
            Suggested coupon:
        </td>
    </tr>
    <tr>

    <#assign i = 0>
    <#list suggested as s>
        <#assign i = i + 1>
        <#if i == 6>
            <td bgcolor="#7fffd4">${s}</td>
        <#else>
            <td bgcolor="#00ffff">${s},</td>
        </#if>
    </#list>
    </tr>
</table>
</body>

</html>