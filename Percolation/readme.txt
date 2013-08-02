This project was done to solve the computational problem
of calculating the percolation state of a N-by-N grid with randomly
distributed open slots. I wrote two Java classes to solve this problem.

Percolation.java
---------------------
---------------------
This class sets up the N-by-N grid.
It set's up probabilistic opening of a slot, etc.
Methods:
 - Percolation(int N) // constructor
 - open(int i, int j)
 - isOpen(int i, int j)
 - isFull(int i, int j)
 - pecolates() // checks whether the system is percolated or not


PercolationStata.java
---------------------
---------------------
This class sets up Monte Carlo simulation for the percolation problem.
It generates N-by-N percolation boards T number of times.
Methods:
 - PercolationStats(int N, int T) // constructor
 - mean()
 - stddev()
 - confidenceLo()
 - confidenceHi()
