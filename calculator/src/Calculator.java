//Written Fall of 2013 by John Sakosky

import javax.swing.JFrame;
public class Calculator
{
	public static void main(String[] args)
	{
		JFrame frame = new CalculatorFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Calculator");
		frame.setVisible(true);
	}
}