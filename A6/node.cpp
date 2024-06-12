// Assignment 4 -> modified for assignment 6
// CS 341

// Honor Pledge: 
//
// I pledge that I have neither given nor
// receieved help on this assignment. 
//
// kmlee1

// node.cpp

#include "node.h"

Node::Node(HashEntry entry)
{ entry_ = entry; }

void Node::setData(HashEntry entry)
{ entry_ = entry; }

HashEntry Node::getData()
{ return entry_; }