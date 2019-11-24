package base;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import gui.MainFrame;

public class Main
{
	public static void main(String[] args)
	{
		// This will try and make the GUI look like an actual Windows application
		// I have no clue what will happen if this is ran on Linux or Mac
		// but if it fails, try changing the "Windows".equals to "Nimbus".equals
		try
		{
			for(UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
			{
				if("Windows".equals(info.getName()))
				{
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		}
		catch(ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex)
		{
			System.err.println("Can not load the specified look and feel");
		}

		// Creates and displays the Main GUI
		new MainFrame().setVisible(true);
	}
}
