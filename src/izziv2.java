import java.util.Random;

public class izziv2 {
    static int[] generateTable(int n) {
        int[] a = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = i + 1;
        }

        return a;
    }

    static int findLinear(int[] a, int v) {
        for (int vnos: a) {
            if (vnos == v) {
                return vnos;
            }
        }
        return -1;
    }

    static int findBinary(int[] a, int l, int r, int v) {
        if (r >= l) {
            int sred = l + (r - l) / 2;

            if (a[sred] == v)
                return sred;

            if (a[sred] > v)
                return findBinary(a, l, sred - 1, v);

            return findBinary(a, sred + 1, r, v);
        }

        return -1;
    }

    static long timeLinear(int n) {
        int [] x = generateTable(n);
        long startTime = System.nanoTime();
        Random rand = new Random();


        for (int i = 0; i < 1000; i++) {
            int stevilo = rand.nextInt(n);
            int y = findLinear(x, stevilo);
        }

        long executionTime = System.nanoTime() - startTime;
        long povp = executionTime/1000;
        return povp;
    }

    static long timeBinary(int n) {
        int [] x = generateTable(n);
        long startTime = System.nanoTime();
        Random rand = new Random();


        for (int i = 0; i < 1000; i++) {
            int stevilo = rand.nextInt(n);
            int y = findBinary(x,0,n, stevilo);
        }

        long executionTime = System.nanoTime() - startTime;
        long povp = executionTime/1000;
        return povp;
    }

    public static void main(String[] args) {
        StdDraw.setPenRadius(0.015);
        int z = 100000;
        System.out.println("   n       |     linearno  |   dvojisko  |");
        System.out.println("---------+--------------+------------------");
        int i = 1;
        StdDraw.setPenColor(StdDraw.BLUE);

        while (z <= 1000000) {
            double stevec = i/91.0;
            StdDraw.setPenColor(StdDraw.BLUE);
            long prvi = timeLinear(z);
            double prvix = prvi/1000000.0;
            StdDraw.point(stevec, prvix);
            long drugi = timeBinary(z);
            double drugix = drugi/1000000.0;
            StdDraw.setPenColor(StdDraw.MAGENTA);
            StdDraw.point(stevec, drugix);
            System.out.printf("%10d | %13d | %10d\n", z, prvi, drugi);
            z+=10000;
            i++;
        }
    }
}
