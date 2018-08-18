package qtree;

/*
 * AUthor: Vinuta Hegde
 * Created Date: 08/17/2018
 * Modified Date: 08/17/2018
 * 
 * Available methods: 
 * 	iterate(current_node,path) 
 *  insert(root, current_node, coordinte_point )
 *  search(current_node, search_point, path)
 *  delete(current_node, delete_point, path)
 * 
 */

public class quadTree {

	public void iterate(Node current_node, String path) {
		if (current_node == null) {
			return;
		} else if (current_node.is_leaf == true) {
			System.out.println(path + current_node.points.size());
			return;
		} else if (current_node.is_leaf == false) {
			iterate(current_node.NE, path + "NE->");
			iterate(current_node.SE, path + "SE->");
			iterate(current_node.NW, path + "NW->");
			iterate(current_node.SW, path + "SW->");
		}
	}

	public void insert(Node root, Node current_node, CoordintePoint cp) {
		if (current_node.is_leaf == true) { // if the node is leaf node 
			if (current_node.points.size() < Node.node_capacity) { // and has capacity 
				current_node.points.add(cp); // add the point
				return; 
			} else {  
				
				// if node exceeds the capacity, split the node into 4 more.
				
				Double x_mid = (current_node.max_x_axis + current_node.min_x_axis) / 2;
				Double y_mid = (current_node.max_y_axis + current_node.min_y_axis) / 2;

				current_node.NE = new Node(current_node.max_x_axis, current_node.max_y_axis, x_mid, y_mid, false, true);
				current_node.SE = new Node(current_node.max_x_axis, y_mid, x_mid, current_node.min_y_axis, false, true);
				current_node.NW = new Node(x_mid, current_node.max_y_axis, current_node.min_x_axis, y_mid, false, true);
				current_node.SE = new Node(x_mid, y_mid, current_node.min_x_axis, current_node.min_y_axis, false, true);

				for (CoordintePoint cpts : current_node.points) {
					if (cpts.x_axis >= x_mid) {
						if (cpts.y_axis >= y_mid) {
							insert(current_node, current_node.NE, cpts);
						} else if (cpts.y_axis < y_mid) {
							insert(current_node, current_node.SE, cpts);
						}
					} else if (cpts.x_axis < x_mid) {
						if (cpts.y_axis >= y_mid) {
							insert(current_node, current_node.NW, cpts);
						} else if (cpts.y_axis < y_mid) {
							insert(current_node, current_node.SW, cpts);
						}
					}
				}

				// clear the current node points
				current_node.is_leaf = false;
				current_node.points.clear();
				
				// Insert current point to be inserted into right node
				if (cp.x_axis >= x_mid) {
					if (cp.y_axis >= y_mid) {
						insert(current_node, current_node.NE, cp);
					} else if (cp.y_axis < y_mid) {
						insert(current_node, current_node.SE, cp);
					}
				} else if (cp.x_axis < x_mid) {
					if (cp.y_axis >= y_mid) {
						insert(current_node, current_node.NW, cp);
					} else if (cp.y_axis < y_mid) {
						insert(current_node, current_node.SW, cp);
					}
				}
				
			}
		} else if (current_node.is_leaf == false) { 
			
			// if current node is not a leaf node, go to the right child based on the coordinate values ( x and y values) 

			Double x_mid = (current_node.max_x_axis + current_node.min_x_axis) / 2;
			Double y_mid = (current_node.max_y_axis + current_node.min_y_axis) / 2;
			if (cp.x_axis >= x_mid) {
				if (cp.y_axis >= y_mid) {
					insert(current_node, current_node.NE, cp);
				} else if (cp.y_axis < y_mid) {
					insert(current_node, current_node.SE, cp);
				}
			} else if (cp.x_axis < x_mid) {
				if (cp.y_axis >= y_mid) {
					insert(current_node, current_node.NW, cp);
				} else if (cp.y_axis < y_mid) {
					insert(current_node, current_node.SW, cp);
				}
			}
		}
	}

	// search any coordinate point
	public void search(Node current_node, CoordintePoint search_point, String path) {
		if (current_node == null) {
			return;
		} else if (current_node.is_leaf == true) {
			if (current_node.points.contains(search_point)) {
				System.out.println(search_point.point_name + ":(" + search_point.x_axis + "," + search_point.y_axis
						+ ") found in " + path);
			}
			return;
		} else if (current_node.is_leaf == false) {

			search(current_node.NE, search_point, path + "NE->");
			search(current_node.SE, search_point, path + "SE->");
			search(current_node.NW, search_point, path + "NW->");
			search(current_node.SW, search_point, path + "SW->");
		}
	}

	// Delete any coordinate point
	public void delete(Node current_node, CoordintePoint delete_point, String path) {
		if (current_node == null) {
			return;
		} else if (current_node.is_leaf == true) {
			if (current_node.points.contains(delete_point)) {
				System.out.println("Deleting " + delete_point.point_name + ":(" + delete_point.x_axis + ","
						+ delete_point.y_axis + ") from " + path);
				current_node.points.remove(delete_point);
			}
			return;
		} else if (current_node.is_leaf == false) {

			delete(current_node.NE, delete_point, path + "NE->");
			delete(current_node.SE, delete_point, path + "SE->");
			delete(current_node.NW, delete_point, path + "NW->");
			delete(current_node.SW, delete_point, path + "SW->");
		}
	}

}
