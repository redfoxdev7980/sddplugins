/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package charlie.client;

import charlie.card.Hid;
import charlie.dealer.Seat;
import charlie.message.Message;
import charlie.message.view.to.Blackjack;
import charlie.message.view.to.Bust;
import charlie.message.view.to.Charlie;
import charlie.message.view.to.Loose;
import charlie.message.view.to.Win;
import charlie.message.view.to.Outcome;
import charlie.message.view.to.Push;
import charlie.message.view.to.SplitResponse;
import charlie.plugin.ITrap;
import org.apache.log4j.Logger;

/**
 *
 * @author Balaji
 */
public class Trap implements ITrap {

    protected static Logger LOG = Logger.getLogger(Trap.class);
    protected int youWin = 0;
    protected int youLoose = 0;
    protected int youPush = 0;
    protected int youBreak = 0;
    protected int youBlackjack = 0;    
    protected int youCharlie = 0;
    protected int totalSplits = 0;

    protected int dealerWin = 0;
    protected int dealerLoose = 0;
    protected int dealerPush = 0;
    
    protected int totalHandsYou = 0;
    protected int totalHandsDealer = 0;
    protected double bankroll = charlie.util.Constant.PLAYER_BANKROLL;

    @Override
    public void onSend(Message msg) { 
    }

    @Override
    public void onReceive(Message msg) 
    {
        //check if the message recived is Win and if the message broadcasted belongs to YOU. Add the count for YOU and Dealer using the variables. Calculate the bankroll at the end.
        if (msg instanceof Win) {
            Hid hid = ((Outcome) msg).getHid();
            if (hid.getSeat() == Seat.YOU) {
                youWin++;
                dealerLoose++;
                bankroll = bankroll + hid.getAmt();
            }
            this.writeToLog(youWin, youLoose, youPush, youBreak, youBlackjack, youCharlie, totalSplits, dealerWin, dealerLoose, dealerPush, totalHandsYou, totalHandsDealer,bankroll);
        }
        //check if the message recived is split and if the message broadcasted belongs to YOU. Add the count for splits.
        else if(msg instanceof SplitResponse) {
            Hid hid = ((SplitResponse) msg).getOrigHid();
            Hid newHid = ((SplitResponse) msg).getNewHid();
            if ((hid.getSeat() == Seat.YOU) && (newHid.getSeat() == Seat.YOU)) {
                totalSplits++;
            }
            this.writeToLog(youWin, youLoose, youPush, youBreak, youBlackjack, youCharlie, totalSplits, dealerWin, dealerLoose, dealerPush, totalHandsYou, totalHandsDealer,bankroll);
        }
        //check if the message recived is Blackjack and if the message broadcasted belongs to YOU. Add the count for YOU and Dealer using the variables. Calculate the bankroll at the end.
        else if (msg instanceof Blackjack) {
            Hid hid = ((Outcome) msg).getHid();
            if (hid.getSeat() == Seat.YOU) {
                youBlackjack++;
                dealerLoose++;
                bankroll = bankroll + hid.getAmt();
            } 
            this.writeToLog(youWin, youLoose, youPush, youBreak, youBlackjack, youCharlie, totalSplits, dealerWin, dealerLoose, dealerPush, totalHandsYou, totalHandsDealer, bankroll);
        }
        //check if the message recived is Loose and if the message broadcasted belongs to YOU. Add the count for YOU and Dealer using the variables. Calculate the bankroll at the end.
        else if (msg instanceof Loose) {
            Hid hid = ((Outcome) msg).getHid();
            if (hid.getSeat() == Seat.YOU) {
                youLoose++;
                dealerWin++;
                bankroll = bankroll - hid.getAmt();
            } 
            this.writeToLog(youWin, youLoose, youPush, youBreak, youBlackjack, youCharlie, totalSplits, dealerWin, dealerLoose, dealerPush, totalHandsYou, totalHandsDealer, bankroll);
        }
        //check if the message recived is Bust and if the message broadcasted belongs to YOU. Add the count for YOU and Dealer using the variables. Calculate the bankroll at the end.
        else if (msg instanceof Bust) {
            Hid hid = ((Outcome) msg).getHid();            
            if (hid.getSeat() == Seat.YOU) {
                youBreak++;
                dealerWin++;
                bankroll = bankroll - hid.getAmt();
            }
            this.writeToLog(youWin, youLoose, youPush, youBreak, youBlackjack, youCharlie, totalSplits, dealerWin, dealerLoose, dealerPush, totalHandsYou, totalHandsDealer, bankroll);
        } 
        //check if the message recived is Push and if the message broadcasted belongs to YOU. Add the count for YOU and Dealer using the variables. Calculate the bankroll at the end.
        else if (msg instanceof Push) {
            Hid hid = ((Outcome) msg).getHid();            
            if (hid.getSeat() == Seat.YOU) {
                youPush++;
                dealerPush++;
            }
            this.writeToLog(youWin, youLoose, youPush, youBreak, youBlackjack, youCharlie, totalSplits, dealerWin, dealerLoose, dealerPush, totalHandsYou, totalHandsDealer, bankroll);
        }
        //check if the message recived is Charlie and if the message broadcasted belongs to YOU. Add the count for YOU and Dealer using the variables. Calculate the bankroll at the end.
        else if (msg instanceof Charlie) {
            Hid hid = ((Outcome) msg).getHid();            
            if (hid.getSeat() == Seat.YOU) {
                youCharlie++;
                dealerLoose++;
                bankroll = bankroll + hid.getAmt();
            }
            this.writeToLog(youWin, youLoose, youPush, youBreak, youBlackjack, youCharlie, totalSplits, dealerWin, dealerLoose, dealerPush, totalHandsYou, totalHandsDealer, bankroll);
        }        
    }
    /* This method to write in the log file for client. Diagnostics should include all player statistics and delaer statistics*/
    public void writeToLog(int youWin,int youLoose,int youPush,int youBreak,int youBlackjack,int youCharlie,int totalSplits,int dealerWin,int dealerLoose,int dealerPush,
            int totalHandsYou,int totalHandsDealer, double bankroll)
    {
            LOG.info("Your Wins: " + youWin + " Losses: " + youLoose + " Push: " + youPush+" Break: "+youBreak+" Blackjack: "+youBlackjack
                    + " Charlie: "+youCharlie+" Total Splits: "+totalSplits+" Total Hands : "+(youWin+youPush+youLoose+youCharlie+youBreak+youBlackjack));
            LOG.info("Dealer Wins: " + dealerWin + " Losses: " + dealerLoose + " Push: " + youPush+ " Total Hands: "+(dealerPush+dealerLoose+dealerWin-totalSplits));  
            LOG.info("Bankroll: "+bankroll);
    }
}
