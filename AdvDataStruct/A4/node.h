// Assignment 4
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

class Node
{
    public:
        Node() {}
        Node(int data);
        ~Node() {}
        void setData(int data);
        int getData();
    private:
        int data_;
};

#endif