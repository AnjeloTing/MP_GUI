public class ItemIrregular extends Item
{
	//constructor
	public ItemIrregular(double length, double width, double height, double weight)
	{
		super(length,width,height,weight);
	}
	
	public double getPrice()
	{
		double volumetricWeight = (getLength() * getWidth() * getHeight()) / 305;
		double price = 0;
		if ((40.0 * getWeight()) > (30 * volumetricWeight))
			price += 40.0 * getWeight();
		else
			price += 30 * volumetricWeight;
		
		return price;
	}
}