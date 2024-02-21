package mst;

import java.util.Scanner;

public class Graph {
    public static final int infinity = 10000;
    Vertex[] v;
    Edge[] adj;
    
    int size = 0;
    
    //Constructor: constructs the undirected graph described by the input string;
    //the string contains only non-negative
    //integers separated by white spaces; the first integer is
    //the number of vertices (n); each of the following triples of integers
    //specifies an edge, namely end1, end2 and weight, where end1 and end2
    //are the indexes of the endpoints in the array of vertices
    //and weight is the weight of the edge; you may assume that the input string
    //respects the required format, that 0<=end1<=n-1, 0<=end2<=n-1,
    //and that the input represents a connected graph
    public Graph(String inputString) {
        Scanner input = new Scanner(inputString);
        size = input.nextInt();
        v = new Vertex[size]; //alllocate the array of vertices
        adj = new Edge[size]; //alllocate the array of headers to adjacency lists
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
            //create an edge with endPoint=end1 and
            //insert it in the adjacency list of end2
            //each insertion is made at the beginning of the list
        }//end while
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
        return new String();
    }//end method
    
    //adjMatrix(): returns a two-dimensional array that represents
    //the adjacency matrix of the graph
    public int[][] adjMatrix(){
        return new int[][] {};
    }//end method
    
}
