/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mycharlie.plugin;

import charlie.card.Card;
import charlie.card.Hand;
import charlie.plugin.IAdvisor;
import charlie.util.Play;
import java.util.HashMap;
import charlie.card.Card.Suit;

/**
 *
 * @author BMurali
 */
public class SimpleAdvisor implements IAdvisor{
    
    @Override
    public Play advise(Hand myHand, Card upCard) {
        
        //get the value of the hand and assign it to local variable instead of calling the
        //function every time.
        int myValue = myHand.getValue();        
        
        //make sure there are atleast 2 cards before suggesting any play and total value < 21.
        if(myHand.size() != 2 || myValue >= 21 || myValue < 2)
            return Play.NONE;

        //condition a and b from the specification. When pair and value is A or 8
        if((myHand.isPair()) && (myHand.getCard(0).value() == 1 || myHand.getCard(0).value() == 8)){
            return Play.SPLIT;
        }
        //condition b and e from specification. This condition covers all stay cases.
        else if((myValue >= 17) || (myValue >=12 && myValue <= 16 && upCard.getRank()+10 <= 16)){
            return Play.STAY;
        }
        //condition c and f from the specification. This condition covers all hit cases.
        else if((myValue <= 10) || (myValue >= 12 && myValue <= 16 && upCard.getRank()+10 > 16)){
            return Play.HIT;
        }
        //condition d from specification. When 11 just double down.
        else if(myValue == 11){
            return Play.DOUBLE_DOWN;
        }
        //something really went wrong
        return Play.NONE;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SimpleAdvisor sc = new SimpleAdvisor();
        System.out.println(sc.advise(null,null));
    }    
}
