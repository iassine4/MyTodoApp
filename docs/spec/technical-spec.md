
## Spécifications techniques

### Architecture technique
Le projet repose sur une architecture en couches :

- **Entités** : modélisation des objets métier ;
- **DAO / Repository** : accès aux données ;
- **Business / Service** : logique métier ;
- **Web / Controller** : gestion des requêtes HTTP et des vues.

Cette séparation permet d’avoir un code plus lisible, maintenable et évolutif.

---

### Technologies utilisées
Le projet est développé avec les technologies suivantes :

- **Java 17**
- **Spring Boot 3.5.13**
- **Spring MVC**
- **Spring Data JPA**
- **Hibernate ORM**
- **Spring Security**
- **Thymeleaf**
- **Thymeleaf Extras Spring Security 6**
- **Jakarta Validation**
- **MariaDB**
- **Maven**
- **HTML5 / CSS3**

---

### Versions retenues
Les versions utilisées dans le projet sont :

- **Java** : 17
- **Spring Boot** : 3.5.13
- **Spring Security** : version gérée par Spring Boot 3.5.13
- **Spring Data JPA** : version gérée par Spring Boot 3.5.13
- **Hibernate** : version gérée par Spring Boot 3.5.13
- **Thymeleaf** : version gérée par Spring Boot 3.5.13
- **MariaDB JDBC Driver** : version gérée par Spring Boot 3.5.13
- **Maven** : projet basé sur `spring-boot-starter-parent`
- **SGBD** : MariaDB

---

### Couche persistance
La persistance repose sur :

- **JPA** pour le mapping objet-relationnel ;
- **Hibernate** comme implémentation ORM ;
- **Spring Data JPA** pour simplifier les opérations CRUD ;
- **MariaDB** comme base de données relationnelle.

Les entités principales du projet sont :

- `Task`
- `Category`

La relation principale mise en œuvre est :

- **une tâche appartient éventuellement à une catégorie.**
- **une catégorie peut contenir plusieurs tâches.**

---

### Couche web
La couche web repose sur :

- des **contrôleurs Spring MVC** ;
- des **vues Thymeleaf** côté serveur ;
- des formulaires HTML liés à des objets de formulaire ;
- l’utilisation de `Model` pour transmettre les données aux vues ;
- l’utilisation de `BindingResult` pour la validation.

Les principales vues sont :

- `home.html`
- `login.html`
- `tasks/list.html`
- `tasks/form.html`

---

### Sécurité
La sécurité est gérée avec **Spring Security**.

Règles prévues :

- la page d’accueil `/` est publique ;
- la page `/login` est publique ;
- les ressources statiques (`/css/**`) sont publiques ;
- les pages `/tasks/**` sont protégées ;
- seul un utilisateur authentifié peut accéder à la gestion des tâches ;
- la déconnexion est disponible pour l’utilisateur connecté.

L’authentification est actuellement basée sur des **utilisateurs en mémoire** pour simplifier la démonstration.

---

### Validation et qualité
Le projet intègre :

- la validation des formulaires avec annotations ;
- des messages d’erreur utilisateur ;
- une organisation du code par packages ;
- des classes documentées avec **Javadoc** ;
- des commits Git réguliers et atomiques ;
- une structure compatible avec une évolution future.

---

### Outils de développement
Les outils utilisés ou recommandés sont :

- **IntelliJ IDEA** ou **Eclipse**
- **Maven**
- **MariaDB**
- **Git / GitHub**
- **navigateur web moderne**
- **débogueur de l’IDE**
- **SonarQube** pour l’analyse qualité

---

### Données de démonstration
Le projet contient un fichier `data.sql` permettant d’insérer automatiquement :

- plusieurs catégories ;
- plusieurs tâches fictives ;

Afin de tester rapidement :

- la page d’accueil ;
- la liste des tâches ;
- la recherche ;
- les formulaires ;
- l’affichage des catégories et des statuts.