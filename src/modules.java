import java.sql.*;
import java.util.*;
public class modules {
    static String sql = "select name from PersonalInfo where mobileno='8698153399'";
    static String url = "jdbc:postgresql://localhost:5432/RegistrationForm";
    static String username = "postgres";
    static String password = "Dev@8698";
    static Scanner sc = new Scanner(System.in);
    static String email;
    static String pass;
    static String rmail,rpass,rname,rmobile;
    static int auth;
    static Main obj2  = new Main();
    boolean checker(String outt){
        boolean a = false;
        if (outt.equals("1")){
            a=true;
        }
        return a;
    }

    void for_mail(String mail1){
        String emailExistORnot = "SELECT COUNT(*) " + "FROM students " + "WHERE email = '"+mail1+"'";
        Connection con = null;
        try {
            con = DriverManager.getConnection(url, username, password);
            Statement st =con.createStatement();
            ResultSet rs = st.executeQuery(emailExistORnot);
            rs.next();
            String name = rs.getString( 1);
            con.close();
            if (name.equals("0")){
                System.out.println("Wrong email / This email is not register !!!!!\n ENTER IT AGAIN");
                System.out.print("Enter your email :- ");
                email = sc.next();
                for_mail(email);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    void for_pass (String pass1){
        String mobileExistORnot = "SELECT passwordd FROM students " + "WHERE email = '"+email+"'";
        Connection con = null;
        try {
            con = DriverManager.getConnection(url, username, password);
            Statement st =con.createStatement();
            ResultSet rs = st.executeQuery(mobileExistORnot);
            rs.next();
            String name = rs.getString( 1);
            con.close();
            if (name.equals(pass1)){
                auth = 1;
            }
            else {
                System.out.println("Wrong password !!!!!\n ENTER IT AGAIN");
                System.out.print("Enter your password :- ");
                pass = sc.next();
                for_pass(pass);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    void EditInfo(){
        int ab;
        String fname,lname,fullname,pass2,mQuery;
        String mobileNo,remobileNo;

        System.out.println("\n1. Name \n2. Mobile no.");
        System.out.print("Enter Choice :- ");
        ab= sc.nextInt();
        if (ab==1){
            System.out.print("First name :- ");
            fname = sc.next();
            System.out.print("First last :- ");
            lname = sc.next();
            fullname=fname+" "+lname;
            System.out.print("Enter your password :- ");
            pass2= sc.next();
            for_pass(pass2);
            mQuery="UPDATE students SET student_name='"+fullname+"' WHERE passwordd = '"+pass+"';";
            Connection con = null;
            try {
                con = DriverManager.getConnection(url, username, password);
                Statement st =con.createStatement();
                ResultSet rs = st.executeQuery(mQuery);

                con.close();


            } catch (SQLException e) {
                System.out.println("Updated successfully");
                login_window();
                obj2.Choice12();
            }

        }
        else {
            MobileValidation();
            mQuery="UPDATE students SET mobile_no ='"+rmobile+"' WHERE passwordd = '"+pass+"';";
            Connection con = null;
            System.out.print("Enter your password :- ");
            pass2= sc.next();
            for_pass(pass2);
            try {
                con = DriverManager.getConnection(url, username, password);
                Statement st =con.createStatement();
                ResultSet rs = st.executeQuery(mQuery);
                con.close();


            } catch (SQLException e) {
                System.out.println("Updated successfully");
                login_window();
                obj2.Choice12();
            }
        }
    }

    void login_window(){
        System.out.println("\n********* login successfully *********");
        String mobileQuery = "SELECT mobile_no FROM students " + "WHERE email = '"+email+"'";
        String nameQuery = "SELECT student_name FROM students " + "WHERE email = '"+email+"'";
        Connection con = null;
        try {
            con = DriverManager.getConnection(url, username, password);
            Statement st =con.createStatement();
            ResultSet rs = st.executeQuery(nameQuery);
            rs.next();
            String name = rs.getString( 1);
            System.out.println("\nName :- "+name);

            ResultSet mn = st.executeQuery(mobileQuery);
            mn.next();
            String mobile = mn.getString( 1);
            System.out.println("\nMobile No. :- "+mobile);
            con.close();



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    void login( ){
        System.out.println("\n********* login *********");
        System.out.print("Enter your email :- ");
        email = sc.next();
        for_mail(email);
        System.out.print("Enter your password :- ");
        pass = sc.next();
        for_pass(pass);
        if (auth == 1){
            login_window();

        }

    }

    void emailValidation(String email2){
        String email1;
        String emailExistORnot = "SELECT COUNT(*) " + "FROM students " + "WHERE email = '"+email2+"'";
        Connection con = null;
        try {
            con = DriverManager.getConnection(url, username, password);
            Statement st =con.createStatement();
            ResultSet rs = st.executeQuery(emailExistORnot);
            rs.next();
            String name = rs.getString( 1);
            con.close();
            if (name.equals("1")){
                System.out.println("This email is already registered !!!!!\n use another one");
                System.out.print("Enter your email :- ");
                email1 = sc.next();
                emailValidation(email1);
            }
            else{
                rmail=email2;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    void PasswordValidation(){
        String p1,p2;
        System.out.print("Enter password :- ");
        p1=sc.next();
        System.out.print("ReEnter password :- ");
        p2=sc.next();
        if (p1.equals(p2)){
            rpass=p1;
        }
        else {
            System.out.println("Password does not match!!!!! \n RETRY!!!");
            PasswordValidation();
        }
    }

    void MobileValidation(){
        String m1;
        System.out.print("Enter mobile No. :- ");
        m1=sc.next();
        if (m1.length()==10){
            rmobile=m1;
        }
        else {
            MobileValidation();
        }
    }



    void registration(){
        Scanner sc1 = new Scanner(System.in);
        int c11;
        String fName,lName;
        System.out.println("\n********* Registration *********\n");
        System.out.print("Enter email :- ");
        rmail= sc.next();
        emailValidation(rmail);
        System.out.println();
        PasswordValidation();
        System.out.println();
        MobileValidation();
        System.out.println();
        System.out.print("Enter First name :- ");
        fName= sc.next();
        System.out.print("Enter Last name :- ");
        lName= sc.next();
        rname = fName+" "+lName;
        System.out.println("\n1.Conform\n2.Re-enter\n");
        System.out.print("Enter choice :- ");
        c11= sc1.nextInt();
        if (c11==1){
            String pushh ="INSERT INTO students (email,passwordd,mobile_no,student_name) VALUES ('"+rmail+"','"+rpass+"','"+rmobile+"','"+rname+"')";
            Connection con = null;
            try {
                con = DriverManager.getConnection(url, username, password);
                Statement st =con.createStatement();
                ResultSet rs = st.executeQuery(pushh);

                con.close();

            } catch (SQLException e) {
                System.out.println("Rgisterd successfully");
            }
        }
        else {
            registration();
        }


    }

}
