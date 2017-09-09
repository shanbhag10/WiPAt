<?php
session_start();
/**
 * Created by PhpStorm.
 * User: omkar
 * Date: 12-01-2017
 * Time: 06:07
 */
 if($_SESSION['level']<2)
 {
 header("location:report1.php");
 }
require_once("dbcon.php");
include("header.php");
//echo "here";
//print_r($_GET);
if(isset($_GET['subm']))
{
    extract($_SESSION);
    $qu="INSERT INTO `attendance`.`conducted` (`lecture_no`, `date`, `class_id`, `subject_id`) VALUES ('$lno', '$date', '$class', '$sub')";
    $dbh->query($qu) or die("error".print_r($dbh->errorInfo()));
    // echo "Marking for".$date.$lno.$topic.$class.$sub."<br>";
    foreach ($_GET as $key => $value)
    {
        if($key!="subm") {
            $str="INSERT INTO `attendance`.`atten` (`student_id`, `date`, `lecture_no`, `topic`, `attendance`, `Class_id`, `subj_id`) VALUES ('$key', '$date', '$lno', '$topic', '$value', '$class', '$sub')";
            //echo $str;
echo "<br>";
           $dbh->query($str) or die("error".print_r($dbh->errorInfo()));
            $str="";
            

        }
    }
    ?>
    <script type="text/javascript">
alert("attendance marked");
window.location.href = "report1.php";
</script>
    <?php
}
if(isset($_GET['submit'])) {

    $pieces = explode(",", $_GET['class']);
    $_SESSION['class'] = $pieces[1];
    $_SESSION['sub'] = $pieces[0];
    $_SESSION['date'] = $_GET['date'];
    $_SESSION['topic'] = $_GET['topic'];
    $_SESSION['lno'] = $_GET['lno'];
    $str = "SELECT  * FROM student_details WHERE  class_id=" . $_SESSION['class'];

    $class = $dbh->query($str) or die("asdf" . $dbh->errorInfo());
    $res = $class->fetchAll(PDO::FETCH_ASSOC);
//print_r($res);
//exit;
}
?>

<!<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
</head>
<body>

</body>
</html>>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Report For Class</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.6 -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.13/css/dataTables.bootstrap.min.css">
    <script src="plugins/custom/attendance.js"></script>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="dist/css/AdminLTE.min.css">
    <!-- AdminLTE Skins. Choose a skin from the css/skins
         folder instead of downloading all of them to reduce the load. -->
    <link rel="stylesheet" href="dist/css/skins/_all-skins.min.css">
    <!-- iCheck -->
    <link rel="stylesheet" href="plugins/iCheck/flat/blue.css">
    <!-- Morris chart -->
    <link rel="stylesheet" href="plugins/morris/morris.css">
    <!-- jvectormap -->
    <link rel="stylesheet" href="plugins/jvectormap/jquery-jvectormap-1.2.2.css">
    <!-- Date Picker -->
    <link rel="stylesheet" href="plugins/datepicker/datepicker3.css">
    <!-- Daterange picker -->
    <link rel="stylesheet" href="plugins/daterangepicker/daterangepicker.css">
    <!-- bootstrap wysihtml5 - text editor -->
    <link rel="stylesheet" href="plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.13/css/jquery.dataTables.min.css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>


<body>

<section class="content-header">
    <h1>
        Class Wise Attendance
        <small>Select Subject</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        <li class="active">Class Wise Attendance</li>
    </ol>
</section>
<section class="content">

<?php if(!isset($_GET['submit'])) { ?>
    <form role="form" id="target" name="target">
        <div class="box-body">
            <div class="form-group">
                <select name="class" id="class">
                    <option value="">Select Subject & Class</option>
                    <?php
                    $class = $dbh->query('SELECT subject. * , class. * , branch.branch_name
FROM subject
INNER JOIN class ON subject.class_id = class.class_id
INNER JOIN branch ON branch.branch_id = class.Branch_id
WHERE subject.teacher_id =1');
                    foreach ($class as $row) {
                        echo "<option value=" . $row['subject_id'] . "," . $row['class_id'] . ">" . $row['Subject_name'] . "   " . $row['Class'] . "-" . $row['branch_name'] . "</option>";
                        //  echo ($row['Class']." ".$row['branch_name']);
                    }

                    ?>
                </select>
            </div>

            <div class="form-group">
                <div class=" box-tools">


                    Topic covered<input type="text" name="topic">
                    Date <input type="date" name="date">
                    Lecture Number <input type="number" name="lno">
                </div>

            </div>

        </div>
        <!-- /.box-body -->

        <div class="box-footer">
            <button type="submit" name="submit" class="btn btn-primary">Submit</button>
        </div>
    </form>
    <br><br>
    <?php
}
   else if(isset($_GET['submit'])) {
    ?>
        <form >
    <table id="example" >
        <thead>
        <tr>
            <th>Student ID</th>
            <th>Student </th>
            <th>Absent</th>
            <th>Present</th>



        </tr>
        </thead>

       <?php
       $index=0;

        foreach($res as $row)
        {

        echo "<tr> <td>".$row['student_id']."</td><td>";
                $cl=$row['name'];
                echo $cl;

                ?>

            <td>
                <input type='radio' name='<?php echo $row['student_id'] ?>' value='0' >
            </td>
            <td>
                <input type='radio' name='<?php echo $row['student_id']?>' value='1' checked="true">
            </td>
        </tr>
        <?php
        $index=$index+1;
        }
        ?>


        <tfoot>
        <tr>
            <th>Student ID</th>
            <th>Student </th>
            <th>Absent</th>
            <th>Present</th>
        </tr>
        </tfoot>

    </table>
            <div class="box-footer">
                <button type="submit" name="subm" class="btn btn-primary">Submit</button>
            </div>
            </form>
    <?php } ?>
</section>


<div class="control-sidebar-bg"></div>
<!-- jQuery 2.2.3 -->
<!-- jQuery 2.2.3 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<!-- jQuery UI 1.11.4 -->
<script src="https://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
<script>
    $.widget.bridge('uibutton', $.ui.button);
</script>
<!-- Bootstrap 3.3.6 -->
<script src="bootstrap/js/bootstrap.min.js"></script>
<!-- Morris.js charts -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js"></script>
<script src="plugins/morris/morris.min.js"></script>
<!-- Sparkline -->
<script src="plugins/sparkline/jquery.sparkline.min.js"></script>
<!-- jvectormap -->
<script src="plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
<script src="plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
<!-- jQuery Knob Chart -->
<script src="plugins/knob/jquery.knob.js"></script>
<!-- daterangepicker -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.11.2/moment.min.js"></script>
<script src="plugins/daterangepicker/daterangepicker.js"></script>
<!-- datepicker -->
<script src="plugins/datepicker/bootstrap-datepicker.js"></script>
<!-- Bootstrap WYSIHTML5 -->
<script src="plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script>
<!-- Slimscroll -->
<script src="plugins/slimScroll/jquery.slimscroll.min.js"></script>
<!-- FastClick -->
<script src="plugins/fastclick/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="dist/js/app.min.js"></script>
<!-- AdminLTE dashboard demo (This is only for demo purposes) -->

<!-- AdminLTE for demo purposes -->
<script src="dist/js/demo.js"></script>

<script src="https://cdn.datatables.net/1.10.13/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.10.13/js/dataTables.bootstrap.min.js"></script>

<script src="https://cdn.datatables.net/buttons/1.2.4/js/dataTables.buttons.min.js"></script>
<script src="http://cdn.datatables.net/buttons/1.2.2/js/buttons.print.min.js"></script>




<script src="http://cdnjs.cloudflare.com/ajax/libs/jszip/2.5.0/jszip.min.js"></script>
<script src="http://cdn.rawgit.com/bpampuch/pdfmake/0.1.18/build/pdfmake.min.js"></script>
<script src="http://cdn.rawgit.com/bpampuch/pdfmake/0.1.18/build/vfs_fonts.js"></script>
<script src="http://cdn.datatables.net/buttons/1.2.4/js/buttons.html5.min.js"></script>



</body>
</html>