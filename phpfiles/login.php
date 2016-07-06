<?php

$mysqli = new mysqli('mysql.hostinger.de','u271680072_sw','AGW2fGZet6','u271680072_sw');
$myArray = array();

if ($result = $mysqli->query("SELECT * FROM users WHERE name='" . $_GET["name"] . "' AND password='" . $_GET["password"] . "'")) {
	if(mysqli_fetch_array($result) != false)
	{
        $myArray[] = "login";
		echo json_encode($myArray);
		$result->close();
	}
	else
	{

		$myArray[] = "false";
        echo json_encode($myArray);
	}
}
$mysqli->close();
?>
	