Servlet 101
===========

Pour débuter, nous allons utiliser Jetty.
Jetty est un serveur Web écrit en Java offrant, en autre, un conteneur de servlet.
Ce serveur est, de mon point de vue, facile à manipuler, léger et rapide.
Cela en fait un excellent outil pour développer un site web à base de servlet.

Pré-requis
----------

- [ ] Installer java JDK 7
- [ ] Récupérer Jetty

Recommandé
----------

- [ ] Installer Eclipse
- [ ] commande curl et lecture du man :)

Par où commencer ?
------------------

Essayons de lancer le serveur et regardons ce qu'il se passe.

### Lancement depuis eclipse

- File / Import / General: existing project into workspace
- Sélectionner le répertoire 'Servlet101'
- Finish
- Ouvrir src/fr/eservice/server/Run.java
- Clic droit / Run As / Java Application
- Pour arrêter le serveur : cliquer sur le bouton stop de la console

### Lancement depuis la console

- Vérifier la version de java atteignable par votre shell: java -version
java version "1.7.0_25"
Java(TM) SE Runtime Environment (build 1.7.0_25-b15)
Java HotSpot(TM) 64-Bit Server VM (build 23.25-b01, mixed mode)
- Au besoin configurer votre shell pour ajouter le chemin de votre SDK dans le PATH
- Vérifier que le fichier "run.sh" possède les droits d'exécution
- Pour arrêter le serveur : Ctrl+C dans la console

### Notre première requête au serveur

Normalement notre serveur web écoute maintenant sur le port 8080 ...

- Dans une console :
curl localhost:8080
- Ou la même chose dans un navigateur mais c'est moins fun ;)

Hello World !

### Envoyer un paramètre au serveur

Il y a deux manières d'envoyer des paramètres:

- en GET, dans l'url http://www.server.com/page?parametre=valeur
- en POST, dans ce cas la requête transmise au serveur contiendra un contenu généralement encodé en "application/x-www-form-urlencoded"

Tester l'envoie de paramètre.

- Lancer le serveur
- Dans une console, essayer
curl localhost:8080/circ
C'est une requête (GET) sans paramètre
- Essayer
curl localhost:8080/circ?rayon=15.23
C'est une requête sans contenu avec un paramètre GET 'circ' dont la valeur est '15.23'
- Essayer
curl -d rayon=15.23 localhost:8080/circ
Cette fois ci curl envoie une requête 'POST' avec un contenu. Un seul paramètre est envoyé.
Pour voir la requête executée et la réponse reçue :
curl --trace-ascii - -d rayon=15.23 localhost:8080/circ
- Relisez le code de la servlet Circonference
- Complétez le code pour afficher réellement la circonférence d'un cercle

### Lire les paramètres d'une requête

Généralement les servlets sont appelées depuis un navigateur, il convient donc d'y écrire du html qui pourra s'afficher dans celui-ci.

- Compléter la méthode doGet de FormGreet :
Ajouter un champ de saisi du nom dont le paramètre sera nommé "name"
- Compléter la fonction doPost :
La fonction javax.servlet.http.HttpServletRequest.getParameter permet de lire le contenu d'un paramètre GET ou POST d'une requête Http.
Utilisez cette fonction dans la servlet FormGreet.doPost pour lire le paramètre "name" et écrire "Bonjour " + name + " !".
La réponse doit être en html et le nom en gras. Si aucun nom n'est saisi, remplacer le nom par "world".
- Essayer votre code
Re-Lancer le serveur et rendez-vous sur localhost:8080/hello

Plutôt que d'écrire du code html dans le code java, nous allons le mettre dans un fichier à pars pour faciliter son édition 
(par exemple par un webdesigner) et pour faciliter sa mise à jour (pas besoin de recompiler ...)

- Compléter la servlet FormGreet2 (qui étend FormGreet) :
pour lire le contenu du fichier www/form.html et l'afficher dans la réponse http.

