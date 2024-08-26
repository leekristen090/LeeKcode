// Assignment 4
// CS 341

// Honor Pledge: 
//
// I pledge that I have neither given nor
// receieved help on this assignment. 
//
// kmlee1

// node.cpp

#include "node.h"

Node::Node(int data)
{ data_ = data; }

void Node::setData(int data)
{ data_ = data; }

int Node::getData()
{ return data_; }