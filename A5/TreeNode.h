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
// TreeNode.h
#ifndef TREENODE_H
#define TREENODE_H

class RedBlackTree;

class TreeNode
{
    public: 
        TreeNode();
        TreeNode(int data);
        ~TreeNode();
        void setParent(TreeNode*parent);
        void setLeftChild(TreeNode*leftChild_);
        void setRightChild(TreeNode*rightChild_);
        TreeNode*getLeftChild();
        TreeNode*getRightChild();
        TreeNode*getParent();
        void setData(int data);
        int getData();
        enum Color{RED,BLACK}; // i think red=0 and black=1
        void setColor(Color color); // This is added for phase 3 
        Color getColor() const;
         
    private:
        TreeNode*leftChild_;
        TreeNode*rightChild_;
        TreeNode*parent_;
        int data_;
        Color color_;
};

#endif