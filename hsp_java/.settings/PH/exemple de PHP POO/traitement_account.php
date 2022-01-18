<?php
require 'inc/bootstrap.php';
require_once 'account.php';
App::getAuth()->restrict();
if(!empty($_POST)){

    if(empty($_POST['password']) || $_POST['password'] != $_POST['password_confirm']){ //Vérifier si le mot de passe est correct
        $_SESSION['flash']['danger'] = "Les mots de passes ne correspondent pas"; //Message d'erreur
    }else{
        $user_id = $_SESSION['auth']->id;
        $password= password_hash($_POST['password'], PASSWORD_BCRYPT);
        require_once 'inc/db.php';
		$db->query('UPDATE users SET password = ? WHERE id = ?', [$password, $user->id]); //mettre à jour sur la BDD le mot de passe
        $_SESSION['flash']['success'] = "Votre mot de passe a bien été mis à jour"; //Message de validation
    }

}
require 'inc/header.php';
?>