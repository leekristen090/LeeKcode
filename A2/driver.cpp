// Assignment 2
// CS 341

// Honor Pledge: 
//
// I pledge that I have neither given nor
// receieved help on this assignment. 
//
// kmlee1

//driver 
#include "card.h"
#include "standardDeck.h"
#include "nonStandardDeck.h"
#include <iostream>
#define NUM_SIM 50

//---------------------------------------------------------------------------------------------------------
//---------------------------------------------------------------------------------------------------------

void phaseOne()
{
    // Creating array of cards
    Card cards[52];
    
    int cardIndex = 0;
    for(int suit=1; suit <=4; suit++)
    {
        for(int face=1; face<=13; face++)
        {
            cards[cardIndex].initialize(suit, face);
            cardIndex++;
        }
    }
    
    // Printing to Thomas
    std::cout<< "The contents of the array: " << std::endl;
    std::cout<< " " << std::endl;
    for (int i=0;i<52;i++)
    { std::cout<< cards[i].print()<< std::endl; }
}

//---------------------------------------------------------------------------------------------------------
//---------------------------------------------------------------------------------------------------------

void phaseTwoThree()
{
    //this is from phases 2 and 3
    // PHASE 2
    StandardDeck myDeck(true);
    // PHASE 3
    StandardDeck otherDeck(false);
    //myDeck.printDeck();
    std:: cout<< "Number of cards in the deck: "<< myDeck.getNumCards()<< std::endl;
    std::cout<< "Is the deck Empty?: "<< (myDeck.isEmpty()? "Yes":"No" )  <<std::endl;
    std::cout<< "50th card: " <<std::endl;
    myDeck.displayCard(49);
    std::cout<< " " << std::endl;
    
    std::cout<< "Shuffled deck: " << std::endl;
    myDeck.shuffle();
    myDeck.printDeck();
    std::cout<< " " << std::endl;
    std::cout<< "50th card: " <<std::endl;
    myDeck.displayCard(49);
    std::cout<< " " << std::endl;
    std::cout<< "Merge Success: " << (otherDeck.mergeDecks(myDeck,true)? "Yes" : "No")<<std::endl;
    std::cout<< "Merged deck: " << std::endl;
    otherDeck.printDeck(); 
    std::cout<< " " << std::endl;
    std:: cout<< "Number of cards in the merged deck: "<< otherDeck.getNumCards()<< std::endl;
    std::cout<< "50th card: " <<std::endl;
    otherDeck.displayCard(49);
    std::cout<< " " << std::endl;
}

//---------------------------------------------------------------------------------------------------------
//---------------------------------------------------------------------------------------------------------

void phaseFour()
{
    int player1Wins=0;
    int player2Wins=0;
    int player1TotalScore=0;
    int player2TotalScore=0;
    
    for(int sim=0; sim<NUM_SIM; sim++)
    {
        StandardDeck deck(true); // PHASE 4
        StandardDeck player1Deck(false);
        StandardDeck player2Deck(false);
        StandardDeck battleGround(false);
        deck.shuffle(); // PHASE 4
        //deck.printDeck();

        // spliting the deck between the two players 
        for(int i=0; i<DECK_SIZE/2; i++)
        {
            player1Deck.addCard(deck.dealCard()); // phase 4
            player2Deck.addCard(deck.dealCard()); //phase 4
        }
        player1Deck.shuffle();
        player2Deck.shuffle();
        //player1Deck.printDeck();
        int round=1;
        
        //war game play
        while(!player1Deck.isEmpty() && !player2Deck.isEmpty())
        {
            int player1FaceVal, player2FaceVal;
            
            // player 1 plays a card 
            Card player1Card =player1Deck.dealCard();
            if(player1Card.getFace()=="Ace")
            { player1FaceVal=1; }
            else if(player1Card.getFace()=="Two")
            { player1FaceVal=2; }
            else if(player1Card.getFace()=="Three")
            { player1FaceVal=3; }
            else if(player1Card.getFace()=="Four")
            { player1FaceVal=4; }
            else if(player1Card.getFace()=="Five")
            { player1FaceVal=5; }
            else if(player1Card.getFace()=="Six")
            { player1FaceVal=6; }
            else if(player1Card.getFace()=="Seven")
            { player1FaceVal=7; }
            else if(player1Card.getFace()=="Eight")
            { player1FaceVal=8; }
            else if(player1Card.getFace()=="Nine")
            { player1FaceVal=9; }
            else if(player1Card.getFace()=="Ten")
            { player1FaceVal=10; }
            else if(player1Card.getFace()=="Jack")
            { player1FaceVal=11; }
            else if(player1Card.getFace()=="Queen")
            { player1FaceVal=12; }
            else if(player1Card.getFace()=="King")
            { player1FaceVal=13; } 
            // player 2 plays a card
            Card player2Card =player2Deck.dealCard();
            if(player2Card.getFace()=="Ace")
            { player2FaceVal=1; }
            else if(player2Card.getFace()=="Two")
            { player2FaceVal=2; }
            else if(player2Card.getFace()=="Three")
            { player2FaceVal=3; }
            else if(player2Card.getFace()=="Four")
            { player2FaceVal=4; }
            else if(player2Card.getFace()=="Five")
            { player2FaceVal=5; }
            else if(player2Card.getFace()=="Six")
            { player2FaceVal=6; }
            else if(player2Card.getFace()=="Seven")
            { player2FaceVal=7; }
            else if(player2Card.getFace()=="Eight")
            { player2FaceVal=8; }
            else if(player2Card.getFace()=="Nine")
            { player2FaceVal=9; }
            else if(player2Card.getFace()=="Ten")
            { player2FaceVal=10; }
            else if(player2Card.getFace()=="Jack")
            { player2FaceVal=11; }
            else if(player2Card.getFace()=="Queen")
            { player2FaceVal=12; }
            else if(player2Card.getFace()=="King")
            { player2FaceVal=13; }


            //debugging 
            std::cout<<  "player 1 plays " << player1Card.print()<< ", player 2 plays " << player2Card.print()<< std::endl;
            //std::cout<< "Player 1 val " <<player1FaceVal<< std::endl;
            //std::cout<< "Player 2 val " <<player2FaceVal<< std::endl;
            //std::cout<< " " << std::endl;

            //checking if the cards match
            if(player1FaceVal==player2FaceVal)
            {
                std::cout<< "Match!" << std::endl;

                // swithching which round a player takes the matches from the battle ground
                if(round%2==1)
                {
                    //player1Deck.mergeDecks(battleGround, true);
                    player1Deck.addCard(player1Card);
                    player1Deck.addCard(player2Card);
                    std:: cout<< "Player 1 takes all the cards." << std::endl;
                    player1Deck.shuffle();
                }
                else
                {
                    //player2Deck.mergeDecks(battleGround, true);
                    player2Deck.addCard(player1Card);
                    player2Deck.addCard(player2Card);
                    std:: cout<< "Player 2 takes all the cards." << std::endl;
                    player2Deck.shuffle();
                }

            }
            else //if the cards don't match
            { 
                if(player1FaceVal>player2FaceVal)
                {
                    std::cout<<"player 1 card is bigger"<< std::endl;
                    //battleGround.addCard(player1Card);
                    //battleGround.addCard(player2Card);
                    //player1Deck.mergeDecks(battleGround, true);
                    player1Deck.addCard(player1Card);
                    player1Deck.addCard(player2Card);

                    player1Deck.shuffle();
                    player2Deck.shuffle();
                    //std:: cout<< "Player 1 num cards "<< player1Deck.getNumCards()<< std::endl;
                }
                else
                {
                    std::cout<<"player 2 card is bigger"<< std::endl;
                    //battleGround.addCard(player1Card);
                    //battleGround.addCard(player2Card);
                    //player2Deck.mergeDecks(battleGround, true);
                    player2Deck.addCard(player1Card);
                    player2Deck.addCard(player2Card);
                    player2Deck.shuffle();
                    player1Deck.shuffle();
                    //std:: cout<< "Player 2 num cards "<< player2Deck.getNumCards()<< std::endl;
                }
            }
            //std:: cout<< "Player 1 num cards "<< player1Deck.getNumCards()<< std::endl;
            //std:: cout<< "Player 2 num cards "<< player2Deck.getNumCards()<< std::endl;
            //std:: cout<< " "<< std::endl;
            //std:: cout<< "battle field cards: " << std::endl;
            //battleGround.printDeck();
            //std::cout<< " " << std::endl;
            round++;
        }
        
        // checking which deck is empty to assign wins and score things
        if(player1Deck.isEmpty())
        {
            //std::cout<<"Player 1 deck is empty"<< std::endl;
            player2Wins++;
            player2TotalScore += player2Deck.getNumCards();
        }
        else if(player2Deck.isEmpty())
        {
            //std::cout<<"Player 2 deck is empty"<< std::endl;
            player1Wins++;
            player1TotalScore += player1Deck.getNumCards();
        }
    }

    // PHASE 4 and 5
    //calculating averages and stuff
    int player1Avg=(player1TotalScore)/NUM_SIM;
    int player2Avg=(player2TotalScore)/NUM_SIM;

    std::cout<< " " << std::endl;
    if(player1Wins>player2Wins)
    { std::cout<< "Player 1 was the champion with "<< player1Wins<< " victories over player 2."<< std:: endl; }
    else 
    { std::cout<< "Player 2 was the champion with "<< player2Wins<< " victories over player 1."<< std:: endl; }
    std::cout<< "Player 1 average score: "<< player1Avg<< std:: endl;
    std::cout<< "Player 2 average score: "<< player2Avg<< std:: endl; 
}

//---------------------------------------------------------------------------------------------------------
//---------------------------------------------------------------------------------------------------------

// PHASE 5
void phaseFive()
{
    int player1Wins=0;
    int player2Wins=0;
    int player1TotalScore=0;
    int player2TotalScore=0;
    
    for(int sim=0; sim<NUM_SIM; sim++)
    {
        NonStandardDeck nonStandard(true); // PHASE 5
        int deckSize_=nonStandard.getNumCards();
        //StandardDeck deck(false); 
        NonStandardDeck player1Deck(false);
        NonStandardDeck player2Deck(false);
        //NonStandardDeck battleGround(false);
        nonStandard.shuffle(); // PHASE 5
        //deck.mergeNonStandDeck(nonStandard, false); 
        //deck.printDeck();

        // spliting the deck between the two players 
        for(int i=0; i<deckSize_/2; i++)
        {
            player1Deck.addCard(nonStandard.dealCard()); //phase 5
            player2Deck.addCard(nonStandard.dealCard()); //phase 5
        }
        player1Deck.shuffle();
        player2Deck.shuffle();
        //player1Deck.printDeck();
        int round=1;
        
        //war game play
        while(!player1Deck.isEmpty() && !player2Deck.isEmpty())
        {
            int player1FaceVal, player2FaceVal;
            // player 1 plays a card 
            Card player1Card =player1Deck.dealCard();
            if(player1Card.getFace()=="Ace")
            { player1FaceVal=1; }
            else if(player1Card.getFace()=="Two")
            { player1FaceVal=2; }
            else if(player1Card.getFace()=="Three")
            { player1FaceVal=3; }
            else if(player1Card.getFace()=="Four")
            { player1FaceVal=4; }
            else if(player1Card.getFace()=="Five")
            { player1FaceVal=5; }
            else if(player1Card.getFace()=="Six")
            { player1FaceVal=6; }
            else if(player1Card.getFace()=="Seven")
            { player1FaceVal=7; }
            else if(player1Card.getFace()=="Eight")
            { player1FaceVal=8; }
            else if(player1Card.getFace()=="Nine")
            { player1FaceVal=9; }
            else if(player1Card.getFace()=="Ten")
            { player1FaceVal=10; }
            else if(player1Card.getFace()=="Jack")
            { player1FaceVal=11; }
            else if(player1Card.getFace()=="Queen")
            { player1FaceVal=12; }
            else if(player1Card.getFace()=="King")
            { player1FaceVal=13; } 
            // player 2 plays a card
            Card player2Card =player2Deck.dealCard();
            if(player2Card.getFace()=="Ace")
            { player2FaceVal=1; }
            else if(player2Card.getFace()=="Two")
            { player2FaceVal=2; }
            else if(player2Card.getFace()=="Three")
            { player2FaceVal=3; }
            else if(player2Card.getFace()=="Four")
            { player2FaceVal=4; }
            else if(player2Card.getFace()=="Five")
            { player2FaceVal=5; }
            else if(player2Card.getFace()=="Six")
            { player2FaceVal=6; }
            else if(player2Card.getFace()=="Seven")
            { player2FaceVal=7; }
            else if(player2Card.getFace()=="Eight")
            { player2FaceVal=8; }
            else if(player2Card.getFace()=="Nine")
            { player2FaceVal=9; }
            else if(player2Card.getFace()=="Ten")
            { player2FaceVal=10; }
            else if(player2Card.getFace()=="Jack")
            { player2FaceVal=11; }
            else if(player2Card.getFace()=="Queen")
            { player2FaceVal=12; }
            else if(player2Card.getFace()=="King")
            { player2FaceVal=13; }

            //debugging 
            //std::cout<< "Round: "<<round<<std::endl;
            //std::cout<<  "player 1 plays " << player1Card.print()<< ", player 2 plays " << player2Card.print()<< std::endl;
            //std::cout<< "Player 1 val " <<player1FaceVal<< std::endl;
            //std::cout<< "Player 2 val " <<player2FaceVal<< std::endl;

            //checking if the cards match
            if(player1FaceVal==player2FaceVal)
            {
                //std::cout<< "Match!" << std::endl;

                // swithching which round a player takes the matches from the battle ground
                if(round%2==1)
                {
                    //player1Deck.mergeDecks(battleGround, true);
                    //player1Deck.mergeDecks(battleGround, true);
                    player1Deck.addCard(player1Card);
                    player1Deck.addCard(player2Card);
                    player1Deck.shuffle();
                    //std:: cout<< "Player 1 takes all the cards." << std::endl;
                }
                else
                {
                    //player2Deck.mergeDecks(battleGround, true);
                    //player2Deck.mergeDecks(battleGround, true);
                    player2Deck.addCard(player1Card);
                    player2Deck.addCard(player2Card);
                    player2Deck.shuffle();
                    //std:: cout<< "Player 2 takes all the cards." << std::endl;
                }
            }
            else //if the cards don't match
            { 
                if(player1FaceVal>player2FaceVal)
                {
                    //std::cout<<"player 1 card is bigger"<< std::endl;
                    //battleGround.addCard(player1Card);
                    //battleGround.addCard(player2Card);
                    //player1Deck.mergeDecks(battleGround, true);
                    //player1Deck.mergeDecks(battleGround, true);
                    player1Deck.addCard(player1Card);
                    player1Deck.addCard(player2Card);

                    player1Deck.shuffle();
                    //player2Deck.shuffle();
                    //std:: cout<< "Player 1 num cards "<< player1Deck.getNumCards()<< std::endl;
                }
                else
                {
                    //std::cout<<"player 2 card is bigger"<< std::endl;
                    //battleGround.addCard(player1Card);
                    //battleGround.addCard(player2Card);
                    //player2Deck.mergeDecks(battleGround, true);
                    //player2Deck.mergeDecks(battleGround, true);
                    player2Deck.addCard(player1Card);
                    player2Deck.addCard(player2Card);
                    player2Deck.shuffle();
                    //player1Deck.shuffle();
                    //std:: cout<< "Player 2 num cards "<< player2Deck.getNumCards()<< std::endl;
                }
            }
            //std:: cout<< "Player 1 num cards "<< player1Deck.getNumCards()<< std::endl;
            //std:: cout<< "Player 2 num cards "<< player2Deck.getNumCards()<< std::endl;
            //std:: cout<< " "<< std::endl;
            round++;
        }
        
        // checking which deck is empty to assign wins and score things
        if(player1Deck.isEmpty())
        {
            //std::cout<<"Player 1 deck is empty"<< std::endl;
            player2Wins++;
            player2TotalScore += player2Deck.getNumCards();
        }
        else if(player2Deck.isEmpty())
        {
            //std::cout<<"Player 2 deck is empty"<< std::endl;
            player1Wins++;
            player1TotalScore += player1Deck.getNumCards();
        }
    }

    // PHASE 4 and 5
    //calculating averages and stuff
    int player1Avg=(player1TotalScore)/NUM_SIM;
    int player2Avg=(player2TotalScore)/NUM_SIM;

    std::cout<< " " << std::endl;
    if(player1Wins>player2Wins)
    { std::cout<< "Player 1 was the champion with "<< player1Wins<< " victories over player 2."<< std:: endl; }
    else 
    { std::cout<< "Player 2 was the champion with "<< player2Wins<< " victories over player 1."<< std:: endl; }
    std::cout<< "Player 1 average score: "<< player1Avg<< std:: endl;
    std::cout<< "Player 2 average score: "<< player2Avg<< std:: endl; 
}

//---------------------------------------------------------------------------------------------------------
//---------------------------------------------------------------------------------------------------------

int main()
{
    std::cout<< " " << std::endl;
    std::cout<< "** Welcome to Assignment 2 **" << std::endl;
    std::cout<< " " << std::endl;

    //phaseOne();
    //phaseTwoThree();
    //phaseFour();
    phaseFive();

    return 0;
}