package GUI;

import java.io.IOException;

import javax.swing.JFrame;

import Geom.Point3D;




public class Main {

	public static void main(String[] args) throws IOException
	{
		Point3D g1=new Point3D(32.10551815162297,35.202532285610324,0.0);
		Point3D g2=new Point3D(32.10551814364916,35.20253228539825,0.0);
		//Map m=new Map("C:\\\\Users\\\\אליהו סתת\\\\Desktop\\\\Ariel1.png",g1,g2);
		MainWindow window = new MainWindow();
		window.setVisible(true);
		window.setSize(window.myImage.getWidth(),window.myImage.getHeight());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Point3D g1=new Point3D(32.10551816438954,35.20253230283035,0.0);
		Point3D p2=new Point3D(32.1055181642623,35.20253229218447,0.0);
		//System.out.println(g1.north_angle(p2));
	//System.out.println(TimeNow());
	//long l=1000;
	//int x=1;
	//System.out.println(TimeNow());
    //System.out.println(pointTime(x))
		
	}

}
