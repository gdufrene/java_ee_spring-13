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

### Quelques bases ...

Afin de distinguer le code html ou javascript à donner au navigateur et le code java à éxécuter sur le serveur,
nous utilisons les balises "<%" et "%>" pour délimiter le code java.
Une balise particulière permet de définir des propriétés sur le type de réponse HTTP, l'encodage de la page, ou encore
les imports java nécessaire pour la compilation de la page.
Toutes ces directives se retrouvent entre les balises "<%@" et "%>".
Enfin, il est très courant de vouloir afficher le contenu d'une variable dans le html qui sera renvoyé au navigateur.
Cela peut se faire en faisant un <code><% out.print( maVariable ); %></code> mais un tag a été défini pour réduire
l'écriture à <code><%= maVariable %></code> qui fait exactement la même chose.
Comme nous sommes de bon développeurs consciencieux, nous n'hésiterons également pas à utiliser le tag permettant 
de mettre des commentaires dans nos jsp : <code><%-- mon super commentaire qui ne sera pas compilé ou envoyé au navigateur --%></code>
Il faut bien comprendre que la jsp va être traduite en code java. Plus précisément en une classe qui hérite de Servlet
dont il sera exécuter la méthode "service".
Dans certains cas il peut être utile de vouloir introduire des attributs ou des méthodes à cette classe générée et compilée.
Cela peut se faire avec le tag <code><%! private double montant; %></code>

Depuis votre code java, vous pouvez utiliser les variables suivantes :
* request  - qui contient l'objet HttpServletRequest
* response - qui contient l'objet HttpServletResponse
* out - qui est le flux de sorti équivalent à request.getWriter()
* application - un objet possédant notament la méthode "log" utile pour débugger
* pageContext - un objet permettant d'échanger des variables entre les différents morceaux de jsp de la page.

Pour rendre l'écriture de nos jsp un peu plus modulaire il est possible d'inclure du code jsp à partir d'une autre page jsp.
Cela se fera via la directive <code><%@ include file="ma_page.jsp" %></code>
Si vous donnez un chemin absolu au fichier (exemple /layout/header.jsp), la racine considéré est celle du contexte web du serveur.
Il est ainsi plus facile d'être indépendant de l'environnement de déploiement.

### Exercice de mise en jambe

Ecrire une page présentant un film, permettant aux visiteurs de laisser un commentaire.

