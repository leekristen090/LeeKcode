// Assignment 6
// CS 341

// Honor Pledge: 
//
// I pledge that I have neither given nor
// receieved help on this assignment. 
//
// kmlee1

// HashTableChaining.h

#ifndef HASHTABLECHAINING_H
#define HASHTABLECHAINING_H
#include "HashTableArray.h"
#include "DoublyLinkedList.h"
// ask dr R if we need to put the linked list stuff in the makefile_hash

class HashTableChaining: public HashTable
{
    public:
        // constructors
        HashTableChaining();
        HashTableChaining(int size);
        // destructors 
        ~HashTableChaining();
        // accessor methods
        void insert(int key, int value);
        int search(int key);
        void remove(int key);
        void print();
    private:
        DoublyLinkedList * entry_;
        int size_;
};
#endif