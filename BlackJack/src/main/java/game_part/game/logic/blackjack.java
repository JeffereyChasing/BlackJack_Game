package game_part.game.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class blackjack {
		
	//this class is to simulate the actual game part, it will have necessary element such that a card deck
	//which consists of 6 decks of 52 cards (no jokers). The reason that I used 6 decks is because many casinos
	//apply the same pattern (6 decks) 
	
	public ArrayList<myCard> cardDeck = new ArrayList<>();
	// this attribute represents the card deck that the game will use, it only contains myCard object
	
	public static dealer dl = new dealer ();
	public static player pl = new player ();
	//create dealer and player corresponding to the live game
	
	public void deckUp(){
        for (int k = 0; k < 6; k++) {
        	//6 decks
            for (int i = 2; i < 15; i++) {
            	//the smallest value is 2, in blackjack, Ace can be 1 or 11, but for now, initialize to 11
                for (int j = 1; j < 5; j++) {
                	//for every suit
                    myCard c = new myCard(i, j);
                    cardDeck.add(c);
                }
            }
    }
        // we use a triple loop to go through every number and suit to generate myCard object
        // and then we add it to the card deck
        // 6 decks of card
    }
	//create deck
	
	public void Shuffler(){
	        Random rand = new Random();
	        //create random
	        for (int i = 0; i < rand.nextInt(1,10); i++){
	            Collections.shuffle(cardDeck);
	        }
	        //shuffle random number of time
	}
	//shuffle the deck

	public ArrayList bust(int value, int acecount) {
		//function that determine whether a hand is over 21 based on value and ace count  
		//output format: actual value and whether or not over 21 (1 is yes, 0 is no)
		
		ArrayList<Integer> result = new ArrayList<>(2);
		// two number in the outputted result
		if (value <21) {
			result.add(value);
			result.add(0);
			return result;
		}
		// in the case that the hand value is less than 21, it's for sure not a bust
		
		while (acecount>0 && value > 21) {
			value -=10;
			acecount -=1;
		}
		//while there is still Ace available and the value is over 21, we use each Ace to subtract 10 from the total value
		
		if (value > 21) {
			result.add(value);
			result.add(1);
		}else {
			result.add(value);
			result.add(0);
		}
		//add actual value and whether or not bust to the result 
		
		return result;
		//after all the Ace has been used, check whether the value is over 21
		
	}
	
	public blackjack() {
		deckUp();
		//as the start of the game, create the card deck (6 card dekcs)
	    Shuffler();
	    //shuffle
	    
	    for (int i = 0; i < 4; i++){
	    	//the reason that for-loop is up to 3 is that both dealer and player get 4 cards (2 for each), so there ia total of 4
	    	if (i % 2 == 0) {
	    		//player get even cards(so 0 and 2 (index))
	    		myCard current = cardDeck.remove(i);
	    		//remove the card
	            dl.dealerall.add(current);
	            //add current card to dealer's card
	            dl.dealerTotal += current.getNumber();
	            //add current value to dealer's total value
	            if(current.getNumber()==11) {
	            	dl.dealerAce +=1;
	            }
	            //if the card is A, add it to the record to count how many A in dealer's hand		
	    	}else {
	    		//dealer get odd cards(so 1 and 3 (index))
	    		myCard current = cardDeck.remove(i);
	    		//remove the card
	            pl.playerall.add(current);
	            //add current card to player's card
	            pl.playerTotal += current.getNumber();
	            //add current value to player's total value
	            if(current.getNumber()==11) {
	            	pl.playerAce +=1;
	            }
	            //if the card is A, add it to the record to count how many A in player's hand		
	    	}
        }
	    
	    System.out.println("player: " + pl.playerTotal + pl.playerall);
	    System.out.println("dealer: " + dl.dealerTotal + dl.dealerall);
	    //sample output to see whether the card are being corerctly outputted and whether the values are correct
	   
	    
	}
	//constructor
	
	public void reset() {
		this.cardDeck = new ArrayList<>();
		//empty the current deck for new game
		deckUp();
		Shuffler();
		//deck up and shuffle
		dl.dealerAce=0;
		dl.dealerall=new ArrayList<>();
		dl.dealerTotal = 0;
		pl.playerAce=0;
		pl.playerall=new ArrayList<>();
		pl.playerTotal = 0;
		// reset the player and dealer stats
		
	    for (int i = 0; i < 4; i++){
	    	if (i % 2 == 0) {
	    		//dealer get the even card
	    		myCard current = cardDeck.remove(i);
	            dl.dealerall.add(current);
	            //add current card to dealer's card
	            dl.dealerTotal += current.getNumber();
	            //add current value to dealer's total value
	            if(current.getNumber()==11) {
	            	dl.dealerAce +=1;
	            }
	            //if the card is A, add it to the record to count how many A in dealer's hand		
	    	}else {
	    		//player get the odd card
	    		myCard current = cardDeck.remove(i);
	            pl.playerall.add(current);
	            //add current card to player's card
	            pl.playerTotal += current.getNumber();
	            //add current value to player's total value
	            if(current.getNumber()==11) {
	            	pl.playerAce +=1;
	            }
	            //if the card is A, add it to the record to count how many A in player's hand		
	    	}
        }
	}
	
	
	
	
	public static void main(String[]args) {
		blackjack b1 = new blackjack();
	}
	
	

}
