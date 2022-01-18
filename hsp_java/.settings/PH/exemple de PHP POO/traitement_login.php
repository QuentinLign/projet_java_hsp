<?php
require 'inc/bootstrap.php';
require_once 'login.php';
$auth = App::getAuth();
$db = App::getDatabase();
$auth->connectFromCookie($db);
if($auth->user()){
    App::redirect('reservation.php');
}
if(!empty($_POST) && !empty($_POST['username']) && !empty($_POST['password'])) { //Verifier le login et le mot de passe de l'utilisateur
    $user = $auth->login($db, $_POST['username'], $_POST['password'], isset($_POST['remember']));
    $session = Session::getInstance();
    if($user){
        $session->setFlash('success', 'Vous êtes maintenant connecté'); //Message de confirmation de connexion
        App::redirect('reservation.php');
    }else{
        $session->setFlash('danger', 'Identifiant ou mot de passe incorrecte'); //Message d'erreur
    }
}
?>
