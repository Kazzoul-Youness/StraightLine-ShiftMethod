import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class MyPoint extends Point {
	private int x;
    private int y;
    private String name;
    private MyPoint parent;
    private List<MyPoint> children;
    
//    public MyPoint(int x, int y) {
//        this.x = x;
//        this.y = y;
//    }
       
//    public MyPoint(int x, int y, String name) {
//        super(x, y);
//        this.name = name;
//        this.parent = null;
//        this.children = new ArrayList<>();
//    }

    public MyPoint(double x, double y, String name) {
        super((int) x, (int) y);
        this.name = name;
        this.parent = null;
        this.children = new ArrayList<>(); // Ajoutez cette ligne pour initialiser la liste des enfants
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MyPoint getParent() {
        return parent;
    }

    public void setParent(MyPoint parent) {
        this.parent = parent;
    }
    
    public List<MyPoint> getChildren() {
        return children;
    }

    public void addChild(MyPoint child) {
        if (!children.contains(child)) {
            children.add(child);
        }
    }

    
//    public void printChildren() {
//        System.out.print("Parent " + name + " has children: ");
//        for (MyPoint child : children) {
//            System.out.print(child.getName() + " ");
//        }
//        System.out.println();
//    }
    
//    public double distanceTo(MyPoint other) {
//        double dx = this.x - other.x;
//        double dy = this.y - other.y;
//        return Math.sqrt(dx*dx + dy*dy);
//    }


        
}