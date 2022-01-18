<?php
require_once 'inc/bootstrap.php';
require_once 'register.php';

// Je veux récupérer le premier utilisateur

if(!empty($_POST)){

    $errors = array();

    $db = App::getDatabase();
    $validator = new Validator($_POST);
    $validator->isAlpha('username', "Votre pseudo n'est pas valide (alphanumérique)");
    if($validator->isValid()){
        $validator->isUniq('username', $db, 'users', 'Ce pseudo est déjà pris');
    }
    $validator->isEmail('email', "Votre email n'est pas valide");
    if($validator->isValid()){
        $validator->isUniq('email', $db, 'users', 'Cet email est déjà utilisé pour un autre compte');
    }
    $validator->isConfirmed('password', 'Vous devez rentrer un mot de passe valide');

    if($validator->isValid()){

        App::getAuth()->register($db, $_POST['username'], $_POST['password'], $_POST['email']);
        Session::getInstance()->setFlash('success', 'Un email de confirmation vous a été envoyé pour valider votre compte');
        App::redirect('login.php');

    } else {
        $errors = $validator->getErrors();
    }


}
?>