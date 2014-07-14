import javax.swing.*;
import java.awt.*;

class GameStatus extends JPanel
{
	private Player player;
	private StatusItem name,life,score,kills;
	private int x,y;
	private final int WIDTH,HEIGHT;
	private final int ITEM_WIDTH,ITEM_HEIGHT,ITEM_NAME_EXTRASPACE,ITEM_NUMBER;
	private int space_Items;
	
	GameStatus(Player p,int xP,int yP)
	{
		player = p;
		x = xP;
		y = yP;
		
		ITEM_WIDTH = 100;
		ITEM_HEIGHT = 20;
		ITEM_NAME_EXTRASPACE = 30;
		ITEM_NUMBER = 4;
		space_Items = 20;
		
		WIDTH = (ITEM_NAME_EXTRASPACE)+(ITEM_WIDTH+space_Items)*ITEM_NUMBER;
		HEIGHT = ITEM_HEIGHT+2;
		
		
		name = new StatusItem("Name",player.getName(),ITEM_WIDTH+ITEM_NAME_EXTRASPACE,ITEM_HEIGHT,10,1);
		life = new StatusItem("Life",String.valueOf(player.getShip().getLife()),ITEM_WIDTH+ITEM_NAME_EXTRASPACE,ITEM_HEIGHT,110+ITEM_NAME_EXTRASPACE,1);
		score = new StatusItem("Score",String.valueOf(player.getScore()),ITEM_WIDTH,ITEM_HEIGHT,(220+ITEM_NAME_EXTRASPACE),1);
		kills = new StatusItem("Kills",String.valueOf(player.getKills()),ITEM_WIDTH,ITEM_HEIGHT,(330+ITEM_NAME_EXTRASPACE),1);
		
		
		add(name);
		add(life);
		add(score);
		add(kills);
		setLayout(null);
		setOpaque(false);
		setSize(WIDTH, HEIGHT);
		setLocation(x,y);
		setVisible(true);
	}
	
	public Player getPlayer()
	{
		return player;
	}
	public void refreshName()
	{
		name.setData(player.getName());
	}
	public void refreshLife()
	{
		life.setData(String.valueOf(player.getShip().getLife()));
	}
	public void refreshScore()
	{
		score.setData(String.valueOf(player.getScore()));
	}
	public void refreshKills()
	{
		kills.setData(String.valueOf(player.getKills()));
	}
	public void refreshAll()
	{
		name.setData(player.getName());
		life.setData(String.valueOf(player.getShip().getLife()));
		String.valueOf(player.getScore());
		String.valueOf(player.getKills());
	}
}