
public class Timer {
	private long startTime;
	private long elapsedTime;
	private boolean isRunning;

	public Timer() {
		reset();
	}

	/**
	 * Starts timer, but does not reset the elapsed time accumulated.
	 * 
	 */
	public void start() {
		if (this.isRunning)
			return;
		this.isRunning = true;
		this.startTime = System.currentTimeMillis();
	}

	/**
	 * Stops timer.
	 */
	public void stop() {
		if (!this.isRunning)
			return;
		this.isRunning = false;
		long endTime = System.currentTimeMillis();
		this.elapsedTime = this.elapsedTime + (endTime - this.startTime);
	}

	/**
	 * @return the total elapsed time accumulated by this stop watch.
	 */
	public long getElapsedTime() {
		if (this.isRunning) {
			long endTime = System.currentTimeMillis();
			return this.elapsedTime + (endTime - this.startTime);
		}
		return this.elapsedTime;
	}

	/**
	 * Steps this stop watch and resets the accumulated time to zero.
	 */
	public void reset() {
		this.elapsedTime = 0;
		this.isRunning = false;
	}
}