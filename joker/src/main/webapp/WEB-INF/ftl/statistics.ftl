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
                    <td bgcolor="#7fffd4">${fn[0]}</td>
                    <td bgcolor="#00ffff">${fn[1]}</td>
                    <td bgcolor="#7fff00">${fn[2]?string["dd/MM/yyyy"]}</td>
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
                    <td bgcolor="#7fffd4">${j[0]}</td>
                    <td bgcolor="#00ffff">${j[1]}</td>
                    <td bgcolor="#7fff00">${j[2]?string["dd/MM/yyyy"]}</td>
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
                    <td bgcolor="#7fffd4">${fn[0]}</td>
                    <td bgcolor="#00ffff">${fn[1]}</td>
                    <td bgcolor="#7fff00">${fn[2]?string["dd/MM/yyyy"]}</td>
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
                    <td bgcolor="#7fffd4">${j[0]}</td>
                    <td bgcolor="#00ffff">${j[1]}</td>
                    <td bgcolor="#7fff00">${j[2]?string["dd/MM/yyyy"]}</td>
                </tr>
            </#list>
            </table>
        </td>
    </tr>
</table>
</body>

</html>