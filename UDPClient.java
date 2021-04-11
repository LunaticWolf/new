
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;


public class UDPClient
{
    
    static int serverPort = 8897;
    static String serverName = "localhost";
    static String message="";
    
    public static void main(String args[])
    {
        
        DatagramSocket aSocket = null;
        Scanner scanner = new Scanner(System.in);

        try 
        {
           aSocket = new DatagramSocket();     
            InetAddress aHost = InetAddress.getByName(serverName);
			
            byte []bufferRequest = new byte[1000];
            byte []bufferResponse = new byte[1000];
            
           
            message = menu(scanner);
            
            while(message!=null)
            {
            	
	            bufferRequest=message.getBytes();
            
	            DatagramPacket request =new DatagramPacket(bufferRequest,bufferRequest.length, aHost, serverPort);
	            aSocket.send(request);
	            
	            DatagramPacket reply = new DatagramPacket(bufferResponse, bufferResponse.length);
	            aSocket.receive(reply);
	            
	            String serverResponse = new String(reply.getData()).trim();
	            System.out.println("Server Response : " + serverResponse);
	            message = menu(scanner);
            }
           
        } 
        catch (IOException e) 
        {
        	System.out.println("Client: IO Exception - " + e.getMessage());
        }
        finally
        {
        	if(aSocket!=null)
        	aSocket.close();
        }
        
        
        
    }



	static String menu(Scanner scanner)
	{
		System.out.println("**************TRAVEL KIOSK***************");
		System.out.println("                 1:IN                    ");
		System.out.println("                 2:OUT                   ");
		System.out.println("                 3:EXIT                  ");
		System.out.println("Enter:");
                
                String clientID = "";
                String pinNumber = "";
                
                String input = scanner.nextLine();
                switch(input)
			{
			case "1":               System.out.println("Enter client ID:");
                                    clientID=scanner.nextLine();
                                    System.out.println("Enter Pin ID:");
                                    pinNumber=scanner.nextLine();
                           
                                    message=clientID+":"+pinNumber+":"+"IN";
                                    break;
                                    
			case "2":               System.out.println("Enter client ID:");
                                    clientID=scanner.nextLine();
                                    System.out.println("Enter Pin ID:");
                                    pinNumber=scanner.nextLine();
                           
                                    message=clientID+":"+pinNumber+":"+"OUT";
                                    break;
                                    
			case "3":   			message = null;
                                    break;
			
			default: 				menu(scanner);
									 break;
                        } 
                return message;
                
	}
    
    
}
