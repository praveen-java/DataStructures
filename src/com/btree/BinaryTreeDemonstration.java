package com.btree;

import java.util.ArrayList;
import java.util.Arrays;

public class BinaryTreeDemonstration {
	public static void main(String[] args) {
		ArrayList<Integer> arrayList = new ArrayList(Arrays.asList(5,4,7,8,2,1,3,6,9,10));
		CreateTree tree = new CreateTree(arrayList);
		BinaryTreeNode root = tree.getRoot();
		tree.printTree(root," ");
	}
}
