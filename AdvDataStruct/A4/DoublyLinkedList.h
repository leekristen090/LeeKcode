// Assignment 4
// CS 341

// Honor Pledge: 
//
// I pledge that I have neither given nor
// receieved help on this assignment. 
//
// kmlee1
//
// DoublyLinkedList.h
#ifndef DOUBLYLINKEDLIST_H
#define DOUBLYLINKEDLIST_H
#include "LinkedList.h"

class DoublyLinkedList: public LinkedList
{
    public:
        DoublyLinkedList();
        virtual ~DoublyLinkedList();
        int getLength()const;
        LinkedNode* getHead() const;
        LinkedNode* getTail() const;
        virtual void printList() const;
        void insertLinkedNode(LinkedNode * node, int data) ;
        void insertAfterLinkedNode(LinkedNode * node, int data) ;
        void insertBeforeLinkedNode(LinkedNode * node, int data);
};
#endif