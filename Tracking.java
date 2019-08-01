/** 
	The class Tracking represents an object with important information about a Parcel
	such as parcel type, start date, destnation code, number of items in the parcel,
	and sequence number. These are combined into a tracking number.
	 
	@author Anjelo Antioquia, Anton Sta. Ana
	@version 1.0
*/
import java.util.*;
import java.time.*;
import java.time.temporal.ChronoUnit;

public class Tracking
{
	//attributes
	private LocalDate shippedDate;
	private String destCode;
	private String sStatus;
	private String sTrackingNumber;

	//constructor
	/** This constructor initializes all attributes of the Tracking object.
	
		@param parcelType a String that indicates whether the parcel is of type flat (FLT) or box (BOX)
		@param date a LocalDate that indicates the start date.
		@param destCode a String that indicates whether the parcel is for delivery within Metro Manila (MML),
			   provincial Luzon (LUZ), Visayas (VIS), or Mindanao (MIN).
		@param itemQuantity an integer that indicates the 2-digit number of items in the parcel.
		@param sequenceNum an integer that indicates the 3-digit sequence number for the day.
	*/
	public Tracking (String parcelType, LocalDate shippedDate, String destCode, int itemQuantity, int sequenceNum)
	{	/**
			Format Guide:
			<ParcelType><MMDD><destCode><#Items><#seq>
		*/
		
		this.shippedDate = shippedDate;
		this.destCode = destCode;	
		sStatus = "Preparing";
		sTrackingNumber =  parcelType + 
						   String.format("%02d", shippedDate.getMonthValue()) + 
						   String.format("%02d", shippedDate.getDayOfMonth()) + 
						   destCode + 
						   String.format("%02d", itemQuantity) + 
						   String.format("%03d", sequenceNum); // The string format of the tracking number
	}
	
	/** This method returns the delivery status of the parcel
		
		@return sStatus a String indication of the delivery status
	*/	
	public String getStatus ()
	{
		return sStatus;
	}	
	
	/** This method returns a tracking number generated with its attributes.
		
		@return sTrackingNumber a String that contains the Tracking object's attributes.
	*/
	public String getTrackingNumber ()
	{
		return sTrackingNumber;
	}
	
	/** This method modifies the delivery status of the parcel on a given date.
		
		@param curDate date a LocalDate that indicates the current system date.
	*/	
	public void setStatus (LocalDate curDate)
	{
		long dif = ChronoUnit.DAYS.between(shippedDate, curDate);		//checks the difference between the date the transaction was made to the current date
		if (dif > 0) 			//if there is a difference
		{
			if (dif >= 2 && dif < 3)		//if 2 day difference
			{
				if (destCode.equals("MML"))	//Manila only takes 2 days to get the parcel delivered
					sStatus = "Delivered";	//otherwise it is now shipping
				else
					sStatus = "Shipping";
			
			} 
		
			if (dif >= 3 && dif < 5)		//Luzon takes 3 days to get a parcel delivered
			{
				if (destCode.equals("MML") || destCode.equals("LUZ"))
					sStatus = "Delivered";
			}	
		
			if (dif >= 5 && dif < 8)		//Visayas takes 5 days to get a parcel delivered
			{
				if (destCode.equals("MML") || destCode.equals("LUZ") || destCode.equals("VIS"))
					sStatus = "Delivered";
			}
		
			if (dif >= 8)				//Else all parcels from the difference of the date are already delivered
			{
				sStatus = "Delivered";
			}
		} 
		else 
		{
			System.out.print("You cannot turn back time.");
		}
	}
}