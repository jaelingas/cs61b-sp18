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
        System.arraycopy(items, nextFirst + 1, p, 0, (items.length - nextFirst - 1));
        System.arraycopy(items, 0, p, (items.length - nextFirst - 1), nextFirst + 1);
        items = p;
        nextFirst = minusOne(0);
        nextLast = size;
    }

    public void addFirst(T item) {
        items[nextFirst] = item;
        size++;
        if (size == items.length) {
            resize(2);
        } else {
            nextFirst = minusOne(nextFirst);
        }
    }

    public void addLast(T item) {
        items[nextLast] = item;
        size++;
        if (size == items.length) {
            resize(2);
        } else {
            nextLast  = addOne(nextLast);
        }
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
        L1.addLast(0);
        L1.addLast(1);
        L1.addLast(2);
        L1.addLast(3);
        System.out.println(L1.removeFirst());
        L1.addLast(5);
        System.out.println(L1.isEmpty());
        L1.addLast(7);
        L1.addLast(8);
        L1.addLast(9);
        L1.addLast(10);
        System.out.println(L1.removeFirst());
    }
}
