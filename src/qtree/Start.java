package qtree;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
/*
 * AUthor: Vinuta Hegde
 * Created Date: 08/17/2018
 * Modified Date: 08/17/2018
 * 
 * Main picks the input from the file and constructs a quad-tree to categorize the points in 2D plane
 * 
 */

public class Start {

	public static void main(String[] args) {
		try {
			File file = new File(".\\input\\GSV_spatialdata.txt"); // input file
			//File file = new File(".\\input\\sampledata"); // update Node.nodecapacity to 4 to analyze the result with sample data
			
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			//Create root node which spreads across (-100,-100) , (-100,100), (100,100), (100,-100)
			// 
			Node root = new Node(100.00,100.00,-100.00,-100.00,true,true);	// Node(x_max,y_max,x_min,y_min,is_leaf,is_root)		
			quadTree qt = new quadTree(); 
			
			String line;
			while ((line = bufferedReader.readLine()) != null) { //reading the file line by line.
				String[] row = line.split(" ", 0); 
				
				//parse and create a point object
				CoordintePoint cp = new CoordintePoint();
				cp.point_name = row[0];
				cp.x_axis = Double.parseDouble(row[1]);
				cp.y_axis = Double.parseDouble(row[2]); 
				
				//insert the point to tree
				qt.insert(root,root, cp);			
				}
			fileReader.close();
			
			//Traverse the tree 
			qt.iterate(root , "root->");	
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
