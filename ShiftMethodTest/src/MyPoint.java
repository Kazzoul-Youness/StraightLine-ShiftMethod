import java.awt.Point;
import java.util.HashSet;
import java.util.Set;

public class MyPoint extends Point {
    private String name;
    private MyPoint parent;
    private Set<MyPoint> children;

    public MyPoint(double x, double y, String name) {
        super((int) x, (int) y);
        this.name = name;
        this.parent = null;
        this.children = new HashSet<>();
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
    
    public Set<MyPoint> getChildren() {
        return children;
    }

    public void addChild(MyPoint child) {
        children.add(child);
    }
}



//import java.awt.Point;
//import java.util.ArrayList;
//import java.util.List;
//
//public class MyPoint extends Point {
//	private int x;
//    private int y;
//    private String name;
//    private MyPoint parent;
//    private List<MyPoint> children;
//
//    public MyPoint(double x, double y, String name) {
//        super((int) x, (int) y);
//        this.name = name;
//        this.parent = null;
//        this.children = new ArrayList<>(); // Ajoutez cette ligne pour initialiser la liste des enfants
//    }
//
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public MyPoint getParent() {
//        return parent;
//    }
//
//    public void setParent(MyPoint parent) {
//        this.parent = parent;
//    }
//    
//    public List<MyPoint> getChildren() {
//        return children;
//    }
//
//    public void addChild(MyPoint child) {
//        if (!children.contains(child)) {
//            children.add(child);
//        }
//    }
//   
//}