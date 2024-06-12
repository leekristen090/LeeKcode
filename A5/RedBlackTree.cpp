// Assignment 5
// CS 341
//
// Honor Pledge: 
//
// I pledge that I have neither given nor
// receieved help on this assignment. 
//
// kmlee1
//
// RedBlackTree.cpp

#include "RedBlackTree.h"

RedBlackTree::RedBlackTree(): BinarySearchTree() {}
RedBlackTree::~RedBlackTree() {}

// override insert in base so that we can also add color to things
void RedBlackTree::insert(int data)
{
    TreeNode*newNode=new TreeNode(data);
    std::cout<<"Inserting "<< data<<std::endl;
    
    if(root_==nullptr)
    { newNode->setColor(TreeNode::BLACK); }
    else 
    {newNode->setColor(TreeNode::RED);}
    root_=insertNode(root_, newNode);
    // call balanceColor() within insert 
    balanceColor(root_, newNode);
}

void RedBlackTree::printTree(TreeNode*root) 
{
    if(root==nullptr)
    { return; }
    else
    {
        printTree(root->getLeftChild());// this is recursive bc we are calling the func within the func
        std::cout<< root->getData()<<" ";
        printTree(root->getRightChild());
    }
}

// call this internally in balanceColor() so let's get the rotations to work first i guess
void RedBlackTree::rotateLeft(TreeNode*&root, TreeNode*&newNode)
{
    //std::cout<<"entering rotateLeft()"<<std::endl; // debugging

    TreeNode*rightChild=newNode->getRightChild();
    newNode->setRightChild(rightChild->getLeftChild());

    if(newNode->getRightChild()!=nullptr)
    { newNode->getRightChild()->setParent(newNode); }
    rightChild->setParent(newNode->getParent());

    if(newNode->getParent()==nullptr)
    { root=rightChild; }
    else if(newNode==newNode->getParent()->getLeftChild())
    { newNode->getParent()->setLeftChild(rightChild); }
    else
    { newNode->getParent()->setRightChild(rightChild); }

    rightChild->setLeftChild(newNode);
    newNode->setParent(rightChild);
    //std::cout<<"exiting rotateLeft()"<<std::endl; // debugging
}

void RedBlackTree::rotateRight(TreeNode*&root, TreeNode*&newNode)
{
    //std::cout<<"entering rotateRight() where root = "<<root->getData()<<" newNode = "<<newNode->getData()<<std::endl; // debugging
    if(newNode==nullptr || newNode ->getLeftChild()==nullptr)
    { return; }
    TreeNode*leftChild=newNode->getLeftChild();
    newNode->setLeftChild(leftChild->getRightChild());

    if(newNode->getLeftChild()!=nullptr)
    { newNode->getLeftChild()->setParent(newNode); }
    leftChild->setParent(newNode->getParent());

    if(newNode->getParent()==nullptr)
    { root=leftChild; }
    else if (newNode==newNode->getParent()->getLeftChild())
    { newNode->getParent()->setLeftChild(leftChild); }
    else
    { newNode->getParent()->setRightChild(leftChild); }

    leftChild->setRightChild(newNode);
    newNode->setParent(leftChild);
    //std::cout<<"exiting rotateRight()"<<std::endl; // debugging
}

void RedBlackTree::balanceColor(TreeNode*&root, TreeNode*& newNode)
{
    //std::cout<<"Using balanceColor()"<<std::endl; // debugging
    while(newNode!=root && newNode->getParent()->getColor()==TreeNode::RED)
    {
        //std::cout<<"entered while loop"<< std::endl;
        // need to balance color when the newNode is not the root and the parent is red
        //if(newNode->getData()<root->getData())
        if(newNode->getParent()==newNode->getParent()->getParent()->getLeftChild())
        {
            //std::cout<<"if(newNode->getParent()==newNode->getParent()->getParent()->getLeftChild())"<<std::endl;
            TreeNode *aunt=newNode->getParent()->getParent()->getRightChild();

            if(aunt!=nullptr && aunt->getColor()==TreeNode::RED)
            {
                //std::cout<<"if(aunt!=nullptr && aunt ->getColor()==TreeNode::RED)"<<std::endl;
                newNode->getParent()->setColor(TreeNode::BLACK);
                aunt->setColor(TreeNode::BLACK);
                newNode->getParent()->getParent()->setColor(TreeNode::RED);
                newNode=newNode ->getParent()->getParent();
            }
            else
            {
                //std::cout<<" the else of the if(aunt!=nullptr && aunt ->getColor()==TreeNode::RED)"<<std::endl;
                if(newNode==newNode ->getParent()->getRightChild())
                {
                    //std::cout<<"if(newNode==newNode ->getParent()->getRightChild())"<<std::endl;
                    newNode=newNode->getParent();
                    //std::cout<<"using rotateLeftt() where root = "<<root->getData()<<" parent of newNode = "<<newNode->getData()<<std::endl; // debugging
                    rotateLeft(root, newNode);
                }
                //std::cout<<"the else of if(newNode==newNode ->getParent()->getRightChild())"<<std::endl;
                newNode->getParent()->setColor(TreeNode::BLACK);
                newNode->getParent()->getParent()->setColor(TreeNode::RED);
                TreeNode*grandP=newNode->getParent()->getParent();
                //std::cout<<"using rotateRight() where root = "<<root->getData()<<" newNode = "<<newNode->getData()<<std::endl; // debugging
                rotateRight(root, grandP);
            }
        }
        else
        {
            // always goes into this else statement no matter what number is being inserted
            //std::cout<<" This is the else of the if(newNode->getParent()==newNode->getParent()->getParent()->getLeftChild())"<<std::endl;

            TreeNode*aunt =newNode->getParent()->getParent()->getLeftChild();

            if(aunt!=nullptr && aunt->getColor()==TreeNode::RED)
            {
                //std::cout<<"if(aunt!=nullptr && aunt->getColor()==TreeNode::RED)"<<std::endl;
                newNode->getParent() ->setColor(TreeNode::BLACK);
                aunt->setColor(TreeNode::BLACK);
                newNode=newNode->getParent()->getParent();
                aunt->getParent()->setColor(TreeNode::RED);
            }
            else
            {
                // aunt is nullptr and is black
                //std::cout<<"else of if(aunt!=nullptr && aunt->getColor()==TreeNode::RED)"<<std::endl;
                if(newNode==newNode->getParent()->getLeftChild())
                {
                    //std::cout<<"if(newNode==newNode->getParent()->getLeftChild())"<<std::endl;
                    newNode=newNode->getParent();
                    //std::cout<<"using rotateRight() where root = "<<root->getData()<<"  parent of newNode = "<<newNode->getData()<<std::endl; // debugging
                    rotateRight(root, newNode);
                }
                //std::cout<<"else of if(newNode==newNode->getParent()->getLeftChild())"<<std::endl;
                newNode->getParent()->setColor(TreeNode::BLACK);
                newNode->getParent()->getParent()->setColor(TreeNode::RED);
                TreeNode*grandP=newNode->getParent()->getParent();
                //std::cout<<"using rotateLeft() where root = "<<root->getData()<<" newNode = "<<newNode->getData()<<std::endl; // debugging
                rotateLeft(root, grandP);              
            }
        }
    }
    root->setColor(TreeNode::BLACK); // ensuring that the root color is always black
    //std::cout<<"Done using balanceColor()"<<std::endl; // debugging
}

void RedBlackTree::printRedNodes(TreeNode*root)
{
    // inorder traversal 
    if(root==nullptr)
    { return; }
    printRedNodes(root->getLeftChild());
    if(root->getColor()==TreeNode::RED)
    { std::cout<<root->getData()<<" "; }
    printRedNodes(root->getRightChild());
}

void RedBlackTree::printBlackNodes(TreeNode*root)
{
    // preorder traversal
    if(root==nullptr)
    { return; }
    //printBlackNodes(root);
    if(root->getColor()==TreeNode::BLACK)
    { std::cout<<root->getData()<<" "; }
    printBlackNodes(root->getLeftChild());
    printBlackNodes(root->getRightChild());
}