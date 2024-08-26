// Assignment 4 -> modified for assignment 6
// CS 341

// Honor Pledge: 
//
// I pledge that I have neither given nor
// receieved help on this assignment. 
//
// kmlee1

// node.h

#ifndef NODE_H
#define NODE_H

#include <iostream>
#include "HashEntry.h"

class Node
{
    public:
        Node() {}
        Node(HashEntry entry);
        ~Node() {}
        void setData(HashEntry entry);
        HashEntry getData();
    private:
        //int data_;
        HashEntry entry_;
};

#endif