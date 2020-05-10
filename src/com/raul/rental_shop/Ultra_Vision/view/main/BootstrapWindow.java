package com.raul.rental_shop.Ultra_Vision.view.main;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRootPane;

public class BootstrapWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	public BootstrapWindow() {
		
		this.setSize(400, 300);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		setUndecorated(true);
		getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		
		ImageIcon icon = new ImageIcon("resources/images/logo2.jpg");
		JLabel div = new JLabel(icon);
		// com/raul/rental_shop/Ultra_Vision/view/main
		this.add(div);
		this.setVisible(true);
	}
}
