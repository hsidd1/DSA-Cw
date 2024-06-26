package heapyt;

public class MaxBinHeap {
    private int[] heap; // array to store the keys of the heap
    private int size; // number of elements in the heap
    
    public MaxBinHeap(int n) {
        /* constructs an empty MaxBinHeap. The parameter indicates the size of 
        the array that is allocated. If n < 10, it is reset to 10. */
        if (n < 10)
            n = 10;
        heap = new int[n];
        heap[0] = 0; // placeholder
        size = 0;
    }

    public MaxBinHeap (int[] a) {
        /*  constructs a MaxBinHeap that stores the integers from array a. 
        The constructor does not modify the input array. The running
        time is Θ(n) in the worst case. */
        size = a.length;
        heap = new int[size + 1];
        heap[0] = 0; // placeholder

        for(int i = 0; i < size; i++){ // copy the elements from a to heap
            heap[i+1] = a[i];
        }
        // rearranges to satisfy the heap property
        buildHeap();
    }

    private void buildHeap() {
        // percolate down all the non-leaf nodes in the heap
        for (int i = size / 2; i > 0; i--){
            percolateDown(i);
        }
    }

    public int getSize() {
        return size;
    }

    private void percolateDown(int i) {
        /*Percolate down the element at index i in the heap, ensuring the max heap property is maintained.
        Starting with the element at index i, we compare it with its children, swapping with the larger child if necessary,
        and continue this process until the element is in its correct position or it becomes a leaf node.
        This method ensures that the subtree rooted at index i satisfies the max heap property.*/
        int key = heap[i];
        int child = 2*i;
        while (child <= size) {
            if (child < size && heap[child+1] > heap[child]) { // check if right child exists and is larger than left child
                ++child;
            }
            if (heap[child] > key) { // check if the child is larger than the key, if so, swap and continue percolating down
                heap[i] = heap[child];
                i = child;
                child = 2*i;
            } else {
                break; // the key is in its correct position (greater than or equal to its children), so we stop percolating down
            }
        }
        heap[i] = key; // place the key in its correct position
    }

    public void insert(int x) {
        /* inserts the key x in the heap. If the array is not
        big enough to hold the new key, a larger array (of double size) is allocated */
        if (size == heap.length - 1) {
            doubleArray();
        }
        size++;
        int i = size; // increment size and store the index in i
        while (x > heap[i/2] && i > 1) { // compare the key with its parent, if it is larger, swap and continue
            heap[i] = heap[i/2];
            i /= 2;
        }
        heap[i] = x; // place the key in its correct position
    }

    private void doubleArray() {
        /* doubles the size of the array heap */
        int[] b = new int[heap.length*2];
        for (int i = 0; i < heap.length; i++) {
            b[i] = heap[i];
        }
        heap = b;
    }

    public int readMax() throws RuntimeException {
        /* returns the largest key without removing it from the heap. If the heap is empty, it throws an exception with
        a message indicating that. */
        if (size ==0) {
            throw new RuntimeException("The heap is empty!");
        }
        return heap[1]; // the largest key is at the root of the heap
    }

    public int deleteMax() throws RuntimeException {
        /* returns the largest key
        and removes it from the heap. If the heap is empty, it throws an exception with a
        message indicating that.*/
        if(size==0) {
            throw new RuntimeException("The heap is empty!");
        }
        int x = heap[1]; // store the largest key
        heap[1] = heap[size]; // replace the root with the last element in the heap
        heap[size] = 0; // remove the last element
        size--;

        percolateDown(1); // percolate down the new root to satisfy the max heap property
        return x; // return the largest key
    }

    public String toString() {
        /* returns a String that lists the items stored in the
        heap in level order, separated by a comma and a space. */
        String result = "";
        for (int i = 1; i <= size; i++) {
            result += heap[i] + ", ";
        } 
        // remove the last comma and space
        result = result.substring(0, result.length()-2);
        return result;
    }

    public static void sortArray(int[] a) {
        /* sorts the array a in place using HeapSort. */
        MaxBinHeap h = new MaxBinHeap(a);
        for(int i = 0; i < a.length; i++){
            a[i] = h.deleteMax(); // delete the max element and store it at the end of the array
        }
    }

    // Version with 0 based indexing (scuffed and abandoned)

    // public MaxBinHeap(int n) {
    //     /* constructs an empty MaxBinHeap. The parameter indicates the size of 
    //     the array that is allocated. If n < 10, it should be reset to 10. */
    //     if (n < 10) {
    //         n = 10;
    //     }
    //     heap = new int[n];
    //     size = 0;
    // }

    // public MaxBinHeap(int[] a) {
    //     size = a.length;
    //     heap = new int[size];
    //     for (int i = 0; i < size; i++) {
    //         heap[i] = a[i];
    //     }
    //     buildHeap();
    // }

    // private void buildHeap() {
    //     for (int i = (size / 2) - 1; i >= 0; i--) {
    //         percolateDown(i);
    //     }
    // }

    // public int getSize() {
    //     return size;
    // }

    // private void percolateDown(int i) {
    //     int key = heap[i];
    //     int child;
    //     while ((child = 2 * i + 1) < size) {
    //         if (child + 1 < size && heap[child + 1] > heap[child]) {
    //             child++;
    //         }
    //         if (heap[child] > key) {
    //             heap[i] = heap[child];
    //             i = child;
    //         } else {
    //             break;
    //         }
    //     }
    //     heap[i] = key;
    // }

    // // public MaxBinHeap(int[] a) {
    // //     /* constructs a MaxBinHeap that stores the integers from array a. 
    // //     The constructor should not modify the input array. The running
    // //     time should be Θ(n) in the worst case. Hint: you can use the appropriate 
    // //     buidHeap operation implemented as a private method. */
    // //     heap = a;
    // //     size = a.length - 1;
    // //     buildHeap();
    // // }

    // // private void buildHeap() {
    // //     for (int i = (heap.length-1)/2; i>0; i--) {
    // //         percolateDown(i);
    // //     }
    // // }

    // // public int getSize() {
    // //     return size;
    // // }

    // // private void percolateDown(int i) {
    // //     int child = 2*i;
    // //     int key = heap[i];
    // //     while(child <= size) {
    // //         if(child < size && heap[child+1] < heap[child]) {
    // //             child++;
    // //         }
    // //         if(heap[child] > key) {
    // //             heap[i] = heap[child];
    // //             i = child;
    // //             child = 2*i;
    // //         } else { break; }
    // //     }
    // //     heap[i] = key;
    // // }
    
    
    // public void insert(int x) {
    //     if (size == heap.length) {
    //         doubleArray();
    //     }
    //     int i = ++size;
        
    //     heap[0] = x;
        
    //     while(x>heap[i/2] && i>1) {
    //         heap[i] = heap[i/2];
    //         i /= 2;
    //     }
    //     heap[i] = x; 
    // }
    
    // private void doubleArray() {
    //     int[] b = new int[2*heap.length];
    //     for (int i = 0; i < size; i++) {
    //         b[i] = heap[i];
    //     }
    //     heap = b;
    // }
    
    // public int readMax() throws RuntimeException {
    //     if (size == 0) {
    //         throw new RuntimeException("Error: The heap is empty!");
    //     }
    //     return heap[0];
    // }
    
    // public int deleteMax() throws RuntimeException {
    //     if (size == 0) {
    //         throw new RuntimeException("Error: The heap is empty!");
    //     }
    //     int x = heap[0];
    //     heap[0] = heap[size-1];
    //     size--;
    //     percolateDown(0);
    //     return x;
    // }

    // public String toString() { 
    //     /* returns a String that lists the items stored in the
    //     heap in level order, separated by a comma and a space. */
    //     String result = "";
    //     for (int i = 0; i<size; i++) {
    //         result += heap[i] + ", ";
    //     }
    //     // remove the last comma and space
    //     result = result.substring(0, result.length()-2);
    //     return result;
    // }
    
    // public static void sortArray(int[] a) {
    //     // int x = a[0];
    //     // MaxBinHeap heap = new MaxBinHeap(a);

    //     // while(heap.getSize() > 0) {
    //     //     a[heap.getSize()] = heap.deleteMax();
    //     // }

    //     // int i = 1; 
    //     // int n = a.length;

    //     // while(i < n && x < a[i]) {
    //     //     a[i-1] = a[i];
    //     //     i++;
    //     // }
        
    //     // a[i-1] = x;

    //     MaxBinHeap heap = new MaxBinHeap(a);
    //     for (int i = 0; i < a.length; i++) {
    //         a[i] = heap.deleteMax();
    //     }
    // }
}
