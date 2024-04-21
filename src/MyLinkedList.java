import java.util.Iterator;

public class MyLinkedList<E> implements MyList<E> {
    private static class MyNode<E> {
        E element;
        MyNode<E> next;
        MyNode<E> prev;

        public MyNode(E element, MyNode<E> prev, MyNode<E> next) {
            this.element = element;
            this.prev = prev;
            this.next = next;
        }
    }

    private MyNode<E> head;
    private MyNode<E> tail;
    private int size;

    public MyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(E element) {
        MyNode<E> current = head;
        while (current != null) {
            if (current.element.equals(element)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public void add(E element) {
        addLast(element);
    }

    public void addFirst(E element) {
        if (isEmpty()) {
            head = tail = new MyNode<>(element, null, null);
        } else {
            MyNode<E> newNode = new MyNode<>(element, null, head);
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }

    public void addLast(E element) {
        if (isEmpty()) {
            head = tail = new MyNode<>(element, null, null);
        } else {
            MyNode<E> newNode = new MyNode<>(element, tail, null);
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    @Override
    public void remove(E element) {
        MyNode<E> current = head;
        while (current != null) {
            if (current.element.equals(element)) {
                removeNode(current);
                break;
            }
            current = current.next;
        }
    }

    private void removeNode(MyNode<E> node) {
        if (node.prev != null) {
            node.prev.next = node.next;
        } else {
            head = node.next;
        }

        if (node.next != null) {
            node.next.prev = node.prev;
        } else {
            tail = node.prev;
        }

        size--;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        MyNode<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.element;
    }

    @Override
    public void set(int index, E element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        MyNode<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        current.element = element;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private MyNode<E> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public E next() {
                E element = current.element;
                current = current.next;
                return element;
            }
        };
    }
}
