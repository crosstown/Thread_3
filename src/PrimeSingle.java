public class PrimeSingle {
	int localCount = 0;
	int start;
	int end;

	long time1;
	public double elapsedTime;

	public PrimeSingle(int start, int end) {
		this.start = start;
		this.end = end;
	}

	public void run() {
		// # of primes in current thread

		time1 = System.nanoTime();

		for (int number = start; number <= end; number++) {
			// check if numbers are prime
			if (isPrime(number)) {

				localCount++; // count the local primes

			}
		}
		elapsedTime = (System.nanoTime() - time1) / (1000000000);

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