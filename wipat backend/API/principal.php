<?php
session_start();
require_once("dbcon.php");
//include("header.php");
extract($_POST);

    $class= $dbh->query("SELECT * from principal_login where User_name='".$id."' AND pass='".$pass."'");
    //echo "SELECT * from principal_login where User_name='".$id."' AND password='".$pass."'";
   // echo $class->rowCount();
    if($class->rowCount()>0)
    {
        foreach ($class as $row) {
            $_SESSION['id']=$row['User_name'];
            $_SESSION['level']=4;
        }
        header("Location: report1.php");
    }
    else{
        echo "<script>alert('Username Or Password Incorrect')</script>";
    }




?>

