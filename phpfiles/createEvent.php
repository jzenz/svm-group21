<?php

error_reporting(E_ALL);
ini_set('display_errors', 1);

$mysqli = new mysqli('mysql.hostinger.de','u271680072_sw','AGW2fGZet6','u271680072_sw');
$myArray = array();



if ($result = $mysqli->query("SELECT * FROM users WHERE name='" . $_GET["user_name"] . "' AND password='" . $_GET["password"] . "'")) {
	if(mysqli_fetch_array($result) != false)
	{
         
        $mysqli->query("INSERT INTO events (`id`, `name`, `location`, `date`, `desc`, `owner`, `user`) VALUES (NULL,'".$_GET["name"]."','".$_GET["location"]."','".$_GET["date"]."','".$_GET["desc"]."','".$_GET["user_name"]."','')");
        echo $mysqli->error;
		$result->close();
	}
}


$mysqli->close();
?>
							