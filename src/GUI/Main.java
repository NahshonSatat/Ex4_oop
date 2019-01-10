package GUI;

import java.io.IOException;

import javax.swing.JFrame;

import Geom.Point3D;




public class Main {

	public static void main(String[] args) throws IOException
	{

		MainWindow window = new MainWindow();
		window.setVisible(true);
		window.setSize(window.myImage.getWidth(),window.myImage.getHeight());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
