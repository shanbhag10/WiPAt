<?php
/**
 * Created by PhpStorm.
 * User: omkar
 * Date: 12-01-2017
 * Time: 19:41
 */
require_once("../dbcon.php");

if($_GET['req']=="subject")
{

    //$class= $dbh->query('SELECT class.*,branch.* from class INNER JOIN branch ON branch.branch_id=class.Branch_id');
    $str="SELECT * from subject where class_id=".$_GET['cid'];

    $class= $dbh->query($str) or die("asd".$dbh->errorInfo());
    $res=$class->fetchAll(PDO::FETCH_ASSOC);
    echo json_encode($res);
    exit;
}

if($_GET['req']=="rep1")
{

    //$class= $dbh->query('SELECT class.*,branch.* from class INNER JOIN branch ON branch.branch_id=class.Branch_id');
    $str="SELECT atten . * , student.*
FROM  `atten` 
INNER JOIN student ON student.rollno = atten.student_id
WHERE atten.Class_id ='".$_GET['cid']."'
AND subj_id ='".$_GET['sid']."'
AND  `date` 
BETWEEN  '".$_GET['start']."' AND '".$_GET['end']."' ORDER BY date,lecture_no,atten.student_id";

    $class= $dbh->query($str) or die("asd".$dbh->errorInfo());
    $res=$class->fetchAll(PDO::FETCH_ASSOC);
    echo json_encode($res);
}

if($_GET['req']=="rep2")
{

     $str="SELECT atten.* from atten 
WHERE atten.Class_id ='".$_GET['cid']."'
AND  `date` 
BETWEEN  '".$_GET['start']."' AND '".$_GET['end']."' ORDER BY date,lecture_no,atten.student_id";
	
    $class= $dbh->query($str) or die("asdf".$dbh->errorInfo());
    $res=$class->fetchAll(PDO::FETCH_ASSOC);
    echo json_encode($res);
}

if($_GET['req']=="rep3")
{

    //$class= $dbh->query('SELECT class.*,branch.* from class INNER JOIN branch ON branch.branch_id=class.Branch_id');
    $str="SELECT atten . * , student.name
FROM  `atten` 
INNER JOIN student ON student.rollno = atten.student_id
WHERE atten.Class_id ='".$_GET['cid']."'
AND atten.student_id='".$_GET['stud']."'
AND  `date` 
BETWEEN  '".$_GET['start']."' AND '".$_GET['end']."' ORDER BY date,lecture_no,atten.student_id";

    $class= $dbh->query($str) or die("asdf".print_r($dbh->errorInfo()));
    $res=$class->fetchAll(PDO::FETCH_ASSOC);
    echo json_encode($res);
}
if($_GET['req']=="rep4")
{

    //$class= $dbh->query('SELECT class.*,branch.* from class INNER JOIN branch ON branch.branch_id=class.Branch_id');
    $str="SELECT atten . * , student.name
FROM  `atten` 
INNER JOIN student ON student.rollno = atten.student_id
WHERE atten.Class_id ='".$_GET['cid']."'
AND atten.student_id='".$_GET['stud']."'
AND  `date` 
BETWEEN  '".$_GET['start']."' AND '".$_GET['end']."' ORDER BY date,lecture_no,atten.student_id";
    $class= $dbh->query($str) or die("asd".$dbh->errorInfo());
    $res=$class->fetchAll(PDO::FETCH_ASSOC);
    echo json_encode($res);
}

if($_GET['req']=="tchr")
{
extract($_GET);
    //$class= $dbh->query('SELECT class.*,branch.* from class INNER JOIN branch ON branch.branch_id=class.Branch_id');
    $str="INSERT INTO `attendance`.`atten` (`student_id`, `date`, `lecture_no`, `topic`, `attendance`, `Class_id`, `subj_id`) VALUES ('$sid', '$date', '$lno', '$topic', '$att', '$cid', '$sid')";
    echo $str;

    $class= $dbh->query($str) or die("asdf".$dbh->errorInfo());

}

