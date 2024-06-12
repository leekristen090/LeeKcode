// Assignment 4 -> modified for assignment 6
// CS 341

// Honor Pledge: 
//
// I pledge that I have neither given nor
// receieved help on this assignment. 
//
// kmlee1

// LinkedList.cpp
#include "LinkedList.h"

LinkedList::LinkedList(): head_(nullptr), tail_(nullptr), length_(0) {}

LinkedList::~LinkedList() 
{
    if(head_!=nullptr)
    { 
        delete head_; 
    }
}

bool LinkedList::isEmpty()
{ return head_==nullptr; }

int LinkedList::getLength()
{ return length_; }

void LinkedList::insert(HashEntry entry)
{  
    //std::cout<< "Inserting "<< element<< "..." <<std::endl;
    LinkedNode*newNode= new LinkedNode(entry);
    if(isEmpty())
    { 
        //std::cout<< "List was empty and now we are inserting."<<std::endl; // for debugging 
        head_=newNode;
        tail_=newNode;
    }
    else
    {
        //std::cout<< "List is not empty so we are updating the tail."<<std::endl; // for debugging
        tail_->setNextLinkedNode(newNode);
        newNode->setPrevLinkedNode(tail_);
        tail_=newNode;
    }
    length_++;
    //std::cout<< "Head: "<< head_->getData()<< " Tail: "<< tail_->getData()<< std::endl;// for debugging
    //std::cout<<"Length: "<<getLength()<<std::endl;
}

void LinkedList::printList()
{ 
    std::cout<< "\nPrinting list: "<<std::endl;
    LinkedNode* current=head_;

    while(current!=nullptr)
    {
        std::cout<< current->getData().getKey();
        if(current->hasNextLinkedNode()==false)
        { std::cout<<" --> "; }
        else 
        { std::cout<< " (End of list)"<< std::endl; }
        current=current->getNextLinkedNode();
    }
}