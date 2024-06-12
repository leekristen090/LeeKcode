// Assignment 2
// CS 341

// Honor Pledge: 
//
// I pledge that I have neither given nor
// receieved help on this assignment. 
//
// kmlee1

#include "card.h"

std::string Card::SUIT[] = {"No Suit", "Spades", "Hearts", "Diamonds", "Clubs"};

std::string Card::FACE[] = {"Joker", "Ace", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"};

/* will have to add code here to implement what is in the .h
can't change anything above this comment
*/

Card::Card() : suitVal_(0), faceVal_(0) {} //constructor ->default
Card::Card(int suit, int face) : suitVal_(suit), faceVal_(face) {} // initializing
Card::~Card() {} // destructor

//making string 
std::string Card::getSuit()
{ return SUIT[suitVal_]; }

std::string Card::getFace()
{ return FACE[faceVal_]; }

// this is for printing
std::string Card::print()
{
    return getFace()+" of "+getSuit();
}

void Card::initialize(int suit, int face)
{
    suitVal_=suit;
    faceVal_=face;
}
