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
// TreeNode.cpp

#include "TreeNode.h"

TreeNode::TreeNode():data_(0),leftChild_(nullptr), rightChild_(nullptr),parent_(nullptr)
{}

TreeNode::TreeNode(int data):data_(0),leftChild_(nullptr), rightChild_(nullptr),parent_(nullptr)
{ data_=data; }

TreeNode::~TreeNode()
{
    if(leftChild_!=nullptr)
    { delete leftChild_; }
    if(rightChild_!=nullptr)
    { delete rightChild_; }
}

void TreeNode::setParent(TreeNode*parent)
{ parent_ =parent; }

void TreeNode::setLeftChild(TreeNode*leftChild)
{ leftChild_=leftChild; }

void TreeNode::setRightChild(TreeNode*rightChild)
{ rightChild_=rightChild; }

TreeNode*TreeNode::getLeftChild()
{ return leftChild_; }

TreeNode*TreeNode::getRightChild()
{ return rightChild_; }

TreeNode*TreeNode::getParent()
{ return parent_; }

void TreeNode::setData(int data)
{ data_=data; }

int TreeNode::getData()
{ return data_; }

void TreeNode::setColor(Color color)
{ color_=color; }

TreeNode::Color TreeNode::getColor() const
{ return color_; }
