package game_part.game.logic;


import java.util.Dictionary;
import java.util.Hashtable;

import org.eclipse.jdt.internal.compiler.ast.ThisReference;

public class myCard {
	
	public static Dictionary<Integer, String> suitmap = new Hashtable<>();
	public int number;
	public int suit;
	//number would simply represent the card value (the largest is 10 in blackjack game (besides Ace which has 11))
	//and then different suits would all be transformed to numeric values
	
	// the major attributes I will use is number and suit
	// even though suit does not matter in BlackJack Game but it's essential 
	// for presenting correct images
	
	public myCard() {
		
	}
	//this is the empty constructor
	
	public myCard(int number, int suit) {
		this.setNumber(number);
		this.setSuit(suit);
		suitmap.put(1,"Heart");
		suitmap.put(2,"Spades");
		suitmap.put(3,"Clubs");
		suitmap.put(4,"Diamonds");
		// defined the rule for suit transformation
	}
	// define card

	public String toString() {
		return suitmap.get(this.suit) + "_" + this.number;
	}
	//String representation
	
	public int getNumber() {
		if (this.number == 14) {
			return 11;
		}
		// when the card is Ace, we set it to 11
		// we prioritize this condition before the larger than 10 check
		
		if (this.number > 10) {
			return 10;
		}
		// for j q k, they are all 10
		
		return number;
		//return the number
	}

	public void setNumber(int number) {
		this.number = number;
	}
	//getters and setters for number

	public int getSuit() {
		return suit;
	}

	public void setSuit(int suit) {
		this.suit = suit;
	}
	//getters and setter for value suit
	
	public String imagePath() {
		return "../game_part/game/assets/" + this.number +"/"+ this.number + "_" + this.suit+".png";
	}
	// this method is for getting the right path based on number and suit
	

}












