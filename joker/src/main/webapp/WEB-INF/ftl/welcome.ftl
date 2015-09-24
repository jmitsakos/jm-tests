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

<#if bydate??>Result for date: <#else>Latest 5 results:</#if><br>
<table class="jm">
    <tr bgcolor="#d3d3d3">
        <td class="jm">#</td>
        <td class="jm">N1</td>
        <td class="jm">N2</td>
        <td class="jm">N3</td>
        <td class="jm">N4</td>
        <td class="jm">N5</td>
        <td class="jm">Joker</td>
        <td>Date</td>
    </tr>
<#if bydate??>
    <tr>
        <td bgcolor="#7fffd4">${bydate.drawNum?string["0"]}</td>
        <td bgcolor="#00ffff">${bydate.n1}</td>
        <td bgcolor="#00ffff">${bydate.n2}</td>
        <td bgcolor="#00ffff">${bydate.n3}</td>
        <td bgcolor="#00ffff">${bydate.n4}</td>
        <td bgcolor="#00ffff">${bydate.n5}</td>
        <td bgcolor="#00ffff">${bydate.joker}</td>
        <td bgcolor="#00ffff">${bydate.drawDate?string["dd/MM/yyyy"]}</td>
    </tr>
<#elseif latest??>
<#list latest as l>
    <tr>
        <td bgcolor="#7fffd4">${l.drawNum?string["0"]}</td>
        <td bgcolor="#00ffff">${l.n1}</td>
        <td bgcolor="#00ffff">${l.n2}</td>
        <td bgcolor="#00ffff">${l.n3}</td>
        <td bgcolor="#00ffff">${l.n4}</td>
        <td bgcolor="#00ffff">${l.n5}</td>
        <td bgcolor="#00ffff">${l.joker}</td>
        <td bgcolor="#00ffff">${l.drawDate?string["dd/MM/yyyy"]}</td>
    </tr>
</#list>
</table>
</#if>
</html>