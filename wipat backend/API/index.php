<?php
session_start();
require_once("dbcon.php");
//include("header.php");
extract($_POST);

    $class= $dbh->query("SELECT * from stud_login where stud_id='".$user."' AND pass='".$pass."'");
    //echo "SELECT * from principal_login where User_name='".$id."' AND password='".$pass."'";
   
    if($class->rowCount()>0)
    {
        foreach ($class as $row) {
            $_SESSION['id']=$row['stud_id'];
            $_SESSION['level']=1;
        }
        header("Location: report1.php");
    }
    else{
        echo "<script>alert('Username Or Password Incorrect')</script>";
    }




?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>BVJNIOT Attendance System</title>
<!--Testing css-->

 <link rel="stylesheet" href="css_test/style_header.css" type="text/css" media="screen">
 <link rel="stylesheet" type="text/css" href="css/captcha.css" />
<!-- Themes CSS --->
<!-- Themes CSS --->
<link rel="stylesheet" type="text/css" href="css/main_style.css"/>
<!-- Themes CSS --->
<link rel="stylesheet" type="text/css" href="css/sliderkit-core.css" media="screen, projection" />
<!--Common JS & CSS-->
<script language="javaScript" type="text/javascript" src="js/calendar.js"></script>
<script language="javaScript" type="text/javascript" src="js/calendar1.js"></script>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery.MultiFile.js"></script>

<!-- <script type="text/javascript" src="js/chat.js"></script> -->
<link href="css/calendar.css" rel="stylesheet" type="text/css">
<link href="css/nf.lightbox.css" rel="stylesheet" type="text/css" media="screen" />
<link rel="stylesheet" type="text/css" href="css/ddlevelsfiles/ddlevelsmenu-base.css" />
<link rel="stylesheet" type="text/css" href="css/ddlevelsfiles/ddlevelsmenu-topbar.css" />
<link rel="stylesheet" type="text/css" href="css/site_jui.css" />
<link type="text/css" rel="stylesheet" media="all" href="css/chat.css" />
<!-- For RAC Winter 2013 -->
<link rel="shortcut icon" type="image/x-icon" href="rac_w/images/favicon.ico">
<link rel="stylesheet" type="text/css" href="rac_w/css/rac_main_style.css"/>
 <link href="rac_w/css/calendar.css" rel="stylesheet" type="text/css">
<!-- -->
<!-- <script type="text/javascript" src="enrollment/gpdl/enrollment.js"></script> -->
<!-- <link type="text/css" rel="stylesheet" media="all" href="css/screen.css" /> -->
<!-- Search Institutes-->
<script type="text/javascript" src="js/search_inst.js"></script>
<script type="text/javascript" src="js/master_ctrl.js"></script>
<!-- Affiliation-->
<!-- Eligibility-->


<!-- jQuery library -->
<script type="text/javascript" src="js/jquery-1.3.min.js"></script>
<script type="text/javascript" src="js/jquery-1.6.2.min.js"></script>
<script type="text/javascript" src="css/ddlevelsfiles/ddlevelsmenu.js"> </script>
<!-- jQuery Plugin scripts -->
<script type="text/javascript" src="js/jquery.easing.1.3.min.js"></script>
<script type="text/javascript" src="js/jquery.mousewheel.min.js"></script>
<script type="text/javascript" src="js/NFLightBox.js" ></script>
<script type="text/javascript" src="js/register.js" ></script>
<script type="text/javascript" src="js/andman_register.js" ></script>
<script type="text/javascript" src="js/datepicker.js" ></script>
<script type="text/javascript" src="exemption/exemption.js" ></script>
<!--<script type="text/javascript" src="js/news.js" ></script>-->
<script type="text/javascript" src="copy_case_s/enq_center/validations.js" ></script>
<!-- Messages Jquery-->
<!-- <script type="text/javascript" src="js/messages.js"></script>
<script type="text/javascript" src="js/jquery/jquery.js" ></script>
<script type="text/javascript" src="js/jquery/jquery.MultiFile.js" ></script>   -->
<!-- Slider Kit scripts -->
<script type="text/javascript" src="js/jquery.sliderkit.1.9.2.pack.js"></script>
<!-- Slider Kit launch -->
<script type="text/javascript">
$(window).load(function(){ //$(window).load() must be used instead of $(document).ready() because of Webkit compatibility
// News slider > Vertical
$(".newslider-vertical").sliderkit({
shownavitems:3,
			verticalnav:true,
			navitemshover:true,
			circular:true
		});

		// News slider > Horizontal
		$(".newslider-horizontal").sliderkit({
			auto:false,
			shownavitems:5,
			panelfx:"sliding",
			panelfxspeed:1000,
			panelfxeasing:"easeInOutExpo", //"easeOutExpo", "easeOutCirc", etc.
			mousewheel:true,
			keyboard:true,
			fastchange:false
		});

		// News slider > Minimal
		$(".newslider-minimal").sliderkit({
			auto:true,
			circular:true,
			shownavitems:1,
			panelfx:"sliding",
			panelfxspeed:400,
			panelfxeasing:"easeOutCirc",
			mousewheel:false,
			verticalnav:true,
			verticalslide:true
		});

	});
</script>
<!-- Slider Kit styles -->
 <script type="text/javascript" language="javascript">
$(document).ready(function(){
  // Reset Font Size
var originalFontSize = $('html').css('font-size');
  $(".resetFont").click(function(){
  $('html').css('font-size', originalFontSize);
  });
  // Increase Font Size
  $(".increaseFont").click(function(){
  	var currentFontSize = $('html').css('font-size');
 	var currentFontSizeNum = parseFloat(currentFontSize, 10);
    var newFontSize = currentFontSizeNum*1.1;
	if(newFontSize > 16)
		return false;
	else
		$('html').css('font-size', newFontSize);
	return false;
  });
  // Decrease Font Size
  $(".decreaseFont").click(function(){
  	var currentFontSize = $('html').css('font-size');
 	var currentFontSizeNum = parseFloat(currentFontSize, 10);
    var newFontSize = currentFontSizeNum*0.8;
	if(newFontSize < 8)
		return false;
	$('html').css('font-size', newFontSize);
	return false;
  });
});
</script>

<style>
.resetFont {
	font-size:12px;
}
</style>
</head>
<body>
	<div id="wrapper">
	    	<div id="header">
        	<div class="header">
            	<div ><img src="/rac_w/images/logo.jpg" height="420" width="520"><a href="" title="Maharashtra State Board of Technical Education"> BVJNIOT</a>  <div class="header_right">

                    <div class="header_login_btn" id="ddtopmenubar" >

                     	<div class="clear"></div>
                     </div>
					<script type="text/javascript">
                    ddlevelsmenu.setup("ddtopmenubar", "topbar") //ddlevelsmenu.setup("mainmenuid", "topbar|sidebar")
                    </script>
					<!--Top Drop Down Menu HTML-->
					<ul id="ddsubmenu1" class="ddsubmenustyle">
					<li><a href="index.php?act=inst_login">Student Login</a></li>
						<li><a href="index.php?act=inst_login">Teacher Login</a></li>
					<li><a href="index.php?act=rbte_login">HOD Login</a></li>
					<li><a href="index.php?act=msbte_login">Principal Login</a></li>
					<!-- <li><a href="#url">RAC Login</a></li>
					<li><a href="#url">DC Login</a></li> -->
					</ul>
 				</div>
                <div class="clear"></div>
            </div>
          

            </div>
			<div class="clear"></div>
			            </div>
			   </div>
	   
	<div class="welocme_text">
		<marquee>Welcome to BVJNIOT Online Attendance </marquee>
   </div>
	<div id="sidebar">
                	<div class="sidebarBox">
                    	<div class="box_tilte">Login</div>
                        <div class="box_mid">
                        	<div class="left_links">
                            	<ul>
									<li><a href="index.html">Student Login</a></li>
									<li><a href="teacher.html">Teacher Login</a></li>
									<li><a href="hod.html">HOD Login</a></li>
									<li><a href="principal.html">Principal Login</a></li>
									<!--<li><a href="index.php?act=hall_tkt_login" title="HALL TICKET STAFF Login" class="sprite">HALL TICKET STAFF Login</a></li>-->
                                </ul>
                            </div>
                        </div> 
                    </div>
                    

				   
            </div><div id="content">
<div class="container">

                    <div class="whitebox">
                    		
                            <div class="box_mid" style="min-height:490px; height:auto;">
                            <div style=" margin-top:0px;" class="notebox">

              
				</div><br><br><br><br><br>


                            	<link rel="stylesheet" href="css_test/style_login.css" type="text/css" media="screen">
                            	<div class='form aniamted bounceIn'>
								<div style=" margin-top: -63px;padding-bottom: 30px;"><h3 style="text-align:center">Student Login</h3></div>
                                         <form name="frm" method="post" action="" autocomplete="off">
                                       
                                            	<label>Username: <span>*</span></label>
                                               <input type="text" name="user" maxlength="10" size="15" class="inputbox" id="user" onchange="" value="">

                                            	<label>Password: <span>*</span></label>
                                                <input type="password" name="pass" maxlength="15" size="15" class="inputbox" id="pass" onclick="return validate_blank('username','Please enter username')"/>

												<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>PHP Secure Professional Captcha.</title>
<link href="./css/style.css" rel="stylesheet">

</head>
<body>

<span><input type="submit" name="sub" value="Login" class="btn-blue" onClick="return validate(document.getElementById('uname'),document.getElementById('pass'));"></span>

<!--<form action="" method="post" name="form1" id="form1" >-->

   <!-- <tr>
      <td>&nbsp;</td>
      <td><input name="Submit" type="submit" onclick="return validate();" value="Submit" class="button1"></td>
    </tr>-->
  </table>
<!--</form>-->
</body>
</html> </div>
											<br>



                                        </form>
                                 
								 <div class="box_mid">
					<center><a href="index.php?act=forgot_pass_cand">Forgot Your Password ?</a></center> 
				</div>

                            </div>
                    </div>
</div>
</div>
</div>
<script>
function validate(u1,p1)
{
	if(u1.value=="")
	{
		alert("Please Enter Your Username");
		return false;
	}
	if(p1.value=="")
	{
		alert("Please Enter Your Password");
		return false;
	}
}
</script><div class="clear"></div>
            </div>
        </div>
    </div>

    </div>

    </div>

   </div>
    <div id="footer">&copy; Copyright 2015-2016 All rights reserved. Best viewed at 1024 X 768 resolution. Browser support Mozilla Firefox 2.0 & above , I.E 6 & above. (2) </div>
<style>

.xyz_main1 {

    position:fixed;

    top: 40%;

	left:30%;

	text-align:center;

	margin:0 auto;

	background-color:#FFFFFF;

  }

</style>

 <!--<div class="xyz_main1" id="javascript_support_main">

   <div id="javascript_support" class="success" ><span>&nbsp;&nbsp;<strong style='color:red;'>Your Browser Does not support javascript,Javascript must be enabled in Order to Fill and Confirm E Marksheet. Please read The Instructions carefully in User manual to Enable Java Script.</strong>&nbsp;&nbsp;</span></div>
   </div>

<script language="javascript">

document.getElementById("javascript_support").style.visibility="hidden";

document.getElementById("javascript_support_main").style.visibility="hidden";

</script>-->

</body>
</html>




