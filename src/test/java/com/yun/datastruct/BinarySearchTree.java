package com.yun.datastruct;

public class BinarySearchTree<AnyType extends Comparable<? super AnyType>> {
    private BinaryNode<AnyType> root;

    public BinarySearchTree(){
        root=null;
    }
    public boolean isEmpty(){
        return root==null;
    }

    public void insert(AnyType x){
        root=insert(x,root);
    }
    public BinaryNode<AnyType> insert(AnyType x,BinaryNode<AnyType> t){
        if(t==null){
            return new BinaryNode<AnyType>(x,null,null);
        }
        int compareResult=x.compareTo(t.element);
        if(compareResult<0)
            t.leftchild=insert(x,t.leftchild);
        else if(compareResult>0)
            t.rightchild=insert(x,t.rightchild);
        else
            ;
        return t;
    }

    //中序遍历
    public static void middle(BinaryNode node){
        if(node==null){
            return;
        }
        BinaryNode leftchild = node.leftchild;
        BinaryNode rightchild = node.rightchild;
        if(leftchild!=null)
            middle(leftchild);
        else if(rightchild!=null)
            middle(rightchild);
        else
            System.out.print(" "+node.element);
    }
    public static void main(String[] args) {
        BinarySearchTree tree=new BinarySearchTree();
        tree.insert(5);
        tree.insert(6);
        tree.insert(1);
        tree.insert(8);
        tree.insert(4);
        tree.insert(2);
        middle(tree.root);
    }
    private static class BinaryNode<AnyType>{
        private AnyType element;
        private BinaryNode<AnyType> leftchild;
        private BinaryNode<AnyType> rightchild;
        BinaryNode(AnyType element){
            this(element,null,null);
        }
        BinaryNode(AnyType element,BinaryNode<AnyType> leftchild,BinaryNode<AnyType> rightchild){
            this.element=element;
            this.leftchild=leftchild;
            this.rightchild=rightchild;
        }
    }

}
