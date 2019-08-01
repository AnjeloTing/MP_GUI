/**
	The Item class represents an object of an Item of type document,
	which contains a number of pages.
	
	@author Anjelo Antioquia, Anton Sta. Ana
	@version 2.0
*/

public class ItemDocument extends Item
{
	//attributes
	private int nPages;
	
	//constructor
	/**
		This constructor is for an item that is a document containing number of pages
		
		@param nPages an double data type specifying the number of pages it has
		@param length a double data type specifying the length of the page
		@param width a double data type specifying the width of the page
		
	*/
	public ItemDocument (int nPages, double length, double width)
	{
		super(length, width, (Double.valueOf(nPages) / 25.0), (nPages * 0.008)); //25 pages = to 1 inch, 1 page = 0.008 kilograms		
		this.nPages = nPages;
	}
	
	/**
		getPages method returns the number of pages
		
		@return int number of pages
	*/
	public int getPages ()
	{
		return nPages;
	}
	
	public double getPrice()
	{
		return 40.0 * getWeight();
	}
	
}
