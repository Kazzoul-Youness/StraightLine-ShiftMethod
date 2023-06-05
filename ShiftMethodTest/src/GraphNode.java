import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class GraphNode {
	private Map<String, MyPoint> points;

	private int pointCount;
	private MyPoint wpPoint;
	private MyPoint wqPoint;


	public GraphNode(int nbPoints) {
		//points = new ArrayList<>();
		points = new HashMap<>();
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

		// Ajouter le nouveau sommet
		points.put(p.getName(), p);
	}


	public void printCoordonnee() {
		System.out.println("	Sommets :");
		for (Map.Entry<String, MyPoint> entry : points.entrySet()) {
			MyPoint p = entry.getValue();
			System.out.printf("		%s : (%d,%d) \n", p.getName(), (int) p.getX(), (int) p.getY());
		}
		System.out.println();
	}



	public MyPoint getPointByName(String name) {
		return points.get(name);
	}

	public List<MyPoint> getPoints() {
		return new ArrayList<>(points.values());
	}



}