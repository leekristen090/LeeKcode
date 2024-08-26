// Assignment 5
// CS 341

// Honor Pledge: 
//
// I pledge that I have neither given nor
// receieved help on this assignment. 
//
// kmlee1

// Red-Black tree Driver

//---------------------------------------------------------------------------------------------------------
//---------------------------------------------------------------------------------------------------------
#include "RedBlackTree.h"
#include <fstream>
//---------------------------------------------------------------------------------------------------------
//---------------------------------------------------------------------------------------------------------

// IN CLASS STUFF
void inClass()
{
    BinarySearchTree*myTree=new BinarySearchTree;
    myTree->insert(7);
    myTree->insert(3);
    myTree->insert(10);
    myTree->insert(1);
    std::cout<< "Root: "<< myTree->getRoot()->getData()<<std::endl;
    std::cout<< "Right child: "<< (myTree->getRoot()->getRightChild())->getData()<<std::endl;
    std::cout<< "Left child: "<< (myTree->getRoot()->getLeftChild())->getData()<<std::endl;
    myTree->printTree(myTree->getRoot());
    std::cout<<std::endl;
    delete myTree;
}

//---------------------------------------------------------------------------------------------------------
//---------------------------------------------------------------------------------------------------------

// PHASE ONE
void phaseOne()
{
    std::cout<<"Phase 1"<<std::endl;
    // reading data in from data.txt and just printing it to thomas I guess 
    int data;
    std::ifstream file("data.txt");
    while(file>>data)
    { std::cout<<data<<" "; }
    file.close();
    std::cout<<std::endl;
}

//---------------------------------------------------------------------------------------------------------
//---------------------------------------------------------------------------------------------------------

// PHASE TWO
void phaseTwo()
{
    std::cout<<"Phase 2"<<std::endl;
    BinarySearchTree*myTree=new BinarySearchTree;
    
    // reading stuff from data.txt
    int data;
    std::ifstream file("data.txt");
    while(file>>data)
    { myTree->insert(data); }
    file.close();
    std::cout<<std::endl;

    // printing and such 
    std::cout<< "Root: "<< myTree->getRoot()->getData()<<std::endl;
    myTree->printTree(myTree->getRoot()); // i think i went a little overboard with the printing
    std::cout<<std::endl;
    // idk if this is calculating the hieght correctly i was going off of the online visualization thing you showed us in class
    std::cout<<"Height of BST: "<<myTree->getHeight()<<std::endl; 
    std::cout<<std::endl;
    // deleteing because we manage memory and stuff
    delete myTree;
}

//---------------------------------------------------------------------------------------------------------
//---------------------------------------------------------------------------------------------------------

/* 
*   in this phase we want to root to be black and the rest of the inserted nodes to be red
*   we are not balancing at this time i don't think
*/

// PHASE 3
void phaseThree()
{
    std::cout<<"Phase 3"<<std::endl;
    RedBlackTree*tree=new RedBlackTree;
    /*tree->insert(7);
    tree->insert(18);
    tree->insert(3);*/
    int data;
    std::ifstream file("data.txt");
    while(file>>data)
    { tree->insert(data); }
    file.close();
    std::cout<<std::endl;

    tree->printTree(tree->getRoot());
    std::cout<< "\nRoot: "<< tree->getRoot()->getData()<<std::endl;
    std::cout<< "Color: "<<((tree->getRoot())->getColor()? "Black":"Red")<<std::endl;
    std::cout<<"(tree->getRoot())->getRightChild()->getData(): "<<(tree->getRoot())->getRightChild()->getData()<<std::endl;
    std::cout<<"(tree->getRoot())->getRightChild()->getColor(): "<<((tree->getRoot())->getRightChild()->getColor()? "Black":"Red")<<std::endl;
    
    delete tree;
}

//---------------------------------------------------------------------------------------------------------
//---------------------------------------------------------------------------------------------------------

// PHASE 4
void phaseFour()
{
    std::cout<<"Phase 4"<<std::endl;
    RedBlackTree*tree=new RedBlackTree;
/*  this is the insertion data that matches the example in assign5 pdf
    tree->insert(3);
    tree->insert(18);
    tree->insert(7);
    // when we insert 7 there should be a rotate and 7 is black and 3 and 18 are red
    tree->insert(10);
    // when we insert 10 it stays red and 3 and 18 turn black 
    tree->insert(22); 
    // when we insert 22 everything should stay the same(7, 3, 18 are black) and10 and 22 are red
    tree->insert(8);
    // when we insert 8 it stays red, then 10 and 22 (uncle to 8) become black and 18 becomes red
    tree->insert(15);
    // when we insert 15, nothing changes and 15 is red
    tree->insert(29);
    // when we insert 29, nothing changes and 29 is red
    // these notes are based on the RBT visualization online thingy
*/
    // this is the data from data.txt that was given to us
    int data;
    std::ifstream file("data.txt");
    while(file>>data)
    { tree->insert(data); }
    file.close();
    std::cout<<std::endl;

    tree->printTree(tree->getRoot());
    std::cout<<std::endl;
    std::cout<< "Root: "<< tree->getRoot()->getData()<<std::endl;
    std::cout<< "Root Color: "<<((tree->getRoot())->getColor()? "Black":"Red")<<std::endl;
    std::cout<< "Root right child: "<< tree->getRoot()->getRightChild()->getData()<<std::endl;
    std::cout<<"Root right child color: "<<((tree->getRoot())->getRightChild()->getColor()? "Black":"Red")<<std::endl;
    std::cout<<"(tree->getRoot())->getRightChild()->getLeftChild(): "<<((tree->getRoot())->getRightChild()->getLeftChild()->getData())<<std::endl;    
    std::cout<<"(tree->getRoot())->getRightChild()->getLeftChild()->getColor(): "<<((tree->getRoot())->getRightChild()->getLeftChild()->getColor()? "Black":"Red")<<std::endl;
    std::cout<<"Root left child: "<< tree->getRoot()->getLeftChild()->getData()<<std::endl;
    std::cout<<"Root left child color: "<< ((tree->getRoot())->getLeftChild()->getColor()? "Black":"Red")<<std::endl;
    
    delete tree;
}

//---------------------------------------------------------------------------------------------------------
//---------------------------------------------------------------------------------------------------------

// PHASE 5
void phaseFive()
{
    std::cout<<"Phase 5\n"<<std::endl;
    RedBlackTree*tree=new RedBlackTree;

    tree->insert(3);
    tree->insert(18);
    tree->insert(7);
    tree->insert(10);
    tree->insert(22);
    tree->insert(8);
    tree->insert(15);
    tree->insert(29);
    std::cout<<std::endl;

    /*int data;
    std::ifstream file("data.txt");
    while(file>>data)
    { tree->insert(data); }
    file.close();
    std::cout<<std::endl;*/

    tree->printTree(tree->getRoot());
    std::cout<<std::endl;
    std::cout<<"Red nodes: ";
    tree->printRedNodes(tree->getRoot());
    std::cout<<std::endl;
    std::cout<<"Black nodes: ";
    tree->printBlackNodes(tree->getRoot());
    std::cout<<std::endl;
    std::cout<<"Root: "<< tree->getRoot()->getData()<<std::endl;

    delete tree;
}

//---------------------------------------------------------------------------------------------------------
//---------------------------------------------------------------------------------------------------------
//---------------------------------------------------------------------------------------------------------
//---------------------------------------------------------------------------------------------------------

int main()
{
    std::cout<<"\n** Welcome to Assignment 5 **"<<std::endl;
    //inClass();
    //phaseOne();
    //phaseTwo();
    //phaseThree();
    //phaseFour();
    phaseFive();

    return 0;
}