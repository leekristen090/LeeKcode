// nonStandardDeck.cpp
// Assignment 2
// CS 341

// Honor Pledge: 
//
// I pledge that I have neither given nor
// receieved help on this assignment. 
//
// kmlee1

#include "nonStandardDeck.h"
#include <iostream>

NonStandardDeck::NonStandardDeck(bool initialize) : deck_(nullptr), numCards_(0)
{
    if(initialize)
    { initializeDeck(); }
}

NonStandardDeck::~NonStandardDeck()
{ delete [] deck_; }

bool NonStandardDeck::isEmpty() { return numCards_==0; }

int NonStandardDeck:: getNumCards() { return ; }

void NonStandardDeck::displayCard(int i)
{
    if(i>=0&& i<numCards_)
    { std::cout<<deck_[i].print()<<std::endl; }
}

void NonStandardDeck::printDeck()
{
    for(int i=0; i<numCards_;i++)
    { 
        //if(deck_[i].getFace()!="Joker"||deck_[i].getSuit()!="No Suit")
        //{ displayCard(i); }
        displayCard(i);
    }
}

bool NonStandardDeck::addCard(Card c)
{
    Card*newDeck=new Card[numCards_+1];
    for (int i=0; i<numCards_; i++)
    { newDeck[i]=deck_[i]; }
    newDeck[numCards_]=c;
    numCards_++;
    delete [] deck_;
    deck_=newDeck;
    return true;
}

void NonStandardDeck::shuffle()
{
    std::srand(static_cast<unsigned>(std::time(nullptr)));
    int numSwaps=3*numCards_;
    for(int i=0; i<numSwaps;i++)
    {
        int randIndex1=std::rand()%numCards_;
        int randIndex2=std::rand()%numCards_;
        swapCards(randIndex1, randIndex2);
    }
}

bool NonStandardDeck::mergeDecks(Deck& otherDeck, bool doShuffle)
{
    int totalCards = numCards_+otherDeck.getNumCards();
    Card* newDeck=new Card[totalCards];

    // copy current deck
    for (int i=0; i<totalCards;i++)
    { newDeck[i]=deck_[i]; }
    // copy otherDeck
    for (int i=0; i<totalCards; i++)
    { newDeck[i]=otherDeck.dealCard(); }

    delete[]deck_;
    deck_=newDeck;
    numCards_=totalCards;

    if(doShuffle)
    { shuffle(); }
    return true;
}

void NonStandardDeck::initializeDeck()
{
    delete[]deck_;
    std::ifstream inputFile("deck.txt");
    if(inputFile.is_open())
    {
        int numCards_;
        inputFile>>numCards_;
        deck_=new Card[numCards_];
        for(int i=0; i<numCards_;i++)
        {
            int suit, face;
            inputFile>> suit>> face;
            deck_[i].initialize(suit,face);
            numCards_++;
        }
        inputFile.close();
    }
    else
    { std::cout<< "Error opening deck.txt file."<<std::endl; }
}

void NonStandardDeck::swapCards(int i, int j)
{
    if(i>=0 && i<numCards_ && j>=0 && j<numCards_)
    {
        Card temp=deck_[i];
        deck_[i]=deck_[j];
        deck_[j]=temp;
    }
}

Card NonStandardDeck::dealCard()
{
    if(numCards_>=0)
    { 
        Card topCard = deck_[numCards_-1]; 
        numCards_--;
        return topCard;
    }
    //else
    //{ return Card(0,0); }
}