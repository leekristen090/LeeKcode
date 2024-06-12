// Assignment 6
// CS 341

// Honor Pledge: 
//
// I pledge that I have neither given nor
// receieved help on this assignment. 
//
// kmlee1

// HashTableCuckoo.cpp
#include "HashTableCuckoo.h"

HashTableCuckoo::HashTableCuckoo(int size): size_(size) 
{
    entry_=new HashEntry[size]();
    entry2_=new HashEntry[size]();
}

HashTableCuckoo::~HashTableCuckoo()
{
    delete[]entry_;
    delete[]entry2_;
}

int HashTableCuckoo::hash1(int key)
{ return key%size_; }

int HashTableCuckoo::hash2(int key)
{ return (key/size_)%size_; }

HashEntry* HashTableCuckoo::getEntry()
{ return entry_; }

HashEntry* HashTableCuckoo::getEntry2()
{ return entry2_; }

void HashTableCuckoo::insert(int key, int value) 
{
    int index=hash1(key);
    int alternativeIndex=-1;

    // Check if slot is occupied
    if(entry_[index].getStatus()==OCCUPITED) 
    {
        // If occupied, move the existing entry to the alternative table
        alternativeIndex=hash2(entry_[index].getKey());
        if(entry2_[alternativeIndex].getStatus()==OCCUPITED) 
        {
            std::cout<<"Cycle detected. Rehashing required.\n";
            if(!rehash()) 
            {
                std::cout<<"Rehashing failed due to cycle.\n";
                return;
            }
            // After rehashing, insert the new key-value pair again
            insert(key, value);
            return;
        }
        entry2_[alternativeIndex]=entry_[index];
        entry_[index].setStatus(EMPTY);
    }

    // Insert new entry into the primary table
    entry_[index]=HashEntry(key, value);
    entry_[index].setStatus(OCCUPITED);
}

// Rehashing 
bool HashTableCuckoo::rehash() 
{
    // Double the size of the hash tables
    int newSize=size_*2;
    HashEntry*newTable=new HashEntry[newSize];
    HashEntry*newTable2 = new HashEntry[newSize];

    // Reinsert all entries into the new tables
    for (int i=0; i<size_; i++) {
        if(entry_[i].getStatus()==OCCUPITED) 
        {
            int newIndex=hash1(entry_[i].getKey())%newSize;
            int newIndex2=hash2(entry_[i].getKey())%newSize;
            if(newTable[newIndex].getStatus()==OCCUPITED) 
            {
                delete[]newTable;
                delete[]newTable2;
                return false; // Indicate failure
            }
            newTable[newIndex]=entry_[i];
            newTable[newIndex].setStatus(OCCUPITED);
            if(newTable2[newIndex2].getStatus()==OCCUPITED) 
            {
                delete[]newTable;
                delete[]newTable2;
                return false; // Indicate failure
            }
            newTable2[newIndex2]=entry2_[i];
            newTable2[newIndex2].setStatus(OCCUPITED);
        }
    }

    // Delete old tables and update pointers
    delete[]entry_;
    delete[]entry2_;
    entry_=newTable;
    entry2_=newTable2;
    size_=newSize;

    std::cout<<"Rehashing successful. New table size: "<<newSize <<"\n";
    return true; // Indicate success
}

int HashTableCuckoo::search(int key)
{
    int index1=hash1(key);
    int index2=hash2(key);

    if(entry_[index1].getKey()==key) 
    { return entry_[index1].getValue(); } 
    else if(entry2_[index2].getKey()==key) 
    { return entry2_[index2].getValue(); }

    return -1; // Key not found
}

void HashTableCuckoo::remove(int key) 
{
    int index1=hash1(key);
    int index2=hash2(key);

    if(entry_[index1].getKey()==key)//&& entry_[index1].getStatus()!=EMPTY) 
    { 
        entry_[index1].setKey(-1);
        entry_[index1].setValue(-1);
        entry_[index1].setStatus(REMOVED); 
        return;
    } 
    else if(entry2_[index2].getKey()==key)//&& entry2_[index2].getStatus()!=EMPTY) 
    { 
        entry2_[index2].setKey(-1);
        entry2_[index2].setValue(-1);
        entry2_[index2].setStatus(REMOVED); 
        return;
    }
}

void HashTableCuckoo::print() 
{
    std::cout << "Table I:" << std::endl;
    std::cout<<"***********"<<std::endl;
    for(int i=0; i<size_; i++) {
        if(entry_[i].getKey()!=-1) 
        { std::cout<<"["<<i<< "]: "<< entry_[i].getKey()<<std::endl; } 
        else 
        { std::cout<< "["<<i <<"]: "<<std::endl; }
    }
    std::cout<<"***********"<<std::endl;

    std::cout<<"Table II:"<<std::endl;
    std::cout<<"***********"<<std::endl;
    for(int i=0; i<size_; i++) 
    {
        if(entry2_[i].getKey()!=-1) 
        { std::cout<<"["<<i<<"]: "<<entry2_[i].getKey()<<std::endl; } 
        else 
        { std::cout<<"["<<i<<"]: "<<std::endl; }
    }
    std::cout<<"***********"<<std::endl;
}