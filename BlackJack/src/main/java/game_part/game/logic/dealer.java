package game_part.game.logic;

import java.util.ArrayList;

public class dealer {
	
    public ArrayList<myCard> dealerall = new ArrayList<>();
    //dealer's hand
    public int dealerTotal;
    //dealer's total card value
    public int dealerAce; 
    //how many Aces dealer has
    
    public dealer() {
        this.dealerall =  new ArrayList<myCard>();
        this.dealerTotal = 0;
        this.dealerAce = 0;
    }
    //basic constructor for dealer class
    
    public dealer(ArrayList<myCard> dealerall, int dealerTotal, int dealerAce) {
        this.dealerall = dealerall; 
        this.dealerTotal = dealerTotal;
        this.dealerAce = dealerAce;
    }
    //constructor with parameters

}	
