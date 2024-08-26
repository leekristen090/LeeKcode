// Assignment 6
// CS 341

// Honor Pledge: 
//
// I pledge that I have neither given nor
// receieved help on this assignment. 
//
// kmlee1

// HashTableChaining.cpp

#include "HashTableChaining.h"

HashTableChaining::HashTableChaining():size_(0), entry_(nullptr) {}
HashTableChaining::HashTableChaining(int size):size_(size)
{ entry_ = new DoublyLinkedList[size]; }

HashTableChaining::~HashTableChaining()
{ delete[]entry_; }

void HashTableChaining::insert(int key, int value)
{
    int index=key%size_;
    HashEntry entry(key, value);
    entry_[index].insertLinkedNode(nullptr, entry);
}

int HashTableChaining::search(int key)
{
    int index=key%size_;
    
    return entry_[index].find(key);
}

void HashTableChaining::remove(int key)
{
    int index=key%size_;
    std::cout<<"Index: "<<index<<std::endl;
    entry_[index].removeLinkedNode(key);
}

void HashTableChaining::print()
{
    std::cout<<"***********"<<std::endl;
    for(int i=0; i<size_; i++)
    {
        std::cout<<"["<<i<<"]: ";
        entry_[i].printList();
    }
    std::cout<<"***********"<<std::endl;
}