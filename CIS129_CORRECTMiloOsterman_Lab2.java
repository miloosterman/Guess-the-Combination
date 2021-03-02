/*******************************************************************************************************************
  * CIS129_MiloOsterman_Lab2.java
  * Author: Milo Osterman
  * CIS 129 - Programming and Problem Solving I
  * Pima Community College
  ******************************************************************************************************************
  * This program is a game in which the user guesses for a 3-digit combination.
  ******************************************************************************************************************/

import java.util.*;

public class CIS129_CORRECTMiloOsterman_Lab2{

//Declare variables for the game.
public static int HIGH_VAL = 999;
public static int LOW_VAL = 900;
public static int GUESSES = 5;
public static int ROUNDS = 3;
public static double CREDIBILITY = 100;
public static double CREDIBILITY_LOST = CREDIBILITY / ROUNDS;

//Main module containing introduction, gets username, runs guessing game and takes result, returns end message.
 public static void main (String [] args){
   //Introduction
   introMessage();
   //Get user name
   String userName = IR.getString("What are you called in your city?");
   //Runs main guessing game and returns whether guessed right or not.
   do{
   boolean result = guessingGame(userName);
   //Displays ending message based on if user guessed correctly or not, asks if they want to play again.
   displayResults(result, userName);
   }
   while(IR.getYorN("Does a new locksmith want to try to open the lock?"));

   goodByeMessage(userName);

 }
 //Intro message
 public static void introMessage(){

   System.out.println("Guess the combination!");
   System.out.println("You are a great locksmith trying to open a door by guessing its combination.");
   System.out.println("If you can guess the number that opens the door, you will solve the case.");
   System.out.println("If you have not opened the door after " + GUESSES + " guesses, you will lose " + (int)CREDIBILITY_LOST + "% of your credibility.");
   System.out.println("After " + ROUNDS +  " rounds, you will have lost all your credibility and will retire in defeat.");
   System.out.println("     Good luck - you'll need it!");
   System.out.println("**********************************************");

 }
//Main game module
 public static boolean guessingGame(String userName){

  double userCredibility = CREDIBILITY;

  System.out.println("OK, let's do this " + userName + ".");
//Nested for loops allow for one for loop that runs each round and another which tracks guesses
  for (int numOfRounds = 0; numOfRounds < ROUNDS; numOfRounds++) {

   System.out.println("Round #" + (numOfRounds + 1));

   int codeAnswer = IR.getRandomNumber(LOW_VAL, HIGH_VAL);

   int lowValue = LOW_VAL;
   int highValue = HIGH_VAL;

   for (int numOfGuesses = 0; numOfGuesses < GUESSES; numOfGuesses++) {

     if(numOfGuesses == GUESSES - 1){
       System.out.print("Last Guess: ");
     } else {
       System.out.print("Guess #" + (numOfGuesses + 1) + ":   ");
     }

    int userGuess = IR.getInteger("What is your guess from " + lowValue + " to " + highValue + "?");

    while (isInvalid(userGuess, lowValue, highValue)) {

     System.out.println("You have entered a number out of range.");
     userGuess = IR.getInteger("What is your guess from " + lowValue + " to " + highValue + "?");

   }

     if (userGuess == codeAnswer) {
      return true;
     }
     //CHECK IF GUESS WAS HIGH, IF HIGH NEW HIGH VAL IS USERGUESS - 1
     boolean highTrueFalseLow = checkHigh(userGuess, codeAnswer);
     if (highTrueFalseLow) {
      highValue = userGuess - 1;
     }
     //IF LOW, NEW LOW VALUE IS USERGUESS + 1
     else {
      lowValue = userGuess + 1;
     }
  }
   //Each cycle of the loops the user credibility will go down by the preset variables
   userCredibility = userCredibility - CREDIBILITY_LOST;

   System.out.println("Wrong, " + userName + "! The 3-digit combination was " + codeAnswer);
   System.out.println("Your credibility as a locksmith is now at " + (int)userCredibility + "%");
   }

  return false;

 }
//Checks if the guess was higher than the correct answer, changes the range of what the answer could be.
   public static boolean checkHigh(int userGuess, int codeAnswer){

   if (userGuess > codeAnswer){
    return true;
   }
   else {
     return false;
   }

  }
//Input validation.
  public static boolean isInvalid(int userGuess, int lowValue, int highValue){

     if (userGuess < lowValue || userGuess > highValue){
       return true;
     } else {
       return false;
     }

   }
  //Displays end results of the game.
  public static void displayResults(boolean results, String userName){
    if(results){
    System.out.println("That's it! Well done, " + userName + "!");
    System.out.println(userName + " opens the door and solves the case!");
    }
    else{
      System.err.println("***** Game Over *****");
      System.out.println("The light fades as you lower your head in defeat.");
      System.out.println("Goodbye, " + userName + ". Maybe you should brush up on your lock-picking skills!");
    }
  }

  public static void goodByeMessage(String userName){
       System.out.println("Goodbye, " + userName + ". Until we meet again.");
     }
 }
