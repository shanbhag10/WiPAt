<?php
session_start();
require_once("dbcon.php");
//include("header.php");
extract($_POST);

    $class= $dbh->query("SELECT * from hod_login where User_name='".$id."' AND password='".$pass."'");
    //echo "SELECT * from teacher_login where User_name='".$user."' AND password='".$pass."'";
   
    if($class->rowCount()>0)
    {
        foreach ($class as $row) {
            $_SESSION['id']=$row['id'];
            $_SESSION['level']=3;
        }
        header("Location: report1.php");
    }
    else{
        echo "<script>alert('Username Or Password Incorrect')</script>";
    }




?>
