<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <style type="text/css">
        table.jm{
            width: 30%;
            border: 1px solid green;
            border-collapse: collapse;
        }
        table.jm td {
            border: 1px solid green;
            padding: 1px;
            width: 40px;
            height: 50px;
            vertical-align: bottom;
        }
        input[type=text]
        {
            width: 35px;
        }
        input[type=text].two
        {
            width: 80px;
        }
        body{
            font-size: 12px;
        }
        p{
            font-size: 17px;
        }
    </style>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
    <#--<link rel="stylesheet" href="css/jquery-ui.css">-->
    <script src="//code.jquery.com/jquery-1.10.2.js"></script>
    <#--<script src="js/jquery-1.10.2.js"></script>-->
    <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
    <#--<script src="js/jquery-ui.js"></script>-->
    <#--<link rel="stylesheet" href="/resources/demos/style.css">-->
    <script>
        $(function() {
            /*$( "#datepicker" ).datepicker();*/
            $( "#datepicker" ).datepicker({
                dateFormat: "dd/mm/yy",
                numberOfMonths: [ 1, 2 ]
            });
            $("#datepicker").datepicker("setDate", new Date());
        });
    </script>
</head>
<body>
<p> Last Draw Num: ${lastDrawNum}</p>
<p> Add new result:</p>
<form name="newResult" action="add/new" method="post">
    <table class="jm">
        <tr class="jm">
            <td class="jm">#</td>
            <td class="jm">N1</td>
            <td class="jm">N2</td>
            <td class="jm">N3</td>
            <td class="jm">N4</td>
            <td class="jm">N5</td>
            <td class="jm">Joker</td>
            <td>Date</td>
        </tr>
        <tr>
            <td class="jm"><input type="text" name="drawNum" maxlength="5"/></td>
            <td class="jm"><input type="text" name="n1" maxlength="2"/></td>
            <td class="jm"><input type="text" name="n2" maxlength="2"/></td>
            <td class="jm"><input type="text" name="n3" maxlength="2"/></td>
            <td class="jm"><input type="text" name="n4" maxlength="2"/></td>
            <td class="jm"><input type="text" name="n5" maxlength="2"/></td>
            <td class="jm"><input type="text" name="joker" maxlength="2"/></td>
            <td><input class="two" type="text" id="datepicker" name="drawDate" maxlength="10"/></td>
        </tr>
        <tr class="jm">
            <td class="jm" colspan="8" align="middle"><input type="submit" value="Insert"/></td>
        </tr>
    </table>
</form>
</body>

</html>