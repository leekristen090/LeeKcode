// nonStandardDeck.h
// Assignment 2
// CS 341

// Honor Pledge: 
//
// I pledge that I have neither given nor
// receieved help on this assignment. 
//
// kmlee1

#ifndef NON_STANDARD_DECK_H
#define NON_STANDARD_DECK_H

#include "deck.h"
#include <cstdlib>
#include <ctime>
#include <fstream>

class NonStandardDeck : public Deck
{
    public:
        NonStandardDeck(bool initialize=false);
        ~NonStandardDeck() override;

        bool isEmpty();
        int getNumCards();
        void displayCard(int i);
        void printDeck();
        bool addCard(Card c);
        void shuffle();
        bool mergeDecks(Deck& otherDeck, bool doShuffle);
        void initializeDeck();
        void swapCards(int i, int j);
        Card dealCard();
    private:
        Card* deck_;
        int numCards_;
};
#endif