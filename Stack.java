package GrahamScan;
import java.util.*;

public class Stack<Item> {

	private Node head; 				// top of stack (most recently added node)
	private int N; 					// number of items
	
	private class Node{			    // nested class to define nodes
		Item item;
		Node next;
	}
	
	public boolean isEmpty() { 
		return head == null; 
	}
	
	public int size() { 
		return N; 
	}
	
	public void push(Item item)	{ 			// Add item to top of stack.
		Node temp = head;
		head = new Node();
		head.item = item;
		head.next = temp;
		N++;
	}
	public Item pop(){ 						// Remove item from top of stack.
		if(isEmpty()) 
			return null;
		
		Item item = head.item;
		head = head.next;
		N--;
		return item;
	}
	
	public void disp(){
		Node temp = head;                   //here did not assign memory as only reference was required
		while(temp!=null) {
			System.out.println(temp.item);
			temp = temp.next;
		}
	}

	public Item peek() {
		
		return head.item;
	}
	
}
