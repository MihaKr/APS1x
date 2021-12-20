import java.util.Scanner;

//abandon hope all ye who enter here (za tiste ki želite razumet kodo)


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
    private int dir;
    private String type;
    private int compares;
    private int moves;
    private String counting;

    public sorter(String[] ops, String[] num) {
        this.field = new int[num.length];
        for (int i = 0; i < num.length; i++) {
            this.field[i] = Integer.parseInt(num[i]);
        }
        if(ops[2].equals("up")) {
            dir = 1;
        }
        else {
            dir = -1;
        }
        mode = ops[0];
        type = ops[1];

        select(type);
    }

    public boolean comparable(int[] array, int x, int y) {
        this.compares++;
        return (dir * array[x]) > (y * dir) ;
    }

    void select(String type) {
        switch (type) {
            case "insert":
                this.counting = "";
                insertSort();
                if(mode.equals("count")) {
                    insertSort();
                    this.dir *= -1;
                    insertSort();
                    this.counting =  this.counting.substring(0, this.counting.length() - 2);
                    System.out.println(this.counting);
                }
                break;
            case "select":
                this.counting = "";
                selectSort();
                if(mode.equals("count")) {
                    selectSort();
                    this.dir *= -1;
                    selectSort();
                    this.counting = this.counting.substring(0, this.counting.length() - 2);
                    System.out.println(this.counting);
                }
                break;
            case "bubble":
                bubbleSort();
                break;
            case "merge":
                StringBuilder mid = new StringBuilder();
                this.moves = 0;
                this.compares = 0;
                this.counting = "";

                if(this.mode.equals("trace")) {
                    for (int i = 0; i < this.field.length; i++) {
                        System.out.print(this.field[i] + " ");
                    }
                    System.out.println();
                }
                this.field = mergeSort(this.field);
                mid.append(this.moves + " ");
                mid.append(this.compares + " ");
                mid.append("| ");

                if(mode.equals("count")) {
                    this.moves = 0;
                    this.compares = 0;
                    this.field = mergeSort(this.field);
                    mid.append(this.moves + " ");
                    mid.append(this.compares + " ");
                    mid.append("| ");

                    this.moves = 0;
                    this.compares = 0;
                    this.dir *= -1;
                    this.field = mergeSort(this.field);
                    mid.append(this.moves + " ");
                    mid.append(this.compares + " ");
                    System.out.println(mid.toString());
                }
                break;

            case "heap":
                heapSort();
                break;
            }
        }

    void insertSort() {
        StringBuilder mid = new StringBuilder();
        this.moves = 0;
        this.compares = 0;

        int div = 1;

        if(mode.equals("trace")) {
            for (int k = 0; k < this.field.length; k++) {
                System.out.print(this.field[k] + " ");
            }
            System.out.println();
        }

        for (int i = 1; i <= this.field.length-1; i++) {
            int key = this.field[i];
            this.moves++;

            int j = i;

            while (j > 0 && comparable(this.field, j-1, key)) {
                this.field[j] = this.field[j-1];
                this.moves++;
                j = j - 1;
            }

            this.field[j] = key;
            this.moves++;

            if(mode.equals("trace")) {
                insertTrace(div);
            }
            div++;
        }

        if(mode.equals("count")) {
            mid.append(this.moves + " ");
            mid.append(this.compares + " ");
            mid.append("| ");
            this.counting += mid.toString();
        }
    }

    void selectSort() {
        StringBuilder mid = new StringBuilder();
        this.moves = 0;
        this.compares = 0;
        int div = 0;

        if(mode.equals("trace")) {
            StringBuilder zac = new StringBuilder();

            for (int k = 0; k < this.field.length; k++) {
                zac.append(this.field[k] + " ");
            }
            System.out.println(zac);
        }

        for (int i = 0; i <= this.field.length - 2; i++) {
            int min = i;
            for (int j = i + 1; j <= this.field.length-1; j++) {
                if(!comparable(this.field, j, this.field[min])){
                    min = j;
                }
            }

            swap(i,min);

            if(mode.equals("trace")) {
                insertTrace(div);
            }
            div++;
        }

        if(mode.equals("count")) {
            mid.append(this.moves + " ");
            mid.append(this.compares + " ");
            mid.append("| ");
            this.counting += mid.toString();
        }
    }

    void bubbleSort() {
        int m = 0;
        int div = 1;
        for (int i = 0; i < this.field.length; i++) {
            System.out.print(this.field[i] + " ");
        }
        System.out.println();

        for (int i = 0; i < this.field.length-1; i = m) {
            m = this.field.length-1;
            for (int j = this.field.length-2; j >= i; j--) {
                if(comparable(this.field, j, this.field[j+1])) {
                    swap(j,j+1);
                    m = j;
                }
            }

            div += bubbleDiv(this.field);
            insertTrace(div);
        }
        System.out.println(12);
    }

    void heapSort() {
        for (int i = 0; i < this.field.length; i++) {
            System.out.print(this.field[i] + " ");
        }

        System.out.println();

        for (int i = this.field.length / 2 - 1; i >= 0; i--) {
            siftDown(this.field, this.field.length, i);
        }
        int last = this.field.length - 1;

        while (last > 0) {
            insertTrace(last);
            swap(0,last);
            siftDown(this.field, last--, 0);
        }
        insertTrace(last);
    }

    void siftDown(int[] field, int size, int i) {
        int max = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < size && comparable(this.field, left, field[max])) {
            max = left;
        }

        if (right < size && comparable(this.field, right, field[max])) {
            max = right;
        }

        if (max != i) {
            swap(i, max);
            siftDown(field, size, i);
        }
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
            this.moves++;

        }

        for (int i = 0; i < rightV.length  ; i++) {
            rightV[i] = array[i+middle+lenFix];
            this.moves++;

        }

        if(mode.equals("trace")) {
            mergeTrace(leftV, rightV);
        }

        int[] left = mergeSort(leftV);
        int[] right = mergeSort(rightV);

        int [] merged = merge(left,right);

        if(mode.equals("trace")) {
            for (int i = 0; i < merged.length; i++) {
                System.out.print(merged[i] + " ");
            }
            System.out.println();
        }
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
            this.compares++;
            if (this.dir * right[rightP] < left[leftP] * this.dir) {
                merged[mergedP] = right[rightP];
                rightP++;
                mergedP++;
                this.moves++;

            } else {
                merged[mergedP] = left[leftP];
                leftP++;
                mergedP++;
                this.moves++;
            }
        }

        while (mergedP < merged.length) {
            if (rightP < rightSize) {
                merged[mergedP] = right[rightP];
                rightP++;
                mergedP++;
                this.moves++;
            }
            if (leftP < leftSize) {
                merged[mergedP] = left[leftP];
                leftP++;
                mergedP++;
                this.moves++;
            }
        }
        return merged;
    }

    void parition() {

    }

    int bubbleDiv(int [] arr) {
        int div = 0;
        for (int i = 0; i < arr.length-1; i++) {
            for (int j = 0; j < arr.length-2; j++) {
                if(!comparable(arr, i, arr[j+1])) {
                    return div;
                }
                else {
                    div++;
                }
            }
        }
        return div;
    }

    private void swap(int a, int b) {
        int tmp = this.field[a];
        this.field[a] = this.field[b];
        this.field[b] = tmp;
        this.moves += 3;
    }

    void insertTrace(int div) {
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