// Assignment 4 -> modified for assignment 6
// CS 341

// Honor Pledge: 
//
// I pledge that I have neither given nor
// receieved help on this assignment. 
//
// kmlee1

// LinkedNode.h
#ifndef LINKEDNODE_H
#define LINKEDNODE_H

#include "node.h"

class LinkedNode: public Node
{
    public:
        LinkedNode() {}
        LinkedNode(HashEntry entry);
        LinkedNode(HashEntry entry, LinkedNode*nextLinkedNode, LinkedNode*prevLinkedNode);
        virtual ~LinkedNode();
        LinkedNode*getNextLinkedNode();
        void setNextLinkedNode(LinkedNode*nextLinkedNode);
        LinkedNode*getPrevLinkedNode();
        void setPrevLinkedNode(LinkedNode*prevLinkedNode);
        bool hasNextLinkedNode();
        bool hasPrevLinkedNode();
    private:
        LinkedNode*nextLinkedNode_;
        LinkedNode*prevLinkedNode_;
};

#endif