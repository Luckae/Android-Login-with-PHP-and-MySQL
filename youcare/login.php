<?php
 include ('connect.php');
if($_SERVER['REQUEST_METHOD']=='POST'){
    $email = $_POST['email'];
    $password = $_POST['password'];
    
    $Sql_Query = "select * from users where user_email = '$email' and user_password = '$password' ";
    
    $check = mysqli_fetch_array(mysqli_query($con,$Sql_Query));
    
    if(isset($check)){
    
    echo "Data Matched";
    }
    else {
    echo "Invalid Username or Password Please Try Again";
    }
    } else {
    echo "Check Again";
    }

   // mysqli_close($con);

   






?>