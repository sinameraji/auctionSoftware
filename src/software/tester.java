package software;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Sina
 */
public class tester {
    public static void main(String[] args) throws IOException {
//        Auction sib = new Auction("shampoo");
//        System.out.println(sib.auctionType);
//        Seller sina = new Seller("Sina");
//        System.out.println(sina.sellerID);
//        sina.addItem("football boots", "24-05-2016 3:30", 270, "japanese", "25-05-2016 00:00");
//        sina.display();
//        sina.edit(1, "house", date_time, 0, auction_type, deadLine);
//        Bidder n = new Bidder("Sina");
//        n.enterAuction("I3");
//        n.bid("I3", 1000);
        
        System.out.println("Welcome:");

	Scanner input = new Scanner(System.in);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        int role = 0;
        Login user = null;
        String username = null, pw, name = null;
        System.out.println("\t\t\t▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼\t\t\t");
        System.out.println("\t\t\tWelcome to E-Auction\t\t\t");
        System.out.println("\t\t\t▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲\t\t\t");
        System.out.println("");
        System.out.println("\t\tPlease log in or sign up to continue\t\t\t");
        System.out.println("\t\tEnter 1 for Sign-up, 2 for Login.");
        int select = input.nextInt();
        boolean pass = false;
        while(!pass){
            if(select == 2){
                System.out.println("");
                System.out.println("Are you Selling[1] or Bidding[2]?");
                role = input.nextInt();
                System.out.print("Username ☞ ");
                username = input.next();
                System.out.print("Password ☞ ");
                pw = input.next();
                pass = true;

                user = new Login(username, pw, role); //users from seller and bidder class
            }
            else if(select == 1){
                System.out.println("Are you a seller[1] or a buyer[2]?");
                role = input.nextInt();
                input.nextLine();
                System.out.print("Name ☞ ");
                name = input.nextLine();
                System.out.print("Username ☞ ");
                username = input.next();
                System.out.print("Password ☞ ");
                pw = input.next();
                pass = true;
                Signup newUser = new Signup(name, username, pw, role);
//                user = new Login(username, pw, role);
            }
            else{
                System.out.println("Wrong entry.");
            }
        }
        
        switch (role) {
            case 1:
                {
                    //boolean method from login class to see if the seller exists
                    boolean quit = false;
                    Seller newSeller = new Seller(username);
                    while(!quit){
                        System.out.println("Select action");
                        System.out.println(" ");
                        System.out.println("★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★");
                        System.out.println("➊ Add item ➋ Edit item ➌ Delete item ➍ Display item ➎ Display bids ➏ Display winner ➐ Back");
                        System.out.println("★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★");
                        int choose = input.nextInt();
                        switch(choose){
                            case 1:
                                System.out.print("Enter item to sell: ");
                                String item = input.next();
                                
                                System.out.print("Starting date and time: ");
                                Date date = new Date();
                                String dateStart = dateFormat.format(date);
                                
                                System.out.print("enter price ");
                                double price = input.nextDouble();
                                
                                System.out.print("Enter deadline (dd/MM/yyyy HH:mm)");
                                String deadline = input.next();
                                
                                System.out.print("enter auction type ");
                                String auction = input.next();
                                
                                newSeller.addItem(item, dateStart, price, auction, deadline);
                                break;
                                
                            case 2:
                                Seller.display();
                                System.out.println("Enter the no of line you wish to edit: ");
                                int num = input.nextInt();
                                
                                System.out.print("enter item to sell: ");
                                item = input.next();
                                
                                System.out.print("Starting date and time:" );
                                date = new Date();
                                dateStart = dateFormat.format(date);
                                
                                System.out.println("enter the starting price: ");
                                price = input.nextDouble();
                                
                                System.out.print("enter the deadline (dd/MM/yyyy HH:mm) : ");
                                deadline = input.next();
                                
                                System.out.print("enter the auction type: ");
                                auction = input.next();
                                
                                newSeller.edit(num, item, dateStart, price, auction, deadline);
                                break;
                                
                            case 3:
                                Seller.display();
                                System.out.println("");
                                System.out.print("enter no of line you wish to delete: ");
                                num = input.nextInt();
                                newSeller.delete(num);
                                break;
                                
                            case 4:
                                Seller.display();
                                break;
                                
                            case 5:
                                newSeller.displayBidsforItemsOf(newSeller.sellerID);//display from bidder.txt
                                break;
                                
                            case 6:
                                System.out.print("Winner of the following auction: ");
                                //Auction.winner();
                                
                            case 7:
                                quit = true;
                                break;
                                
                            default:
                                System.out.println("invalid key");
                        }
                        
                    }         break;
                }
            case 2:
                {
                    // BIDDER MENU
                    boolean quit = false;
                    Bidder newBidder = new Bidder(username);
                    while(!quit){
                        System.out.println("Select action");
                        System.out.println(" ");
                        System.out.println("★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★");
                        System.out.println("1) display, 2)bid on items, 3)back");
                        System.out.println("★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★");
                        
                        int choose = input.nextInt();
                        input.nextLine();
                        switch(choose){
                            case 1:
                                Seller.display();//from seller text file
                                break;
                                
                            case 2:
                                Seller.display();//from seller text file
                                
                                System.out.println("input the ID of the item");
                                String itemID = input.nextLine();
                                
                                System.out.println("Enter bidding price: ");
                                double bidAmount = input.nextDouble();
//                                input.nextLine();
//                                System.out.print("Date and time bid: ");
//                                Date date = new Date();
//                                String biddingDate = dateFormat.format(date);
                                newBidder.enterAuction(itemID);
                                newBidder.bid(itemID, bidAmount);//bidder class
                                break;
                                
                            case 3:
                                quit = true;
                                break;
                                
                            default:
                                System.out.println("invalid key");
                        }
                    } 
                    break;
                }
            default:
                System.out.println("you need to sign up to access the system");
                break;
        }
    }
}
