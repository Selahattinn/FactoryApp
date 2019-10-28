
public class Cache implements IProduct {
	private boolean isManufactured=false;
	private boolean isStored=false;
	private boolean isSold=false;

	public boolean isManufactured() {
		isManufactured=true;
		return isManufactured;
	}

	public boolean isStored() {
		isStored=true;
		return isStored;
	}

	
	public boolean isSold() {
		isSold=true;
		return isSold;
	}
	public String toString() {
		return "CACHE";
	}

}
