# MailStone

## Contexte et objectif

Les documents sont généralement à la base des échanges entre entreprises : échanges avec les clients (ventes en B2B), avec les fournisseurs, les partenaires, etc. Mais les diverses application internes dans les entreprises (les « métiers » : services des ressources humaines, des ventes, des affaires juridiques, des achats, etc.) utilisent des données structurées pour réaliser le pilotage de l’activité, la réalisation de bilan, (etc.). Les applications métiers étant conçues séparément, la communication entre la base de données et chaque application se fait par des messages : un fichier contenant les informations utiles à une application est généré à la demande depuis la base de données pour être consommé ensuite par l’application.

Ainsi il est utile de partir des documents pour alimenter la base de données, puis de générer des extractions de ces données, selon les besoins des applications des « métiers », pour que ces données soient utilisées.

L’objectif du projet est ainsi de traiter

1. L’extraction de donnée à partir de texte
2. La conception et l’alimentation d’une base de données
3. La génération de contenus structurés dédiés à des applications métiers

## Architecture

Le projet est composé de quatre applications : mail, analyzer, client et server.

Pour comprendre, la communication entre ces applications nous vous recommendons de lire le rapport de projet situé dans `docs/MailStone_Rapport_de_Projet.pdf`.

### L'application **mail**

Cette application consiste en un formulaire permettant d'envoyer des emails sur la boîte commune : mailstone2022@gmail.com.

Le port de cette application est le **8084**.

### L'application **analyzer**

Celle-ci se charge de récupérer les mails de la boîte commune à l'aide du protocole pop3.
Une fois les mails récupérés, un traitement est effectué sur les mails pour pouvoir en extraire les données les plus pertinantes et ainsi pouvoir être stocké dans notre base de données.

Le port de cette application est le **8083**.

### L'application **client**

C'est à partir de cette application que l'utilisateur va pouvoir questioner le server. Cette application consiste donc en une interface graphique permettant de questionner le server et d'afficher les résultats des recherches éffectuées.

Le port de cette application est le **8080**.

### L'application **server**

Toutes les requêtes sur la base de données sont réalisées dans cette application. Le server traite les messages des applications tierces et effectue les actions demandées. Cela peut être des messages d'insertions de données dans la base ou encore des demandes d'informations sur certains produits, client, ect...

Le port de cette application est le **8082**.
