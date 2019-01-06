package Geom;

import Geom.Point3D;

/**
 * this class represent a packman that is a point 
 * with speed  and Weight
 * @author אליהו סתת
 *
 */

public class Pacmen {
	private double Radius;
	private double id;
	private double speed;
	private Point3D point;
	private double time;
	
	/**
	 * the class build from a line that given from play class
	 */
	public Pacmen(String line) {
		String[] arr=line.split(",");
		// getting the lat lont alt for the point
		double x= Double.parseDouble(arr[2]);
		double y= Double.parseDouble(arr[3]);
		double z= Double.parseDouble(arr[4]);
		double id=Double.parseDouble(arr[1]);
		double speed=Double.parseDouble(arr[5]);
		double radius=Double.parseDouble(arr[6]);
		this.point=new Point3D(x,y,z);
		this.id=id;
		this.speed=speed;
		this.Radius=radius;
		this.time=0;
	}
	public Point3D getPoint() {
		return this.point;
	}
	public String toString() {
		return id+","+point.x()+","+point.y()+","+point.z()+","+speed+","+Radius;
	}
}
