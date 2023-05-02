
# "Straight line" d'un graphe planaire - Algorithm Shift method

## Table of contents
- [General info](#general-info)
- [Technologies](#technologies)
- [Algorithm shift method](#algorithm-shift-method)
- [Graphe planaire](#graphe-planaire)
- [Test](#test)


## General info
Mémoire pour l'obtention du diplome Master en sciences informatiques à l'UMONS, année académique 2022-2023:<br/>
Sujet : Représentation "Straight line" d'un graphe planaire - Algorithm Shift method
###### Auteurs: Youness KAZZOUL
###### Encadré par monsieur Jef WIJSEN
	
  
## Technologies
Ce projet est créé avec JavaSE-1.8


## Algorithm shift method
* C'est une implémentation d'algorithme Shift method
![Algorithm Shift method](https://github.com/NessYou-cyber/StraightLine-ShiftMethod/blob/main/images/Capture%20d%E2%80%99%C3%A9cran%20du%202023-05-01%2023-15-29.png)

<br/>

## Graphe planaire
* l'ordre canonique est : v1, v2, .... v16, comme montre l'image,
![Graphe avec l'ordre canonique](https://github.com/NessYou-cyber/StraightLine-ShiftMethod/blob/main/images/Capture%20d%E2%80%99%C3%A9cran%20du%202023-04-28%2010-19-36.png)

On introduit les sommets un par un, en choisissant leurs emplacement comme le montre le test [Test](#test).
On a au départ le graphe (v1, v2, v3), on a v4 entre v3 et v2, on a v5 entre v4 et v2 ....

	

## Test
```
	Sommets :
		v1 : (0,0) 
		v2 : (2,0) 
		v3 : (1,1) 

---------------------------
		 Etape 1
---------------------------		 
     
	Sommets :
		v1 : (0,0) 
		v2 : (2,0) 
		v3 : (1,1) 

Entez le point wp : v3
Enter le point wq : v2

   Division du graphe   
 Gauche : v1 v3  
 Milieu :  
 Droit  : v2  

      Shift / Decalage   
	Sommets :
		v1 : (0,0) 
		v2 : (4,0) 
		v3 : (1,1) 
		v4 : (2,2) 

 liste de frontière  
v1 v3 v4 v2 

		 Fin étape 1

Voulez-vous continuer ? (o/n) o

```
// étape 1, dans la photo on voit le décalage du sommet v2 de 2 unité vers la droite et insertion de v4

![étape 1](https://github.com/NessYou-cyber/StraightLine-ShiftMethod/blob/main/images/Capture%20d%E2%80%99%C3%A9cran%20du%202023-05-01%2023-31-45.png)
```



---------------------------
		 Etape 2
---------------------------
	Sommets :
		v1 : (0,0) 
		v2 : (4,0) 
		v3 : (1,1) 
		v4 : (2,2) 

Entez le point wp : v4
Enter le point wq : v2

   Division du graphe   
 Gauche : v1 v3 v4  
 Milieu :  
 Droit  : v2  

      Shift / Decalage   
	Sommets :
		v1 : (0,0) 
		v2 : (6,0) 
		v3 : (1,1) 
		v4 : (2,2) 
		v5 : (3,3) 

 liste de frontière  
v1 v3 v4 v5 v2 


		 Fin étape 2

Voulez-vous continuer ? (o/n) o

---------------------------
		 Etape 3
---------------------------
	Sommets :
		v1 : (0,0) 
		v2 : (6,0) 
		v3 : (1,1) 
		v4 : (2,2) 
		v5 : (3,3) 

Entez le point wp : v5
Enter le point wq : v2

   Division du graphe   
 Gauche : v1 v3 v4 v5  
 Milieu :  
 Droit  : v2  

      Shift / Decalage   
	Sommets :
		v1 : (0,0) 
		v2 : (8,0) 
		v3 : (1,1) 
		v4 : (2,2) 
		v5 : (3,3) 
		v6 : (4,4) 

 liste de frontière  
v1 v3 v4 v5 v6 v2 


		 Fin étape 3

Voulez-vous continuer ? (o/n) o

---------------------------
		 Etape 4
---------------------------
	Sommets :
		v1 : (0,0) 
		v2 : (8,0) 
		v3 : (1,1) 
		v4 : (2,2) 
		v5 : (3,3) 
		v6 : (4,4) 

Entez le point wp : v1
Enter le point wq : v3

   Division du graphe   
 Gauche : v1  
 Milieu :  
 Droit  : v3 v4 v5 v6 v2  

      Shift / Decalage   
	Sommets :
		v1 : (0,0) 
		v2 : (10,0) 
		v3 : (3,1) 
		v4 : (4,2) 
		v5 : (5,3) 
		v6 : (6,4) 
		v7 : (2,2) 

 liste de frontière  
v1 v7 v3 v4 v5 v6 v2 


		 Fin étape 4

Voulez-vous continuer ? (o/n) o

---------------------------
		 Etape 5
---------------------------
	Sommets :
		v1 : (0,0) 
		v2 : (10,0) 
		v3 : (3,1) 
		v4 : (4,2) 
		v5 : (5,3) 
		v6 : (6,4) 
		v7 : (2,2) 

Entez le point wp : v1
Enter le point wq : v7

   Division du graphe   
 Gauche : v1  
 Milieu :  
 Droit  : v7 v3 v4 v5 v6 v2  

      Shift / Decalage   
	Sommets :
		v1 : (0,0) 
		v2 : (12,0) 
		v3 : (5,1) 
		v4 : (6,2) 
		v5 : (7,3) 
		v6 : (8,4) 
		v7 : (4,2) 
		v8 : (3,3) 

 liste de frontière  
v1 v8 v7 v3 v4 v5 v6 v2 


		 Fin étape 5

Voulez-vous continuer ? (o/n) o

---------------------------
		 Etape 6
---------------------------
	Sommets :
		v1 : (0,0) 
		v2 : (12,0) 
		v3 : (5,1) 
		v4 : (6,2) 
		v5 : (7,3) 
		v6 : (8,4) 
		v7 : (4,2) 
		v8 : (3,3) 

Entez le point wp : v1
Enter le point wq : v8

   Division du graphe   
 Gauche : v1  
 Milieu :  
 Droit  : v8 v7 v3 v4 v5 v6 v2  

      Shift / Decalage   
	Sommets :
		v1 : (0,0) 
		v2 : (14,0) 
		v3 : (7,1) 
		v4 : (8,2) 
		v5 : (9,3) 
		v6 : (10,4) 
		v7 : (6,2) 
		v8 : (5,3) 
		v9 : (4,4) 

 liste de frontière  
v1 v9 v8 v7 v3 v4 v5 v6 v2 


		 Fin étape 6

Voulez-vous continuer ? (o/n) o

```
// étape 6, insertion de v9, jusquà ici on a rien de spécial, on a le contour v1 v9 v8 v7 v3 v4 v5 v6 v2 , c'est à partir de maintenant qu'on commence à ajouter des éléments en dehor du conteur comme le montre l'étape suivante, avec l'insertion de v10 le parents des enfants v8, v7 et v3

![étape 6](https://github.com/NessYou-cyber/StraightLine-ShiftMethod/blob/main/images/Capture%20d%E2%80%99%C3%A9cran%20du%202023-04-28%2011-07-28.png)
```

---------------------------
		 Etape 7
---------------------------
	Sommets :
		v1 : (0,0) 
		v2 : (14,0) 
		v3 : (7,1) 
		v4 : (8,2) 
		v5 : (9,3) 
		v6 : (10,4) 
		v7 : (6,2) 
		v8 : (5,3) 
		v9 : (4,4) 

Entez le point wp : v9
Enter le point wq : v4

   Division du graphe   
 Gauche : v1 v9  
 Milieu : v8 v7 v3  
 Droit  : v4 v5 v6 v2  

      Shift / Decalage   
	Sommets :
		v1 : (0,0) 
		v2 : (16,0) 
		v3 : (8,1) 
		v4 : (10,2) 
		v5 : (11,3) 
		v6 : (12,4) 
		v7 : (7,2) 
		v8 : (6,3) 
		v9 : (4,4) 
		v10 : (6,6) 

 liste de frontière  
v1 v9 v10 v4 v5 v6 v2 


		 Fin étape 7

Voulez-vous continuer ? (o/n) o

```
étape 7, dans la photo on veut inserer v10, on a les sommets : 
à gauche : v1 v9
au milieu: v8 v7 v3    => décalage +1 vers la droite
à droite : v4 v5 v6 v2 => décalage +2 vers la droite

Apres décalage on inser v10,

On a aussi la liste de frontière qui change, pour êtres commes ça : v1 v9 v10 v4 v5 v6 v2 
et donc v10 devient le parent des sommets v8 v7 v3  

![étape 7](https://github.com/NessYou-cyber/StraightLine-ShiftMethod/blob/main/images/Capture%20d%E2%80%99%C3%A9cran%20du%202023-04-28%2011-07-41.png)
```

---------------------------
		 Etape 8

	Sommets :
		v1 : (0,0) 
		v2 : (16,0) 
		v3 : (8,1) 
		v4 : (10,2) 
		v5 : (11,3) 
		v6 : (12,4) 
		v7 : (7,2) 
		v8 : (6,3) 
		v9 : (4,4) 
		v10 : (6,6) 

Entez le point wp : v4
Enter le point wq : v6

   Division du graphe   
 Gauche : v1 v9 v10 v4  
 Milieu : v5  
 Droit  : v6 v2  

      Shift / Decalage   
	Sommets :
		v1 : (0,0) 
		v2 : (18,0) 
		v3 : (8,1) 
		v4 : (10,2) 
		v5 : (12,3) 
		v6 : (14,4) 
		v7 : (7,2) 
		v8 : (6,3) 
		v9 : (4,4) 
		v10 : (6,6) 
		v11 : (13,5) 

 liste de frontière  
v1 v9 v10 v4 v11 v6 v2 


		 Fin étape 8

Voulez-vous continuer ? (o/n) o

```
étape 7, insertion de v11

![étape 7](https://github.com/NessYou-cyber/StraightLine-ShiftMethod/blob/main/images/v11.png)
```

---------------------------
		 Etape 9

	Sommets :
		v1 : (0,0) 
		v2 : (18,0) 
		v3 : (8,1) 
		v4 : (10,2) 
		v5 : (12,3) 
		v6 : (14,4) 
		v7 : (7,2) 
		v8 : (6,3) 
		v9 : (4,4) 
		v10 : (6,6) 
		v11 : (13,5) 

Entez le point wp : v4
Enter le point wq : v11

   Division du graphe   
 Gauche : v1 v9 v10 v4  
 Milieu :  
 Droit  : v11 v6 v2  

      Shift / Decalage   
	Sommets :
		v1 : (0,0) 
		v2 : (20,0) 
		v3 : (8,1) 
		v4 : (10,2) 
		v5 : (14,3) 
		v6 : (16,4) 
		v7 : (7,2) 
		v8 : (6,3) 
		v9 : (4,4) 
		v10 : (6,6) 
		v11 : (15,5) 
		v12 : (14,6) 

 liste de frontière  
v1 v9 v10 v4 v12 v11 v6 v2 


		 Fin étape 9

Voulez-vous continuer ? (o/n) o

```
étape 9, insertion de v12

![étape 79](https://github.com/NessYou-cyber/StraightLine-ShiftMethod/blob/main/images/v12.png)
```

---------------------------
		 Etape 10

	Sommets :
		v1 : (0,0) 
		v2 : (20,0) 
		v3 : (8,1) 
		v4 : (10,2) 
		v5 : (14,3) 
		v6 : (16,4) 
		v7 : (7,2) 
		v8 : (6,3) 
		v9 : (4,4) 
		v10 : (6,6) 
		v11 : (15,5) 
		v12 : (14,6) 

Entez le point wp : v10
Enter le point wq : v12

   Division du graphe   
 Gauche : v1 v9 v10  
 Milieu : v4  
 Droit  : v12 v11 v6 v2  

      Shift / Decalage   
	Sommets :
		v1 : (0,0) 
		v2 : (22,0) 
		v3 : (8,1) 
		v4 : (11,2) 
		v5 : (16,3) 
		v6 : (18,4) 
		v7 : (7,2) 
		v8 : (6,3) 
		v9 : (4,4) 
		v10 : (6,6) 
		v11 : (17,5) 
		v12 : (16,6) 
		v13 : (11,11) 

 liste de frontière  
v1 v9 v10 v13 v12 v11 v6 v2 


		 Fin étape 10

Voulez-vous continuer ? (o/n) o

```
étape 10, insertion de v13

![étape 10](https://github.com/NessYou-cyber/StraightLine-ShiftMethod/blob/main/images/v13.png)
```

---------------------------
		 Etape 11

	Sommets :
		v1 : (0,0) 
		v2 : (22,0) 
		v3 : (8,1) 
		v4 : (11,2) 
		v5 : (16,3) 
		v6 : (18,4) 
		v7 : (7,2) 
		v8 : (6,3) 
		v9 : (4,4) 
		v10 : (6,6) 
		v11 : (17,5) 
		v12 : (16,6) 
		v13 : (11,11) 

Entez le point wp : v13
Enter le point wq : v6

   Division du graphe   
 Gauche : v1 v9 v10 v13  
 Milieu : v12 v11  
 Droit  : v6 v2  

      Shift / Decalage   
	Sommets :
		v1 : (0,0) 
		v2 : (24,0) 
		v3 : (8,1) 
		v4 : (11,2) 
		v5 : (17,3) 
		v6 : (20,4) 
		v7 : (7,2) 
		v8 : (6,3) 
		v9 : (4,4) 
		v10 : (6,6) 
		v11 : (18,5) 
		v12 : (17,6) 
		v13 : (11,11) 
		v14 : (12,12) 

 liste de frontière  
v1 v9 v10 v13 v14 v6 v2 


		 Fin étape 11

Voulez-vous continuer ? (o/n) o

```
étape 11, insertion de v14

![étape 11](https://github.com/NessYou-cyber/StraightLine-ShiftMethod/blob/main/images/v14.png)
```

---------------------------
		 Etape 12

	Sommets :
		v1 : (0,0) 
		v2 : (24,0) 
		v3 : (8,1) 
		v4 : (11,2) 
		v5 : (17,3) 
		v6 : (20,4) 
		v7 : (7,2) 
		v8 : (6,3) 
		v9 : (4,4) 
		v10 : (6,6) 
		v11 : (18,5) 
		v12 : (17,6) 
		v13 : (11,11) 
		v14 : (12,12) 

Entez le point wp : v1
Enter le point wq : v13

   Division du graphe   
 Gauche : v1  
 Milieu : v9 v10  
 Droit  : v13 v14 v6 v2  

      Shift / Decalage   
	Sommets :
		v1 : (0,0) 
		v2 : (26,0) 
		v3 : (9,1) 
		v4 : (13,2) 
		v5 : (19,3) 
		v6 : (22,4) 
		v7 : (8,2) 
		v8 : (7,3) 
		v9 : (5,4) 
		v10 : (7,6) 
		v11 : (20,5) 
		v12 : (19,6) 
		v13 : (13,11) 
		v14 : (14,12) 
		v15 : (12,12) 

 liste de frontière  
v1 v15 v13 v14 v6 v2 


		 Fin étape 12

Voulez-vous continuer ? (o/n) o

```
étape 12, insertion de v15

![étape 12](https://github.com/NessYou-cyber/StraightLine-ShiftMethod/blob/main/images/v15.png)
```

---------------------------
		 Etape 13

	Sommets :
		v1 : (0,0) 
		v2 : (26,0) 
		v3 : (9,1) 
		v4 : (13,2) 
		v5 : (19,3) 
		v6 : (22,4) 
		v7 : (8,2) 
		v8 : (7,3) 
		v9 : (5,4) 
		v10 : (7,6) 
		v11 : (20,5) 
		v12 : (19,6) 
		v13 : (13,11) 
		v14 : (14,12) 
		v15 : (12,12) 

Entez le point wp : v1
Enter le point wq : v2

   Division du graphe   
 Gauche : v1  
 Milieu : v15 v13 v14 v6  
 Droit  : v2  

      Shift / Decalage   
	Sommets :
		v1 : (0,0) 
		v2 : (28,0) 
		v3 : (10,1) 
		v4 : (14,2) 
		v5 : (20,3) 
		v6 : (23,4) 
		v7 : (9,2) 
		v8 : (8,3) 
		v9 : (6,4) 
		v10 : (8,6) 
		v11 : (21,5) 
		v12 : (20,6) 
		v13 : (14,11) 
		v14 : (15,12) 
		v15 : (13,12) 
		v16 : (14,14) 

 liste de frontière  
v1 v16 v2 



		 Fin étape 13


Voulez-vous continuer ? (o/n) 
```
étape 13, insertion de v16

![étape 13](https://github.com/NessYou-cyber/StraightLine-ShiftMethod/blob/main/images/v16.png)

