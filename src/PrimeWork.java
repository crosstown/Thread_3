public class PrimeWork {

	private int lo;
	private int hi;
	public int bite;
	public int current;
	public boolean complete;

	public PrimeWork(int max, int bite) {
		lo = 3;
		hi = max;
		current = lo;
		this.bite = bite;
	}

	public PrimeWork(int min, int max, int bite) {
		lo = min;
		hi = max;
		current = lo;
		this.bite = bite;
	}

	public synchronized Range getWork() {

		Range range = null;
		if (!complete) {

			if (current + bite > hi) {

				range = new Range(current, hi);
				complete = true;
				System.out.println();

			} else {

				range = new Range(current, current + bite - 1);
				current = current + bite;

			}
		}

		return range;
	}


}
