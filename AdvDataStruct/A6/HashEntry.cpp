// Assignment 6
// CS 341

// Honor Pledge: 
//
// I pledge that I have neither given nor
// receieved help on this assignment. 
//
// kmlee1

// HashEntry.cpp

#include "HashEntry.h"

HashEntry::HashEntry(): key_(-1),value_(0), status_(EMPTY) {}
HashEntry::HashEntry(int key, int value): key_(key), value_(value), status_(OCCUPITED) {}
HashEntry::~HashEntry() {}

int HashEntry::getKey() 
{ return key_; }

int HashEntry::getValue()
{ return value_; }

Status HashEntry::getStatus()
{ return status_; }

void HashEntry::setKey(int key)
{ key_=key; }

void HashEntry::setValue(int value)
{ value_=value; }

void HashEntry::setStatus(Status status)
{ status_=status; }