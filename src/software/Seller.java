/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package software;

/**
 *
 * @author fatinhalim
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Seller {
   protected String sellerID, item, date_time ,deadLine, auction_type;
   private double price;
    public Seller(String sellerID){
        this.sellerID = sellerID;
    }
    
    public void addItem(String item, String date_time, double price, String auction_type, String deadLine){
        String itemID = "I" + (countItems() + 1);
        try{
            PrintWriter output = new PrintWriter(new FileOutputStream("/Users/MAC/Dropbox/DS Group Assignment/Altogether/Software/src/software/seller.txt", true));
            output.println(itemID + "," + item + "," + date_time + "," +price + "," + auction_type + "," +deadLine + "," + this.sellerID);
            output.close();
        }
        catch(IOException e){
            System.out.println("Problem with output");
        }
    }
    
    public void edit(int lineNo, String item, String date_time, double price, String auction_type, String deadLine){
        ArrayList<String[]> array = new ArrayList<>();
        
        array.get(lineNo-1)[0] = item;
        array.get(lineNo-1)[1] = date_time;
        array.get(lineNo-1)[2] = String.valueOf(price) + "0";
        array.get(lineNo-1)[3] = auction_type;
        array.get(lineNo-1)[4] = deadLine;
        
        try{
            PrintWriter output = new PrintWriter(new FileOutputStream("seller.text"));
            
            for(int i = 0; i<array.size(); i++){
                for(int j = 0; j<array.get(i).length; i++){
                    if(j !=1 ){
                        output.print(array.get(i)[j] + ",");
                    }
                    
                    else{
                        output.print(array.get(i)[j]);
                    }
                }
            }
            
            output.close();
        }
        
        catch(FileNotFoundException e){
            System.out.println("file not discovered");
        }
        
        System.out.println("edit is successful");
    }
    
    public void delete(int itemID, String sellerID){
        
        ArrayList<String[]> array = newArray("seller.txt");
        array.remove(lineNo-1);
        try{
            PrintWriter output = new PrintWriter(new FileOutputStream("seller.txt"));
            
             for(int i = 0; i<array.size(); i++){
                for(int j = 0; j<array.get(i).length; j++){
                    
                    if(j != 1)
                        output.print(array.get(i)[j] + ",");
                    else
                        output.print(array.get(i)[j]);

                }
            }
            output.close();
        }
        
        catch(IOException e){
          System.out.println("problem with file");
        }

        System.out.println("deleting is successful");
    }
    
    
    
    
    public static void chooseAuction(int choice){
        
        Scanner input = new Scanner(System.in);
        
        switch(choice){
            case 1:
                System.out.println("english");
                //enter method here
                break;
            
            case 2:
                System.out.println("japanese");
                //enter method here
                break;
            
            case 3:
                System.out.println("i forgot");
                //enter method here
                break;
            
            case 4:
                System.out.println("vickrey");
                //enter method here
                break;
                
            case 5:
                System.out.println("reserve");
                //enter method here
                break;
        }
        
    }
  
    
    public static void display(){
        
        ArrayList<String[]> array = newArray("seller.txt");
       
            for(int i = 0; i<array.size(); i++){
                System.out.printf("#%d ", i+1);
                
                    for(int j = 0; j<array.get(i).length; j++){
                    
                    System.out.printf("%-20s",array.get(i)[j]);
                    
                    }
                    
                System.out.println("");
            } 
    }
    
    public void displayBidsforItemsOf(String sellerID){
        ArrayList<String[]> bids = newArray("bidder.txt");
        ArrayList<String[]> allItems = newArray("seller.txt");
        ArrayList<String> sellerItems = new ArrayList<>();
        ArrayList<String[]> bidsForItem = new ArrayList<>();
        
        for(int i = 0; i < allItems.size(); i++){
            if(allItems.get(i)[allItems.get(i).length - 1].equalsIgnoreCase(sellerID)){
                    sellerItems.add(allItems.get(i)[0]);
                }
//            for(int j = 0; j < allItems.get(i).length; j++){
//                
//            }
        }
        System.out.println("You have " + sellerItems.size() + " items for sale.");
        int bidCount = 0;
        for(int j = 0; j < sellerItems.size(); j++){
            for(int i = 0; i < bids.size(); i++){
                if(bids.get(i)[1].equalsIgnoreCase(sellerItems.get(j))){
                    bidCount++;
                    bidsForItem.add(bids.get(i));
                }
            }
        }
        
        System.out.println(bidCount + " bids have been submitted for your items.");
        for(int i = 0; i < bidsForItem.size(); i++){
            for(int j = 0; j < bidsForItem.get(i).length; j++){
                System.out.print(bidsForItem.get(i)[j] + "\t");
            }
            System.out.println();
        }
    }
    
    
    public static ArrayList<String[]> newArray(String filename){
        ArrayList<String[]> array = new ArrayList<>();
        
        try{
            Scanner input = new Scanner(new FileInputStream(filename));
            while(input.hasNextLine()){
                String line = input.nextLine();
                array.add(line.split(","));
            }
            input.close();
        }
        
        catch(Exception e){
            System.out.println(e);
        }
        return array;
    }
    public int countItems(){
        int lineCount = 0;
        try{
            Scanner read = new Scanner(new FileInputStream("seller.txt"));
            while(read.hasNextLine()){
                String line = read.nextLine();
                lineCount++;
            }
            read.close();
        }
        catch(IOException e){
            System.out.println("File not found.");
        }
        return lineCount;    
    }

    public void display(String sellerID) {
        ArrayList<String[]> allItems = newArray("seller.txt");
        ArrayList<String[]> sellerItems = new ArrayList<>();
        for(int i = 0; i < allItems.size(); i++){
            if(allItems.get(i)[allItems.get(i).length - 1].equalsIgnoreCase(sellerID)){
                    sellerItems.add(allItems.get(i));
                }
        }
        for(int i = 0; i < sellerItems.size(); i++){
            for(int j = 0; j < sellerItems.get(i).length; j++){
                System.out.print(sellerItems.get(i)[j]+ "\t");
            }
            System.out.println("");
        }
    }
}
    

 
