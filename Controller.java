import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import javax.swing.Timer;
import java.time.*;

public class Controller implements ActionListener
{
	//attributes
	private GUI gui;
	private int i,nItems,nRegion;
	private String iType;
	private boolean checker;
	private TimeClass tc;
	private Timer timer;
	private int nParcels;
	
	//declare classes that were created
	private ArrayList <Parcel> parcelList = new ArrayList <Parcel> ();
	private Recipient customer;
	private Item[] items;
	private boolean isInsured;
	private int scene;
	private String[] SIZESNAMES = {"Pouch (S)", "Pouch (M)", "Box (S)", "Box (M)", "Box (L)", "Box (XL)"}; 
	
	public Controller(GUI gui)
	{
		this.gui = gui;
		scene = 1;
		gui.addListeners(this);
		
		nParcels = 0;
		int count = 0;
		isInsured = false;
		iType = new String();
		customer = new Recipient();
			
		tc = new TimeClass();
		timer = new Timer(1000, tc); ///1000 = 1 sec
		timer.start(); //intiate count		
			
	}
	
	public void actionPerformed(ActionEvent e)
	{
		
		if(e.getActionCommand().equals("Create Transaction"))
		{
			i=0;
			nRegion = 0;
			gui.CreateTrans(this);
			scene = 2;
		}
		
		else if(e.getActionCommand().equals("Back"))
		{
			gui.remove(gui.getcPanel());
			gui.menu();
			gui.addListeners(this);
			scene = 1;
		}
		
		else if(e.getActionCommand().equals("Next"))
		{
		
			String m1 = "Please input a valid item quantity.\n";
			String m2 = "Please select a region.\n";
			String m3 = "Please enter the recipient's name.\n";
			String mTotal = new String();
				   
			System.out.println(gui.getName().length());
			
			if(gui.getSelectedItem().equals("Manila"))
				nRegion =1;
			else if(gui.getSelectedItem().equals("Luzon"))
				nRegion =2;
			else if(gui.getSelectedItem().equals("Visayas"))
				nRegion =3;
			else if(gui.getSelectedItem().equals("Mindanao"))
				nRegion =4;
			
			if(gui.getnItems() > 0 && gui.getName().length() > 0 && nRegion != 0)
			{
				gui.remove(gui.getcPanel());
				nItems = gui.getnItems();
				//System.out.println(gui.getName());
				//TO DO: gui.getName gets the recipient name// place in the parcel
				//System.out.println(gui.getnItems());
				//TO DO: gui.getnItems is the number of items// place in the parcel object
				customer = new Recipient(gui.getName(),nRegion);
				items = new Item[nItems];
				System.out.println(customer.getRecipientName() + " " + customer.getRegion());
				gui.ItemMaking(this,i+1);
				scene = 3;
			}
			else
			{
				if (gui.getName().length() == 0)
					mTotal += m3;
				
				if (gui.getnItems() <= 0)
					mTotal += m1;
				
				if (nRegion == 0)
					mTotal += m2;
				
				JOptionPane.showMessageDialog(null, mTotal);
			}		
		}
		
		else if(e.getActionCommand().equals("Regular Product"))
		{
			gui.remove(gui.getiPanel());
			gui.ProdTextField(this,gui.getiPanel());
			iType = "Regular Product";
		}
		
		else if(e.getActionCommand().equals("Irregular Product"))
		{
			gui.remove(gui.getiPanel());
			gui.ProdTextField(this,gui.getiPanel());
			iType = "Irregular Product";
		}
		
		else if(e.getActionCommand().equals("Document"))
		{
			gui.remove(gui.getiPanel());
			gui.DocTextFields(this,gui.getiPanel());
			iType = "Document";
		}
		
		else if(e.getActionCommand().equals("Set"))
		{
			checker = true;
			if(iType.equals("Regular Product")||iType.equals("Irregular Product"))
			{
				if(gui.getLgt()<=0 || gui.getWid()<=0 || gui.getHgt()<=0 || gui.getWgt()<=0)
					checker = false;
				if(!checker)
					JOptionPane.showMessageDialog(null,"Invalid inpu. Please enter positive numbers only!");
				else
				{
					if(iType.equals("Regular Product"))
							items[i] = new ItemRegular(gui.getLgt(),gui.getWid(),gui.getHgt(),gui.getWgt());
						
					else
						items[i] = new ItemIrregular(gui.getLgt(),gui.getWid(),gui.getHgt(),gui.getWgt());
					//Check purposes
					System.out.println(items[i].getLength() + " " + items[i].getWidth() + " " + items[i].getHeight() + " " + items[i].getWeight());
					i++;
					if(i == nItems)
					{
						//Proceed to next panel
						JOptionPane.showMessageDialog(null,"All items have been set.");
						gui.remove(gui.getiPanel());
						gui.Insure(this);
						scene = 4;
					}
					else
					{
						JOptionPane.showMessageDialog(null,"Item has been set, proceed to the next item.");
						gui.remove(gui.getiPanel());
						gui.ItemMaking(this,i+1);
					}
				}
			}
			else if(iType.equals("Document"))
			{
				if(gui.getnPages()<=0 || gui.getLgt()<=0 || gui.getWid()<=0)
					checker = false;
				if(!checker)
					JOptionPane.showMessageDialog(null,"Invalid input. Please input positive numbers only.");
				else
				{
					items[i] = new ItemDocument(gui.getnPages(),gui.getLgt(),gui.getWid());
					//Check purposes
					System.out.println(items[i].getLength() + " " + items[i].getWidth() + " " + items[i].getHeight() + " " + items[i].getWeight() + " " + ((ItemDocument)items[i]).getPages());
					i++;
					if(i == nItems)
					{
						//Proceed to next panel
						JOptionPane.showMessageDialog(null, "All items have been set.");
						gui.remove(gui.getiPanel());
						gui.Insure(this);
						scene = 4;
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Item has been set, proceed to the next item.");
						gui.remove(gui.getiPanel());
						gui.ItemMaking(this,i+1);
					}
				}
			}
		}
		
		else if(e.getActionCommand().equals("Yes"))
		{
			isInsured = true;
			
			gui.remove(gui.getrPanel());
			Parcel parcel = new Parcel(items,nItems);
			int i;
			boolean valid = false;
			boolean[] sizes = new boolean[6];
			sizes = parcel.getValidSizes();
			for(i=0;i<6;i++)
			{
				if(sizes[i])
					valid = true;
			}
			if(valid)
			{
				JOptionPane.showMessageDialog(null,"Select parcel size.");
				gui.createParcel(this,parcel.getValidSizes());
				scene = 5;
			}
			else
			{
				JOptionPane.showMessageDialog(null,"Sorry your items does not fit in any of our parcels.");
				gui.menu();
				gui.addListeners(this);
				scene = 1;
			}
		}
		
		else if(e.getActionCommand().equals("No"))
		{
			isInsured = false;
			
			gui.remove(gui.getrPanel());
			Parcel parcel = new Parcel(items,nItems);
			int i;
			boolean valid = false;
			boolean[] sizes = new boolean[6];
			sizes = parcel.getValidSizes();
			for(i=0;i<6;i++)
			{
				if(sizes[i])
					valid = true;
			}
			if(valid)
			{
				JOptionPane.showMessageDialog(null,"Select parcel size.");
				gui.createParcel(this,parcel.getValidSizes());
				scene = 5;
			}
			else
			{
				JOptionPane.showMessageDialog(null,"Sorry your items does not fit in any of our parcels.");
				gui.menu();
				gui.addListeners(this);
				scene = 1;
			}
		}
		
		else if(e.getActionCommand().equals(SIZESNAMES[0]))
		{
			Parcel parcel = new Parcel(items, nItems, customer, 1, tc.getDate(), isInsured,0);
			parcelList.add(parcel);
			nParcels++;
			JOptionPane.showMessageDialog(null,"Proceed to checkout.");
			gui.remove(gui.getpPanel());
			String content;
			content = new String("Recipient: " + (parcelList.get(nParcels-1).getRecipient().getRecipientName())+ "\nParcel Tracking number: " + (parcelList.get(nParcels-1).getTracking()).getTrackingNumber() + "\n" + parcelList.get(nParcels-1).getPriceBreakdown());
			gui.GenTracking(this,content);
			
			scene = 6;
		}
		
		else if(e.getActionCommand().equals(SIZESNAMES[1]))
		{
			Parcel parcel = new Parcel(items, nItems, customer, 1, tc.getDate(), isInsured,1);
			parcelList.add(parcel);
			nParcels++;
			JOptionPane.showMessageDialog(null,"Proceed to checkout.");
			gui.remove(gui.getpPanel());
			String content;
			content = new String("Recipient: " + (parcelList.get(nParcels-1).getRecipient().getRecipientName())+ "\nParcel Tracking number: " + (parcelList.get(nParcels-1).getTracking()).getTrackingNumber() + "\n" + parcelList.get(nParcels-1).getPriceBreakdown());
			gui.GenTracking(this,content);
			
			scene = 6;
		}
		else if(e.getActionCommand().equals(SIZESNAMES[2]))
		{
			Parcel parcel = new Parcel(items, nItems, customer, 1, tc.getDate(), isInsured,2);
			parcelList.add(parcel);
			nParcels++;
			JOptionPane.showMessageDialog(null,"Proceed to checkout.");
			gui.remove(gui.getpPanel());
			String content;
			content = new String("Recipient: " + (parcelList.get(nParcels-1).getRecipient().getRecipientName())+ "\nParcel Tracking number: " + (parcelList.get(nParcels-1).getTracking()).getTrackingNumber() + "\n" + parcelList.get(nParcels-1).getPriceBreakdown());
			gui.GenTracking(this,content);
			
			scene = 6;
		}
		else if(e.getActionCommand().equals(SIZESNAMES[3]))
		{
			Parcel parcel = new Parcel(items, nItems, customer, 1, tc.getDate(), isInsured,3);
			parcelList.add(parcel);
			nParcels++;
			JOptionPane.showMessageDialog(null,"Proceed to checkout.");
			gui.remove(gui.getpPanel());
			String content;
			content = new String("Recipient: " + (parcelList.get(nParcels-1).getRecipient().getRecipientName())+ "\nParcel Tracking number: " + (parcelList.get(nParcels-1).getTracking()).getTrackingNumber() + "\n" + parcelList.get(nParcels-1).getPriceBreakdown());
			gui.GenTracking(this,content);
			
			scene = 6;
		}
		else if(e.getActionCommand().equals(SIZESNAMES[4]))
		{
			Parcel parcel = new Parcel(items, nItems, customer, 1, tc.getDate(), isInsured,4);
			parcelList.add(parcel);
			nParcels++;
			JOptionPane.showMessageDialog(null,"Proceed to checkout.");
			gui.remove(gui.getpPanel());
			String content;
			content = new String("Recipient: " + (parcelList.get(nParcels-1).getRecipient().getRecipientName())+ "\nParcel Tracking number: " + (parcelList.get(nParcels-1).getTracking()).getTrackingNumber() + "\n" + parcelList.get(nParcels-1).getPriceBreakdown());
			gui.GenTracking(this,content);
			
			scene = 6;
		}
		
		else if(e.getActionCommand().equals(SIZESNAMES[5]))
		{
			Parcel parcel = new Parcel(items, nItems, customer, 1, tc.getDate(), isInsured,5);
			parcelList.add(parcel);
			nParcels++;
			JOptionPane.showMessageDialog(null,"Proceed to checkout.");
			gui.remove(gui.getpPanel());
			String content;
			content = new String("Recipient: " + (parcelList.get(nParcels-1).getRecipient().getRecipientName())+ "\nParcel Tracking number: " + (parcelList.get(nParcels-1).getTracking()).getTrackingNumber() + "\n" + parcelList.get(nParcels-1).getPriceBreakdown());
			gui.GenTracking(this,content);
			
			scene = 6;
		}
		
		//Cancel
		else if(e.getActionCommand().equals("Cancel"))
		{
			if(scene == 3)
			{
				//ItemMaking panel
				gui.remove(gui.getiPanel());
				gui.menu();
				gui.addListeners(this);
				scene = 1;
			}
			else if(scene == 4)
			{
				//Insurance Panel
				gui.remove(gui.getrPanel());
				gui.menu();
				gui.addListeners(this);
				scene = 1;
			}
			else if(scene == 5)
			{
				//Creating Parcel
				gui.remove(gui.getpPanel());
				gui.menu();
				gui.addListeners(this);
				scene = 1;
			}
			
			else if(scene == 6)
			{
				//Tracking Panel
				gui.remove(gui.gettPanel());
				gui.menu();
				gui.addListeners(this);
				scene = 1;
			}
			
			else if(scene == 7)
			{
				//Exit panel
				gui.remove(gui.getePanel());
				gui.menu();
				gui.addListeners(this);
				scene = 1;
			}
		}
		
		//Done
		else if(e.getActionCommand().equals("Done"))
		{
			gui.remove(gui.gettPanel());
			gui.menu();
			gui.addListeners(this);
			scene = 1;
		}
		
		//Tracking Panel
		else if(e.getActionCommand().equals("Track Parcels"))
		{
			gui.remove(gui.getmPanel());
			gui.trackParcel(this);
			gui.addListeners(this);
			scene = 6;
		}
		
			else if(e.getActionCommand().equals("Track"))
		{
			int i;
			boolean valid = false;
			
			for (i = 0; valid == false && i < parcelList.size(); i++)
			{
				
				if (gui.getTrackingInput().equals(parcelList.get(i).getTracking().getTrackingNumber()))
				{
					valid = true;
				}
			}
			
			if (valid)
			{
				 parcelList.get(i - 1).getTracking().setStatus(tc.getDate());
				JOptionPane.showMessageDialog(null,"Delivery Status for parcel " + parcelList.get(i - 1).getTracking().getTrackingNumber() +
													": " + parcelList.get(i - 1).getTracking().getStatus());
			}
			else
				JOptionPane.showMessageDialog(null,"Sorry, we could not find a parcel corresponding to your tracking number.");			
			
			scene = 6;
		}
		
		/*
		
			if(valid)
			{
				JOptionPane.showMessageDialog(null,"Select parcel size!");
				gui.createParcel(this,parcel.getValidSizes());
				scene = 5;
			}
			else
			{
				JOptionPane.showMessageDialog(null,"Sorry your items does not fit in any of our parcels.");
				gui.menu();
				gui.addListeners(this);
				scene = 1;
			}
		
		*/
		
		
		//Exit
		else if(e.getActionCommand().equals("Exit"))
		{
			gui.remove(gui.getmPanel());
			gui.Exit_Program(this);
			scene = 7;
		}
		
		else if(e.getActionCommand().equals("Confirm"))
		{
			checker = true;
			char[] pass = {'a','b','c','1','2','3'};
			int x;
			for(x=0;x<6;x++)
			{
				if(gui.getPassword()[x] != pass[x])
					checker = false;
			}
			if(checker)
			{
				timer.stop();
				System.exit(0);
			}
			else
			{
				JOptionPane.showMessageDialog(null,"Incorrect Password!");
				gui.remove(gui.getePanel());
				gui.menu();
				gui.addListeners(this);
			}
		}
		
	}
	
	public class TimeClass implements ActionListener
	{
		private int counter;
		private LocalDate currentDate;
		
		public TimeClass ()
		{
			currentDate = LocalDate.now();
			counter = 0;
		}
		
		public void actionPerformed(ActionEvent tc)
		{
			counter++;
			if (counter % 10 == 0)
			{
				counter = 0;
				currentDate = currentDate.plusDays(1);
			}
			gui.getDayLabel().setText("Date: " + currentDate.getMonthValue() + " / " + currentDate.getDayOfMonth() + " / " + currentDate.getYear());
			gui.getTimerLabel().setText("Time: " + counter);
		}
		
		public LocalDate getDate()
		{
			return currentDate;
		}
	}
}