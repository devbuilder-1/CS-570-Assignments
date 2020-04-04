//Name : Prithiv Dev Devendran
//CWID : 10453922

package Homework_3;
//please load the IDLList class.

public class IDLListTest {


	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
	     IDLList<String> f = new IDLList<String>(); //constructor
	     //add values to the list
	     f.add(0,"b"); 
	     f.add("a");
	     f.append("c");
	     f.append("d");
	     f.append("e");
	     f.append("f");
	     f.append("g");
	     f.append("h");
	     f.append("i");
	     f.append("j");
	     //print all the values
	     System.out.println(f.toString());
	     //print head and tail
	     System.out.println("Head:"+f.getHead());
	     System.out.println("Last:"+f.getLast());
	     System.out.println("Size:"+f.size());
	     //get the value from the index
	     System.out.println("Value at index 0:"+f.get(0));
	     System.out.println("Value at index 1:"+f.get(1));
	     System.out.println("Value at index 2:"+f.get(2));
	     System.out.println("Remove and print at index 2:");
	     f.removeAt(2);
	     //get the value from the deleted index
	     f.get(2);
	     //remove specific value, returns true and deletes it if it exists.
	     System.out.println("Remove e:"+f.remove("e"));
	     System.out.println("Remove z:"+f.remove("z"));
	     //removes the tail.
	     f.removeLast();
	     //remove the head
	     f.remove();
	     //print the list
	     System.out.println(f.toString());

	     
	}

}
