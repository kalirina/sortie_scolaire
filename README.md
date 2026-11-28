# Organisation des chambres pour un voyage de groupe

**Projet de Programmation Avancée et Applications**  
**Licence 3 Informatique et applications — Université Paris Cité**
**Année 2026/2027**

**Réalisé par :**
- Irina Kalinina
- Dina Shubina

---

Ce projet consiste à développer un logiciel permettant de gérer les relations et les préférences entre les membres d’un groupe afin de simuler et d’optimiser l’attribution des chambres lors d’un voyage, en minimisant le nombre de personnes jalouses.

## Partie 1 — Gestion du groupe et simulation des affectations

Cette première partie consiste à mettre en place une répartition initiale naïve des chambres : chaque étudiant reçoit sa chambre préférée parmi celles encore disponibles. Le programme permet ensuite de modifier manuellement les affectations en échangeant les chambres entre étudiants et de calculer le nombre de personnes jalouses afin d'évaluer la qualité de la répartition.

### Structure du projet

#### Composants principaux :

- **Configuration** : Configure le nombre d’étudiants.
- **Construction** : Génère une répartition initiale.
- **Student** : Représente un étudiant.
- **RoomAssignment** : Associe chaque étudiant à une chambre.

#### Interface utilisateur :

- **Frontend** : Développement d'une interface utilisateur permettant de configurer le groupe, de visualiser les affectations des chambres et d'interagir avec le programme.
