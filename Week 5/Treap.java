//Prithiv Dev 
//10453922
//CS 570

import java.util.Random;
import java.util.Stack;
import java.util.*;

public class Treap<E extends Comparable<E>>{
	
	class Node<E>{
		public E data;
		public int priority;
		public Node<E> left;
		public Node<E> right;
		
		/**
		 * Initilaizses the data, checks if it is null. Refers to the super class and sets the values.
		 * @param data - the initial entry
		 * @param priority - priority of the data
		 */
		public Node(E data,int priority) {
			if (data == null) {
				throw new IllegalArgumentException("Data is null");
			} else {
				this.data = data;
				this.priority = priority;
				this.right = null;
				this.left = null;
			}
		}
		/**
		 * The rotateRight function - rotates the nodes with respect to the priorities set.
		 */
		Node<E> rotateRight(){
			Node<E> header = this.left;
			Node<E> left = header.right;
			header.right = this;
			this.left = left;
			return header;
		}
		/**
		 * The rotateLeft function - rotates the nodes with respect to the priorities set. 
		 */
		Node<E> rotateLeft(){
			Node<E> header = this.right;
			Node<E> right = header.left;
			header.left = this;
			this.right = right;
			return header;
		}
	}
	
	/**
	 * The root and the priorityGenerator, a random priority is created.
	 */
	private Node<E> root;
	private Random priorityGenerator;
	
	/**
	 * The treap method calls a helper method.
	 */
	public Treap() {
		root = null;
		priorityGenerator = new Random();
	}
	public Treap(long seed) {
		root = null;
		priorityGenerator = new Random(seed);
		
	}
	/**
	 * The add method calls a helper method.
	 */
	boolean add(E key) {
	
		 int randomInteger = priorityGenerator.nextInt();
	        return add(key, randomInteger);
	}
	/**
	 * The add helper that adds the node.
	 */
	boolean add(E key,int priority) {
		
		 if (root == null ) {
	            root = new Node<E>(key, priority);
	            return true;
		 }
		 
		 Node<E> currentRoot = root;
	        boolean insertFlag = false;
	        Stack<Node<E>> s = new Stack<Node<E>>();

	        while (insertFlag  == false) {
	            if (key.compareTo(currentRoot.data) == 0) {
	                break;
	            }

	            if (key.compareTo(currentRoot.data) < 0) {
	                s.push(currentRoot);
	                if (currentRoot.left == null) {
	                	currentRoot.left = new Node<E>(key, priority);
	                	currentRoot = currentRoot.left;
	                    insertFlag  = true;
	                } else {
	                	currentRoot = currentRoot.left;
	                }
	            } else {
	                s.push(currentRoot);
	                if (currentRoot.right == null) {
	                	currentRoot.right = new Node<E>(key,priority);
	                	currentRoot = currentRoot.right;
	                    insertFlag  = true;
	                } else {
	                	currentRoot = currentRoot.right;
	                }
	            }
	        }

	        reheap(currentRoot, s);
	        return insertFlag;
	}
	/**
	 * The reHeap method that balances and consolidates the tree.
	 */
	private void reheap(Node<E> currentNode, Stack<Node<E>> s) {
        if (s.empty()) {
            root = currentNode;
            return;
        } else if (currentNode.priority < s.peek().priority) { 
            return;
        } else {
            Node<E> poppedNode = s.pop();

            if (poppedNode.left != null && poppedNode.left.equals(currentNode)) {
            	poppedNode.rotateRight();
            } else {
            	poppedNode.rotateLeft();
            }

            if (!s.empty()) {
                if (s.peek().left != null && s.peek().left.equals(poppedNode)) {
                    s.peek().left = currentNode;
                } else {
                    s.peek().right = currentNode;
                }
            }

            reheap(currentNode, s);
        }
    }
	/**
	 * The delete helper method that checks for the key to be deleted 
	 */
	private Node<E> delete(E key, Node<E> n){
		if (n == null) {
			return n;
		} else {
			if (n.data.compareTo(key) < 0) {
				n.right = delete(key, n.right);
			} else {
				if (n.data.compareTo(key) > 0) {
					n.left = delete(key, n.left);
				} else {
					if (n.right == null) {
						n = n.left;
					} else if (n.left == null) {
						n = n.right;
					} else {
						if (n.right.priority < n.left.priority) {
							n = n.rotateRight();
							n.right = delete(key, n.right);
						} else {
							n = n.rotateLeft();
							n.left = delete(key, n.left);
						}
					}
				}
			}
		}
		return n;
	}
	/**
	 * The delete method that calls for the delete helper method.
	 */
	public boolean delete(E key) {
		if (root == null || find(key) == false) {
			return false;
		} else {
			root = delete(key, root);
			return true;
		}
	}
	/**
	 * The find method that uses the comaparable package to check iteratively for the key.
	 */
	
	private boolean find(Node<E> root, E key) {
		if (root == null) {	
			return false;
		} else {
			int comparInt = root.data.compareTo(key);
			if (comparInt == 0) {
				return true;
			} else {
				return find(root.right, key) || find(root.left, key);
			}
		}
	}
	
	/**
	 * The find method that call the find helper method.
	 */
	
	public boolean find(E key) {
		return find(root, key);
	}
	
	/**
	 * Gets the keys and iterates through to print.
	 */

	private String toString(Node<E> n, int dn) {
		StringBuilder stringBd = new StringBuilder();
		for (int k=0;k<dn;k++) {
			 stringBd.append("\t");
		}
	
		if (n==null) {
			 stringBd.append("null");
		} else {
			 stringBd.append("(key = " + n.data +", priority = " + n.priority + ")" );
			 stringBd.append("\n");
			 stringBd.append(toString(n.left, dn+1));
			 stringBd.append("\n");
			 stringBd.append(toString(n.right, dn+1));
		}
		return  stringBd.toString();
		
	}
	
	/**
	 * Calls the stringbuiler helper method
	 */

	public String toString() {
		return toString(root,0);
	}
	
	/**
	 * Test Cases.
	 */
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Treap<Integer> tester = new Treap<Integer>();
		tester.add(4,19); 
		tester.add(2,31);
		tester.add(6,70); 
		tester.add(1,84);
		tester.add(3,12); 
		tester.add(5,83);
		tester.add(7,26);
		System.out.println(tester.delete(4));
		System.out.println(tester.delete(2));
		System.out.println(tester.delete(6));
		System.out.println(tester.delete(10000));
		System.out.println(tester.toString());
		tester.add(35,71);
		System.out.println(tester.delete(3));
		System.out.println(tester.toString());

	}

}