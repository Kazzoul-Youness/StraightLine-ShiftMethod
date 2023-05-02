import java.util.ArrayList;

import java.util.List;

public class GraphNode {
	private List<MyPoint> points;
	private int[][] adjMatrix;
	private int pointCount;
	private MyPoint wpPoint;
	private MyPoint wqPoint;


	public GraphNode(int nbPoints) {
		points = new ArrayList<>();
		adjMatrix = new int[nbPoints][nbPoints];
		pointCount = 0;
	}

	public MyPoint getWpPoint() {
		return wpPoint;
	}

	public MyPoint getWqPoint() {
		return wqPoint;
	}

	public void setWpPoint(MyPoint wpPoint) {
		this.wpPoint = wpPoint;
	}

	public void setWqPoint(MyPoint wqPoint) {
		this.wqPoint = wqPoint;
	}


	public void addPoint(MyPoint p) {
		pointCount++;
		p.setName("v" + pointCount);

		if (!points.isEmpty()) {
			MyPoint parent = points.get(points.size() - 1);
			p.setParent(parent);
		}

		// Crï¿œer une nouvelle matrice d'adjacence temporaire avec une taille qui est ï¿œgale ï¿œ la taille actuelle + 1
		int[][] newAdjMatrix = new int[adjMatrix.length + 1][adjMatrix.length + 1];

		// Copier les valeurs de la matrice d'adjacence existante dans la nouvelle matrice temporaire
		for (int i = 0; i < adjMatrix.length; i++) {
			for (int j = 0; j < adjMatrix.length; j++) {
				newAdjMatrix[i][j] = adjMatrix[i][j];
			}
		}

		// Remplacer la matrice d'adjacence existante par la nouvelle matrice temporaire
		adjMatrix = newAdjMatrix;

		// Ajouter le nouveau sommet
		points.add(p);
	}


	public List<MyPoint> getPoints() {
		return points;
	}



	public void printCoordonnee() {
		System.out.println("	Sommets :");
		for (MyPoint p : points) {
			//if(p.getName() != p.getParent().getName()) {
			System.out.printf("		%s : (%d,%d) \n", p.getName(), (int) p.getX(), (int) p.getY());
			//}
			//else System.out.printf("		%s : (%d,%d) \n", p.getName(), (int) p.getX(), 
			//(int) p.getY());
		}
		System.out.println();
	}



	public void printArretes() {
		System.out.println("	Arretes :");
		for (int i = 0; i < adjMatrix.length; i++) {
			for (int j = i + 1; j < adjMatrix[i].length; j++) {
				if (adjMatrix[i][j] == 1) {
					System.out.printf(" (%s,%s) ", points.get(i).getName(), points.get(j).getName());
				}
			}
			System.out.println();
		}
	}


	public void addEdge(MyPoint p1, MyPoint p2) {
		int i = points.indexOf(p1);
		int j = points.indexOf(p2);
		if (i < j) {
			adjMatrix[i][j] = 1;
		} else {
			adjMatrix[j][i] = 1;
		}
	}


	public List<MyPoint> getNeighbors(MyPoint point) {
		List<MyPoint> neighbors = new ArrayList<MyPoint>();
		for (MyPoint p : points) {
			if (!p.equals(point)) {
				for (int i = 0; i < adjMatrix.length; i++) {
					if (adjMatrix[points.indexOf(point)][i] == 1 && points.get(i).equals(p)) {
						neighbors.add(p);
					}
				}
			}
		}
		return neighbors;
	}


	public MyPoint getPointByName(String name) {
		for (MyPoint p : points) {
			if (p.getName().equals(name)) {
				return p;
			}
		}
		return null;
	}


	public List<MyPoint> getBorderPoints() {
		List<MyPoint> borderPoints = new ArrayList<>();
		for (MyPoint point : points) {
			if (point.getX() == 0 || point.getX() == 16) {
				borderPoints.add(point);
			}
		}
		return borderPoints;
	}


}