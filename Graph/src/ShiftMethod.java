import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ShiftMethod {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int pointCount = 1,  k=4;		
		String reponse = "o";
		GraphNode graph = new GraphNode(k);

		// Ajouter les points
		MyPoint v1 = new MyPoint(0,0, "v1");	
		MyPoint v2 = new MyPoint(2*k-6,0,"v2");
		MyPoint v3 = new MyPoint(1,1, "v3");

		// Ajouter les points
		graph.addPoint(v1); graph.addPoint(v2); graph.addPoint(v3);

		// Ajouter les arretes
		graph.addEdge(v1, v3); graph.addEdge(v1, v2); graph.addEdge(v3, v2); 	

		List<MyPoint> orderedCyclePoints = Arrays.asList(
				graph.getPointByName("v1"), graph.getPointByName("v3"), graph.getPointByName("v2")
				);

		// Afficher le graphe initial
		graph.printCoordonnee();
		//graph.printArretes();

		while (reponse.equals("o")) {
			System.out.println("========================================");
			System.out.println("		 Etape "+(pointCount));
			System.out.println("========================================");

			// Afficher le graphe
			graph.printCoordonnee();
			//graph.printArretes();

			System.out.print("Entez le point wp : ");
			String wpName = scanner.nextLine();

			System.out.print("Enter le point wq : ");
			String wqName = scanner.nextLine();

			updateWpWq(graph, wpName, wqName);

			// Récuperer les points wp et wq mis à jour
			MyPoint wpPoint = graph.getWpPoint();
			MyPoint wqPoint = graph.getWqPoint();

			double wpx = wpPoint.getX();
			double wqx = wqPoint.getX();
			double wpy = wpPoint.getY();
			double wqy = wqPoint.getY();  

			//==========================================================================================
			//								Décalage
			System.out.println("\n=========   Division du graphe   ===============");
			//==========================================================================================

			//==========================================================================================
			//								Gauche
			//==========================================================================================
			List<MyPoint> pointsAvantWp = avantWp(graph, wpName, orderedCyclePoints);
			System.out.print(" Gauche : ");
			for (int i = 0; i < pointsAvantWp.size(); i++) {
				MyPoint point = pointsAvantWp.get(i);
				System.out.print(point.getName()+ " ");
				//System.out.print(", ");
			}
			System.out.print(" \n");
			//==========================================================================================
			//								Milieu
			//==========================================================================================
			List<MyPoint> cheminPointsBetween = findPointsBetween(graph, wpName, wqName, orderedCyclePoints);
			System.out.print(" Milieu : ");
			for (int i = 0; i < cheminPointsBetween.size(); i++) {
				MyPoint point = cheminPointsBetween.get(i);
				double x = point.getX();
				double y = point.getY();
				if (!point.getName().equals(wpName) && !point.getName().equals(wqName)) {
					point.setLocation(x + 1, y);

					// Déplacer les enfants du point courant (s'il en a)
					moveChildren(point, 1, 0);
					System.out.print(point.getName() + " ");
				}
			}
			System.out.print(" \n");			
			//==========================================================================================
			//								Droit
			//==========================================================================================
			// Afficher les points après wq
			List<MyPoint> pointsApresWq = apresWq(graph, wqName, orderedCyclePoints);
			System.out.print(" Droit  : ");
			for (int i = 0; i < pointsApresWq.size(); i++) {
				MyPoint point = pointsApresWq.get(i);
				double x = point.getX();
				double y = point.getY();
				point.setLocation(x + 2, y);  

				// Déplacer les enfants du point courant (s'il en a)
				moveChildren(point, 2, 0);
				System.out.print(point.getName()+ " ");
				//System.out.print(", ");
			}
			System.out.print(" \n");			
			//==========================================================================================
			//								Calcule et Insertion vk
			//==========================================================================================
			System.out.println("\n=========      Shift / Decalage   ===============");
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
			graph.addEdge(wpPoint, intersectionPoint);
			graph.addEdge(wqPoint, intersectionPoint);

			// Afficher les points et leurs coordonnées
			// Afficher le graphe
			graph.printCoordonnee();

			//Ajouter le point vk à la liste sur le conteur 
			orderedCyclePoints = addPointBetween(graph, wpName, wqName, intersectionPoint, orderedCyclePoints);


			// Ajouter les points du milieu en tant qu'enfants du sommet calculé vk
			boolean intersectionPointAdded = false;

			for (int i = 0; i < cheminPointsBetween.size(); i++) {
				MyPoint point = cheminPointsBetween.get(i);
				if (!point.getName().equals(wpName) && !point.getName().equals(wqName)) {
					intersectionPoint.addChild(point);
					//intersectionPoint.printChildren();
					int index = orderedCyclePoints.indexOf(point);

					if (index != -1) {
						if (!intersectionPointAdded) {
							orderedCyclePoints.set(index, intersectionPoint);
							intersectionPointAdded = true;
						} else {
							orderedCyclePoints.remove(index);
							i--; // Decrement the index since we've just removed an element
						}
					}
				}
			}


			// Tester la liste
			//System.out.println("========= liste du conteur  ===============");
			System.out.println("========= liste du conteur  ===============");
			for (MyPoint point : orderedCyclePoints) {
				System.out.print(point.getName());
				System.out.print(" ");
			}
			System.out.println("\n");


			System.out.println("========================================");
			System.out.println("		 Fin étape "+(pointCount-1));
			System.out.println("========================================");

			System.out.print("Voulez-vous continuer ? (o/n) ");
			reponse = scanner.nextLine();

			k++;  // augmenter la taille du graphe 		 			
		}
		System.out.print("\n Au revoir \n");



	}

	//==========================================================================================
	public static void moveChildren(MyPoint parent, double dx, double dy) {
		List<MyPoint> children = parent.getChildren();
		for (MyPoint child : children) {
			double x = child.getX();
			double y = child.getY();
			child.setLocation(x + dx, y + dy);
			moveChildren(child, dx, dy);
		}
	}


	//==========================================================================================
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



	//==========================================================================================
	public static List<MyPoint> avantWp(GraphNode graph, String wpName, List<MyPoint> orderedCyclePoints) {
		MyPoint wp = graph.getPointByName(wpName);
		//System.out.println(" wp : "+wp);
		if (wp == null) {
			return Collections.emptyList();
		}

		List<MyPoint> pointsAvantWp = new ArrayList<>();
		int wpIndex = -1;
		for (int i = 0; i < orderedCyclePoints.size(); i++) {
			MyPoint currentPoint = orderedCyclePoints.get(i);
			if (currentPoint != null && currentPoint.getName().equals(wp.getName())) {
				wpIndex = i;
				break;
			}
		}

		if (wpIndex == -1) {
			return Collections.emptyList();
		}

		int currentIndex = wpIndex;
		//System.out.println(" wpIndex : "+wpIndex);

		while (true) {
			MyPoint point = orderedCyclePoints.get(currentIndex);
			if (point == graph.getPointByName("v1")) {
				//System.out.println(" point : "+point);
				pointsAvantWp.add(point);
				break;
			} else {
				pointsAvantWp.add(point);
				currentIndex = (currentIndex - 1 + orderedCyclePoints.size()) % orderedCyclePoints.size();
				//System.out.println(" point : "+point+"  : "+currentIndex);
			}
		}

		Collections.reverse(pointsAvantWp);
		return pointsAvantWp;
	}


	//==========================================================================================
	static List<MyPoint> findPointsBetween(GraphNode graph, String wpName, String wqName, List<MyPoint> orderedCyclePoints) {
		MyPoint wp = graph.getPointByName(wpName);
		MyPoint wq = graph.getPointByName(wqName);
		List<MyPoint> path = new ArrayList<>();


		int wpIndex = orderedCyclePoints.indexOf(wp);
		int wqIndex = orderedCyclePoints.indexOf(wq);

		if (wp == null || wq == null || wpIndex == -1 || wqIndex == -1) {
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
	static List<MyPoint> apresWq(GraphNode graph, String wqName, List<MyPoint> orderedCyclePoints) {
		MyPoint wq = graph.getPointByName(wqName);
		int wqIndex = orderedCyclePoints.indexOf(wq);
		List<MyPoint> pointsApresWq = new ArrayList<>();
		int currentIndex = wqIndex;

		if (wq == null || wqIndex == -1) {
			return Collections.emptyList();
		}

		while (true) {
			MyPoint point = orderedCyclePoints.get(currentIndex);
			if (point == graph.getPointByName("v2")) {
				pointsApresWq.add(point);
				break;
			} else {
				pointsApresWq.add(point);
				currentIndex = (currentIndex + 1) % orderedCyclePoints.size();
			}
		}

		return pointsApresWq;
	}
	//==========================================================================================



	private static void updateWpWq(GraphNode graph, String wpName, String wqName) {
		MyPoint wpPoint = null;
		MyPoint wqPoint = null;

		for (MyPoint p : graph.getPoints()) {
			if (p.getName().equals(wpName)) {
				wpPoint = p;
			} else if (p.getName().equals(wqName)) {
				wqPoint = p;
			}
		}

		if (wpPoint == null) {
			System.out.println("Le point wp n'existe pas !");
		}
		if (wqPoint == null) {
			System.out.println("Le point wq n'existe pas !");
		}

		if (wpPoint != null && wqPoint != null) {
			// VÃ©rifier que wp est infÃ©rieur Ã  wq, et si ce n'est pas le cas, Ã©changer les deux points
			if (wpPoint.getX() > wqPoint.getX()) {
				System.out.println("Attention: wp est supÃ©rieur Ã  wq. Les points seront Ã©changÃ©s.");
				MyPoint tempPoint = wpPoint;
				wpPoint = wqPoint;
				wqPoint = tempPoint;
			}
		}

		// Mettre Ã  jour les points wp et wq de l'objet GraphNode
		graph.setWpPoint(wpPoint);
		graph.setWqPoint(wqPoint);
	}



}