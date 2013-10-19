JPA 104
=======

JPA (Java Persistance Api) est une norme Java EE permettant de lier des objets à des données contenu dans une base de données.
Plus exactement, dans toute source de données possédant une implémentation correspondante ...

JPA est essentiellement utilisé pour la manipulation des bases de données relationnelles construites à partir du développement orienté objet en Java. Comme tout ORM, JPA fourni une abstraction d'accès à la base de données. Il n'est donc plus question d'écrire du code SQL "natif" de votre base. Les opérations de création, recherche, mise à jour et suppression des données sont complètement accessibles via des méthodes génériques.

En fonction de la persistance choisie, l'implémentation JPA pour votre base se chargera de traduire ces appels en SQL (ou autre) valide.

# Avant JPA

Il y a fort longtemps dans une galaxie lointaine ...
lorsque l'on souhaitait accéder à une base de données relationnel on utilisait un drivers spécifique pour la base de données visée et on écrivait du code SQL spécifique ou on utilisait les opérations spécifique du drivers.

Puis est arrivé JDBC ... une abstraction java permettant de standardiser un peu les méthodes permettant de manipuler ces bases.
JDBC ceux sont les interfaces et objets disponibles dans le package java.sql.

Un petit exemple avec hsqldb.

HsqlDB est une base de données relationnelle "classique" implémentée en JAVA, pouvant tourner en mémoire.
Elle a au moins trois avantages :
 - elle est simple
 - elle est efficace
 - elle "parle" le SQL :)

Vous voulons gérer une liste d'étudiant (original ...).
Afin de respecter un minimum les bonnes pratiques, nous allons essayer de mettre en place un petit bout de MVC.

* Le modèle c'est notre objet Java "Etudiant".
* La vue sera un petit bout d'interface swing.
* Le contrôleur sera une classe répondant aux interactions de la fenêtre et déclenchant les opérations sur le modèle.

Il y a un "main" dans VueEtudiant permettant de voir à quoi ressemble la vue. Aucun contrôle n'est effectué lorsqu'on lance cette classe.
Le "main" de la classe CtrlEtudiant crée une vue et y ajoute toutes les actions sur les boutons ainsi que les intéractions avec le modèle.
Pour le moment rien ne fonctionne, il faut terminer le code de notre modèle ... A vous de jouer.

Compléter le code de la classe Etudiant afin de faire passer les test "TestEtudiant".
Lorsque tous les tests passent vous devriez être en mesure d'avoir une interface qui fonctionne.
Notre base de données étant stockée en mémoire évidemment, cela ne persiste pas entre deux lancements d'application. Si vous le souhaitez, changer le mode de persistance de hsql dans l'initialisation de la base. Mais là n'est pas le principal.

Nous avons vu comment utiliser l'api JDBC permettant de réaliser des opération SQL sur une base de données.
Bien que SQL se veut un langage "standard", dans la réalité les SGBD ont chacune leur spécificités. Il y a peu de chance que votre modèle Etudiant continue de fonctionner sur une base mySQL si vous décidez de migrer. Les ORM sont là pour cela : ne plus se soucier des opérations de bas niveau qui sont exécuter sur la base mais se concentrer sur les opérations logiques que nous réalisons sur les tuples (enregistrements) d'une base. JPA défini les opérations (et annotations) utilisables du package javax.persistance, il nous faut ensuite une implémentation de cette "norme". Il en existe 2 assez connues : TopLink et Hibernate.

# JPA avec Hibernate

Essayons de mettre en oeuvre une persistance JPA avec l'implémentation Hibernate par dessus une base de données hsql.

