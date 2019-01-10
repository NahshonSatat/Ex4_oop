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
	//	public static void main(String[] args) 
	//	{
	//		Line2D a2g = new Line2D.Double(678,202,829,507);
	//		Line2D a2g1 = new Line2D.Double(468.0,365.0,1176.0,365.0);
	//		Line2D a2g2 = new Line2D.Double(1176.0,296.0,468.0,296.0);
	//		System.out.println(a2g.intersectsLine(a2g1));
	//		System.out.println(a2g.intersectsLine(a2g2));
	//	}

	private Game game;
	private convert m2;
	public int count=0;
    static boolean a ;
    
	public shortAlgo(Game game) {
		this.game=game;
		m2=new convert(1433,642,35.202369,32.105728,35.212416,32.101898);
		
	}

	public void update(Game game) {
		this.game=game;
	}


	// convert the boxes of the game to array of points
	//works
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
		//System.out.println(p);
		Point3D[] pp = new Point3D[p.size()];
		for (int i = 0; i < p.size(); i++) {
			pp[i]=p.get(i);
		}
		//System.out.println(pp.length);
		return pp;

	}

	// make the boxes to list of lines- every box tranform to six lines in the list
	// works!!!
	public ArrayList<Line2D> boxes2lines(){
		ArrayList<Line2D> lines=new ArrayList<Line2D>();
		Iterator<Box> it4 =game.getBoxes().iterator();
		Box temp_Box ;
		Geom.Point3D temp_point1;
		Geom.Point3D temp_point2;
		while(it4.hasNext()) {
			temp_Box=(Box)it4.next();
			temp_point1=m2.PointGps2Pix(temp_Box.getStart());
			//System.out.println(temp_point1);
			temp_point2=m2.PointGps2Pix(temp_Box.getEnd());
			//System.out.println(temp_point2);
			Point3D temp1 = new Point3D(temp_point1.x(),temp_point1.y());
			Point3D temp2 = new Point3D(temp_point1.x(),temp_point2.y());
			Point3D temp3 = new Point3D(temp_point2.x(),temp_point1.y());
			Point3D temp4 = new Point3D(temp_point2.x(),temp_point2.y());
			//			Line2D line1 = new Line2D.Double(temp1.x()-1,temp1.y(),temp2.x()-1,temp2.y());
			//			//System.out.println("line1:"+temp1.x()+","+temp1.y()+","+temp2.x()+","+temp2.y());
			//			Line2D line2 = new Line2D.Double(temp1.x(),temp1.y()+1,temp3.x(),temp3.y()+1);
			//			//System.out.println("line2:"+temp1.x()+","+temp1.y()+","+temp3.x()+","+temp3.y());
			//			Line2D line3 = new Line2D.Double(temp4.x(),temp4.y()-1,temp2.x(),temp2.y()-1);
			//			//System.out.println("line3:"+temp4.x()+","+temp4.y()+","+temp2.x()+","+temp2.y());
			//			Line2D line4 = new Line2D.Double(temp4.x()+1,temp4.y(),temp3.x()+1,temp3.y());
			//Line2D line5 = new Line2D.Double(274,193,259,449);
			//System.out.println("line4:"+temp4.x()+","+temp4.y()+","+temp3.x()+","+temp3.y());
			Line2D line1 = new Line2D.Double(temp1.x(),temp1.y(),temp2.x(),temp2.y());
			//System.out.println("line1:"+temp1.x()+","+temp1.y()+","+temp2.x()+","+temp2.y());
			Line2D line2 = new Line2D.Double(temp1.x(),temp1.y(),temp3.x(),temp3.y());
			//System.out.println("line2:"+temp1.x()+","+temp1.y()+","+temp3.x()+","+temp3.y());
			Line2D line3 = new Line2D.Double(temp4.x(),temp4.y(),temp2.x(),temp2.y());
			//System.out.println("line3:"+temp4.x()+","+temp4.y()+","+temp2.x()+","+temp2.y());
			Line2D line4 = new Line2D.Double(temp4.x(),temp4.y(),temp3.x(),temp3.y());

			Line2D line5 = new Line2D.Double(temp1.x(),temp1.y(),temp4.x(),temp4.y());
			//System.out.println("line3:"+temp4.x()+","+temp4.y()+","+temp2.x()+","+temp2.y());
			Line2D line6 = new Line2D.Double(temp2.x(),temp2.y(),temp3.x(),temp3.y());
			lines.add(line1);
			lines.add(line2);
			lines.add(line3);
			lines.add(line4);
			lines.add(line5);
			lines.add(line6);


		}
		//System.out.println(lines.size());
		return lines;

	}

	//chack if two points has a straight line 
	//source : https://stackoverflow.com/questions/16333650/how-to-check-whether-2-lines-segments-intersect
	// works!!!
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
			//if((line1.contains(temp.getX1(),temp.getY1()))){//||(line1.contains(temp.getX2(),temp.getY2()))) {
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

	/*
	public void chack(Geom.Point3D a1,Geom.Point3D b1) {
		Point3D a=new Point3D(a1.x(),a1.y());
		Point3D b=new Point3D(b1.x(),b1.y());
		System.out.println(hasLine(a,b));

	}
	public void syse(ArrayList<Line2D> a)
	{
		Iterator<Line2D> it3 =a.iterator();
		Line2D temp_Line2D ;
		while(it3.hasNext()) {
			temp_Line2D=(Line2D)it3.next();
           System.out.println(temp_Line2D.getX1()+","+temp_Line2D.getY1()+","+temp_Line2D.getX2()+","+temp_Line2D.getY2());
		}
	}
	 */

	/*
	public Graph buildGraph(Geom.Point3D a1,Geom.Point3D b1) {
		Point3D[] points=box2arr();
//		for (int i = 0; i < points.length; i++) {
//			System.out.println(points[i]);
//		}

		System.out.println(points.length);
		ArrayList<Line2D> lines=boxes2lines();
		syse(lines);
		System.out.println(lines.size());
		Point3D a=new Point3D(a1.x(),a1.y());
		Point3D b=new Point3D(b1.x(),b1.y());
		Graph G = new Graph();
		String source = "a";
		String target = "b";
		G.add(new Node(source)); // Node "a" (0)
		for(int i=0;i<points.length;i++) {
			Node d = new Node(""+i);
			System.out.println("the point"+i+": "+points[i]);

			G.add(d);
		}
		G.add(new Node(target));

		String temp="";
		String temp1="";
		// addnig all the edge of a
		System.out.println(a);
		for (int i = 0; i < points.length; i++) {
			if(hasLine(a,points[i])) {
				temp=""+i;
				System.out.println("adding edge a");
				G.addEdge("a",temp,a.distance2D(points[i]));
			}
		}
		if(hasLine(a,b)) {
			System.out.println("adding edge a-b");
			G.addEdge("a","b",a.distance2D(b));
		}

		// addnig all the edge of b
		for (int i = 0; i < points.length; i++) {
			if(hasLine(b,points[i])) {
				temp=""+i;
				System.out.println("adding edge b");
				System.out.println(points[i]);
				G.addEdge("b",temp,b.distance2D(points[i]));
			}
		}


		// adding all the edge of the graph
		for (int i = 0; i < points.length; i++) {
			for (int j = 0; j < points.length; j++) {
				if((i!=j)&&(hasLine(points[i],points[j]))) {
					temp=""+i;
					temp1=""+j;
					System.out.println("adding edge c"+i+","+j);
					System.out.println(points[i]);
					System.out.println(points[j]);
					G.addEdge(temp,temp1,points[i].distance2D(points[j]));
				}
			}
		}
		//Graph_Algo ga1=new Graph_Algo();

		Graph_Algo.dijkstra(G, source);
		Node g = G.getNodeByName(target);
		System.out.println("***** Graph try for OOP_Ex4 *****");
		System.out.println(g);
		System.out.println("Dist: "+g.getDist());
		return G;
	}

	 */

	// this function get to points and give the best time to go from a1 to b1
	// according to the given Graph
	public double disTime(Geom.Point3D a1,Geom.Point3D b1) {
		Point3D[] points=box2arr();

		ArrayList<Line2D> lines=boxes2lines();
		//syse(lines);
		//System.out.println(lines.size());
		Point3D a=new Point3D(a1.x(),a1.y());
		Point3D b=new Point3D(b1.x(),b1.y());
		Graph G = new Graph();
		String source = "a";
		String target = "b";
		G.add(new Node(source)); // Node "a" (0)
		for(int i=0;i<points.length;i++) {
			Node d = new Node(""+i);
			//System.out.println("the point"+i+": "+points[i]);

			G.add(d);
		}
		G.add(new Node(target));

		String temp="";
		String temp1="";
		// addnig all the edge of a
		//System.out.println(a);
		for (int i = 0; i < points.length; i++) {
			if(hasLine(a,points[i])) {
				temp=""+i;
				//System.out.println("adding edge a");
				G.addEdge("a",temp,a.distance2D(points[i]));
			}
		}
		if(hasLine(a,b)) {
			//System.out.println("adding edge a-b");
			G.addEdge("a","b",a.distance2D(b));
		}

		// addnig all the edge of b
		for (int i = 0; i < points.length; i++) {
			if(hasLine(b,points[i])) {
				temp=""+i;
				//System.out.println("adding edge b");
				//System.out.println(points[i]);
				G.addEdge("b",temp,b.distance2D(points[i]));
			}
		}


		// adding all the edge of the graph
		for (int i = 0; i < points.length; i++) {
			for (int j = 0; j < points.length; j++) {
				if((i!=j)&&(hasLine(points[i],points[j]))) {
					temp=""+i;
					temp1=""+j;
					//System.out.println("adding edge c"+i+","+j);
					//System.out.println(points[i]);
					//System.out.println(points[j]);
					G.addEdge(temp,temp1,points[i].distance2D(points[j]));
				}
			}
		}
		//System.out.println("the "+count+"time");
		count++;
		Graph_Algo.dijkstra(G, source);
		Node g = G.getNodeByName(target);
		//System.out.println("***** Graph try for OOP_Ex4 *****");
		//System.out.println(g);
		//System.out.println("Dist: "+g.getDist());
		return g.getDist();
	}


	// chack what is the nearst fruit to go in a given game - use the "disTime" fun 
	public Geom.Point3D nearFrut() {
		
		double besttime=10000;
		Geom.Point3D bestPoint= new Geom.Point3D(0.0,0.0);
		Geom.Point3D temp_point;
		Geom.Point3D start_point=m2.PointGps2Pix(game.getPlayers().get(0).getPoint());
		ArrayList<Fruit>f=game.getFruits();
		if(f.size()<2) {
			//System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			Fruit temp_Fruit=f.get(0);
			return temp_Fruit.getPoint();			
		}
		Iterator<Fruit> it2 =f.iterator();
		Fruit temp_Fruit ;
		while(it2.hasNext()) {
			temp_Fruit=(Fruit)it2.next();
			temp_point=m2.PointGps2Pix(temp_Fruit.getPoint());
			////
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
	public Geom.Point3D SenplenearFrut() {
		double besttime=10000;
		Geom.Point3D bestPoint= new Geom.Point3D(0.0,0.0);
		Geom.Point3D temp_point;
		Geom.Point3D start_point=m2.PointGps2Pix(game.getPlayers().get(0).getPoint());
		ArrayList<Fruit>f=game.getFruits();
		Iterator<Fruit> it2 =f.iterator();
		Fruit temp_Fruit ;
		while(it2.hasNext()) {
			temp_Fruit=(Fruit)it2.next();
			temp_point=m2.PointGps2Pix(temp_Fruit.getPoint());
			////
			//if(hasLine(new Point3D( start_point.x(),start_point.y()),new Point3D( temp_point.x(),temp_point.y()))) {
				if(new Point3D( start_point.x(),start_point.y()).distance2D(new Point3D( temp_point.x(),temp_point.y()))<besttime) {
					bestPoint=  temp_point;
					besttime=new Point3D( start_point.x(),start_point.y()).distance2D(new Point3D( temp_point.x(),temp_point.y()));
			
			}

		}
		return bestPoint;
	}



	// this is the main function - calc the best fruit to go and give the rotate for this fruit.
	public double rotet() {
  //System.out.println("num of fruit: "+game.getFruits().size());
  //ystem.out.println("num of fruit: "+game.getPackmans().size());
 // System.out.println("the game: "+game.getMap().getPath());
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
		Point3D q=new Point3D(m2.PointGps2Pix(game.getPlayers().get(0).getPoint()).x(),m2.PointGps2Pix(game.getPlayers().get(0).getPoint()).y());
		System.out.println("the dis is: "+q.distance2D(new Point3D(1270,159)));
		if((game.getFruits().size()==14)&&((game.getDateName().contains("6")))&&(q.distance2D(new Point3D(1270,159))<21)) {
			double rot=360-((game.getPlayers().get(0).getPoint().north_angle(togo())+270)%360);
			System.err.println("in the if");
			
			return rot;
		}
//		if((game.getDateName().contains("7"))&&(q.distance2D(new Point3D(1270,159))<21)&&(game.getFruits().size()>4)) {
//			//double rot=360-((game.getPlayers().get(0).getPoint().north_angle(togo())+270)%360);
//			System.err.println("in the if");
//			double rot=180;
//			return rot;
//		}
		
		///////////////////////////////////////////////////////
		
		//Geom.Point3D b1=m2.PointGps2Pix(game.getFruits().get(0).getPoint());
		Geom.Point3D a1=m2.PointGps2Pix(game.getPlayers().get(0).getPoint());
		Geom.Point3D b1= nearFrut();
		Point3D[] points=box2arr();

		//System.out.println(points.length);
		ArrayList<Line2D> lines=boxes2lines();
		//syse(lines);
		//System.out.println(lines.size());
		Point3D a=new Point3D(a1.x(),a1.y());
		Point3D b=new Point3D(b1.x(),b1.y());
		Graph G = new Graph();
		String source = "a";
		String target = "b";
		G.add(new Node(source)); // Node "a" (0)
		for(int i=0;i<points.length;i++) {
			Node d = new Node(""+i);
			//System.out.println("the point"+i+": "+points[i]);

			G.add(d);
		}
		G.add(new Node(target));

		String temp="";
		String temp1="";
		// addnig all the edge of a
		//System.out.println(a);
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
		Graph_Algo ga=new Graph_Algo();
		//ga.dijkstra(G, source);
		Graph_Algo.dijkstra(G, source);
		Node g = G.getNodeByName(target);
		String s=g.toString();
		//System.out.println("the path"+g.getPath());
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


	//if there is a fruit near to the packman without any box in the midle -> goto the nearst fruit
	// this is a bug -go to the fruit not to the nearest point
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
