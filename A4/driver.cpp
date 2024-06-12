// Assignment 4
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
#include "DoublyLinkedList.h"
#include <fstream>
//---------------------------------------------------------------------------------------------------------
//---------------------------------------------------------------------------------------------------------

// PHASE 1
void phaseOne()
{
    Node*node1=new Node;
    node1->setData(15);
    std::cout<< "Node 1: "<<node1->getData()<< std::endl;

    Node*node2=new Node(10);
    std::cout<< "Node 2: "<<node2->getData()<< std::endl;
}

//---------------------------------------------------------------------------------------------------------
//---------------------------------------------------------------------------------------------------------

// PHASE 2
void phaseTwo()
{
    LinkedNode*node1=new LinkedNode(15);
    LinkedNode*node2=new LinkedNode(10);
    LinkedNode*node3=new LinkedNode(7);

    node1->setNextLinkedNode(node2);
    node2->setNextLinkedNode(node3);

    std::cout<< "Node 1: " << node1->getData()<< std::endl;
    std::cout<< "Is there a node after node1?: " << (node1->hasNextLinkedNode()? "nextLinkedNode()=null" : "nextLinkedNode()!=null")<< std::endl;
    std::cout<< "next Node: " << node1->getNextLinkedNode()->getData()<< std::endl;
    std::cout<< "Is there a node after node2?: " << (node2->hasNextLinkedNode()? "nextLinkedNode()=null" : "nextLinkedNode()!=null")<< std::endl;
    std::cout<< "next Node: " << node2->getNextLinkedNode()->getData()<< std::endl;
    std::cout<< "Is there a node after node3?: " << (node3->hasNextLinkedNode()? "nextLinkedNode()=null" : "nextLinkedNode()!=null")<< std::endl;
    //std::cout<< node1->getData()<<"-->"<<node2->getData() <<"-->"<<node3->getData()<<std::endl;
    delete node1;
}

// "Part 2" of phase 2 (implementing LinkedList)
// i split it up because it was a lot easier for me to work through issues this way
void phaseTwoPartTwo()
{
    LinkedNode*node1=new LinkedNode(15);
    LinkedNode*node2=new LinkedNode(10);
    LinkedNode*node3=new LinkedNode(7);
    LinkedNode*node4=new LinkedNode(13);

    node1->setNextLinkedNode(node2);
    node2->setNextLinkedNode(node3);
    node3->setNextLinkedNode(node4);
    std::cout<< "----- Below is the linkedList part of phase 2 -----"<< std::endl;

    LinkedList* list=new LinkedList;
    std::cout<< "\nIs the list empty?: "<<(list->isEmpty()? "Yes (head_=null)":"No (head_!=null)")<< std::endl;

    list->insert(node1->getData()); 
    list->insert(node2->getData()); 
    list->insert(node3->getData());
    std::cout<< "\nIs the list empty?: "<<(list->isEmpty()? "Yes (head_=null)":"No (head_!=null)")<< std::endl;
    list->printList(); 
    list->insert(node4->getData());
    list->printList(); 

    delete node1;
    delete list;
}

//---------------------------------------------------------------------------------------------------------
//---------------------------------------------------------------------------------------------------------

// PHASE 3
void phaseThree()
{
    std::cout<<"-----Doing phase 3 things and such-----"<<std::endl;

    LinkedNode*node1=new LinkedNode(15);
    LinkedNode*node2=new LinkedNode(10);
    LinkedNode*node3=new LinkedNode(7);

    // setting forward links 
    node1->setNextLinkedNode(node2);
    node2->setNextLinkedNode(node3);

    //setting backwards links
    node3->setPrevLinkedNode(node2);
    node2->setPrevLinkedNode(node1);

    LinkedList*list=new LinkedList;
    list->insert(node1->getData()); 
    list->insert(node2->getData()); 
    list->insert(node3->getData());
    list->printList();

    std::cout<<"\nnode3->hasPrevLinkedNode(): "<< (node3 -> hasPrevLinkedNode()?"prevLinkedNode()=null" : "prevLinkedNode()!=null")<<std::endl;
    std::cout<< "node3->getPrevLinkedNode(): "<<node3->getPrevLinkedNode()->getData()<<std::endl;

    LinkedNode*current=node3;
    std::cout<<"\nGoing backward: ";    
    while(current!=nullptr)
    {
        std::cout<< current->getData();
        if(current->hasPrevLinkedNode()==false)
        { std::cout<<" --> "; }
        else 
        { std::cout<< " (End of list)"<< std::endl; }
        current=current->getPrevLinkedNode();
    }
    std::cout<<std::endl;
    delete node1;
    delete list;
}

//---------------------------------------------------------------------------------------------------------
//---------------------------------------------------------------------------------------------------------

// PHASE 4
void phaseFour()
{
    std::cout<<"-----Phase 4 things------"<<std::endl;
    DoublyLinkedList*dll=new DoublyLinkedList;

    dll->insert(15);
    dll->insert(10);
    dll->insert(7);
    dll->printList();
    dll->insertAfterLinkedNode(dll->getHead(), 78);
    dll->printList();
    dll->insertBeforeLinkedNode(dll->getTail(), 66);
    dll->printList();
    delete dll;
}

//---------------------------------------------------------------------------------------------------------
//---------------------------------------------------------------------------------------------------------

// here is a function to find the optimal insertion point 
LinkedNode*findOptPoint(DoublyLinkedList*list, int data, int&minHopCount)
{
    //starting search beginning at head and tail
    LinkedNode*currentHead=list->getHead();
    LinkedNode*currentTail=list->getTail();
    LinkedNode*prevHead=nullptr;
    LinkedNode*prevTail=nullptr;

    int hopCountHead=0;
    int hopCountTail=0;

    //std:: cout<< "\n---------- Head traversal ----------"<<std::endl;//debugging
    if(data<currentHead->getData())
    { 
        prevHead=currentHead; 
        //list->insertBeforeLinkedNode(prevHead, data);
        currentHead=currentHead->getNextLinkedNode();
    }
    else
    {
        while(currentHead!= nullptr&& currentHead->getData()<=data)
        {
            //std:: cout<< "currentHead = "<< currentHead->getData()<<std::endl; //debugging
            //std::cout<<"current head =nullptr?? " << (currentHead==nullptr? "yes":"no")<<std::endl; //debugging
            prevHead=currentHead;
            //std:: cout<< "prevHead = "<< prevHead->getData()<<std::endl; //debugging
            currentHead=currentHead->getNextLinkedNode();
            //std:: cout<< "currentHead=currentHead->getNextLinkedNode() "<< currentHead->getData()<<std::endl; //debugging
            hopCountHead++;
        }
    }   
    //std::cout<<"Hop count head: "<< hopCountHead << std::endl;//debugging

    //return prevHead;
    //std:: cout<< "\n---------- Tail traversal ----------"<<std::endl;//debugging
    while(currentTail->getPrevLinkedNode()!=nullptr&& currentTail->getData()>=data)
    {
        //std:: cout<< "currentTail = "<< currentTail->getData()<<std::endl; //debugging
        //std::cout<<"current tail =nullptr?? " << (currentTail==nullptr? "yes":"no")<<std::endl;//debugging
        prevTail=currentTail;
        //std:: cout<< "prevTail = "<< prevTail->getData()<<std::endl;//debugging
        currentTail=currentTail->getPrevLinkedNode();
        //std:: cout<< "currentTail=currentTail->getPrevLinkedNode(): "<< currentTail->getData()<<std::endl;//debugging
        hopCountTail++;
        //std::cout<<"current tail =nullptr?? " << (currentTail==nullptr? "yes":"no")<<std::endl;//debugging
    }
    
    //std::cout<<"Hop count tail: "<< hopCountTail << std::endl;//debugging

    if(hopCountHead<=hopCountTail)
    {
        //std::cout<<"hopCountHead<=hopCountTail "<< std::endl;//debugging
        minHopCount=hopCountHead;
        return prevHead;
        //list->insertAfterLinkedNode(prevHead, data);
    }
    else
    {
        //std::cout<<"hopCountHead>hopCountTail "<< std::endl;//debugging
        minHopCount=hopCountTail;
        return currentTail;
        //list->insertAfterLinkedNode(currentTail, data);
    }
    delete list;
}

void phaseFiveTest()
{
    std::cout<<"-----Phase 5 things------"<<std::endl;
    // This is the test of the function above i hope its working correctly lol
    DoublyLinkedList*test=new DoublyLinkedList;
    DoublyLinkedList*test2=new DoublyLinkedList;
    test->insert(15);
    test->insert(10);
    test->insert(24);
    test->insert(31);
    test->printList();
    int hopCount=0;
    test2->insert(71);
    test2->insert(30);
    test2->insert(11);
    test2->insert(3);
    
    LinkedNode*current=test2->getHead();
    while(current!=nullptr)
    {
        LinkedNode*insertionPoint=findOptPoint(test,current->getData(), hopCount);
        if(current->getData()<insertionPoint->getData())
        { test->insertBeforeLinkedNode(insertionPoint, current->getData()); }
        else
        { test->insertAfterLinkedNode(insertionPoint, current->getData()); }
        current=current->getNextLinkedNode();
    }

    /*
    *  
    *  below is was me testing individual insertions and such 
    *  
    */

    /*LinkedNode*insertionPoint=findOptPoint(test,11, hopCount);
    std:: cout<< "insertion point "<< insertionPoint->getData()<<std::endl;
    //test->insertAfterLinkedNode(insertionPoint,11);
    test->printList();
    LinkedNode*insertionPoint2=findOptPoint(test,29, hopCount);
    std:: cout<< "insertion point "<< insertionPoint2->getData()<<std::endl;
    test->insertAfterLinkedNode(insertionPoint2,29);
    test->printList();
    LinkedNode*insertionPoint3=findOptPoint(test,17, hopCount);
    std:: cout<< "insertion point "<< insertionPoint3->getData()<<std::endl;
    test->insertAfterLinkedNode(insertionPoint3,17);
    test->printList();
    LinkedNode*insertionPoint4=findOptPoint(test,6, hopCount);
    std:: cout<< "insertion point "<< insertionPoint4->getData()<<std::endl;
    //test->insertBeforeLinkedNode(insertionPoint4,6);*/
    test->printList();
    delete test;
    delete test2;
}

// PHASE 5
void phaseFive()
{
    /* 
    *  we are loading data.txt and sorted.txt as well as inserts.txt 
    *  will need to run the program twice (once of data.txt and once for sorted.txt)
    *  in both of those instances you should insert the data elements from inserts.txt
    *  need two output files (output.txt and sortedOutput.txt)
    *  hop count stored in README.TXT
    */
    std::cout<<"-----Phase 5 things------"<<std::endl;

    DoublyLinkedList*unSortDLL=new DoublyLinkedList;
    DoublyLinkedList*sortedDLL=new DoublyLinkedList;
    // ---------------- reading and putting into dll and stuff ----------------
    std::ifstream dataFile("data.txt");
    int data;
    while(dataFile>>data)
    {
        unSortDLL->insert(data);
    }
    dataFile.close();
    std::cout<< "\nPrinting data.txt"<<std::endl;
    unSortDLL->printList();
    std::cout<< "Length: "<< unSortDLL->getLength()<<std::endl;
    
    // sorted.txt
    std::ifstream sortedFile("sorted.txt");
    while(sortedFile>>data)
    {
        sortedDLL->insert(data);
    }
    sortedFile.close();
    std::cout<< "\nPrinting sorted.txt"<<std::endl;
    sortedDLL->printList();    
    //std::cout<< "Length: "<< sortedDLL->getLength()<<std::endl;

    std::ifstream insertFile("inserts.txt");

    //---------------- insert data from inserts.txt into the dll ----------------
    
    std::ofstream readMe("README.txt");
    int hopCountTotalSort=0;
    int hopCountTotalUnsort=0;
    while(insertFile>>data)
    {
        int hopCountSort=0;
        int hopCountUnsort=0;

        LinkedNode*insertionPoint=findOptPoint(sortedDLL, data, hopCountSort);
        if(insertionPoint!=nullptr)
        { 
            //std::cout<< "insert point "<< insertionPoint->getData()<< " with data "<<data<< std::endl;
            sortedDLL->insertAfterLinkedNode(insertionPoint, data); 
            hopCountTotalSort+=hopCountSort;
            //std::cout<<"Hop count total (sorted): "<<hopCountTotalSort<<std::endl;
            readMe<< "Hop count total (sorted): "<<hopCountTotalSort<<std::endl;
        }
        else
        { std::cout<<"Insertion point is not found for "<<data<<std::endl; }

        LinkedNode*insertionPointUnsort=findOptPoint(unSortDLL, data, hopCountUnsort);
        if(insertionPointUnsort!=nullptr)
        { 
            //std::cout<< "insert point "<< insertionPointUnsort->getData()<< " with data "<<data<< std::endl;
            if(data<(unSortDLL->getHead())->getData())
            { unSortDLL->insertBeforeLinkedNode(insertionPointUnsort, data); }
            else
            { unSortDLL->insertAfterLinkedNode(insertionPointUnsort, data); }
            hopCountTotalUnsort+=hopCountUnsort;
            //std::cout<<"Hop count total (unsorted): "<<hopCountTotalUnsort<<std::endl;
            readMe<< "Hop count total (unsorted): "<<hopCountTotalUnsort<<std::endl;
        }
        else
        { std::cout<<"Insertion point is not found for "<<data<<std::endl; }
    }

    insertFile.close();
    readMe.close();
    std::cout<<"exit while loop"<<std::endl;
    
    std::cout<< "\nPrinting data.txt + inserts.txt"<<std::endl;
    unSortDLL->printList();
    std::cout<< "Length: "<< unSortDLL->getLength()<<std::endl;

    std::cout<< "\nPrinting sorted.txt + inserts.txt"<<std::endl;
    sortedDLL->printList();
    std::cout<< "Length: "<< sortedDLL->getLength()<<std::endl;

    //---------------- opening output files to write to it and such ----------------
    std::ofstream SortOutputFile("SortedOutput.txt");
    if(SortOutputFile.is_open())
    {
        LinkedNode*current=sortedDLL->getHead();
        while(current!=nullptr)
        {
            SortOutputFile<< current->getData();
            if(current->getNextLinkedNode()!=nullptr)
            { SortOutputFile<<" "; }
            else 
            { SortOutputFile<<" (End of list)"<< std::endl; }
            current=current->getNextLinkedNode();
        }
        SortOutputFile.close();
        std::cout<<"Data was written to output file"<<std::endl;
    }
    else
    { std::cout<<"Error opening file."<<std::endl; }

    std::ofstream OutputFile("output.txt");
    if(OutputFile.is_open())
    {
        LinkedNode*current=unSortDLL->getHead();
        while(current!=nullptr)
        {
            OutputFile<< current->getData();
            if(current->getNextLinkedNode()!=nullptr)
            { OutputFile<<" "; }
            else 
            { OutputFile<<" (End of list)"<< std::endl; }
            current=current->getNextLinkedNode();
        }
        OutputFile.close();
        std::cout<<"Data was written to output file"<<std::endl;
    }
    else
    { std::cout<<"Error opening file."<<std::endl; }
    
    delete unSortDLL;
    delete sortedDLL;
}

//---------------------------------------------------------------------------------------------------------
//---------------------------------------------------------------------------------------------------------

// MAIN
int main()
{
    std::cout<<"\n** Welcome to Assignment 4 **\n" << std::endl;

    //phaseOne();
    //phaseTwo();
    //phaseTwoPartTwo();
    //phaseThree();
    //phaseFour();
    //phaseFiveTest();
    phaseFive();

    return 0;
}