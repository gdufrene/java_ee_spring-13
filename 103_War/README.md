War 103
=======

Un war est une "web archive".
C'est en fait un fichier zip contenant une structure normée de répertoires et de fichiers.
C'est une manière de packager une application web java à destination d'un serveur applicatif compatible.

Un war crée un contexte web qu'il est possible de démarrer, arrêter, redémarrer.
Cela se fait en ligne de commande ou via une interface web de gestion du serveur d'application.

Quelle différence entre un war, un jar et un zip ?

Un fichier zip est une collection de fichiers compressés selon un format du même nom.
Un fichier zip peut contenir des répertoire et des fichiers de n'importe quelle sorte.
La compression est surtout efficace sur les fichiers textes, faible sur les fichiers exécutable,
quasi-nul sur les formats déjà compressés (jpg, mp3, ...). Cela reste tout de même un moyen
efficace que ranger dans un même fichier un ensemble de choses.

Un fichier jar EST un fichier zip. Il peut se décompresser avec les commandes zip habituelles.
Un jar est un jar lorsqu'il possède cette extension et qu'il contient un dossier META-INF, 
contenant un ensemble d'information utile à l'exécution du code java (class) inclus également
dans le fichier.

Un fichier war EST un fichier jar contenant un fichier de description de l'application web ;
il se trouve dans WEB-INF/web.xml.
Il déclare éventuellement différentes ressources qu'il utilise (dépendances ou publication de fichiers à travers
le serveur web). Un fichier permet par exemple de faire le lien entre une url appelée sur le
serveur et la servlet à exécuter.

# Une Application web de tâches à réaliser

aka TODO List.

Nous allons réaliser un war permettant d'exposer une application de "TODO" liste.
Pour tester facilement ce war nous allons utiliser "jetty-runner". Jetty Runner est un
jar contenant un ensemble assez minimaliste de classes permettant de déployer des applications web (war).

Dans cet exercice nous appliquerons quelques bonnes pratiques de séparation du code.
Notamment, nous allons essayer d'appliquer plus ou moins bien le pattern "MVC".
Nous aurons ainsi:

- le modèle de notre application. 
Ceux sont les classes java qui forme la logique dîtes "métier".
- le contrôleur.
C'est une classe qui reçoit les actions de l'utilisateur et agit sur les classes métier.
- la vue.
C'est ce qui est affiché à l'utilisateur.

Autant que possible, le modèle doit être indépendant des autres éléments.
Le contrôleur et la vue sont normalement assez dépendant, de part leurs interactions.
Nous devrions tout de même essayer de rendre la vue "interchangeable" vis à vis du contrôleur.
L'idée étant de pouvoir contrôler notre application de différentes manières.

## Pré-requis

- [ ] Récupérer un serveur applicatif
Vous pouvez télécharger Jetty Runner sur l'annuaire central Maven :
http://repo1.maven.org/maven2/org/eclipse/jetty/jetty-runner/9.0.4.v20130625/jetty-runner-9.0.4.v20130625.jar
Placez le dans le répertoire "103_War" de ce projet.
- [ ] Avoir la librairie servlet-api dans le classpath pour compiler
Si vous avez réalisé les TP précédant, il n'y a rien à faire, utilisez Eclipse en important le projet.


## Compiler, créer le war, lancer l'application ?

_Compiler_

- Eclipse compile tout seul le code dans le répertoire "bin"
- Il est possible de compiler le code avec le script fourni compile.sh
- Pour les utilisateur windows, inspirez vous du script pour faire la même chose.

_Créer le war_

- Utiliser eclipse: Fichier Export, Java/jar, sélectionner les ressources (au moins www et WEB-INF), donner un nom au jar : "todo.war" ...
- utilisez "makewar.sh", c'est au choix

_Lancer l'application web_

java -jar jetty-runner* todo.war

Le "soucis" c'est que si vous faîtes une modification dans le fichier jsp, vous devez recompilez, refaire le war, et relancer le serveur...
C'est pas cool.
Pendant le développement on peut plutôt demander à jetty-runner de lancer le serveur en faisant comme si un répertoire était le contenu d'un war :

java -jar jetty-runner* .

(le répertoire . étant le répertoire courant).
Dans ce dernier cas, lorsque vous modifiez le jsp, il est recompilé pour vous et vous voyez les modifications.
Idem si vous aviez des ressources disponibles à travers le war (images, feuille de style, javascript ...)
Par contre, il faut quand même re-compiler et relancer le serveur si vous modifier les classes java.
D'où l'intérêt d'avoir bien testé avant avec du TDD :)


## Ecriture de notre application

En bon développeur que nous sommes, nous allons évidemment réaliser l'application en
écrivant tout d'abord des Tests Unitaires qui nous permettront :

- de s'assurer que notre code java est correct
- que nos modifications futures ne casseront pas l'existant

### Développement dirigé par les Tests (TDD)

J'ai réalisé pour vous un ensemble de petits tests.
Ils se trouvent dans le répertoire "test" et se lance 

- dans eclipse :
clic droit sur "AllTests", "run as ...", "Junit Test"
- à l'aide de la commande "run_test.sh"
L'interface console est moins pratique mais rempli sa fonction ...

Evidemment, pour le moment les tests ne passent pas.

- [ ] Lisez le Test "TaskTest" et implémentez le code de Task.java pour qu'il passe ce Test.
- [ ] Lisez le Test "TodoTest" et implémentez le code de TodoList.java pour qu'il passe ce Test.
- [ ] Relancez "AllTests", idem à chaque modification importante du code ;-)

### Début d'implémentation de la "vue", notre jsp

Une servlet va délivrer le contenu html de notre vue.
Cette servlet permettra également de répondre aux interactions avec l'utilisateur.
C'est notre contrôleur.

Le code fourni propose une servlet qui, en fonction du paramètre "action" (get ou post)

- réalise l'affichage de la liste de tâche, 
- ajoute ou supprime une tâche,
- permet de compléter une tâche

Notez comment, depuis une servlet, il est possible d'enchainer le traitement avec une page jsp.
(voir le cas LISTTASK). Afin de passer des données entre la servlet et le jsp, il est possible
d'utiliser les méthodes "setAttribute" et "getAttribute" de l'objet Request.

- [ ] Compléter le code pour lister les tâches
- [ ] Essayez de compléter le formulaire de création d'un événement

### Implémentation du "contrôleur", notre servlet

Complétez le contrôleur pour permettre l'ajout d'un événement.
Vérifiez que lorsque vous ajoutez un événement, celui-ci apparait dans la liste.

### Finalisation de la vue

- [ ] Gérez la fonction permettant de compléter une tâche
- [ ] Gérez la suppression d'une tâche

