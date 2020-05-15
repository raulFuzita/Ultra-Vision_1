package com.raul.rental_shop.Ultra_Vision.view.main;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRootPane;

/**
 * @author Raul Macedo Fuzita
 * 
 * @version 13.05.20
 * <br>Version is based on the last update date.
 * 
 * @apiNote
 * <p>BootstrapWindow will display a window with the system logo until the start<br>
 * method is called and load the main window resource, icons and establish a<br>
 * database connection.</p>
 * 
 * @role It'll initialize the system and gives a sort of visual feedback to the user<br>
 * while important dependencies are loading.
 * 
 * <p>All attributes in this class are private.<p>
 */
public class BootstrapWindow extends JFrame {

	/* This was recommended by the IDE. It probably happened since serialization
	 * will load it with a class that is serializable. UltraVision class deal
	 * with JavaFX classes and this class is built in Swing. Serialization basically
	 * flat bites in serial manner to convert an object into a stream of bytes.
	 * It might help somehow the classes that extends Application, but such a 
	 * recommendation is not very clear by the IDE. I searched about it on the 
	 * internet but besides the obviously and known explanation it seems not 
	 * have an apparently reason. The order solution would be suppress the warning.
	 * */
	private static final long serialVersionUID = 1L;

	/**
	 * Once this constructor is launch it will draw a window with the system logo.
	 * It is nice to say that no matter what size is your computer screen it is
	 * going to display the window in the middle of the computer screen.
	 */
	public BootstrapWindow() {
		
		this.setSize(400, 300);
		// Gets the computer screen size in the dimension measuring system.
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		/* The location BootstrapWindow window is calculate according to the size 
		 * of the computer screen.*/
		this.setLocation(dim.width/2-this.getSize().width/2, 
							dim.height/2-this.getSize().height/2);
		
		// Just an ordinary setting for a JFrame window
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		setUndecorated(true);
		getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		
		// Sets the Ultra Vision system logo.
		ImageIcon icon = new ImageIcon("resources/images/logo2.jpg");
		JLabel div = new JLabel(icon);
		
		this.add(div);
		this.setVisible(true);
	}
}
