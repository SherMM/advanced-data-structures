public class Entry<T> {
T element;
Entry<T> left, right, parent;

Entry(T x, Entry<T> l, Entry<T> r, Entry<T> p) {
        element = x;
        left = l;
        right = r;
        parent = p;
}
}
