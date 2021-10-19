import java.io.*;
import java.net.*;

public class Server extends Thread{
	ServerSocket server;
	
	public static void main(String argv[]) throws Exception{
		new Server();
	}
	
	public Server() throws Exception{
		server = new ServerSocket(5000);
		System.out.println("Server listening on port 5000....");
		this.start();
	}
	
	public void run() {
		while(true) {
			try {
				System.out.println("Waiting for connections...");
				Socket client = server.accept();
				
				System.out.println("Accepted a connection from: " + client.getInetAddress().getHostAddress() + " " + client.getPort());
				
				BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
				PrintWriter pw = new PrintWriter(client.getOutputStream(), true);
				
				String country = br.readLine();
				System.out.println("Checking country...");
				
				BufferedReader brFile = new BufferedReader(new FileReader("countryContinent.csv"));
				
				String line = "";
				Boolean checkCountry = false;
				
				while((line = brFile.readLine()) != null) {
			         String[] values = line.split(",");
			         
			         if(values[0].equals(country)) {
			             pw.println("The continent is: " + values[5]);
			             pw.println("The sub-region is: " + values[6]);
			             pw.println("The country code is: " + values[2]);
			             checkCountry = true;
			             break;
			         }
			     }
				
				if(checkCountry == false) {
			    	 pw.println("There is no country: " + country + ". Please try again! (Ctrl+C)");
			     }
				
				brFile.close();
				
			} catch(Exception e) {}
		}
	}
}