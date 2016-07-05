<?php
$mysqli = new mysqli('mysql.hostinger.de','u271680072_sw','AGW2fGZet6','u271680072_sw');
$myArray = array();
if ($result = $mysqli->query("SELECT * FROM events")) {

    while($row = $result->fetch_array(MYSQL_ASSOC)) {
            $myArray[] = $row;
    }
    echo json_encode($myArray);
}

$result->close();
$mysqli->close();
?>
