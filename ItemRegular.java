public class ItemRegular extends Item
{
	//constructor
	public ItemRegular(double length, double width, double height, double weight)
	{
		super(length,width,height,weight);
	}
	
	public double getPrice()
	{
		return 40.0 * getWeight();
	}
}