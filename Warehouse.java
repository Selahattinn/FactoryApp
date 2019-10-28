import java.util.Arrays;

public class Warehouse<T> {

	private T[] products ;
	private boolean initiliazed=false;
	private static final int DEFAULT_CAPACITY=100;
	private static final int MAX_CAPACITY=1000;
	private int topIndex=-1;
	public Warehouse() {
		this(DEFAULT_CAPACITY);	
	}
	@SuppressWarnings("unchecked")
	public Warehouse(int capacity) {
		products=(T[])new Object[capacity];
		initiliazed=true;
	}
	public void  push(T product) throws MaxCapacityException, NotInitializedException {
		CheckInitialization();
		ensureCapacity();
		topIndex++;
		products[topIndex]=product;
		
	}
	public T pop() throws NotInitializedException, EmptyDataException {
		CheckInitialization();
		if(isEmpty()) {
			throw new EmptyDataException("The Warehouse is empty");
		}
		else {
			T removedProduct=products[topIndex];
			products[topIndex]=null;
			topIndex--;
			return removedProduct;
			
		}
	}
	public T peek() throws EmptyDataException, NotInitializedException {
		CheckInitialization();
		if(isEmpty()) {
			throw new EmptyDataException("The Warehouse is empty");
			
		}
		else {
			return products[topIndex];
		}
	}
	public boolean isEmpty() {
			return topIndex==-1;
	}
	public boolean isFull() {
			return topIndex==products.length-1;
	}
	public void clear() {
		for (int i = 0; i <= topIndex; i++) {
			products[i]=null;
			topIndex=-1;
		}
		
	}
	private void ensureCapacity() throws MaxCapacityException {
		if(topIndex==products.length-1) {
			int newLength=2*products.length;
			CheckCapacity(newLength);
			products=Arrays.copyOf(products,newLength);
		}
	}
	private void CheckCapacity(int newCapacity) throws MaxCapacityException {
		if (newCapacity>MAX_CAPACITY) {
			throw new MaxCapacityException("Max Capacity is exceeded.");
		}
	}
	private void CheckInitialization() throws NotInitializedException {
		if (initiliazed==false) {
			throw new NotInitializedException("Warehouse is not initialized.");
		}
	}
}