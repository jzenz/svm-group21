<?php

$mysqli = new mysqli('mysql.hostinger.de','u271680072_sw','AGW2fGZet6','u271680072_sw');
$myArray = array();



if ($result = $mysqli->query("SELECT * FROM users WHERE name='" . $_GET["name"] . "'")) {
	if(mysqli_fetch_array($result) != false)
	{
                $myArray[] = "exists";
		echo json_encode($myArray);
		$result->close();
	}
	else
	{
		$mysqli->query("INSERT INTO users (name,password) VALUES ('".$_GET["name"]."','".$_GET["password"]."')");
$myArray[] = "created";
                echo json_encode($myArray);
	}
}


$mysqli->close();
?>
			