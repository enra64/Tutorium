package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class HangMan {
    /**
     * Word the user has to guess
     */
    private static String mTargetWord;

    /**
     * An array of guessed characters
     */
    private static char[] mGuessedCharacters;

    /**
     * Characters that were entered, but are wrong.
     */
    private static List<Character> mWrongCharacters;

    /**
     * Death occurs at 0 life
     */
    private static int mCurrentLife = 10;

    /**
     * The scanner used to get user input
     */
    private static Scanner mScanner;

    /**
     * The amount of chars filled into the word
     */
    private static int mFilledInChars = 0;

    private static int mGameCounter = 0;

    public static void main(String[] args){
        mScanner = new Scanner(System.in);
        mWrongCharacters = new ArrayList<>();

        initializeHangman();

        boolean gameIsFinished = false;

        while(!gameIsFinished){
            mGameCounter++;
            checkInput();
            draw();
            gameIsFinished = mTargetWord.length() - mFilledInChars == 0 || mCurrentLife <= 0;
        }
        if(mCurrentLife > 0)
            System.out.println("you win");
        else
            System.out.println("you lost");
    }

    /**
     * Initialize class variables and ask the user for the game configuration
     */
    private static void initializeHangman(){
        System.out.println("Target word?");
        mTargetWord = mScanner.nextLine();
        System.out.println("The target word is now: " + mTargetWord + "\n");

        mGuessedCharacters = new char[mTargetWord.length()];

        //fill the guessedCharacters array with underscores to ease visualization
        for(int i = 0; i < mGuessedCharacters.length; i++)
            mGuessedCharacters[i] = '_';
    }

    /**
     * Get a valid, one char input from the user
     * @return one char the user wants to enter
     */
    private static char getInput() {
        //get input
        System.out.println("Enter one character.");
        String inputString = mScanner.nextLine();
        //check for validity
        if (inputString.length() != 1){
            System.out.println("Please enter exactly one character!");
            return getInput();
        }
        return inputString.charAt(0);
    }

    /**
     * Get input from the user, and check whether it is valid for the hangman game
     */
    private static void checkInput(){
        //get one-char input
        char input = getInput();
        //check in hangman system
        if (!mTargetWord.contains(String.valueOf(input))) {
            mWrongCharacters.add(input);
            mCurrentLife--;
            System.out.println("This is a wrong character!");
        }
        else{
            for(int i = 0; i < mTargetWord.length(); i++){
                if(mTargetWord.charAt(i) == input){
                    mGuessedCharacters[i] = input;
                    mFilledInChars++;
                }
            }
            System.out.println("That is a correct character!");
        }
    }

    /**
     * Output a hangman representation to the console
     */
    private static void draw(){
        System.out.println("You currently have " + mCurrentLife + " life, your guessed chars are:");
        System.out.println(Arrays.toString(mGuessedCharacters) + '\n');
    }
}