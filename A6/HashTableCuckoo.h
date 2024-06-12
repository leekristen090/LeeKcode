// Assignment 6
// CS 341

// Honor Pledge: 
//
// I pledge that I have neither given nor
// receieved help on this assignment. 
//
// kmlee1

// HashTableCuckoo.h

#ifndef HASHTABLECUCKOO_H
#define HASHTABLECUCKOO_H
#include "HashTableChaining.h"

class HashTableCuckoo: public HashTable
{
    public:
        //constructors
        HashTableCuckoo(int size);
        //destructors
        ~HashTableCuckoo();
        //accessors
        void insert(int key, int value)override;
        // void insert2(int index, int key, int value);
        int search(int key)override;
        void remove(int key)override;
        void print()override;
        bool rehash();
        HashEntry*getEntry();
        HashEntry*getEntry2();
    private:
        HashEntry * entry_; // Table I
        HashEntry * entry2_; // Table II
        int size_;
        // helper funcs to calc the hash funcs 
        int hash1(int key);
        int hash2(int key);
};

#endif