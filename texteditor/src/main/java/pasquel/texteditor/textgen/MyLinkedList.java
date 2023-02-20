package pasquel.texteditor.textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		head = new LLNode<>();
		tail = new LLNode<>();
		size = 0;
		head.next=tail;
		tail.prev=head;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	@Override
	public boolean add(E element ) 
	{	try {
		LLNode<E> temp = new LLNode<>(element);
		temp.prev=tail.prev;
		temp.next=temp.prev.next;
		temp.prev.next=temp;
		temp.next.prev	=temp;
		size++;
		return true;
		} catch (Exception e) {
			return false;
		}	
	}

	public boolean addFront(E element ) 
	{	try {
		LLNode<E> temp = new LLNode<>(element);
		temp.next=head.next;
		temp.prev=temp.next.prev;
		temp.next.prev=temp;
		temp.prev.next	=temp;
		size++;
		return true;
		} catch (Exception e) {
			return false;
		}	
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	private LLNode<E> getLLN(int index) 
	{
		if(index<0 || index>size-1){
			throw new IndexOutOfBoundsException("Index is out of bounds");
		}
		double ratio = (index/(size));
		LLNode<E> temp1;
		if(ratio<0.5){
			temp1 = head;
			for (int i = 0; i <= index; i++) {
				temp1=temp1.next;
			}
			return temp1;
		}
		else{
			temp1 = tail;
			for (int i = size-1; index < i; i--) {
				temp1=temp1.prev;
			}
			return temp1;
		}
	}
	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		return getLLN(index).data;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	@Override
	public void add(int index, E element ) 
	{
		if(index==0){
			addFront(element);
		}else if(index==size-1){
			add(element);
		}else{
		LLNode<E> tempNext = getLLN(index);
		LLNode<E> temp = new LLNode<>(element);
		temp.prev=tempNext.prev;
		temp.next=temp.prev.next;
		temp.prev.next=temp;
		temp.next.prev	=temp;
		size++;
		}
	}
	/** Return the size of the list */
	public int size() 
	{
		return size;
	}
	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	@Override
	public E remove(int index) 
	{
		LLNode<E> temp = getLLN(index);
		temp.prev.next=temp.next;
		temp.next.prev=temp.prev;
		size--;

		return temp.data;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	@Override
	public E set(int index, E element) 
	{
		LLNode<E> temp = getLLN(index);
		temp.data=element;
		return temp.data;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("");
		sb.append("[");
		for (int i = 0; i < size; i++) {
			sb.append(get(i)+",");
		}
		sb.append("]");
		return sb.toString();
	}
	   
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}
	public LLNode() 
	{
		this.data = null;
		this.prev = null;
		this.next = null;
	}
}
