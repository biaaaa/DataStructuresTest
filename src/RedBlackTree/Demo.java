package RedBlackTree;

import RedBlackTree.RedBlackTree;
import RedBlackTree.RedBlackTree.RedBlackNode;

public class Demo {

	public static void main(String [] args){
		RedBlackTree<Integer> rbTree=new RedBlackTree<Integer>();
		rbTree.insert(13);
		rbTree.insert(44);
		rbTree.insert(22);
		rbTree.insert(2);
		rbTree.insert(12);
		rbTree.insert(1);
		rbTree.insert(45);
		rbTree.insert(42);
		rbTree.insert(15);
		rbTree.insert(0);
		rbTree.insert(6);
		rbTree.insert(9);
		rbTree.insert(5);
		
		rbTree.preOrder();
		System.out.println("Size: "+rbTree.size());
		
		RedBlackNode<Integer> A=rbTree.search(16);
		RedBlackNode<Integer> B=rbTree.search(44);
		System.out.println(A);
		System.out.println(B);
		
	}
	
}
