<?php
try {

    $dbh = new PDO('mysql:host=localhost;dbname=attendance', "root", "password");
    
} 
  catch(Exception $e)
    {
        echo "There was an error connecting to the database";
    }

?>
