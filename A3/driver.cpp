// Assignment 3
// CS 341

// Honor Pledge: 
//
// I pledge that I have neither given nor
// receieved help on this assignment. 
//
// kmlee1

// Driver
//---------------------------------------------------------------------------------------------------------
//---------------------------------------------------------------------------------------------------------
#include "bitarray.h"
#include "set.h"
#include "dictionary.h"
//---------------------------------------------------------------------------------------------------------
//---------------------------------------------------------------------------------------------------------

// PHASE 1
void phaseOne()
{
    //creating an array of bits with 32 bits
    BitArray myBit(32);
    //setting the bits
    
    //displaying the bitarray
    std::cout<<"Number of bits: "<< myBit.length()<<std::endl;
    myBit.print();
}

//---------------------------------------------------------------------------------------------------------
//---------------------------------------------------------------------------------------------------------

// PHASE 2
void phaseTwo()
{
    BitArray myBit(32); //initialize bitarray
    // number of bits
    //std::cout<<"Number of bits: "<< myBit.length()<<std::endl;

    // initialize bitarray with char word
    char word[]="test";
    myBit.initialize(word, (sizeof(word)-1));
    std::cout<<"\nInitalized bit array with char: "<<std::endl;
    myBit.print();
    //std::cout<<"Number of bits: "<< myBit.length()<<std::endl;

    std::cout<<"\nUsing get8(): "<<myBit.get8(1)<<std::endl;

    // using get() function to get value of bit at specific location - false if 0, true if 1
    std::cout<< "\nOth bit set: "<< (myBit.get(0)? "true": "false")<<std::endl;
    std::cout<< "1Oth bit set: "<< (myBit.get(10)? "true": "false")<<std::endl;

    // using set() method
    std::cout<<"\nSetting 0th position to 1: "<<std::endl;
    myBit.set(0,1);
    myBit.print();
    std::cout<<"\nSetting 10th position to 0: "<<std::endl;
    myBit.set(10,0);
    myBit.print();

    // using flip() method
    std::cout<<"\nFlipping 10th position: "<<std::endl;
    myBit.flip(10);
    myBit.print();
    
    // complement function
    std::cout<< "\nComplement: " << std::endl;
    myBit.complement();
    myBit.print();

    // clearing the bit array
    std::cout<< "\nClearing the bit array: " << std::endl;
    myBit.clear(); 
    myBit.print();
}

//---------------------------------------------------------------------------------------------------------
//---------------------------------------------------------------------------------------------------------

// PHASE 3
void phaseThree()
{
    
    // creating the sets
    Set A(32);
    Set B(32);

    // initializing the sets
    char wordA[]="test";
    char wordB[]="ryan";
    A.initialize(wordA,sizeof(wordA)-1);
    B.initialize(wordB,sizeof(wordB)-1);
    // printing the initialized sets
    std::cout<<"Set A: "<<std::endl;
    A.getData().print();
    std::cout<<"Set B: "<<std::endl;
    B.getData().print();

    // cardinality and such 
    std::cout<<"\nCardinality of A: "<<A.getCardinality()<< std::endl;

    // doing union and intersection
    //
    // Union
    A.setUnion(B);
    std::cout<<"\nUnion (A U B): "<<std::endl;
    A.getData().print();
    // Intersection
    //A.setIntersection(B);
    //std::cout<<"\nIntersection (A X B): "<<std::endl;
    //A.getData().print();
    
    // Using set8() method
    Set C(32);
    char wordC[]="pest";
    C.initialize(wordC,(sizeof(wordC)-1));
    std::cout<<"\nSet C: "<<std::endl;
    C.getData().print();
    std::cout<<"using set8() to put 't' in pos 0: "<<std::endl;
    C.getData().set8('t',0);
    C.getData().print();
}

//---------------------------------------------------------------------------------------------------------
//---------------------------------------------------------------------------------------------------------

// PHASE 4
void phaseFour()
{
    Dictionary diction;
    BitArray myBit(32);
    char word[]="test";
    myBit.initialize(word, (sizeof(word)-1));
    std::cout<<"Bitarray: "<<std::endl;
    myBit.print();
    diction.initialize(word,(sizeof(word)-1));
    

    // Using rank() and rank_range()
    std::cout<< "rank: "<<diction.rank(32)<<std::endl;
    std::cout<< "rank range (15, 29): position "<<diction.rank_range(15,29)<<std::endl;

    std::cout<< "Select 4th bit=1: position "<<diction.select(4)<<std::endl;
    std::cout<< "Select range (0,15,4,1): position "<<diction.select_range(0,15, 4)<<std::endl;
}

//---------------------------------------------------------------------------------------------------------
//---------------------------------------------------------------------------------------------------------

// PHASE 5
void phaseFive()
{
    Dictionary diction;
    char word[]="test";
    diction.initialize(word, (sizeof(word))-1);

    std::cout<< "rank: "<< diction.rank(32)<< std::endl;
    std::cout<< "Select (5,1): position "<<diction.select(5)<< std::endl;
    std::cout<<"\nPrinting: "<<std::endl;
    diction.printLookupTable(std::cout);
}

//---------------------------------------------------------------------------------------------------------
//---------------------------------------------------------------------------------------------------------

// Main
int main()
{
    std::cout<< "\n** Welcome to Assignment 3 **\n" << std::endl;

    //phaseOne();
    //phaseTwo();
    //phaseThree();
    //phaseFour();
    phaseFive();

    return 0;
}