import java.util.Comparator;

public class MyMinHeap<E extends Comparable<E>> {
    private MyArrayList<E> heap;

    public MyMinHeap() {
        heap = new MyArrayList<>();
    }

    public int size() {
        return heap.size();
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public void add(E element) {
        heap.add(element);
        heapifyUp(heap.size() - 1);
    }

    private void heapifyUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            if (heap.get(index).compareTo(heap.get(parentIndex)) < 0) {
                swap(index, parentIndex);
                index = parentIndex;
            } else {
                break;
            }
        }
    }

    public E peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }
        return heap.get(0);
    }

    public E remove() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }
        E removedElement = heap.get(0);
        E lastElement = heap.removeAt(heap.size() - 1); // Теперь removeAt возвращает элемент
        if (!isEmpty()) {
            heap.set(0, lastElement);
            heapifyDown(0);
        }
        return removedElement;
    }


    private void heapifyDown(int index) {
        int leftChild;
        int rightChild;
        int smallestChild;

        while (index < heap.size() / 2) {
            leftChild = 2 * index + 1;
            rightChild = 2 * index + 2;
            smallestChild = leftChild;

            if (rightChild < heap.size() && heap.get(rightChild).compareTo(heap.get(leftChild)) < 0) {
                smallestChild = rightChild;
            }

            if (heap.get(index).compareTo(heap.get(smallestChild)) > 0) {
                swap(index, smallestChild);
                index = smallestChild;
            } else {
                break;
            }
        }
    }

    private void swap(int i, int j) {
        E temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }
}
