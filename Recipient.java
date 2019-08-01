/**
	The class Recipient represents a simple recipient object, containing their name
	and the region where the parcel will be delievered.
	
	@author Anjelo Antioquia, Anton Sta. Ana
	@version 1.0
*/

public class Recipient
{
	//attributes
	private String name;
	private String region;
	
	//constructor
	/** 
		This constructor contains the 2 attributes of a recipient: The recipient's name and the 
		region then package should be to be delievered to.
		
		@param name a String data type containing the name of the recipient.
		@param nRegion an integer data type with a value that corresponds to the region the parcel
			   will delivered to.
	*/
	public Recipient(String name, int nRegion)
	{
		this.name = name;
		if(nRegion == 1)
			region = "MML";
		else if(nRegion == 2)
			region = "LUZ";
		else if(nRegion == 3)
			region = "VIS";
		else if(nRegion == 4)
			region = "MIN";
	}
	
	/**
		This constructor is used to instantiate or create the object with no values.
	*/
	public Recipient()
	{
		
	}
	/**
		getRecipientName returns the name of the Recipient object.
		
		@return name a String data type which is the name of the recipient.
	*/
	public String getRecipientName()
	{
		return name;
	}
	
	/**
		getRegion returns the region where the parcel would be delivered.
		
		@return region a String data type that corresponds to the region the parcel will delivered to.
	*/
	public String getRegion()
	{
		return region;
	}
	
	
	
}