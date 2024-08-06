package game_part.game.logic;

import java.util.ArrayList;

public class player {
	
    public ArrayList<myCard> playerall = new ArrayList<>();
    //player's hand
    public int playerTotal;
    //player's hand total value
    public int playerAce; 
    //how many Aces player has
    public int chips;
    //number of chips that a player has
    
    public player() {
    	this.playerall =  new ArrayList<myCard>();
        this.playerTotal = 0;
        this.playerAce = 0;
        this.chips = 10;
        //initially set to 10, each game is cost 10 to play
    }
    //basic constructor for player class
    
    public player(ArrayList<myCard> playerall, int playerTotal, int playerAce) {
        this.playerall = playerall; 
        this.playerTotal = playerTotal;
        this.playerAce = playerAce;
    }
    //constructor with parameters

}	
