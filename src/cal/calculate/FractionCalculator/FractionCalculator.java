package cal.calculate.FractionCalculator;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;

/**
 * Created by mostafa on 10/2/2017.
 */
public class FractionCalculator{


  public static void main(String[] args){

      FractionCalculator fc = new FractionCalculator();
      fc.intrduction();
      Scanner input = new Scanner(System.in);

      while(true) {
          String operation = fc.getOperation(input);
          if(operation.equals("q")){
              System.out.println("Thanks for using our app :)");
              break;
          }
          Fraction a = fc.getFraction(input);
          Fraction b = fc.getFraction(input);
          Fraction result;

             if(operation.equals("+")) {
                 result = a.add(b);
                 result.toLowestTerms();
                 System.out.println(a + " + " + b + " = " + result.toString());
             }else if(operation.equals("-")) {
                     result = a.subtract(b);
                     result.toLowestTerms();
                     System.out.println(a + " - " + b + " = " + result.toString());
             }else if(operation.equals("*")) {

                 result = a.multiply(b);
                 result.toLowestTerms();
                 System.out.println(a + " * " + b + " = " + result.toString());
             }else if(operation.equals("/")){
                  result = a.divide(b);
                  result.toLowestTerms();
                  System.out.println(a + " / " + b + " = " + result.toString());

          }

      }

  }


     public void intrduction(){

        System.out.println("This Program is a fraction Calculator ");
        System.out.println("It will add,subtract, multiply & divide fractions until you type Q to quit");
        System.out.println("Please Enter your fractions in form a/b, where a and b are integers");
        System.out.println("------------------------------------------------");

      }
//prompt user to insert a valid math operation or q to quit (+,-,/,*,q)
//return: a string of a valid math operation
    public String getOperation(Scanner input) {

        input = new Scanner(System.in);
        System.out.println("Please Enter a valid mathimatical operation: +,-,*,/ or q to quit: ");
        String userInput = input.nextLine();
        String[] c = {"+", "-", "*", "/", "q"};


        while(true) {
            //if valid math operation then return it as string
            if ((userInput.equals("+")) || (userInput.equals("-")) || (userInput.equals("*")) || (userInput.equals("/"))) { //
                return userInput;
             //if q then quit
            } else if (userInput.equals("q")) {

                return "q";
            } else { //if invalid input then reprompt user to insert a valid operation
                System.out.println("Invalid Charachter...Please Enter a valid mathimatical operation: +,-,*,/ or q to quit: ");
                userInput = input.nextLine();
            }
        }


    }


//checks whether a fraction entered by user is valid
//return: ture if valid fraction false otherwise
//valid fraction is a fraction that does not contain a NON integer and zero or - denominator (invalid: 3/0 , 3/-2 , one, three/four)
    public boolean validFraction(String input){
        String pattern = "^(-?[0-9]+)(/?)(-?[1-9]+)$|^(-?[0-9]+)$";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(input);

        //array to store results of reg expresion match
        String[] r = new String[8];

         while(m.find()){
              r[0] = m.group(0);
              r[1] = m.group(1);
              r[2] = m.group(2);
              r[3] = m.group(3);
              r[4] = m.group(4);
         /*    System.out.println("zero: "+ m.group(0));
             System.out.println("first: "+m.group(1));
             System.out.println("second: " + m.group(2));
             System.out.println("third: " + m.group(3));
             System.out.println("forth: " + m.group(4));
             System.out.println("count: " + m.groupCount()); */

         }



          if(r[0] != null) { // if there is a match
               if (r[2] != null && r[3] != null) { // fraction case ex: 3/4 or -3/4 true
                  int den = Integer.parseInt(r[3]);
                   if (den < 0) { //denominator  < 0 ex: 3/-4 false

                       return false;
                   }

                   return true;
               }else if(r[4] != null){ //one digit case ex: 1 or -2
                   return true;
               }
             return false;

           }else{  // any other input or no match ex: one, two or 3/ false
               return false;
           }



    }

    //prompt user to enter a valid fraction
    //return: a valid fraction, will never return anything unless a valid fraction is inserted by user (infinite loop)
    public Fraction getFraction(Scanner input){


    while(true) { //infinite loop meaning that it must reprompt the user until user inputs some valid fraction
        System.out.println("Please enter a fraction (a/b) or integer (a): " );
        input = new Scanner(System.in);
        String userInput = input.nextLine();

        if (validFraction(userInput)) { //if the fraction is valid ex: denominator != 0
            if (userInput.indexOf('/') > 0) {  //fraction case ex: 1/4
                int fractionSignIndex = userInput.indexOf('/');
                int num = Integer.parseInt(userInput.substring(0, fractionSignIndex));
                int den = Integer.parseInt(userInput.substring(fractionSignIndex + 1));

                return new Fraction(num, den);
            } else {  //one digit case ex: 3 , which means denominator = 1

                int num = Integer.parseInt(userInput);

                return new Fraction(num, 1);
            }

        } else { //any input other than a valid fraction ex: one, e , 4/0
            System.out.print("Invalid fraction.");

        }
    }

    }







}
