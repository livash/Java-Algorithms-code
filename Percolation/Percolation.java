public class Percolation {

    private int[][] arr;

    public Percolation(int N) {
        arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i > arr.length || j > arr.length || i < 0 || j < 0) {
                    throw new java.lang.IndexOutOfBoundsException();
                }
                arr[i][j] = 0;
            }
        }
    }
    // open one site (row i, column j) if it is not already

    public void open(int i, int j) {
        i = i - 1;
        j = j - 1;
        if (i > arr.length || j > arr.length || i < 0 || j < 0) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        arr[i][j] = 1;
    }
    // is site (row i, column j) open?

    public boolean isOpen(int i, int j) {
        i = i - 1;
        j = j - 1;
        if (i > arr.length || j > arr.length || i < 0 || j < 0) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        return arr[i][j] == 1;
    }
    // is site (row i, column j) full?

    public boolean isFull(int i, int j) {
        i = i - 1;
        j = j - 1;
        if (i > arr.length || j > arr.length || i < 0 || j < 0) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        return arr[i][j] == 0;
    }

    public boolean percolates() {
        int N = arr.length;
        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(N * N + 2);
        int k1 = 0; //position 1 in the id array of the obect "uf"
        int k2 = 0; //position 2 in the id array of the obect "uf"
        for (int iRan = 2; iRan < N; iRan++) {
            for (int jRan = 2; jRan < N; jRan++) {
                if (isOpen(iRan, jRan)) {
                    k1 = iRan + N * (jRan - 1);
                    if (isOpen(iRan, jRan - 1)) {
                        k2 = iRan + N * (jRan - 1 - 1);
                        uf.union(k1, k2);
                    }
                    if (isOpen(iRan + 1, jRan)) {
                        k2 = (iRan + 1) + N * (jRan - 1);
                        uf.union(k1, k2);
                    }
                }
            }
        }
        for (int jj = 1; jj < N; jj++) {
            if (isOpen(N, jj)) {
                k1 = N + N * (jj - 1);
                if (isOpen(N, jj + 1)) {
                    k2 = N + N * (jj - 1 + 1);
                    uf.union(k1, k2);
                }
            }
        }
        for (int ii = 1; ii <= (N - 1); ii++) {
            if (isOpen(ii, N)) {
                k1 = ii + N * (1 - 1);
                if (isOpen(ii + 1, 1)) {
                    k2 = (ii + 1) + N * (1 - 1);
                    uf.union(k1, k2);
                }
            }
        }
        for (int i = 1; i <= N; i++) {
            if (isOpen(i, 1)) {
                k1 = i + N * (1 - 1);
                uf.union(0, k1);
            }
        }
        for (int i = 1; i <= N; i++) {
            if (isOpen(i, N)) {
                k1 = i + N * (N - 1);
                uf.union(N * N + 1, k1);
            }
        }
        return uf.connected(0, N * N + 1);
    }
}