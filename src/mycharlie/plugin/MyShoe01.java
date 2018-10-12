/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mycharlie.plugin;

import charlie.card.Shoe;
import java.util.Random;

/**
 *
 * @author Balaji
 */
public class MyShoe01 extends Shoe {
    @Override
    public void init() {
        super.ran = new Random(/*1*/);
                        
        super.numDecks = 1;
        
        super.load();
        
        super.shuffle();
    }    
}
