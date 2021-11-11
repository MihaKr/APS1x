package Izziv2;

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

    long timeLinear(int n) {
        return 0;

    }

    public static void main(String[] args) {
        int [] x = generateTable(1000000);
        System.out.println(findLinear(x, 5000));
        System.out.println(findBinary(x, 0,1000000,5000));
        }
    }
