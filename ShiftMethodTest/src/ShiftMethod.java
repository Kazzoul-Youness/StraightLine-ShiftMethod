import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Random;


public class ShiftMethod {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int pointCount = 1;
		int k = 4;

		String reponse = "o";
		GraphNode graph = new GraphNode(k);

		// Ajouter les points
		MyPoint v1 = new MyPoint(0,0, "v1");	
		MyPoint v2 = new MyPoint(2*k-6,0,"v2");
		MyPoint v3 = new MyPoint(1,1, "v3");

		Random random = new Random();

		// Ajouter les points
		graph.addPoint(v1); graph.addPoint(v2); graph.addPoint(v3);

		// Ajouter les arretes
		//		graph.addEdge(v1, v3); graph.addEdge(v1, v2); graph.addEdge(v3, v2); 	

		List<MyPoint> orderedCyclePoints = Arrays.asList(
				graph.getPointByName("v1"), graph.getPointByName("v3"), graph.getPointByName("v2")
				);

		
		//*********************************************************************************		
		//System.out.print("Entrez la somme des points = ");
		//int NbSommets = scanner.nextInt();
		int NBtest = 50;
		int NbSommets = 200; 	    
		long[] executionTimesTest = new long[NBtest];
		long[] executionTimesTour = new long[NbSommets + 1];
		
		long startTimeTest, endTimeTest, startTimeTour, endTimeTour;		
		
		for (int test = 0; test < NBtest; test++) {			 

		for (int tour = 3; tour <= NbSommets; tour++) {

	//System.out.println("========================= Etape "+(tour-2)+"=========================");
// on va éviter l'aléatoire risque de tomber dans les cas interdit comme les cordes 
			int wp, wq;
//			do {
//				wp = random.nextInt(tour - 1);
//				wq = wp + 1 + random.nextInt(tour - wp - 1);
//			} while (wp == wq || wq == tour - 1);

//			String wpName = "v" + Integer.toString(wp+1);
//			String wqName = "v" + Integer.toString(wq+1);		
			//System.out.println("wpName: " + wpName + ", wqName: " + wqName);
			
			String wpName = "v" + tour;
		    String wqName = "v" + 2 ;
		    
//			 String wpName = "v1";
//			 String wqName = "v2";	

//			System.out.print("Entrez le nom du point wp : ");
//			String wpName = scanner.nextLine();
//			System.out.print("Entrez le nom du point wq : ");
//			String wqName = scanner.nextLine();

			updateWpWq(graph, wpName, wqName);

			// Recuperer les points wp et wq mis a jour
			MyPoint wpPoint = graph.getWpPoint();
			MyPoint wqPoint = graph.getWqPoint();

			//System.out.println(" wpPoint: " + wpPoint);

			double wpx = wpPoint.getX();
			double wqx = wqPoint.getX();
			double wpy = wpPoint.getY();
			double wqy = wqPoint.getY();  
			
			
			//============================ startTime ===========================================			
			//startTimeTest = System.nanoTime();			
			//==================================================================================
			startTimeTour = System.nanoTime();
		
			//==========================================================================================
			//								Decalage
			//==========================================================================================
			//								Milieu
			//==========================================================================================
			List<MyPoint> cheminPointsBetween = findPointsBetween(graph, wpName, wqName, orderedCyclePoints);
			//System.out.print(" Milieu : ");
			for (int i = 0; i < cheminPointsBetween.size(); i++) {
				MyPoint point = cheminPointsBetween.get(i);
				double x = point.getX();
				double y = point.getY();
				if (!point.getName().equals(wpName) && !point.getName().equals(wqName)) {
					point.setLocation(x + 1, y);
					// Deplacer les enfants du point courant (s'il en a)
					moveChildren(point, 1, 0);
				//	System.out.print(point.getName() + " ");
				}
			}
			//System.out.print(" \n");			
			//==========================================================================================
			//								Droit
			//==========================================================================================
			// Afficher les points apres wq
			List<MyPoint> pointsApresWq = apresWq(graph, wqName, orderedCyclePoints);
			//System.out.print(" Droit  : ");
			for (int i = 0; i < pointsApresWq.size(); i++) {
				MyPoint point = pointsApresWq.get(i);
				double x = point.getX();
				double y = point.getY();
				point.setLocation(x + 2, y);  
				// Deplacer les enfants du point courant (s'il en a)
				moveChildren(point, 2, 0);
			//	System.out.print(point.getName()+ " ");
			}
			//System.out.print(" \n");			
			//==========================================================================================
			//								Calcule et Insertion vk
			//==========================================================================================
			//System.out.println("\n=========      Shift / Decalage   ===============");
			wpx = wpPoint.getX();
			wqx = wqPoint.getX();
			wpy = wpPoint.getY();
			wqy = wqPoint.getY();   
			// Calculer les coordonnees du point d'intersection
			double x = (0.5 * (wqx + wpx + wqy - wpy));			
			double y = (0.5 * (wqx - wpx + wqy + wpy));
			pointCount++;

			// Creer le point d'intersection avec un nom qui s'incremente automatiquement
			String intersectionName = "v" + pointCount;
			MyPoint intersectionPoint = new MyPoint(x, y, intersectionName);

			// Ajouter le point d'intersection au graphe
			graph.addPoint(intersectionPoint);

			// Ajouter les aretes correspondantes
			// pour les tests de la complexité on n'ajout pas les arrêtes! pour optimizer le calcule!! 			
			//			graph.addEdge(wpPoint, intersectionPoint); 
			//			graph.addEdge(wqPoint, intersectionPoint);

			// Afficher le graphe
			//graph.printCoordonnee();



			//Ajouter le point vk à  la liste sur le contour 
			orderedCyclePoints = addPointBetween(graph, wpName, wqName, intersectionPoint, orderedCyclePoints);
			
			
			Map<MyPoint, Integer> pointIndices = new HashMap<>();
			for (int i = 0; i < orderedCyclePoints.size(); i++) {
				pointIndices.put(orderedCyclePoints.get(i), i);
			}

			// Ajouter les points du milieu en tant qu'enfants du sommet calculé vk
			boolean intersectionPointAdded = false;

			for (int i = 0; i < cheminPointsBetween.size(); i++) {
				MyPoint point = cheminPointsBetween.get(i);
				if (!point.getName().equals(wpName) && !point.getName().equals(wqName)) {
					intersectionPoint.addChild(point);
					//intersectionPoint.printChildren();
					Integer index = pointIndices.get(point);  // Utilisez la HashMap ici

					if (index != null) {
						if (!intersectionPointAdded) {
							orderedCyclePoints.set(index, intersectionPoint);
							pointIndices.put(intersectionPoint, index);  // Mettez à jour la HashMap
							intersectionPointAdded = true;
						} else {
							orderedCyclePoints.remove(index.intValue());
							// Mettez à jour les indices dans la HashMap
							for (int l = index; l < orderedCyclePoints.size(); l++) {
								MyPoint pointAfterRemoval = orderedCyclePoints.get(l);
								pointIndices.put(pointAfterRemoval, pointIndices.get(pointAfterRemoval) - 1);
							}
							pointIndices.remove(point);  // Retirez le point de la HashMap
							i--; // Decrement the index since we've just removed an element
						}
					}
				}
			}
			

			//==================================================================================
			//============================ endTime =============================================			
			endTimeTour = System.nanoTime();
			executionTimesTour[tour] = endTimeTour - startTimeTour;
			//==================================================================================

				
		k++;
		}	

			long sumTour = 0;
			for (int i = 3; i <= NbSommets; i++) {
				sumTour += executionTimesTour[i];
			}
		
			
		//endTimeTour = System.nanoTime();
		executionTimesTest[test] = +sumTour;
		//long duree = endTime - startTime;
		}
		
		long avg, sumTest=0;
		for (int i = 0; i < executionTimesTest.length; i++) {
			sumTest += executionTimesTest[i];
		}

		avg = sumTest/NBtest;
		//graph.printCoordonnee();

		System.out.println("Temps pour "+NbSommets+" sommets = "+ avg + " Nano");
		System.out.println("("+NbSommets+","+ avg + ")");



		//*********************************************************************************
		//*********************************************************************************	
	}

	
	
	
	
	
	
	
	//==========================================================================================
	//===========================  debut Optimisation ==========================================		
	public static void moveChildren(MyPoint parent, double dx, double dy) {
		for (MyPoint child : parent.getChildren()) {
			child.setLocation(child.getX() + dx, child.getY() + dy);
			moveChildren(child, dx, dy);
		}
	}

	//===========================  fin Optimisation ============================================
	//==========================================================================================
	//===========================  debut Optimisation ==========================================	
	public static List<MyPoint> addPointBetween(GraphNode graph, String firstPointName, 
			String secondPointName, MyPoint newPoint, List<MyPoint> orderedCyclePoints) {
		MyPoint firstPoint = graph.getPointByName(firstPointName);
		MyPoint secondPoint = graph.getPointByName(secondPointName);

		if (firstPoint == null || secondPoint == null) {
			return orderedCyclePoints;
		}

		int firstPointIndex = orderedCyclePoints.indexOf(firstPoint);

		if (firstPointIndex == -1) {
			return orderedCyclePoints;
		}

		int secondPointIndex = (firstPointIndex + 1) % orderedCyclePoints.size();
		if (!orderedCyclePoints.get(secondPointIndex).equals(secondPoint)) {
			return orderedCyclePoints;
		}

		List<MyPoint> newOrderedCyclePoints = new ArrayList<>(orderedCyclePoints);
		newOrderedCyclePoints.add(secondPointIndex, newPoint);

		return newOrderedCyclePoints;
	}

	//=========================	code optimisé	=============================================

	private static void updateWpWq(GraphNode graph, String wpName, String wqName) {
		MyPoint wpPoint = null;
		MyPoint wqPoint = null;

		for (MyPoint p : graph.getPoints()) {
			if (p.getName().equals(wpName)) {
				wpPoint = p;
				if (wqPoint != null) {
					break;
				}
			} else if (p.getName().equals(wqName)) {
				wqPoint = p;
				if (wpPoint != null) {
					break;
				}
			}
		}

		// Mettre a jour les points wp et wq de l'objet GraphNode
		graph.setWpPoint(wpPoint);
		graph.setWqPoint(wqPoint);
	}

	//==========================================================================================

	static List<MyPoint> apresWq(GraphNode graph, String wqName, List<MyPoint> orderedCyclePoints) {
		MyPoint wq = graph.getPointByName(wqName);
		MyPoint v2 = graph.getPointByName("v2");  // Get v2 only once
		int wqIndex = orderedCyclePoints.indexOf(wq);

		if (wq == null || wqIndex == -1) {
			return Collections.emptyList();
		}

		List<MyPoint> pointsApresWq = new ArrayList<>();
		int currentIndex = wqIndex;

		MyPoint point;
		do {
			point = orderedCyclePoints.get(currentIndex);
			pointsApresWq.add(point);
			currentIndex = (currentIndex + 1) % orderedCyclePoints.size();
		} while (!point.equals(v2));  // Use equals for comparing MyPoint objects

		return pointsApresWq;
	}


	//==========================================================================================
	static List<MyPoint> findPointsBetween(GraphNode graph, String wpName, String wqName, List<MyPoint> orderedCyclePoints) {
		List<MyPoint> path = new ArrayList<>();

		int wpIndex = -1;
		int wqIndex = -1;

		for (int i = 0; i < orderedCyclePoints.size(); i++) {
			MyPoint point = orderedCyclePoints.get(i);

			if (point.getName().equals(wpName)) {
				wpIndex = i;
			}

			if (point.getName().equals(wqName)) {
				wqIndex = i;
			}
		}

		if (wpIndex == -1 || wqIndex == -1) {
			return Collections.emptyList();
		}

		while (wpIndex != wqIndex) {
			path.add(orderedCyclePoints.get(wpIndex));
			wpIndex = (wpIndex + 1) % orderedCyclePoints.size();
		}
		path.add(orderedCyclePoints.get(wpIndex));

		return path;
	}

	//==========================================================================================
// dans le cadre du calcule et de l'optimisation on a pas besoin de cette methode qui calcule la partie de gauche
// Et d'ailheur l'algorithme ne parle pas de cette partie.
//	public static List<MyPoint> avantWp(GraphNode graph, String wpName, List<MyPoint> orderedCyclePoints) {
//		List<MyPoint> pointsAvantWp = new ArrayList<>();
//		int wpIndex = -1;
//
//		for (int i = 0; i < orderedCyclePoints.size(); i++) {
//			MyPoint currentPoint = orderedCyclePoints.get(i);
//			if (currentPoint != null && currentPoint.getName().equals(wpName)) {
//				wpIndex = i;
//				break;
//			}
//		}
//
//		if (wpIndex == -1) {
//			return Collections.emptyList();
//		}
//
//		int currentIndex = wpIndex;
//
//		while (true) {
//			MyPoint point = orderedCyclePoints.get(currentIndex);
//			if (point.getName().equals("v1")) {
//				pointsAvantWp.add(point);
//				break;
//			} else {
//				pointsAvantWp.add(point);
//				currentIndex = (currentIndex - 1 + orderedCyclePoints.size()) % orderedCyclePoints.size();
//			}
//		}
//
//		Collections.reverse(pointsAvantWp);
//		return pointsAvantWp;
//	}


	//===========================  fin Optimisation ================================================		






}