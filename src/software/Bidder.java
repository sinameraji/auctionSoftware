/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package software;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
/**
 *
 * @author MAC
 */
public class Bidder {
    protected String bidderID;
    boolean canEnterAuction;
    public Bidder(String bidderID){
        this.bidderID = bidderID;
    }
    
    public void enterAuction(String itemID){ 
        String auctionType = null, auctionStartDate = null, deadline = null;
        try{
            Scanner read = new Scanner(new FileInputStream("seller.txt"));
            while(read.hasNextLine()){
                String line = read.nextLine();
                if(line.split(",")[0].equalsIgnoreCase(itemID)){
                    auctionType = line.split(",")[4];
                    auctionStartDate = line.split(",")[2];
                    deadline = line.split(",")[5];
                }
            }
            read.close();
        }
        catch(IOException e){
            System.out.println("File not found.");
        }
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date now = new Date();
        String dateNow = df.format(now);
        //System.out.println(auctionStartDate);
        if(dateNow.compareTo(auctionStartDate) < 0){
            System.out.println("You have successfully entered this auction. \nAuction beginning time: " + auctionStartDate);
            this.canEnterAuction = true;
        }
        else{
            if(auctionType.equalsIgnoreCase("japanese")){
                System.out.println("Sorry! You should be more punctual to enter a Japanese auction :) ");
                canEnterAuction = false;
            }
            else{
                if(dateNow.compareTo(deadline) < 0){
                    System.out.println("You have entered this auction. \nThe auction has already begun, but you can still set a bid now.");
                    canEnterAuction = true;
                }
                else{
                    System.out.println("You were late! Auction is over.");
                    canEnterAuction = false;
                }
            }
        }   
    }
    
    public void bid(String itemID, double bidAmount){
        if(this.canEnterAuction){
            try{
                PrintWriter write = new PrintWriter(new FileOutputStream("bidder.txt", true));
                write.println(this.bidderID + "," + itemID + "," + bidAmount);
                write.close();
            }
            catch(IOException e){
                System.out.println("Problem with output.");
            }
        }
    }
}
