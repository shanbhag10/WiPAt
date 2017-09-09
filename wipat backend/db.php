<?php
/**
 * Created by PhpStorm.
 * User: omkar
 * Date: 12-01-2017
 * Time: 05:39
 */


try {
   
    $dbh = new PDO('mysql:host=localhost;dbname=wipat', "root", "password");
	$dbh1 = new PDO('mysql:host=localhost;dbname=attendance', "root", "password");

} catch (PDOException $e) {
    print "Error!: " . $e->getMessage() . "<br/>";
    die();
}
?>