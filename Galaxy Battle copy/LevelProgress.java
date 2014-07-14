class LevelProgress
{
	private ShipFrame[] aliens;
	private int counter;
	
	LevelProgress(ShipFrame[]a)
	{
		aliens = a;
		counter = 0;
	}
	public void increaseCounter()
	{
		counter++;
	}	
	public void resetCounter()
	{
		counter = 0;
	}
	public int getCounter()
	{
		return counter;
	}

}