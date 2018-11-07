package csc130;

/**
 * <p>Title: Driver Class - Lab6App</p>
 *
 * <p>Copyright: Copyright (c) 2014</p>
 *
 * @author  Kevin Perez
 */
import java.sql.*;

public class Lab6App {
	
	
	public static void main(String[] args){
		UnorderedArrayList list1 = new UnorderedArrayList();
		System.out.println(list1);
		
		UnorderedArrayList list2 = new UnorderedArrayList(5);
		System.out.println(list2);
		
		Product p1 = new Product ("456u78",10,5.0);
		Product p2 = new Product ("355d98",7,25.0);
		Product p3 = new Product ("243j58",3,10.0);
		Product p4 = new Product ("264j45",15,13.50);
		Product p5 = new Product ("653o09",9,16.75);
		
		
		
		Product [] pArray = {p1,p2,p3,p4,p5};
		System.out.println();
		for (int i = 0; i < pArray.length; i++){
			list2.insert(pArray[i]);
			System.out.println(list2);
		}
		
		//Locates an item if it is in the list
		specialSearch (list2,p4);
		
		Product p6 = new Product ("344d97",3,23.23);
		//Tries to locate an item not in the list
		specialSearch (list2,p6);
		
		//Locates an item if it is found in the beginning of the list
		specialSearch (list2,p1);
		
		//Locates an item if it is found at the end of the list
		specialSearch (list2,p5);
		
		//Tries to locate an item in an empty list
		specialSearch (list1,p4);
		
		//Deletes an item from middle of the list
		specialRemove(list2,p2);
		
		//Tries to delete an item not in the list
		specialRemove(list2,p6);
		
		//Deletes an item from the beginning of the list
		specialRemove(list2,p1);
		
		//Deletes an item from the end of the list
		specialRemove(list2,p3);
		
		//Deletes the remaining products in the list
		specialRemove(list2,p4);
		specialRemove(list2,p5);
		
		//Tries to delete an item from an empty list
		specialRemove (list2,p3);
		
	}
	
	/*
	  The output is the same with error messages when I comment out the
	  equals method in the Product class. I realize this is  because the object I pass the equals
	  
	
	 * Removes an item from a an UnorderedArrayList and prints the list afterward
	 * @param list
	 * @param p
	 */
	public static void specialRemove(UnorderedArrayList list,Product p){
		list.remove(p);
		System.out.println(list);
	}
	
	/**
	 * Searches for an item in an UnorderedArrayList and prints the location if it's found or prints a message saying it wasn't found if it was not found
	 * @param list
	 * @param p
	 * @return index
	 */
	public static int specialSearch(UnorderedArrayList list, Product p){
		int index = list.search(p);
		
		if (index >= 0)
			System.out.println("Product Id: " + p.getId() + "\n" + "Location: " +
				    index + "\n");
		else
			System.out.println("Item " + p.getId() + " not found.\n");
		
		return index;
	}

	/**
	 * getData method -- gets the products from an SQLite database
	 * @return the an array of products
	 */
	public static Product[] getData(){		
		Statement stmt = null;
		int records = 0;
		Product[] products = null; 
		try {
			Class.forName("org.sqlite.JDBC");
			Connection c = DriverManager.getConnection("jdbc:sqlite:I:/grahamf/products.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");			

			stmt = c.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM products;");
			ResultSetMetaData rsmd = rs.getMetaData();			
			
			for(int i = 1; i <= rsmd.getColumnCount(); i++)	{
				System.out.print(String.format("%-12s", rsmd.getColumnLabel(i)) + "\t");
				System.out.print(rsmd.getColumnTypeName(i) + "\t");
				System.out.println(rsmd.getPrecision(i));
			}
			
			rs = stmt.executeQuery("select count (*) AS totalRecords from products");
			int totalRecords = rs.getInt("totalRecords");
			System.out.println("Records: " + totalRecords);
			
			rs = stmt.executeQuery("SELECT * FROM products;");
			if(rs != null){
				products = new Product[totalRecords];
				while (rs.next()) {
					String prodId = rs.getString("prodId");
					int quantity = rs.getInt("quantity");
					double price = rs.getFloat("price");

					System.out.println(String.format("%3s %-6s %3d %6.2f",
							records, prodId, quantity, price));	
					products[records++] = new Product(prodId, quantity, price);
				}
				System.out.println();
			}
			
			stmt.close();
			c.commit();
			c.close();
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch(SQLException se){
			System.err.println(se.getClass().getName() + ": " + se.getMessage());
		}
		return products;
	}
}
