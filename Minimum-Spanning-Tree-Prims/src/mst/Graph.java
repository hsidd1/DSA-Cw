package mst;

import java.util.Scanner;

public class Graph {
    public static final int infinity = 10000; // logical infinity
    Vertex[] v; // array of vertices
    Edge[] adj; // array storing headers of adjacency lists (adj[i] is
                // a reference to the first Edge object in the adjacency
                // list of vertex v[i])
    
    int size = 0; // number of vertices in the graph // NOTE: I make this public for visibility in the test class but removed it here to align with the document
    
    //Constructor: constructs the undirected graph described by the input string;
    //the string contains only non-negative
    //integers separated by white spaces; the first integer is
    //the number of vertices (n); each of the following triples of integers
    //specifies an edge, namely end1, end2 and weight, where end1 and end2
    //are the indices of the endpoints in the array of vertices
    //and weight is the weight of the edge; you may assume that the input string
    //respects the required format, that 0<=end1<=n-1, 0<=end2<=n-1,
    //and that the input represents a connected graph
    public Graph(String inputString) {
        Scanner input = new Scanner(inputString);
        size = input.nextInt();
        v = new Vertex[size]; //allocate the array of vertices
        adj = new Edge[size]; //allocate the array of headers to adjacency lists
        for(int i=0; i<size; i++) {
            v[i] = new Vertex(i); // populate the array of vertices with Vertex objects with index i
        }
        //read the info from the string
        int end1;
        int end2; int w;
        while(input.hasNext()){
            //read next edge
            end1 = input.nextInt();
            end2 = input.nextInt();
            w = input.nextInt();
            //create an edge with endPoint=end2 and
            //insert it in the adjacency list of end1
            adj[end1] = new Edge(adj[end1], v[end2], w);
            //create an edge with endPoint=end1 and
            //insert it in the adjacency list of end2
            //each insertion is made at the beginning of the list
            adj[end2] = new Edge(adj[end2], v[end1], w);
        }//end while
        input.close();
    }//end constructor
    
    public String adjListString() {
        Edge p; //edge pointer
        String s = " ";
        for(int i=0; i<size; i++) {
            p = adj[i]; //p points to first edge in the adjacency list of v[i]
            //scan adjacency list of v[i]
            while(p != null) {
                s += " \n edge: (v" + i +", v" + p.endPoint.index + "), weight: "
                + p.weight;
                p = p.next; //move to next edge in the current list
            }//end while
        }// end for
        return s;
    } // end method
    
    //minSTPrim(int r): finds a minimum spanning tree using Prim’s algorithm
    //implemented with a min-heap, starting at vertex v[r];
    //returns a string that lists all edges in the MST,
    //in the order they were found; see the output of the test class
    //for clarification on the format of the string;
    //you may assume that r is a valid index in the vertex array
    //and that the graph is connected
    public String minSTPrim (int r) {
        MinBinHeap priorityQueue = new MinBinHeap(this, r); // use MinBinHeap to implement priority queue Q
        String mst_edge_list_str = "MST: "; // string to store the list of edges in the MST

			// iterate through the vertices in the graph
			for(int i = 1; i <= size; i++) {
				// extract the min vertex from the heap
				Vertex u = priorityQueue.extractMin();
                // mark the vertex as not in the heap anymore
				 u.isInQ = false;
				
                 /* Traverse through the adjacency list of the minimum vertex */
				 Edge edge = adj[u.index];
				 while (edge != null) {
                    // Check if the edge leads to a vertex still in the priority queue, and if the weight is smaller than the key of the vertex in the priority queue
                    if (edge.endPoint.isInQ && edge.weight < edge.endPoint.key) {
                        // Update the key and previous vertex if the weight is smaller
                        edge.endPoint.prev = u;
                        edge.endPoint.key = edge.weight;
                        // decrease the key of the vertex in the priority queue
                        priorityQueue.decreaseKey(edge.endPoint.heapIndex, edge.endPoint.key);
                    }
                    edge = edge.next; // move to the next edge in the adjacency list
                }
        
                // if not the first vertex, append the edge to the MST string (first vertex has no previous vertex in the MST tree, so no edge to append)
                if (i != 1 && u.prev != null) {
                    mst_edge_list_str += "(v" + u.index + ", v" + u.prev.index + "), ";
                }
            }
			
		return mst_edge_list_str;	

    }//end method
    
    //adjMatrix(): returns a two-dimensional array that represents
    //the adjacency matrix of the graph
    public int[][] adjMatrix() {
        int[][] adjacencyMatrix = new int[size][size];
        
        // initialize the adjacency matrix with infinity for all elements
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                adjacencyMatrix[row][col] = infinity;
            }
        }
        
        int adjacentVertexIndex; // Index of the adjacent vertex
        Edge currentEdge; // Pointer to an edge
        for (int vertexIndex = 0; vertexIndex < size; vertexIndex++) {
            currentEdge = adj[vertexIndex]; // Current edge points to the first edge in the adjacency list of v[vertexIndex]
            while (currentEdge != null) {
                adjacentVertexIndex = currentEdge.endPoint.index; // Get the index of the adjacent vertex
                // Set weight in both directions for the edge between vertexIndex and its adjacent vertex
                adjacencyMatrix[vertexIndex][adjacentVertexIndex] = currentEdge.weight; // w(v_i, v_j) = weight
                adjacencyMatrix[adjacentVertexIndex][vertexIndex] = currentEdge.weight; // w(v_j, v_i) = weight
                currentEdge = currentEdge.next; // Move to the next edge in the adjacency list
            }// end while
        }// end for

        
        return adjacencyMatrix;
    }// end method
    
    
}
