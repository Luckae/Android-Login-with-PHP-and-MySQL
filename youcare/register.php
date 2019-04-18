<?php 
//if($_SERVER['REQUEST_METHOD'] == 'GET'){
include('connect.php');

$F_name = mysqli_real_escape_string($con, $_POST['F_name']);
 $L_name = mysqli_real_escape_string($con,$_POST['L_name']);
 $email = mysqli_real_escape_string($con,$_POST['email']);
 $phone = mysqli_real_escape_string($con,$_POST['Phone_name']);
 $country = mysqli_real_escape_string($con,$_POST['Country_name']);
 $state = mysqli_real_escape_string($con,$_POST['State_name']);
 $date = mysqli_real_escape_string($con,$_POST['Date_name']);
 $password = $_POST['password'];
 $radio = $_POST['Radio_name'];
 

 $CheckSQL = "SELECT * FROM users WHERE user_email='$email'";
 
 $CheckPhone = "SELECT * FROM users WHERE user_email='$phone'";
 
 $check_phone = mysqli_fetch_array(mysqli_query($con,$CheckPhone));
 
 $check = mysqli_fetch_array(mysqli_query($con,$CheckSQL));
 
 if(isset($check)){

 echo 'Email Already exists';

 }
 else if(isset($check_phone)){
     echo 'Phone number already exists';
 }
else{ 
$Sql_Query = "INSERT INTO users (first_name, last_name, user_email,phone,country,state,date, user_password,gender) values ('$F_name','$L_name','$email', '$phone', '$country', '$state', '$date', '$password', '$radio')";

 if(mysqli_query($con,$Sql_Query))
{
 echo 'Registration Successful';
}
else
{
 echo 'Something is wrong';
 }
 }

 mysqli_close($con);
//}

?>

<html>
    <head>
        <title>Registration check</title>
    </head>
    <body>
        <form method="GET" action="register.php" >
            <input type="text" name="F_name" placeholder="first name"/> <br/><br/>
            <input type="text" name="L_name" placeholder="last name"/> <br/><br/>
            <input type="email" name="email" placeholder="email"/> <br/><br/>
            <input type="text" name="Phone_name" placeholder="phone"/> <br/><br/>
            <input type="text" name="Country_name" placeholder="country"/> <br/><br/>
            <input type="text" name="State_name" placeholder="state"/> <br/><br/>
            <input type="text" name="Date_name" placeholder="date"/> <br/><br/>
            <input type="password" name="password" placeholder="password"/> <br/><br/>
            
            <input type="radio" value="male" name="Radio_name"/>male <br/><br/>
             <input type="radio" value="female" name="Radio_name"/>female <br/><br/>
              <input type="radio" value="others" name="Radio_name"/>others<br/><br/>
            
            <input type="submit" value="submit" />
        </form>
    </body>
    
</html>