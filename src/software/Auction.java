package software;
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
/**
 *
 * @author Sina
 */
public class Auction {
    private String itemName;
    protected String auctionType = "";
    //String winner;
    
    public Auction(String itemName){
        ArrayList<String[]> bidsForItem = new ArrayList<>();
        try{
            Scanner read = new Scanner(new FileInputStream("bidder.txt"));
            String line;
            while(read.hasNextLine()){
                line = read.nextLine();
                if(line.split(",")[0].equalsIgnoreCase(itemName))
                    bidsForItem.add(line.split(","));   //in bidder.txt, all bids for itemName will be added to array
            }
            read = new Scanner(new FileInputStream("seller.txt"));
            while(read.hasNextLine()){
                line = read.nextLine();
                if(line.split(",")[0].equalsIgnoreCase(itemName))   //reads seller.txt to find out auctionType for itemName
                    this.auctionType = line.split(",")[3];
            }
            read.close();
        }
        catch(IOException e){
            System.out.println("File not found.");
        }
        
//        System.out.println(bidsForItem.get(0)[2]);        
//        for(int i = 0; i < bidsForItem.size(); i++){
//            for(int j = 0; j < bidsForItem.get(i).length; j++){
//                System.out.print(bidsForItem.get(i)[j] + "\t");
//            }
//            System.out.println();
//        }
        switch(auctionType){
            case "english":
                System.out.println(englishAuction(itemName, bidsForItem));
                break;
            case "japanese":
                System.out.println(japaneseAuction(itemName, bidsForItem));
                break;
            case "vickrey":
                System.out.println(vickreyAuction(itemName, bidsForItem));
                break;
            case "blind":
                System.out.println(blindAuction(itemName, bidsForItem));
                break;
            case "reserve":
                System.out.println(reserveAuction(itemName, bidsForItem));
                break;
            default:
                System.out.println("Congrats! You're seeing a bug we couldn't find!!");
                break;
        }
    }
    
    private String englishAuction(String itemName, ArrayList<String[]> bids){
        sortArrayByBidAmount(bids);
//        return toString(bids.get(0));  
        String line = "";
        for(int i = 0; i < bids.get(0).length; i++){
            if(i != bids.get(0).length - 1)
                line += bids.get(0)[i] + ",";
            else
                line += bids.get(0)[i];
        }
        return line;
    }
    private String japaneseAuction(String itemName, ArrayList<String[]> bids){
        
    }
    private String vickreyAuction(String itemName, ArrayList<String[]> bids){   //same as blind. with one difference
        sortArrayByBidAmount(bids);
//        String winner = bids.get(0)[2];
//        String winnerLine = "";
//        String winnerLineNew = "";
//        for(int i = 0; i < bids.get(0).length; i++){
//            winnerLine += bids.get(0)[i];
//            if(i != bids.get(0).length - 1)
//                winnerLine += ",";
//            if(i != 1){
//                winnerLineNew += bids.get(0)[i];
//                if(i != bids.get(0).length - 1)
//                    winnerLineNew += ",";
//            }
//            else{
//                winnerLineNew += bids.get(1)[i];
//                if(i != bids.get(0).length - 1)
//                    winnerLineNew += ",";
//            }       
//        }
//        try{
//            BufferedReader read = new BufferedReader(new FileReader("bidder.txt"));
//            String oldtext = "", line = "";            
//            while((line = read.readLine()) != null){
//                oldtext += line + System.lineSeparator(); 
//            }
//            read.close();
//            String newtext = oldtext.replaceAll(winnerLine, winnerLineNew);
//            PrintWriter write = new PrintWriter(new FileOutputStream("bidder_test.txt"));
//            write.print(newtext);
//            write.close();
//        }
//        catch(IOException e){
//            System.out.println("Problem with output.");
//        }
//        System.out.println(winnerLineNew + "\n" + winnerLine);
//        ArrayList<String> array = fileToArray("bidder.txt");
        bids.get(0)[1] = bids.get(1)[1];
        
        return toString(bids.get(0));
    }
    private String[] blindAuction(String itemName, ArrayList<String[]> bids){
        sortArrayByBidAmount(bids);
//        String winner = bids.get(0)[2];
//        System.out.println(winner);
        return bids.get(0);
        
    }
    private String reserveAuction(String itemName, ArrayList<String[]> bids){
        
    }
    
    private ArrayList<String> fileToArray(String fileName){
        ArrayList<String> array = null;
        try{
            Scanner read = new Scanner(new FileInputStream(fileName));
            String line;
            array = new ArrayList<>();
            while(read.hasNextLine()){
                line = read.nextLine();
                array.add(line);
            }
            read.close();
        }
        catch(IOException e){
            System.out.println("File not found.");
        }
        return array;
    }
    
    private void sortArrayByBidAmount(ArrayList<String[]> bids){
        String[] temp;
        for(int k = 1; k < bids.size(); k++){
            for(int i = 0; i < bids.size() - k; i++){
//                System.out.println(bids.get(i)[1] + "\t" + bids.get(i + 1)[1]);
                if(Integer.parseInt(bids.get(i)[1]) < Integer.parseInt(bids.get(i + 1)[1])){
                    temp = bids.get(i);
                    bids.set(i, bids.get(i+1));
                    bids.set(i+1, temp);
                }
            }
        }
    }
    
    private String toString(String[] array){
        String line = "";
        for(int i = 0; i < array.length; i++){
            if(i != array.length - 1)
                line += array[i] + ",";
            else
                line += array[i];
        }
        return line;
    }
}
