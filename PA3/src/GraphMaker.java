

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
/*
 * The GraphMaker handles making a graph by reading from a file.
 * The makeGraphFromFile method takes in the name of a file. 
 * The code should then read in the input file and produce the corresponding graph.
 * The first line of the file will be the number of vertices. 
 * The next lines will give the edges as a table where table(i,j) gives the 
 * edge weight between vertices i and j. 
 * The two provided files are in this format.
 * 
 * @author sspurlock
 * @version 2019-10-21
 */
public class GraphMaker {
	public AdjListGraph makeGraphFromFile(String fileName) {
		File test = new File(fileName);
		// Create a new directed AdjListGraph and read from the file to
		// add DijkstraVertex and Edge object to the graph.
		// TODO...
		// first line: number of vertices
		// second line gives table(i,j) along with the edge weights
		// Example of building a graph. Delete before turning in.
		AdjListGraph graph = new AdjListGraph(true);
		Scanner inputFile = null;
		try {
			inputFile = new Scanner(test);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int vertNumber= inputFile.nextInt();
		System.out.println("Number of vertices: " + vertNumber);
		for(int i= 0; i < fileName.length(); i ++) {
			if(i < vertNumber) {
				String vertexName = inputFile.next();
				Vertex a = graph.addVertex(new DijkstraVertex (vertexName));
				System.out.print("      " +vertexName);
			}

		}
		for(int j = 0; j < vertNumber; j++) {
			if(true) {
				String test1 = inputFile.next();
				for(int k = 0; k < vertNumber; k++) {
					double edgeWeight = inputFile.nextDouble();
					System.out.print("Edge weight:   " + edgeWeight);
					if(edgeWeight > 0) {
						ArrayList<Vertex>  vertexs= graph.getVertices();
						Vertex from = vertexs.get(j);
						Vertex to = vertexs.get(k);
						graph.addEdge(from, to, edgeWeight);
					}
				}
			}
			System.out.println();
		}
		
		return graph;
	}
}
