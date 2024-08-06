package user_interface;



import javax.swing.*;

import game_part.game.logic.blackjack;
import game_part.game.logic.myCard;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class user_interface extends JFrame {
	
	JFrame frame = new JFrame("Game");
	//Create a frame
    blackjack g = new blackjack();
    //initialize an instance of blackjack
    
    String dealertitle = "Dealer's Hand: ";
    String playertitle = "Player's Hand: ";
    // create String Variables for dealer and player title
    
    String message = "Sorry you lost";
    String message2 = "Congrats~ You won!";
    //message of both winning and losing

   
    
    
    public user_interface() {
    	//constructor
    	JPanel bottom = new JPanel();
    	// new panel bottom
        JButton hitButton = new JButton("Hit");
        JButton stayButton = new JButton("Stay");
        JButton surrenderButton = new JButton("Surrender");
        JButton againButton = new JButton("Again");
        // buttons for different function
        JButton whethersurrender = new JButton();
        //indicate whether the player surrenders
        JButton chipsButton = new JButton("More Chips");
        //button for adding chips
        chipsButton.setBackground(Color.yellow);
        chipsButton.setOpaque(true);
        //different color for chips button to make it more stand-out
        
        
        whethersurrender.setEnabled(true);
        //on the start state, the surrender is set to false
        

       
        
        JPanel panel = new JPanel(){
        	//this is our main panel
            @Override
            public void paintComponent(Graphics graph){
                super.paintComponent(graph);
                //use graph to write string and image to the screen
               
                String win = "Congrats~ You Won!";
                //winning message
                
                Image sample = new ImageIcon(getClass().getResource("../game_part/game/assets/cardback.png")).getImage();
                //visualize image
                //get the image of card back
                graph.drawImage(sample,100,100,100,150,null );
                //the parameters are as follow: image to be drawn, how many pixel from upper left(x-axis)
                //, how many pixels from upper-left (y-axis), width, and height of the image, image observer
         
                
                
                graph.setColor(Color.WHITE);
                // set the string color
                graph.setFont(new Font("Arial", Font.BOLD, 30));
                //set font, weight, fontsize
                graph.drawString(dealertitle , 100, 50);  
                //draw the dealer's title, 100 from upper left (x-axis), 50 from (y-axis)
              
              
                
                for (int i =1;i<g.dl.dealerall.size();i++){
                    Image cImage = new ImageIcon(getClass().getResource( g.dl.dealerall.get(i).imagePath())).getImage();
                    graph.drawImage(cImage,100+110+(150+50)*i,100,100,150,null );
                }
                //iterate through dealer's hand to visualize (start from 1 because 
                //we do not show the first card until the player's action is over
                
                graph.setColor(Color.WHITE);
                // set the string color
                graph.setFont(new Font("Arial", Font.BOLD, 30));
                //set font, weight, fontsize
                graph.drawString(playertitle, 100, 350);  
                //draw the player's title, 100 from upper left (x-axis), 350 from (y-axis)

                
                for (int i =0;i<g.pl.playerall.size();i++){
                	System.out.println(g.pl.playerall.get(i).imagePath());
                    Image cImage = new ImageIcon(getClass().getResource( g.pl.playerall.get(i).imagePath())).getImage();
                    graph.drawImage(cImage,100+110+(150+50)*i,400,100,150,null);
                }
                //we visualize all the player's card                
                
                
                graph.setColor(Color.WHITE);
                // set the string color
                graph.setFont(new Font("Arial", Font.BOLD, 30));
                //set font, weight, fontsize
                graph.drawString(Integer.toString(  ((Integer) g.bust(g.pl.playerTotal,g.pl.playerAce).get(0)).intValue()), 500, 350);  
                //draw player's hand value
                
                graph.setColor(Color.WHITE);
                // set the string color
                graph.setFont(new Font("Arial", Font.BOLD, 30));
                //set font, weight, fontsize
                
                graph.drawString("Your Chips: "+Integer.toString(g.pl.chips), 1200, 300);  
                //drawing chip amount
                
                if (g.pl.chips < 10) {
              	  stayButton.setEnabled(false);
                  hitButton.setEnabled(false);
                  surrenderButton.setEnabled(false);
                  againButton.setEnabled(false);
        		  // when surrender, disable all buttons
                  graph.drawString("Please press chips button to buy more chips", 800, 350);  
                  //when the chips is not enough for play, disable action buttons and 
                  //and let user know the chip is not enough
                };
                
                
                
                
                
                
                
                if(surrenderButton.isEnabled()) {
                	// in this case, we know that the play is still going (player can still take action)
                	
                	int dealer_every_value = ((Integer) g.bust(g.dl.dealerTotal,g.dl.dealerAce).get(0)).intValue();
                	int dealer_display_value = dealer_every_value - g.dl.dealerall.get(0).getNumber();
                    graph.drawString(Integer.toString( dealer_display_value), 500, 50);  
                    // if the play is still in action, we do not add the card value of the first hidden card into dealer's hand value

                }else {
                	int dealer_every_value = ((Integer) g.bust(g.dl.dealerTotal,g.dl.dealerAce).get(0)).intValue();
                    graph.drawString(Integer.toString( dealer_every_value), 500, 50);  
                    //when the action is over, we show dealer's all value
                }

                //draw player's hand value
                   
                if(!whethersurrender.isEnabled()) {
                	graph.drawString("You've surrendered",600,600);
                	g.pl.chips -= 5;
            		//surrender only lost 5 chips, half the amount
            	}
                //surrender case
                
                if(!surrenderButton.isEnabled() && !hitButton.isEnabled()  && !stayButton.isEnabled() && whethersurrender.isEnabled()) {
                	// when we reach this point, it means that the winner of this game is determined
                	// and for now we need to set the priority for winning conditions
  
                	Image cImage = new ImageIcon(getClass().getResource( g.dl.dealerall.get(0).imagePath())).getImage();
                    graph.drawImage(cImage,100+110,100,100,150,null );
                    // now we can show dealer's first hidden card
                	

                	if(((Integer) g.bust(g.pl.playerTotal,g.pl.playerAce).get(0)).intValue()>21) {
                		win = "Sorry you lost~";
                		g.pl.chips -=10;
                		//player bust
                	}else if(((Integer) g.bust(g.dl.dealerTotal,g.dl.dealerAce).get(0)).intValue()>21) {
                		win = "Yes! you won";
                		g.pl.chips +=10;
                		//dealer bust
                	}else if(((Integer) g.bust(g.dl.dealerTotal,g.dl.dealerAce).get(0)).intValue() ==((Integer) g.bust(g.pl.playerTotal,g.pl.playerAce).get(0)).intValue() ) {
                		win = "Tie";
                		//tie
                	}else if(((Integer) g.bust(g.pl.playerTotal,g.pl.playerAce).get(0)).intValue()>((Integer) g.bust(g.dl.dealerTotal,g.dl.dealerAce).get(0)).intValue()) {
                		win = "Yes! you won";
                		g.pl.chips +=10;
                		//bigger than dealer's hand
                	}else {
                		win = "Sorry you lost~";
                		g.pl.chips -=10;
                		//smaller than dealer's hand
                	}
                	//adjust the message accordingly
                	                		
                	graph.drawString(win,600,600);
                	//show the message
                	
                	
                }
                
                
                
                
                
                
            }
        };
        
       
        
        stayButton.addActionListener(new ActionListener() {
        	//set action for clicking button
            public void actionPerformed(ActionEvent e) {
            	stayButton.setEnabled(false);
                hitButton.setEnabled(false);
                surrenderButton.setEnabled(false);
                //once stayed, all buttons should be set to unclickable
                
                while (((Integer) g.bust(g.dl.dealerTotal, g.dl.dealerAce).get(1)).intValue() ==0 && ((Integer) g.bust(g.dl.dealerTotal, g.dl.dealerAce).get(0)).intValue() <17 ) {
                	// use the bust function which outputs value and whether or not it's a bust
                	// to continuously hit the card for dealer if not bust and stop until the value larger than 17
                	myCard actioncard = g.cardDeck.remove(0);
                	g.dl.dealerall.add(actioncard);
                	g.dl.dealerTotal += actioncard.getNumber();
                	//add card to dealer's hand consistently
                	if (actioncard.getNumber() == 11) {
                		g.dl.dealerAce +=1;
                	}
                	//update ace count for dealer

                }
                
                System.out.println(((Integer) g.bust(g.dl.dealerTotal, g.dl.dealerAce).get(0)).intValue());

                panel.repaint();
                //refresh the page
            }
        }); 

        hitButton.addActionListener(new ActionListener() {
        	//set action for clicking button
            public void actionPerformed(ActionEvent e) {
            	myCard actioncard = g.cardDeck.remove(0);
                g.pl.playerTotal += actioncard.getNumber();
                // take the first card in the deck, add value to our record
                if (actioncard.getNumber() == 11) {
                    g.pl.playerAce +=  1;
                }
                //check whether it's a Ace
                g.pl.playerall.add(actioncard);
                // add the actual card to the player's hand
               
                if (((Integer) g.bust(g.pl.playerTotal, g.pl.playerAce).get(1)).intValue() == 1) {
                	//busted
          		  	stayButton.setEnabled(false);
                    hitButton.setEnabled(false);
                    surrenderButton.setEnabled(false);
                    //if busted, disable all buttons


                }
                
                panel.repaint();
                //refresh the page
            }
        });    
        
        
        chipsButton.addActionListener(new ActionListener() {
        	  public void actionPerformed(ActionEvent e) {
          		  
        		  g.pl.chips+=100;
        		  againButton.setEnabled(true);
                  panel.repaint();
                  // addding 100 chips
                  // enable the again button
              }
          }); 
        
        againButton.addActionListener(new ActionListener() {
      	  public void actionPerformed(ActionEvent e) {
      		  
      		  	whethersurrender.setEnabled(true);
      	    	stayButton.setEnabled(true);
      	        hitButton.setEnabled(true);
      	        surrenderButton.setEnabled(true);
      	        //reset all button to clickable
      		  	g.reset();
      		  	//reset the game
                panel.repaint();
                //refresh the page
            }
        }); 
        
        surrenderButton.addActionListener(new ActionListener() {
        	  public void actionPerformed(ActionEvent e) {
          		  
        		  stayButton.setEnabled(false);
                  hitButton.setEnabled(false);
                  surrenderButton.setEnabled(false);
        		  // when surrender, disable all buttons
                  whethersurrender.setEnabled(false);
                  // this is the surrender indicator: true means already surrendered
                  panel.repaint();
                  //refresh the page
              }
        }); 
        
        
      
        
        

        frame.add(panel, BorderLayout.CENTER);
        //add panel to center
        frame.add(bottom, BorderLayout.SOUTH);
        //add bottom to bottom
        
        
        bottom.add(hitButton);
        bottom.add(stayButton);
        bottom.add(surrenderButton);
        bottom.add(againButton);
        bottom.add(chipsButton);
        //add buttons to bottom panel
        
        frame.setSize(1500,1000);
        //set interface dimensions
        frame.setVisible(true);
        //visible to user
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //so the user can exit the game
        
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.BLACK);
        //setting layout and background color
        
  
    }
    
   

    public static void main(String []args) {
    	user_interface u1 = new user_interface();
    	//start the instance of user_interface
    }
}
    
