105 - Spring Basics
===================

_The Spring Framework provides a comprehensive programming and configuration model for modern Java-based enterprise applications_

Comprenez que Spring est un "couteau suisse" pour faciliter la vie du développeur JAVA, spécialement si il développe pour JAVA EE (mais pas que).

Le coeur de spring c'est, à l'origine, un moteur d'injection de dépendance permettant l'inversion de contrôle du cycle de vie des objets.
Mais encore ? Permettre de "repousser" de choix d'implémentation (ou d'instance) d'un objet inclus dans un autre.

Reprenons l'exemple de notre DAO JPA ou Jdbc.
Imaginons que le développeur de notre interface ne soit pas le même que celui qui réalise la couche de persistance (donc du DAO).
Le développeur de l'interface ne connait pas la classe qui sera utilisé, et d'ailleurs il n'a pas envie de faire new EtudiantJPADao(), ce qui "casserait" en parti l'abstraction que nous avons essayé de mettre en place.
De l'autre coté, le developpeur de notre DAO ne sait pas ou et comment son implémentation JPA sera utilisé. Il ne peut pas faire view.setDao( ... )

Heureusement il y a Spring.
Dans l'interface on demandera à l'injecteur de dépendance de trouver et d'affecter le DAO, de l'autre on déclarera l'implémentation JPA comme composant DAO à être utilisé. Le tout sera initialisé et affecté au lancement de l'application.

Merci Martin, que ferions-nous sans toi.
- pour votre culture générale - http://www.martinfowler.com/articles/injection.html

# Installation

Afin de changer un peu les habitudes utilisons maven.
Maven est un outil permettant de gérer les dépendances, la compilation, le "packaging" et bien d'autres choses ...
Il s'utilise au moyen de fichier "pom" décrivant votre projet.

Télécharger et installer maven, utilisez install_maven.sh

Ensuite, un ./mvn --version devrait vous donnez quelquechose du genre :

<pre>
Apache Maven 3.1.1 (0728685237757ffbf44136acec0402957f723d9a; 2013-09-17 17:22:22+0200)
Maven home: /Users/guillaume/Dev/ens/java_ee_spring/105_SpringBasics/apache-maven-3.1.1
Java version: 1.6.0_51, vendor: Apple Inc.
Java home: /System/Library/Java/JavaVirtualMachines/1.6.0.jdk/Contents/Home
Default locale: fr_FR, platform encoding: MacRoman
OS name: "mac os x", version: "10.7.5", arch: "x86_64", family: "mac"
</pre>

Nous pouvons regarder la liste des dépendances de notre projet (ce qui les télécharge au besoin)
./mvn dependency:tree

<pre>
[INFO] fr.eservice:spring-basics:jar:1.0-SNAPSHOT
[INFO] +- org.springframework:spring-context:jar:3.2.4.RELEASE:compile
[INFO] |  +- org.springframework:spring-aop:jar:3.2.4.RELEASE:compile
[INFO] |  |  \- aopalliance:aopalliance:jar:1.0:compile
[INFO] |  +- org.springframework:spring-beans:jar:3.2.4.RELEASE:compile
[INFO] |  +- org.springframework:spring-core:jar:3.2.4.RELEASE:compile
[INFO] |  |  \- commons-logging:commons-logging:jar:1.1.1:compile
[INFO] |  \- org.springframework:spring-expression:jar:3.2.4.RELEASE:compile
[INFO] \- junit:junit:jar:4.8.2:test
</pre>

Enfin, si vous comptez utiliser eclipse vous devrez configurer une variable "M2_REPO" désignant l'endroit
où se trouvent les jar téléchargés par maven. Par défaut, ils se trouvent dans votre home, dans ".m2/repository"

Passons à Spring.

# L'injection de dépendances, IoC

