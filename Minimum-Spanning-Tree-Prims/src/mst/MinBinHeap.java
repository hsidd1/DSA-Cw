package mst;

public class MinBinHeap {
    Vertex[] heap;
    int size = 0;
    
    //Constructor: allocates the heap array, sets the key of v[r] to 0 and
    //places v[r] at the root; sets the keys of the remaining vertices
    //to logical infinity and copies them in the heap;
    //initializes heapIndex for each vertex appropriately
    public MinBinHeap(Graph g, int r) {
    }//end constructor
    
    //NOTE: When creating the min-heap in the method minSTPrim, you need to pass
    //a reference to this Graph object; use: new MinBinHeap(this, r);
    //extractMin: returns the vertex with the smallest key and removes it from
    //the heap; note that every time a change is made in the heap,
    //the heapIndex of any vertex involved in the change has to be updated
    
    Vertex extractMin() {
        return null;
    }//end method
    //decreaseKey(int i, int newKey): decreases the key of the vertex stored
    //at index i in the heap; newKey is the new value of the key and it is
    //smaller than the old key; NOTE: after the change, the heap ordering property
    //has to be restored - use percolate up
    void decreaseKey(int i, int newKey) {
    
    }//end method
    public String toString(){
        String s = "\n The heap size is " + size + "\n The items’ labels are: \n";
        for(int i=1; i < size+1; i++) {
            s += heap[i].index + " key: ";
            s += heap[i].key + "\n";
        } //end for
        return s;
    }//end method
}
