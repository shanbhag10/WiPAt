<?php
require_once("db.php");

if($_POST['req']=="req1")
{

    
    $str="delete FROM `readings` WHERE position_id='".$_POST['p']."'";

    $class= $dbh->query($str) or die("asdf".print_r($dbh->errorInfo()));

}

if($_POST['req']=="req2")
{

   
    $str="select ssid,mac_id from access_points";

    $class= $dbh->query($str) or die("asdf".print_r($dbh->errorInfo()));
    $res=$class->fetchAll(PDO::FETCH_ASSOC);
    echo json_encode($res);
}

if($_POST['req']=="req3")
{

    
    $str="INSERT INTO `access_points`( `ssid`, `mac_id`) VALUES ('".$_POST['ssid']."','".$_POST['mid']."')";

    $class= $dbh->query($str) or die("asdf".print_r($dbh->errorInfo()));

}

if($_POST['req']=="req4")
{

    
    $str="SELECT distinct position_id FROM `readings`";

    $class= $dbh->query($str) or die("asdf".print_r($dbh->errorInfo()));
    $res=$class->fetchAll(PDO::FETCH_ASSOC);
    echo json_encode($res);
}
if($_POST['req']=="req5")
{

   
    $str="INSERT INTO `readings`(`position_id`, `ssid`, `mac_id`, `rssi`) VALUES ('".$_POST['pid']."','".$_POST['ssid']."','".$_POST['mid']."','".$_POST['rssi']."')";

    $class= $dbh->query($str) or die("asdf".print_r($dbh->errorInfo()));

}


if($_POST['req']=="req6")
{

   
    $str="SELECT access_points.*, readings.rssi, readings.position_id FROM `access_points` INNER JOIN `readings` ON access_points.mac_id=readings.mac_id order by readings.mac_id";

    $class= $dbh->query($str); 
	if($class) {
		if($class->rowCount()!=6)
			die("1");
	}
	else die("asdf".print_r($dbh->errorInfo()));
    $res=$class->fetchAll(PDO::FETCH_ASSOC);
    echo json_encode($res);
}

if($_POST['req']=="req7") //student
{
$str="INSERT INTO `attendance`.`atten` (`student_id`, `date`, `lecture_no`, `topic`, `attendance`, `Class_id`, `subj_id`) VALUES ('".$_POST['student_id']."','".date('Y-m-d')."','".$_POST['lecture_no']."','".$_POST['topic']."','".$_POST['attendance']."','".$_POST['Class_id']."','".$_POST['subj_id']."')";

    $class= $dbh1->query($str) ;
	if($class) die("Attendance has been successfully marked"); 
	else die("1");
    $res=$class->fetchAll(PDO::FETCH_ASSOC);
    echo json_encode($res);
}

if($_POST['req']=="req8") //teacher
{
$str="INSERT INTO `attendance`.`conducted` (`lecture_no`, `date`, `class_id`, `subject_id`) VALUES ('".$_POST['lecture_no']."','".date('Y-m-d')."','".$_POST['Class_id']."','".$_POST['subj_id']."')";

    $class= $dbh1->query($str) or die("asdf".print_r($dbh->errorInfo()));
    $res=$class->fetchAll(PDO::FETCH_ASSOC);
    echo json_encode($res);
}

if($_POST['req']=="req9") //teacher update in db
{
$str="INSERT INTO `details`(`lno`, `classid`, `sub`, `topic`, `start`) VALUES ('".$_POST['lno']."','".$_POST['classid']."','".$_POST['sub']."','".$_POST['topic']."','".$_POST['start']."')";

    $class= $dbh1->query($str);
	if($class) die("Session started successfully"); 
	else
	die("Attendance session for lecture number ".$_POST['lno']." is already running. Please end the session");
    $res=$class->fetchAll(PDO::FETCH_ASSOC);
    echo json_encode($res);
}

if($_POST['req']=="req10") //student update in db
{
$str="INSERT INTO `student`(`rollno`, `name`, `classid`, `mac`) VALUES ('".$_POST['rollno']."','".$_POST['name']."','".$_POST['classid']."','".$_POST['mac']."')";

    $class= $dbh1->query($str);
	if($class) die("Successfully registered");
	else die("This mobile is already registered.");
    $res=$class->fetchAll(PDO::FETCH_ASSOC);
    echo json_encode($res);
}


if($_POST['req']=="req11") //delete session from db
{
$str="DELETE FROM `details` WHERE `lno` = '".$_POST['lno']."' AND `classid`='".$_POST['classid']."'";

    $class= $dbh1->query($str);
	if($class) {
		if($class->rowCount()>0)
			die("Attendance Session for ".$_POST['lno']." has been successfully stopped");
	else 
		die("Invalid lecture ID and/or class ID given. Please provide both lecture no. as well as class name.");
	}
    $res=$class->fetchAll(PDO::FETCH_ASSOC);
    echo json_encode($res);
}


if($_POST['req']=="req12") //retrieve all details to be uploaded by student
{

   
    $str="SELECT * FROM `details` WHERE classid='".$_POST['classid']."'";

    $class= $dbh1->query($str);
	if($class) {
		if($class->rowCount()==0)
			die("No active attendance session");
	}	 
    $res=$class->fetchAll(PDO::FETCH_ASSOC);
    echo json_encode($res);
}

if($_POST['req']=="req13") //retrieve student details to verify mac
{

   
    $str="SELECT mac FROM `student` where mac = '".$_POST['current_mac']."'";

    $class= $dbh1->query($str);
	if($class) {
		if($class->rowCount()==0)
			die("Your mobile device isn't registered. Please register via student registration.");
	}
		else die("asdf".print_r($dbh->errorInfo()));
    $res=$class->fetchAll(PDO::FETCH_ASSOC);
    echo json_encode($res);
}

if($_POST['req']=="req14") //retrieve student details to verify classid
{

   
    $str="SELECT * FROM `student` where mac = '".$_POST['current_mac']."'";

    $class= $dbh1->query($str) or die("asdf".print_r($dbh->errorInfo()));
    $res=$class->fetchAll(PDO::FETCH_ASSOC);
    echo json_encode($res);
}

if($_POST['req']=="req15") //retrieve teacher login details
{

   
    $str="SELECT * FROM `teacher_login` where User_name = '".$_POST['user']."' AND password = '".$_POST['pass']."'";

    $class= $dbh1->query($str);
	if($class) {
		if($class->rowCount()==0)
			die("Incorrect username or password entered.");
		else
			die("1");
		}
	else die("Connection error. ".print_r($dbh->errorInfo()));
    $res=$class->fetchAll(PDO::FETCH_ASSOC);
    echo json_encode($res);
}


