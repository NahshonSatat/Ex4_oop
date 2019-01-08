package Geom;

import java.util.ArrayList;

/**
 * this class represent a game whit packmans and fruits ghost box and player
 * this is the main class that represent all the data that is the output from the paly class 
 */


public class Game {
	private ArrayList<Player> Players;
	private ArrayList<Pacmen> Packmans;
	private ArrayList<Fruit> Fruits;
	private ArrayList<Box> Boxes;
	private ArrayList<Ghost> Ghosts;
    private Map map ;
	public Game(Map map) {
		Packmans=new ArrayList<Pacmen>();
		Fruits=new ArrayList<Fruit>();
		Players=new ArrayList<Player>();
		Boxes=new ArrayList<Box>();
		Ghosts=new ArrayList<Ghost>();
		this.map=map;
	}

	
	/**
	 * update the game by the output from the play class 
	 */
	public void upDate(ArrayList<String> board_data) {
		Packmans.clear();
		Fruits.clear();
		Players.clear();
		Boxes.clear();
		Ghosts.clear();
		for(int i=0;i<board_data.size();i++) {
			if(board_data.get(i).charAt(0)=='M') {
				Player temp_player=new Player(board_data.get(i));
				//System.out.println(temp_player);
				Players.add(temp_player);
			}
			if(board_data.get(i).charAt(0)=='P') {
				Pacmen temp_Pacmen=new Pacmen(board_data.get(i));
				//System.out.println(temp_Pacmen);
				Packmans.add(temp_Pacmen);
			}
			if(board_data.get(i).charAt(0)=='F') {
				Fruit temp_Fruit=new Fruit(board_data.get(i));
				//System.out.println(temp_Fruit);
				Fruits.add(temp_Fruit);
			}
			if(board_data.get(i).charAt(0)=='G') {
				Ghost temp_Ghost=new Ghost(board_data.get(i));
				//System.out.println(temp_Ghost);
				Ghosts.add(temp_Ghost);
			}
			if(board_data.get(i).charAt(0)=='B') {
				Box temp_Box=new Box(board_data.get(i));
				Boxes.add(temp_Box);
			}
		}

	}

	/**
	 * clear the game
	 */
	public void clear() {
		Packmans.clear();
		Fruits.clear();
		Players.clear();
		Boxes.clear();
		Ghosts.clear();
	}
	public Map getMap() {
		return this.map;
	}
	public ArrayList<Pacmen> getPackmans(){
		return this.Packmans;
	}
	public ArrayList<Fruit> getFruits(){
		return this.Fruits;
	}
	public ArrayList<Player> getPlayers(){
		return this.Players;
	}
	public ArrayList<Box> getBoxes(){
		return this.Boxes;
	}
	public ArrayList<Ghost> getGhosts(){
		return this.Ghosts;
	}

}
