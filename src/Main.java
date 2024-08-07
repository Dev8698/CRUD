import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static String nulll[]={""};
    static int a;
    static modules obj1 = new modules();
    void Choice12(){

        System.out.println();
        System.out.println("1. Edit Info\n2. Logout");
        System.out.print("Enter your choice :- ");
        a= sc.nextInt();
        if (a==1){
            obj1.EditInfo();
        }
        else {
            System.out.println("\n\n*********** Logged out successfully ***********");
            try {
                Main.main(nulll);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        Main obj2 = new Main();
        int choice1 ;
        System.out.print("*********** WELCOME ***********");
        System.out.println("\n1.Login");
        System.out.println("2.register\n");

        System.out.print("ENTER CHOICE :- ");

        choice1 = sc.nextInt();
        if (choice1 == 1){

            obj1.login();
            obj2.Choice12();


        }
        if (choice1 == 2){
         obj1.registration();

        }


    }
}