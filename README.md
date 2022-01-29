# COP4520-HW-1
Compile with: **javac ParallelPrimeSearch.java**<br>
Run with: **java ParallelPrimeSearch**

### Summary of Approach
The Sieve of Eratosthenes sequentially finds primes by eliminating all multiples of each number that has not yet been eliminated. My program modifies the sieve to have 8 threads eliminating multiples at the same time. That way, the work can be distributed and the program should be more efficient.

In order to accomplish this, I started off by having the numbers 2-10^8 unmarked. I then have each thread pick the first unmarked number it finds, guess at it being prime, and begin marking all of its multiples as composite. Notice: in marking the multiples I allow it to overwrite a number which
another thread has guessed is prime. And if at any point during a thread's execution, it realizes its number has been marked as composite, it stops checking multiples for that number and moves on to the next unmarked number.

### Reasoning about correctness
This algorithm works because a thread can only pick unmarked numbers to guess as primes, meaning the number has not already been marked as composite. This can only happen in 2 cases: either the number is prime (in which case our guess is correct) OR it is composite but has not been marked off yet. However, in that case, there will still be a thread under it responsible for checking all the multiples of that number’s factor, which will eventually reach the number and correctly mark it as composite.

In either case, our number will be correctly classified as either composite or prime by the end of the program’s execution. And this will be true for all numbers in our sieve.

### Efficiency of my design
Checking multiples using 8 concurrent threads offers a significant speedup over the traditional sieve as we can now be checking multiples of up to 8 primes at a time. Empirically, my **single-threaded** sieve implementation runtime is **1011ms** (approximately) whereas my **multi-threaded** sieve implementation runtime is **890ms** (approximately), which amounts to a **12% speedup**.

### Citations
Shoutout the boi Ἐρατοσθένης (Eratosthenes)
