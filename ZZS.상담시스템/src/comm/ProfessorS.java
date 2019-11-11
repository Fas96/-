package comm;

import helperMethods.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ProfessorS {

	//chando was here
	
	private static ServerSocket servSocket;
	private static final int PORT=1234;
	
	public static void main(String args[]) {
	
		System.out.println("Opening port...\n");

		try {
			servSocket= new ServerSocket(PORT);
		} catch (IOException e) {
		
			System.out.println("Unable to attach to port!");
			System.exit(0);
		}
		 
			handleClient(); 

	}
	
	private static void handleClient() {
		
		Socket link= null;
		
		try {
			link=servSocket.accept();
			
			Scanner input= new Scanner(link.getInputStream());
			PrintWriter output= new PrintWriter(link.getOutputStream(),true);
			
			int numberMesages=0;
			String messageString=input.nextLine();
			
			while (!messageString.equalsIgnoreCase("***CLOSE***")) {
				System.out.println("Message received.: "+input.nextLine());

				messageString=input.nextLine();
				System.out.println("Client Message: "+messageString);
				//numberMesages++;
				System.out.println("Server>> ");
				String msg=(new Scanner(System.in)).nextLine();
				
				output.println(msg);
				
				
			}
		//	output.println(numberMesages+" messages received.");
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				System.out.println("Closing connection...*");
				link.close();
			} catch (Exception e2) {
				System.out.println("Unable to disconnect");
				System.exit(12);
			}
		}
		
	}

}
