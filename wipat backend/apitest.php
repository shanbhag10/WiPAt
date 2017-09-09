<?php
require_once("db.php");

if($_GET['req']=="req1")
{

    
    $str="delete FROM `readings` WHERE position_id='".$_GET['p']."'";

    $class= $dbh->query($str) or die("asdf".print_r($dbh->errorInfo()));

}

if($_GET['req']=="req2")
{

   
    $str="select ssid,mac_id from access_points";

    $class= $dbh->query($str) or die("asdf".print_r($dbh->errorInfo()));
    $res=$class->fetchAll(PDO::FETCH_ASSOC);
    echo json_encode($res);
}

if($_GET['req']=="req3")
{

    
    $str="INSERT INTO `access_points`( `ssid`, `mac_id`) VALUES ('".$_GET['ssid']."','".$_GET['mid']."')";

    $class= $dbh->query($str) or die("asdf".print_r($dbh->errorInfo()));

}

if($_GET['req']=="req4")
{

    
    $str="SELECT distinct position_id FROM `readings`";

    $class= $dbh->query($str) or die("asdf".print_r($dbh->errorInfo()));
    $res=$class->fetchAll(PDO::FETCH_ASSOC);
    echo json_encode($res);
}
if($_GET['req']=="req5")
{

   
    $str="INSERT INTO `readings`(`position_id`, `ssid`, `mac_id`, `rssi`) VALUES ('".$_GET['pid']."','".$_GET['ssid']."','".$_GET['mid']."','".$_GET['rssi']."')";

    $class= $dbh->query($str) or die("asdf".print_r($dbh->errorInfo()));

}


if($_GET['req']=="req6")
{

   
 
    $str="SELECT access_points.*, readings.rssi, readings.position_id FROM `access_points` INNER JOIN `readings` ON access_points.mac_id=readings.mac_id order by readings.mac_id";

    $class= $dbh->query($str) or die("asdf".print_r($dbh->errorInfo()));
    $res=$class->fetchAll(PDO::FETCH_ASSOC);
    echo json_encode($res);
}


if($_GET['req']=="req7") //student
{
$str="INSERT INTO `attendance`.`atten` (`student_id`, `date`, `lecture_no`, `topic`, `attendance`, `Class_id`, `subj_id`) VALUES ('".$_POST['student_id']."','".date('Y-m-d')."','".$_POST['lecture_no']."','".$_POST['topic']."','".$_POST['attendance']."','".$_POST['Class_id']."','".$_POST['subj_id']."')";

    $class= $dbh1->query($str) ;
	if($class) die("Attendance has been successfully marked"); 
	else die("1");
    $res=$class->fetchAll(PDO::FETCH_ASSOC);
    echo json_encode($res);
}

if($_GET['req']=="req8") //teacher
{
$str="INSERT INTO `attendance`.`conducted` (`lecture_no`, `date`, `class_id`, `subject_id`) VALUES ('".$_GET['lecture_no']."','".date('Y-m-d')."','".$_GET['Class_id']."','".$_GET['subj_id']."')";

    $class= $dbh1->query($str) or die("asdf".print_r($dbh->errorInfo()));
    $res=$class->fetchAll(PDO::FETCH_ASSOC);
    echo json_encode($res);
}