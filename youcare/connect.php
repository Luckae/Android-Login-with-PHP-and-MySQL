<?php

//class Connect{

//function con(){
DEFINE("DB_HOST", "localhost");
DEFINE("DB_USER", "yourusername");
DEFINE("DB_PASS","yourpassword");
DEFINE("DB_NAME","yourdatabase name");

$con = mysqli_connect(DB_HOST,DB_USER,DB_PASS,DB_NAME);

if(!$con){
    echo 'could not establish connection';
}
//}

//}

// $cone = new Connect();
 //$cone->con();
 
?>
