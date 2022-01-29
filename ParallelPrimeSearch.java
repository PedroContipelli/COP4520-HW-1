import java.util.*;
import java.io.*;

public class ParallelPrimeSearch
{

	public static void main(String[] args) throws IOException, InterruptedException
	{
		// Concurrent Sieve for prime numbers up to 10^8
		int checkUpTo = 100_000_000;
		int[] sieve = new int[checkUpTo + 1];
		Arrays.fill(sieve, -1);
		sieve[0] = 0;
		sieve[1] = 0;

		long startTime = System.currentTimeMillis();

		int numThreads = 8;
		Thread[] arr = new Thread[numThreads];

		for (int i = 0; i < numThreads; i++)
		{
			arr[i] = new SieveThread(sieve);
			arr[i].start();
		}

		for (int i = 0; i < numThreads; i++)
			arr[i].join();

		long endTime = System.currentTimeMillis();
		long executionTime = endTime - startTime;

		ArrayList<Integer> primes = new ArrayList<>(6_000_000);
		long sumOfPrimes = 0;

		for (int i = 2; i < sieve.length; i++)
			if (sieve[i] == 1)
			{
				primes.add(i);
				sumOfPrimes += i;
			}

		String last10Primes = "";

		for (int i = primes.size() - 10; i < primes.size(); i++)
			last10Primes += primes.get(i) + " ";

		FileWriter out = new FileWriter(new File("primes.txt"));

		String output = String.format("%dms %d %d\n%s\n", executionTime, primes.size(), sumOfPrimes, last10Primes);

		out.write(output);
		out.flush();

		System.out.print(output);
	}

}
