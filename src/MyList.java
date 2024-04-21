import java.util.Iterator;

public interface MyList<E> {
    int size();
    boolean isEmpty();
    boolean contains(E element);
    void add(E element);
    void remove(E element);
    E get(int index);
    void set(int index, E element);
    Iterator<E> iterator();
}
