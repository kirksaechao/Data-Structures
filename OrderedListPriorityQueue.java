//Ordered List Priority Queue

package data_structures;

import java.util.Iterator;

public class OrderedListPriorityQueue<E> implements PriorityQueue<E> {

	private OrderedList<E> list;
	public OrderedListPriorityQueue(){
		list = new OrderedList<E>();
	}
    //  Inserts a new object into the priority queue.  Returns true if
    //  the insertion is successful.  If the PQ is full, the insertion
    //  is aborted, and the method returns false.
	public boolean insert(E object){   
		list.add(object);
		return true;
	}
		
    //  Removes the object of highest priority that has been in the
    //  PQ the longest, and returns it.  Returns null if the PQ is empty.
    public E remove(){
    	if (list.isEmpty() == true)
    		return null;
    	return list.removeFirst();
    }
   
    //  Returns the object of highest priority that has been in the
    //  PQ the longest, but does NOT remove it. 
    //  Returns null if the PQ is empty.
    public E peek(){
    	if (list.isEmpty() ==true)
    		return null;
    	return list.peekFirst();
    }
    
    //  Returns true if the priority queue contains the specified element
    public boolean contains(E obj){
    	return list.contains(obj);
    }
   
    //  Returns the number of objects currently in the PQ.
    public int size(){
    	return list.checkSize();
    }
      
    //  Returns the PQ to an empty state.
    public void clear(){
    	list.clear();
    }
   
    //  Returns true if the PQ is empty, otherwise false
    public boolean isEmpty(){
    	return list.isEmpty();
    }
   
    //  Returns true if the PQ is full, otherwise false.  List based
    //  implementations should always return false.
    public boolean isFull(){
    	return false;
    }
    
    //  Returns an iterator of the objects in the PQ, in no particular
    //  order.
    public Iterator<E> iterator(){
    	return list.iterator();
    } 
}
