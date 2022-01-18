<?php
require_once 'model_reservation.php';
require_once 'traitement_reservation.php';
class Manager{ //Déclaration de la classe Manager
public function reservation($donnee){

      $bdd=new PDO('mysql:host=localhost;dbname=cinemapoo;charset=utf8', 'root', ''); //Connexion à la BDD
    $req=$bdd->prepare('INSERT into reservations (nom, mail, film, nbplaces) VALUES(:nom, :mail, :film, :nbplaces)'); //Préparation de la table réservations avec les valeurs de la table
    $req->execute(array('nom'=>$donnee->getnom(),'mail'=>$donnee->getmail(), 'film'=>$donnee->getfilm(), 'nbplaces'=>$donnee->getnbplaces()); //Execution des requêtes
    $a = $req->fetch();
	//Conditions de redirection
    if ($a ==true){
     header("location: index.php");
    }
    else{
     header("location: https://www.paypal.com/fr/signin"); //Redirection vers le lien de la page de paiement
    }

          }
}

?>
