<?php
class App{ //Déclaration de la classe App

    static $db = null; //la valeur de basse de $db est nulle

    static function getDatabase(){ //Obtenir les informations de connexion de la BDD
        if(!self::$db){
            self::$db = new Database('root', '', 'cinemapoo');
        }
        return self::$db;
    }

    static function getAuth(){
        return new Auth(Session::getInstance(), ['restriction_msg' => 'C est bete... il faut te connecter !']); //Message d'erreur en cas de non connexion au site
    }

    static function redirect($page){
        header("Location: $page");
        exit();
    }

}
?>
