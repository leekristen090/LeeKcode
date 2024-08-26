// Assignment 6
// CS 341

// Honor Pledge: 
//
// I pledge that I have neither given nor
// receieved help on this assignment. 
//
// kmlee1

// HashTableArray.cpp

#include "HashTableArray.h"

HashTableArray::HashTableArray(int size, ProbingType probingType):tableSize_(size), probingType_(probingType) 
{
    table=new HashEntry[tableSize_];
    for(int i=0; i<tableSize_; i++)
    { 
        // table[i].key = -1; 
        table[i].setStatus(EMPTY);
    }
}

HashTableArray::~HashTableArray()
{ delete[] table; }

void HashTableArray::insert(int key, int value)
{
    int index = key % tableSize_;
    int i = 0;
    // std::cout<<"inserting key = "<<key<<" and value = "<<value<<std::endl;

    while(i<tableSize_)
    {
        int currentIndex;
        if(probingType_==LINEAR_PROBING)
        { currentIndex=(index+i) % tableSize_; }
        else if(probingType_==QUADRATIC_PROBING)
        { currentIndex=(index+(i*i))%tableSize_; }
        // std::cout<<"Current index: "<<currentIndex<<std::endl;

        if(table[currentIndex].getStatus()==EMPTY || table[currentIndex].getStatus()==REMOVED)
        {
            table[currentIndex].setKey(key);
            table[currentIndex].setValue(value);
            table[currentIndex].setStatus(OCCUPITED);
            return;
        }
        i++;
    }
}

int HashTableArray::search(int key)
{
    int index = key %tableSize_;
    int step = 1;
    int i = 0;
    while(i<tableSize_)
    {
        int currentIndex;
        if(probingType_==LINEAR_PROBING)
        { currentIndex=(index+i) % tableSize_; }
        else if(probingType_==QUADRATIC_PROBING)
        { 
            currentIndex=(index+step*step) % tableSize_;
            step++;
        }

        if(table[currentIndex].getStatus()!=EMPTY && table[currentIndex].getKey()==key)
        { return table[currentIndex].getValue(); }
        i++;
    }
    return -1; // key not found 
}

void HashTableArray::remove(int key)
{
    int index = key %tableSize_;
    int step = 1;
    int i = 0;
    while(i<tableSize_)
    {
        int currentIndex;
        if(probingType_==LINEAR_PROBING)
        { currentIndex=(index+i) % tableSize_; }
        else if(probingType_==QUADRATIC_PROBING)
        { 
            currentIndex=(index+step*step) % tableSize_;
            step++;
        }

        if(table[currentIndex].getStatus()!=EMPTY && table[currentIndex].getKey()==key)
        {
            table[currentIndex].setStatus(REMOVED);
            return;
        }
        i++;
    }
}

void HashTableArray::print()
{
    //std::cout<<"enter printing func"<<std::endl;
    std::cout<<"***********"<<std::endl;
    for(int i=0; i<tableSize_; i++)
    {
        if(table[i].getStatus()== OCCUPITED)
        { 
            std::cout<<"["<<i<<"]: "<<table[i].getKey()<<std::endl;
            //std::cout<<"Index "<<i<<": Key = "<<table[i].key<<", Value = "<<table[i].value<<std::endl; 
        }
        else 
        { std::cout<<"["<<i<<"]: "<<std::endl; }
    }
    std::cout<<"***********"<<std::endl;
    //std::cout<<"exit printing func"<<std::endl;
}