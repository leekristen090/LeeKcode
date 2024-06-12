// deck.h
// Assignment 2
// CS 341

// Honor Pledge: 
//
// I pledge that I have neither given nor
// receieved help on this assignment. 
//
// kmlee1

#ifndef DECK_H
#define DECK_H
#include "card.h"
#include <iostream>

class Deck
{
    public:
        Deck();
        virtual ~Deck();
        virtual void initializeDeck()=0;
        bool isEmpty();
        int getNumCards();
        void displayCard(int i);
        void printDeck();
        bool addCard(Card c);
        void shuffle();
        bool mergeDecks(Deck &otherDeck, bool doShuffle);
        void swapCards(int i, int j);
        Card dealCard();
};
#endif