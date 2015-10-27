//Unordered Array Priority Queue

package data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class UnorderedArrayPriorityQueue<E> implements PriorityQueue<E> {
	
	private int maxSize;
	private int currentSize;
	private E[] storage;
	private long modcounter;
	
	public UnorderedArrayPriorityQueue(int size){
		currentSize = 0;
		maxSize = size;
		storage = (E[]) new Object[maxSize];
		modcounter = 0;
	}
		
	public UnorderedArrayPriorityQueue(){
		currentSize = 0;
		maxSize = DEFAULT_MAX_CAPACITY;
		storage = (E[]) new Object[maxSize];
		modcounter = 0;
	}
	
//  Inserts a new object into the priority queue.  Returns true if
    //  the insertion is successful.  If the PQ is full, the insertion
    //  is aborted, and the method returns false.
	
    public boolean insert(E object){
    	if (isFull()){
    		return false;
    	}
    	storage[currentSize++] = object;
    	modcounter++;
    	return true;
    }
    
    //  Removes the object of highest priority that has been in the
    //  PQ the longest, and returns it.  Returns null if the PQ is empty.
    public E remove(){
    	if (isEmpty())
    		return null;
    	
    	int topPriority = 0;
    	
    	for (int i = 0; i <= currentSize-1; i++){
    		int comp = ((Comparable<E>)storage[topPriority]).compareTo(storage[i]);
    		if (comp > 0) //if storage item is smaller than topPriority
    			topPriority = i; //topPriority gets INDEX of the highest priority element
    	}
    	
    	E answer = storage[topPriority];
    	for (int j = topPriority; j < currentSize-1; j++){
    		storage[j] = storage[j+1];
    	}

    	currentSize--;
    	modcounter++;
    	return answer;
  
    }
   
    //  Returns the object of highest priority that has been in the
    //  PQ the longest, but does NOT remove it. 
    //  Returns null if the PQ is empty.
    public E peek(){
    	if (isEmpty())
    		return null;
    	
    	int topPriority = 0;
    	
    	for (int i = 0; i<=currentSize-1;i++){
    		int comp = ((Comparable<E>)storage[topPriority]).compareTo(storage[i]);
    		if (comp > 0)
    			topPriority = i;
    	}
    	
    	return storage[topPriority];
    	
    }
    
    //  Returns true if the priority queue contains the specified element
    public boolean contains(E obj){
    	for (int i = 0; i <= currentSize-1; i++){
    		int comp = ((Comparable<E>)obj).compareTo(storage[i]);
    		if (comp == 0) //found
    			return true;
    	}
    		return false;
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
