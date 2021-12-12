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

        selectSort();
    }

    void insertSort() {
        int div = -1;
        for (int i = 0; i < this.field.length; i++) {
            int key = this.field[i];
            int j = i - 1;

            if (this.dir.equals("up")) {
                while (j >= 0 && this.field[j] > key) {
                    this.field[j + 1] = this.field[j];
                    j = j - 1;
                }
            } else {
                while (j >= 0 && this.field[j] < key) {
                    this.field[j + 1] = this.field[j];
                    j = j - 1;
                }
            }
            this.field[j + 1] = key;

            div++;

            StringBuilder trace = new StringBuilder();
            for (int k = 0; k < this.field.length; k++) {
                trace.append(this.field[k] + " ");
                if ((div == k) && (i > 0)) {
                    trace.append("| ");
                }
            }
            System.out.println(trace.toString());
        }
    }

    void selectSort() {
        int div = 0;

        StringBuilder zac = new StringBuilder();

        for (int k = 0; k < this.field.length; k++) {
            zac.append(this.field[k] + " ");
        }
        System.out.println(zac.toString());

        for (int i = 0; i < this.field.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < this.field.length; j++) {
                if (this.dir.equals("up")) {
                    if (this.field[j] < this.field[min]) {
                        min = j;
                    }
                }
                else {
                    if (this.field[j] > this.field[min]) {
                        min = j;
                    }
                }
            }

            int temp = this.field[min];
            this.field[min] = this.field[i];
            this.field[i] = temp;

            StringBuilder trace = new StringBuilder();
            for (int k = 0; k < this.field.length; k++) {
                trace.append(this.field[k] + " ");
                if (div == k) {
                    trace.append("| ");
                }
            }
            div++;
            System.out.println(trace.toString());
        }
    }
}