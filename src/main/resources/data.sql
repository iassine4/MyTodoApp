-- =========================================
-- DONNÉES DE DÉMONSTRATION POUR MYTODOLIST
-- Version française
-- =========================================
-- Remarque importante :
-- Les valeurs du champ status restent en anglais
-- car elles doivent correspondre exactement
-- à l'enum Java : TODO / IN_PROGRESS / DONE
-- =========================================

-- =========================
-- CATÉGORIES
-- =========================
INSERT IGNORE INTO categories (id, name) VALUES
    (1, 'Travail'),
    (2, 'Maison'),
    (3, 'Courses'),
    (4, 'Études'),
    (5, 'Administratif');

-- =========================
-- TÂCHES
-- =========================
-- Si ta colonne s'appelle dueDate au lieu de due_date,
-- remplace simplement due_date par dueDate.
INSERT IGNORE INTO tasks (id, title, description, due_date, status, category_id) VALUES
    (1, 'Préparer les diagrammes UML', 'Finaliser les diagrammes de cas d''utilisation, de classes et de séquence.', '2026-04-10', 'TODO', 4),
    (2, 'Relire Spring Security', 'Vérifier le fonctionnement du login, des rôles et des pages protégées.', '2026-04-11', 'IN_PROGRESS', 1),
    (3, 'Faire les courses de la semaine', 'Acheter du lait, des œufs, du riz, des fruits et des légumes.', '2026-04-12', 'TODO', 3),
    (4, 'Ranger le salon', 'Passer l''aspirateur et remettre les jouets à leur place.', '2026-04-13', 'DONE', 2),
    (5, 'Préparer la démonstration du projet', 'Tester le parcours complet avant la présentation.', '2026-04-14', 'IN_PROGRESS', 1),
    (6, 'Relire le cours Spring MVC', 'Revoir les contrôleurs, le modèle et les vues Thymeleaf.', '2026-04-15', 'TODO', 4),
    (7, 'Appeler le plombier', 'Prendre rendez-vous pour le problème d''évier dans la cuisine.', '2026-04-16', 'TODO', 2),
    (8, 'Améliorer le style CSS', 'Rendre les formulaires et les tableaux plus agréables visuellement.', '2026-04-17', 'DONE', 1),
    (9, 'Envoyer le dossier administratif', 'Préparer et envoyer les documents demandés.', '2026-04-18', 'TODO', 5),
    (10, 'Nettoyer la terrasse', 'Balayer, nettoyer la table et ranger les coussins.', '2026-04-19', 'IN_PROGRESS', 2),
    (11, 'Faire la liste des fonctionnalités', 'Noter les fonctionnalités obligatoires et les bonus du projet.', '2026-04-20', 'DONE', 4),
    (12, 'Acheter des fournitures', 'Prendre des cahiers, des stylos et des classeurs.', '2026-04-21', 'TODO', 3),
    (13, 'Créer la page d''accueil', 'Vérifier le texte, les liens et l''affichage des tâches fictives.', '2026-04-22', 'IN_PROGRESS', 1),
    (14, 'Classer les papiers importants', 'Trier les factures, courriers et documents administratifs.', '2026-04-23', 'TODO', 5),
    (15, 'Tester le formulaire de tâche', 'Vérifier la validation, les erreurs et l''enregistrement en base.', '2026-04-24', 'DONE', 4);