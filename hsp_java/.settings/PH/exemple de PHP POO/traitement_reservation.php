<?php
require_once 'model_reservation.php';
require_once 'manager_reservation.php';
$reservation = new reservation($_POST["nom"], $_POST["mail"], $_POST["film"], $_POST["nbplaces"]);
$co = new Manager();
$co->reservation($reservation);
?>
