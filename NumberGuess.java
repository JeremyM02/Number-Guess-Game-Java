
/**
 * Challenge the computer on a number guessing game from 1 - 100.
 *
 * @author Jeremy M.
 * @version 9/1/22
 */

import java.util.Scanner;
public class NumberGuess
{
    //The method below is from another class I made called RandomNumber, though recreated here for the teacher.

    private static int pickInteger(int min, int max){
        return (int)(Math.random() * (max - min + 1) + min);
    }

    private static boolean pickStatement(){
        return pickInteger(0, 1) == 1; 
    }

    static Scanner scanner = new Scanner(System.in);
    public static void playGame(int maxRounds, boolean hardMode){
        System.out.println("I will guess a number from 1 to 100!");

        //This program has a hard mode! 
        //The number can be added/subtracted by a integer from 1 to 5.
        if (hardMode){
            System.out.println
            ("\nHARD MODE: ACTIVATE! \n" +
                "My number will change by 1-5 every guess!");
        }

        //Refers to the pickInteger method. Getting an integer from 1 to 100.
        int number = pickInteger(1,100);

        //For Hard Mode,since the number variable will change constantly.
        int ORIGINAL_NUMBER = number;

        /*
         * Max Rounds is the number of guesses the user has.
         * In a default game, there's 7 rounds. 
         * That way, the user can have a 100% win rate.
         */
        for (int round = 1; round <= maxRounds; round++){
            System.out.print("\nRound " + round + "/" + maxRounds);
            System.out.println(" (Guess my number)");

            //Get the user's guess
            int guess = scanner.nextInt();

            //Check for invalid guesses
            if (guess == -1){
                System.out.println("Wow, you just gave up?");
                break;
            }
            if (guess > 100){
                System.out.println("That's way too high!");
                continue;
            } else if (guess < 0){
                System.out.println("No negatives!");
                continue;
            }

            //Compare the guess to the number
            if (guess < number){
                System.out.println(guess + " is TOO LOW!");
            } else if (guess > number){
                System.out.println(guess + " is TOO HIGH!");
            } else if (guess == number){
                System.out.println("Correct! You win!");
                break;
            }

            /*For Hard Mode
             *It tells the user if they guessed the original number, but not the actual number which may have since changed. 
             */
            if(hardMode && guess == ORIGINAL_NUMBER){
                System.out.println("But that was the original number!");
            }

            //Like said earlier, this will add or subtract to the number by a value between 1 and 5, both inclusive.
            if (hardMode){
                int change;
                //This whole loop ensures that the change is an integer from -5 and 5, but not 0.
                while (true){
                    change = pickInteger(1, 5);

                    if(pickStatement() == true){
                        change *= -1;
                    }
                    
                    //The condition checks if the changed number would still be between 1 and 100, or else it will loop.
                    if(number + change >= 0 && number + change <= 100){
                        break;
                    }
                }
                number += change;
                
                if (change > 0){
                    System.out.println("Number increased by " + change);
                } else if (change < 0){
                    System.out.println("Number decreased by " + (change * -1));
                }
            }
        }
        System.out.println("\nGAME OVER\nThe Number was " + number);
        //At the end of a Hard Mode game, it tells the original number.
        if(hardMode){
            System.out.println("The Original Number was " + ORIGINAL_NUMBER);
        }
    }

    //For teacher use, no Hard Mode and seven guesses.
    public static void playGameNormalMode(){
        playGame(7, false);
    }

    public static void playGameHardMode(){
        playGame(10, true);
    }
}
