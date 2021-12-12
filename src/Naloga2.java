import java.util.Scanner;

public class Naloga2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] ops = scanner.nextLine().split(" ");
        String[] num = scanner.nextLine().split(" ");

        sorter sort = new sorter(ops, num);
    }
}

class sorter {
    private int field[];
    private String mode;
    private String dir;
    private String type;

    public sorter(String[] ops, String[] num) {
        this.field = new int[num.length];
        for (int i = 0; i < num.length; i++) {
            this.field[i] = Integer.parseInt(num[i]);
        }
        dir = ops[2];
        mode = ops[0];
        type = ops[1];

        insertSort();
    }

    void insertSort() {
        int div = -1;
        for (int i = 0; i < this.field.length; i++) {
            int key = this.field[i];
            int j = i - 1;

            if(this.dir.equals("up")) {
                while (j >= 0 && this.field[j] > key) {
                    this.field[j + 1] = this.field[j];
                    j = j - 1;
                }
            }
            else {
                while (j >= 0 && this.field[j] < key) {
                    this.field[j + 1] = this.field[j];
                    j = j - 1;
                }
            }
            this.field[j + 1] = key;

            div++;

            StringBuilder trace = new StringBuilder();
            for (int k = 0; k < this.field.length; k++) {
                trace.append(this.field[k]+ " ");
                if((div == k) && (i > 0)) {
                    trace.append("| ");
                }
            }
            System.out.println(trace.toString());
        }
    }
}