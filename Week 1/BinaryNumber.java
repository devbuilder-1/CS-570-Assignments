// Prithiv Dev Devendran
// CWID : 10453922
// Email ID : pdevendr@stevens.edu
import java.util.Arrays;

public class BinaryNumber {
	
	private static int[] a;
	//initiating variables
	private  int data[];
	private boolean overflow;
	 
	
	//a constructor for setting the length
	 public BinaryNumber(int length) {
		 
		 if(length<0)
		 {System.out.println("Enter a positive value");}
		 else {
		 //setting the array size according to the length
		 data = new int[length];
		 //making sure the array is only of zeros
		 System.out.print("New binary number:");
		 for (int i = 0; i <length; i++) {
			 data[i]=0;
			 System.out.print(data[i]);}
		 }System.out.print("\n");}
	 
	 
	//a constructor for converting the string to integer
	 public BinaryNumber(String str) {
		 
		 data = new int[str.length()];
		 boolean noAlpha = false;
		 
		 //checks if the user enetered a binary value
		 for (int i = 0; i < str.length(); i++) {
			 if(Character.getNumericValue(str.charAt(i))==0 || Character.getNumericValue(str.charAt(i))==1) {
			 noAlpha=true; 
			 }
			 
		//prints an error if so
			 else {
				 System.out.print("Please enter a binary number.\n");
				 noAlpha=false;
				 break;
			 }
		 }
		 
		 //converts the string values into the data array
		 //System.out.print("Entered a Binary number:");
		 if(noAlpha==true) {
			 System.out.print("Entered a Binary number:");
			 for (int i = 0; i < str.length(); i++) {
				 data[i]=Character.getNumericValue(str.charAt(i));
				 System.out.print(data[i]);
				 noAlpha=false;
			 }System.out.print("\n");
		 }
		 }
	 
	 //returns the length of the binary number
	 public int getLength() {
		 
		//System.out.print("\nLength:");
		return data.length;
		 
	 }
	 
	 //returns the digit at the specific index
	 public int getDigit(int index) {
		 if (index < data.length && index >= 0) {
	            return data[index];
	        } else {
	            System.out.println("Out of bounds.");
	            return 0;
	        }
	 }
	 
	 
	 //convert binary to decimal
	 public int toDecimal() {
		  
		   System.out.print("Decimal Value:");
		    int decimal = 0;
		    int power = 0;
		    for(int i = 0 ; i < data.length ; i++){
		            int tmp = data[i]%10;
		            decimal += tmp*Math.pow(2, power);
		            power++;
		    }return decimal;
		    }
	 
	 //shift the digits
	 public void shiftR(int amount) {
		 if(amount!=0) {
			 
			 int tempArray[];
			 tempArray = new int[amount+data.length];
			 
			 for(int i=0;i<amount;i++) {
				 tempArray[i]=0;
				 }
			 
			 for(int i=0;i<tempArray.length-amount;i++) {
					 
					 tempArray[amount+i]=data[i];
				 
			 }
			 
			 
			 System.out.print("After shifting "+amount+ " digits right:");
			 data = new int[amount+data.length];
			 for(int i=0;i<tempArray.length;i++) {
				 data[i]=tempArray[i];
				 System.out.print(data[i]);
				 }	 
			 
			 
		 }else {
			 System.out.println("Enter a valid number to shift.");
		 }
	 }
		 
    
    public void add(BinaryNumber aBinaryNumber)
    {
        int carryOver = 0;
        int id = data.length - 1;
        int sumOfDigit = 0;
        int decimal = 0;

        // check if the length of both arrays are equal or not
        if (data.length != aBinaryNumber.getLength()) {
            System.out.println("Enter a number of same lenght");
        } else {
        	
            while (id >= 0) {
                sumOfDigit = data[id] + aBinaryNumber.getDigit(id) + carryOver;
                data[id] = sumOfDigit % 2;
                carryOver = sumOfDigit / 2;
                id--;
                }

            // calculate the result of addition
            for (int i = 0; i< data.length; i++) {
                decimal += data[i] * Math.pow(2, i);
            }

            if (carryOver == 1) {
                overflow = true;
            } else {
                clearOverflow();
            }

            }
        System.out.println("After addition:" +
                Arrays.toString(data));
        
        System.out.println("The addition result of new array is: " + decimal);

    }

    public String toString() {

        // append each element in data[] array to string in sequence
        String st = "";
        for (int i = 0; i < data.length; i++) {
            st += data[i];
        }

        // check if overflow is true, then return "overflow", otherwise return string
        if (overflow == true) {
            return "Overflow";
        } else {
            return st;
        }
    }
    

	 
	 //resets the overflow flag
		   public void clearOverflow() {
		        overflow = false;
		        System.out.println("Overflow reset.");
		    }
	 
	 //main method
	public static void main(String[] args) {
	
		 BinaryNumber n = new BinaryNumber(7);
		 BinaryNumber n1 = new BinaryNumber("000100");
		 BinaryNumber n2 = new BinaryNumber("001001");
		 System.out.println("Lenght:"+n1.getLength());
		 System.out.println("At Index:"+n1.getDigit(6));
		 System.out.println(n1.toDecimal());
		 System.out.println(n2.toDecimal());
		 System.out.println(n1.toString());
		 System.out.println(n2.toString());
		 n2.add(n1);
		 n2.shiftR(5);
		 
		 }
}