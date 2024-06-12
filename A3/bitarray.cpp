// Assignment 3
// CS 341

// Honor Pledge: 
//
// I pledge that I have neither given nor
// receieved help on this assignment. 
//
// kmlee1

#include "bitarray.h"

BitArray::BitArray(int size): LENGTH(size), BYTES((size+BIT_IN_BYTE-1)/BIT_IN_BYTE), data_(new char[BYTES])
{ 
	// initializing the bitarray - PHASE 1
	/*
	for(int i=0; i<BYTES; i++)
	{ data_[i]=0; }
	*/
	clear(); // PHASE 2
}

//this is the copy constructor
BitArray::BitArray(const BitArray & array): LENGTH(array.LENGTH)//, BYTES(array.BYTES), data_(new char[BYTES])
{ 
	for(int i=0; i<BYTES;i++)
	{ data_[i]=array.data_[i]; }
}

BitArray::~BitArray() 
{ delete[] data_; }

// clear the bit array
void BitArray::clear() 
{
	if(data_!=nullptr)
	{
		delete[]data_;
		data_=nullptr;
	}
	BYTES=0;
	LENGTH=0;
	//for(int i=0; i<BYTES; i++)
	//{ data_[i]=0; }
}
// initialize the bitarray with char
void BitArray::initialize(char * word, int size)// want to have a new instance of char array
{
	clear();
	data_ = new char[size];
 	BYTES = size;
 	LENGTH = size;

 	for (int i=0; i < size; i++)
 	{
  	data_[i] = word[i];
 	}
	/*
	for(int i=0; i<size; i++)
	{
		for(int j=0; j<BIT_IN_BYTE; j++)
		{
			int bit= (word[i]>>(BIT_IN_BYTE-1-j)) & 1;
			set(i*BIT_IN_BYTE+j, bit);
		}
	}*/
	
}

// void print() -> printing and such DO NOT TOUCH 
void BitArray::print()
{	
	std::cout << "|";
	
	for (int i=0; i < BYTES; i++)
	{
		std::bitset<BIT_IN_BYTE> bits = data_[i];
		std::cout << bits << "|";
	}
	
	std::cout << std::endl;	
}

// getting value in specified position
bool BitArray::get(int position)const
{ 
	//return false; // PHASE 1

	// geting val of bit at a specific position - Returns TRUE if the bit at the specified position is set (1), FALSE otherwise
	int byteIndex=position/BIT_IN_BYTE;
	int bitIndex=position%BIT_IN_BYTE;
	return(data_[byteIndex]&(1<<(BIT_IN_BYTE-1-bitIndex)))!=0;
}

bool BitArray::set(int position, int bit)
{
	// settting bit at specific position
	if(position>=0 && position<LENGTH)

	{
		int byteIndex=position/BIT_IN_BYTE;
		int bitIndex=position%BIT_IN_BYTE;
		if(bit==1) //seting bit to 1
		{ data_[byteIndex]|= (1<< (BIT_IN_BYTE-1-bitIndex)); }
		else if(bit==0) //setting bit to 0
		{ data_[byteIndex]&= ~(1<< (BIT_IN_BYTE-1-bitIndex)); }
		else { return false; }
		return true;
	}
	else 
	{ return false; }
}

// fliping bits at a specified postion 
bool BitArray::flip(int position)
{
	// flipping bits and such
	int byteIndex=position/BIT_IN_BYTE;
	int bitIndex=position%BIT_IN_BYTE;
	data_[byteIndex]^= (1<< (BIT_IN_BYTE-1-bitIndex));
	return true;
}

void BitArray::complement()
{
	// giving the complement(opp) of the bit array
	for(int i=0; i<BYTES;i++)
	{ data_[i]= ~data_[i]; }
}

char BitArray::get8(int position) const
{
	return data_[position];
}

void BitArray::set8(char c, int index)
{
	if(index>=0&&index< BYTES)
	{ data_[index]=c; }
	else 
	{ std::cout<<"error"<<std::endl; }
	
	/*
	if(index>=0&&index<BYTES)
	{ 
		int byteIndex=index/BIT_IN_BYTE;
		int bitOffset=index%BIT_IN_BYTE;
		for(int i=0; i<BIT_IN_BYTE; i++)
		{
			set(index*BIT_IN_BYTE+i, (c&(1<< (BIT_IN_BYTE-1-i)))!=0);
		}
	}
	else
	{ std::cerr<<"Error"<< std::endl; }*/
}