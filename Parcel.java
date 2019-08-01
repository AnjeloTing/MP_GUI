/**
	This class represents a Parcel object that can be sent and tracked, and can contain many 
	items (but has constraints based on its weight, width and length).
	
	@author Anjelo Antioquia, Anton Sta. Ana
	@version 1.0
*/

import java.util.*;
import java.time.*;

public class Parcel
{	
	//attributes
	private Recipient recipient;
	private Tracking tracking;
	private int parcelTypeId, minSizeIdentifier;
	private int nItems;
	private double length, width, height, weight;
	private double price;
	private boolean isInsured;
	private boolean isValid;
	private boolean[] validSizes = {false, false, false, false, false, false}; //for use in determining valid parcel types
	private String parcelType;
	private static String[] SIZESNAMES = {"Pouch (S)", "Pouch (M)", "Box (S)", "Box (M)", "Box (L)", "Box (XL)"}; 
	private String pricebreakdown;
	
	//constructor
	/** 
		This constructor uses the item array, its count, the sequence number, a recipient, the current
		day, and an insurance check to construct a new Parcel object. It uses information from these 
		parameters along with user input to generate all other necessary components.
		
		@param contents an Item[] that holds information on all items to be placed in the parcel. 
		@param nItems an integer that indicates the number of items in the parcel.
		@param recipient a Recipient object where it contains the name and the region where the parcel will be delivered to
		@param sequenceNum an integer that indicates the 3-digit sequence number for the day.
		@param date a LocalDate that indicates the start date.
		@param isInsured is a boolean that dictates whether the parcel is insured or not.
	*/
	public Parcel (Item[] contents, int nItems, Recipient recipient, int sequenceNum, LocalDate date, boolean isInsured,int type)
	{
		int i;
		int minSizeIdentifier = 0;
		length = 0;
		width = 0;
		height = 0; 
		weight = 0;
		price = 0;
		parcelTypeId = type;
		//Getting minimum dimensions
		for (i = 0; i < nItems; i++)
		{
			if (contents[i].getLength() > length)
				length = contents[i].getLength();
			
			if (contents[i].getHeight() > height)
				height = contents[i].getHeight();
			
			weight += contents[i].getWeight();
			width += contents[i].getWidth();
		}
		if (type > 1)
			parcelType = new String ("BOX");
		else
			parcelType = new String("FLT");
		 
		this.nItems = nItems;
		this.isInsured = isInsured;
		this.recipient = recipient; 
		setPrice(contents);
		tracking = new Tracking(parcelType, date, recipient.getRegion(), nItems, sequenceNum);
		isValid = true;
	}
	
	/**
		Plain Constructor
	*/
	public Parcel(Item[] contents, int nItems)
	{
		this.nItems = nItems;
		int i;
		int minSizeIdentifier = 0;
		length = 0;
		width = 0;
		height = 0; 
		weight = 0;
		price = 0;
		for (i = 0; i < nItems; i++)
		{
			if (contents[i].getLength() > length)
				length = contents[i].getLength();
			
			if (contents[i].getHeight() > height)
				height = contents[i].getHeight();
			
			weight += contents[i].getWeight();
			width += contents[i].getWidth();
		}
		
	}
	
	/**
		getValidSizes boolean[]
	*/
	public boolean[] getValidSizes()
	{
		/** 
			Limitations Guide:
			FLAT:	Pouch (S) = 9 x 14 x 1
					Pouch (L) = 12 x 18 x 3
					weight <= 3KG

			BOX:	Box (S) = 12 x 10 x 5
					Box (M) = 14 x 11 x 7
					Box (L) = 18 x 12 x 9
					Box (XL) = 20 x 16 x 12
		*/
		if(weight<=3)
		{
			if (length <= 9 && width <= 14 && height <= 1) //Pouch (S)
				validSizes[0]=true;
			if (length <= 12 && width <= 18 && height <= 3)//Pouch (L)
				validSizes[1]=true;				
		}
		
		if (length <= 12 && width <= 10 && height <= 5) //Box (S)
			validSizes[2]=true;
		if (length <= 14 && width <= 11 && height <= 7) //Box (M)
			validSizes[3]=true;
		if (length <= 18 && width <= 12 && height <= 9) //Box (L)
			validSizes[4]=true;
		if (length <= 20 && width <= 16 && height <= 12)//Box (XL)
			validSizes[5]=true;
		
		return validSizes;
	} 

	
	/**
		This method is used to find a parcel's price based on certain attributes such as parcel type, weight,
		destination, items, insurace status etc. as well as provide a price breakdown for the user.
		
		@param contents an Item[] that holds information on all items to be placed in the parcel. 
	*/	
	private void setPrice (Item[] contents)
	{
		int i;
		double tempAddPrice;//, volumetricWeight;
		Scanner scanner = new Scanner(System.in);
		pricebreakdown = new String("");
		System.out.println("Price Breakdown: ");
		pricebreakdown+= "\nPrice Breakdown: ";
		
		//Base Price
		tempAddPrice = 0;
		if (parcelTypeId == 0)		//Pouch Small
		{
			tempAddPrice = 30;
		} 
		else if (parcelTypeId == 1)	//Pouch Medium
		{
			tempAddPrice = 50;
		} 
		else if (parcelTypeId > 1)			
		{
			for(i = 0 ; i < nItems; i++)
			{
				if(contents[i] instanceof ItemRegular)
				{
					tempAddPrice+= ((ItemRegular)contents[i]).getPrice();
				}
				else if(contents[i] instanceof ItemDocument)
				{
					tempAddPrice+= ((ItemDocument)contents[i]).getPrice();
				}
				else
				{
					tempAddPrice+= ((ItemIrregular)contents[i]).getPrice();
				}
			}
			
			// for (i = 0; i < nItems; i++)
			// {
				// if ((contents[i] instanceof ItemRegular) || (contents[i] instanceof ItemDocument)) //regular product or document
					// tempAddPrice += 
					// tempAddPrice += (40.0 * contents[i].getWeight());
				// else //irregular product
				// {
					// volumetricWeight = (contents[i].getLength() * contents[i].getWidth() * contents[i].getHeight()) / 305;
					// if ((40.0 * contents[i].getWeight()) > (30 * volumetricWeight))
						// tempAddPrice += 40.0 * contents[i].getWeight();
					// else
						// tempAddPrice += 30 * volumetricWeight;
				// }
			// }	
		}
		System.out.println("Base Price: " + tempAddPrice);
		pricebreakdown+= "\nBase Price: " + tempAddPrice;
		price += tempAddPrice;
		
		//Service Fee
		if (recipient.getRegion().equals("MML"))
			tempAddPrice = 50;
		else if (recipient.getRegion().equals("LUZ"))
			tempAddPrice = 100;
		else if (recipient.getRegion().equals("VIS"))
		{
			if (1000 > weight * 0.10)
				tempAddPrice = 1000;
			else
				tempAddPrice = weight * 0.10;
		}
		else if (recipient.getRegion().equals("MIN"))
		{
			if (3000 > weight * 0.25)
				tempAddPrice = 3000;
			else
				tempAddPrice = weight * 0.25;
		}
		System.out.println("Service Fee: " + tempAddPrice);
		pricebreakdown+="\nService Fee: " + tempAddPrice;
		price += tempAddPrice;
		
		//Insurance
		if (isInsured == true) {
			tempAddPrice = 0;
			for (i = 0; i < nItems; i++)
			{
				tempAddPrice += 5;	
			}
			System.out.println("Insurance: " + tempAddPrice);
			pricebreakdown+="\nInsurance: " + tempAddPrice;
			price += tempAddPrice;
		}
		System.out.println("-----------------");
		System.out.println("Total Price: " + price);
		pricebreakdown+="\n-----------------" + "\nTotal Price: " + price;
	}
	
	/**
		getTracking returns the tracking object corresponding to the Parcel.
		
		@return tracking an object of type Tracking containing tracking info about the parcel.
	*/	
	public Tracking getTracking ()
	{
		return tracking;
	}
	
	/**
		getPriceBreakdown
		
		@return String
	*/
	
	public String getPriceBreakdown()
	{
		return pricebreakdown;
	}
	/**
		getTracking returns the validity of the Parcel object as a boolean.
		
		@return isValid a boolean that tells whether the generated parcel is valid or not.
	*/	
	public boolean getIsValid ()
	{
		return isValid;
	}
	
	/**
		getRecipient returns the recipient object of the Parcel object
		
		@return recipient a Recipient object of the parcel
	*/
	public Recipient getRecipient()
	{
		return recipient;
	}
	
	public double getPrice()
	{
		return price;
	}
}