import java.util.Scanner;

public class Naloga2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] ops = scanner.nextLine().split(" ");
        String[] num = scanner.nextLine().split(" ");
    }
}

class sorter {
    private int field[];
    private String mode;
    private String dir;
    private String type;

    public sorter(String[] in) {
        this.field = new int[in.length];
        for (int i = 0; i < in.length; i++) {
            this.field[i] = Integer.parseInt(in[i]);
        }
    }




}