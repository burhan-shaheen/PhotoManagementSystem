package photomanagementapplication;

class Node<T> {
	public T data;
	public Node<T> next;
	public Node (T val) {
		data = val;
		next = null;
	}
}
public class LinkedList<T> {
	private Node<T> head;
	private Node<T> current;
        private int size;
	public LinkedList () {
		head = current = null;
                size = 0;
	}
        public int getSize(){
            return size;
        }
        public Node<T> getHead(){
            return head;
        }
        public void setCurrent(Node<T> cur) {
            current = cur;
        }
        public Node<T> getCurrent(){
            return current;
        }
	public boolean empty () {
		return head == null;
	}
	public boolean last () {
		return current.next == null;
	}
	public boolean full () {
		return false;
	}
	public void findFirst () {
		current = head;
	}
	public void findNext () {
		current = current.next;
	}
	public T retrieve () {
		return current.data;
	}
	public void update (T val) {
		current.data = val;
	}
	public void insert (T val) {
                current = head;
		Node<T> tmp;
		if (empty()) {
			current = head = new Node<T> (val);
		}
		else {
			tmp = current.next;
			current.next = new Node<T> (val);
			current = current.next;
			current.next = tmp;
		}
                size++;
	}
	public void remove () {
		if (current == head) {
			head = head.next;
		}
		else {
			Node<T> tmp = head;
			while (tmp.next != current)
				tmp = tmp.next;
			tmp.next = current.next;
		}
		if (current.next == null)
			current = head;
		else
			current = current.next;
                
                size--;
	}
    void deleteNode(T key) { 
        // Store head node 
        Node temp = head, prev = null; 
  
        // If head node itself holds the key to be deleted 
        if (temp != null && temp.data == key) 
        { 
            head = temp.next;
            size--;// Changed head 
            return; 
        } 
  
        // Search for the key to be deleted, keep track of the 
        // previous node as we need to change temp.next 
        while (temp != null && temp.data != key) 
        { 
            prev = temp; 
            temp = temp.next; 
        }     
  
        // If key was not present in linked list 
        if (temp == null) return; 
  
        // Unlink the node from linked list 
        size--;
        prev.next = temp.next; 
    }
    //Checks whether the value x is present in linked list 
    public boolean search(T x) 
    { 
        Node current = head;    //Initialize current 
        while (current != null) 
        { 
            if (current.data == x) 
                return true;    //data found 
            current = current.next; 
        } 
        return false;    //data not found 
    } 
}
