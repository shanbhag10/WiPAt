<?php
extract($_POST);
echo $occ;


?>




<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<form method="post" action="">
Name <input type="text" name="username">
   Password <input type="password" class="makebold" id="" name="password">
   <br> Gender
    <input type="radio" name="gender" value="male" checked> Male<br>
    <input type="radio" name="gender" value="female"> Female<br>
    <input type="radio" name="gender" value="other"> Other
DOB <input type="date" name="dob">
    Occupation<select name="occ" class="makebold">
    <option value="1">One</option>
    <option value="2">Two</option>
    </select>
   Mobile <input type="number" name="mobile">
    Address<textarea name="add"></textarea>
    <input type="submit" name="sub">
</form>

</body>
</html>