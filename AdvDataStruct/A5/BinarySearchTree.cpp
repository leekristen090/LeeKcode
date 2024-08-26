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
// BinarySearchTree.cpp

#include "BinarySearchTree.h"

BinarySearchTree::BinarySearchTree(): root_(nullptr)
{}

BinarySearchTree::~BinarySearchTree()
{
    if(root_!=nullptr)
    { delete root_; }
}

TreeNode*BinarySearchTree::getRoot()
{ return root_; }

void BinarySearchTree::insert(int value)
{
    TreeNode*node=new TreeNode(value);
    root_=insertNode(root_, node);
}

TreeNode* BinarySearchTree::insertNode(TreeNode*root, TreeNode*newNode)
{
    if(root==nullptr)
    { 
        return newNode; 
    }
    else
    {
        if(newNode->getData()<root->getData())
        {
            // we are in the left subtree
            root->setLeftChild(insertNode(root->getLeftChild(), newNode));
            // setting the parent pointer - this is similar to seting the prev linked node in assign 4
            root->getLeftChild()->setParent(root);
        }
        else
        {
            // we are in the right subtree 
            root->setRightChild(insertNode(root->getRightChild(), newNode));
            root->getRightChild()->setParent(root);
        }
        return root;
    } 
}

void BinarySearchTree::printTree(TreeNode*root)
{
    // three types of traversals 
    // in order traversal -> this is reading left to right as if it is a linear thingy
    if(root==nullptr)
    { return; }
    else
    {
        printTree(root->getLeftChild());// this is recursive bc we are calling the func within the func
        std::cout<< root->getData()<<" ";
        printTree(root->getRightChild());
    }
}

int BinarySearchTree::getHeight(TreeNode*root)
{
    if(root==nullptr)
    { return 0; }
    int left=getHeight(root->getLeftChild());
    int right=getHeight(root->getRightChild());
    if(left>right)
    { return left+1; }
    else
    { return right+1; }
}

int BinarySearchTree::getHeight()
{ return getHeight(root_); }