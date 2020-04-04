//Name : Prithiv Dev Devendran
//CWID : 10453922

package Homework_3;
import java.util.ArrayList;

public class IDLList<E> {
	
	
	
	//the Node<E> Class 
	@SuppressWarnings("hiding")
	public class  Node<E>{
		
	//objects
	E data; 
	Node<E> next; 
	Node<E> prev;
	
	//methods
	Node (E elem){
	data = elem;
	this.next=null;
	this.prev=null;
	}
	Node (E elem, Node<E> prev, Node<E> next){
	this.data=elem;
	this.prev=prev;
	this.next=next;
	}}

	
	
	//The IDLList objects
	private Node<E> head;
	private Node<E> tail;
	private int size;
	private ArrayList<Node<E>> indices;
	
	
	
	//All the IDLList Methods
	public IDLList () {
		this.head=null;
		this.tail=null;
		size=0;
		indices = new ArrayList<Node<E>>();
		
	}
	
	public boolean add (int index, E elem) {
		
		if (index < 0 || index > size) {
			throw new IllegalArgumentException("Out of Bounds:Give an index that exists.");}
		
		else { 
			
			if(index == 0) {
				add(elem);
				return true;
			  }
			else if(index == size) {
				append(elem);
				return true;
			}
			
			
			Node<E> NODE = new Node<E>(elem);
			Node<E> indexNODE = (Node<E>) (indices.get(index));
			
			NODE.next = indexNODE;
			NODE.prev = indexNODE.prev;
			indexNODE.prev.next = NODE;
			indexNODE.prev = NODE;
			
			indices.add(index, NODE);
			size++;
			return true;	
			}}
	
		
	
	

	public boolean add (E elem) {
		
         if(size==0) {
			Node<E> NODE = new Node<E>(elem);
			head = NODE;
			tail = NODE;
			indices.add(0, NODE);
			size++;return true;
			}
         
         else {
        	 
        	 Node<E> NODE = new Node<E>(elem); 
        	 NODE.next = head; 
        	 NODE.prev = null; 
        	  
        	  if (head != null) 
        	        head.prev = NODE; 
        	    head = NODE;
        	    
        	    indices.add(0, NODE);
        		size++;
        	    return true;
         }
         
	}
	
	
	
	
	
	public boolean append (E elem) {
		
		if(size==0) {add(elem);}
		
		else {
		Node<E> NODE = new Node<E>(elem);
		NODE.prev = tail;
		tail.next = NODE;
		tail = NODE;
		
		indices.add(size, NODE);
		size++;return true;
		}return true;
	}
	
	
	
	
	public E get (int index) {

		if(size==0) {throw new IllegalArgumentException("Empty List");}
		else if(index>size || index>size-1) {throw new IllegalArgumentException("Out of Bounds:Give an index that exists.");}
		else {
			
			Node<E> indexNODE = (Node<E>) (indices.get(index));
			//System.out.println(indexNODE.data);
			return  indexNODE.data;
		}
	
	}
	
	
	
	
	public E getHead () {
		if(head == null) {throw new IllegalArgumentException("Empty List");}
		return head.data;
	}
	
	
	
	
	public E getLast () {
		if(tail == null) {throw new IllegalArgumentException("Empty List");}
		return tail.data;
	}
	
	
	
	
	public int size() {
		return size;
	}
	
	
	
	
	public E remove () {
		if(head==null) {throw new IllegalArgumentException("Empty List");}
		 head = head.next;
		 indices.remove(0);
	     size--;
         return head.data;
	}
	
	
	
	
	public E removeLast () {
		if(head==null){throw new IllegalArgumentException("Empty List");}
		else if (head==tail) {head.data=null; tail.data=null;return tail.data;}
		else {
			 tail = tail.prev;  
             tail.next = null;  
             indices.remove(size-1);
             size--;
			return tail.data;
		}
		
	}
	
	
	
	
	public E removeAt (int index) {
		
		if (index < 0 || index > size) {
			throw new IllegalArgumentException("Out of Bounds:Give an index that exists.");}
		
		else if(index==0){remove();return tail.data;}
		
		else if(index==(size-1)) {removeLast();return tail.data;}
		
		else {
			
			Node<E> indexNODE = (Node<E>) (indices.get(index));
			indexNODE.next.prev = indexNODE.prev; 
			indexNODE.prev.next = indexNODE.next; 
			indices.remove(index);
	         size--;
			return tail.data;
			
		}
		
	}
	
	

	public boolean remove (E elem) {
		
		//String string = null;
		boolean flag = false;
		int ind=0;
		Node<E> NODE1 = new Node<E>(elem);
		for(int k=0; k<size; k++) {
			Node<E> indexNODE1 = (Node<E>) (indices.get(k));
			//string = (String) indexNODE.data;
			if(NODE1.data==indexNODE1.data) {flag=true;ind=k;}
			}
		

		if(head==null){throw new IllegalArgumentException("Empty List");}
		
	
		else if(flag==true) {
			
			Node<E> indexNODE = (Node<E>) (indices.get(ind));
			indexNODE.next.prev = indexNODE.prev; 
			indexNODE.prev.next = indexNODE.next; 
			indices.remove(ind);
	         size--;
			return true;
		}
		
		else {
			return false;
		}
	}

	
	
	

	public String toString() {
		
		if(head==null) {throw new IllegalArgumentException("Empty List");}
			
		String string = null;
		StringBuilder strbuild = new StringBuilder("The List is : ");
		

		for(int k=0; k<size; k++) {
			Node<E> indexNODE = (Node<E>) (indices.get(k));
			string = (String) indexNODE.data;
			strbuild.append(string+" ");
			
			
		}
		
		return strbuild.toString();
	}

	
	
	//The main method
	public static void main(String[] args) {
		// TODO Auto-generated method stub

     }
	}