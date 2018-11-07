package csc130;

/**
 * <p>Title: UnorderedArrayList Class</p>
 *
 * <p>Description: An UnorderedArrayList object extends an ArrayListClass and introduces insert, remove, and search methods</p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * @author Kevin Perez
 * @version 1.0
 */

public class UnorderedArrayList extends ArrayListClass {
	
	public UnorderedArrayList (){
		
	}
	
	public UnorderedArrayList (int size){
		super(size);
	}
	
	public void insert(Object insertItem) {
		if (numItems < items.length){
		items[numItems] = insertItem;
		numItems++;
		}
		else
			System.out.println("Your list is full, cannot add any additional items.");
	
	}
	
	/**
	 * Returns index of item if found. If not found, returns -1
	 * @param searchItem A string of the item's id
	 * @return
	 */
	public int search(Object searchItem) {
		boolean found = false;
		int counter = 0;
		while (!found && counter < numItems){
			if (items[counter].equals(searchItem))
				found = true;
			else
				counter++;
		}
		
		//When Item is not found
		if (found == false)
			counter = -1;
		
		return counter; 
	}
	
	public Object remove(Object removeItem) {
		int num = search(removeItem);
		if (num == -1)
			return null; 
		else{
			Object n = items[num];
			numItems--;
			items[num] = items[numItems];
			
			return n;
		}
			
	}
}
