package shortestpath;

// Class representing a queue data structure
public class Queue {
    private int head; // Pointer to the front of the queue
    private int tail; // Pointer to the end of the queue
    private int size; // Number of elements in the queue
    private Vertex[] elements; // Array to hold the elements of the queue
    private int capacity; // Maximum capacity of the queue

    // Constructor to initialize the queue with a given capacity
    public Queue(int input_capacity) {
        capacity = input_capacity;
        elements = new Vertex[capacity];
        head = 0;
        tail = 0;
        size = 0;
    }

    // Check if the queue is full
    public boolean isFull() {
        return size == capacity;
    }

    // Check if the queue is empty
    public boolean isEmpty() {
        return size == 0;
    }

    // Get the number of elements in the queue
    public int numElements() {
        return size; // Return the size of the queue
    }

    // Enqueue operation: add an element to the end of the queue
    public void enqueue(Vertex v) {
        if (isFull()) {
            System.out.println("No more space in the queue!");
            return;
        }
        // Add the element to the end of the queue
        elements[tail] = v;
        // Update the tail pointer and size
        tail = (tail + 1) % capacity; // Wrap around if the end of the array is reached
        size++;
    }

    // Dequeue operation: remove and return the element from the front of the queue
    public Vertex dequeue() {
        if (isEmpty()) {
            System.out.println("No elements in the queue!");
            return null;
        }
        // Remove the element from the front of the queue
        Vertex removedElem = elements[head]; // Get the element at the front
        head = (head + 1) % capacity; // Move the head pointer to the next element
        size--; // Decrease the size of the queue
        return removedElem; // Return the removed element
    }

    // Peek operation: return the element at the front of the queue without removing it
    public Vertex peek() {
        if (isEmpty()) {
            System.out.println("No elements in the queue!");
            return null;
        }
        return elements[head]; // Return the element at the front of the queue
    }

    
}
