<?php
require_once 'manager_reservation.php';
require_once 'traitement_reservation.php';
class reservation { //Déclaration de la classe réservation
//Déclaration des attributs
  private $_nom;
  private $_mail;
  private $_film;
  private $_nbplaces;

  public function __construct($nom, $mail, $film, $nbplaces, $troisd){
//Partie SET
      $this->setNom($nom);
      $this->setmail($mail);
      $this->setfilm($film);
      $this->setnbplaces($nbplaces);
}

public function setnom($nom){
  if(empty($nom)){
    trigger_error('la variable doit etre un caractere');
    return;
  }
  $this->_nom = $nom;
}
public function setmail($mail){
  if(empty($mail)){
    trigger_error('la variable doit etre un caractere');
    return;
  }
  $this->_mail = $mail;
}
public function setfilm($film){
  if(empty($film)){
    trigger_error('la variable doit etre un caractere');
    return;
  }
  $this->_film = $film;
}
public function setnbplaces($nbplaces){
  if(empty($nbplaces)){
    trigger_error('la variable doit etre un caractere');
    return;
  }
  $this->_nbplaces = $nbplaces;
}
//Partie Get
public function getnom(){
  return $this->_nom;
}
public function getmail(){
  return $this->_mail;
}
public function getfilm(){
  return $this->_film;
}
public function getnbplaces(){
  return $this->_nbplaces;

}
}
?>
