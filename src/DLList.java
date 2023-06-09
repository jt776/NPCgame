import java.util.Iterator;

/**
 * A class representing a doubly linked list.
 * Albert Meza
 * PLEASE DO NOT COPY THIS FILE TO OTHER STUDENTS OR TO WEBSITES LIKE CHEGG,
 * GITHUB, ETC WHERE OTHERS MAY VIEW IT!!! IT IS YOUR WORK AND YOU SHOULD BE
 * PROUD OF WHAT YOU HAVE ACCOMPLISHED! IN ADDITION, THIS FILE CONTAINS THE
 * COPYRIGHTED WORK OF MARTIN HOCK AND IS ONLY LICENSED FOR USE BY INDIVIDUAL
 * STUDENTS FOR NONPROFIT EDUCATIONAL PURPOSES.
 */
public class DLList<T> implements Iterable<T> {
	private static class Node<T> {
		// prev is reference to adjacent node closer to head (or null if this node is
		// the head)
		// next is reference to adjacent node closer to tail (or null if this node is
		// the tail)
		public Node<T> prev, next;
		// data is the information stored in the node of type T (T could be String,
		// Integer, etc.)
		public T data;

		public Node(Node<T> before, T data, Node<T> after) {
			this.prev = before;
			this.next = after;
			this.data = data;
		}
	}

	// head is beginning node (no prev), tail is end node (no next)
	// They can both reference the same node if the list is one element long
	// The can both reference null if the list is empty
	private Node<T> head, tail;
	private int size; //instance variable added to keep track of current list size

	/**
	 * Forward iterator class (conductor).
	 */
	private static class Conductor<T> implements Iterator<T> {
		public Node<T> car; // Next node to visit

		public Conductor(DLList<T> list) {
			car = list.head; // Begin at head
		}

		public boolean hasNext() {
			return car != null; // No more to visit
		}

		public T next() {
			T data = car.data; // Remember current
			car = car.next; // Advance to after car
			return data; // Return old car data
		}
	}

	public DLList() {
		head = tail = null; // Empty list
	}

	/**
	 * Add data to the end (tail) of the list.
	 */
	public void add(T data) {
		if (tail == null) {
			// Empty list: one node is head and tail
			head = new Node<>(null, data, null);
			tail = head;
		} else {
			tail.next = new Node<>(tail, data, null);
			tail = tail.next;
		}
		incrementSize(); //increments size after each add
	}

	/**
	 * Retrieve an element from middle of list.
	 * 
	 * @param i Zero-based index of element
	 * @return The element at that index
	 * @throws IndexOutOfBoundsException if i is invalid
	 */
	public T get(int i) {
		if (i < 0 || i >= getSize()) {
			throw new IndexOutOfBoundsException();
		}
		Node<T> current;
		if (i * 2 <= getSize()) {
			current = head;
			for (int j = 0; current != null && j < i; j++) {
				// Count our way up to desired element
				current = current.next;
			}
			if (current == null) {
				throw new IndexOutOfBoundsException();
			}
		}
		else {
			current = tail; //start from the end of the list
			for (int j = getSize() - 1; current != null && j > i; j--) {
				// Count our way down to desired element
				current = current.prev;
			}
		}
		return current.data;
	}

	/**
	 * Get and remove element i from the list.
	 * 
	 * @param i Zero-based index of element
	 * @return The element at that index
	 * @throws IndexOutOfBoundsException if i is invalid
	 */
	public T remove(int i) {
		if (i < 0) {
			throw new IndexOutOfBoundsException();
		}
		Node<T> current = head;
		for (int j = 0; current != null && j < i; j++) {
			// Count our way up to desired element
			current = current.next;
		}
		if (current == null)
			throw new IndexOutOfBoundsException();
		if (current.prev != null) {
			// Link before's after to new after
			// (The node after the node before the current node
			// becomes the node after the current node)
			current.prev.next = current.next;
		} else {
			// current must be head, so head should refer to
			// the node after it to stop referencing current
			head = head.next;
		}
		if (current.next != null) {
			// Link after's before to new before
			// (The node before the node after the current node
			// becomes the node before the current node)
			current.next.prev = current.prev;
		} else {
			// current must be tail, so tail should refer to
			// the node before it to stop referencing current
			tail = tail.prev;
		}
		decrementSize(); //decrement the size instance variable
		return current.data;
	}

	/**
	 * Create a forward iterator for this list.
	 * 
	 * @return iterator that walks from head to tail
	 */
	public Iterator<T> iterator() {
		// The Conductor object can walk this list
		// forward, front to back. Each time
		// .next() is called, the Conductor
		// produces one more piece of data,
		// starting with head and ending with tail
		return new Conductor<>(this);
	}

	/**
	 * An inner class similar to the inner Conductor class that implements Iterator that visits all nodes from back to front.
	 */
	private static class BackwardConductor<T> implements Iterator<T> {
		public Node<T> car; // Next node to visit

		public BackwardConductor(DLList<T> list) {
			car = list.tail; // Begin at tail
		}

		public boolean hasNext() {
			return car != null; // No more to visit
		}

		public T next() {
			T data = car.data; // Remember current
			car = car.prev; // Advance to before car
			return data; // Return old car data
		}
	}
	/**
	 * Create a reverse iterator for this list.
	 * 
	 * @return iterator that walks from tail to head
	 */
	public Iterator<T> descendingIterator() {
		return new BackwardConductor<>(this);
	}

	/**
	 * Retrieve the number of nodes of this list in O(1) time.
	 *
	 * @return number of nodes
	 */
	public int size() {
		return getSize();
	}

	/**
	 * Reverse the list, so that what was the tail is now the head, and so forth. A
	 * list going A <-> B <-> C <-> D would now go D <-> C <-> B <-> A.
	 */
	public void reverse() {
		if (getSize() == 0) {
			System.out.println("You can't reverse an empty list!");
		}
		else if (getSize() == 1){
			System.out.println("How can you prove that I did or did not flip a list of count 1\"");
		} else {
			Node<T> current = head; //start at the head
			while (current != null) {
				//The next 3 lines of code are to switch the two pointers
				Node<T> temp = current.next;
				current.next = current.prev;
				current.prev = temp;

				//move onto the next node
				current = temp;
			}
			//lastly, adjust the tail and head instance variables
			Node<T> temp = tail;
			tail = head;
			head = temp;
		}
	}

	/**
	 * Add data to a new node at index i, causing the nodes at that index and after
	 * to be one place ahead of where they were in the list. (Do nothing if i was
	 * not a valid index in the list.)
	 * 
	 * @param i    existing index in the list
	 * @param data information to add into a new node
	 * @return false if i is not an index in the list, true otherwise
	 */
	public boolean add(int i, T data) {
		//check to ensure the index exists within the list
		if (i < 0 || i > getSize()-1)
			return false;
		//if i is closer to the first half, find the position from the front
		else if (i * 2 <= getSize()){
			Node<T> current = head; //start at the head of the list
			for (int j = 0; current != null && j < i; j++) {
				// Count our way up to desired element
				current = current.next;
			}
			//use the node constructor to set the .prev and .next of our new node
			Node<T> nodeToAdd = new Node<>(current.prev,data, current);
			//adjust the .prev.next of current
			if (current.prev != null){
				current.prev.next = nodeToAdd;
			}
			//adjust the .prev point of current
			current.prev = nodeToAdd;
			//adjust the head instance variable if adding to the front of the list
			if (i == 0){
				head = nodeToAdd;
			}
			//increment size instance variable
			incrementSize();
			return true;
		}
		//logic if the index requested is closer to the end
		else {
			Node<T> current = tail; //start at the end of the list
			for (int j = getSize()-1; current != null && j > i; j--) {
				// Count our way down to desired element
				current = current.prev;
			}
			//use the node constructor to set the .prev and .next of our new node
			Node<T> nodeToAdd = new Node<>(current.prev, data, current);
			//adjust the .prev.next of current
			if (current.prev != null) current.prev.next = nodeToAdd;
			//adjust the .prev point of current
			current.prev = nodeToAdd;
			//increment size instance variable
			incrementSize();
			//adjust the tail instance variable if adding to the end of the list
			if(i == getSize() -1){
				tail = nodeToAdd;
			}
			return true;
		}
	}

	/**
	 * Getter for size instance variable
	 */
	private int getSize() {
		return size;
	}

	/**
	 * Setter for size variable that increments by one
	 */
	private void incrementSize() {
		size++;
	}

	/**
	 * Setter for size variable that decrements by one
	 */
	private void decrementSize(){
		size--;
	}
}
