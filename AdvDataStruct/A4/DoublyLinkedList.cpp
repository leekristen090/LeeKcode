// Assignment 4
// CS 341

// Honor Pledge: 
//
// I pledge that I have neither given nor
// receieved help on this assignment. 
//
// kmlee1
//
// DoublyLinkedList.cpp
#include "DoublyLinkedList.h"

DoublyLinkedList::DoublyLinkedList(): LinkedList() {}
DoublyLinkedList::~DoublyLinkedList() 
{ }

LinkedNode*DoublyLinkedList::getHead() const
{ return head_; }

LinkedNode*DoublyLinkedList::getTail() const
{ return tail_; }

int DoublyLinkedList::getLength() const
{ return length_; }

void DoublyLinkedList::printList() const
{
    //std::cout<<"\nhead: "<< head_->getData()<<std::endl;
    //std::cout<<"tail: "<< tail_->getData()<<std::endl;
    //std::cout<< "Is there a node after head?: " << head_->hasNextLinkedNode()<< std::endl;
    LinkedNode*current=head_;
    //std::cout<<"\nForward print: "<<std::endl; 
    while(current!=nullptr)
    {
        std::cout<< current->getData();
        if(current->getNextLinkedNode()!=nullptr)
        { std::cout<<" <--> "; }
        else 
        { std::cout<<" (End of list)"<< std::endl; }
        current=current->getNextLinkedNode();
    }
    //std::cout<<"exit while loop"<<std::endl; 
    std::cout<<std::endl;
}

void DoublyLinkedList::insertLinkedNode(LinkedNode*node, int data)
{
    LinkedNode*newNode= new LinkedNode(data);
    if(tail_==nullptr)
    {
        head_=newNode;
        tail_=newNode;
    }
    else
    {
        tail_->setNextLinkedNode(newNode);
        newNode->setPrevLinkedNode(tail_);
        tail_=newNode;
    }
    /* i commented out what is below because when i called this function in insertBefore and insertAfter it was counting the length wrong*/
    //length_++;
    //std::cout<< "Head: "<< head_->getData()<< " Tail: "<< tail_->getData()<< std::endl;// for debugging
    //std::cout<<"Length: "<<getLength()<<std::endl;
}

void DoublyLinkedList::insertAfterLinkedNode(LinkedNode*node, int data)
{
    //std::cout<< "\nInserting "<< data<< " after " <<node->getData() <<std::endl; //debugging
    LinkedNode*newNode= new LinkedNode(data);
    if(node->getNextLinkedNode()!=nullptr)
    {
        newNode->setNextLinkedNode(node->getNextLinkedNode());
        node->getNextLinkedNode()->setPrevLinkedNode(newNode);
        newNode->setPrevLinkedNode(node);
        node->setNextLinkedNode(newNode);
    }
    else
    {
        //insert(data);
        //insertLinkedNode(tail_,data);
        node->setNextLinkedNode(newNode);
        newNode->setPrevLinkedNode(node);
        tail_=newNode;
    }
    //newNode->setPrevLinkedNode(node);
    //node->setNextLinkedNode(newNode);
    length_++;
    //std::cout<<"Length: "<<getLength()<<std::endl;
}

void DoublyLinkedList::insertBeforeLinkedNode(LinkedNode*node, int data)
{
    //std::cout<<"\nInserting "<< data<< " before "<< node->getData()<<std::endl; //debugging
    LinkedNode*newNode=new LinkedNode(data);
    if(node->getPrevLinkedNode()!=nullptr)
    {
        //std::cout<<"node->getPrevLinkedNode()!=nullptr"<<std::endl;
        
        node->getPrevLinkedNode()->setNextLinkedNode(newNode);
        newNode->setPrevLinkedNode(node->getPrevLinkedNode());
        node->setPrevLinkedNode(newNode);
        newNode->setNextLinkedNode(node);
    }
    else
    {
        // i believe this is when we are inserting before the head
        //std::cout<<"Inserting before the head"<<std::endl;
        newNode->setNextLinkedNode(node);
        node->setPrevLinkedNode(newNode);
        head_=newNode;
    }
    length_++;
    //std::cout<<"Length: "<<getLength()<<std::endl;
}