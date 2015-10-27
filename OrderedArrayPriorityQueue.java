//Ordered Array Priority Queue
package data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class OrderedArrayPriorityQueue<E> implements PriorityQueue<E> {
	
	private int maxSize;
	private int currentSize;
	private E[] storage;
	private long modcounter;
	
	public OrderedArrayPriorityQueue(int size){
		maxSize = size;
		storage = (E[]) new Object[maxSize];
		currentSize = 0;
		modcounter = 0;
	}
		
	public OrderedArrayPriorityQueue(){
		currentSize=0;
		maxSize = DEFAULT_MAX_CAPACITY;
		storage = (E[]) new Object[maxSize];
		modcounter = 0;
	}
	
	private int binSearch(E obj, int lo, int hi){
		if (hi<lo) return -1; //not found
		int mid = (lo + hi)/2;
		int comp = ((Comparable<E>) obj).compareTo(storage[mid]);
		if (comp == 0) return mid; //found
		if (comp > 0)
			return binSearch(obj, lo, mid-1);
		return binSearch(obj, mid+1, hi);
	}
	
	private int findInsertionPoint(E obj, int lo, int hi){
		if (hi<lo) return lo; //not found
		int mid = (lo + hi)/2;
		int comp = ((Comparable<E>)obj).compareTo(storage[mid]);
		if (comp >= 0)
			return findInsertionPoint(obj, lo, mid-1);
		return findInsertionPoint(obj, mid+1, hi);
	}
	
	//  Inserts a new object into the priority queue.  Returns true if
    //  the insertion is successful.  If the PQ is full, the insertion
    //  is aborted, and the method returns false.
    public boolean insert(E object){
    	if (isFull())
    		return false;
    	int where = findInsertionPoint(object, 0, currentSize-1);
    	for (int i = currentSize-1; i>= where; i--){
    		storage[i+1] = storage[i];
    	}
    	storage [where] = object;
    	currentSize ++;
    	modcounter++;
		return true;
    }
    	
    //  Removes the object of highest priority that has been in the
    //  PQ the longest, and returns it.  Returns null if the PQ is empty.
    public E remove() {
		if (isEmpty())
			return null;
		modcounter++;
		return storage[--currentSize];
	}
    
    //  Returns the object of highest priority that has been in the
    //  PQ the longest, but does NOT remove it. 
    //  Returns null if the PQ is empty.
    public E peek(){   
    	if(isEmpty())
    		return null;
    	return storage[currentSize-1];
    }
    //  Returns true if the priority queue contains the specified element
    public boolean contains(E obj){
    	int checkObj = binSearch(obj, 0, currentSize-1);
    	return checkObj != -1;
    }
   
    
    //  Returns the number of objects currently in the PQ.
    public int size(){
      return currentSize;
      }
      
    //  Returns the PQ to an empty state.
    public void clear(){
    	currentSize = 0;
    }
    //  Returns true if the PQ is empty, otherwise false
    public boolean isEmpty(){
    	if (currentSize == 0)
    		return true;
    	return false;
    }
   
    //  Returns true if the PQ is full, otherwise false.  List based
    //  implementations should always return false.
    public boolean isFull(){
    	if (currentSize == maxSize)
    		return true;
    	return false;
    }
    
    //  Returns an iterator of the objects in the PQ, in no particular
    //  order.
    public Iterator<E> iterator(){
    	return new IteratorHelper();
    }
    
    class IteratorHelper implements Iterator<E> {
        int iterIndex;
        
        public IteratorHelper() {
            iterIndex = 0;
            }
            
        public boolean hasNext() {
            return iterIndex < currentSize;
            }
            
        public E next() {
            if(!hasNext())
                throw new NoSuchElementException();
            return storage[iterIndex++];
            }
            
        public void remove() {
            throw new UnsupportedOperationException();
            }
        }
}
