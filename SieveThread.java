public class SieveThread extends Thread
{
	int[] sieve;
	
	public SieveThread(int[] sieve)
	{
		this.sieve = sieve;
	}
	
	public void run()
	{
		outer:
		for (int current = 2; current < sieve.length; current++)
		{
			// Take numbers which have not been marked as prime or composite yet
			if (sieve[current] == -1)
			{
				// Guess that it is prime
				sieve[current] = 1;
				
				// Mark all of its multiples as composite
				for (long mark = (long)(current) * current; mark < sieve.length; mark += current)
				{
					sieve[(int)mark] = 0;
					
					// If at some point during our execution, another thread has marked our
					// number as composite, we no longer need to continue checking its multiples
					if (sieve[current] == 0)
						continue outer;
				}
			}
		}
	}

}
