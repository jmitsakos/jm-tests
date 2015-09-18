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
            Combinations of ${number} size: <#if comb??>${comb?size}<#else>0</#if><br>
        <#if comb?has_content>
            <table class="jm">
                <tr bgcolor="#d3d3d3"><td>Number</td><td>Frequency</td><td>Last time</td></tr>
                <#list comb as c>
                    <tr>
                        <td bgcolor="#7fffd4">${c.combination}</td>
                        <td bgcolor="#00ffff">${c.frequency}</td>
                        <td bgcolor="#00ffff">${c.lastDrawDate?string["dd/MM/yyyy"]}</td>
                    </tr>
                </#list>
            </table>
        </#if>
        </td>
    </tr>
</table>
</body>

</html>