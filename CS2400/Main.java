public class Main {
    public static void main(String[] args) {
        MaxHeapInterface pq = new ArrayMaxHeap();
        pq.add(18);
        pq.add(100);
        pq.add(164);
        pq.add(22);
        pq.add(4);
        pq.add(99);
        pq.add(56);
        pq.add(12);
        pq.add(9);
        pq.add(68);
        pq.add(120);
        pq.add(110);
        pq.add(1);

        System.out.println("Length: " + pq.getSize());
        Comparable[] heap = ((ArrayMaxHeap) pq).toArray();
        for(int i = 0; i < heap.length; i++) {
            //if(heap[i] == null) continue;
            System.out.println(heap[i]);
        }
        System.out.println("\n");
        System.out.println("Removing: " +pq.removeMax());
        System.out.println("Max: " + pq.getMax());
        System.out.println("Length: " + pq.getSize());
        heap = ((ArrayMaxHeap) pq).toArray();
        for(int i = 0; i < heap.length; i++) {
            System.out.println(pq.removeMax());
        }
    }
}