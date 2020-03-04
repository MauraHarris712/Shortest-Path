/*
 * Uses the GraphMaker to make the graph, asks the user for the source vertex 
 * and destination vertex, and then runs Dijkstra's algorithm. The shortest 
 * path length as well as the actual path should be printed to the screen, 
 * then the program should terminate. See the examples in assignment for the 
 * appropriate formatting.
 * 
 * @author sspurlock
 * @version 2019-10-21
 */

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Dijkstras {
	HashMap<DijkstraVertex, DijkstraVertex> parent = new HashMap<DijkstraVertex, DijkstraVertex>();

	// Constructor: prompt user to enter file name, then
	// call runShortestPath with the file name.
	public Dijkstras() {
		Scanner userFile = new Scanner (System.in);
		System.out.println("Enter the File Name:");
		String fileName = userFile.next();
		runShortestPath(fileName);
	}

	// Make a graph and run Dijkstra's algorithm.
	public void runShortestPath(String fileName){
		System.out.println(fileName + " Graph") ;
		// Create a new GraphMaker object and use it to make a new AdjListGraph.
		GraphMaker graphMaker  = new GraphMaker();
		AdjListGraph graph = graphMaker.makeGraphFromFile(fileName);
		//Print the graph out and prompt the user to enter the starting 
		// and ending vertices.
		graph.print();
		Scanner userInput = new Scanner(System.in);
		System.out.println("Enter the starting vertex: ");
		String vertexStart = userInput.next();
		System.out.println("Enter the ending vertex: ");
		String vertexEnd = userInput.next();



		// loop through the vertex list to find the string that matches the users input
		DijkstraVertex source = null;
		DijkstraVertex end = null;
		ArrayList<Vertex> vertexs= graph.getVertices();
		for(int i = 0; i < vertexs.size(); i++)		{
			Vertex vertexCheck = vertexs.get(i);
			String vertexFrom = vertexCheck.getName();
			if(vertexStart.equals(vertexFrom)) {
				source = (DijkstraVertex) vertexCheck;
			}else if(vertexEnd.equals(vertexFrom)) {
				end = (DijkstraVertex) vertexCheck;
			}
		}
		System.out.println("Source vertex: "+ source.getName());
		// Call the shortestPath method with the graph and the source Vertex.
		shortestPath(graph, source);

		// Get the distance to the destination Vertex and print it out.

		double distance = end.getDistance();
		System.out.println("The shortest path is " + distance);

		// Find and print the path by following back from the destination Vertex to each
		// parent. Note that, in the shortestPath method, you'll have stored 
		// the parent for each Vertex in the HashMap declared at the top of this file).
		DijkstraVertex test1 = null;
		DijkstraVertex test2 = null;
		DijkstraVertex test3 = null;
		DijkstraVertex test4 = null;
		for(int i = 0; i < vertexs.size(); i++) {
			test1 =  parent.get(end);
			test2 =  parent.get(test1);
			test3 = parent.get(test2);
			
		}
		System.out.println("Path: " + test3.getName() + "::" + test2.getName() + "::" + test1.getName()+ "::" + end.getName());
		
		// TODO
	}

	// Given the graph and source vertex, run Dijkstra's algorithm.
	public void shortestPath(AdjListGraph graph, DijkstraVertex source){
		// Initialize the distance to all the vertices in the graph to infinity,
		// except the source vertex, which should be 0.
		ArrayList<Vertex>  vertexs= graph.getVertices();
		DijkstraVertex vertex = null;
		PriorityQueue<DijkstraVertex> pQueue =  new PriorityQueue<DijkstraVertex>(); 
		for(int i = 0; i < graph.numVertices(); i++){
			if(i<graph.numVertices()){
				vertex = (DijkstraVertex) vertexs.get(i);
				vertex.setDistance(Double.POSITIVE_INFINITY);
				source.setDistance(0);
				pQueue.add(vertex);
			}

		}
		// Make a PriorityQueue (imported above in Java.Util.PriorityQueue)
		// of DijkstraVertex objects and add all the vertices.

		pQueue.add(source);



		// Keep looping as long as the priority queue is not empty, doing the following:
		// - get the next closest Vertex from the priority queue
		// - get its adjacent vertices
		// - for each adjacent vertex, check if the distance to get there from the 
		//   current vertex would be shorter than its current distance. If so, remove
		//   it from the priority queue, update its distance, and re-add it. Keep track
		//   of which vertex led to this vertex using the parent HashMap declared
		//   at the top of the file.
		while(!pQueue.isEmpty()){
			DijkstraVertex currentVert = pQueue.poll();
			Collection<Vertex> adjVertex = currentVert.getAdjacentVertices();	
			Iterator<Vertex> itr = adjVertex.iterator();
			for(int i = 0; i < adjVertex.size(); i++) {
				DijkstraVertex adjVertex1 = (DijkstraVertex) itr.next();
				// 
				if(adjVertex1.getDistance() > currentVert.getDistance() + graph.getWeight(currentVert, adjVertex1)) {
					adjVertex1.setDistance(currentVert.getDistance()+graph.getWeight(currentVert, adjVertex1));
					pQueue.remove(adjVertex1);
					parent.put(adjVertex1, currentVert);
					pQueue.add(adjVertex1);

				}

			}

		}

	}
}
