import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JLabel;

//import Enums.Base;

/**
This frame contains a panel that displays buttons
for a calculator and a panel with a text fields to
specify the result of calculation.
*/
public class CalculatorFrame extends JFrame
{
	private JLabel display;
	private JPanel buttonPanel;
	private JCheckBox radianCheckBox;
	private JComboBox mathOpCombo;
	private JButton mathOpButton;
	
	private double lastValue;
	private String lastOperator;
	private boolean startNewValue;
	private boolean reset = true;
	
	private static Enums.Base base = Enums.Base.Decimal;
	
	private static final int NUM_COL_WIDTH = 3;
	private static final int FRAME_WIDTH = 400;
	private static final int FRAME_HEIGHT = 300;
	
	public CalculatorFrame()
	{
		PaintScreen();
	}
	
	public void PaintScreen(){
		//removeAll();
		createMenu();
		createButtonPanel();
		changeLayout();
		createControlPanel();
		display = new JLabel("0");
		add(display, BorderLayout.NORTH);
		
		if (reset)reset();
		
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
	}
	
	public void reset(){
		display.setText("0");
		lastValue = 0;
		lastOperator = "=";
		startNewValue = true;
	}
	
	/**
	Creates the control panel with the text field
	and buttons on the frame.
	*/
	private void createMenu(){
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		ButtonGroup bases = new ButtonGroup();
		JMenu menuFile = new JMenu("File");
		JMenu menuOptions = new JMenu("Options");
		JMenu menuHelp = new JMenu("Help");
		menuBar.add(menuFile);
		menuBar.add(menuOptions);
		menuBar.add(menuHelp);
		
		JMenuItem menuItemFileReset = new JMenuItem("Reset");
		JMenuItem menuItemFileExit = new JMenuItem("Exit");
		JRadioButtonMenuItem menuItemOptionsBase16 = new JRadioButtonMenuItem("Base 16");
		JRadioButtonMenuItem menuItemOptionsBase10 = new JRadioButtonMenuItem("Base 10");
		JRadioButtonMenuItem menuItemOptionsBase8 = new JRadioButtonMenuItem("Base 8");
		JRadioButtonMenuItem menuItemOptionsBase2 = new JRadioButtonMenuItem("Base 2");
		JMenuItem menuItemHelpAbout = new JMenuItem("About");
		
		menuItemOptionsBase10.setSelected(true);
		bases.add(menuItemOptionsBase16);
		bases.add(menuItemOptionsBase10);
		bases.add(menuItemOptionsBase8);
		bases.add(menuItemOptionsBase2);
		
		menuFile.add(menuItemFileReset);
		menuFile.add(menuItemFileExit);
		menuOptions.add(menuItemOptionsBase16);
		menuOptions.add(menuItemOptionsBase10);
		menuOptions.add(menuItemOptionsBase8);
		menuOptions.add(menuItemOptionsBase2);
		menuHelp.add(menuItemHelpAbout);
		
		ActionListener listener = new MenuItemResetListener();
		menuItemFileReset.addActionListener(listener);
		listener = new MenuItemExitListener();
		menuItemFileExit.addActionListener(listener);
		listener = new MenuItemExitListener();
		menuItemFileExit.addActionListener(listener);
		listener = new MenuItemBaseListener(Enums.Base.Hex);
		menuItemOptionsBase16.addActionListener(listener);
		listener = new MenuItemBaseListener(Enums.Base.Decimal);
		menuItemOptionsBase10.addActionListener(listener);
		listener = new MenuItemBaseListener(Enums.Base.Octal);
		menuItemOptionsBase8.addActionListener(listener);
		listener = new MenuItemBaseListener(Enums.Base.Binary);
		menuItemOptionsBase2.addActionListener(listener);
		listener = new MenuItemAboutListener();
		menuItemHelpAbout.addActionListener(listener);
		
		
	}
	
	private void createButtonPanel()
	{
		
		JPanel buttonPanelB = new JPanel();
		JPanel buttonPanelO = new JPanel();
		JPanel buttonPanelD = new JPanel();
		JPanel buttonPanelH = new JPanel();
		JPanel buttonPanelOp = new JPanel();
		
		buttonPanelB.setLayout(new GridLayout(Enums.Base.Binary.rows(NUM_COL_WIDTH), NUM_COL_WIDTH));
		buttonPanelO.setLayout(new GridLayout(Enums.Base.Octal.rows(NUM_COL_WIDTH), NUM_COL_WIDTH));
		buttonPanelD.setLayout(new GridLayout(Enums.Base.Decimal.rows(NUM_COL_WIDTH), NUM_COL_WIDTH));
		buttonPanelH.setLayout(new GridLayout(Enums.Base.Hex.rows(NUM_COL_WIDTH), NUM_COL_WIDTH));
		buttonPanelOp.setLayout(new GridLayout(5, 1));
	
			buttonPanelH.add(makeDigitButton("E"));
			buttonPanelH.add(makeDigitButton("D"));
			buttonPanelH.add(makeDigitButton("C"));
			buttonPanelH.add(makeDigitButton("B"));
			buttonPanelH.add(makeDigitButton("A"));
			buttonPanelH.add(makeDigitButton("9"));
			buttonPanelH.add(makeDigitButton("6"));
			buttonPanelH.add(makeDigitButton("7"));
			buttonPanelH.add(makeDigitButton("8"));
			buttonPanelH.add(makeDigitButton("3"));
			buttonPanelH.add(makeDigitButton("4"));
			buttonPanelH.add(makeDigitButton("5"));
			buttonPanelH.add(makeDigitButton("0"));
			buttonPanelH.add(makeDigitButton("1"));
			buttonPanelH.add(makeDigitButton("2"));

			buttonPanelD.add(makeDigitButton("7"));
			buttonPanelD.add(makeDigitButton("8"));
			buttonPanelD.add(makeDigitButton("9"));
			buttonPanelD.add(makeDigitButton("4"));
			buttonPanelD.add(makeDigitButton("5"));
			buttonPanelD.add(makeDigitButton("6"));
			buttonPanelD.add(makeDigitButton("1"));
			buttonPanelD.add(makeDigitButton("2"));
			buttonPanelD.add(makeDigitButton("3"));
			buttonPanelD.add(makeDigitButton("0"));
			buttonPanelD.add(makeDigitButton("."));
			
			buttonPanelO.add(makeDigitButton("5"));
			buttonPanelO.add(makeDigitButton("6"));
			buttonPanelO.add(makeDigitButton("7"));
			buttonPanelO.add(makeDigitButton("2"));
			buttonPanelO.add(makeDigitButton("3"));
			buttonPanelO.add(makeDigitButton("4"));
			buttonPanelO.add(makeDigitButton("0"));
			buttonPanelO.add(makeDigitButton("1"));

			buttonPanelB.add(makeDigitButton("0"));
			buttonPanelB.add(makeDigitButton("1"));
		
		
		buttonPanelOp.add(makeOperatorButton("/"));
		buttonPanelOp.add(makeOperatorButton("*"));
		buttonPanelOp.add(makeOperatorButton("-"));
		buttonPanelOp.add(makeOperatorButton("+"));
		buttonPanelOp.add(makeOperatorButton("="));
		
		buttonPanel = new JPanel(new CardLayout());
		buttonPanel.add(buttonPanelH, "Hex");
		buttonPanel.add(buttonPanelD, "Decimal");
		buttonPanel.add(buttonPanelO, "Octal");
		buttonPanel.add(buttonPanelB, "Binary");
		
		add(buttonPanel, BorderLayout.CENTER);
		add(buttonPanelOp, BorderLayout.EAST);
	}
	
	class MathOpListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			double value = Double.parseDouble(display.getText());
			String mathOp = (String) mathOpCombo.getSelectedItem();
			
			//double base = 10;
			//if (baseeButton.isSelected()) { base = Math.E; }
			//else if (base2Button.isSelected()) { base = 2; }
			
			boolean radian = radianCheckBox.isSelected();
			if (!radian && (mathOp.equals("sin")
			|| mathOp.equals("cos") || mathOp.equals("tan")))
			{
				value = Math.toRadians(value);
			}
			
			if (mathOp.equals("sin"))
			{
				value = Math.sin(value);
			}
			else if (mathOp.equals("cos"))
			{
				value = Math.cos(value);
			}
			else if (mathOp.equals("tan"))
			{
				value = Math.tan(value);
			}
			else if (mathOp.equals("log"))
			{
				value = Math.log(value) / Math.log(base.numarical());
			}
			else if (mathOp.equals("exp"))
			{
				value = Math.pow(base.numarical(), value);
			}
			display.setText("" + value);
			
			startNewValue = true;
		}
	}
	
	/*private JPanel createBaseButtons()
	{
		baseeButton = new JRadioButton("e");
		base10Button = new JRadioButton("10");
		base2Button = new JRadioButton("2");
		
		baseeButton.setSelected(true);
		
		ButtonGroup group = new ButtonGroup();
		group.add(baseeButton);
		group.add(base10Button);
		group.add(base2Button);
		
		JPanel basePanel = new JPanel();
		basePanel.add(baseeButton);
		basePanel.add(base10Button);
		basePanel.add(base2Button);
		basePanel.setBorder(new TitledBorder(new EtchedBorder(), "Base"));
		
		return basePanel;
	}*/
	
	private void createControlPanel()
	{
		radianCheckBox = new JCheckBox("Radian");
		radianCheckBox.setSelected(true);
		
		mathOpCombo = new JComboBox();
		mathOpCombo.addItem("sin");
		mathOpCombo.addItem("cos");
		mathOpCombo.addItem("tan");
		mathOpCombo.addItem("log");
		mathOpCombo.addItem("exp");
		
		mathOpButton = new JButton("Apply");
		mathOpButton.addActionListener(new MathOpListener());
		
		JPanel controlPanel = new JPanel();
		controlPanel.add(radianCheckBox);
		//controlPanel.add(createBaseButtons());
		controlPanel.add(mathOpCombo);
		controlPanel.add(mathOpButton);
		add(controlPanel, BorderLayout.SOUTH);
	}
	
	/**
	Combines two values with an operator.
	@param value1 the first value
	@param value2 the second value
	@param op an operator (+, -, *, /, or =)
	*/
	public double calculate(double value1, double value2, String op)
	{
		if (op.equals("+"))
		{
			return value1 + value2;
		}
		else if (op.equals("-"))
		{
			return value1 - value2;
		}
		else if (op.equals("*"))
		{
			return value1 * value2;
		}
		else if (op.equals("/"))
		{
			return value1 / value2;
		}
		else // "="
		{
			return value2;
		}
	}
	
	public void changeLayout(){
		CardLayout c1 = (CardLayout)(buttonPanel.getLayout());
		c1.show(buttonPanel, base.toString());
	}
	
	class DigitButtonListener implements ActionListener
	{
		private String digit;
		/**
		Constructs a listener whose actionPerformed method adds a digit
		to the display.
		@param aDigit the digit to add
		*/
		public DigitButtonListener(String aDigit)
		{
			digit = aDigit;
		}
		public void actionPerformed(ActionEvent event)
		{
			if (startNewValue)
			{
				display.setText("");
				startNewValue = false;
			}
			display.setText(display.getText() + digit);
		}
	}
	
	/**
	Makes a button representing a digit of a calculator.
	@param digit the digit of the calculator
	@return the button of the calculator
	*/
	public JButton makeDigitButton(String digit)
	{
		JButton button = new JButton(digit);
		ActionListener listener = new DigitButtonListener(digit);
		button.addActionListener(listener);
		return button;
	}
	
	class OperatorButtonListener implements ActionListener
	{
		private String operator;
		
		/**
		Constructs a listener whose actionPerformed method
		schedules an operator for execution.
		*/
		public OperatorButtonListener(String anOperator)
		{
			operator = anOperator;
		}
		
		public void actionPerformed(ActionEvent event)
		{
			if (!startNewValue)
			{
				double value;
				if(base == Enums.Base.Decimal) value = Double.parseDouble(display.getText());
				else value = (double)Long.parseLong(display.getText(),base.numarical());
				lastValue = calculate(lastValue, value, lastOperator);
				switch(base){
				case Hex:
					display.setText(Long.toHexString(new Double(lastValue).longValue()));
					break;
				case Decimal:
					display.setText("" + lastValue);
					break;
				case Octal:
					display.setText("" + Long.toOctalString(new Double(lastValue).longValue()));
					break;
				case Binary:
					display.setText("" + Long.toBinaryString(new Double(lastValue).longValue()));
					break;
				}
				//double value = Double.parseDouble(display.getText());
				//display.setText("" + lastValue);
				startNewValue = true;
			}
			
			lastOperator = operator;
		}
	}
	
	/**
	Makes a button representing an operator of a calculator.
	param op the operator of the calculator
	@return the button of the calculator
	*/
	public JButton makeOperatorButton(String op)
	{
		JButton button = new JButton(op);
		ActionListener listener = new OperatorButtonListener(op);
		button.addActionListener(listener);
		return button;
	}
	
	

	class MenuItemAboutListener implements ActionListener
	{
		
		
		public MenuItemAboutListener()
		{
			
		}
		public void actionPerformed(ActionEvent event)
		{
			JOptionPane.showMessageDialog(buttonPanel, "Hey, I'm John Sakosky!\nThis is my calculator, I hope you enjoy using it.\n\nComing soon... keyboard input!");

		}
	}
	class MenuItemResetListener implements ActionListener
	{
		
		
		public MenuItemResetListener()
		{
			
		}
		public void actionPerformed(ActionEvent event)
		{
			base = Enums.Base.Decimal;
			reset();
			changeLayout();
		}
	}
	class MenuItemExitListener implements ActionListener
	{
		
		
		public MenuItemExitListener()
		{}
		public void actionPerformed(ActionEvent event)
		{
			System.exit(0);
		}
	}
	class MenuItemBaseListener implements ActionListener
	{
		private Enums.Base b;
		
		public MenuItemBaseListener(Enums.Base bIn)
		{
			b = bIn;		
		}
		public void actionPerformed(ActionEvent event)
		{
			double value;
			if(base == Enums.Base.Decimal) value = Double.parseDouble(display.getText());
			else value = (double)Long.parseLong(display.getText(),base.numarical());
			base = b;
			switch(base){
			case Hex:
				display.setText(Long.toHexString(new Double(value).longValue()));
				break;
			case Decimal:
				display.setText("" + value);
				break;
			case Octal:
				display.setText(Long.toOctalString(new Double(value).longValue()));
				break;
			case Binary:
				display.setText(Long.toBinaryString(new Double(value).longValue()));
				break;
			}
			changeLayout();
			
			//buttonPanel.removeAll();
			//PaintScreen();
			//revalidate();
			//repaint();
		}
	}
	
	
}