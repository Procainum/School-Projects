public class ArrayMaxHeap<T extends Comparable<? super T>> implements MaxHeapInterface<T> {

    private T[] heap;
    private int numEntries;
    private static final int DEFAULT_CAPACITY = 99;

    public ArrayMaxHeap() {
        @SuppressWarnings("unchecked")
        T[] tempHeap = (T[])new Comparable[DEFAULT_CAPACITY  + 1];
        heap = tempHeap;
        numEntries = 0;
    }

    public void add(T newEntry) {
        numEntries++;
        if (numEntries >= heap.length) {
            resizeHeap();
        }
        
    }
//        numEntries++;
//        if(numEntries == 0) {
//            heap[numEntries] = newEntry;
//            return;
//        }
//        heap[numEntries ] = newEntry;
//        maxUpHeap(heap, numEntries);

    public T removeMax() {
        T max = getMax();
        if(numEntries > 0) {
            heap[0] = heap[numEntries-1];
            numEntries--;
            maxHeapify(heap, 0);
        }
        else {
            heap[0] = null;
        }
        return max;
    }

    public T getMax() {
        if(isEmpty()) {
            return null;
        }
        return heap[0];
    }

    public boolean isEmpty() {
        return numEntries == -1;
    }

    public int getSize() {
        return ++numEntries;
    }

    public void clear() {
        numEntries = -1;
       heap = null;
    }

    // Private helper methods
    private void swap(int indexA, int indexB) {
        T temp = heap[indexA];
        heap[indexA] = heap[indexB];
        heap[indexB] = temp;
    }

    private void maxHeapify(T[] h, int index) {
        if(index > numEntries / 2) {
            return;
        }
        int largest = index;
        int leftChildIndex = 2 * index;
        int rightChildIndex = (2 * index) + 1;
        if (leftChildIndex <= numEntries && h[leftChildIndex].compareTo(h[largest]) > 0) {
            largest = leftChildIndex;
        }
        if (rightChildIndex <= numEntries && h[rightChildIndex].compareTo(h[largest]) > 0) {
            largest = rightChildIndex;
        }
        if (largest != index) {
            swap(index, largest);
            maxHeapify(h, largest);
        }
    }

    private void maxUpHeap(T[] h, int index) {
        while (index > 0 && h[index / 2].compareTo(h[index]) < 0)
        {
            swap(index / 2, index);
            index = index / 2;
        }
    }

    private void resizeHeap() {
        @SuppressWarnings("unchecked")
        T[] copy = (T[])new Comparable[heap.length * 2];
        for (int i = 1; i < numEntries; i++) {
            copy[i] = heap[i];
        }
        heap = copy;
    }

    public T[] toArray() {
        return heap;
    }
}
