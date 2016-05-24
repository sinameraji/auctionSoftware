/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package software;

/**
 *
 * @author Daniela
 */
import java.util.*;
import java.io.*;

public class Login {
    
   
        
    protected String userId;
    private String password;
    protected int role; //1-seller   2-bidder
    
     public Login(){}
    
    public Login(String username,String password, int role) throws IOException{
        this.userId = username;
        this.password = password;
        this.role = role;
        
        this.checkUser(username, role);
        if(this.checkUser(username, role)==true){
            this.checkPassword(password, role);
                if(this.checkPassword(password, role)==true){
                    System.out.println("yay you're backz. we've missed u "+this.userId);
                }else{System.out.println("r u sure you're "+this.userId+" lol");}
        }else{System.out.println("r u sure u have an account lol");}
    
    }
    
    Scanner in = new Scanner(System.in);
    
    
    public String Password(){
        System.out.print("Enter password: ");
        this.password = in.next();
        return this.password;
        
        
    }

    
    public boolean checkUser(String name,int role) throws FileNotFoundException, IOException{
        boolean result = false;
        if(role==1){

            Scanner read = new Scanner(new FileInputStream("user1.txt"));
            ArrayList<String[]> array = new ArrayList<>();
            String line;
            String[] splitString;
            while(read.hasNextLine()){
                line = read.nextLine();
                splitString = line.split(",");
                array.add(splitString); 
            }
            read.close();
             for(int i = 0; i < array.size(); i++){
                for(int j = 0; j < array.get(i).length; j++){
                    if(name.equalsIgnoreCase(array.get(i)[0])){
                        result = true;
                    }
                }
            } 
        }
        else{
            Scanner read = new Scanner(new FileInputStream("user2.txt"));
            ArrayList<String[]> array = new ArrayList<>();
            String line;
            String[] splitString;
            while(read.hasNextLine()){
                line = read.nextLine();
                splitString = line.split(",");
                array.add(splitString);
            }
            read.close();
            for(int i = 0; i < array.size(); i++){
                for(int j = 0; j < array.get(i).length; j++){
                    if(name.equalsIgnoreCase(array.get(i)[0])){
                        result = true;
                    }
                }
            }
        } 
        return result;
        
    }
    
    public boolean checkPassword(String password,int role) throws FileNotFoundException, IOException{
    boolean result = false;
    if(role==1){
            Scanner read = new Scanner(new FileInputStream("user1.txt"));
            ArrayList<String[]> array = new ArrayList<>();
            String line;
            String[] splitString;
            while(read.hasNextLine()){
                line = read.nextLine();
                splitString = line.split(",");
                array.add(splitString);
            }
            read.close();
            for(int i = 0; i < array.size(); i++){
                for(int j = 0; j < array.get(i).length; j++){
                    if(password.equals(array.get(i)[2])){
                        result = true;
                    }
                }
            }
    }
        else{
            Scanner read = new Scanner(new FileInputStream("user2.txt"));
            ArrayList<String[]> array = new ArrayList<>();
            String line;
            String[] splitString;
            while(read.hasNextLine()){
                line = read.nextLine();
                splitString = line.split(",");
                array.add(splitString);
            }read.close();
            for(int i = 0; i < array.size(); i++){
                for(int j = 0; j < array.get(i).length; j++){
                    if(password.equalsIgnoreCase(array.get(i)[2])){
                        result = true;
                    }
                }
            }
        } 
    return result;
    }
   
//    public boolean findAcc(String name, String password, int role) throws IOException{
//    boolean result = false;
//    this.checkUser(name, role);
//    if(this.checkUser(name, role)==true){
//        this.checkPassword(password, role);
//        if(this.checkPassword(password, role)==true){
//            result = true;
//            //call class
//        }
//        else{
//            int i = 1;
//            while(i!=5){
//            do{
//            System.out.println("Incorrect Password. Try again.");
//            this.Password();
//            this.checkPassword(password, role);
//            i++;
//            }
//            while(this.checkPassword(password, role)!=true);
//            }
//        }
//    }
//    else{
//        System.out.println("No account matches the username.");
//        //call sign up prompt.
//    }
//    return result;
//    }
//    

    
}
