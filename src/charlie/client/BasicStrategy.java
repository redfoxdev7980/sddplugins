
package charlie.client;

import charlie.card.Card;
import charlie.card.Hand;
import charlie.util.Play;

/**
 * This class is an incomplete implementation of the Basic Strategy rules.
 * @author maristuser
 */
public class BasicStrategy {
    public final static Play P = Play.SPLIT;
    public final static Play H = Play.HIT;
    public final static Play S = Play.STAY;
    public final static Play D = Play.DOUBLE_DOWN;
    
    Play[][] section1Rules = {
                // 2  3  4  5  6  7  8  9  T  A
        /* 21 */ { S, S, S, S, S, S, S, S, S, S},
        /* 20 */ { S, S, S, S, S, S, S, S, S, S},
        /* 19 */ { S, S, S, S, S, S, S, S, S, S},
        /* 18 */ { S, S, S, S, S, S, S, S, S, S},
        /* 17 */ { S, S, S, S, S, S, S, S, S, S},
        /* 16 */ { S, S, S, S, S, H, H, H, H, H},
        /* 15 */ { S, S, S, S, S, H, H, H, H, H},
        /* 14 */ { S, S, S, S, S, H, H, H, H, H},
        /* 13 */ { S, S, S, S, S, H, H, H, H, H},
        /* 12 */ { H, H, S, S, S, H, H, H, H, H}
    };
    Play[][] section2Rules = {
        /* 11 */ { D, D, D, D, D, D, D, D, D, H},
        /* 10 */ { D, D, D, D, D, D, D, D, S, S},
        /* 9 */  { H, D, D, D, H, H, H, H, H, H},
        /* 8 */  { H, H, H, H, H, H, H, H, H, H},
        /* 7 */  { H, H, H, H, H, H, H, H, H, H},
        /* 6 */  { H, H, H, H, H, H, H, H, H, H},
        /* 5 */  { H, H, H, H, H, H, H, H, H, H}        
    };
    Play[][] section3Rules = {
        /* A10 */  { S, S, S, S, S, S, S, S, S, S} ,
        /* A9 */  { S, S, S, S, S, S, S, S, S, S} ,
        /* A8 */  { S, S, S, S, S, S, S, S, S, S} ,
        /* A7 */  { S, D, D, D, D, S, S, H, H, H} ,
        /* A6 */  { H, D, D, D, D, H, H, H, H, H} ,
        /* A5 */  { H, H, D, D, D, H, H, H, H, H} ,
        /* A4 */  { H, H, D, D, D, H, H, H, H, H} ,
        /* A3 */  { H, H, H, D, D, H, H, H, H, H} ,
        /* A2 */  { H, H, H, D, D, H, H, H, H, H} 
    };
    Play[][] section4Rules = {
        /* 1010 */{ S, S, S, S, S, S, S, S, S, S} ,
        /* 99 */  { P, P, P, P, P, S, P, P, S, S} ,
        /* 88 */  { P, P, P, P, P, P, P, P, P, P} ,        
        /* 77 */  { P, P, P, P, P, P, H, H, H, H} ,
        /* 66 */  { P, P, P, P, P, H, H, H, H, H} ,
        /* 55 */  { D, D, D, D, D, D, D, D, H, H} ,
        /* 44 */  { H, H, H, S, S, H, H, H, H, H} ,
        /* 33 */  { P, P, P, P, P, P, H, H, H, H} , 
        /* 22 */  { P, P, P, P, P, P, H, H, H, H} ,
        /* AA */  { P, P, P, P, P, P, P, P, P, P} 
    };


    
    
    public Play getPlay(Hand hand, Card upCard) {
        Card card1 = hand.getCard(0);
        Card card2 = hand.getCard(1);
        
        if(hand.isPair()) {
            return doSection4(hand,upCard);
        }
        else if(hand.size() == 2 && (card1.getRank() == Card.ACE || card2.getRank() == Card.ACE)) {
            return doSection3(hand,upCard);
        }
        else if(hand.getValue() >=5 && hand.getValue() < 12) {
            return doSection2(hand,upCard);
        }
        else if(hand.getValue() >= 12)
            return doSection1(hand,upCard);
        
        return Play.NONE;
    }
    
    /**
     * Does section 1 processing of the basic strategy, 12-21 (player) vs. 2-A (dealer)
     * @param hand Player's hand
     * @param upCard Dealer's up-card by Prof. Coleman
     */
    protected Play doSection1(Hand hand, Card upCard) {
        int value = hand.getValue();
     
        // Subtract 21 since the player's hand starts at 21 and we're working
        // our way down through section 1
        int rowIndex = 21 - value;
        
        Play[] row = section1Rules[rowIndex];
        
        // Subtract 2 since the dealer's up-card start at 2
        int colIndex = upCard.getRank() - 2;
         
        if(upCard.isFace())
            colIndex = 10 - 2;

        // Ace is the 10th card (index 9)
        else if(upCard.isAce())
            colIndex = 9;
        
        Play play = row[colIndex];
        
        return play;
    }
    /**
     * Does section 2 processing of the basic strategy, 12-21 (player) vs. 2-A (dealer)
     * @param hand Player's hand
     * @param upCard Dealer's up-card
     */
    protected Play doSection2(Hand hand, Card upCard) {
        int value = hand.getValue();
        
        // Subtract 21 since the player's hand starts at 21 and we're working
        // our way down through section 1
        int rowIndex = 11 - value;
        
        Play[] row = section2Rules[rowIndex];
        
        // Subtract 2 since the dealer's up-card start at 2
        int colIndex = upCard.getRank() - 2;
         
        if(upCard.isFace())
            colIndex = 10 - 2;

        // Ace is the 10th card (index 9)
        else if(upCard.isAce())
            colIndex = 9;
        
        Play play = row[colIndex];
        
        return play;
    }
    protected Play doSection3(Hand hand, Card upCard) {
        //get the value of the hand
        int value = hand.getValue();
        int rowIndex = 21 - value;
        Play[] row = section3Rules[rowIndex];
        // Subtract 2 since the dealer's up-card start at 2
        int colIndex = upCard.getRank() - 2;
         
        if(upCard.isFace())
            colIndex = 10 - 2;

        // Ace is the 10th card (index 9)
        else if(upCard.isAce())
            colIndex = 9;
        
        Play play = row[colIndex];
        
        return play;        
    }
    protected Play doSection4(Hand hand, Card upCard){

        int rowIndex = 10 - hand.getCard(0).value();
        Play[] row = section4Rules[rowIndex];
        // Subtract 2 since the dealer's up-card start at 2
        int colIndex = upCard.getRank() - 2;
         
        if(upCard.isFace())
            colIndex = 10 - 2;

        // Ace is the 10th card (index 9)
        else if(upCard.isAce())
            colIndex = 9;
        
        Play play = row[colIndex];
        
        return play;                
    }
}
