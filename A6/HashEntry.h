// Assignment 6
// CS 341

// Honor Pledge: 
//
// I pledge that I have neither given nor
// receieved help on this assignment. 
//
// kmlee1

// HashEntry.h

#ifndef HASHENTRY_H
#define HASHENTRY_H
#include "HashTable.h"

enum Status { EMPTY, OCCUPITED, REMOVED };

class HashEntry
{
    public:
        // constructor
        HashEntry();
        HashEntry(int key, int value);
        // destructor
        ~HashEntry();
        // accessors
        int getKey();
        int getValue();
        Status getStatus();
        void setKey(int key);
        void setValue(int value);
        void setStatus(Status status);
    private:
        int key_;
        int value_;
        Status status_;
};

#endif