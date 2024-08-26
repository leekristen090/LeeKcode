// Assignment 2
// CS 341

// Honor Pledge: 
//
// I pledge that I have neither given nor
// receieved help on this assignment. 
//
// kmlee1

#ifndef STANDARD_DECK_H // if this has not been defined, then define it
#define STANDARD_DECK_H
/*
this is a header guard
*/

#include "card.h"
#include "deck.h"
#include "nonStandardDeck.h"

#define DECK_SIZE 52

/**
 * @class StandardDeck
 *
 * The StandardDeck class represents a standard deck of 52 cards.
 * 
 */
class StandardDeck
{
	public:
		/// Default constructor.
		StandardDeck(bool initialize=false);
		
		/// Default destructor.
		~StandardDeck();

		/**
	     * Returns True/False (1/0) whether or not the Deck is empty.
	     *
	     * @return          Boolean
	    */ 
		bool isEmpty();	

		/**
	     * Returns the number of cards remaining in the deck.
	     *
	     * @return          Integer		value
	    */ 
		int getNumCards();

		/**
	     * Displays the i'th card in the Deck.
	     *
		 * @param[in]      Index
	    */
		void displayCard(int i);

		/**
	     * Prints the contents of the Deck. This method should call the 
		 * print() method on each Card.
	    */
		void printDeck();	

		bool addCard(Card c);
		void shuffle();
		bool mergeDecks(StandardDeck &, bool doShuffle=false);
		void initializeDeck();

		Card dealCard();
		bool mergeNonStandDeck(NonStandardDeck &otherDeck, bool doShuffle=false);
		
	protected: 
		Card * deck_;	// Pointer to record the location of the array of Cards in memory.
		// will be using the heap, so we will have to keep track of this
		int numCards_;	// Stores the number of Cards currently in the deck.
		void swapCards(int i, int j);
};

#endif //end this compiler level conditional, this ensures that if this class has been defined somewhere else, don't do it again