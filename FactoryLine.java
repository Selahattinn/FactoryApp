import java.util.Arrays;

public class FactoryLine<T> {
	private T[] products;
	private int frontIndex;
	private int backIndex;
	private boolean initialized=false;
	private static final int DEFAULT_CAPACITY = 100;
	private static final int MAX_CAPACITY=1000;
	
	public FactoryLine() {
		this(DEFAULT_CAPACITY);
		
	}
	@SuppressWarnings("unchecked")
	public FactoryLine(int cap) {
		products = (T[]) new Object[cap+1];
		frontIndex=0;
		backIndex=products.length-1;
		initialized=true;
	}
	public void enqueue(T newProduct) throws NotInitializedException, MaxCapacityException {
		CheckInitialization();
		EnsureCapacity();
		backIndex=(backIndex +1) % products.length;
		products[backIndex]=newProduct;
	
	}
	public T dequeue() {
		T front =  null;
		
		if(!isEmpty()) {
			front = products[frontIndex];
			products[frontIndex] = null;
			frontIndex = (frontIndex + 1) % products.length;
		}
		
		return front;
	}
	public T getFront() throws EmptyDataException, NotInitializedException {
	
		CheckInitialization();
		
		if (isEmpty()) {
			
			throw new EmptyDataException("Queue is empty.");
		}else {
			return products[frontIndex];
		}
	}
	public boolean isFull() {
		
		return frontIndex== (backIndex+2) % products.length;
	}
	public boolean isEmpty() {
	
		return frontIndex==(backIndex+1) % products.length;
	}
	public void clear() {
		boolean flagLoop=true;
		while (flagLoop){
			products[frontIndex]=null;
			
			if (frontIndex==backIndex) {
				flagLoop=false;
				
			}
			frontIndex=(frontIndex+1) % products.length;
		}
	}
	public void displayProducts() {
		
		for (int i=0;i<products.length;i++) {
			
			if (products[i]!=null) {
				
				System.out.println(products[i].toString());
			}
			
		}
		
	}
	@SuppressWarnings("unchecked")
	private void EnsureCapacity() throws MaxCapacityException {
		if (isFull()) {
			T[] oldQueue= products;
			int oldSize=oldQueue.length;
			int newSize = oldSize *2;
			CheckCapacity(newSize);
			T [] tempQueue=(T[]) new Object[newSize];
			products=tempQueue;
			for (int index=0; index < oldSize; index++) {
				products[index]=oldQueue[frontIndex];
				frontIndex= (frontIndex+1) % oldSize;
			}
			frontIndex=0;
			backIndex=oldSize-2;
			
			
			
		}
	}
	private void  CheckInitialization() throws NotInitializedException {
		if (initialized==false) {
			throw new NotInitializedException("Line is not initialized!");
		}
	}
	private void CheckCapacity(int newCapacity) throws MaxCapacityException {
		if (newCapacity >MAX_CAPACITY) {
			throw new MaxCapacityException("Max Capacity is exceeded.");
		}
	}
}
 