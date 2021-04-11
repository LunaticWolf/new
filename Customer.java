import java.io.Serializable;



public class Customer implements Serializable
{
    private String clientID;
    private int pinNumber;
    private Boolean status=false;
    private int numberOfTravels;
    private double totalCost;
    
    public Customer()
    {
    }

    public Customer(String clientID, int pinNumber) {
        this.clientID = clientID;
        this.pinNumber = pinNumber;
    }

    public Customer(String clientID, int pinNumber, Boolean status, int numberOfTravels) {
        this.clientID = clientID;
        this.pinNumber = pinNumber;
        this.status = status;
        this.numberOfTravels = numberOfTravels;
    }

   
    
    
    

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public int getPinNumber() {
        return pinNumber;
    }

    public void setPinNumber(int pinNumber) {
        this.pinNumber = pinNumber;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public int getNumberOfTravels() {
        return numberOfTravels;
    }

    public void setNumberOfTravels(int numberOfTravels) {
        this.numberOfTravels = numberOfTravels;
    }

    public double getTotalCost() {
        return totalCost;
    }
    
    public double calculateCost(){
        if(numberOfTravels>5){
            int extraTravel = numberOfTravels - 5;
            totalCost += extraTravel * 3;
            return totalCost;
        }
        return totalCost;
    }

    

//    @Override
//    public String toString() {
//        return "Customer{" + "clientID=" + clientID + ", pinNumber=" + pinNumber + ", status=" + status + ", numberOfTravels=" + numberOfTravels + ", totalCost=" + totalCost + '}';
//    }

    @Override
    public String toString() {
        return   "clientID=" + clientID + ", numberOfTravels=" + numberOfTravels + ", totalCost=" + totalCost + '}';
    }
     
    
    
    
}
    
    
    

