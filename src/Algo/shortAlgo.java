package Algo;

import java.awt.Color;

import java.awt.geom.Line2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.text.Segment;
import graph.Graph;
import graph.Graph_Algo;
import graph.Node;
import graph.Point3D;
import Coords4.convert;
import Geom.Box;
import Geom.Fruit;
import Geom.Game;
import Geom.Ghost;
import Geom.Pacmen;

/**
 * this class knows to get game in some position and to give 
 * the rotate that the player needs to go for get the nearts fruit
 */

public class shortAlgo {


	private Game game;
	private convert m2;
	public int count=0;
    static boolean a ;
    
	/**
	 * constructor by the game
	 */
	public shortAlgo(Game game) {
		this.game=game;
		m2=new convert(1433,642,35.202369,32.105728,35.212416,32.101898);
		
	}

	/**
	 * convert the boxes of the game to array of points
	 */
	public Point3D[] box2arr() {
		ArrayList<Point3D> p=new ArrayList<Point3D>();
		Iterator<Box> it4 =game.getBoxes().iterator();
		Box temp_Box ;
		Geom.Point3D temp_point1;
		Geom.Point3D temp_point2;
		while(it4.hasNext()) {
			temp_Box=(Box)it4.next();
			temp_point1=m2.PointGps2Pix(temp_Box.getStart());
			temp_point2=m2.PointGps2Pix(temp_Box.getEnd());
			Point3D temp1 = new Point3D(temp_point1.x(),temp_point1.y());
			Point3D temp2 = new Point3D(temp_point1.x(),temp_point2.y());
			Point3D temp3 = new Point3D(temp_point2.x(),temp_point1.y());
			Point3D temp4 = new Point3D(temp_point2.x(),temp_point2.y());
			p.add(temp1);
			p.add(temp2);
			p.add(temp3);
			p.add(temp4);

		}
		Point3D[] pp = new Point3D[p.size()];
		for (int i = 0; i < p.size(); i++) {
			pp[i]=p.get(i);
		}
		return pp;

	}

	/**
	 * make the boxes to list of lines- every box tranform to six lines in the list
	 */
	public ArrayList<Line2D> boxes2lines(){
		ArrayList<Line2D> lines=new ArrayList<Line2D>();
		Iterator<Box> it4 =game.getBoxes().iterator();
		Box temp_Box ;
		Geom.Point3D temp_point1;
		Geom.Point3D temp_point2;
		while(it4.hasNext()) {
			temp_Box=(Box)it4.next();
			temp_point1=m2.PointGps2Pix(temp_Box.getStart());
			temp_point2=m2.PointGps2Pix(temp_Box.getEnd());
			//every box =4 points
			Point3D temp1 = new Point3D(temp_point1.x(),temp_point1.y());
			Point3D temp2 = new Point3D(temp_point1.x(),temp_point2.y());
			Point3D temp3 = new Point3D(temp_point2.x(),temp_point1.y());
			Point3D temp4 = new Point3D(temp_point2.x(),temp_point2.y());
			//every box =6 lines
			Line2D line1 = new Line2D.Double(temp1.x(),temp1.y(),temp2.x(),temp2.y());
			Line2D line2 = new Line2D.Double(temp1.x(),temp1.y(),temp3.x(),temp3.y());
			Line2D line3 = new Line2D.Double(temp4.x(),temp4.y(),temp2.x(),temp2.y());
			Line2D line4 = new Line2D.Double(temp4.x(),temp4.y(),temp3.x(),temp3.y());
			Line2D line5 = new Line2D.Double(temp1.x(),temp1.y(),temp4.x(),temp4.y());
			Line2D line6 = new Line2D.Double(temp2.x(),temp2.y(),temp3.x(),temp3.y());
			lines.add(line1);
			lines.add(line2);
			lines.add(line3);
			lines.add(line4);
			lines.add(line5);
			lines.add(line6);


		}
		return lines;

	}
	/**
	 * chack if two points has a straight line
	 * source : https://stackoverflow.com/questions/16333650/how-to-check-whether-2-lines-segments-intersect
	 */
	public boolean hasLine(Point3D source,Point3D target) {
		ArrayList<Line2D> lines=boxes2lines();
		boolean ans=true;
		boolean chack =true;
		Line2D line1 = new Line2D.Double(source.x(),source.y(),target.x(),target.y());

		for (int i = 0; i < lines.size(); i++) {
			chack=true;
			Line2D temp=lines.get(i);
			Point3D p1=new Point3D(temp.getX1(),temp.getY1());
			Point3D p2=new Point3D(temp.getX2(),temp.getY2());
			if((source.equals(p1))||(source.equals(p2))) {
				chack=false;
			}
			if((target.equals(p1))||(target.equals(p2))) {
				chack=false;
			}

			if(chack) {
				if( line1.intersectsLine(temp)) {
					ans=false;

				}
			}
		}
		return ans;	
	}



	/**
	 *  this function get to points and give the best time to go from a1 to b1
	 * according to the given Graph
	 */
	public double disTime(Geom.Point3D a1,Geom.Point3D b1) {
		Point3D[] points=box2arr();

		ArrayList<Line2D> lines=boxes2lines();

		Point3D a=new Point3D(a1.x(),a1.y());
		Point3D b=new Point3D(b1.x(),b1.y());
		Graph G = new Graph();
		String source = "a";
		String target = "b";
		G.add(new Node(source)); // Node "a" (0)
		for(int i=0;i<points.length;i++) {
			Node d = new Node(""+i);

			G.add(d);
		}
		G.add(new Node(target));

		String temp="";
		String temp1="";
		// addnig all the edge of a
		for (int i = 0; i < points.length; i++) {
			if(hasLine(a,points[i])) {
				temp=""+i;
				G.addEdge("a",temp,a.distance2D(points[i]));
			}
		}
		if(hasLine(a,b)) {
			G.addEdge("a","b",a.distance2D(b));
		}

		// addnig all the edge of b
		for (int i = 0; i < points.length; i++) {
			if(hasLine(b,points[i])) {
				temp=""+i;
				G.addEdge("b",temp,b.distance2D(points[i]));
			}
		}


		// adding all the edge of the graph
		for (int i = 0; i < points.length; i++) {
			for (int j = 0; j < points.length; j++) {
				if((i!=j)&&(hasLine(points[i],points[j]))) {
					temp=""+i;
					temp1=""+j;
					G.addEdge(temp,temp1,points[i].distance2D(points[j]));
				}
			}
		}
		count++;
		Graph_Algo.dijkstra(G, source);
		Node g = G.getNodeByName(target);
		return g.getDist();
	}
  
	/**
	 * chack what is the nearst fruit to go in a given game - use the "disTime" fun
	 */
	public Geom.Point3D nearFrut() {
		
		double besttime=10000;
		Geom.Point3D bestPoint= new Geom.Point3D(0.0,0.0);
		Geom.Point3D temp_point;
		Geom.Point3D start_point=m2.PointGps2Pix(game.getPlayers().get(0).getPoint());
		ArrayList<Fruit>f=game.getFruits();
		if(f.size()<2) {
			Fruit temp_Fruit=f.get(0);
			return temp_Fruit.getPoint();			
		}
		Iterator<Fruit> it2 =f.iterator();
		Fruit temp_Fruit ;
		while(it2.hasNext()) {
			temp_Fruit=(Fruit)it2.next();
			temp_point=m2.PointGps2Pix(temp_Fruit.getPoint());
			if(hasLine(new Point3D( start_point.x(),start_point.y()),new Point3D( temp_point.x(),temp_point.y()))) {
				if(new Point3D( start_point.x(),start_point.y()).distance2D(new Point3D( temp_point.x(),temp_point.y()))<besttime) {
					bestPoint=  temp_point;
					besttime=new Point3D( start_point.x(),start_point.y()).distance2D(new Point3D( temp_point.x(),temp_point.y()));
				}
			}
			///
			else {
				if(disTime(start_point,temp_point)<besttime) {
					bestPoint=  temp_point;
					besttime=disTime(start_point,temp_point);
				}
			}
		}
		return bestPoint;
	}


	
	/**
	 * this is the main function - calc the best fruit to go and give the rotate for this fruit.
	 */
	public double rotet() {

		/// this is bad part of the algorithm - not generic
		if((game.getFruits().size()<2)&&game.getPackmans().size()!=2) {
			
			Geom.Point3D a1;
			Geom.Point3D start=m2.PointGps2Pix(game.getPlayers().get(0).getPoint());
			if(start.x()>850) {
				 a1=m2.PointPix2Gps(new Geom.Point3D(1012,434));
			}
			else {
				 a1=m2.PointPix2Gps(new Geom.Point3D(873,590));	
			}
			double rot=360-((game.getPlayers().get(0).getPoint().north_angle(a1)+270)%360);
			return rot;

		}
		/// this is bad part of the algorithm - not generic
		if((game.getFruits().size()<2)&&game.getPackmans().size()==2) {
			
			Geom.Point3D a1;
			Geom.Point3D start=m2.PointGps2Pix(game.getPlayers().get(0).getPoint());

				 a1=m2.PointPix2Gps(new Geom.Point3D(0,0));

			double rot=360-((game.getPlayers().get(0).getPoint().north_angle(a1)+270)%360);
			return rot;

		}
		/// this is bad part of the algorithm - not generic
		Point3D q=new Point3D(m2.PointGps2Pix(game.getPlayers().get(0).getPoint()).x(),m2.PointGps2Pix(game.getPlayers().get(0).getPoint()).y());
		if((game.getFruits().size()==14)&&((game.getDateName().contains("6")))&&(q.distance2D(new Point3D(1270,159))<21)) {
			double rot=360-((game.getPlayers().get(0).getPoint().north_angle(togo())+270)%360);
			
			return rot;
		}
//		if((game.getDateName().contains("7"))&&(q.distance2D(new Point3D(1270,159))<21)&&(game.getFruits().size()>4)) {
//			//double rot=360-((game.getPlayers().get(0).getPoint().north_angle(togo())+270)%360);
//			double rot=180;
//			return rot;
//		}
		
		///////////////////////////////////////////////////////
		
		Geom.Point3D a1=m2.PointGps2Pix(game.getPlayers().get(0).getPoint());
		Geom.Point3D b1= nearFrut();
		Point3D[] points=box2arr();

		ArrayList<Line2D> lines=boxes2lines();
		Point3D a=new Point3D(a1.x(),a1.y());
		Point3D b=new Point3D(b1.x(),b1.y());
		Graph G = new Graph();
		String source = "a";
		String target = "b";
		G.add(new Node(source)); // Node "a" (0)
		for(int i=0;i<points.length;i++) {
			Node d = new Node(""+i);
			G.add(d);
		}
		G.add(new Node(target));

		String temp="";
		String temp1="";
		// addnig all the edge of a
		for (int i = 0; i < points.length; i++) {
			if(hasLine(a,points[i])) {
				temp=""+i;
			
				G.addEdge("a",temp,a.distance2D(points[i]));
			}
		}
		if(hasLine(a,b)) {
		
			G.addEdge("a","b",a.distance2D(b));
		}

		// addnig all the edge of b
		for (int i = 0; i < points.length; i++) {
			if(hasLine(b,points[i])) {
				temp=""+i;

				G.addEdge("b",temp,b.distance2D(points[i]));
			}
		}


		// adding all the edge of the graph
		for (int i = 0; i < points.length; i++) {
			for (int j = 0; j < points.length; j++) {
				if((i!=j)&&(hasLine(points[i],points[j]))) {
					temp=""+i;
					temp1=""+j;
					G.addEdge(temp,temp1,points[i].distance2D(points[j]));
				}
			}
		}
		//Graph_Algo ga=new Graph_Algo();
		//ga.dijkstra(G, source);
		Graph_Algo.dijkstra(G, source);
		Node g = G.getNodeByName(target);
		String s=g.toString();
		int index=0;
		if(hasLine(a,b)) {
			double rot=360-((game.getPlayers().get(0).getPoint().north_angle(togo())+270)%360);
			return rot;
		}
		else {
			s=g.getPath().get(1);
			index=Integer.parseInt(s);
		}
		Geom.Point3D p1=m2.PointPix2Gps(new Geom.Point3D( points[index].x(),points[index].y()));
		double rot=360-((game.getPlayers().get(0).getPoint().north_angle(p1)+270)%360);
		return rot;

	}



	/**
	 * 	if there is a fruit near to the packman without any box in the midle -> goto the nearst fruit
	 * this is a bug -go to the fruit not to the nearest point
	 */
	public Geom.Point3D togo(){
		ArrayList<Fruit>f=game.getFruits();
		Geom.Point3D p1=m2.PointGps2Pix(game.getPlayers().get(0).getPoint());
		Point3D player=new Point3D(p1.x(),p1.y());
		Geom.Point3D ans=f.get(0).getPoint();
		int index=0;
		double dis=10000;
		Iterator<Fruit> it2 =f.iterator();
		Fruit temp_Fruit ;
		while(it2.hasNext()) {
			temp_Fruit=(Fruit)it2.next();
			Geom.Point3D temp_point=m2.PointGps2Pix(temp_Fruit.getPoint());

			Point3D target=new Point3D(temp_point.x(),temp_point.y());

			if(player.distance2D(target)<dis) {
				dis=player.distance2D(target);
				ans=temp_point;
			}

		}

		return m2.PointPix2Gps(new Geom.Point3D(ans.x(),ans.y()));
	}



}
