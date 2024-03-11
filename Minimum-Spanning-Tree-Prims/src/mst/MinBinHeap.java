package mst;

public class MinBinHeap {
    Vertex[] heap;
    int size = 0;
    
    public MinBinHeap(Graph g, int r) {
        //Constructor: allocates the heap array, sets the key of v[r] to 0 and
        //places v[r] at the root; sets the keys of the remaining vertices
        //to logical infinity and copies them in the heap;
        //initializes heapIndex for each vertex appropriately

        /* initialize the heap field */
		heap = new Vertex[g.size + 1]; // allocate the array of vertices (heap) with size of the graph + 1 for the root
		
        /* set up the root vertex in the graph and place in the heap */
		g.v[r].key = 0; // set the key of the root to 0
		g.v[r].heapIndex = 1; // the root is at the root index 1 of the heap
        g.v[r].isInQ = true; // the root is in the heap (true by default)
		heap[1] = g.v[r]; // place v[r] at the root of the heap

		size = 1; // set the size of the heap to 1 initially
		
        /* add the remaining vertices in the heap as logical infinities with appropriate heapIndex */
		for (int i = 0; i < g.size; i++) { // for each vertex in the graph
			if( r != i) { // if the vertex is not the root vertex
				g.v[i].heapIndex = size + 1; // the vertex is at the next index in the heap
				g.v[i].key = Graph.infinity; // set the key of the vertex to infinity
				heap[size+1] = g.v[i]; // place the vertex in the heap. use size+1 because the root is at index 1
				size++; // increment the size of the heap since we added a vertex
			}
		} // end while
	} // end constructor

    Vertex extractMin() {
        //extractMin: returns the vertex with the smallest key and removes it from
        //the heap; note that every time a change is made in the heap,
        //the heapIndex of any vertex involved in the change has to be updated

        /* check for empty heap case */
        if (size == 0) return null; // if the heap is empty return null

        /* remove the minimum vertex from the heap */
        Vertex min = heap[1]; // get the root of the heap (the minimum)
        heap[1] = heap[size]; // move the last vertex to the root
        heap[1].heapIndex = 1; // update the heap index of the vertex now at the root
        size--; // decrement the size of the heap since we removed a vertex
        min.isInQ = false; // the minimum vertex is no longer in the heap
        percolateDown(1); // percolate down the root to restore the heap property

        /* return the minimum vertex */
        return min; // return the minimum vertex
    }//end method

    private void percolateDown(int i) {
        //percolateDown: percolate down starting at index i

        /* initialize the child and temp vertex */
        int child = 2*i;  // left child of i
        Vertex tempV = heap[i]; // temp vertex to store the hole
        int tempVKey = heap[i].key; // key of the vertex at index i

        /* percolate down the root */
        while (child <= size) {
            // check if the current node has a left child and if the key of the right child is smaller than the left child
            if (child < size && heap[child+1].key < heap[child].key) {
                child++; // child with the smallest key (vertex key) - the right child chosen if it exists and has a smaller key
            }
            // check if the key of the smallest child is less than the key of the current node
            if (heap[child].key < tempVKey) {
                heap[i] = heap[child]; // move vertex key of child up at parent
                heap[i].heapIndex = i; // update the heap index of the vertex
                i = child; // move hole down at child
                child = 2*i; //  update child to the left child
            } else break;
        }

        heap[i] = tempV; // place the vertex at the hole
        heap[i].heapIndex = i; // update the heap index of the vertex
    }//end method


    void decreaseKey(int i, int newKey) {
        //decreaseKey(int i, int newKey): decreases the key of the vertex stored
        //at index i in the heap; newKey is the new value of the key and it is
        //smaller than the old key; NOTE: after the change, the heap ordering property
        //has to be restored - use percolate up

        heap[0] = heap[i]; // store the vertex at index i in the heap at index 0 dummy index

        /* use percolate up for reordering the heap */
        while(newKey < heap[i/2].key) { // while the new key is less than the parent key
            heap[i] = heap[i/2]; // move the parent down to the child / hole
            heap[i].heapIndex = i; // update the heap index of the vertex
            i = i/2; // move the hole up - child becomes parent
        } // end while

        /* place the vertex at the hole */
        heap[i] = heap[0]; // place the vertex at the hole
        heap[i].key = newKey; // update the key of the vertex
        heap[i].heapIndex = i; // update the heap index of the vertex
    }//end method


    public String toString(){
        String s = "\n The heap size is " + size + "\n The items' labels are: \n";
        for(int i=1; i < size+1; i++) {
            s += heap[i].index + " key: ";
            s += heap[i].key + "\n";
        } //end for
        return s;
    }//end method

      //Constructor: allocates the heap array, sets the key of v[r] to 0 and
    //places v[r] at the root; sets the keys of the remaining vertices
    //to logical infinity and copies them in the heap;
    //initializes heapIndex for each vertex appropriately
    // public MinBinHeap(Graph g, int r) {

    //     /* initialize the heap fields */
    //     size = g.size; // size of the heap is the number of vertices
    //     heap = new Vertex[size+1]; // allocate the array of vertices (heap)

    //     /* set up the vertex in the graph */
    //     g.v[r].key = 0; // set the key of the root to 0
    //     g.v[r].isInQ = true; // the root is in the heap
    //     g.v[r].prev = null; // the root has no predecessor
    //     g.v[r].heapIndex = 1; // the root is at the root of the heap (crazy)

    //     /* place the root in the heap */
    //     heap[1] = g.v[r]; // place the root at the root of the heap

    //     /* add the remaining vertices in the heap as logical infinities with appropriate heapIndex */
    //     for(int i = 0; i < size; i++) { // for each vertex in the graph
    //         if(i != r) { // if the vertex is not the root vertex add to heap, otherwise skip
    //             g.v[i].key = Graph.infinity; // set the key of the vertex to infinity
    //             g.v[i].isInQ = true; // the vertex is in the heap
    //             g.v[i].prev = null; // the vertex has no predecessor
    //             // use +2 because the root is at index 1 and the next vertex is at index 2
    //             g.v[i].heapIndex = i+2; // the vertex is at the next index in the heap
    //             heap[i+2] = g.v[i]; // place the vertex in the heap
    //         } // end if
    //     } // end for

    // }//end constructor
    
    // //NOTE: When creating the min-heap in the method minSTPrim, you need to pass
    // //a reference to this Graph object; use: new MinBinHeap(this, r);

    // //extractMin: returns the vertex with the smallest key and removes it from
    // //the heap; note that every time a change is made in the heap,
    // //the heapIndex of any vertex involved in the change has to be updated
    
    // Vertex extractMin() {

    //    if (size == 0) return null;
    //    Vertex min = heap[1]; // get the root of the heap (the minimum)
    //    heap[1] = heap[size]; // move the last vertex to the root
    //    size--; // decrement the size of the heap

    //    percolateDown(1); // percolate down the root to restore the heap property
    //    return min; // return the minimum vertex
    // }//end method

    // private void percolateDown(int i) { // percolate down starting at index i
	// 	int child = 2*i;  // left child of i
	// 	Vertex tempV = heap[i]; // temp vertex to store the hole
	// 	int tempVKey = heap[i].key; // key of the vertex at index i
		
	// 	while (child <= size) {
	// 		if (child < size && heap[child+1].key < heap[child].key) {
	// 			child++; // child with the smallest key (vertex key)
	// 		}
	// 		if (heap[child].key < tempVKey) {
	// 			heap[i] = heap[child]; // move vertex key of child up at parent
	// 			heap[i].heapIndex = i; // update the heap index of the vertex
	// 			i = child; // move hole down at child
	// 			child = 2*i; //  update child
	// 		} else break;
	// 	}
		
	// 	heap[i] = tempV; // place the vertex at the hole
	// 	heap[i].heapIndex = i; // update the heap index of the vertex
	// }


    // //decreaseKey(int i, int newKey): decreases the key of the vertex stored
    // //at index i in the heap; newKey is the new value of the key and it is
    // //smaller than the old key; NOTE: after the change, the heap ordering property
    // //has to be restored - use percolate up
    // void decreaseKey(int i, int newKey) {
    //     heap[0] = heap[i]; // store the vertex at index i in the heap at index 0 dummy index

    //     /* use percolate up for reordering the heap */
    //     while(newKey < heap[i/2].key) { // while the new key is less than the parent key
    //         heap[i] = heap[i/2]; // move the parent down to the child / hole
    //         heap[i].heapIndex = i; // update the heap index of the vertex
    //         i = i/2; // move the hole up - child becomes parent
    //     } // end while

    //     heap[i] = heap[0]; // place the vertex at the hole
    //     heap[i].key = newKey; // update the key of the vertex
    //     heap[i].heapIndex = i; // update the heap index of the vertex
    // }//end method
}
