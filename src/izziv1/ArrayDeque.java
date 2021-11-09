package izziv1;

class ArrayDeque<T> implements Deque<T>, Stack<T>, Sequence<T> {
    private static final int DEFAULT_CAPACITY = 64;
    private T[] a;
    private int front, back, size;

    public ArrayDeque() {
        a = (T[]) (new Object[DEFAULT_CAPACITY]);
        front = 0;
        back = 0;
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public boolean isFull() {
        return (size == DEFAULT_CAPACITY);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T top() throws CollectionException {
        if(isEmpty()) {
            throw new CollectionException(ERR_MSG_EMPTY);
        }
        return a[size - 1];
    }

    @Override
    public void push(T x) throws CollectionException {
        if(isFull()) {
            throw new CollectionException(ERR_MSG_FULL);
        }
        a[size] = x;
        size++;
    }

    @Override
    public T pop() throws CollectionException {
        if(isEmpty()) {
            throw new CollectionException(ERR_MSG_EMPTY);
        }
        size --;
        T vmesni = a[size];
        a[size] = null;
        return vmesni;
    }

    @Override
    public T front() throws CollectionException {
        if(isEmpty()) {
            throw new CollectionException(ERR_MSG_EMPTY);
        }
        return top();
    }

    @Override
    public T back() throws CollectionException {
        if(isEmpty()) {
            throw new CollectionException(ERR_MSG_EMPTY);
        }
        return a[size-1];
    }

    @Override
    public void enqueue(T x) throws CollectionException {
        if(isFull()) {
            throw new CollectionException(ERR_MSG_FULL);
        }
        a[back] = x;
        size++;
        back = (back + 1) % DEFAULT_CAPACITY;
    }


    @Override
    public void enqueueFront(T x) throws CollectionException {
        if(isFull()) {
            throw new CollectionException(ERR_MSG_FULL);
        }
        for (int i = DEFAULT_CAPACITY - 2; i >= 0; i--) {
            a[i + 1] = a[i];
        }
        front++;
        front = (front - 1) % DEFAULT_CAPACITY;
        a[0] = x;
        size++;
        back = (back + 1) % DEFAULT_CAPACITY;
    }

    @Override
    public T dequeue() throws CollectionException {
        if(isEmpty()) {
            throw new CollectionException(ERR_MSG_EMPTY);
        }
        T x = a[front];
        size--;
        a[front] = null;
        front = (front + 1) % DEFAULT_CAPACITY;
        return x;
    }

    @Override
    public T dequeueBack() throws CollectionException {
        if(isEmpty()) {
            throw new CollectionException(ERR_MSG_EMPTY);
        }
        back = (back - 1) % DEFAULT_CAPACITY;
        T x = a[back];
        a[back] = null;
        size--;
        return x;
    }

    @Override
    public T get(int i) throws CollectionException {
        if(isEmpty()) {
            throw new CollectionException(ERR_MSG_EMPTY);
        }
        T vmesni = a[i];
        return vmesni;
    }

    @Override
    public void add(T x) throws CollectionException {
        if(isFull()) {
            throw new CollectionException(ERR_MSG_FULL);
        }
        a[size] = x;
        size++;
    }

    private  int next(int i) {
        return (i + 1) % DEFAULT_CAPACITY;
    }
    private int prev (int i) {
        return (DEFAULT_CAPACITY + i - 1 ) % DEFAULT_CAPACITY;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        if(size > 0) {
            sb.append(a[front].toString());
        }
        for (int i = 0; i < size() -1; i++) {
            sb.append(", " + a[next(front+i)].toString());
        }
        sb.append("]");

        return sb.toString();
    }


    // Tukaj napiši svojo kodo.
}