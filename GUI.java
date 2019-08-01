/**
	This is the platform for the view of the program for the
	Johnny Moves. The Graphics User Interface of it.
	
	@author Anjelo Antioquia, Anton Sta. Ana
	@version 1.0
*/
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.*;


public class GUI extends JFrame
{
	//Attributes
	private int t1,t2; //tokens
	
	//Components	
	private ActionListener listener;
	private JButton btnCreate,btnTrack,btnExit,btnNext,btnPrev;
	private JButton btnProd,btnDoc,btnIrreg,btnSet,btnCfm,btnYes,btnNo,btnCancel,btnDone;
	private JComboBox<String> cbRegions;
	private JLabel timerLabel, dayLabel;
	private JLabel lblFrame, lblLength,lblWidth,lblHeight,lblWeight,lblnPages, blankLabel;
	private JPanel mPanel, bPanel, cPanel,iPanel,ePanel,rPanel,tPanel, pPanel;
	private JPasswordField password;
	private JTextArea taContent;
	private JTextField tfField,nItems, trField;
	private JTextField tfnPages, tfLength,tfWidth,tfHeight,tfWeight;
	
	//Background and color
	private JLabel background;
	private Color highColor = new Color(243, 243, 251); //Light Gray
	private Color midColor = new Color(234, 235, 240); //Mid Gray
	private Color darkColor = new Color(221, 223, 235); //Dark Gray
	private Color greenColor = new Color(84, 194, 139); //Green
	private Color lGreenColor = new Color(219, 237, 237); //Light Green
	private Color redColor = new Color(230, 95, 91); //Red
	private Color lRedColor = new Color(243, 218, 224); //Light Red
	private Color blueColor = new Color (71, 200, 232); //Blue

	//Parcel packages
	private JButton[] btnSizes;
	//private JButton btnPouchS,btnPouchM, btnBoxS,btnBoxM,btnBoxL,btnBoxXL;	
	//private String[] SIZESNAMES = {"Pouch (S)", "Pouch (M)", "Box (S)", "Box (M)", "Box (L)", "Box (XL)"}; 
	
	//Constructor
	public GUI()
	{
		super("Johnny Moves 2.0");
		setSize(800,500);
		
		//UI Manager		
		UIManager.put("Button.background", blueColor);
		UIManager.put("Button.foreground", Color.WHITE);
		UIManager.put("Button.select", darkColor);
		UIManager.put("Button.margin", new Insets(10, 10, 10, 10));
		//UIManager.put("Button.border", new LineBorder(blueColor, 10));
		UIManager.put("Button.focus", new Color(0, 0, 0, 0));	
		
		UIManager.put("OptionPane.background", highColor);
		
		UIManager.put("Panel.background", highColor);
		
		UIManager.put("TextField.background", highColor);
		UIManager.put("TextField.border", new LineBorder(blueColor, 1));
		
		UIManager.put("TextArea.background", midColor);
		UIManager.put("TextArea.border", new LineBorder(blueColor, 1));
		
		UIManager.put("PasswordField.border", new LineBorder(redColor, 1));
		UIManager.put("PasswordField.background", lRedColor);
		
		UIManager.put("ComboBox.buttonShadow", midColor);
		UIManager.put("ComboBox.Foreground", blueColor);
		UIManager.put("ComboBox.Background", midColor);
		UIManager.put("ComboBox.selectionForeground", Color.WHITE);
		UIManager.put("ComboBox.selectionBackground", blueColor);
		UIManager.put("ComboBox.border", new LineBorder(highColor, 1));
		
		UIManager.getLookAndFeelDefaults().put("Panel.background", highColor);
		
		

		// setLayout(null);
		// ImageIcon img = new ImageIcon("bg2.jpg");
		// background = new JLabel("",img,JLabel.CENTER);
		// background.setLayout(null);
		// background.setBounds(0,0,1520,800);
		// background.setOpaque(true);
		//add(background);
		setLayout(new BorderLayout());
		menu();
		setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		t1 = 0;
		t2 = 0;
	}
	
	//Component Formatters
	public void formatButton (JButton button, Color background, Color foreground)
	{

		//button.setBorder(new LineBorder(background, 10));
		button.setMargin(new Insets(10, 10, 10, 10));
		//button.setSelect(background.darker());
		button.setForeground(foreground);
		button.setBackground(background);
		//button.setPreferredSize(new Dimension(200, 50));
	}
	
	public void formatTextField (JTextField textfield, Color background)
	{
		if (!background.equals(blueColor))
		{
			Border border = new LineBorder(background, 1);
			textfield.setBackground(highColor);
			textfield.setBorder(border);
		}
		
		textfield.setHorizontalAlignment(JTextField.CENTER);
	}
	
	/**
		menu method displays the menu screen of the Johnny moves program for creating a Parcel
	*/
	public void menu()
	{
		//Panels
		mPanel = new JPanel(new BorderLayout()); //Main Panel
		mPanel.setBackground(highColor);
		JPanel subPanel = new JPanel(new GridBagLayout()); //Create and Track buttons
		subPanel.setBackground(highColor);
		JPanel miniPanel = new JPanel(new GridBagLayout()); //Exit button
		miniPanel.setBackground(highColor);
		bPanel = new JPanel(new GridLayout(1, 7, 10, 10)); //Top panel
		bPanel.setBackground(darkColor);
		bPanel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		bPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		//Buttons
		btnCreate = new JButton("Create Transaction");
		btnTrack = new JButton("Track Parcels");
		btnExit = new JButton("Exit");
		formatButton(btnExit, redColor, Color.WHITE);
		
		//GridBagLayout Constraints
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(10,10,10,10);
		
		c.gridx = 0;
		c.gridy = 0;
		
		blankLabel = new JLabel(new ImageIcon("res/jmoves.png"));
		//blankLabel.setPreferredSize(new Dimension(300, 300));
		subPanel.add(blankLabel, c);	
		
		c.gridx = 0;
		c.gridy = 1;
		subPanel.add(btnCreate,c);
		
		c.gridx = 0;
		c.gridy = 2;
		subPanel.add(btnTrack,c);
		
		c.gridx = 0;
		c.gridy = 0;
		miniPanel.add(btnExit,c);
		mPanel.add(miniPanel,BorderLayout.SOUTH);
		
		//lblFrame = new JLabel("<html><font color='white'>Welcome to Johnny Moves</font></html>");
		//lblFrame.setFont(new Font("Times New Roman",Font.PLAIN,50));
		//lblFrame.setOpaque(true);
		//lblFrame.setBackground(Color.BLACK);
		//mPanel.add(lblFrame, BorderLayout.NORTH);
		
		//c.gridx = 2;
		//subPanel.add(timerLabel,c);
		timerLabel = new JLabel("Time: _");
		timerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		bPanel.add(timerLabel);
		
		//c.gridx = 10;
		//subPanel.add(dayLabel,c);	
		dayLabel = new JLabel("Date: __ / __ / ____");
		dayLabel.setHorizontalAlignment(SwingConstants.CENTER);
		bPanel.add(dayLabel);
		
		int i;
		for (i = 0; i < 5; i++)
		{
			bPanel.add(new JLabel());
		}
		
		mPanel.add(bPanel, BorderLayout.NORTH);
		mPanel.add(subPanel,BorderLayout.CENTER);
		// mPanel.add(lblFrame,BorderLayout.NORTH);
		// ImageIcon background=new ImageIcon("bg.jpg");
		// Image img=background.getImage();
		// Image temp=img.getScaledInstance(700,500,Image.SCALE_SMOOTH);
		// background=new ImageIcon(temp);
		// JLabel back=new JLabel(background);
		// back.setLayout(null);
		// back.setBounds(0,0,700,500);
		// mPanel.add(back);
		//add(mPanel, BorderLayout.CENTER);
	
		getContentPane().add(mPanel,BorderLayout.CENTER);
		revalidate();
		repaint();
	}
	
	/**
		CreateTrans method is the view after the user clicks on the button "Create Transaction"
		It will ask for the name, number of items, and the selected delivery location.
		
		@param listener an EventListener to enable the ComboBox and JButtons to intake user actions.
	*/
	public void CreateTrans(EventListener listener)
	{
		
		remove(mPanel);
		
		
		String[] region = {"<Select Delivery Location>","Manila","Luzon","Visayas","Mindanao"};
		
		cPanel = new JPanel(new GridBagLayout());
		cPanel.setBackground(highColor);
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(10,10,10,10);
		
		c.gridx = 0;
		c.gridy = 0;
		lblFrame = new JLabel("Enter Name:");
		cPanel.add(lblFrame,c);
		
		c.gridx = 1;
		c.gridy = 0;
		tfField = new JTextField(15);
		formatTextField(tfField, blueColor);
		tfField.setText("");
		cPanel.add(tfField,c);
		
		c.gridx = 0;
		c.gridy = 1;
		lblFrame = new JLabel("Enter number of items: ");
		cPanel.add(lblFrame,c);
		
		c.gridx = 1;
		c.gridy = 1;	
		nItems = new JTextField(5);
		formatTextField(nItems, blueColor);
		nItems.setText("");
		cPanel.add(nItems,c);
		
		lblFrame = new JLabel("Choose delivery location");
		c.gridx = 0;
		c.gridy = 2;
		cPanel.add(lblFrame,c);

		cbRegions = new JComboBox<>(region);
		cbRegions.setSelectedIndex(0);
		cbRegions.setBackground(blueColor);
		cbRegions.setForeground(highColor);
		
		c.gridx = 1;
		c.gridy = 2;
		cPanel.add(cbRegions,c);
		
		btnNext = new JButton("Next");
		formatButton(btnNext, greenColor, Color.WHITE);
		btnPrev = new JButton("Back");
		formatButton(btnPrev, darkColor, Color.WHITE);
		
		btnNext.addActionListener((ActionListener)listener);
		btnPrev.addActionListener((ActionListener)listener);
		
		c.gridx = 0;
		c.gridy = 7;
		cPanel.add(btnPrev,c);
		
		c.gridx = 1;
		c.gridy = 7;
		cPanel.add(btnNext,c);
		
		add(cPanel, BorderLayout.CENTER);
		
		revalidate();
		repaint();
	}
	
	/**
		The ItemMaking method is the view after the user has given the needed information to start
		creating the number of objects that he or she wishes to place in the parcel.
		This view will ask for the user what type of object would be placed inside the parcel,
		the dimensions of the object and if the type is a "Document" it will ask for the number of pages.
		
		@param listener an ActionListener to enable the JButtons to intake user actions.
		@param i an integer data type that serves as the counter for the number of objects the would be created.
	*/
	public void ItemMaking(ActionListener listener,int i)
	{
		iPanel = new JPanel(new GridBagLayout());
		iPanel.setBackground(highColor);
		getContentPane().add(iPanel,BorderLayout.CENTER);
		getContentPane().setBackground(highColor);
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(10,10,10,10);
		
		c.gridx = 0;
		c.gridy = 0;
		lblFrame = new JLabel("Item: " + i);
		iPanel.add(lblFrame,c);
		
		c.gridx = 0;
		c.gridy = 2;
		lblFrame = new JLabel("Please choose the type of product.");
		iPanel.add(lblFrame,c);
		
		//Regular Product Button
		c.gridx = 1;
		c.gridy = 2;
		btnProd = new JButton("Regular Product");
		btnProd.addActionListener(listener);
		iPanel.add(btnProd,c);
		
		//Document Button
		c.gridx = 2;
		c.gridy = 2;
		btnDoc = new JButton("Document");
		btnDoc.addActionListener(listener);
		iPanel.add(btnDoc,c);
		
		//Irregular Product Button
		c.gridx = 3;
		c.gridy = 2;
		btnIrreg = new JButton("Irregular Product");
		btnIrreg.addActionListener(listener);
		iPanel.add(btnIrreg,c);
		
		//Cancel Button
		c.gridx = 0;
		c.gridy = 10;
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(listener);
		formatButton(btnCancel, redColor, Color.WHITE);
		iPanel.add(btnCancel,c);
		
		revalidate();
		repaint();
	}
	
	/**
		The DocTextFields method is called to display JTextFields that correspond to user input 
		regarding the placement of a document object inside the parcel.
		
		@param listener an ActionListener to enable the JButtons to intake user actions.
		@param panel a JPanel that was used in the ItemMaking method to be added with the JTextFields.
	*/
	
	public void DocTextFields(ActionListener listener,JPanel panel)
	{
		if(t1 == 2)
		{
			panel.remove(lblLength);
			panel.remove(lblWidth);
			panel.remove(lblHeight);
			panel.remove(lblWeight);
			panel.remove(tfLength);
			panel.remove(tfWidth);
			panel.remove(tfHeight);
			panel.remove(tfWeight);
			panel.remove(btnSet);
		}
		else if(t1 == 1)
		{
			panel.remove(lblLength);
			panel.remove(lblWidth);
			panel.remove(lblnPages);
			panel.remove(tfLength);
			panel.remove(tfWidth);
			panel.remove(tfnPages);
			panel.remove(btnSet);
		}
		
		t1 = 1;
		getContentPane().add(iPanel,BorderLayout.CENTER);
		lblnPages = new JLabel("Enter number of Pages: ");
		tfnPages = new JTextField(5);
		formatTextField(tfnPages, blueColor);
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(10,10,10,10);
		c.gridx = 0;
		c.gridy = 3;
		
		panel.add(lblnPages,c);
		
		c.gridx = 1;
		c.gridy = 3;
		panel.add(tfnPages,c);
		
		tfLength = new JTextField(5);
		formatTextField(tfLength, blueColor);
		tfWidth = new JTextField(5);
		formatTextField(tfWidth, blueColor);
		
		lblLength = new JLabel("Enter length in inches:");
		c.gridx = 0;
		c.gridy = 4;
		panel.add(lblLength,c);
		
		c.gridx = 1;
		c.gridy = 4;
		panel.add(tfLength,c);
		
		c.gridx = 0;
		c.gridy = 5;
		lblWidth = new JLabel("Enter width in inches:");
		panel.add(lblWidth,c);
		c.gridx = 1;
		panel.add(tfWidth,c);
		
		btnSet = new JButton("Set");
		btnSet.addActionListener(listener);
		
		c.gridy = 6;
		
		panel.add(btnSet,c);
		
		revalidate();
		repaint();
	}
	
	/**
		The ProdTextField method is called to display JTextFields is called to display JTextFields
		that correspond to user input regarding length,width,height,and weight, as required by both
		Regular Product and Irregular Product objects.
		
		@param listener an ActionListener to enable the JButtons to intake user actions.
		@param panel a JPanel that was used in the ItemMaking method to be added with the JTextFields.
	*/
	public void ProdTextField(ActionListener listener, JPanel panel)
	{
		if(t1==1)
		{
			panel.remove(lblLength);
			panel.remove(lblWidth);
			panel.remove(lblnPages);
			panel.remove(tfLength);
			panel.remove(tfWidth);
			panel.remove(tfnPages);
			panel.remove(btnSet);
		}
		else if(t1 ==2)
		{
			panel.remove(lblLength);
			panel.remove(lblWidth);
			panel.remove(lblHeight);
			panel.remove(lblWeight);
			panel.remove(tfLength);
			panel.remove(tfWidth);
			panel.remove(tfHeight);
			panel.remove(tfWeight);
			panel.remove(btnSet);
		}
		
		t1 = 2;
		getContentPane().add(iPanel,BorderLayout.CENTER);
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(10,10,10,10);
		tfLength = new JTextField(5);
		tfWidth = new JTextField(5);
		tfHeight = new JTextField(5);
		tfWeight = new JTextField(5);
		formatTextField(tfLength, blueColor);
		formatTextField(tfWidth, blueColor);
		formatTextField(tfHeight, blueColor);
		formatTextField(tfWeight, blueColor);
		
		lblLength = new JLabel("Enter length in inches:");
		c.gridx = 0;
		c.gridy = 3;
		panel.add(lblLength,c);
		
		c.gridx = 1;
		c.gridy = 3;
		panel.add(tfLength,c);
		
		lblWidth = new JLabel("Enter width in inches:");
		c.gridx = 0;
		c.gridy = 4;
		panel.add(lblWidth,c);
		
		c.gridx = 1;
		c.gridy = 4;
		panel.add(tfWidth,c);
		
		lblHeight = new JLabel("Enter height in inches:");
		c.gridx = 0;
		c.gridy = 5;
		panel.add(lblHeight,c);
		
		c.gridx = 1;
		panel.add(tfHeight,c);
		
		lblWeight = new JLabel("Enter weight in kilograms:");
		c.gridx = 0;
		c.gridy = 6;
		panel.add(lblWeight,c);
		
		c.gridx = 1;
		panel.add(tfWeight,c);
		
		btnSet = new JButton("Set");
		btnSet.addActionListener(listener);
		
		c.gridy = 7;
		
		panel.add(btnSet,c);
		
		revalidate();
		repaint();
		
	}
	
	/**
		The Insure method is next view directly after the user has added all of the items into the
		parcel. Here the user will be prompted on whether they want to insure the parcel or not.
		
		@param listener an ActionListener to enable the JButtons to intake user actions.
	*/
	public void Insure(ActionListener listener)
	{
		rPanel = new JPanel(new BorderLayout());
		rPanel.setBackground(highColor);
		JPanel subPanel = new JPanel(new FlowLayout());
		subPanel.setBackground(highColor);
		JPanel ttempPanel = new JPanel(new FlowLayout());
		ttempPanel.setBackground(highColor);
		JPanel tempPanel = new JPanel(new GridBagLayout());
		tempPanel.setBackground(highColor);
		getContentPane().add(rPanel,BorderLayout.CENTER);
		getContentPane().setBackground(highColor);
		
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(10,10,10,10);
		
		c.gridx =0;
		c.gridy = 0;
		lblFrame = new JLabel("Do you want to insure the parcel?");
		subPanel.add(lblFrame);
		rPanel.add(subPanel,BorderLayout.NORTH);
		
		//Yes Button
		btnYes = new JButton("Yes");
		btnYes.addActionListener(listener);
		tempPanel.add(btnYes,c);
		
		//No Button
		c.gridx = 1;
		btnNo = new JButton("No");
		btnNo.addActionListener(listener);
		tempPanel.add(btnNo,c);
		
		//Cancel Button
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(listener);
		formatButton(btnCancel, redColor, Color.WHITE);
		ttempPanel.add(btnCancel);
		
		rPanel.add(tempPanel,BorderLayout.CENTER);
		rPanel.add(ttempPanel,BorderLayout.SOUTH);
		
		revalidate();
		repaint();
	}
	
	/**
		createParcel is the view where the user will choose the size of the parcel
		
		@param listener an ActionListener to enable buttons to intake user actions.
		@param validSizes an array of boolean where it shows which parcels are valid
	*/
public void createParcel(ActionListener listener, boolean[] validSizes)
	{
		int i;
		Icon icon;
		String[] SIZESNAMES = {"Pouch (S)", "Pouch (M)", "Box (S)", "Box (M)", "Box (L)", "Box (XL)"}; 
		ArrayList <JButton> btnSizes = new ArrayList <JButton> ();
		
		for (i = 0; i < 6;i++)
		{
			icon = new ImageIcon("res/btn" + i + ".png");
			
			btnSizes.add(new JButton(SIZESNAMES[i], icon));
			
			btnSizes.get(i).addActionListener(listener);
			btnSizes.get(i).setActionCommand(SIZESNAMES[i]);
			btnSizes.get(i).setVerticalTextPosition(JButton.TOP);
			btnSizes.get(i).setHorizontalTextPosition(JButton.CENTER);
			formatButton(btnSizes.get(i), highColor, Color.BLACK);
		}
		
		pPanel = new JPanel(new GridBagLayout());
		getContentPane().add(pPanel,BorderLayout.CENTER);
		GridBagConstraints c = new GridBagConstraints();
		
		c.insets = new Insets(10,10,10,10);
		c.gridy = 0;
		c.gridx = 0;
		
		for(i=0;i<6;i++)
		{
			if(!validSizes[i])
				btnSizes.get(i).setEnabled(false);
			
			btnSizes.get(i).setFocusPainted(false);	
			pPanel.add(btnSizes.get(i), c);
			c.gridx++;
		}
		
		c.gridx = 0;
		c.gridy= 10;
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(listener);
		pPanel.add(btnCancel,c);
		
		revalidate();
		repaint();
		
	}
	
	/**
		GenTracking is the view where are the information needed to track the parcel will be displayed
		
		@param listener an ActionListener to enable buttons to intake user actions.
		@String content of the panel
	*/
	
	public void GenTracking(ActionListener listener,String content)
	{
		tPanel = new JPanel(new GridBagLayout());
		tPanel.setBackground(highColor);
		getContentPane().add(tPanel,BorderLayout.CENTER);
		//Border border = BorderFactory.createLineBorder(Color.BLUE, 8);
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(10,10,10,10);
		c.gridx = 0;
		c.gridy =0;
		//Display Price breakdown
		taContent = new JTextArea(content);
		//taContent.setBorder(border);
		taContent.setFont(new Font("Times New Roman", Font.BOLD, 16));
		taContent.setBackground(highColor);
		taContent.setPreferredSize(new Dimension(350, 250));
		taContent.setEditable(false);
		tPanel.add(taContent);
		btnDone = new JButton("Done");
		btnDone.addActionListener(listener);
		
		//btnDone.setPreferredSize(new Dimension(75,50));
		c.gridy = 1;
		tPanel.add(btnDone,c);
		
		//generate the Tracking number
		
		revalidate();
		repaint();
	}
	
	public void trackParcel(ActionListener listener)
	{
		tPanel = new JPanel(new GridBagLayout());
		getContentPane().add(tPanel,BorderLayout.CENTER);
		lblFrame = new JLabel("Enter Tracking Number: ");
		trField = new JTextField(15);
		formatTextField(trField, blueColor);
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(10,10,10,10);
		c.gridx =0 ;
		c.gridy =0 ;
		
		tPanel.add(lblFrame,c);
		c.gridx = 1;
		tPanel.add(trField,c);
		
		c.gridy = 1;
		btnCfm = new JButton("Track");
		btnCfm.addActionListener(listener);
		tPanel.add(btnCfm,c);
		
		c.gridx = 0;
		c.gridy= 1;
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(listener);
		tPanel.add(btnCancel,c);
		
		revalidate();
		repaint();
		
	}
	
	/**
		Exit_Program method is the view if the user wishes to exit the program.
		This will display a PasswordField that will intake the password to exit the program.
		Only the admins knows the password.
		
		@param listener an ActionListener to enable the JButtons to intake user actions.
	*/
	public void Exit_Program(ActionListener listener)
	{
		ePanel = new JPanel(new GridBagLayout());
		getContentPane().add(ePanel,BorderLayout.CENTER);
		lblFrame = new JLabel("Enter Password: ");
		password = new JPasswordField(15);
		password.setEchoChar('\u25CF');
		
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(10,10,10,10);
		c.gridx =0 ;
		c.gridy =0 ;
		
		ePanel.add(lblFrame,c);
		c.gridx = 1;
		ePanel.add(password,c);
		
		c.gridy = 1;
		btnCfm = new JButton("Confirm");
		btnCfm.addActionListener(listener);
		ePanel.add(btnCfm,c);
		
		c.gridx = 0;
		c.gridy= 1;
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(listener);
		ePanel.add(btnCancel,c);
		
		revalidate();
		repaint();
		
	}
	
	/**
		getName is a String method that returns the recipient name that was typed into the JTextField tfField.
		
		@return String the name that was typed into the textfield.
	*/
	
	public String getName()
	{
		return tfField.getText();
	}
	
	/**
		getLgt is a double method that returns the length dimension of the item.
		
		
		@return the length of the item.
	*/
	public double getLgt()
	{
		double temp = -1;
		try
		{
			if (Double.parseDouble(tfLength.getText()) > 0)
				temp = Double.parseDouble(tfLength.getText());
		}
		catch (NumberFormatException e)
		{
			//System.out.print("Parsing Error");
		}
		
		return temp;
	}
	
	/**
		getWid is a double method that returns the width dimension of the item.
		
		@return the width of the item.
	*/
	public double getWid()
	{
		double temp = -1;
		try
		{
			if (Double.parseDouble(tfWidth.getText()) > 0)
				temp = Double.parseDouble(tfWidth.getText());
		}
		catch (NumberFormatException e)
		{
			//System.out.print("Parsing Error");
		}
		
		return temp;
	}
	
	/**
		getHgt is a double method that returns the height dimension of the item.
		
		@return the height of the item.
	*/
	public double getHgt()
	{
		double temp = -1;
		try
		{
			if (Double.parseDouble(tfHeight.getText()) > 0)
				temp = Double.parseDouble(tfHeight.getText());
		}
		catch (NumberFormatException e)
		{
			//System.out.print("Parsing Error");
		}
		
		return temp;
	}
	
	/**
		getWgt is a double method that returns the weight dimension of the item.
		
		@return the weight of the item.
	*/
	public double getWgt()
	{
		double temp = -1;
		try
		{
			if (Double.parseDouble(tfWeight.getText()) > 0)
				temp = Double.parseDouble(tfWeight.getText());
		}
		catch (NumberFormatException e)
		{
			//System.out.print("Parsing Error");
		}
		
		return temp;
	}
	
	/**
		getnPages is a int method that returns the number of pages of the document.
		
		@return the number of pages.
	*/
	public int getnPages()
	{
		int temp = -1;
		try
		{
			if (Integer.parseInt(tfnPages.getText()) > 0)
				temp = Integer.parseInt(tfnPages.getText());
		}
		catch (NumberFormatException e)
		{
			//System.out.print("Parsing Error");
		}
		
		return temp;
	}
		
	/**
		getnItems is a int method that returns the number of items that the user
		would place inside the parcel.
		
		@return the number of items to be placed inside the parcel.
	*/
	public int getnItems()
	{
		int temp = -1;
		try
		{
			if (nItems.getText().length() > 0)
				temp = Integer.parseInt(nItems.getText());
		}
		catch (NumberFormatException e)
		{
			//System.out.print("Parsing Error");
		}
		
		return temp;
	}
	
	/**
		getPassword is a char[] method that returns the input of the user in the
		PasswordField in a character array.
		
		@return the input of the user.
	*/
	public char[] getPassword()
	{
		return password.getPassword();
	}
	
	/**
		getSelectedItem is a String method that returns the selected item in the 
		JComboBox used for choosing the region to where the parcel will be delivered.
		
		@return the chosen item in the JComboBox
	*/
	public String getSelectedItem()
	{
		return (String) cbRegions.getSelectedItem();
	}
	
	/**
		getTrackingInput is a String method that returns the selected tracking number
		inputted by the user.
	
		@return the parcel tracking number inputted by the user.
	*/
	public String getTrackingInput()
	{
		return trField.getText();
	}	
	
	/**
		getDayLabel
	*/
	
	public JLabel getDayLabel()
	{
		return dayLabel;
	}
	
	/**
		getTimerLabel
	*/
	public JLabel getTimerLabel()
	{
		return timerLabel;
	}
	
	/**
		getePanel is a JPanel method that returns the Exit JPanel.
		
		@return ePanel the exit JPanel.
	*/
	public JPanel getePanel()
	{
		return ePanel;
	}
	
	/**
		getcPanel is a JPanel method that returns the Create Transaction JPanel
		
		@return cPanel the Create Transaction JPanel.
	*/
	public JPanel getcPanel()
	{
		return cPanel;
	}
	
	/**
		getiPanel is a JPanel method that returns the Item Making JPanel
		
		@return iPanel the Item Making JPanel.
	*/
	public JPanel getiPanel()
	{
		return iPanel;
	}
	
	/**
		getmPanel is a JPanel method that returns the Menu JPanel
		
		@return mPanel the Menu panel.
	*/
	public JPanel getmPanel()
	{
		return mPanel;
	}
	
	/**
		getrPanel is a JPanel method that returns the Insurance checker JPanel
		
		@return rPanel the Insurance checker Panel.
	*/
	public JPanel getrPanel()
	{
		return rPanel;
	}
	
	/**
		gettPanel is a JPanel method that returns the Tracking JPanel
		
		@return tPanel the Tracking Panel.
	*/
	
	public JPanel gettPanel()
	{
		return tPanel; 	
	}
	
	/**
		gettPanel is a JPanel method that returns the Parcel JPanel
		
		@return tPanel the Parcel Panel.
	*/
	
	public JPanel getpPanel()
	{
		return pPanel;
	}
	
	/**
		addListeners is a void method that sets the initial menu buttons to have an ActionListener.
		
		@param listener an ActionListener to enable the JButtons to intake user actions.
	*/
	public void addListeners(ActionListener listener)
	{
		btnCreate.addActionListener(listener);
		btnTrack.addActionListener(listener);
		btnExit.addActionListener(listener);
	}
}