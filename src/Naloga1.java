import java.util.Scanner;

interface Collection {
    String ERR_MSG_EMPTY = "izziv1.Collection is empty.";
    String ERR_MSG_FULL = "izziv1.Collection is full.";

    boolean isEmpty();
    boolean isFull();
    int size();
    String toString();
}

class CollectionException extends Exception {
    public CollectionException(String msg) {
        super(msg);
    }
}

interface Sequence<T> extends Collection {
    static final String ERR_MSG_INDEX = "Wrong index in sequence.";

    T get(int i) throws CollectionException;

    void add(T x) throws CollectionException;
}

interface Deque<T> extends Collection {
    T front() throws CollectionException;
    T back() throws CollectionException;
    void enqueue(T x) throws CollectionException;
    void enqueueFront(T x) throws CollectionException;
    T dequeue() throws CollectionException;
    T dequeueBack() throws CollectionException;
}

interface Stack<T> extends Collection {
    T top() throws CollectionException;
    void push(T x) throws CollectionException;
    T pop() throws CollectionException;
}

@SuppressWarnings("unchecked")
class ArrayDeque<T> implements Deque<T>, Stack<T>, Sequence<T> {
    private static final int DEFAULT_CAPACITY = 64;
    private final T[] a;
    private int front, back, size;

    public ArrayDeque() {
        a = (T[]) (new Object[DEFAULT_CAPACITY]);
        front = 0;
        back = 0;
        size = 0;
    }

    public ArrayDeque(int num) {
        a = (T[]) (new Object[num]);
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

}

@SuppressWarnings("unchecked")
class Calc {
    private Sequence<Stack<String>> sequence;
    private Scanner in;
    private boolean check;

    Calc() throws CollectionException {
        sequence = new ArrayDeque<Stack<String>>(42);
        for (int i = 0; i < 42; i++) {
            sequence.add(new ArrayDeque<String>());
        }
    }

    void drugScan(String vrstica) throws CollectionException {
        in = new Scanner(vrstica);
        check = false;
        while(in.hasNext()) {
            String niz = in.next();
            operacije(niz);
        }
    }

    void operacije(String niz) throws CollectionException {
        if ((niz.charAt(0) == '?' && check) || niz.charAt(0) != '?') {
            if(niz.charAt(0) == '?') {
                niz = niz.substring(1);
            }
            switch (niz) {
                case "echo":
                    echo();
                    break;
                case "pop":
                    pop();
                    break;
                case "dup":
                    dup();
                    break;
                case "dup2":
                    dup2();
                    break;
                case "swap":
                    swap();
                    break;
                case "char":
                    charr();
                    break;
                case "even":
                    even();
                    break;
                case "odd":
                    odd();
                    break;
                case "!":
                    fakt();
                    break;
                case "len":
                    len();
                    break;
                case "<>":
                    razlicen();
                    break;
                case "<":
                    manjsi();
                    break;
                case "<=":
                    manjsiE();
                    break;
                case "==":
                    enak();
                    break;
                case ">":
                    vecji();
                    break;
                case ">=":
                    vecjiE();
                    break;
                case "+":
                    plus();
                    break;
                case "-":
                    minus();
                    break;
                case "*":
                    krat();
                    break;
                case "/":
                    deli();
                    break;
                case "%":
                    mod();
                    break;
                case ".":
                    dot();
                    break;
                case "rnd":
                    rnd();
                    break;
                case "then":
                    then();
                    break;
                case "else":
                    elsee();
                    break;
                case "print":
                    print();
                    break;
                case "clear":
                    clear();
                    break;
                case "reverse":
                    reverse();
                    break;
                case "run":
                    run();
                    break;
                case "loop":
                    loop();
                    break;
                case "fun":
                    fun();
                    break;
                case "move":
                    move();
                    break;
                default:
                    add(niz);
                    break;
            }
        }
    }

    //1. SKLOP OPERACIJ - OSNOVE
    void add(String niz) throws CollectionException {
        sequence.get(0).push(niz);
    }

    void echo() throws CollectionException {
        if (sequence.get(0).isEmpty()) {
            System.out.println();
        }
        else {
            System.out.println(sequence.get(0).top());
        }
    }

    void pop() throws CollectionException {
        sequence.get(0).pop();
    }
    void dup() throws CollectionException {
        sequence.get(0).push(sequence.get(0).top());
    }

    void dup2() throws CollectionException {
        String y = sequence.get(0).pop();
        String x = sequence.get(0).pop();
        for (int i = 0; i < 2; i++) {
            sequence.get(0).push(x);
            sequence.get(0).push(y);
        }
    }

    void swap() throws CollectionException {
        String y = sequence.get(0).pop();
        String x = sequence.get(0).pop();
        sequence.get(0).push(y);
        sequence.get(0).push(x);
    }

    //2. SKLOP OPERACIJ - OSNOVE PT 2

    void charr() throws CollectionException {
        double x = Double.parseDouble(sequence.get(0).pop());
        char ch = (char) x;
        sequence.get(0).push(String.valueOf(ch));
    }
    void even() throws CollectionException {
        int x = Math.abs(Integer.parseInt(sequence.get(0).pop()));
        if(x % 2 == 0) {
            sequence.get(0).push("1");
        }
        else {
            sequence.get(0).push("0");
        }
    }

    void odd() throws CollectionException {
        int x = Math.abs(Integer.parseInt(sequence.get(0).pop()));
        if(x % 2 == 1) {
            sequence.get(0).push("1");
        }
        else {
            sequence.get(0).push("0");
        }
    }

    void fakt() throws CollectionException {
        int x = Integer.parseInt(sequence.get(0).pop());
        int sest = 1;

        for (int i = 2; i <= x; i++) {
            sest *= i;
        }
        sequence.get(0).push(Integer.toString(sest));
    }

    void len() throws CollectionException {
        String x = sequence.get(0).pop();
        sequence.get(0).push(Integer.toString(x.length()));
    }

    //3. SKLOP OPERACIJ - ALE
    void razlicen() throws CollectionException {
        int x = Integer.parseInt(sequence.get(0).pop());
        int y = Integer.parseInt(sequence.get(0).pop());

        sequence.get(0).push(x == y ? "0" : "1");
    }

    void manjsi() throws CollectionException {
        int x = Integer.parseInt(sequence.get(0).pop());
        int y = Integer.parseInt(sequence.get(0).pop());

        sequence.get(0).push(y < x ? "1" : "0");
    }

    void manjsiE() throws CollectionException {
        int x = Integer.parseInt(sequence.get(0).pop());
        int y = Integer.parseInt(sequence.get(0).pop());

        sequence.get(0).push(y <= x ? "1" : "0");
    }

    void enak() throws CollectionException {
        int x = Integer.parseInt(sequence.get(0).pop());
        int y = Integer.parseInt(sequence.get(0).pop());

        sequence.get(0).push(x == y ? "1" : "0");
    }

    void vecji() throws CollectionException {
        int x = Integer.parseInt(sequence.get(0).pop());
        int y = Integer.parseInt(sequence.get(0).pop());

        sequence.get(0).push(y > x ? "1" : "0");
    }

    void vecjiE() throws CollectionException {
        int x = Integer.parseInt(sequence.get(0).pop());
        int y = Integer.parseInt(sequence.get(0).pop());

        sequence.get(0).push(y >= x ? "1" : "0");
    }

    void plus() throws CollectionException {
        int x = Integer.parseInt(sequence.get(0).pop());
        int y = Integer.parseInt(sequence.get(0).pop());

        sequence.get(0).push(Integer.toString(x + y));
    }

    void minus() throws CollectionException {
        int x = Integer.parseInt(sequence.get(0).pop());
        int y = Integer.parseInt(sequence.get(0).pop());

        sequence.get(0).push(Integer.toString(y - x));
    }


    void krat() throws CollectionException {
        int x = Integer.parseInt(sequence.get(0).pop());
        int y = Integer.parseInt(sequence.get(0).pop());

        sequence.get(0).push(Integer.toString(x * y));
    }

    void deli() throws CollectionException {
        int x = Integer.parseInt(sequence.get(0).pop());
        int y = Integer.parseInt(sequence.get(0).pop());

        sequence.get(0).push(Integer.toString(y / x));
    }

    void mod() throws CollectionException {
        int x = Integer.parseInt(sequence.get(0).pop());
        int y = Integer.parseInt(sequence.get(0).pop());
        try {
            sequence.get(0).push(Integer.toString(y % x));
        } catch (ArithmeticException ignored) {}
    }

    void dot() throws CollectionException {
        String x = sequence.get(0).pop();
        String y = sequence.get(0).pop();

        sequence.get(0).push((y + x));
    }

    void rnd() throws CollectionException {
        int x = Integer.parseInt(sequence.get(0).pop());
        int y = Integer.parseInt(sequence.get(0).pop());

        int z = (int) (Math.random() * ((x - y)) + y);
        sequence.get(0).push(Integer.toString(z));
    }

    //4. SKLOP - POGOJNE OPERACIJE

    void then() throws CollectionException {
        int x = Integer.parseInt(sequence.get(0).pop());
        check = (x != 0);
    }

    void elsee() throws CollectionException {
        check = !check;
    }
    //5. SKLOP - OPERACIJE NAD VSEMI

    void print() throws CollectionException {
        StringBuilder output = new StringBuilder();
        Stack<String> zac = new ArrayDeque<String>();

        int sklad = Integer.parseInt(sequence.get(0).pop());
        while(!sequence.get(sklad).isEmpty()) {
            zac.push(sequence.get(sklad).pop());
        }

        while(!zac.isEmpty()) {
            output.append(zac.top());
            output.append(" ");
            sequence.get(sklad).push(zac.pop());
        }

        System.out.println(output.toString());
    }

    void clear() throws CollectionException {
        int sklad = Integer.parseInt(sequence.get(0).pop());

        while(!sequence.get(sklad).isEmpty()) {
            String x = sequence.get(sklad).pop();
        }
    }

    void reverse() throws CollectionException {
        int sklad = Integer.parseInt(sequence.get(0).pop());
        Stack<String> nov = new ArrayDeque<String>();
        Stack<String> novnov = new ArrayDeque<String>();

        while(!sequence.get(sklad).isEmpty()) {
            nov.push(sequence.get(sklad).pop());
        }

        while(!nov.isEmpty()) {
            novnov.push(nov.pop());
        }

        while(!novnov.isEmpty()) {
            sequence.get(sklad).push(novnov.pop());
        }
    }

    void run() throws CollectionException {
        int sklad = Integer.parseInt(sequence.get(0).pop());
        Stack<String> nov = new ArrayDeque<String>();

        while(!sequence.get(sklad).isEmpty()) {
            nov.push(sequence.get(sklad).pop());
        }

        while (!nov.isEmpty()) {
            String x = nov.pop();
            operacije(x);
            sequence.get(sklad).push(x);
        }
    }

    void loop() throws CollectionException {
        int sklad = Integer.parseInt(sequence.get(0).pop());
        int stevilo = Integer.parseInt(sequence.get(0).pop());
        Stack<String> nov = new ArrayDeque<String>();
            for (int i = 0; i < stevilo; i++) {
                check = false;

                while (!sequence.get(sklad).isEmpty()) {
                    nov.push(sequence.get(sklad).pop());
                }

                while (!nov.isEmpty()) {
                    String x = nov.pop();
                    operacije(x);
                    sequence.get(sklad).push(x);
                }
            }
    }

    void fun() throws CollectionException {
        int sklad = Integer.parseInt(sequence.get(0).pop());
        int stevilo = Integer.parseInt(sequence.get(0).pop());

        for (int i = 0; i < stevilo; i++) {
            sequence.get(sklad).push(in.next());
        }
    }

    void move() throws CollectionException {
        int sklad = Integer.parseInt(sequence.get(0).pop());
        int stevilo = Integer.parseInt(sequence.get(0).pop());

        for (int i = 0; i < stevilo; i++) {
            sequence.get(sklad).push(sequence.get(0).pop());
        }
    }
}

public class Naloga1 {
    public static void main(String[] args) throws CollectionException{
        Calc nov = new Calc();
        Scanner sc_v = new Scanner(System.in);
        while(sc_v.hasNextLine()) {
            String vrstica = sc_v.nextLine();
            nov.drugScan(vrstica);
            for (int i = 0; i < 42; i++) {
                nov.add(Integer.toString(i));
                nov.clear();
            }
        }
    }
}
