// Ver 1.0:  Wec, Feb 3.  Initial description.

public interface PQ<T> {
public void insert(T x);
public T deleteMin();
public T min();

public void add(T x);
public T remove();
public T peek();
public boolean isEmpty();
public int size();
}
