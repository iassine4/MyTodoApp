# MyTodoApp


Application web de gestion de tâches réalisée avec **Spring Boot**, **Spring MVC**, **Thymeleaf**, **Spring Data JPA** et **Spring Security**.

Ce projet a été développé dans le cadre de l’évaluation **TodoList** de la phase web. Le sujet demande une application permettant à un utilisateur connecté d’ajouter, rechercher, modifier et supprimer des tâches, avec une page d’accueil publique affichant des tâches fictives.  
L’application suit également l’architecture MVC côté serveur présenté dans le support Spring MVC : contrôleurs, modèle, vues Thymeleaf et séparation en couches.

---

## Fonctionnalités réalisées

### Partie publique
- page d’accueil accessible sans connexion ;
- affichage d’un aperçu de tâches fictives ;
- accès à la page de connexion.

### Partie sécurisée
- authentification avec Spring Security ;
- bouton de connexion / déconnexion ;
- accès protégé aux pages de gestion des tâches.

### Gestion des tâches
- affichage de la liste des tâches ;
- pagination ;
- recherche par mot-clé ;
- ajout d’une tâche ;
- modification d’une tâche ;
- suppression d’une tâche ;
- gestion du statut :
    - À faire
    - En cours
    - Terminée
- affichage du statut avec badges colorés ;
- association optionnelle d’une catégorie à une tâche.

### Ergonomie
- interface stylisée avec un fichier CSS global ;
- formulaires clairs avec affichage des erreurs de validation ;
- header réutilisable avec navigation et état de connexion ;
- vues Thymeleaf structurées.

---

## Technologies utilisées

Le projet utilise les technologies suivantes :

- **Java 17**
- **Spring Boot 3.5.13**
- **Spring MVC**
- **Spring Data JPA**
- **Hibernate**
- **Spring Security**
- **Thymeleaf**
- **Thymeleaf Extras Spring Security 6**
- **Jakarta Validation**
- **MariaDB**
- **Maven**
- **HTML / CSS**

Le support de cours présente justement l’usage de Spring Boot, Spring MVC, Thymeleaf, Spring Data JPA, Hibernate, MariaDB et Spring Security pour ce type d’application web.

---

## Architecture du projet

Le projet est organisé en couches :

- **entity** : entités métier (`Task`, `Category`)
- **repository** : accès aux données (`TaskRepository`, `CategoryRepository`)
- **service** : logique métier (`TaskService`, `CategoryService`)
- **service.impl** : implémentations des services
- **controller** : contrôleurs web (`HomeController`, `TaskController`, `LoginController`)
- **dto** : objets de formulaire (`TaskForm`)
- **config** : configuration de sécurité
- **templates** : vues Thymeleaf
- **static/css** : ressources statiques
- **exception** : exceptions personnalisées

Cette séparation est cohérente avec l’architecture en couches demandée dans le sujet d’évaluation. :contentReference[oaicite:3]{index=3}

---

## Entités principales

### Task
Une tâche contient :
- un identifiant ;
- un titre ;
- une description ;
- une date d’échéance ;
- un statut ;
- une catégorie optionnelle.

### Category
Une catégorie permet de classer plusieurs tâches.

### TaskStatus
Les valeurs techniques de l’enum sont :
- `TODO`
- `IN_PROGRESS`
- `DONE`

Les libellés affichés dans l’interface sont :
- **À faire**
- **En cours**
- **Terminée**

---

## Pages disponibles

### Pages publiques
- `/` : page d’accueil
- `/login` : page de connexion

### Pages protégées
- `/tasks` : liste des tâches
- `/tasks/create` : création d’une tâche
- `/tasks/edit/{id}` : modification d’une tâche
- `/tasks/delete/{id}` : suppression d’une tâche

---

## Sécurité

La sécurité est gérée avec **Spring Security**.

### Règles mises en place
- la page d’accueil `/` est publique ;
- la page `/login` est publique ;
- les ressources statiques sont publiques ;
- les routes `/tasks/**` sont protégées ;
- la déconnexion est disponible via un formulaire POST sécurisé.

### Comptes de test
Deux utilisateurs en mémoire sont définis pour la démonstration :

- **user / password**
- **admin / password**

Le support Spring MVC rappelle que la couche sécurité fait partie de la suite logique du projet web. :contentReference[oaicite:4]{index=4}

---

## Validation des formulaires

La validation est réalisée avec les annotations Jakarta Validation.

### Champs obligatoires
- titre ;
- date d’échéance ;
- statut.

Les erreurs de saisie sont affichées directement dans la vue avec Thymeleaf, conformément au fonctionnement `@Valid` + `BindingResult` présenté dans le support. :contentReference[oaicite:5]{index=5}

---

## Données de démonstration

Le projet contient un fichier `data.sql` permettant d’insérer automatiquement :

- plusieurs catégories ;
- 15 tâches de démonstration en français.

Cela permet de tester rapidement :
- la page d’accueil ;
- la liste ;
- la recherche ;
- la pagination ;
- les formulaires ;
- l’affichage des catégories et des statuts.

---

## Lancer le projet

### 1. Prérequis
- Java 17
- Maven
- MariaDB
- un IDE comme IntelliJ IDEA ou Eclipse

### 2. Créer la base de données
Créer une base MariaDB, par exemple :

```sql
CREATE DATABASE mytodolist_db;
