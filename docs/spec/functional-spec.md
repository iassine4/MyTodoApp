
## Spécifications fonctionnelles

### Présentation générale
L’application **MyTodoList** est une application web de gestion de tâches à faire.  
Elle permet à un utilisateur authentifié de créer, consulter, rechercher, modifier et supprimer des tâches.  
Chaque tâche peut être classée dans une catégorie, mais ce classement reste optionnel.  
Un utilisateur non authentifié peut accéder à une page d’accueil publique présentant l’application ainsi qu’un ensemble de tâches fictives.

### Rôles de l’application
L’application distingue deux rôles principaux :

- **Visiteur** : utilisateur non authentifié
- **Utilisateur connecté** : utilisateur authentifié

---

### Fonctionnalités du rôle Visiteur
Le visiteur peut :

- accéder à la **page d’accueil** de l’application ;
- consulter une **présentation du projet** ;
- visualiser un **aperçu de tâches fictives** ;
- accéder à la **page de connexion** ;
- consulter l’interface publique de navigation.

Le visiteur ne peut pas :

- accéder à la liste complète des tâches ;
- créer une tâche ;
- modifier une tâche ;
- supprimer une tâche ;
- accéder aux formulaires de gestion.

---

### Fonctionnalités du rôle Utilisateur connecté
L’utilisateur connecté peut :

- se connecter à l’application avec un identifiant et un mot de passe ;
- accéder à la **liste des tâches** ;
- visualiser les tâches avec **pagination** ;
- rechercher des tâches à partir d’un **mot-clé** ;
- afficher le formulaire de création d’une tâche ;
- ajouter une nouvelle tâche ;
- afficher le formulaire de modification d’une tâche ;
- modifier une tâche existante ;
- supprimer une tâche ;
- associer une tâche à une **catégorie** ;
- laisser une tâche **sans catégorie** ;
- choisir un **statut** pour chaque tâche ;
- se déconnecter de l’application.

---

### Gestion fonctionnelle des tâches
Une tâche doit contenir au minimum les informations suivantes :

- un identifiant technique ;
- un titre ;
- une description ;
- une date d’échéance ;
- un statut ;
- une catégorie optionnelle.

Les statuts affichés à l’utilisateur sont :

- **À faire**
- **En cours**
- **Terminée**

---

### Gestion fonctionnelle des catégories
L’application permet de classer les tâches dans des catégories afin de faciliter leur organisation.

Exemples de catégories :
- Travail
- Maison
- Courses
- Études
- Administratif

Une tâche peut :
- appartenir à une catégorie ;
- ou ne pas être classée.

---

### Recherche et consultation
L’application doit permettre :

- l’affichage de toutes les tâches ;
- l’affichage paginé de la liste ;
- la recherche par mot-clé dans le titre ou la description ;
- la conservation du mot-clé de recherche pendant la navigation ;
- l’affichage d’un message clair lorsque aucune tâche n’est trouvée.

---

### Validation des formulaires
Lors de la création ou de la modification d’une tâche :

- le titre est obligatoire ;
- la date d’échéance est obligatoire ;
- le statut est obligatoire ;
- les erreurs de saisie doivent être affichées clairement à l’utilisateur.

---

### Ergonomie attendue
L’application doit proposer une interface simple, lisible et agréable à utiliser, avec :

- une navigation claire ;
- des boutons visibles ;
- des messages d’erreur compréhensibles ;
- un affichage coloré des statuts ;
- une page d’accueil distincte de la zone de gestion.