package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import com.mysql.fabric.Server;

import Algo.shortAlgo;

import Coords4.convert;
import Geom.Box;
import Geom.Database;
import Geom.Fruit;
import Geom.Game;
import Geom.Ghost;
import Geom.Map;
import Geom.Pacmen;
import Geom.Player;
import Geom.Point3D;

import Robot.Play;


/**
 * this class is the gui class of this game
 * @author אליהו סתת
 *with the following application:
 *you can loud game from the data
 *you can play by hand 
 *you can run the algorithm of the game
 */
public class MainWindow extends JFrame implements MouseListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//private static final ImageIO ImegeIO = null;

	public BufferedImage myImage;
	private convert m1;
	private convert m2;
	private double rot;
	private Point3D current_palyer;
	//private Image p1;
	//private Image f1;
	private String file_name ;
	private Play play1 ;
	private Game game;
	private boolean play =true;
	private boolean run =true;
	private boolean init =true;
	// the constructor 
	public MainWindow() throws IOException 
	{
		//this.map=map;
		//gameP=new Game();
		//file_name=s;
		//play1 = new Play(file_name);
        Map m=new Map("Ariel1.png");
		//play1.setInitLocation(32.10486058280427,35.20937630059002);
		game=new Game(m);
		//game.upDate(play1.getBoard());
		//play1.start();
		rot=0;
		//current_palyer=game.getPlayers().get(0).getPoint();
		//System.out.println("fruts"+game.getFruits().size());
		//System.out.println("pac"+game.getPackmans().size());
		//System.out.println("ghost"+game.getGhosts().size());


		//File pacFile = new File("C:\\Users\\אליהו סתת\\Downloads\\1.png");
		//p1=ImageIO.read(pacFile);
		initGUI();		
		this.addMouseListener(this);
		// my coordinete
		m1=new convert(1433,642,35.202306,32.105730,35.212407,32.101867);
		//boaz coords
		m2=new convert(1433,642,35.202369,32.105728,35.212416,32.101898);

	}

	private void initGUI() 
	{
		MenuBar menuBar = new MenuBar();
		Menu menu = new Menu("file"); 
		MenuItem item1 = new MenuItem("save");
		MenuItem item2 = new MenuItem("load");
		Menu menu1 = new Menu("game"); 
		MenuItem item3 = new MenuItem("run");
		MenuItem item4 = new MenuItem("new game");
		MenuItem item5 = new MenuItem("play");
		MenuItem item6 = new MenuItem("toKml");


		// the "save" action
		item1.addActionListener(new ActionListener() {
			// acording to https://stackoverflow.com/questions/22261130/how-to-save-a-file-using-jfilechooser-showsavedialog
			@Override
			public void actionPerformed(ActionEvent arg0) {

			}

		});

		// the "load" action
		item2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// according to https://stackoverflow.com/questions/18774652/how-to-use-jfilechooser-to-find-a-file-location

				JFileChooser fc = new JFileChooser();

				int returnVal = fc.showOpenDialog(null);
				fc.showOpenDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					if(!file.getAbsolutePath().contains(".csv")) {
						System.out.println("this is not csv file!!!");
					}
					else {
						System.out.println("Opening: " + file.getAbsolutePath());

						try {
							play1 = new Play(file.getAbsolutePath());
	
							game.upDate(play1.getBoard());

						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					//System.out.println("num of boxes"+game.getBoxes().size());
					repaint();
				}
			}
		});

		// the "run" action
		item3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Point3D p1;
				play1.setIDs(315823880, 204395644);
//				if(!game.getPackmans().isEmpty()) {
//				 p1=game.getPackmans().get(0).getPoint();
//				}
//				else {
					p1=game.getFruits().get(0).getPoint();
				//}
				if(init) {
				play1.setInitLocation(p1.x(),p1.y());
				init=false;
				}
				play1.start();
				game.upDate(play1.getBoard());
				repaint();
				
				RunThread t1 = new RunThread();
				t1.start();
			}

		});


		// the "new game" action
		item4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				shortAlgo sa=new shortAlgo(game);
				play1.setIDs(315823880, 204395644);
				Point3D a1 = new Point3D(425,153);
				Point3D c1 = new Point3D(450,538);
				Point3D b1 = new Point3D(1094,509);
				
				//sa.chack(a1, c1);
				
				
			}

		});
		// the "play" action
		item5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				//play1.setInitLocation(32.10486058280427,35.20937630059002);
				play1.setIDs(315823880, 204395644);
				Point3D p1=m1.PointPix2Gps(new Point3D(x,y,0));
				if(init) {
				play1.setInitLocation(p1.x(),p1.y());
				init=false;
				}
				play1.start();
				PlayThread t1 = new PlayThread();
				t1.start();

			}});



		// the "tokml" action
		item6.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				shortAlgo sa=new shortAlgo(game);
				Point3D t=new Point3D(32.10386468847352,35.20379330704815,0.0);
				play1.setIDs(315823880, 204395644);
				Point3D temp_point1=m2.PointGps2Pix(game.getPlayers().get(0).getPoint());
				Point3D temp_point2=m2.PointGps2Pix(t);
				Point3D temp1 = new Point3D(678,202);
				//Point3D temp1 = new Point3D(0,0);
				//Point3D temp1 = new Point3D(temp_point1.x(),temp_point1.y());
				//Point3D temp2 = new Point3D(temp_point2.x(),temp_point2.y());
				Point3D temp2 = new Point3D(1010,453);
				//sa.buildGraph(temp1,temp2);
//				System.out.println("the num of points:");
//				sa.box2arr();
//				System.out.println("the num of lines:");
//				sa.boxes2lines();
				play1.setIDs(315823880, 204395644);

			}});

		//menu.add(item1);
		menu.add(item2);
		menu1.add(item3);
		//menu1.add(item4);
		menu1.add(item5);
		//menu1.add(item6);
		menuBar.add(menu);
		menuBar.add(menu1);

		this.setMenuBar(menuBar);

		try {
			//myImage = ImageIO.read(new File(map.getPath()));
			//myImage = ImageIO.read(new File("Ariel1.png"));
			myImage = ImageIO.read(new File(game.getMap().getPath()));
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}




	int x = -1;
	int y = -1;

	public void paint(Graphics g)
	{

		g.drawImage(myImage, 0, 0,this.getWidth(),this.getHeight(), this);

		//m1.setFrame(this.getWidth(), this.getHeight());
		m2.setFrame(this.getWidth(), this.getHeight());
		// g.fillRect(230,80,10,10);
		ArrayList<Pacmen>p=game.getPackmans();
		ArrayList<Fruit>f=game.getFruits();
		ArrayList<Player>pl=game.getPlayers();
		ArrayList<Ghost>gh=game.getGhosts();
		ArrayList<Geom.Box> b =game.getBoxes();
		Point3D temp_point;
		Point3D temp_point1;
		Point3D temp_point2;

		// print the player
		if(!pl.isEmpty()) {
			Player p1=pl.get(0);

			//temp_point=m1.PointGps2Pix(p1.getPoint());
			temp_point=m2.PointGps2Pix(p1.getPoint());
			g.setColor(Color.BLACK);
			g.fillOval((int)temp_point.x(), (int)temp_point.y(), 20, 20);
		}

		// print the Packmans
		Iterator<Pacmen> it1 =p.iterator();
		Pacmen temp_Packman ;
		while(it1.hasNext()) {
			temp_Packman=(Pacmen)it1.next();
			//temp_point=m1.PointGps2Pix(temp_Packman);
			//temp_point=m1.PointGps2Pix(temp_Packman.getPoint());
			temp_point=m2.PointGps2Pix(temp_Packman.getPoint());
			// System.out.println("print pacmen");
			g.setColor(Color.YELLOW);
			g.fillOval((int)temp_point.x(), (int)temp_point.y(), 20, 20);

		}
		// print the Fruit
		Iterator<Fruit> it2 =f.iterator();
		Fruit temp_Fruit ;
		while(it2.hasNext()) {
			temp_Fruit=(Fruit)it2.next();
			//temp_point=m1.PointGps2Pix(temp_Packman);
			//temp_point=m1.PointGps2Pix(temp_Fruit.getPoint());
			temp_point=m2.PointGps2Pix(temp_Fruit.getPoint());
			//  System.out.println("print fruit");
			g.setColor(Color.GREEN);
			g.fillOval((int)temp_point.x(), (int)temp_point.y(), 10, 10);

		}
		// print the ghost
		Iterator<Ghost> it3 =gh.iterator();
		Ghost temp_Ghost ;
		while(it3.hasNext()) {
			temp_Ghost=(Ghost)it3.next();
			//temp_point=m1.PointGps2Pix(temp_Packman);
			//temp_point=m1.PointGps2Pix(temp_Ghost.getPoint());
			temp_point=m2.PointGps2Pix(temp_Ghost.getPoint());
			//  System.out.println("print ghost");
			g.setColor(Color.RED);
			g.fillOval((int)temp_point.x(), (int)temp_point.y(), 20, 20);

		}
		// print the boxes
		Iterator<Box> it4 =b.iterator();
		Box temp_Box ;
		while(it4.hasNext()) {
			temp_Box=(Box)it4.next();
			temp_point2=m2.PointGps2Pix(temp_Box.getStart());
			temp_point1=m2.PointGps2Pix(temp_Box.getEnd());
			g.setColor(Color.BLUE); 
			g.fillRect(temp_point1.ix()-(temp_point1.ix()-temp_point2.ix())+10,temp_point1.iy()+10,(temp_point1.ix()-temp_point2.ix()),(temp_point2.iy()-temp_point1.iy()));
			g.setColor(Color.RED); 
			//g.fillOval((int)temp_point1.x(), (int)temp_point1.y(), 10, 10);
			//g.fillOval((int)temp_point2.x(), (int)temp_point2.y(), 10, 10);
		}

	}


	@Override
	public void mouseClicked(MouseEvent arg) {
		Point3D current_palyer;
		x = arg.getX();
		y = arg.getY();
		Point3D p1=m1.PointPix2Gps(new Point3D(x,y,0));
		//Point3D p1=m2.PointPix2Gps(new Point3D(x,y,0));
		System.out.println(arg.getX()+","+arg.getY());
		//	System.out.println(p1);
		if(!game.getPlayers().isEmpty()) {
			//current_palyer=game.getPlayers().get(0).getPoint();
			//rot= (game.getPlayers().get(0).getPoint().north_angle(p1)+270)%360;
			rot=360-( (game.getPlayers().get(0).getPoint().north_angle(p1)+270)%360);
		}
		//System.out.println(rot);


	}




	@Override
	public void mouseEntered(MouseEvent arg0) {
		//System.out.println("mouse entered");

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public class PlayThread extends Thread{
		@Override
		public void run() 
		{

			// chang to isRunning
			//while(!game.getFruits().isEmpty()) {
				while(play1.isRuning()) {
				play1.rotate(rot);
				game.upDate(play1.getBoard());
				repaint();
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
			System.out.println(play1.getStatistics());



		}

	}
	public class RunThread extends Thread{
		@Override
		public void run() 
		{

			// chang to isRunning
			while(play1.isRuning()) {
			//while(!game.getFruits().isEmpty()) {
				shortAlgo sa=new shortAlgo(game);
				play1.rotate(sa.rotet());
				System.out.println(play1.getStatistics());
				game.upDate(play1.getBoard());
				repaint();
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
			System.out.println(play1.getStatistics());
			play1.setIDs(315823880, 204395644);
                  System.out.println(play1.getHash1());
                 String[]name=play1.getStatistics().split(","); 
			Database a=new Database();
		double averge=	a.main(play1.getHash1());
			System.out.println("my shit :" + name[2].substring(6)+ " averge time : "+averge);
			
		

		}
		

	}

}


