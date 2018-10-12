/*
    Assignment-2 by Balaji Murali
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
        return Play.NONE;
    }
    
}
