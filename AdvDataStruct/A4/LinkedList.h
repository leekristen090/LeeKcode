// Assignment 4
// CS 341

// Honor Pledge: 
//
// I pledge that I have neither given nor
// receieved help on this assignment. 
//
// kmlee1

// LinkedList.h

#ifndef LINKEDLIST_H
#define LINKEDLIST_H
#include "LinkedNode.h"

class LinkedList: public LinkedNode
{
    public:
        LinkedList();// {}
        ~LinkedList();
        bool isEmpty(); // this returns true or false if the list is empty 
        int getLength();
        void insert(int element);
        void printList();
   
    protected:
        LinkedNode*head_; //pointer to head
        LinkedNode*tail_; 
        int length_;
};

#endif