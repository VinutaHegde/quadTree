package qtree;
import java.util.ArrayList;


/*
 * AUthor: Vinuta Hegde
 * Created Date: 08/17/2018
 * Modified Date: 08/17/2018
 * 
 * Defines the Node.
 * 
 */


public class Node {
	public static int node_capacity = 2000;
	//public static int node_capacity = 4;

	Node NE;
	Node SE;
	Node NW;
	Node SW;
	
	double max_x_axis;
	double max_y_axis;
	
	double min_x_axis;
	double min_y_axis;
	
	boolean is_root;
	boolean is_leaf;
	
	ArrayList<CoordintePoint> points = new ArrayList<>();
	
	
	Node(double max_x_axis ,double max_y_axis, double min_x_axis, double min_y_axis,boolean is_root, boolean is_leaf ) {
		this.NE = null;
		this.SE = null;
		this.NW = null;
		this.SW = null;
		this.max_x_axis = max_x_axis;
		this.max_y_axis = max_y_axis;
		this.min_x_axis = min_x_axis;
		this.min_y_axis = min_y_axis;	
		this.is_root = is_root;
		this.is_leaf = is_leaf;
     }
}
