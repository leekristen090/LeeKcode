// Assignment 3
// CS 341

// Honor Pledge: 
//
// I pledge that I have neither given nor
// receieved help on this assignment. 
//
// kmlee1

#include "set.h"

// constructor 
Set::Set(int size): data_(size) {}
Set::~Set() {} //deconstructor

// initialize bit array with char word
void Set::initialize(char * word, int size)
{
    data_.clear();
    data_.initialize(word, size);
}

// calculating cardinality - represented by the number of 1's in the underlying BitArray
int Set::getCardinality() const
{
    int cardinality=0;
    for(int i=0; i< data_.length()*8; i++)
    {
        if(data_.get(i))
        { cardinality++; }
    }
    return cardinality;
}

BitArray & Set::getData()
{ return data_; }

// union of A and B
bool Set::setUnion(Set & B)
{
    for(int i=0; i<data_.length(); i++)
    {
        int bitA = data_.get(i);
        int bitB = B.getData().get(i);
        int unionBit = bitA|bitB; 
        data_.set(i, unionBit);
    }
    return true;
}

// intersection of A and B
bool Set::setIntersection(Set & B)
{
    for(int i=0; i<data_.length(); i++)
    {
        int bitA = data_.get(i);
        int bitB = B.getData().get(i);
        int intersectBit = bitA&bitB; 
        data_.set(i, intersectBit);
    }
    return true;
}