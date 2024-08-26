// Assignment 3
// CS 341

// Honor Pledge: 
//
// I pledge that I have neither given nor
// receieved help on this assignment. 
//
// kmlee1

// instead of having to calculate the rank of a letter, then i can look at the table and see the number of ones in the dictionary

#include "dictionary.h"

// constructor
Dictionary::Dictionary(): lookupTable_(new int [DICTIONARY_SIZE])
{
    //for(int i=0; i<DICTIONARY_SIZE;i++)
    //{ lookupTable_[i]=0; }
    intializeLookup();
}
// copy cunstructor
Dictionary::Dictionary (const Dictionary & dict):lookupTable_(new int [DICTIONARY_SIZE])
{
    data_=dict.data_;
    for(int i=0; i<DICTIONARY_SIZE;i++)
    { lookupTable_[i]=dict.lookupTable_[i]; }
}

Dictionary::~Dictionary()
{ delete[] lookupTable_; }

void Dictionary::initialize(char*word, int size)
{ 
    data_.initialize(word, size); 
    //buildLook
    //intializeLookup();
}

// Returns how many of the bit b are in the interval [0,end).
int Dictionary::rank(int end, int bit)
{   
    /*int count=0;
    for(int i=0; i< data_.length()*8; i++)
    {
        if(data_.get(i))
        { count++; }
    }
    return count;*/
    return rank_range(0, end); 
}

//Returns how many of the bit b are in the interval [start,end).
int Dictionary::rank_range(int start, int end, int b)         
{
    //PHASE 4
	int count = 0; 
	for(int i = start; i<end; i++)
	{
		if(data_.get(i))
		{
			count++;	
		}
	}
	return count;

    //PHASE 5
    /*int count=0;
    for(int i=start; i<end; i++)
    {
        char byte=data_.get8(i);
        count+=lookupTable_[byte];
    }*/
    return count;
}

// Returns the position of the kth bit b in the interval [start,end).
int Dictionary::select(int k, int b)
{
    /*int count=0;
    for(int i=0; i<data_.length()*8; i++)
    {
        if(data_.get(i)) 
        { 
            count++; 
            if(count==k)
            { return i; }
        }
    }
    return count;
    */
    //return select_range(0,end,k);
    return select_range(0,data_.length()*8,k);
}

int Dictionary::select_range(int start, int end, int k, int bit)
{
    int count=0;
    for(int i=start; i<end; i++)
    {
        if(data_.get(i)) 
        { 
            count++; 
            if(count==k)
            { return i; }
        }
    }

    /*for (int i=start; i< end; i++)
    {
        char byte=data_.get8(i);
        count+=lookupTable_[byte];
        if(count>=k) { return i; }
    }*/
    return count;
}

void Dictionary::intializeLookup()
{
    for(int i=0; i< DICTIONARY_SIZE; i++)
    {
        unsigned char byte=static_cast<char>(i);
        int count=0;
        for(int j=0; j<8; j++)
        { count+=(byte&(1<<j))?1:0; }
        lookupTable_[byte]=count; 
    }
}

void Dictionary::printLookupTable(std::ostream & output)
{
    int tableSize=DICTIONARY_SIZE;
    for(int i=0; i<tableSize;i++)
    {
        if(i>=0&&i<tableSize)
        { output<<"Lookup table_["<<i<<"] | " << lookupTable_[i]<<" |"<< std::endl; }
        else 
        { std::cout<<"index out of bounds: "<<i<<std::endl; }
    }
}