public class LinkedListDeque <T> {
    private class ItemNode {
        public ItemNode prev;
        public T item;
        public ItemNode next;

        public ItemNode(ItemNode prev, T item, ItemNode next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }
    }

    private final ItemNode sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new ItemNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    public void addFirst(T item) {
        ItemNode p = new ItemNode(sentinel, item, sentinel.next);
        sentinel.next.prev = p;
        sentinel.next = p;
        size ++;
    }

    public void addLast(T item) {
        ItemNode p = new ItemNode(sentinel.prev, item, sentinel);
        sentinel.prev.next = p;
        sentinel.prev = p;
        size ++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        ItemNode p = sentinel;
        for (int i = 0; i < size; i++) {
            p = p.next;
            System.out.print(p.item);
            if (p.next == sentinel) {
                System.out.println();
            }
            else System.out.print(' ');
        }
    }

    public T removeFirst() {
        T front = sentinel.next.item;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        if (front != null){
            size --;
        }
        return front;
    }

    public T removeLast() {
        T back = sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        if (back != null) {
            size --;
        }
        return back;
    }

    public T get(int index) {
        ItemNode p = sentinel.next;
        if (index >= size || index < 0) {
            return null;
        }
        while (index > 0) {
            p = p.next;
            index --;
        }
        return p.item;
    }

    public T getRecursive(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        return getR(index, sentinel.next);
    }

    private T getR(int i, ItemNode node) {
        if (i == 0) {
            return node.item;
        }
        return getR(i-1, node.next);
    }

    public static void main(String[] args) {
        LinkedListDeque<Integer> L1 = new LinkedListDeque<>();
        L1.addFirst(15);
        L1.addLast(20);
        L1.addFirst(10);
        L1.addFirst(5);
        System.out.println(L1.getRecursive(2));
        System.out.println(L1.removeFirst());
        System.out.println(L1.removeLast());
        L1.printDeque();
        System.out.println(L1.isEmpty());
        System.out.println(L1.size());
    }
}
