Jsp 102
=======

Jsp est un équivalent java des fichiers asp ou php.
C'est une manière de mettre du java, exécuté coté serveur, dans une page html.
Le jsp n'est pas interprété, il est en fait traduit en java puis compilé (généralement au premier appel de la page), et ensuite exécuté.
C'est le résultat de cette exécution qui donne le code html renvoyé au navigateur.

Nous allons encore une fois utiliser jetty pour exécuter quelques fichiers jsp.
Nous utiliserons "jasper" comme implémentation du moteur de jsp.

### Configuration de notre environnement

Afin d'exécuter du jsp, il faut :

- Configurer un Context "Web"
- écrire un fichier jsp
- Avoir un compilateur jsp dans le classpath avec toutes ses dépendances
- lancer le serveur

### Lancer le serveur

- Modifier et lancer le script run.sh ou Lancer Run.java dans eclipse après avoir importé le projet.
- Rendez vous sur http://localhost:8080/index.jsp

