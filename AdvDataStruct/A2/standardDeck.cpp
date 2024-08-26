// Assignment 2
// CS 341

// Honor Pledge: 
//
// I pledge that I have neither given nor
// receieved help on this assignment. 
//
// kmlee1

#include "standardDeck.h"
#include "nonStandardDeck.h"
#include <iostream>
#include <cstdlib>
#include <ctime>
#include <fstream>

//StandardDeck::StandardDeck(bool initialize) : deck_(nullptr), numCards_(0)
StandardDeck::StandardDeck(bool initialize) : deck_(new Card[DECK_SIZE]), numCards_(0)
{
    /* //phase 2
    deck_=new Card[DECK_SIZE];
    for(int i=0; i<DECK_SIZE; i++)
    {
        deck_[i].initialize(i/13+1, (i%13)+1);
        //deck_[i].initialize(i%4, i%13+1);
        numCards_++;
    }*/

    //phase 3
    if(initialize)
    {initializeDeck();}
}

StandardDeck::~StandardDeck() { delete[] deck_; }

bool StandardDeck::isEmpty() { return numCards_==0; }
int StandardDeck:: getNumCards() { return numCards_; }

// Printing and stuff 
void StandardDeck::displayCard(int i) 
{
    if(i>=0&&i<DECK_SIZE)
    { std::cout<<deck_[i].print()<<std::endl; }
    else
    { std::cout<< "invalid"<< std::endl; }
}

// printing the deck
void StandardDeck::printDeck() 
{
    for(int i=0; i<DECK_SIZE;i++)
    { 
        if(deck_[i].getFace()!="Joker"||deck_[i].getSuit()!="No Suit")
        {displayCard(i); }
    }
}

// PHASE 3
// add a card to the end of the deck
bool StandardDeck::addCard(Card c)
{
    if(numCards_<=DECK_SIZE)
    {
        deck_[numCards_] = c;
        numCards_++;
        return true;
    }
    else
    { return false; }
}

// Shuffle the deck 
void StandardDeck::shuffle()
{
    std::srand(static_cast< unsigned>(std::time(nullptr)));
    // performing 3x the number of cards in the deck
    int numSwaps=3*numCards_;
    for(int i=0; i< numSwaps; i++)
    {
        int randIndex1=std::rand()%numCards_;
        int randIndex2=std::rand()%numCards_;
        swapCards(randIndex1, randIndex2);
    }
}

//merging decks and stuff 
bool StandardDeck:: mergeDecks(StandardDeck& otherDeck, bool doShuffle)
{
    //int totalCards=numCards_+otherDeck.numCards_;
    //std:: cout<< "Number of cards: "<< numCards_<<std::endl;
    //if(totalCards>=DECK_SIZE)
    if(numCards_<DECK_SIZE)
    {
        for(int i=0; i<otherDeck.numCards_; i++)
        {
            deck_[numCards_]=otherDeck.deck_[i];
            numCards_++;
            //addCard(otherDeck.deck_[i]);
        }
        
        if(doShuffle)
        { shuffle(); }
        otherDeck.numCards_=0;
        return true;
    }
    else 
    { return false; }
}

//initializing the deck with this thingy
void StandardDeck:: initializeDeck()
{
    delete[]deck_;
    deck_=new Card[DECK_SIZE];
    numCards_=0;
    for(int i=0; i<DECK_SIZE;i++)
    {
        deck_[i].initialize(i/13+1,(i%13)+1);
        numCards_++;
    }
}

//swaping cards privately 
void StandardDeck:: swapCards(int i, int j)
{
    if(i>=0 && i<numCards_ && j>=0 && j<numCards_)
    {
        Card temp=deck_[i];
        deck_[i]= deck_[j];
        deck_[j]=temp;
    }
}

//this is for dealing the top most card in game play
Card StandardDeck:: dealCard()
{
    if(numCards_>0)
    {
        Card topCard=deck_[numCards_-1];
        numCards_--;
        return topCard;
    }
    else // this is when there are no cards left in the deck
    { 
        return Card(0,0); 
    } // this is what is causing the 'jack of no suit' when dealing cards to players
}