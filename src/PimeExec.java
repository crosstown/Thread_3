public class PimeExec extends Thread {
	double start;
	double stop;
	double timeElapsed;
	public double accumTime;
	public int bite = 0;

	private int lo;
	private int hi;
	private int localCount;
	private PrimeWork prime;
	private String mess;
	Range range;
	int counter = 0;
	public int count;

	public PimeExec(PrimeWork pw1, String mess) {
		prime = pw1;

		this.mess = mess;
	}

	public void run() {

		while (!prime.complete) {
			System.out.println(mess + " getting a bite");
			range = prime.getWork();
			hi = range.hi;
			lo = range.lo;
			start = System.nanoTime();
			count = countPrimes(lo, hi); // Counts the primes.
			stop = System.nanoTime();
			timeElapsed = (stop - start) / (1000000000);
			accumTime = accumTime + timeElapsed;

			bite++;
		}

	}

	public int countPrimes(int lo, int hi) {

		for (int i = lo; i <= hi; i++) {
			if (isPrime(i)) {
				counter++;
			}

		}
		return counter;
	}

	public static boolean isPrime(int number) {
		int sqrt = (int) Math.sqrt(number) + 1;
		for (int i = 2; i < sqrt; i++) {
			if (number % i == 0) {
				// number is perfectly divisible - no prime
				return false;
			}
		}
		return true;

	}
}
