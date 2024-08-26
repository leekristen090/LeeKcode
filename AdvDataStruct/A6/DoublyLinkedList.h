// Assignment 4 -> modified for assignment 6
// CS 341

// Honor Pledge: 
//
// I pledge that I have neither given nor
// receieved help on this assignment. 
//
// kmlee1
//
// DoublyLinkedList.h

/*
need to modify all the files from assign 4 to store a HashEntry rather than just int data
*/
#ifndef DOUBLYLINKEDLIST_H
#define DOUBLYLINKEDLIST_H
#include "LinkedList.h"
//#include "HashEntry.h"

class DoublyLinkedList: public LinkedList
{
    public:
        DoublyLinkedList();
        virtual ~DoublyLinkedList();
        int getLength()const;
        LinkedNode* getHead() const;
        LinkedNode* getTail() const;
        virtual void printList() const;
        void insertLinkedNode(LinkedNode * node, HashEntry entry) ;
        void insertAfterLinkedNode(LinkedNode * node, HashEntry entry) ;
        void insertBeforeLinkedNode(LinkedNode * node, HashEntry entry);

        int find(int key);
        void removeLinkedNode(int key);
};
#endif