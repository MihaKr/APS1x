import java.util.Scanner;

public class Naloga3 {
    static class ArrayDeque<T> {
        private T[] a;
        private int front, back, size;

        public ArrayDeque(int size) {
            a = (T[]) (new Object[size]);
            front = 0;
            back = 0;
            size = 0;
        }

        public T get(int i){
            T vmesni = a[i];
            return vmesni;
        }

        public void add(T x){
            a[size] = x;
            size++;
        }
    }

    static class graph {
        private int[][] matrix;
        public int size;
        public boolean type;
        public int[] paths;
        public int nodes;

        public ArrayDeque<Integer> in = new ArrayDeque<Integer>(this.size);
        public ArrayDeque<Integer> out = new ArrayDeque<Integer>(this.size);



        public graph(int size, boolean type) {
            this.size = size;
            this.type = type;
            this.matrix = new int[this.size][this.size];
            this.paths = new int[this.size];
        }

        public void addNode(int y, int x) {
            if(this.matrix[y][x] == 0) {
                this.nodes++;
                this.matrix[y][x] = 1;
                if (!this.type) {
                    this.matrix[x][y] = 1;
                }
            }
        }

        public void info() {
            System.out.println(this.size + " " + this.nodes);
            for (int i = 0; i < size; i++) {
                int counter = 0;
                for (int j = 0; j < size; j++) {
                    if (this.matrix[i][j] == 1) {
                        counter++;
                    }
                }
                int counter2 = 0;

                if(this.type) {
                    for (int k = 0; k < size; k++) {
                        if(k != i) {
                            if(this.matrix[k][i] == 1) {
                                counter2++;
                            }
                        }
                    }
                }
                if(!this.type) {
                    System.out.printf("%d %d\n", i, counter);
                }
                else {
                    System.out.printf("%d %d %d\n", i, counter, counter2);
                }
            }
        }

        public void walks(int k) {
            int [][] rez = this.matrix;
            int [][] vmes = new int[this.size][this.size];

            for (int i = 0; i < k-1; i++) {
                vmes = new int[this.size][this.size];
                for (int g = 0; g < this.size; g++) {
                    for (int h = 0; h < this.size; h++) {
                        for (int u = 0; u < this.size; u++) {
                            vmes[g][h] =  vmes[g][h] + this.matrix[g][u] * rez[u][h];
                        }
                    }
                }
                rez = vmes;
            }

            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    System.out.print(rez[i][j]);

                    if(j != size - 1) {
                        System.out.print(" ");
                    }
                }
                System.out.println();
            }
        }
        public void DFS() {
            this.paths = new int[this.size];
            for (int i = 0; i < this.size; i++) {
                this.DFS();
            }
        }
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int walks = 0;

        String first = sc.nextLine();
        String[] firstARR = first.split(" ");
        boolean type;
        if(firstARR[0].equals("undirected")) {
            type = false;
        }
        else {
            type = true;
        }
        String operation = firstARR[1];

        if(firstARR.length == 3) {
            walks = Integer.parseInt(firstARR[2]);
        }

        String test = sc.nextLine().trim();
        int size = Integer.parseInt(test);

        graph graph = new graph(size, type);

        while (sc.hasNextLine()) {
            String path = sc.nextLine();
            String [] path2 = path.split(" ");
            graph.addNode(Integer.parseInt(path2[0]), Integer.parseInt(path2[1]));
        }

        switch (operation) {
            case "info":
                graph.info();
                break;
            case "walks":
                graph.walks(walks);
                break;
        }
    }
}
