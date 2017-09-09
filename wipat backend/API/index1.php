<!DOCTYPE html>
<html>

<?php
extract($_POST);
include("db.php");
if(isset($sub))
{
  $class= $dbh->query("SELECT * from stud_login where stud_id='".$user."' AND pass='".$pass."'");

   //echo $class->rowCount();
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



}


?>

<head>
  <meta charset="UTF-8">
  <title>Sign-Up/Login Form</title>
  <link href='http://fonts.googleapis.com/css?family=Titillium+Web:400,300,600' rel='stylesheet' type='text/css'>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">

  
      <link rel="stylesheet" href="css/style1.css">

  
</head>

<body>

  <div class="form">
      
      <ul class="tab-group">
     <h1> BVJNIOT ATTENDANCE SYSTEM</h1><img style='height: 50%; width: 100%; object-fit: contain' src="bvjniot.png">
        <li class="tab active"><a href="#student">Student</a></li>
        <li class="tab"><a href="#teacher">Teacher</a></li>
         <li class="tab "><a href="#hod">HOD</a></li>
        <li class="tab"><a href="#princ">Principal</a></li>
      </ul>
      
      <div class="tab-content">
        <div id="student">   
          <h1>Log IN as Student</h1>
          
          <form action="" method="post">
          
            <div class="field-wrap">
            <label>
              User Name<span class="req">*</span>
            </label>
            <input type="text" name="user" required autocomplete="off"/>
          </div>
          
          <div class="field-wrap">
            <label>
              Password<span class="req">*</span>
            </label>
            <input type="password"name="pass" required autocomplete="off"/>
          </div>
          
          <p class="forgot"><a href="#">Forgot Password?</a></p>

            <button type="submit" name="sub1" class="button button-block"/>Log In</button>
          
          </form>
        </div>
        </div>
        
       
        
         <div class="tab-content">
        <div id="teacher">   
          <h1>Log IN as Teacher</h1>
          
          <form action="teacher.php" method="post">
          
            <div class="field-wrap">
            <label>
              User Name<span class="req">*</span>
            </label>
            <input type="text" name="id" required autocomplete="off"/>
          </div>
          
          <div class="field-wrap">
            <label>
              Password<span class="req">*</span>
            </label>
            <input type="password"name="pass" required autocomplete="off"/>
          </div>
          
          <p class="forgot"><a href="#">Forgot Password?</a></p>

            <button type="submit" name="sub1" class="button button-block"/>Log In</button>
          
          </form>
        </div>
        </div>
        
        
        
         <div class="tab-content">
        <div id="hod">   
          <h1>Log IN as HOD</h1>
          
          <form action="HOD.php" method="post">
          
            <div class="field-wrap">
            <label>
              User Name<span class="req">*</span>
            </label>
            <input type="text" name="id" required autocomplete="off"/>
          </div>
          
          <div class="field-wrap">
            <label>
              Password<span class="req">*</span>
            </label>
            <input type="password"name="pass" required autocomplete="off"/>
          </div>
          
          <p class="forgot"><a href="#">Forgot Password?</a></p>

            <button type="submit" name="sub1" class="button button-block"/>Log In</button>
          
          </form>
        </div>
        
        
        
        
        
         <div class="tab-content">
        <div id="princ">   
          <h1>Log IN as Principal</h1>
          
          <form action="principal.php" method="post">
          
            <div class="field-wrap">
            <label>
              User Name<span class="req">*</span>
            </label>
            <input type="text" name="id" required autocomplete="off"/>
          </div>
          
          <div class="field-wrap">
            <label>
              Password<span class="req">*</span>
            </label>
            <input type="password"name="pass" required autocomplete="off"/>
          </div>
          
          <p class="forgot"><a href="#">Forgot Password?</a></p>

            <button type="submit" name="sub1" class="button button-block"/>Log In</button>
          
          </form>
        </div>
        </div>
        
      
  <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>

    <script src="js/index.js"></script>

</body>
</html>
