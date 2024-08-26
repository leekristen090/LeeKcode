// Assignment 6
// CS 341

// Honor Pledge: 
//
// I pledge that I have neither given nor
// receieved help on this assignment. 
//
// kmlee1

// HashTableArray.h

#ifndef HASHTABLEARRAY_H
#define HASHTABLEARRAY_H
#include "HashEntry.h"

enum ProbingType
{ LINEAR_PROBING, QUADRATIC_PROBING, SEP_CHAIN, CUCKOO };


class HashTableArray: public HashTable
{
    public:
        HashTableArray(int size, ProbingType probingType);
        virtual ~HashTableArray();
        virtual void insert(int key, int value);
        virtual int search(int key) override;
        virtual void remove(int key) override;
        virtual void print() override;
    private:
        HashEntry* table;
        int tableSize_;
        ProbingType probingType_;
};
#endif