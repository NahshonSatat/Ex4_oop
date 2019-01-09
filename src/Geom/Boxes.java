package Geom;

import java.nio.file.Path;
import java.util.ArrayList;
import Coords.GeoBox;

import Geom.Box;
public class Boxes {
	private ArrayList<Box> boxess;
	private ArrayList<Point3D> path;
	
	
	public Boxes() {
		boxess=new ArrayList<Box>();
		path=new ArrayList<Point3D>();
	}
	public  ArrayList<Point3D> allthepathbox(ArrayList<Box> boxess,ArrayList<Point3D> path) {
		for (int i = 0; i <  boxess.size(); i++) {
			path.add(boxess.get(i).getStart());
			path.add(boxess.get(i).getEnd());
			Point3D temp=new  Point3D(boxess.get(i).getStart().y(),boxess.get(i).getEnd().x());
			path.add(temp);
			Point3D temp1=new  Point3D(boxess.get(i).getStart().x(),boxess.get(i).getEnd().y());
			path.add(temp1);
		}
		return path;
	}
	public  ArrayList<Point3D> Isin(ArrayList<Box> boxess,ArrayList<Point3D> path) {
		for (int i = 0; i <  path.size(); i++) {
             Point3D temp=new  Point3D(boxess.get(i).getStart().y(),boxess.get(i).getEnd().x());
			Point3D temp1=new  Point3D(boxess.get(i).getStart().x(),boxess.get(i).getEnd().y());
			if (boxess.get(i).getStart()==path.get(i)||boxess.get(i).getEnd()==path.get(i)||temp==path.get(i)||temp1==path.get(i)) {
				
			}
			else if(isIn2D( path.get(i))) {
				
			}
			
		
			
		}
		return path;
	}
	public boolean isIn2D(Point3D q)
    {
        boolean ans = false;
        if(q.x() >= boxess.get(0).getStart().x() && q.y() >=boxess.get(0).getStart().y() && q.x() <= boxess.get(0).getEnd().x() && q.y() <=boxess.get(0).getEnd().y())
        {
            ans = true;
        }
        return ans;
    }

    public boolean isIn3D(Point3D q)
    {
        boolean ans = isIn2D(q);
        ans = ans && q.z() >= boxess.get(0).getStart().z() && q.z() <= boxess.get(0).getEnd().z() ;
        return ans;
    }

}
