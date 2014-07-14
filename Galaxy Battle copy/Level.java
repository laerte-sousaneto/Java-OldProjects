import java.applet.*;
class Level 
{
	private int level;
	private int speed;
	private int scoreBase;
	private LevelProgress progress;
	
	private ShipFrame[] aliens;
	
	private AudioClip[]levelSongs;
	
	Level(ShipFrame[] a, AudioClip[] audio)
	{
		level = 0;
		speed = 1;
		scoreBase = 0;
		progress = new LevelProgress(a);
		
		aliens = a;
		levelSongs = audio;
	}
	public int getLevel()
	{
		return level;
	}
	public int getSpeed()
	{
		return speed;
	}
	public void setLevel(int l)
	{
		level = l;
	}

	public int getDifficulty()
	{
		return speed;
	}
	public int getScoreBase()
	{
		return scoreBase;
	}
	public LevelProgress getProgress()
	{
		return progress;
	}
	public void advance()
	{
		level++;
		if(speed<50)
		{speed++;}
		progress.resetCounter();
	}
	public void increaseScoreBase()
	{
		int points = 10;
		scoreBase += points;
	}
}