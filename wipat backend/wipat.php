<?php
extract($_POST);
$con = mysqli_connect("localhost","root","password","wipat");
if(!$con)
{
     die("Could not connect".mysqli_error($con));
}
else
{
	$s = "SELECT `name` FROM `details` WHERE `mac` = '$mac' ";
	$ss=mysqli_query($con,$s);
	if (!$ss) die('Error: ' . mysqli_error($con));
	$sss=mysqli_fetch_assoc($ss);
	/*if($sss) {
		echo $sss['name'] . " name already registered. Error";
	}
	else
	{*/
	
	if($r1==3999&&$r2==3999&&$r3==3999)
	{
		
		$sql="SELECT  `id` FROM  `details` WHERE  `mac` ='$mac'";
  
		$result=mysqli_query($con,$sql);
		if (!$result) {
			die('Error: ' . mysqli_error($con));
		}
		 $res=mysqli_fetch_array($result);	
		  if($res)
		{
	   
			$id= $res['id'];
			$sql="INSERT INTO `attendance`(`id`, `att`, `lno`) VALUES ($id,1,1)";
  
			$result2=mysqli_query($con,$sql);
			if (!$result2) {
					die('Error: ' . mysqli_error($con));
			}
			else {
			
				echo "Attendance has been marked.";
			}
		}
		else
		{
			if($name==null) echo "Please register in student register section";
			else
			{
				$sql="INSERT INTO `details`(`name`, `class`, `mac`) VALUES ('$name','$div','$mac')";
				$result=mysqli_query($con,$sql);
				if (!$result) {
					die('Error: ' . mysqli_error($con));
				}
				else
					echo "Student registered successfully.";
			    //echo "Attendance not marked :(" ;
			}
		}
	
		
	}
	//}
	
}

?>