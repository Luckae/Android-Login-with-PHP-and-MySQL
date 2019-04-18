<?php

//class Connect{

//function con(){
DEFINE("DB_HOST", "localhost");
DEFINE("DB_USER", "lawsonlu_lawsonlu");
DEFINE("DB_PASS","youcare12345678");
DEFINE("DB_NAME","lawsonlu_retro");

$con = mysqli_connect(DB_HOST,DB_USER,DB_PASS,DB_NAME);

if(!$con){
    echo 'could not establish connection';
}
//}

//}

// $cone = new Connect();
 //$cone->con();
 
?>