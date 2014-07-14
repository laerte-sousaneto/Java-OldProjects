import java.io.*;
import java.net.*;

public class Server {
	private ServerSocket serverSocket;
	private Socket connectionSocket = null;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	
	private int port;
	private int backlog;
	private boolean killConnection;
	
	private Grid grid;
	
	Server(){
		this.port = 444;
		this.backlog = 1;
		this.killConnection = false;
	}
	Server(Grid grid){
		this.port = 444;
		this.backlog = 1;
		this.killConnection = false;
		this.grid = grid;
	}
	Server(int port, int backlog){
		this.port = port;
		this.backlog = backlog;
	}
	public void run() {
		communicate("I got you message");
	}
	public void communicate(String msg) 
	{
		try {
			String message = null;
			serverSocket = new ServerSocket(port,backlog);
			System.out.println("Waiting for Connection");
			do
			{
				connectionSocket = null;
				connectionSocket = serverSocket.accept();
				System.out.println("Connection received from " + connectionSocket.getInetAddress().getHostName());
				output = new ObjectOutputStream(connectionSocket.getOutputStream());
				output.flush();
				input = new ObjectInputStream(connectionSocket.getInputStream());
				message = (String)input.readObject();
				if(message.equals("turn off"))
				{
					String password;
					respondToTurnOff("Input password: ");
					connectionSocket = null;
					connectionSocket = serverSocket.accept();
					output = new ObjectOutputStream(connectionSocket.getOutputStream());
					output.flush();
					input = new ObjectInputStream(connectionSocket.getInputStream());
					password = (String)input.readObject();
					if(password.equals("1234"))
					{
						killConnection = true;
						respond("Logging out...");
						connectionSocket.close();
					}
					
				}
				if(message.equals("move right"))
				{
					grid.moveR();
					System.out.println(grid.getX());
				}
				if(!killConnection)
				{
					System.out.println("Message Recieved: "+message);
					System.out.println("Message Sent: "+respond(msg));
				}
			}while(!killConnection);
		}
		catch(ClassNotFoundException ex){
			ex.printStackTrace();
		}
		catch(IOException ex){
			ex.printStackTrace();
		}
		finally{
			try{
				connectionSocket.close();
				output.close();
				input.close();
			}
			catch(IOException ex){
				ex.printStackTrace();
			}
			finally{
				System.out.println("Connection closed.");
			}
		}
	}
	public void turnoff() {
		boolean correct = false;
		String password = null;
		do
		{
			communicate("Enter password: (type q to quit)");
			if(password.equals("1234"))
			{
				killConnection = true;
				correct = true;
				System.out.println("Turning Off...");
			}
			else
			{
				System.out.println("Try again...");
			}
		}while(!correct || password.equals("q"));
	}
	public void respondToTurnOff(String msg){
		try {
			output.writeObject(msg);
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
		finally{
			try{
				output.flush();
			}
			catch(IOException ex){
				ex.printStackTrace();
			}		
		}
	}
	public String respond(String msg){
		try {
			output.writeObject(msg);
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
		finally{
			try{
				output.flush();
			}
			catch(IOException ex){
				ex.printStackTrace();
			}		
			return msg;	
		}
	}
}