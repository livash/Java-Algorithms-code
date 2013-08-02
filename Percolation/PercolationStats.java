public class PercolationStats {

    private double[] x;

    public PercolationStats(int N, int T) {
        x = new double[T];
        for (int experiment = 0; experiment < T; experiment++) {
            Percolation array = new Percolation(N);
            WeightedQuickUnionUF uf = new WeightedQuickUnionUF(N * N + 2);
            int k1 = 0;
            int k2 = 0;
            int openSites = 0; //number of open sites in the 
            while (!uf.connected(0, N * N + 1)) {
                openSites++;
                int iRan = (int) (StdRandom.uniform(1, N + 1));
                int jRan = (int) (StdRandom.uniform(1, N + 1));
                while (array.isOpen(iRan, jRan)) {
                    iRan = (int) (StdRandom.uniform(1, N + 1));
                    jRan = (int) (StdRandom.uniform(1, N + 1));
                }
                array.open(iRan, jRan); //open one site
                k1 = iRan + N * (jRan - 1);
                if (iRan > 1 && jRan > 1 && iRan < N && jRan < N) {
                    if (array.isOpen(iRan, jRan - 1)) {
                        k2 = iRan + N * (jRan - 1 - 1);
                        uf.union(k1, k2);
                    }
                    if (array.isOpen(iRan + 1, jRan)) {
                        k2 = iRan + 1 + N * (jRan - 1);
                        uf.union(k1, k2);
                    }
                    if (array.isOpen(iRan, jRan + 1)) {
                        k2 = iRan + N * (jRan + 1 - 1);
                        uf.union(k1, k2);
                    }
                    if (array.isOpen(iRan - 1, jRan)) {
                        k2 = iRan - 1 + N * (jRan - 1);
                        uf.union(k1, k2);
                    }
                }
                if (jRan == 1) {
                    uf.union(0, k1);
                    if (iRan > 1 && iRan < N) {
                        if (array.isOpen(iRan - 1, jRan)) {
                            k2 = iRan - 1 + N * (jRan - 1);
                            uf.union(k1, k2);
                        }
                        if (array.isOpen(iRan + 1, jRan)) {
                            k2 = iRan + 1 + N * (jRan - 1);
                            uf.union(k1, k2);
                        }
                        if (array.isOpen(iRan, jRan + 1)) {
                            k2 = iRan + N * (jRan + 1 - 1);
                            uf.union(k1, k2);
                        }
                    }
                }
                if (jRan == N) {
                    uf.union(N * N + 1, k1);
                    if (iRan > 1 && iRan < N) {
                        if (array.isOpen(iRan - 1, jRan)) {
                            k2 = iRan - 1 + N * (jRan - 1);
                            uf.union(k1, k2);
                        }
                        if (array.isOpen(iRan + 1, jRan)) {
                            k2 = iRan + 1 + N * (jRan - 1);
                            uf.union(k1, k2);
                        }
                        if (array.isOpen(iRan, jRan - 1)) {
                            k2 = iRan + N * (jRan - 1 - 1);
                            uf.union(k1, k2);
                        }
                    }
                }
                if (iRan == 1) {
                    if (jRan > 1 && jRan < N) {
                        if (array.isOpen(iRan, jRan - 1)) {
                            k2 = iRan + N * (jRan - 1 - 1);
                            uf.union(k1, k2);
                        }
                        if (array.isOpen(iRan, jRan + 1)) {
                            k2 = iRan + N * (jRan + 1 - 1);
                            uf.union(k1, k2);
                        }
                        if (array.isOpen(iRan + 1, jRan)) {
                            k2 = iRan + 1 + N * (jRan - 1);
                            uf.union(k1, k2);
                        }
                    }
                }
                if (iRan == N) {
                    if (jRan > 1 && jRan < N) {
                        if (array.isOpen(iRan, jRan - 1)) {
                            k2 = iRan + N * (jRan - 1 - 1);
                            uf.union(k1, k2);
                        }
                        if (array.isOpen(iRan, jRan + 1)) {
                            k2 = iRan + N * (jRan + 1 - 1);
                            uf.union(k1, k2);
                        }
                        if (array.isOpen(iRan - 1, jRan)) {
                            k2 = iRan - 1 + N * (jRan - 1);
                            uf.union(k1, k2);
                        }
                    }
                }
                if (iRan == 1 && jRan == 1) {
                    if (array.isOpen(2, 1)) {
                        k2 = 2;
                        uf.union(k1, k2);
                    }
                    if (array.isOpen(1, 2)) {
                        k2 = 1 + N;
                        uf.union(k1, k2);
                    }
                }
                if (iRan == N && jRan == 1) {
                    if (array.isOpen(N - 1, 1)) {
                        k2 = N - 1;
                        uf.union(k1, k2);
                    }
                    if (array.isOpen(N, 2)) {
                        k2 = N + N;
                        uf.union(k1, k2);
                    }
                }
                if (iRan == N && jRan == N) {
                    if (array.isOpen(N - 1, N)) {
                        k2 = N - 1 + N * (N - 1);
                        uf.union(k1, k2);
                    }
                    if (array.isOpen(N, N - 1)) {
                        k2 = N + N * (N - 1 - 1);
                        uf.union(k1, k2);
                    }
                }
                if (iRan == 1 && jRan == N) {
                    if (array.isOpen(2, N)) {
                        k2 = 2 + N * (N - 1);
                        uf.union(k1, k2);
                    }
                    if (array.isOpen(1, N - 1)) {
                        k2 = 1 + N * (N - 1 - 1);
                        uf.union(k1, k2);
                    }
                }
            }
            x[experiment] = (double) openSites / (double) (N * N);
        }
    }

    public double mean() {
        int T = x.length;
        double sum = 0;
        for (int ii = 0; ii < T; ii++) {
            sum = sum + x[ii];
        }
        return sum / (double) T;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        double sigma = 0;
        for (int jj = 0; jj < x.length; jj++) {
            sigma = sigma + (mean() - x[jj]) * (mean() - x[jj]);
        }
        return Math.sqrt(sigma / (double) (x.length - 1));
    }

    public double confidenceLo() {
        return (mean() - 1.96 * stddev() / Math.sqrt((double) x.length));
    }

    public double confidenceHi() {
        return (mean() + 1.96 * stddev() / Math.sqrt((double) x.length));
    }

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        if (N <= 0 || T <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
        PercolationStats test = new PercolationStats(N, T);
        double a = test.confidenceLo();
        double b = test.confidenceHi();
        StdOut.println("mean                    = " + test.mean());
        StdOut.println("stddev                  = " + test.stddev());
        StdOut.println("95% vonfidence interval = " + a + ", " + b);
    }
}
