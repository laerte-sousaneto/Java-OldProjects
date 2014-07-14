class Player
{
	private String name;
	private int kills;
	private int score;
	
	private ShipFrame ship;
	private Level level;
	
	Player(String n,ShipFrame s,Level l)
	{
		name = n;
		kills = 0;
		score = 0;
		
		ship = s;
		level = l;
	}
	public String getName()
	{
		return name;
	}
	public int getKills()
	{
		return kills;
	}
	public int getScore()
	{
		return score;
	}
	public Level getLeve()
	{
		return level;
	}
	public ShipFrame getShip()
	{
		return ship;
	}
	public void increaseKills()
	{
		kills++;
	}
	public void addScore(int n)
	{
		score += n;
	}
}