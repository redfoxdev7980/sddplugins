/*
    Assignment-2 by Balaji
*/

package charlie.client;

import charlie.card.Card;
import charlie.card.Hand;
import charlie.plugin.IAdvisor;
import charlie.util.Play;

/**
 *
 * @author Balaji
 */
public class Advisor implements IAdvisor {

    @Override
    public Play advise(Hand myHand, Card upCard) {
        BasicStrategy bs = new BasicStrategy();
        Play play = bs.getPlay(myHand, upCard);
        return play;
    }
    
}
