public class Stopwatch
{
    private long startTime;
    private long stopTime;

    public static final double NANOS_PER_SEC = 1000000000.0;

	public void start(){
		startTime = System.nanoTime();
	}

	public void stop()
	{	stopTime = System.nanoTime();	}

	public double time()
	{	return (stopTime - startTime) / NANOS_PER_SEC;	}

	public String toString(){
	    return "elapsed time: " + time() + " seconds.";
	}

	public long timeInNanoseconds()
	{	return (stopTime - startTime);	}
}

