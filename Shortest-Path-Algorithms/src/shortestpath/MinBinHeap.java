package shortestpath;


public class MinBinHeap {
    Vertex[] heap;
    int size = 0;

    public MinBinHeap(Graph g, int r) {
        size = g.size; // Set the size of the heap
        heap = new Vertex[size + 1]; // Allocate the heap array

        g.v[r].key = 0;    // the head has a key of 0 and prev is null due to it being the root     
        g.v[r].prev = null; 
        heap[1] = g.v[r]; // set the root of the heap to the vertex at index r 
        heap[1].heapIndex = 1; // set heap index of the root to 1 to assert it is the root   
        int idxInHeap = 2; // index to track the position of the vertices in the heap

        for (int i = 0; i < g.size; i++) {   // iterate through the vertices and add them to the heap
            if (i != r) { // if the vertex is not the root
                g.v[i].key = Graph.infinity; // set the key of the vertex to infinity to represent the default value    
                g.v[i].prev = null; // set the prev of the vertex to null
                heap[idxInHeap] = g.v[i]; // this is the vertex to be added to the heap    
                heap[idxInHeap].heapIndex = idxInHeap; // set the heap index of the vertex
                idxInHeap++; // increment the index to track the position of the vertices in the heap     
            } // end if
        } // end for
    } // end constructor

    public Vertex extractMin() { // extract the minimum vertex from the heap
        Vertex minimumVertex = heap[1]; // store the root of the heap in min

        performNodeExchange(1, size); // swap the root with the last element in the heap
        // decrement the size of the heap to remove the minimum vertex
        size--;  

        percolateDown(1); // apply percolate down to restore the heap ordering property

        return minimumVertex; // return the minimum vertex
    } // end method

    private void performNodeExchange(int idx1, int idx2) { // swap the nodes at index idx1 and idx2 
        Vertex tmpVertex = heap[idx1]; // store the node at index idx1 in temp
        heap[idx1] =  heap[idx2]; // set the node at index idx1 to the node at index idx2
        heap[idx2] = tmpVertex; // set the node at index idx2 to temp
        // Update indices of the nodes
        heap[idx1].heapIndex = idx1; // update the heap index of the node at index idx1
        heap[idx2].heapIndex = idx2; // update the heap index of the node at index idx2
    } // end method

    private void percolateDown(int idx) { // percolate down the heap starting at index idx
        if (2*idx > size) { // if the node is a leaf (left childs index greater than the size of the heap)
            return; // return
        } // end if

        int indexOfLeftChild =2*idx; // get the index of the left child
        int indexOfRightChild = 2*idx+1; // get the index of the right child
        int indexOfMinChild; // index of the smallest child

        if (!(2*idx > size) && (2*idx+1 > size)) { // if the node has only one child. left child index not greater than the size of the heap and right child index greater than the size of the heap
            // first condition checks if the node has two children and the second condition checks if the right child is not within the heap
            // this means the node has only one child which is the left child
            indexOfMinChild = indexOfLeftChild; // set the smallest child index to the left child
        } else { // if the node has two children: this condition checks if the right child is smaller 
            // than the left child and sets the smallest child index to the index of the smallest child if the right child 
            // is smaller than the left child otherwise it sets the smallest child index to the index of the left child
            indexOfMinChild =
                    heap[indexOfLeftChild].key < heap[indexOfRightChild].key ?
                            indexOfLeftChild :
                            indexOfRightChild;
        } // end if

        if (heap[indexOfMinChild].key < heap[idx].key) { // if the key of the smallest child is less than the key of the node
            performNodeExchange(idx, indexOfMinChild); // swap the node with the smallest child
            percolateDown(indexOfMinChild); // apply percolate down to restore the heap ordering property
        } // end if
    } // end method

    private void percolateUp(int idx) {
        if (idx == 1) { // if the node is the root
            return; // return
        } // end if

        if (heap[idx].key < heap[indexOfParent(idx)].key) { // if the key of the node is less than the key of the parent
            performNodeExchange(idx, indexOfParent(idx)); // swap the node with the parent
            percolateUp(indexOfParent(idx)); // apply percolate up to restore the heap ordering property
        } // end if
    }

    public void decreaseKey(int i, int newKey) { // decrease the key of the vertex stored at index i in the heap
        if (heap[i] == null || i > size ) { // if the index is greater than the size of the heap or the node at index i is null
            return; // return
        }
        heap[i].key = newKey; // set the new key of the vertex

        // here check the heap ordering property with the parent of the node
        if (i > 1 && heap[i].key < heap[indexOfParent(i)].key) { // if the index is greater than 1 and the key of the node is less than the key of the parent
            percolateUp(i); // apply percolate up to restore the heap ordering property
        }

        // apply percolate down to restore the heap ordering property
        percolateDown(i); 
    }

    public String toString(){
        String s = "\n The heap size is " + size + "\n The items’ labels are: \n";
        for(int i=1; i < size+1; i++) {
            s += heap[i].index + " key: ";
            s += heap[i].key + "\n";
        }
        return s;
    }

    private static int indexOfParent(int idx) {
        return idx%2 == 0 ? idx/2 : (idx-1)/2;
    }
}

// legacy code
// public class MinBinHeap {
//     Vertex[] heap;
//     int size = 0;

//     // Constructor: allocates the heap array, sets the key of v[r] to 0 and
//     // places v[r] at the root; sets the keys of the remaining vertices
//     // to logical infinity and copies them in the heap;
//     // initializes heapIndex for each vertex appropriately
//     public MinBinHeap(Graph g, int r) {
//         size = g.size; // Set the size of the heap
//         heap = new Vertex[size + 1]; // Allocate the heap array
//         g.v[r].key = 0; // The key of the root will be 0

//         // Set the root of the heap to the vertex at index r
//         for (int i = 1; i <= size; i++) { // Iterate through the vertices
//             heap[i] = g.v[i - 1]; // Set the vertex at index i-1 to the heap
//             heap[i].heapIndex = i; // Set the heapIndex of the vertex
//         }
//         // Percolate down the heap to restore the heap ordering property
//         g.v[r].heapIndex = 1; // asserting the heap index of the root to be 1
//         // here we exchange the root of the heap with the last element in the heap
//         heap[r + 1] = heap[1]; // Set the first element in the heap to the root
//         heap[1] = g.v[r]; // Set the root to the first element in the heap
//         heap[r + 1].heapIndex = r + 1; // Set the heapIndex of the root to r+1
//     }// end constructor

//     // NOTE: When creating the min-heap in the method minSTPrim, you need to pass
//     // a reference to this Graph object; use: new MinBinHeap(this, r);

//     // extractMin: returns the vertex with the smallest key and removes it from
//     // the heap; note that every time a change is made in the heap,
//     // the heapIndex of any vertex involved in the change has to be updated
//     private void percolateDown(int x) { // percolate down the heap starting at index x
//         int index; // Temporary index
//         Vertex temp; // Temporary vertex

        
//         while (x * 2 <= size) { // While the left child is within the heap
//             index = x * 2;  // Set the index to the left child
//             // Check if the right child is smaller than the left child
//             if (x * 2 + 1 <= size && heap[x * 2 + 1].key < heap[x * 2].key) { 
//                 index = x * 2 + 1; // Set the index to the right child
//             } 
//             // verify heap ordering property
//             if (heap[x].key <= heap[index].key) { // If the heap ordering property is satisfied
//                 index = x; // Set the index to x
//             } 

//             // Swap the elements if the heap ordering property is not satisfied
//             temp = heap[index]; // Swap the elements
//             heap[index] = heap[x]; // Swap the elements
//             heap[index].heapIndex = index; // Update the heapIndex of the vertex
//             heap[x] = temp; // Swap the elements
//             temp.heapIndex = x; // Update the heapIndex of the vertex
//             // end of swap
//             if (x == index) // If the heap ordering property is satisfied
//                 break; // Break out of the loop

//             x = index; // Set x to the index
//         }

//     }

//     Vertex extractMin() {
//         Vertex minVertex = heap[1]; //  Store the root of the heap
//         heap[1] = heap[size--]; //  Replace the root with the last element in the heap
//         heap[1].heapIndex = 1; //  Set the heapIndex of the root to 1
//         percolateDown(1); // apply percolate down to restore the heap ordering property
//         return minVertex;
//     }// end method
//      // decreaseKey(int i, int newKey): decreases the key of the vertex stored
//      // at index i in the heap; newKey is the new value of the key and it is
//      // smaller than the old key; NOTE: after the change, the heap ordering property
//      // has to be restored - use percolate up

//     private void percolateUp(int y) { // Percolate up the heap starting at index y
//         Vertex x; // Temporary vertex
//         // While the parent is within the heap and the key of the parent is greater than the key of the child
//         while (y / 2 >= 1 && heap[y].key < heap[y / 2].key) { // While the parent is within the heap
//             x = heap[y / 2]; // Set x to the parent
//             x.heapIndex = y; // Update the heapIndex of the parent
//             heap[y / 2] = heap[y]; // Set the parent to the child
//             heap[y / 2].heapIndex = y / 2; // Update the heapIndex of the parent
//             heap[y] = x; // Set the child to the parent
//             y = y / 2; // Set y to the parent
//         }
//     }

//     void decreaseKey(int i, int newKey) {
//         heap[i].key = newKey; // the new key is smaller than the old key
//         percolateUp(i); // apply percolate up to restore the heap ordering property
//     }

//     public String toString() {
//         String s = "\n The heap size is " + size + "\n The items’ labels are: \n";
//         for (int i = 1; i < size + 1; i++) {
//             s += heap[i].index + " key: ";
//             s += heap[i].key + "\n";
//         } // end for
//         return s;
//     }// end method
// }
