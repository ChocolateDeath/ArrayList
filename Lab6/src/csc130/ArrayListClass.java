package csc130;

/**
 * <p>Title: ArrayListClass</p>
 *
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * @author Kevin Perez
 * @version 1.0
 */
public abstract class ArrayListClass
{
    protected int numItems;         //to store the number of items in the list
    protected Object[] items;       //array to hold the items in the list

    /**
     * default constructor - creates an list of size 50; the number of items
     * in the list is initialized to 0
     */
    public ArrayListClass()
    {
          numItems = 0;
          items = new Object[50];
    }

    /**
     * parameterized constructor - allows the user to specify the maximum size of the list.
     * The list created can store at most size items; the number of items in the list is
     * initialized to 0.
     * @param size indicates the maximum size of the list as specified by the user
     */
    public ArrayListClass(int size)
    {
          if(size <= 0)
          {
                System.err.println("The list size must be positive. "
                                   + "Creating a list whose size is 50. ");
                items = new Object[50];
          }
          else
                items = new Object[size];
          numItems = 0;
    }

    /**
     * empty method - determines whether or not the list is empty
     * @return true if the list is empty; false otherwise
     */
    public boolean empty()
    {
          return (numItems == 0);
    }

    /**
     * full method - determines whether or not the list is full
     * @return true if the list is full; false otherwise
     */
    public boolean full()
    {
          return (numItems == items.length);
    }

    /**
     * listSize method - returns the number of items in the list
     * @return the number of items in the list
     */
    public int listSize()
    {
          return numItems;
    }

    /**
     * maxListSize method - returns the maximum size of the list
     * @return the maximum size of the list
     */
    public int maxListSize()
    {
          return items.length;
    }

    /**
     * makeEmpty method - makes the list empty and sets numItems to 0
     */
    public void makeEmptpy()
    {
          items = new Object[items.length];
          numItems = 0;
          System.gc();
    }

    /**
     * toString method - returns the state of the object as a String
     * @return a String containing the items in the list
     */
    public String toString()
    {
        String temp = new String();
        if (empty())
            temp = new String("The list is empty...");
        else
        {
            temp = new String("The list contains:\n");
            for (int i = 0; i < numItems; i++)
                temp += items[i] + "\n";
        }
        return temp;
    }

    /**
     * search method - determines whether or not searchItem is in the list
     * @param searchItem is a reference to an item whose key-field has been initialized
     * @return an integer which represents the location in the list where searchItem
     * was found; if searchItem is not found, -1 is returned
     */
    public abstract int search(Object searchItem);

    /**
     * insert method - inserts an item in the list
     * @param insertItem is a reference to the item to be inserted
     */
    public abstract void insert(Object insertItem);

    /**
     * remove method - removes an item from the list
     * @param removeItem is a reference to an item whose key-field has been initialized.
     * If the item is found it is removed; otherwise the list remains unchanged
     */
    public abstract Object remove(Object removeItem);
    
}

