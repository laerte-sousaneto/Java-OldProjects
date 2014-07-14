import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
	private Socket clientSocket;
	private ObjectOutputStream output;
 	private ObjectInputStream input;
	
	private String ip = null;
	private int port;
	
 	private String message;
	
	
	Client(){
		this.ip = "localhost";
		this.port = 444;
	}
	Client(String ip, int port){
		this.ip = ip;
		this.port = port;
	}
	public void run() {
		try { 		
			clientSocket = new Socket(this.ip,this.port);
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Connected to the server");
			output = new ObjectOutputStream(clientSocket.getOutputStream());
			input = new ObjectInputStream(clientSocket.getInputStream());
			String message = null;
			do
			{
				if(!clientSocket.isClosed())
				{			
					message = reader.readLine();	
					sendMessage(message);
					String message2 = (String)input.readObject();
					System.out.println("Message sent: "+message);
					System.out.println("Message received: "+message2);
				}
				else
				{
					System.out.println("socket is closed.");
				}
				if(!message.equals("logout"))
				{
					clientSocket = null;
					clientSocket = new Socket(this.ip,this.port);
					input.close();
					output.close();
					input = new ObjectInputStream(clientSocket.getInputStream());
					output = new ObjectOutputStream(clientSocket.getOutputStream());
				}
			}while(!(message.equals("logout")) && !clientSocket.isClosed());
			
		
		}
		catch(ClassNotFoundException ex){
			System.out.println("object not found.");
		}
		catch(ConnectException ex){
			System.out.println("Server is offline.");
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
		finally{
		
		}
	}
	private void sendMessage(String msg)
	{
		try{
			output.writeObject(msg);		
		}
		catch(IOException ioException){
			ioException.printStackTrace();
		}
		finally{
			try{
				output.flush();	
			}
			catch(IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	public static void main(String[]args){
		Client client = new Client();
		client.run();
	}
	
}