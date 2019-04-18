<?php
include 'connect.php';
if(isset($_POST['btn'])){
$mesg = $_POST['mesg'];

if($mesg === ''){
    echo '<script>alert("Message field is empty")</script>';
} else{
$ins = mysqli_query($con, "INSERT INTO users_questions(questions)VALUES('$mesg')");
if($ins){
    echo '<script>alert("Question logged. Will reply shortly")</script>';
}else{
    echo '<script>alert("Could not send message. Please try agian later")</script>';
}
}
}

?>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Ask doctor</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
      <!-- Compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">

    <!--Fonts dependency -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700|Material+Icons">

<!-- Compiled and minified JavaScript -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>

<style>
@font-face{
    font-family:Jura;
    src:url('fonts/jura.otf');
}
body{
    font-family:Jura;
    font-weight:bold;
}
</style>
</head>
<body onload="checkEmpty()">
<div class="container">
<div class="row">
    <form method="POST" action="ask.php" class="col s12">
      <div class="row">
        <div class="input-field col s6 s12">
          <i class="material-icons prefix">mode_edit</i>
          <textarea name="mesg"  id="msg" onkeyup="checkEmpty();" class="materialize-textarea"></textarea>
          <label for="icon_prefix2">Message</label>
</div>    
</div>
<p style="position:relative;left:50px;top:-20px;" id="showError"></p>
        <div class="row">
            <div class="col s12">
                <center>
        <button class="btn waves-effect waves-light blue lighten-1" id="button" type="submit" name="btn">Submit
    <i class="material-icons right">send</i>
  </button>
  </center>
  </div>
      </div>
    </form>
  </div>
        
</div>
    
</body>

<script>

let btn = document.getElementById("button");
let mesg = document.getElementById("msg");
let showError = document.getElementById("showError");

checkEmpty=()=>{
    if(mesg.value === ''){
        btn.disabled = true;
        
    }else if(mesg.value.length > 400){
        btn.disabled = true;
        showError.innerText = "Characters must not be more than 400";
        showError.style.color = "#b71c1c";
    }else{
        btn.disabled = false;
        mesg.style.backgroundColor = "";
        showError.innerText = "";
    }
 
}


</script>
</html>