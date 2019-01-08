package Geom;

import Geom.Point3D;

/**
 * this class represent a box of daed space in the game whit pacmans and fruits
 */
public class Box {
	private double id;
	private Point3D start;
	private Point3D end;
	private double Weight;
	
	
	/**
	 * the box build from a line that give the play class
	 */
	public Box(String line) 
	{
		String[] arr=line.split(",");
		double x= Double.parseDouble(arr[2]);
		double y= Double.parseDouble(arr[3]);
		double z= Double.parseDouble(arr[4]);
		double x1= Double.parseDouble(arr[5]);
		double y1= Double.parseDouble(arr[6]);
		double z1= Double.parseDouble(arr[7]);
		double id=Double.parseDouble(arr[1]);
		double Weight=Double.parseDouble(arr[8]);
		this.start=new Point3D(x-0.000011,y-0.000011,z);
		this.end=new Point3D(x1+0.000011,y1+0.000011,z1);
		this.id=id;
		this.Weight=Weight;
	}
	public Point3D getStart() {
		return start;
	}
	public Point3D getEnd() {
		return end;
	}
	public String toString() {
			return id+","+start.x()+","+start.y()+","+start.z()+","+end.x()+","+end.y()+","+end.z()+","+Weight;
	}
	
}
