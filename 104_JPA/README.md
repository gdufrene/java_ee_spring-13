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
* Afin de rendre notre contrôleur un peu indépendant de l'implémentation du modèle, nous allons mettre en place un pattern "DAO" (Data Access Object)

Il y a un "main" dans VueEtudiant permettant de voir à quoi ressemble la vue. Aucun contrôle n'est effectué lorsqu'on lance cette classe.
Le "main" de la classe CtrlEtudiant crée une vue et y ajoute toutes les actions sur les boutons ainsi que les intéractions avec le modèle.
Pour le moment rien ne fonctionne, il faut terminer le code de notre Dao ... A vous de jouer.

En premier lieu il faut télécharger les dépendances dont nous aurons besoin pour ce cours.
Pour notre 1er implémentation il nous faut le jar de hsqldb, pour la suite il nous faudra hibernate et ses quelques méga-octets de dépendances.
Pour facilité le travail vous pouvez exécuter le script get_dependencies.sh qui récupère ce qu'il faut (et juste ce qu'il faut).
Nous aurions pu utiliser un gestionnaire de dépendance type "maven", "ivy", "gradle" ou encore faire un script "ant" ...
Mais comme je suis fan de chose simple qui fonctionne je préfère m'en passer, au moins quand cela est possible et raisonnable.

Pous pouvez compilez les sources avec compile.sh et exécuter les tests avec run_test.sh ... 7 erreurs qui vous insultent en prétextant que le code n'est pas implémenté. Normal, c'est votre boulo de développeur pour aujourd'hui.

Compléter le code de la classe EtudiantJdbcDao afin de faire passer les tests "TestEtudiant".
Lorsque tous les tests passent vous devriez être en mesure d'avoir une interface qui fonctionne.

Pour lancer l'interface en ligne de commande vous pouvez utiliser application.sh, pour le moment avec le DAO JDBC.

Notre base de données étant stockée en mémoire évidemment, cela ne persiste pas entre deux lancements d'application. Si vous le souhaitez, changer le mode de persistance de hsql dans l'initialisation de la base. Mais là n'est pas le principal...

Nous avons vu comment utiliser l'api JDBC permettant de réaliser des opération SQL sur une base de données.
Bien que SQL se veut un langage "standard", dans la réalité les SGBD ont chacune leur spécificités. Il y a peu de chance que votre modèle Etudiant continue de fonctionner sur une base mySQL si vous décidez de migrer. Les ORM sont là pour cela : ne plus se soucier des opérations de bas niveau qui sont exécuter sur la base mais se concentrer sur les opérations logiques que nous réalisons sur les tuples (enregistrements) d'une base. JPA défini les opérations (et annotations) utilisables du package javax.persistance, il nous faut ensuite une implémentation de cette "norme". Il en existe 2 assez connues : TopLink et Hibernate.

# JPA avec Hibernate

Essayons de mettre en oeuvre une persistance JPA avec l'implémentation Hibernate par dessus une base de données hsql.

Avec JPA, nous pouvons annoter nos classes JAVA pour indiquer que les données de ces classes seront persistée.
De manière simple, il "suffit" de placer l'annotation @Entity sur une classe pour que cela fonctionne ... ou presque.
Il est nécessaire que les propriétés de l'objet à persister soit accessible par le gestionnaire d'entité.
Comprenez : 

* soit une propriété public ;
* soit un getter / setter avec le nom qui correspond getPropriété et setPropriété( valeur ).

Pour certaine propriété il est utile de mettre d'autres annotation pour indiquer comment le gestionnaire ou la base se comportera.
Par exemple l'identifiant de l'entité doit être annoté @Id.
Nous pouvons aussi indiquer que cette proriété sera généré par la base via une séquence (ou auto increment).
Pour cela nous ajoutons la propriété @GeneratedValue(strategy=GenerationType.IDENTITY)

Observez la classe EtudiantJPA.
Cette classe étend la classe Etudiant pour des raisons de compatibilité avec notre DAO générique, et pour éviter d'avoir des annotations dans notre classe Etudiant de base (ce qui ne gênerait pas vraiment le fonctionnement du JDBC)...

Vous pouvez consultez la classe DemoJPA dans les tests pour voir un exemple simple d'usage de l'entity manager et des transactions.
Toutes les requêtes JPA doivent être exécutées dans une transaction, c'est ainsi. On "oblige" le développeur à se soucier de ce qui a du sens en terme d'état de base de données. Si une Exception survient durant la transaction, les modifications en base ne sont pas appliquées (ou défaites).

La magie a des limites, pour que cela fonctionne, l'entity manager prend quelques informations dans META-INF/persistence.xml.
C'est notamment à cet endroit que l'on défini hibernate comme implémentation JPA à utiliser, ou encore les identifiants et mot de passe d'accès à notre base de données, un peu de configuration propre à hibernate ...

L'Entity Manager permet de sauvegarder (persist), charger (find), ou créer des requêtes exprimées en JPQL ... une sorte de sql orienté objet.
Notez qu'il est aussi possible de créer des requêtes à l'aide de l'API Criteria de JPA.
Pour tout cela, il y a la doc.
Consultez par exemple : http://www.objectdb.com/java/jpa/query
La doc officielle, un peu moins agréable : http://docs.oracle.com/cd/E13189_01/kodo/docs40/full/html/ejb3_overview_query.html
Et la javadoc, encore plus austère : http://docs.oracle.com/javaee/6/api/index.html?javax/persistence/EntityManager.html

Bon amusement et bon courage pour implémenter EtudiantJPADao.

Pour tester tout cela il suffit de modifier le TestEtudiant pour utiliser votre implémentation JPA.
'''
@BeforeClass
public static void initDB() {
	dao = new EtudiantJPADao();
	dao.init();
}
'''

Si les tests passent, vous devriez être en mesure de faire fonctionner l'interface avec le DAO JPA.
Félicitation vous avez survécu jusqu'ici !

