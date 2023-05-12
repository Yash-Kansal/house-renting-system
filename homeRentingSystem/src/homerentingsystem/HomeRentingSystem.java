package homerentingsystem;
//import java.util.*;
import java.sql.*;
import java.util.Scanner;


class tenant{
    
    Scanner sc=new Scanner(System.in);
    
    // to see any particular detail of owner 
    public void seeOwnerDetails(int hcode,String username) throws Exception
    {
        Class.forName("org.sqlite.JDBC");
        Connection con=DriverManager.getConnection("jdbc:sqlite:C://sqlite//hrs.db");
        PreparedStatement ps;
        ResultSet rs;
        ps=con.prepareStatement("select  H.username, first_name, last_name, phoneno from homes H join details D on H.username=D.username where hcode=?");
        ps.setInt(1, hcode);
        rs=ps.executeQuery();
        if(!rs.next()){
            System.out.println("No House With This hcode");
        }
        else{
            rs=ps.executeQuery();
        while(rs.next()){
            System.out.println("**");
            System.out.println("Username:- "+rs.getString("username"));
            System.out.println("Name:- "+ rs.getString("first_name")+ " "+rs.getString("last_name"));
            System.out.println("Contact No.:- "+ rs.getString("phoneno"));
            System.out.println("**");
        }
        }
        ps.close();
        con.close();
        System.out.println("1.Show Interest In The House(Y/N)");
        String inter=sc.next();
        if(inter.equals("Y") || inter.equals("y"))
        {
            showInterest(username,hcode);
        }
        else if(inter.equals("N")|| inter.equals("n")){
            System.out.println("no");
           
        }
    
    }
    
    
    
    // to search houses by cities
    public int byCity(String usernameT) throws Exception 
    {
        System.out.println("Enter city name in which you want to search house:- ");
        String city=sc.next();
        Class.forName("org.sqlite.JDBC");
        Connection con=DriverManager.getConnection("jdbc:sqlite:C://sqlite//hrs.db");
        PreparedStatement ps;
        ResultSet rs;
        
        ps=con.prepareStatement("select hcode, area, houseno, bhk, house_type, expected_price, other_detail, city, state from homes H join details D on H.username=D.username where city=?");
        ps.setString(1, city);
        rs=ps.executeQuery();
        
        if(!rs.next()){
            System.out.println("no house available in this city...");
            ps.close();
            con.close();
            return 1;
        }
        
        else{
            rs=ps.executeQuery();
            while(rs.next()){
                System.out.println("**");
                System.out.println("Hcode:- "+rs.getInt("hcode") );
                System.out.println("Area:- "+rs.getString("area"));
                System.out.println("HouseNo.:- "+rs.getString("houseno"));
                System.out.println("BHK:- "+rs.getString("bhk"));
                System.out.println("House Type:- "+rs.getString("house_type"));
                System.out.println("Expected Price:- "+rs.getInt("expected_price"));
                System.out.println("Other Details:- "+rs.getString("other_detail"));
                System.out.println("City:- "+rs.getString("city"));
                System.out.println("State:- "+rs.getString("state"));
//                System.out.println("**");
                
            }
            ps.close();
            con.close();
            int fl=1;
            
            while(fl==1){
            System.out.println("1. To see owner details\n2. Exit");
            int cho=sc.nextInt();
            if(cho==1){
                System.out.println("Enter hcode of house");
                int hcode=sc.nextInt();
                seeOwnerDetails(hcode,usernameT);
            }
            else if(cho==2){
                fl=0;
            }
            }
            return 0;
        }
    }
    
    
    
    //by price and given city
    public int byPriceAndCity(String username) throws Exception
    {
        System.out.println("Enter city in which you want to search house:-");
        String city=sc.next();
        System.out.println("Enter budget :-");
        int expected_price=sc.nextInt();
        
        Class.forName("org.sqlite.JDBC");
        Connection con=DriverManager.getConnection("jdbc:sqlite:C://sqlite//hrs.db");
        PreparedStatement ps;
        ResultSet rs;
        ps=con.prepareStatement("select hcode, area, houseno, bhk, house_type, expected_price, other_detail, city, state from homes H join details D on H.username=D.username where city=? and expected_price between ? and ?;");
        ps.setString(1, city);
        ps.setInt(2, (expected_price-2000));
        ps.setInt(3, (expected_price+2000));
        
        rs=ps.executeQuery();
        if(!rs.next()){
            System.out.println("no house available in this city...");
            ps.close();
            con.close();
            return 1;
        }
        else{
            rs=ps.executeQuery();
            while(rs.next()){
                System.out.println("**");
                System.out.println("Hcode:- "+rs.getInt("hcode") );
                System.out.println("Area:- "+rs.getString("area"));
                System.out.println("HouseNo.:- "+rs.getString("houseno"));
                System.out.println("BHK:- "+rs.getString("bhk"));
                System.out.println("House Type:- "+rs.getString("house_type"));
                System.out.println("Expected Price:- "+rs.getInt("expected_price"));
                System.out.println("Other Details:- "+rs.getString("other_detail"));
                System.out.println("City:- "+rs.getString("city"));
                System.out.println("State:- "+rs.getString("state"));
                
            }
            ps.close();
            con.close();
            
            int fl=1;
            
            while(fl==1){
            System.out.println("1. To see owner details\n2. Exit");
            int cho=sc.nextInt();
            if(cho==1){
                System.out.println("Enter hcode of house");
                int hcode=sc.nextInt();
                seeOwnerDetails(hcode,username);
            }
            else if(cho==2){
                fl=0;
            }
            }
            return 0;
        }
    
    }
    
    
    
    
    
    // by price in own city
    public int byPriceInYourCity(String city,String username) throws Exception
    {
        System.out.println("Enter budget :-");
        int expected_price=sc.nextInt();
        
        Class.forName("org.sqlite.JDBC");
        Connection con=DriverManager.getConnection("jdbc:sqlite:C://sqlite//hrs.db");
        PreparedStatement ps;
        ResultSet rs;
        ps=con.prepareStatement("select hcode, area, houseno, bhk, house_type, expected_price, other_detail, city, state from homes H join details D on H.username=D.username where city=? and expected_price between ? and ?;");
        ps.setString(1, city);
        ps.setInt(2, (expected_price-2000));
        ps.setInt(3, (expected_price+2000));
        
        rs=ps.executeQuery();
        if(!rs.next()){
            System.out.println("no house available in this city...");
            ps.close();
            con.close();
            return 1;
        }
        else{
            rs=ps.executeQuery();
            while(rs.next()){
                System.out.println("**");
                System.out.println("Hcode:- "+rs.getInt("hcode") );
                System.out.println("Area:- "+rs.getString("area"));
                System.out.println("HouseNo.:- "+rs.getString("houseno"));
                System.out.println("BHK:- "+rs.getString("bhk"));
                System.out.println("House Type:- "+rs.getString("house_type"));
                System.out.println("Expected Price:- "+rs.getInt("expected_price"));
                System.out.println("Other Details:- "+rs.getString("other_detail"));
                System.out.println("City:- "+rs.getString("city"));
                System.out.println("State:- "+rs.getString("state"));
                
            }
            ps.close();
            con.close();
            
            int fl=1;
            
            while(fl==1){
            System.out.println("1. To see owner details\n2. Exit");
            int cho=sc.nextInt();
            if(cho==1){
                System.out.println("Enter hcode of house");
                int hcode=sc.nextInt();
                seeOwnerDetails(hcode,username);
               
            }
            else if(cho==2){
                fl=0;
            }
            }
            return 0;
        }
    }
    
    
    
    // to show interest
    public void showInterest(String username,int hcode)throws Exception
    {
        Class.forName("org.sqlite.JDBC");
        Connection con=DriverManager.getConnection("jdbc:sqlite:C://sqlite//hrs.db");
        PreparedStatement ps;
        ResultSet rs;
        
        ps=con.prepareStatement("select intrested_user from homes where hcode=?");
        ps.setInt(1, hcode);
        rs=ps.executeQuery();
        String in_users="";
        while(rs.next()){
            in_users=in_users+rs.getString("intrested_user");
        
        }
        
        System.out.println("Enter Your Number");
        String phoneno=sc.next();
        
        String total=in_users+username+":- "+phoneno+"\t";
        ps=con.prepareStatement("update homes set intrested_user=? where hcode=?");
        ps.setString(1,total);
        ps.setInt(2,hcode);
        ps.executeUpdate();
     
        ps.close();
        con.close();
        System.out.println("...Interest Send Successfully...");
        
       
    }
    
    
    
}
//////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////
// class for owner


class owner{
    Scanner sc=new Scanner(System.in);
    
    
    // to add a house
    public void addHouse(String username) throws Exception
    {
        String area,houseno,bhk,house_type,phoneno,other_detail,interested_user;
        int expected_price;
        
        System.out.println("Enter your house details");
        System.out.println("House Number:- ");
        houseno=sc.next();
        System.out.println("Area:- ");
        area=sc.next();
        System.out.println("House Type:- ");
        house_type=sc.next();
        System.out.println("BHK:- ");
        bhk=sc.next();
        System.out.println("Phone Number:- ");
        phoneno=sc.next();
        System.out.println("Expected Price:- ");
        expected_price=sc.nextInt();
        System.out.println("House Description:- ");
        sc.nextLine();
        other_detail=sc.nextLine();
        
        
        Class.forName("org.sqlite.JDBC");
        Connection con=DriverManager.getConnection("jdbc:sqlite:C://sqlite//hrs.db");
        PreparedStatement ps;
//        ResultSet rs;
        
        ps=con.prepareStatement("insert into homes(username,area,houseno,bhk,house_type,phoneno,other_detail,expected_price,intrested_user) values(?,?,?,?,?,?,?,?,?)");
        ps.setString(1,username);
        ps.setString(2,area);
        ps.setString(3,houseno);
        ps.setString(4,bhk);
        ps.setString(5,house_type);
        ps.setString(6,phoneno);
        ps.setString(7,other_detail);
        ps.setInt(8,expected_price);
        ps.setString(9,"->");
        ps.executeUpdate();
        System.out.println("Details added sucessfully");
        ps.close();
        con.close();
    }
    
    
    // for deleting a house
    public int deleteHouse(String username) throws Exception 
    {
        
        Class.forName("org.sqlite.JDBC");
        Connection con=DriverManager.getConnection("jdbc:sqlite:C://sqlite//hrs.db");
        PreparedStatement ps;
        ResultSet rs;

        
        ps=con.prepareStatement("select hcode, houseno from homes where username=?");
        ps.setString(1, username);
        rs=ps.executeQuery();
            
        
        if(!rs.next()){
            System.out.println("you have not inserted any house yet...");
            return 1;
        }
        
        else{
            rs=ps.executeQuery();
            System.out.println("hcode"+" "+"houseno.");
            while(rs.next()){
            
                System.out.print(rs.getInt("hcode")+"     ");
                System.out.println(rs.getString("houseno"));
            }
            }
        
        System.out.println("enter hcode which you want to delete");
        int hcode=sc.nextInt();
        
        
        ps=con.prepareStatement("delete from homes where username=? and hcode=?");
        ps.setString(1, username);
        ps.setInt(2, hcode);
        ps.executeUpdate();
        System.out.println("...Deleted Successfully...");
        ps.close();
        con.close();
        return 0;
        
    }
    
    
    
    // to change phone number of a user
    public int changePhoneno(String username)throws Exception
    {
        Class.forName("org.sqlite.JDBC");
        Connection con=DriverManager.getConnection("jdbc:sqlite:C://sqlite//hrs.db");
        PreparedStatement ps;
        ResultSet rs;
        
        ps=con.prepareStatement("select hcode, houseno ,phoneno from homes where username=?");
        ps.setString(1, username);
        rs=ps.executeQuery();
        
        if(!rs.next()){
            System.out.println("you have not inserted any house yet...");
            return 1;
        }
        
        else{
            rs=ps.executeQuery();
            System.out.println("hcode"+"     "+"HouseNo."+"     "+"Phoneno.");
            while(rs.next()){
            
                System.out.print(rs.getInt("hcode")+"         ");
                System.out.print(rs.getString("houseno")+"         ");
                System.out.println(rs.getString("phoneno"));
            }
            }
        
        System.out.println("enter hcode of house for which you want to change Phoneno.");
        int hcode=sc.nextInt();
        System.out.println("Enter the New PhoneNo.");
        String phoneno=sc.next();
        
        ps=con.prepareStatement("Update homes SET phoneno=? where username=? and hcode=?");
        ps.setString(1, phoneno);
        ps.setString(2, username);
        ps.setInt(3, hcode);
        ps.executeUpdate();
        System.out.println("...Updated Successfully...");
        ps.close();
        con.close();
        return 0;
        
    }
    
    
    // to change the price of a house
    public int changeExpectedPrice(String username)throws Exception
    {
        Class.forName("org.sqlite.JDBC");
        Connection con=DriverManager.getConnection("jdbc:sqlite:C://sqlite//hrs.db");
        PreparedStatement ps;
        ResultSet rs;
        
        ps=con.prepareStatement("select hcode, houseno ,expected_price from homes where username=?");
        ps.setString(1, username);
        rs=ps.executeQuery();
            
        
        if(!rs.next()){
            System.out.println("you have not inserted any house yet...");
            return 1;
        }
        
        else{
            rs=ps.executeQuery();
            System.out.println("hcode"+"     "+"houseNo."+"     "+"ExpectedPrice");
            while(rs.next()){
            
                System.out.print(rs.getInt("hcode")+"         ");
                System.out.print(rs.getString("houseno")+"         ");
                System.out.println(rs.getInt("expected_price"));
            }
            }
        
        System.out.println("enter hcode of house of which you want to change expected price");
        int hcode=sc.nextInt();
        System.out.println("Enter the New Expected Price");
        int expected_price=sc.nextInt();
        
        ps=con.prepareStatement("Update homes SET expected_price=? where username=? and hcode=?");
        ps.setInt(1, expected_price);
        ps.setString(2, username);
        ps.setInt(3, hcode);
        ps.executeUpdate();
        System.out.println("...Updated Successfully...");
        ps.close();
        con.close();
        return 0;
        
    }
    
    
    // to get list of interested users
    public int getInterestedUsers(String username)throws Exception
    {
        Class.forName("org.sqlite.JDBC");
        Connection con=DriverManager.getConnection("jdbc:sqlite:C://sqlite//hrs.db");
        PreparedStatement ps;
        ResultSet rs;
        
       
        viewOwnHouseAdded(username);
        System.out.println("Enter hcode of House to see Interested Users");
        int hcode=sc.nextInt();
        ps=con.prepareStatement("select intrested_user from homes where username=?");
        ps.setString(1,username);
        rs=ps.executeQuery();
        System.out.println("interested user"+" "+rs.getString("intrested_user"));
        ps.close();
        con.close();
        return 0;
    }
    
    //to delete list of interested users
    public void deleteInterestedUsers(String username)throws Exception
    {
        Class.forName("org.sqlite.JDBC");
        Connection con=DriverManager.getConnection("jdbc:sqlite:C://sqlite//hrs.db");
        PreparedStatement ps;
      //  ResultSet rs;
       
        viewOwnHouseAdded(username);
        System.out.println("Enter hcode of house ");
        int hcode=sc.nextInt();
        ps=con.prepareStatement("update homes set intrested_user='->' where username=? and hcode=?");
        ps.setString(1, username);
        ps.setInt(2, hcode);
        ps.executeUpdate();
        ps.close();
        con.close();
    }
    
    
    
    // to view houses added  by user itself
    public int viewOwnHouseAdded(String username)throws Exception
    { 
        Class.forName("org.sqlite.JDBC");
        Connection con=DriverManager.getConnection("jdbc:sqlite:C://sqlite//hrs.db");
        PreparedStatement ps;
        ResultSet rs;
        
        ps=con.prepareStatement("select hcode,area,houseno,bhk,house_type,phoneno,other_detail,expected_price from homes where username=?");
        ps.setString(1, username);
        rs=ps.executeQuery();
        if(!rs.next()){
            System.out.println("No Details Found...");
            return 1;
        }
        else{
            rs=ps.executeQuery();
            
            while (rs.next()) {
                System.out.println("hcode:-"+rs.getInt("hcode"));
                System.out.println("Area:-"+rs.getString("area"));
                System.out.println("HouseNo.:-"+rs.getString("houseno"));
                System.out.println("BHK:- "+rs.getString("bhk"));
                System.out.println("House Type:-"+rs.getString("house_type"));
                System.out.println("Contact No. :-"+rs.getString("phoneno"));
                System.out.println("Other Details:-"+rs.getString("other_detail"));
                System.out.println("Expected Price:-"+rs.getInt("expected_price"));   
            }
        }
        rs.close();
        ps.close();
        con.close();
        return 0;
}
}

class options{
        
    
    int mainp=1,mainp2=1;
    public void afterLogin(String username) throws Exception{
        
            Scanner sc=new Scanner(System.in);
        // stablishing connection between database and java program
        Class.forName("org.sqlite.JDBC");
        Connection con=DriverManager.getConnection("jdbc:sqlite:C://sqlite//hrs.db");
        PreparedStatement ps;
        ResultSet rs;
        
        ps=con.prepareStatement("select first_name,city from details where username=?");
                    ps.setString(1,username);
                    
                    rs=ps.executeQuery();
                    String city=rs.getString(2);
                    
                    System.out.println("Welcome, "+ rs.getString(1));
                    ps.close();
                    con.close();
                    
                    while(mainp2==1){
                    
                    System.out.println("continue as,\n1.Owner\n2.Tenant\n3.Logout");
                    int choice2=sc.nextInt();
                    if(choice2==1){
                        //owner's choices
                        owner o=new owner();   
                    System.out.println("Choose A Action To Perform");
                    System.out.println("1.Add House\n2.Delete House\n3.Change Phone Number\n4.Change Expected Price\n5.View House Added\n6.Show Interested Users\n7.Delete Interested User\n8.Exit");    
                    int sel=sc.nextInt();
                        switch (sel) {
                            case 1:
                                o.addHouse(username);
                                continue;
                            case 2:
                                o.deleteHouse(username);
                                continue;
                            case 3:
                                o.changePhoneno(username);
                                continue;
                            case 4:
                                o.changeExpectedPrice(username);
                                continue;
                            case 5:
                                o.viewOwnHouseAdded(username); 
                                continue;
                            case 6:
                                o.getInterestedUsers(username);
                                continue;
                            case 7:
                                o.deleteInterestedUsers(username);
                                continue;
                            case 8:
                                 mainp2=0;
                                 break;
                            default:
                                System.out.println("Enter A Valid Choice");
                                continue;
                        }
                       
                        
                    }
                    else if(choice2==2){
                        //tenant's choices
                        tenant t =new tenant();
                    System.out.println("Search a house By:");
                    System.out.println("1.City\n2.Expected Price In Your City\n3.Expected Price in Any City");
                    System.out.print("Your Choice:-");
                    int sel1=sc.nextInt();
                    System.out.println();
                    switch(sel1){
                        case 1:
                            t.byCity(username);
                            break;
                        case 2:
                            t.byPriceInYourCity(city,username);
                            break;
                        case 3:
                            t.byPriceAndCity(username);
                            break;
                        default :
                            System.out.println("Enter A Valid Choice");
                            continue;
                    }  
                        mainp2=0;
                        
                    }
                    else if(choice2==3){
                        mainp2=0;
                    }
                    else{
                        System.out.println("Enter a Valid Choice");
                    }
                }
    }
    
    
    public void loginPage() throws Exception 
    {
        Scanner sc=new Scanner(System.in);
        // stablishing connection between database and java program
        Class.forName("org.sqlite.JDBC");
        Connection con=DriverManager.getConnection("jdbc:sqlite:C://sqlite//hrs.db");
        PreparedStatement ps;
        ResultSet rs;
        
        
        
        while(mainp==1){
            System.out.println("1.Login\n2.Signup\n3.Exit");
            
            int choice=sc.nextInt();
            if(choice==1){
                
                //getting user login details
                System.out.println("Enter your username");
                String username=sc.next();
                System.out.println("Enter your password");
                String password=sc.next();
                try{
                ps=con.prepareStatement("select username,password from details where username=? and password=?");
                ps.setString(1, username);
                ps.setString(2, password);
                rs=ps.executeQuery();
                String un;
                String pw;
                un= rs.getString(1);
                pw= rs.getString(2);
                if(un.equals(username) && pw.equals(password)){
                    System.out.println("Successfully logged in... ");
                    ps.close();
                    con.close();
                    afterLogin(username);
                    mainp=0;
                }
                }
                catch(Exception e){
                    System.out.println("invalid username or password try again! ");
                }
            }
            
            //signup
            else if(choice==2){
                int w=1;
                String f_name;
                String l_name;
                String u_name;
                String password="";
                String repassword;
                String dob="";
                String state;
                String city;
                System.out.println("Enter First Name");
                f_name=sc.next();
                System.out.println("Enter LastName");
                l_name=sc.next();
                System.out.println("Enter username");
                u_name=sc.next();
                while(w==1){
                System.out.println("Enter password");
                password=sc.next();
                System.out.println("Re-enter password");
                repassword=sc.next();
                if(password.equals(repassword)){
                    w=0;
                }
                else
                    System.out.println("Password not matching");
                }
                
                System.out.println("Enter State");
                state=sc.next();
                System.out.println("Enter City");
                city=sc.next();
                while(w==0)
                {
                System.out.println("Enter DOB in DD/MM/YYYY format");
                dob=sc.next();
                if(dob.matches("[0-3]{1}[0-9]{1}[/]{1}[0-1]{1}[0-9]{1}[/]{1}[0-9]{4}")){
                    w=1;
                }
                else
                    System.out.println("invalid format please type in DD/MM/YYYY format");
                }
                
                ps=con.prepareStatement("insert into details values(?,?,?,?,?,?,?)");
                ps.setString(1,u_name);
                ps.setString(2,password);
                ps.setString(3,f_name);
                ps.setString(4,l_name);
                ps.setString(5,state);
                ps.setString(6,city);
                ps.setString(7,dob);
                ps.executeUpdate();
                System.out.println("details added sucessfully");
                
            }
            //signup ended
            
            
            else if(choice==3){
                System.exit(0);
            }
            else{
                System.out.println("please enter valid choice");
            }
        }
    }
}

public class HomeRentingSystem 
{
    
    public static void main(String[] args) throws Exception
    {
        Scanner sc=new Scanner(System.in);
        
        System.out.println("HOME RENTING SYSTEM ");
        int hrsMain=1;
        while(hrsMain==1){
        options opt=new options();
        opt.loginPage();
        System.out.println("Exit Confirm(Y/N)");
        String ex=sc.next();
            if(ex.equals("Y")|| ex.equals("y")){
            hrsMain=0;
            }
        }
    }
}