
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.ArrayList;


public class UDPServer {
    
    static String fileName = "src/member.txt";
    static ArrayList<Customer> customerList = null;
    static int serverPort = 8897;

    
    public static void main (String args[]) throws FileNotFoundException, IOException
    {
        DatagramSocket aSocket = null;
        String responseMessage = "";
        customerList = customerList(fileName);
        
        try
        {
                aSocket = new DatagramSocket(serverPort);     
                byte[] requestReceived = new byte[1000];         
                byte[]  requestResponse=new byte[1000];
        
                while(true)
                {
                    DatagramPacket request = new DatagramPacket(requestReceived, requestReceived.length);
                    aSocket.receive(request);

                    String receivedData = (new String(request.getData()).trim());
                    
//                    if(receivedData!=null || receivedData!="")
//                    {
     
//                    	customerList = customerList(fileName);
//                    	if(customerList!=null)
                    	responseMessage = validateMemberData(receivedData,customerList);
//                    }
                    
                    requestResponse=responseMessage.getBytes();
                    
                    DatagramPacket reply = new DatagramPacket(requestResponse, requestResponse.length,request.getAddress(), request.getPort());

                    aSocket.send(reply);
    			
                }
        } 
        catch(IOException e){
            e.printStackTrace();
        }
        finally
        {
        	if(aSocket!=null)
        	aSocket.close();
        }
        
        
    }
    
    private static String validateMemberData(String receivedData,ArrayList<Customer> customerList) 
    {

    	String[] data = receivedData.split(":");
    	String message = "";
    	int c = 0;
    	
    	for(int i=0;i<customerList.size();i++)
    	{
    		if(customerList.get(i).getClientID().equalsIgnoreCase(data[0].trim()))
    		{
				System.out.println("ID Number - " + customerList.get(i).getClientID());
    			c++;
    			System.out.println("Count1 - " + c);
    			if(customerList.get(i).getPinNumber()==(Integer.parseInt(data[1].trim())))
    			{
    				System.out.println("Pin Number - " + customerList.get(i).getPinNumber());
    				c++;
    				System.out.println("Count2 - " + c + ":" + data[2]);
    				
    				if((data[2].trim()).equalsIgnoreCase("OUT"))
    				{
						if(customerList.get(i).getStatus()==false)
							message = "Already Signed-Out" + customerList.get(i).getStatus()+ customerList.get(i).getNumberOfTravels();
						if(customerList.get(i).getStatus()==true)
						{
							customerList.get(i).setStatus(false);
							message = "Good bye" + customerList.get(i).getStatus()+ customerList.get(i).getNumberOfTravels();
						}
					}
    				
    				if((data[2].trim()).equalsIgnoreCase("IN"))
    				{
    					if(customerList.get(i).getStatus()==true)
    						{
    							customerList.get(i).setNumberOfTravels(customerList.get(i).getNumberOfTravels()+1);
    							message = "Already Signed-in" + customerList.get(i).getStatus() + customerList.get(i).getNumberOfTravels();
    						}
    					if(customerList.get(i).getStatus()==false)
    					{
    						customerList.get(i).setStatus(true);
    						customerList.get(i).setNumberOfTravels(customerList.get(i).getNumberOfTravels()+1);
    						message = "Welcome" + customerList.get(i).getStatus()+ customerList.get(i).getNumberOfTravels();
    					}
				}
				
			}
		}
	}
	if(c==1)
		message = "Invalid password";
	if(c==0)
		message = "Invalid Client";
			
	return message;
	
}
    	
    	
	public static ArrayList<Customer> customerList(String fileName) throws FileNotFoundException, IOException
    {

			FileReader fr = new FileReader(fileName);
			BufferedReader br = new BufferedReader(fr); 
			
			String line = null;
			ArrayList<String> memberdata = new ArrayList<>();
			while((line=br.readLine())!=null)
			memberdata.add(line);
			

			ArrayList<Customer> customerData = new ArrayList<Customer>();
			for(int i=0;i<memberdata.size();i++)
			{           
				String values[] = memberdata.get(i).split(" ");
				customerData.add(new Customer(values[0], Integer.parseInt(values[1])));
			}
			if(br!=null)
				br.close();
						
			if(customerData==null)
            return null;
			else
				return customerData;
    }
}