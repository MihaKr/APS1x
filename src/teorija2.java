import java.util.Stack;

public class teorija2 {
    static void obrni(Stack s, int n, int m) {
        Stack<Integer> s1 = new Stack<Integer>();
        Stack<Integer> s2 = new Stack<Integer>();
        Stack<Integer> s3 = new Stack<Integer>();

        for (int i = 0; i < n; i++) {
            s1.push((Integer) s.pop());
        }

        for (int i = 0; i < m; i++) {
            s2.push((Integer) s.pop());
        }
        for (int i = 0; i < m; i++) {
            s3.push(s2.pop());
        }

        for (int i = 0; i < m; i++) {
            s.push(s3.pop());
        }
        for (int i = 0; i < n; i++) {
            s.push( s1.pop());
        }
    }
    public static void main(String[] args) {
        Stack<Integer> s = new Stack<Integer>();
        s.push(1);
        s.push(2);
        s.push(3);
        s.push(4);
        s.push(5);
        s.push(6);
        s.push(7);
        s.push(8);
        s.push(9);

        obrni(s,2,3);

        for (int i = 0; i < 9; i++) {
            System.out.println(s.pop());

        }
    }
}
