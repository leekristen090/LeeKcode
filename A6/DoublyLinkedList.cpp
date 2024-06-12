// Assignment 4 -> modified for assignment 6
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
        std::cout<< current->getData().getKey();
        if(current->getNextLinkedNode()!=nullptr)
        { std::cout<<" <--> "; }
        else 
        { std::cout<<" (End of list)";}//<< std::endl; }
        current=current->getNextLinkedNode();
    }
    //std::cout<<"exit while loop"<<std::endl; 
    std::cout<<std::endl;
}

void DoublyLinkedList::insertLinkedNode(LinkedNode*node, HashEntry entry)
{
    LinkedNode*newNode= new LinkedNode(entry);
    if(tail_==nullptr)
    {
        std::cout<<"tail==nullptr"<<std::endl;
        head_=newNode;
        tail_=newNode;
        head_->setNextLinkedNode(nullptr);
    }
    else
    {
        std::cout<<"tail!=nullptr"<<std::endl;
        tail_->setNextLinkedNode(newNode);
        newNode->setPrevLinkedNode(tail_);
        tail_=newNode;
    }
    /* i commented out what is below because when i called this function in insertBefore and insertAfter it was counting the length wrong*/
    //length_++;
    std::cout<< "Head: "<< head_->getData().getKey()<< " Tail: "<< tail_->getData().getKey()<< std::endl;// for debugging
    //std::cout<<"Length: "<<getLength()<<std::endl;
}

void DoublyLinkedList::insertAfterLinkedNode(LinkedNode*node, HashEntry entry)
{
    //std::cout<< "\nInserting "<< data<< " after " <<node->getData() <<std::endl; //debugging
    LinkedNode*newNode= new LinkedNode(entry);
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

void DoublyLinkedList::insertBeforeLinkedNode(LinkedNode*node, HashEntry entry)
{
    //std::cout<<"\nInserting "<< data<< " before "<< node->getData()<<std::endl; //debugging
    LinkedNode*newNode=new LinkedNode(entry);
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

void DoublyLinkedList::removeLinkedNode(int key)
{
    // std::cout<<"entering remove linked node in DLL.cpp"<<std::endl; //debugging
    // case 1 
    if(key==head_->getData().getKey())
    {
        // std::cout<<"key==head_->getData().getKey()"<<std::endl;//debugging
        // either head of the list
        if(head_->getNextLinkedNode()!=nullptr)
        {
            // std::cout<<"head_->getNextLinkedNode()!=nullptr"<<std::endl;//debugging
            LinkedNode*currentHead=head_;
            LinkedNode*nextNode=head_->getNextLinkedNode();
            head_=nextNode; // setting the new head to be the next node
            nextNode->setPrevLinkedNode(nullptr); // severe the link backwards
            currentHead->setNextLinkedNode(nullptr); // severe the link forwards
            delete currentHead; // delete the old head
        }
        else
        {
            // std::cout<<"else of if(head_->getNextLinkedNode()!=nullptr)"<<std::endl; //debugging
            LinkedNode*currentHead=head_;
            head_=nullptr;
            tail_=nullptr;
            delete currentHead;
        }
        // or it is the head and the tail at the same time 
    }
    else if(key==tail_->getData().getKey())
    {
        // std::cout<<"else if(key==tail_->getData().getKey())"<<std::endl;//debugging
        //case 2
        if(tail_->getPrevLinkedNode()!=nullptr)
        {
            // std::cout<<"tail_->hasPrevLinkedNode()"<<std::endl;//debugging
            LinkedNode* currentTail=tail_;
            // std::cout<<"Tail "<<tail_<<std::endl;//debugging
            LinkedNode* previousNode=tail_->getPrevLinkedNode();
            tail_=previousNode;
            previousNode->setNextLinkedNode(nullptr);
            currentTail->setPrevLinkedNode(nullptr);
            delete currentTail;
        }
    }
    else
    {
        // std::cout<<"else of if(tail_->hasPrevLinkedNode())"<<std::endl; //debugging
        // case 3
        LinkedNode*currentNode=head_;
        bool isFound=false;
        while(currentNode->getNextLinkedNode()!=nullptr&&!isFound) // either we reach the end of the list or find what we are looking for 
        { 
            // std::cout<<"while(currentNode->hasNextLinkedNode()&&!isFound)"<<std::endl; //debugging
            currentNode=currentNode->getNextLinkedNode();
            if(currentNode->getData().getKey()==key)
            {
                isFound=true;
            }
        } // when this while loop stops we know that we will have the currentNode that we are looking for 
        if(isFound)
        {
            // std::cout<<"if(isFound)"<<std::endl; //debugging
            // if it is found then we want to delete 
            currentNode->getPrevLinkedNode()->setNextLinkedNode(currentNode->getNextLinkedNode()); // setting the previous node's next node to the current node's next node
            currentNode->getNextLinkedNode()->setPrevLinkedNode(currentNode->getPrevLinkedNode());
            currentNode->setNextLinkedNode(nullptr);
            currentNode->setPrevLinkedNode(nullptr);
            delete currentNode;
        }
        else
        {
            std::cout<<"Key not found"<<std::endl;
        }
    } 
    // std::cout<<"exit remove linked Node in DLL.cpp"<<std::endl; //debugging
}

int DoublyLinkedList::find(int key)
{
    LinkedNode*current=head_;
    while(current!=nullptr)
    {
        if(current->getData().getKey()==key)
        { return current->getData().getValue(); }
        current=current->getNextLinkedNode();
    }
    return -1;
}