

public class PrimeDriver {
	private static int N = 6;                 //# of Threads
	//-----Vars used to calculate times
	private static double timeAcc;
	private static double runninTime;
	private static double speedup;
	private static int[] numOfPrimes;
	private static double[] timer;
	private static int[] biteCount;
	private static double start;
	private static double end;
	private static double elapseTime;
	//----------------------------------------
	
	private static int range = 10000000;
	private static int bite = 25000;
	private static int totalPrimes;

	public static void main(String[] args) throws InterruptedException {

		PimeExec[] worker = new PimeExec[N];

		PrimeWork pw = new PrimeWork(3, range, bite);
		numOfPrimes = new int[N];
		timer = new double[N];
		biteCount = new int[N];
		start = System.nanoTime();
		for (int i = 0; i < N; i++) {
			worker[i] = new PimeExec(pw, "THREAD-" + (i + 1));

		}
		for (int i = 0; i < N; i++) {
			worker[i].start();

		}
		for (int i = 0; i < N; i++) {
			worker[i].join();
			numOfPrimes[i] = worker[i].count;
			timer[i] = worker[i].accumTime;
			biteCount[i] = worker[i].bite;
			totalPrimes = totalPrimes + numOfPrimes[i];
			timeAcc = timeAcc + timer[i];
		}

		end = System.nanoTime();
		elapseTime = end - start;
		System.out.println();
		System.out.println("The Program Results");
		System.out.println("-------------------");
		//--------Compare to Single Core and Print Results--------------------------
		for (int i = 0; i < N; i++) {
			String Results = String
					.format("THREAD-%2d: PRIMECOUNT: % 7d TIMER: % .6f sec  TOTAL BITES: %3d",
							(i + 1), numOfPrimes[i], timer[i], biteCount[i]);
			System.out.println(Results);
         
		}
		printResults();
	}
		
		//---Summary------
		public static void printResults() {
		runninTime = elapseTime / (1000000000);
		System.out.println();
		System.out.printf("RANGE: %, d  BITE SIZE = %, d \n", range, bite);
		System.out.printf("TOTAL PRIMES: %, d \n", totalPrimes);
		System.out.printf("TOTAL THREAD TIME: %.6f sec\n", timeAcc);
		System.out.printf("RUNNING TIME: %.6f sec\n", runninTime);
		/* Single-Core Prime calculation */
		PrimeSingle ps = new PrimeSingle(3, range);
		ps.run();
		// Calculate speed based on single core
		speedup = ps.elapsedTime - runninTime;
		System.out.printf("SPEEDUP: %.2f", speedup);
		}

	

}
