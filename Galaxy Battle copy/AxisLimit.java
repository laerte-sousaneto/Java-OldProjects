class AxisLimit
{
	private int start;
	private int end;
	
	AxisLimit(int s, int e)
	{
		start = s;
		end = e;
	}
	
	public void setStart(int s)
	{
		start = s;
	}
	public void setEnd(int e)
	{
		end = e;
	}
	public int getStart()
	{
		return start;
	}
	public int getEnd()
	{
		return end;
	}
}