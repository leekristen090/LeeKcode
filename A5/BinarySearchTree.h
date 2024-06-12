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
// BinarySearchTree.h

#ifndef BINARYSEARCHTREE_H
#define BINARYSEARCHTREE_H
#include "TreeNode.h"
#include <iostream>

class BinarySearchTree: public TreeNode
{
    public:
        BinarySearchTree();
        ~BinarySearchTree();
        TreeNode*getRoot(); // returns root of tree
        void insert(int value); // insert value into BST
        void printTree(TreeNode*root); // printing the tree
        int getHeight(); // returns height of tree
    protected:
        TreeNode* insertNode(TreeNode*root, TreeNode*newNode);
        TreeNode*root_;
        int getHeight(TreeNode*root);
};

#endif