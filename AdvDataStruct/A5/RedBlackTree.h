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
// RedBlackTree.h

#include "BinarySearchTree.h"
#ifndef REDBLACKTREE_H
#define REDBLACKTREE_H

class RedBlackTree: public BinarySearchTree
{
    public:
        RedBlackTree();
        virtual ~RedBlackTree();
        virtual void insert(int data);
        void printTree(TreeNode*root); // inorder traversal
        void printRedNodes(TreeNode*root); // inorder traversal (L, N, R) to search for red colored nodes 
        void printBlackNodes(TreeNode*root); // preorder traversal (N, L, R) to search for black colored nodes

    private:
        void rotateLeft(TreeNode*&root, TreeNode*&newNode);
        void rotateRight(TreeNode*&root, TreeNode*&newNode);
        void balanceColor(TreeNode*& root, TreeNode*&newNode);
};

#endif
