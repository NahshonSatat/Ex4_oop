package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
/*
import Algorithms.GameAlgorithem;
import Coords.convert;
import File_format.Path2Kml;
import Geom.Fruit;
import Geom.Game;

import Geom.Packman;
import Geom.PathPoint;
import Geom.Point3D;
import Geom.myLine;
import Threads.PlayThread;
import javafx.scene.shape.Line;
import javafx.stage.FileChooser;
 */

import Coords4.convert;
import Geom.Fruit;
import Geom.Game;
import Geom.Ghost;
import Geom.Pacmen;
import Geom.Player;
import Geom.Point3D;

import Robot.Play;


/**
 * this class is the gui class of this game
 * @author אליהו סתת
 *with the following application:
 *you can add packman - press left 
 *you can add fruit - press right
 *you can save as csv file - and load 
 *you can run the algorithm or play and see it
 *and you can export the path of the packmans to kml 
 */
public class MainWindow extends JFrame implements MouseListener
{
	private static final ImageIO ImegeIO = null;
	//private Game gameP;
	//private Map map;
	//////////////// Change to private!!!!!!!!!!!!!!!!
	public BufferedImage myImage;
	private convert m1;
	private double rot;
	Point3D current_palyer;
	//private Image p1;
	//private Image f1;
	String file_name = "data/Ex4_OOP_example5.csv";
	Play play1 ;
	Game game;
	//private boolean run=false;
	//private boolean play=false;
	// the constructor 
	//public MainWindow(Map map) throws IOException 
	public MainWindow() throws IOException 
	{
		//this.map=map;
		//gameP=new Game();
		//file_name=s;
		play1 = new Play(file_name);
		
		play1.setInitLocation(32.10486058280427,35.20937630059002);
		game=new Game();
		game.upDate(play1.getBoard());
		play1.start();
		rot=0;
		current_palyer=game.getPlayers().get(0).getPoint();
		//System.out.println("fruts"+game.getFruits().size());
		//System.out.println("pac"+game.getPackmans().size());
		//System.out.println("ghost"+game.getGhosts().size());


		//File pacFile = new File("C:\\Users\\אליהו סתת\\Downloads\\1.png");
		//p1=ImageIO.read(pacFile);
		initGUI();		
		this.addMouseListener(this);
		m1=new convert(1433,642,35.202306,32.105730,35.212407,32.101867);

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
				//				String filename = JOptionPane.showInputDialog("Name this file");
				//				JFileChooser savefile = new JFileChooser();
				//				savefile.setSelectedFile(new File(filename));
				//				savefile.showSaveDialog(savefile);
				//				int sf = savefile.showSaveDialog(null);
				//				if(sf == JFileChooser.APPROVE_OPTION){
				//
				//					filename=""+savefile.getSelectedFile();
				//
				//					try {
				//						gameP.gametocsv(filename);
				//					} catch (Exception e) {
				//						// TODO Auto-generated catch block
				//						e.printStackTrace();
				//					}
				//					JOptionPane.showMessageDialog(null, "File has been saved","File Saved",JOptionPane.INFORMATION_MESSAGE);
				//					// true for rewrite, false for override
				//
				//					//		            } catch (IOException e) {
				//					//		                e.printStackTrace();
				//					//		            }
				//				}else if(sf == JFileChooser.CANCEL_OPTION){
				//					JOptionPane.showMessageDialog(null, "File save has been canceled");
				//				}
				//				//System.out.println("game saved");
				//
				//				gameP.clear();
				//				repaint();
			}

		});

		// the "load" action
		item2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// according to https://stackoverflow.com/questions/18774652/how-to-use-jfilechooser-to-find-a-file-location
				//gameP.clear();
				//				JFileChooser fc = new JFileChooser();
				//
				//				int returnVal = fc.showOpenDialog(null);
				//				fc.showOpenDialog(null);
				//				if (returnVal == JFileChooser.APPROVE_OPTION) {
				//					File file = fc.getSelectedFile();
				//					if(!file.getAbsolutePath().contains(".csv")) {
				//						System.out.println("this is not csv file!!!");
				//					}
				//					else {
				//						System.out.println("Opening: " + file.getAbsolutePath());
				//						//gameP.clear();
				//						//try {
				//						//	gameP.csvtogame(file.getAbsolutePath());
				//						//} catch (Exception e) {
				//						//	e.printStackTrace();
				//						}
				//					}
				//					repaint();
				//				}
			}
		});

		// the "run" action
		item3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				//				run=true;
				//				GameAlgorithem ga=new GameAlgorithem(gameP);
				//				ga.GoAlgo();
				//				//gameP=ga.GoAlgo();
				//				///////////////////////////////
				//				Iterator<ArrayList<Line> > it1=gameP.getSolution().iterator();
				//				ArrayList<Line> temp_Packman ;
				//				while(it1.hasNext()) {
				//					temp_Packman=it1.next();
				//					/// temp_Packman=m1.PacGps2Pix(temp_Packman);
				//					// System.out.println("packman "+temp_Packman.GetId()+"lines "+temp_Packman.getPath().size());	 
				//					System.out.println("in the run");
				//				}
				//				//////////////////////////////
				//				repaint();
				//				//run=false;
			}

		});


		// the "new game" action
		item4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				//				System.out.println("start new gameP");
				//				gameP.clear();
				//				repaint();
			}

		});
		// the "play" action
		item5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
							//	play=true;
								//GameAlgorithem ga=new GameAlgorithem(gameP);
								//ga.GoAlgo();
				              //  System.out.println(gameP.lastTimePoint());
								PlayThread t1 = new PlayThread();
								t1.start();
//								System.out.println(play1.getBoard());
//							    play1.rotate(80);
//							    System.out.println("rotTE");
//							    System.out.println(play1.getBoard());
				//

			}});
		// the "tokml" action
		item6.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				

			}});

		menu.add(item1);
		menu.add(item2);
		menu1.add(item3);
		menu1.add(item4);
		menu1.add(item5);
		menu1.add(item6);
		menuBar.add(menu);
		menuBar.add(menu1);

		this.setMenuBar(menuBar);

		try {
			//myImage = ImageIO.read(new File(map.getPath()));
			 myImage = ImageIO.read(new File("Ariel1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}




	int x = -1;
	int y = -1;

	public void paint(Graphics g)
	{
		
		g.drawImage(myImage, 0, 0,this.getWidth(),this.getHeight(), this);
		
		m1.setFrame(this.getWidth(), this.getHeight());
		ArrayList<Pacmen>p=game.getPackmans();
		ArrayList<Fruit>f=game.getFruits();
		ArrayList<Player>pl=game.getPlayers();
		ArrayList<Ghost>gh=game.getGhosts();
		ArrayList<Geom.Box> b =game.getBoxes();
		Point3D temp_point;

		// print the player
		Player p1=pl.get(0);
		temp_point=m1.PointGps2Pix(p1.getPoint());
		g.setColor(Color.BLACK);
		g.fillOval((int)temp_point.x(), (int)temp_point.y(), 20, 20);
		
		
		// print the Packmans
		Iterator<Pacmen> it1 =p.iterator();
		Pacmen temp_Packman ;
		while(it1.hasNext()) {
			temp_Packman=(Pacmen)it1.next();
			//temp_point=m1.PointGps2Pix(temp_Packman);
			temp_point=m1.PointGps2Pix(temp_Packman.getPoint());
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
			temp_point=m1.PointGps2Pix(temp_Fruit.getPoint());
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
			temp_point=m1.PointGps2Pix(temp_Ghost.getPoint());
                 //  System.out.println("print ghost");
					g.setColor(Color.RED);
					g.fillOval((int)temp_point.x(), (int)temp_point.y(), 20, 20);

			}
//			temp_Packman=m1.PacGps2Pix(temp_Packman);
//			g.setColor(Color.yellow);
//			//g.drawImage(p1, (int)temp_Packman.Getpoint().x(),  (int)temp_Packman.Getpoint().y(), 20, 20,this);
//			g.fillOval((int)temp_Packman.Getpoint().x(), (int)temp_Packman.Getpoint().y(), 20, 20);
//
//			if(run) {
//				Iterator<Line> it3 =temp_Packman.getPath().iterator();
//				Line temp_Line ;
//				while(it3.hasNext()) {
//					temp_Line=it3.next();
//					//System.out.println("line draw!");
//					//temp_Line=m1.LineGps2Pix(temp_Line);
//					g.setColor(Color.black);
//					g.drawLine((int)temp_Line.getStartX(),(int)temp_Line.getStartY(),(int)temp_Line.getEndX(),(int)temp_Line.getEndY());
//					run=false;
//				}
//				//g.drawLine(temp_Line.getStart().ix(), temp_Line.getEnd().ix(),temp_Line.getStart().iy(), temp_Line.getStart().iy());
//			}
//
//		}
//
//		// draw the fruits
//		Iterator<Fruit> it2 =f.iterator();
//		Fruit temp_Fruit ;
//
//		// run over all the fruits
//		while(it2.hasNext()) {
//			temp_Fruit=(Fruit)it2.next();
//			temp_Fruit=m1.FruGps2Pix(temp_Fruit);
//			g.setColor(Color.GREEN);
//			g.fillOval((int)temp_Fruit.Getpoint().x(),(int) temp_Fruit.Getpoint().y(), 10, 10);
//
//		}
//
//		// draw the line
//		Iterator<ArrayList<Line> > it7=gameP.getSolution().iterator();
//		ArrayList<Line> temp_Solu ;
//		while(it7.hasNext()) {
//			temp_Solu=it7.next();
//			Iterator<Line> it8=temp_Solu.iterator();
//			Line templ;
//			g.setColor(Color.yellow);
//			while(it8.hasNext()) {
//				templ=it8.next();
//				templ=m1.LineGps2Pix(templ);
//				g.drawLine((int)templ.getStartX(),(int)templ.getStartY(),(int)templ.getEndX(),(int)templ.getEndY());
//
//			}
//
//		}

	}



	@Override
	public void mouseClicked(MouseEvent arg) {
		Point3D current_palyer;
		//if right click add Packman
		if(arg.getButton()==1) {
			x = arg.getX();
			y = arg.getY();
			//System.out.println("x:"+x+"y:"+y);
			Point3D p=m1.PointPix2Gps(m1.PointPix2Gps(new Point3D(x,y,0)));
			//System.out.println(p);
			 current_palyer=game.getPlayers().get(0).getPoint();
			System.out.println(current_palyer.north_angle(p));
			rot=(rot+ current_palyer.north_angle(p))%360; 
			//System.out.println(rot);
		}
//			x = arg.getX();
//			y = arg.getY();
//			System.out.println("x:"+x+"y:"+y);
//			Point3D p=m1.PointPix2Gps(m1.PointPix2Gps(new Point3D(y,x,0)));
//			System.out.println(p);
//			Point3D current_palyer=game.getPlayers().get(0).getPoint();
//			rot= current_palyer.north_angle(p);
//			System.out.println(rot);
////			Fruit p1=new Fruit(x,y,gameP.getFruits().size());
////			Fruit p2=m1.FruPix2Gps(p1);
////			gameP.addFru(p2);
//			repaint();
//		}
		//if left click add Fruit
		if(arg.getButton()==3) {
			x = arg.getX();
			y = arg.getY();
			System.out.println("x:"+x+"y:"+y);
			Point3D p=m1.PointPix2Gps(m1.PointPix2Gps(new Point3D(y,x,0)));
			System.out.println(p);
			 current_palyer=game.getPlayers().get(0).getPoint();
			rot= current_palyer.north_angle(p); 
			System.out.println(rot);
//			Fruit p1=new Fruit(x,y,gameP.getFruits().size());
//			Fruit p2=m1.FruPix2Gps(p1);
//			gameP.addFru(p2);
			//repaint();
		}
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
			
				for (int j = 0; j < 1000; j++) {
					
					//System.out.println(play1.getBoard());
				    play1.rotate(rot);
				    //System.out.println("rotTE");
				    //System.out.println(play1.getBoard());
					game.upDate(play1.getBoard());
					ArrayList<String> board_data = play1.getBoard();
					for(int i=0;i<board_data.size();i++) {
						//System.out.println(board_data.get(i));
					}
					//System.out.println("fruts"+game.getFruits().size());
					//System.out.println("pac"+game.getPackmans().size());
					//System.out.println("ghost"+game.getGhosts().size());

					repaint();
						try {
							Thread.sleep(20);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						
					}

				
			
			}

		}

	}


