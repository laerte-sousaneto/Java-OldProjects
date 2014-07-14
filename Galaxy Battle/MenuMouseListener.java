import java.awt.event.*;
class MenuMouseListener implements MouseListener
{
  		private StartMenuPanel menuPanel;
		private StartMenuItem startButton;
		private StartMenuItem scoresButton;
		private Galaxy main;
		private boolean scoreClicked;
		
		MenuMouseListener(Galaxy g,StartMenuPanel mPanel)
		{
			menuPanel = mPanel;
			startButton = menuPanel.getStartButton();
			scoresButton = menuPanel.getScoresButton();
			main = g;
			scoreClicked = false;
		}	
  		public void mousePressed(MouseEvent e) 
		{	 			 			
			int mx = e.getX();
			int my = e.getY();
			
			if(mx > startButton.getX() && mx < startButton.getX()+startButton.getWidth() &&
					my > startButton.getY() && my < startButton.getY()+startButton.getHeight() ) 
			{		
				if(scoreClicked)
				{
					main.closeScores();
				}
				menuPanel.removeAllItems();
				main.statusBar();	
				main.runGame(-1);						
			}
			else if(mx > scoresButton.getX() && mx < scoresButton.getX()+scoresButton.getWidth() &&
					my > scoresButton.getY() && my < scoresButton.getY()+scoresButton.getHeight() )
			{
				if(!scoreClicked)
				{
					main.openScores(scoreClicked);
					scoreClicked = true;
				}
				else
				{
					main.openScores(scoreClicked);
					scoreClicked = false;
				}
			}
		}
		public void mouseClicked(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {}		 
		public void mouseExited(MouseEvent e) {}	
		public void mouseReleased(MouseEvent e) {}		
	  
  }