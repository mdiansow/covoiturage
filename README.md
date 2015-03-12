# README #

Ce répository contient les projets
1. covoiturage : Serveur --JPA, Services (Jax-rs) --, Client --AngularJS et GWT.
2. Play!: Le projet play va utiliser la stack serveur du projet covoiturage.

Pour faire tourner tout ça, il faut:
1. Lancer le script './run-hsqldb-server' avec les droits qu'il faut.
2. Puis celui de la base de données HSQL, soit le fichier './show-hsqldb'
3. Enfin, le fichier './run-project-war.sh'. C'est ce dernier qui lancera l'application.

!!!! ATTENTION !!!
Pour tester:
1. Les services rest, utiliser les url du type 'http://localhost:8080/rest/'. Les paths sont dans les classes de services EventServicesImpl et PersonServicesImpl
2. Le client GWT, page accessible à l'adresse 'http://localhost:8080/carshare.html'
3. Le client AngularJS, page accessible à l'adresse 'http://localhost:8080/angular/index.html'


### What is this repository for? ###



* Auteur : Jeremie Cotiniaux & Mamadou Dian Sow