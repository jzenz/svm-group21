<?php

error_reporting(E_ALL);
ini_set('display_errors', 1);

$mysqli = new mysqli('mysql.hostinger.de','u271680072_sw','AGW2fGZet6','u271680072_sw');
$myArray = array();



if ($result = $mysqli->query("SELECT * FROM users WHERE name='" . $_GET["user_name"] . "' AND password='" . $_GET["password"] . "'")) {
	if(mysqli_fetch_array($result) != false)
	{
         
        $mysqli->query("UPDATE events SET `name`='".$_GET["name"]."',`location`='".$_GET["location"]."',`date`='".$_GET["date"]."',`desc`='".$_GET["desc"]."',`owner`='".$_GET["user_name"]."' WHERE `id`=".$_GET["id"]. "AND `owner`='".$_GET["user_name"]."';");
        echo $mysqli->error;
		$result->close();
	}
}


$mysqli->close();
?>
							
