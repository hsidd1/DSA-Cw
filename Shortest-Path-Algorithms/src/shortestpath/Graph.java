package shortestpath;
import java.util.Scanner;

public class Graph {
    public static final int infinity = 10000;
    Vertex[] v;
    Edge[] adj;
    int size = 0;

    public Graph(String inputString) {
        Scanner input = new Scanner(inputString);
        size = input.nextInt();
        v = new Vertex[size];
        adj = new Edge[size];

        for (int i = 0; i < size; i++) {
            v[i] = new Vertex(i);
        }

        int end1, end2, w;
        while (input.hasNext()) {
            end1 = input.nextInt();
            end2 = input.nextInt();
            w = input.nextInt();

            Edge edge1 = new Edge(adj[end1], v[end2], w);
            adj[end1] = edge1;
        }
    }

    public String dijkstraSP(int i, int j) {
        MinBinHeap myHeap = new MinBinHeap(this, i); // Create a new heap
        Edge edgePointer; // Edge pointer
        Vertex u; // Vertex pointer
        boolean isReached = false; // Flag to indicate if the destination vertex is reached

        while (myHeap.size != 0) { // While the heap is not empty
            u = myHeap.extractMin(); // Extract the vertex with the smallest key
            if (u.key != infinity && u.index == j) { // if the destination vertex is reached and its key is not infinity
                isReached = true; // Set the flag to true
            }

            // Traverse the adjacency list of the vertex u and relax the edges if necessary 
            edgePointer = adj[u.index]; // Set the edge pointer to the first edge in the adjacency list of u
            while (edgePointer != null) { // While there are more edges in the adjacency list
                if (u.key + edgePointer.weight < edgePointer.endPoint.key) { // If a shorter path is found
                    // Relax the weight of the edge
                    myHeap.decreaseKey(edgePointer.endPoint.heapIndex, u.key + edgePointer.weight); // Decrease the key of the vertex
                    edgePointer.endPoint.prev = u; // Set the previous vertex of the endpoint of the edge
                }
                // Move to the next edge in the adjacency list
                edgePointer = edgePointer.next;
            }
        }
        // If the destination vertex is not reached, return null
        if (!isReached) return null; // If the destination vertex is not reached, return null
        // Otherwise, return the shortest path string
        return SPStringRepresentation(i, j); // Return the shortest path string
    }

    private String SPStringRepresentation(int i, int j) { // Get the shortest path string
        StringBuilder shortestPathString = new StringBuilder(); // Create a new string builder
        Vertex u; // Vertex pointer

        // Backtrack through the path to get the shortest path
        u = v[j]; // Set the vertex pointer to the destination vertex
        int vertexWeight = v[j].key; // Get the weight of the destination vertex
        while (u.prev != null && u.prev != v[j]) { // While there are more vertices in the path
            shortestPathString.insert(0, "v" + u.index + ", "); // Insert the vertex index to the path string
            u = u.prev; // Move to the previous vertex in the path
        } // End of while loop

        // Append the weight of the path to the path string
        shortestPathString.insert(0, "v" + u.index + ", "); // Insert the vertex index to the path string
        shortestPathString.insert(0, "path: "); // Insert the path label to the path string
        shortestPathString.append("weight: ").append(vertexWeight); // Append the weight of the path to the path string

        // Return the path string
        return shortestPathString.toString(); // Return the path string
    }

    public String bellmanFordSP(int i, int j) { // Bellman-Ford shortest path algorithm
        // Initialize the vertices with default values and set the distance of the source vertex to 0 
        for (int x = 0; x < size; x++) { // Loop through all vertices
            v[x].key = infinity; // Set the key of the vertex to infinity
            v[x].prev = null; // Set the previous vertex of the vertex to null
            v[x].isInQ = true; // Set the vertex to be in the queue
        }  // End of for loop
        v[i].key = 0; // Set the key of the source vertex to 0
        Edge edgePointerTrav; // Edge pointer 
        // Relax all edges repeatedly to find the shortest path to all vertices 
        for (int x = 1; x <= size - 1; x++) { // Loop through all vertices - 1 times
            // Traverse all edges in the graph
            for (int y = 0; y < size; y++) { // Loop through all vertices
                edgePointerTrav = adj[y]; // Set the edge pointer to the first edge in the adjacency list of the vertex
                while (edgePointerTrav != null) { // While there are more edges in the adjacency list
                    //  Relax the edge if a shorter path is found 
                    if (v[y].key + edgePointerTrav.weight < edgePointerTrav.endPoint.key) { // If a shorter path is found
                        edgePointerTrav.endPoint.key = v[y].key + edgePointerTrav.weight; // Update the key of the endpoint vertex
                        edgePointerTrav.endPoint.prev = v[y]; // Set the previous vertex of the endpoint vertex
                    } // End of if statement
                    // Move to the next edge in the adjacency list
                    edgePointerTrav = edgePointerTrav.next; // Move to the next edge in the adjacency list
                } // End of while loop
            } // End of for loop
        } // End of for loop
        //  Check for negative weight cycles 
        for (int y = 0; y < size; y++) { // Loop through all vertices 
            edgePointerTrav = adj[y]; // Set the edge pointer to the first edge in the adjacency list of the vertex
            while (edgePointerTrav != null) { // While there are more edges in the adjacency list
                // If a shorter path is found, return "negative-weight cycle!" 
                if (v[y].key + edgePointerTrav.weight < edgePointerTrav.endPoint.key) { // If a negative weight cycle is found
                    return "negative-weight cycle!"; // Return "negative-weight cycle!"
                } // End of if statement
                // 
                edgePointerTrav = edgePointerTrav.next;
            }
        }
        // Return the shortest path string by backtracking through the path 
        Vertex u = v[j]; // Set the vertex pointer to the destination vertex
        int vertexPrevIdx; // Previous index of the vertex in the path
        String outputStr = "Weight: " + u.key; // Initialize the result string with the weight of the path
        
        while (u != null) { // While there are more vertices in the path
            outputStr = "v" + u.index + ", " + outputStr; // Append the vertex index to the result string
            vertexPrevIdx = u.index; // Set the previous index to the current index
            u = u.prev; // Move to the previous vertex in the path
            // If the vertex is null and the previous index is not the current index, return null
            if (u == null && vertexPrevIdx != i) { // If the vertex is null and the previous index is not the current index
                return null; // Return null
            } // End of if statement
        } // End of while loop
        return "path: " + outputStr; // Return the shortest path string
    }

    public String dagSP(int i, int j) { // Shortest path in a directed acyclic graph
        Vertex[] topSortedVerticesList = topologicalSort(); // Get the topological sort of the graph

        if (!hasNoNegEdge()) return null; // If there are negative edges, return null
        if (topSortedVerticesList == null) return null; // If the graph is not a DAG, return null

        int vertexIndex = 0; // Index of the source vertex in the topologically sorted list
        for(int x = 0; x < size; x++) { // Loop through all vertices
            topSortedVerticesList[x].key = infinity; // Set the key of the vertex to infinity
            topSortedVerticesList[x].prev = null; // Set the previous vertex of the vertex to null

            if(topSortedVerticesList[x].index == i) { // If the index of the vertex is the source vertex
                topSortedVerticesList[x].key = 0; // Set the key of the vertex to 0
                vertexIndex = x; // Set the index to k
            }  // End of if statement
        } // End of for loop

        boolean isReached = false; // Flag to indicate if the destination vertex is reached
        for(int k = vertexIndex; k < size; k++) { // Loop through all vertices
            Edge e = adj[topSortedVerticesList[k].index]; // Set the edge pointer to the first edge in the adjacency list of the vertex
            while(e != null) { // While there are more edges in the adjacency list
                if((topSortedVerticesList[k].key + e.weight) < e.endPoint.key) { // If a shorter path is found
                    if (e.endPoint.index == j) { // If the destination vertex is reached
                        isReached = true; // Set the flag to true
                    } // End of if statement
                    e.endPoint.key = topSortedVerticesList[k].key + e.weight; // Update the key of the endpoint vertex
                    e.endPoint.prev = topSortedVerticesList[k]; 
                } // End of if statement

                e = e.next; // Move to the next edge in the adjacency list
            }
        }

        if (!isReached) return null; // If the destination vertex is not reached, return null
 
        return SPStringRepresentation(i, j); // Return the shortest path string
    }

    public Vertex[] topologicalSort() { // Topological sort of a directed acyclic graph
        Vertex[] topSortedVerticesArr = new Vertex[size]; // Create a new vertex array to store the topological sort of the graph
        Queue myQ = new Queue(size); // Create a new queue

        boolean hasLoop = false; // Flag to indicate if a loop is found
        int vertexIndex = 0; // Index  of the topSortedVerticesArr array 

        for (Vertex u : v) u.key = 0; // Set the key of all vertices to 0

        for(int i = 0; i < v.length; i++) { // Loop through all vertices
            Edge e = adj[i]; // Set the edge pointer to the first edge in the adjacency list of the vertex
            while(e != null) { // While there are more edges in the adjacency list
                e.endPoint.key++; // Increment the key of the endpoint vertex
                e = e.next; // Move to the next edge in the adjacency list
            } // End of while loop
        }

        boolean zeroFound = false; // Flag to indicate if a zero is found
        for(int i = 0; i < v.length; i++) { // Loop through all vertices
            if(v[i].key == 0) { // If the key of the vertex is 0
                zeroFound = true; // Set the flag to true
                myQ.enqueue(v[i]); // Enqueue the vertex
            } // End of if statement
        } // End of for loop 

        if (!zeroFound) return null;   // If a zero is not found, return null

        for(int i = 0; i <v.length; i++) { // Loop through all vertices
            if(myQ.isEmpty()) { // If the queue is empty 
                hasLoop = true; // Set the flag to true 
                break; // Break out of the loop 
            }   // End of if statement
            Vertex u = myQ.dequeue(); // Dequeue the vertex to get the next vertex
            topSortedVerticesArr[vertexIndex] = u; // Set the vertex to the array
            vertexIndex++; // Increment the index

            Edge e = adj[u.index]; // Set the edge pointer to the first edge in the adjacency list of the vertex
            while(e != null) { // While there are more edges in the adjacency list
                e.endPoint.key--; // Decrement the key of the endpoint vertex
                if(e.endPoint.key == 0) myQ.enqueue(e.endPoint); // If the key of the endpoint vertex is 0, enqueue the vertex
                e = e.next; // Move to the next edge in the adjacency list
            } // End of while loop
        } // End of for loop

        if(hasLoop) return null; // If a loop is found, return null 

        return topSortedVerticesArr; // Return the topological sort
    }

    public String shortestPath(int i, int j) { // Shortest path algorithm
        String OutputStr = dagSP(i ,j); // Get the shortest path in a DAG
        if(OutputStr != null) { // If the result is not null
            return "dagSP, " + OutputStr; // Return the result
        }

        if(hasNoNegEdge()) { // If there are no negative edges
            return "dijkstraSP, " + dijkstraSP(i, j); // Return the result of Dijkstra's algorithm
        } 

        return "bellmanFordSP, " + bellmanFordSP(i, j); // Return the result of Bellman-Ford algorithm
    }

    private boolean hasNoNegEdge() { // Check if there are no negative edges
        for (int i = 0; i < size; i++) { // Loop through all vertices
            Edge e = adj[i]; // Set the edge pointer to the first edge in the adjacency list of the vertex
            while (e != null) { // While there are more edges in the adjacency list
                if (e.weight < 0) { // If the weight of the edge is negative
                    return false; //  Return false as there is a negative edge
                } // End of if statement
                e = e.next; // Move to the next edge in the adjacency list
            }
        }
        return true; //  Return true if there are no negative edges
    }

    public int[][] adjMatrix() { // Get the adjacency matrix
        int[][] matrix = new int[size][size]; // Create a new matrix

        // Initialize the matrix with zeros
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) { // Loop through all vertices
                matrix[i][j] = infinity;
            }
        }

        // Fill in the matrix based on the adjacency list
        for (int i = 0; i < size; i++) {
            Edge p = adj[i];
            while (p != null) {
                matrix[i][p.endPoint.index] = p.weight; // Set the weight of the edge
                p = p.next;
            }
        }

        return matrix; // Return the adjacency matrix
    }


    public String adjListString() {
        Edge p; //edge pointer
        String s = " ";
        for (int i = 0; i < size; i++) {
            p = adj[i]; //p points to first edge in the adjacency list of v[i]
            //scan adjacency list of v[i]
            while (p != null) {
                s += " \n edge: (v" + i + ", v" + p.endPoint.index + "), weight: "
                        + p.weight;
                p = p.next; //move to next edge in the current list
            }
        }

        return s;
    }
}
