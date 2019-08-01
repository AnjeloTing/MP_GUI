/**
	The Item class represents an object of an Item which contains the 
	following information about the item: name, type, dimensions such
	as length, width, height and weight.
	
	@author Anjelo Antioquia, Anton Sta. Ana
	@version 2.0
*/

public abstract class Item
{
	//attributes
	private double length, width, height, weight;
	
	
	//constructor
	/**
		This constructor is for objects containing the following parameters:
		Name, type, length, width, heigh, weight.
		
		@param name a String data type which will be the name of the item
		@param type an integer data type specifying what type is the object
		@param length a double data type representing the length of the object
		@param width a double data type representing the width of the object
		@param height a double data type representing the height of the object 
		@param weight a double data type representing the weight of the object
	*/
	public Item(double length, double width, double height, double weight)
	{
		this.length = length;
		this.width = width;
		this.height = height;
		this.weight= weight;
	}


	
	/**
		getLength method returns the length of the item.
		
		@return double the length of the item
	*/
	public double getLength()
	{
		return length;
	}
	
	/**
		getWidth method returns the width of the item
		
		@return double width of the item
	*/
	public double getWidth()
	{
		return width;
	}
	
	/**
		getHeight method returns the height of the item
		
		@return double height of the item
	*/
	public double getHeight()
	{
		return height;
	}
	
	/**
		getWeight method returns the weight of the item
		
		@return double weight of the item
	*/
	public double getWeight()
	{
		return weight;
	}
	
	/**
		ISPOSITIVE is a static boolean method to validate if the given value is a positive number
		@param x a double data type to be checked if its positive
		@return true if it is positive.
	*/
	public static boolean ISPOSITIVE(double x)
	{
		if(x>0)
			return true;
		else 
			return false;
	}
	
	public abstract double getPrice();
}