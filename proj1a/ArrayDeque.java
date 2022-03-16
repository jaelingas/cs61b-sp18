public class ArrayDeque <T> {
    private T[] items;
    private int nextFirst;
    private int nextLast;
    private int size;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        nextFirst = 0;
        nextLast = 1;
        size = 0;
    }

    private int addOne(int index) {
        return (index + 1) % items.length;
    }

    private int minusOne(int index) {
        return (index - 1 + items.length) % items.length;
    }

    private void resize(double capacity) {
        T[] p = (T[]) new Object[(int) (items.length * capacity)];
        int carrier = addOne(nextFirst);
        for (int i = 0; i < size; i++) {
            p[i] = items[carrier];
            carrier = addOne(carrier);
        }
        items = p;
        nextFirst = minusOne(0);
        nextLast = size;
    }

    public void addFirst(T item) {
        if (size == items.length) {
            resize(2);
        }
        items[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
        size++;
    }

    public void addLast(T item) {
        if (size == items.length) {
            resize(2);
        }
        items[nextLast] = item;
        nextLast  = addOne(nextLast);
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        int idx = addOne(nextFirst);
        for (int i = 0; i < size; i++) {
            System.out.print(items[idx]);
            idx = addOne(idx);
            if (i == size - 1) {
                System.out.println();
            } else {
                System.out.print(' ');
            }
        }
    }

    public T removeFirst() {
        nextFirst = addOne(nextFirst);
        T first = items[nextFirst];
        items[nextFirst] = null;
        if (first != null) {
            size--;
        }
        if (size < items.length * 0.25 && items.length > 16) {
            resize(0.5);
        }
        return first;
    }

    public T removeLast() {
        nextLast = minusOne(nextLast);
        T last = items[nextLast];
        items[nextLast] = null;
        if (last != null) {
            size--;
        }
        if (size < items.length * 0.25 && items.length > 16) {
            resize(0.5);
        }
        return last;
    }

    public T get(int index) {
        int idx = (nextFirst + 1 + index) % items.length;
        return items[idx];
    }

    public static void main(String[] args) {
        ArrayDeque<Integer> L1 = new ArrayDeque();
        for (int i=0; i < 32; i++) {
            L1.addLast(i);
        }
        for (int i=0; i < 25; i++) {
            L1.removeFirst();
        }
    }
}
