

import java.util.Random;
import java.util.Scanner;

public class FactoryApp {
	
	public static void main(String[] args) throws NotInitializedException, EmptyDataException, MaxCapacityException {
		
		FactoryLine<IProduct> factoryLine = new FactoryLine<IProduct>();
		Warehouse<IProduct> cacheWarehouse = new Warehouse<>();
		Warehouse<IProduct> cpuWarehouse = new Warehouse<>();
		Warehouse<IProduct> graphicsCardWarehouse = new Warehouse<>();
		Warehouse<IProduct> motherBoardWarehouse = new Warehouse<>();
		Warehouse<IProduct> ramWarehouse = new Warehouse<>();
		Scanner scanLine = new Scanner(System.in);
		System.out.println("Enter the number of random request cycles: ");
		int cycleNum = scanLine.nextInt();
		for (int i=0;i<cycleNum;i++) {
			Random rand = new Random();
			int random_operation=rand.nextInt(2);
			// TRIGGERING MARKETING ANALYST
			if (random_operation==0) {
				int random_product=rand.nextInt(4);
				MarketingAnalyst(factoryLine,random_product);
			// TRIGGERING STORAGE CHIEF
			}else if (random_operation==1) {
				try {
					IProduct storedProduct=factoryLine.dequeue();
					switch (storedProduct.getClass().getSimpleName()) {
					case "Cache":
						cacheWarehouse.push(storedProduct);
					case "Cpu":
						cpuWarehouse.push(storedProduct);
					case "GraphicsCard":
						graphicsCardWarehouse.push(storedProduct);
					case "MotherBoard":
						motherBoardWarehouse.push(storedProduct);
					case "Ram":
						ramWarehouse.push(storedProduct);
					}
					System.out.println("Storage Chief storing "+storedProduct.toString()+", SUCCESS , "+storedProduct.toString()+" stored in "+storedProduct.toString()+" Warehouse.");
					
				} catch (NotInitializedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// TRIGGERING CUSTOMER
			}else if (random_operation==3) {
				int random_product=rand.nextInt(4);
				switch(random_product) {
				case 0:
					Customer(cacheWarehouse,"CACHE");
				case 1:
					Customer(cpuWarehouse,"CPU");
				case 2:
					Customer(graphicsCardWarehouse,"GRAPHICS CARD");
				case 3:
					Customer(motherBoardWarehouse,"MOTHERBOARD");
				case 4:
					Customer(ramWarehouse,"RAM");
				}
			}
		}
		
		
		
		
		
	}
	public static void MarketingAnalyst(FactoryLine<IProduct> factoryLine, int product_num) {
		IProduct newProduct=null;
		if (product_num==0) {
			newProduct = new Cache();
		}else if(product_num==1) {
			newProduct= new Cpu();
		}else if(product_num==2) {
			newProduct= new GraphicsCard();
		}else if(product_num==3) {
			newProduct= new MotherBoard();
		}else if(product_num==4) {
			newProduct= new Ram();
		}
		try {
			factoryLine.enqueue(newProduct);
			System.out.println("Marketing Analyst requesting " + newProduct.toString()+ ", SUCCESS, "+newProduct.toString()+" manufactured.");
		} catch (NotInitializedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MaxCapacityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void Customer(Warehouse<IProduct> productWarehouse, String productName) {
		try {
			productWarehouse.pop();
		} catch (NotInitializedException e) {
			
			e.printStackTrace();
		} catch (EmptyDataException e) {
			System.out.println("Customer buying "+productName+ ", FAIL ,"+productName+" Warehouse is empty.");
		}
	}
}
