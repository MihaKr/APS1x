import javax.crypto.spec.PSource;
import javax.swing.plaf.synth.SynthOptionPaneUI;
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

        select(type);
    }

    void select(String type) {
        switch (type) {
            case "insert":
                insertSort();
                insertSort();
                break;
            case "select":
                selectSort();
                break;
            case "bubble":
                bubbleSort();
                break;
            case "merge":
                for (int i = 0; i < this.field.length; i++) {
                    System.out.print(this.field[i] + " ");
                }
                System.out.println();
                mergeSort(this.field);
        }
    }

    void insertSort() {
        int comp = 0;
        int move = 0;
        int div = 1;

        for (int k = 0; k < this.field.length; k++) {
            System.out.print(this.field[k] + " ");
        }
        System.out.println();

        for (int i = 1; i <= this.field.length-1; i++) {
            int key = this.field[i];
            move++;

            int j = i;

            comp++;
            if (this.dir.equals("up")) {
                comp++;
                while (j > 0 && this.field[j-1] > key) {
                    this.field[j] = this.field[j-1];
                    move++;

                    j = j - 1;
                }
            } else {
                comp++;
                while (j > 0 && this.field[j-1] < key) {
                    this.field[j] = this.field[j-1];
                    move++;
                    j = j - 1;
                }
            }
            if (this.field[j] != key) {
                this.field[j] = key;
                move++;
            }

            insertTrace(div, i);

            div++;
        }
        System.out.println(move);
        System.out.println(comp);

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

            insertTrace(div,i);
            div++;
        }
    }

    void bubbleSort() {
        int i, j, temp;
        boolean swapped;
        int div = 0;

        for (i = 0; i < this.field.length - 1; i++) {
            swapped = false;

            for (j = 0; j < this.field.length - i - 1; j++) {
                if (this.dir.equals("up")) {
                    if (this.field[j] > this.field[j + 1]) {
                        temp = this.field[j];
                        this.field[j] = this.field[j + 1];
                        this.field[j + 1] = temp;
                        swapped = true;
                    }
                }
                else {
                    if (this.field[j] < this.field[j + 1]) {
                        temp = this.field[j];
                        this.field[j] = this.field[j + 1];
                        this.field[j + 1] = temp;
                        swapped = true;
                    }
                }
            }

            StringBuilder trace = new StringBuilder();
            for (int k = 0; k < this.field.length; k++) {
                trace.append(this.field[k] + " ");
                if (div == k) {
                    trace.append("| ");
                }
            }
            div++;
            System.out.println(trace.toString());

            if (swapped == false) {
                break;
            }
        }
        System.out.println(12);
    }

    void heapSort() {
        for (int i = this.field.length / 2 - 1; i >= 0; i--) {
            siftDown(this.field, i);
            System.out.println("NOT DONE");
        }
    }

    void siftDown(int[] field, int i) {

    }

    int[] mergeSort(int[] array) {
        int middle;
        int lenFix = 0;
        if(array.length <= 1) {
            return array;
        }

        int[] leftV;
        int[] rightV;

        if(array.length % 2 == 0) {
            middle = (array.length) / 2;
            leftV = new int[middle];
            rightV = new int[middle];
        }
        else {
            middle = (array.length-1) / 2;
            leftV = new int[middle + 1];
            rightV = new int[middle];
            lenFix = 1;
        }


        for (int i = 0; i < leftV.length; i++) {
            leftV[i] = array[i];
        }

        for (int i = 0; i < rightV.length  ; i++) {
            rightV[i] = array[i+middle+lenFix];
        }

        mergeTrace(leftV, rightV);

        int[] left = mergeSort(leftV);
        int[] right = mergeSort(rightV);

        int [] merged = merge(left,right);

        for (int i = 0; i < merged.length; i++) {
            System.out.print(merged[i] + " ");
        }

        System.out.println();

        return merged;
    }

    int[] merge(int[] left, int[] right) {
        int mergedP = 0;
        int rightP = 0;
        int leftP= 0;
        int leftSize = left.length;
        int rightSize = right.length;

        int[] merged = new int[(leftSize + rightSize)];

        while ((rightP < rightSize) && (leftP < leftSize)) {
            if(this.dir.equals("up")) {
                if (right[rightP] < left[leftP]) {
                    merged[mergedP] = right[rightP];
                    rightP++;
                    mergedP++;
                } else {
                    merged[mergedP] = left[leftP];
                    leftP++;
                    mergedP++;
                }
            }
            else {
                if (right[rightP] > left[leftP]) {
                    merged[mergedP] = right[rightP];
                    rightP++;
                    mergedP++;
                } else {
                    merged[mergedP] = left[leftP];
                    leftP++;
                    mergedP++;
                }
            }
        }

        while (mergedP < merged.length) {
            if (rightP < rightSize) {
                merged[mergedP] = right[rightP];
                rightP++;
                mergedP++;
            }
            if (leftP < leftSize) {
                merged[mergedP] = left[leftP];
                leftP++;
                mergedP++;
            }
        }
        return merged;
    }

    void insertTrace(int div, int i) {
        StringBuilder trace = new StringBuilder();
        for (int k = 0; k < this.field.length; k++) {
            trace.append(this.field[k] + " ");
            if (div == k) {
                trace.append("| ");
            }
        }
        System.out.println(trace.toString().trim());
    }

    void mergeTrace(int [] left, int[] right) {
        StringBuilder traced = new StringBuilder();
        for (int i = 0; i < left.length; i++) {
            traced.append(left[i]);
            traced.append(" ");
        }

        traced.append("| ");

        for (int i = 0; i < right.length; i++) {
            traced.append(right[i]);
            traced.append(" ");
        }

        System.out.println(traced.toString().trim());

    }
}