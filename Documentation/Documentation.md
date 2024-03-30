# Cultugym

## Table des Matières

- [Introduction](#introduction)
- [Prérequis](#prérequis)
  - [Matériel](#matériel)
  - [Logiciels](#logiciels)
  - [Dépendances](#dépendances)
- [Installation](#installation)
- [Comment_Commencer](#comment-commencer)
- [Fonctionnalités](#fonctionnalités)
- [Contribuer](#contribuer)
  - [Licence](#licence)
- [Contact](#contact)

# Introduction

Ce logiciel a été conçu avec pour objectif principal de fournir aux entraîneurs sportifs un outil leur permettant d'élaborer des programmes d'entraînement de manière plus efficace et à distance. L'avantage majeur réside dans le gain de temps significatif qu'il offre. En outre, ce système facilite le suivi des progrès des clients par les coachs, leur permettant ainsi d'ajuster les programmes d'entraînement en fonction de l'évolution des performances de chaque individu. Développée dans le cadre d'un projet scolaire, cette solution logicielle tire parti de mes connaissances approfondies dans le domaine du sport, me permettant d'exploiter pleinement mon expertise pour répondre aux besoins spécifiques des entraîneurs et de leurs clients.

# Prérequis

### Matériel

- Processeur : 2 GHz dual-core ou supérieur.
- Mémoire : 8 GB de RAM (à partir de 12 pour une performance optimale).
- Espace disque : 500 MB d'espace libre.
- Connexion internet stable.

### Logiciels

- Système d'exploitation : Windows 10.
- Java Runtime Environment (JRE) version 22.
- Kit de développement logiciel (SDK) version 22. (pour tester le fat jar dans le cmd).

### Dépendances

Votre projet nécessite les dépendances suivantes pour fonctionner correctement :

- **JavaFX Modules**
  - `org.openjfx:javafx-controls:21-ea+24`
  - `org.openjfx:javafx-fxml:21-ea+24`
  - `org.openjfx:javafx-web:21-ea+24`
  - `org.openjfx:javafx-swing:21-ea+24`
  - `org.openjfx:javafx-media:21-ea+24`

- **ControlsFX**
  - `org.controlsfx:controlsfx:11.1.2`

- **FormsFX**
  - `com.dlsc.formsfx:formsfx-core:11.6.0` (Exclut toutes les dépendances `org.openjfx`)

- **ValidatorFX**
  - `net.synedra:validatorfx:0.4.0` (Exclut toutes les dépendances `org.openjfx`)

- **Ikonli pour JavaFX**
  - `org.kordamp.ikonli:ikonli-javafx:12.3.1`

- **BootstrapFX**
  - `org.kordamp.bootstrapfx:bootstrapfx-core:0.4.0`

- **TilesFX**
  - `eu.hansolo:tilesfx:17.1.17` (Exclut toutes les dépendances `org.openjfx`)

- **FXGL**
  - `com.github.almasb:fxgl:21` (Exclut toutes les dépendances `org.openjfx`)

- **JUnit Jupiter (pour tests)**
  - `org.junit.jupiter:junit-jupiter-api:${junit.version}` (scope: test)
  - `org.junit.jupiter:junit-jupiter-engine:${junit.version}` (scope: test)

- **MySQL Connector/J**
  - `com.mysql:mysql-connector-j:8.3.0`

- **jBCrypt**
  - `org.mindrot:jbcrypt:0.4`

- **Jakarta Mail**
  - `jakarta.mail:jakarta.mail-api:2.0.1`
  - `com.sun.mail:jakarta.mail:2.0.1`

- Assurez-vous de remplacer `${junit.version}` par la version spécifique de JUnit que vous utilisez dans votre projet.

# Installation

L'installation de Cultugym nécessite plusieurs étapes cruciales pour s'assurer que l'application fonctionne correctement sur votre système. Suivez ces instructions pour configurer l'environnement nécessaire et lancer l'application.

### 1. Prérequis

Assurez-vous d'avoir satisfait à tous les [prérequis](#prérequis) mentionnés précédemment, y compris l'installation de toutes les [dépendances](#dépendances) requises pour le projet.

### 2. Téléchargement

Téléchargez la dernière version de l'application Cultugym depuis la page de releases de votre dépôt GitHub ou le site web officiel. Extrayez le contenu de l'archive téléchargée dans le dossier de votre choix.

### 3. Installation de JavaFX et Configuration

Cultugym nécessite JavaFX pour l'interface utilisateur. Si vous utilisez Maven ou Gradle, assurez-vous d'inclure les modules JavaFX spécifiés dans les dépendances de votre `pom.xml` ou fichier `build.gradle`.

Pour Maven, ajoutez les dépendances suivantes à votre fichier `pom.xml` :

```xml
<dependencies>
    <!-- JavaFX modules -->
    <dependency>
        <groupId>org.openjfx</groupId>
        <artifactId>javafx-controls</artifactId>
        <version>21-ea+24</version>
    </dependency>
    <dependency>
        <groupId>org.openjfx</groupId>
        <artifactId>javafx-fxml</artifactId>
        <version>21-ea+24</version>
    </dependency>
    <!-- Ajoutez les autres dépendances JavaFX et projets ici -->
</dependencies> 
```

### 4. Configuration de l'Environnement

Configurez votre environnement de développement pour utiliser Java 22. Cela peut inclure la configuration des variables d'environnement `JAVA_HOME` et `PATH` pour pointer vers votre installation Java.

### 5. Lancement de l'Application

Avec Maven, vous pouvez lancer l'application directement depuis la ligne de commande :

```shell
mvn javafx:run

````
### Pour démarrer Cultugym sans utiliser Maven ou Gradle,

 suivez les instructions ci-dessous. Ces commandes doivent être exécutées dans le terminal (sous Linux ou MacOS) ou dans l'invite de commandes (sous Windows).

1. Définissez le chemin du module JavaFX en remplaçant `CheminVersLibJavaFX` par le chemin d'accès au dossier lib de votre SDK JavaFX. Exemple pour Windows :

    ```shell
    --module-path "C:\chemin\vers\sdk\javafx-sdk-22\lib"
    ```

2. Ajoutez les modules nécessaires de JavaFX, en particulier `javafx.controls` et `javafx.fxml`.

3. Exécutez le fichier JAR de votre application en remplaçant `CheminVersVotreFichierJar` par le chemin d'accès complet à votre fichier JAR. La commande complète ressemblerait à :

    ```shell
    java --module-path "CheminVersLibJavaFX" --add-modules javafx.controls,javafx.fxml -jar "CheminVersVotreFichierJar"
    ```

   Par exemple :

    ```shell
    java --module-path "C:\chemin\vers\sdk\javafx-sdk-22\lib" --add-modules javafx.controls,javafx.fxml -jar "C:\chemin\vers\Application\out\artifacts\Application_jar\Application.jar"
    ```

- Veuillez vous assurer que toutes les ressources nécessaires, comme les fichiers de configuration et les assets multimédia, sont disponibles dans le dossier de l'application ou correctement référencés dans le classpath. Cette organisation garantit que votre application fonctionne comme prévu sans la nécessité d'un environnement de gestion de projet.

### 6. Première Exécution

Bien démarrer avec Cultugym est essentiel pour une expérience utilisateur optimale. Lors de votre première utilisation, vous pourriez avoir besoin de configurer certains paramètres ou d'apprendre à naviguer dans l'application.

Si des difficultés surgissent ou si vous requérez une assistance supplémentaire, n'hésitez pas à consulter la section [Dépannage](#dépannage). Pour une assistance plus spécifique ou personnalisée, notre équipe de support est à votre disposition. Contactez-nous via la section [Contact](#contact) pour toute question ou aide requise.

# Comment Commencer

Pour garantir une prise en main efficace de Cultugym, nous vous invitons à consulter le manuel d'utilisation. Ce document contient des informations précieuses sur les fonctionnalités disponibles et des instructions sur leur utilisation.

### Accès au Manuel d'Utilisation

Le manuel d'utilisation est disponible sous forme de fichier PDF et peut être consulté ou téléchargé pour référence ultérieure :

[Manuel d'Utilisation (PDF)](Manuel_utilisation.pdf)

Nous recommandons de sauvegarder une copie du manuel sur votre appareil pour pouvoir le consulter même hors ligne. Prenez le temps de le lire pour vous familiariser avec toutes les capacités de Cultugym.

# Fonctionnalités

Cultugym est doté d'une gamme étendue de fonctionnalités conçues pour optimiser le processus d'élaboration et de suivi des programmes d'entraînement sportif. Chaque fonctionnalité est pensée pour apporter une valeur ajoutée spécifique aux entraîneurs et à leurs clients. Ci-dessous, découvrez les capacités clés de l'application :

### Planification d'Entraînement

- **Programmation Personnalisée** : Créez des programmes d'entraînement personnalisés qui répondent aux objectifs spécifiques de chaque client.
- **Calendrier Intégré** : Gérez et visualisez les séances d'entraînement planifiées grâce à un calendrier interactif.

### Gestion des Clients

- **Profils Utilisateurs** : Stockez et accédez aux informations des clients, y compris les données personnelles, les notes de santé et l'historique d'entraînement.
- **Communication Efficace** : Utilisez des outils intégrés pour communiquer avec les clients et partager des mises à jour importantes.

### Sécurité et Confidentialité

- **Protection des Données** : Bénéficiez d'un haut niveau de sécurité pour protéger les informations personnelles des clients.
- **Respect de la Confidentialité** : Respectez les normes de confidentialité avec une gestion rigoureuse des données utilisateurs.

Assurez-vous de consulter le [Manuel d'Utilisation (PDF)](Manuel_utilisation.pdf) pour des instructions détaillées sur l'utilisation de chaque fonctionnalité. Si vous avez des questions ou si vous avez besoin d'une démonstration des fonctionnalités, contactez notre équipe via la section [Contact](#contact).

# Contribuer

Votre contribution à Cultugym est très appréciée, que vous soyez débutant ou expérimenté dans le domaine du développement logiciel. Voici comment vous pouvez participer à l'amélioration et au développement de Cultugym :

1. **Fork le Projet** : Créez un fork du dépôt sur votre propre compte GitHub pour commencer à travailler sur vos modifications.
2. **Créer une Branche** : Pour chaque ensemble de modifications que vous souhaitez apporter, créez une nouvelle branche dans votre fork.
3. **Soumettre une Pull Request** : Après avoir apporté vos modifications, soumettez une pull request pour qu'elle soit examinée et intégrée dans le projet principal.

### Signalement de Bugs

Si vous rencontrez des bugs ou des problèmes lors de l'utilisation de Cultugym, n'hésitez pas à ouvrir un problème (issue) sur le dépôt GitHub du projet. Votre rapport aidera à identifier et à corriger les problèmes plus rapidement.

### Proposition de Fonctionnalités

Les suggestions d'améliorations ou de nouvelles fonctionnalités sont également les bienvenues. Elles peuvent être soumises sous forme d'issues sur GitHub, où elles seront discutées avec la communauté.

## Licence

Cultugym est distribué sous la licence GNU General Public License v3.0 (GPLv3), ce qui signifie que vous êtes libre de modifier, distribuer et améliorer le logiciel dans le respect des termes de cette licence. Pour plus d'informations sur vos droits et obligations en tant que contributeur, veuillez consulter le texte complet de la licence [GNU GPLv3](https://www.gnu.org/licenses/gpl-3.0.fr.html).

Nous encourageons toute forme de contribution qui respecte les principes de notre communauté. Merci de participer à l'évolution de Cultugym !

Pour consulter la licence, veuillez cliquer [ici](../LICENSE).

# Contact 

Pour nous contacter vous pouvez directement envoyer un mail au créateur du logiciel [ici](https://mail.google.com/mail/u/0/#inbox?compose=new)
avec l'adresse email : 
```jh.virtual.tech@gmail.com' ```.



