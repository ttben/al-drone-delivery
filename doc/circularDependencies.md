# Optimisation de la livraison

Une livraison consiste en un ensemble d'information indiquant son déroulement complet.
Les données à optimiser sont les suivantes:

* Assignation d'un ensemble d'adresses dans une tournée 
* Placement optimal du camion pour effectuer cette tournée
* Envoi des colis une fois le drop-point atteint
	* Ordre
	* Type de drone utilisé
	
Afin de simplifier les problèmes, les cas d'erreurs ne seront pas considérés pour l'optimisation et il est accepté que
tout évènement non planifié donnera lieux à des performances sub-optimales par rapport à la solution initiale (à moins
 d'un coup de chance!).

## Problèmes à résoudre

### Drop-point et adresses

Le problème de détermination du drop-point consiste à être capable de déterminer la position à partir de laquelle la 
livraison à partir du camion sera effectuée, ainsi que les adresses que ce point est censé desservir.

Objectifs :

* livrer plus rapidement les colis
* limiter le nombre de drones à grande portée nécessaires pour effectuer la livraison

Paramètres en entrée:

* adresses à livrer dans la journée
* dimensions des colis à livrer
* drones dans le camion (peut également être un élément à déterminer)
  	* portée

#### Sous-problème 1: détermination des adresses d'une tournée
#### Sous-problème 2: détermination du drop-point pour chaque tournée

### Séquençage

Le problème de détermination du séquençage consiste à choisir dans quel ordre et avec quels drones les paquets seront 
envoyés.  
La difficulté de trouver la meilleure solution provient du fait que les paquets peuvent être livrés par des
 drones surdimensionnés par rapport à leur taille (note: mais jamais sous-dimensionnés).
 
Objectifs :

* réduire le nombre de drones nécessaires à la livraison
* livrer plus rapidement les colis

Paramètres en entrée:

* adresses à livrer dans la tournée
* dimensions des colis à livrer
* drones dans le camion (peut également être un élément à déterminer)
	* portée

#### Sous-problème 1: Binding colis/drone

Objectifs :

* réduire le nombre de drones nécessaires à la livraison

#### Sous-process: Ordre des envois

En fonction de la distance.

Objectifs :

* livrer plus rapidement

## Approche naive

Evidemment, les deux problèmes sont très liés, et chacun a besoin de la solution de l'autre pour trouver la 
solution optimale. Un manière de couper cette dépendence cyclique est de proposer une implémentation sub-optimale 
de l'un d'eux sur laquelle l'autre se reposera.

On choisira, en approche naive, pour résoudre la boucle de dépendance principale :

* de déterminer le drop-point de façon non-optimale 

Il en va de même avec les sous-problèmes de binding colis/drone et d'ordre des envois.
On choisira, en approche naive, pour résoudre la boucle de dépendance secondaire :

* d'attribuer à chaque colis le drone le plus petit non sous-dimensionné


## Idées pour améliorer les solutions

* Détermination du drop point:Choix des adresses livrées dans une tournée
	* Créer des clusters d'adresses proches (Problème théorique lié: cluster 2d points)
	Les clusters peuvent servir uniquement à restreindre le domaine de recherche.
	
La détermination des clusters doit être réalisée en fonction des différentes portées offertes par les drones, pondérée
 par le nombre dont dispose l'entreprise.