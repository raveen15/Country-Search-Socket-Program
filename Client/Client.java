import java.io.*;
import java.net.*;
import java.util.*;

public class Client{
	public static void main(String argv[]) throws Exception{
		Socket socket = new Socket("localhost", 5000);
		BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter pw = new PrintWriter(socket.getOutputStream(), true); 
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the country you want to find the continent, sub-region, and country code: ");
		String country = scanner.nextLine();
		
		System.out.println();
		pw.println(country);
		pw.flush();
		
		String str = br.readLine();
		System.out.println(str);
		str = br.readLine();
		System.out.println(str);
		str = br.readLine();
		System.out.println(str);
	}
}