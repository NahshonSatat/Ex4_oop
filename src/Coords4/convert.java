package Coords4;


import Geom.Point3D;

public class convert {
	private int mapWidth;
	private int mapHeight;
	private double mapLongitudeStart;
	private double mapLatitudeStart;
	private double mapLongitude;
	private double mapLatitude;
	
	/**
	 * this class dose convert between pixels and lat lon coordinate
	 * note you have to give this class the point of the edges of the map in lat lon and the size
	 * of your map in pixels
	 *  its not going to work
	 * @author אליהו סתת
	 *
	 */
	// according to https://stackoverflow.com/questions/38748832/convert-longitude-and-latitude-coordinates-to-image-of-a-map-pixels-x-and-y-coor
	
	
	public convert(int mapWidth,int mapHeight,double mapLongitudeStart,double mapLatitudeStart,double mapLongitudeEnd,double mapLatitudeEnd) {
		this.mapWidth=mapWidth;
		this.mapHeight=mapHeight;
		this.mapLongitudeStart=mapLongitudeStart;
		this.mapLatitudeStart=mapLatitudeStart;
		this.mapLongitude=mapLongitudeEnd-mapLongitudeStart;
		this.mapLatitude=mapLatitudeStart-mapLatitudeEnd;
		
	}
	 
	/**
	 * convert pacmans from pixels to gps
	 */
	public Point3D PointPix2Gps(Point3D p) {
		double xPIX=p.x();
		double yPIX=p.y();
		   double x=xPIX*mapLongitude ;//;
		   //System.out.println(z);
		   x=x/(mapWidth);
		  // System.out.println(z);
		   x=x +mapLongitudeStart;
	       //System.out.println(x);
	       double y=yPIX*mapLatitude;
	       y=y/mapHeight;
	       y=y-mapLatitudeStart;
	       y=y*(-1);
	       //System.out.println(x+","+y);
	       Point3D p1=new Point3D(y,x,0);
	       return p1;
	}
	/**
	 * convert pacmans from  gps to pixels 
	 */
	public Point3D PointGps2Pix(Point3D p) {
	    double x,y;
	    x=p.y() - mapLongitudeStart;
	   // System.out.println(x);
	    // do inverse because the latitude increases as we go up but the y decreases as we go up.
	    // if we didn't do the inverse then all the y values would be negative.
	    y = mapLatitudeStart-p.x();
	    //System.out.println(y);
	    // set x & y using conversion
	    int x1 = (int) (mapWidth*(x/mapLongitude));
	    int y1 = (int) (mapHeight*(y/mapLatitude));
       // System.out.println((int)x1+","+ (int)y1);
	    Point3D p1=new Point3D(x1,y1,0);
       // System.out.println(p1);
        return p1;
	}

	


	/**
	 *if the size of the frame is change
	 */
	public void setFrame(int mapWidth,int mapHeight) {
		this.mapHeight=mapHeight;
		this.mapWidth=mapWidth;
	}
	

	

}
