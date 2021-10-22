package com.btree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class CreateTree {
  private BinaryTreeNode root;
  public char prefixChar;

  public BinaryTreeNode getRoot() {
    return root;
  }

  public CreateTree(ArrayList<Integer> listData) {
    this.root = null;
    constructTree(listData);
    inorderTraversal(root);
    System.out.println();
    preorderTraversal(root);
    System.out.println();
    postorderTraversal(root);
    System.out.println();
    System.out.println(preorder(root));
    System.out.println(inOrder(root));
    System.out.println(postOrder(root));
    // String str = traversePreOrder(root);
    // System.out.println(str);
  }

  public void inorderTraversal(BinaryTreeNode root) {
    if (root != null) {
      inorderTraversal(root.getLeft());
      System.out.print(root.getData() + "  ");
      inorderTraversal(root.getRight());
    }
  }

  public void preorderTraversal(BinaryTreeNode root) {
    if (root != null) {
      System.out.print(root.getData() + "  ");
      preorderTraversal(root.getLeft());
      preorderTraversal(root.getRight());
    }
  }

  public void postorderTraversal(BinaryTreeNode root) {
    if (root != null) {
      postorderTraversal(root.getLeft());
      postorderTraversal(root.getRight());
      System.out.print(root.getData() + "  ");
    }
  }

  void printTree(BinaryTreeNode node, String prefix) {
    if (node == null)
      return;
    System.out.println(prefix + "+ " + node.data);
    printTree(node.getLeft(), prefix + prefixChar);
    printTree(node.getRight(), prefix + prefixChar);
  }

  public String traversePreOrder(BinaryTreeNode root) {

    if (root == null) {
      return "";
    }

    StringBuilder sb = new StringBuilder();
    sb.append(root.getData());

    String pointerRight = "└──";
    String pointerLeft = (root.getRight() != null) ? "├──" : "└──";

    traverseNodes(sb, "", pointerLeft, root.getLeft(), root.getRight() != null);
    traverseNodes(sb, "", pointerRight, root.getRight(), false);

    return sb.toString();
  }

  public void traverseNodes(StringBuilder sb, String padding, String pointer, BinaryTreeNode node,
      boolean hasRightSibling) {
    if (node != null) {
      sb.append("\n");
      sb.append(padding);
      sb.append(pointer);
      sb.append(node.getData());

      StringBuilder paddingBuilder = new StringBuilder(padding);
      if (hasRightSibling) {
        paddingBuilder.append("│  ");
      } else {
        paddingBuilder.append("   ");
      }

      String paddingForBoth = paddingBuilder.toString();
      String pointerRight = "└──";
      String pointerLeft = (node.getRight() != null) ? "├──" : "└──";

      traverseNodes(sb, paddingForBoth, pointerLeft, node.getLeft(), node.getRight() != null);
      traverseNodes(sb, paddingForBoth, pointerRight, node.getRight(), false);
    }
  }

  private void constructTree(ArrayList<Integer> listData) {
    for (int e : listData) {
      // addElement(e);
      insert(e);
    }
  }

  private void insert(int e) {

    if (root == null) {
      root = new BinaryTreeNode(e);
      return;
    } else
      insertElementInAscendingOrder(root, e);
  }

  private void insertElementInAscendingOrder(BinaryTreeNode root, int e) {
    if (root.getData() == e) {
      root.setData(e);
      return;
    }
    if (root.getData() > e) {
      if (root.getLeft() == null) {
        root.setLeft(new BinaryTreeNode(e));
      } else
        insertElementInAscendingOrder(root.getLeft(), e);
    } else if (root.getData() < e) {
      if (root.getRight() == null)
        root.setRight(new BinaryTreeNode(e));
      else
        insertElementInAscendingOrder(root.getRight(), e);
    }
  }

  private BinaryTreeNode addElement(int e) {
    if (root == null) {
      root = new BinaryTreeNode(e);
      return root;
    }
    Queue<BinaryTreeNode> q = new LinkedList<>();
    q.offer(root);
    while (!q.isEmpty()) {
      BinaryTreeNode tmp = q.poll();
      if (tmp != null)
        if (tmp.getLeft() != null)
          q.offer(tmp.getLeft());
        else {
          tmp.setLeft(new BinaryTreeNode(e));
          return root;
        }
      if (tmp.getRight() != null)
        q.offer(tmp.getRight());
      else {
        tmp.setRight(new BinaryTreeNode(e));
        return root;
      }
    }
    return root;
  }

  public List<Integer> preorder(BinaryTreeNode root) {
    List<Integer> res = new ArrayList<>();
    if (root == null)
      return res;
    Stack<BinaryTreeNode> s = new Stack<BinaryTreeNode>();
    s.push(root);
    while (!s.isEmpty()) {
      BinaryTreeNode tmp = s.pop();
      res.add(tmp.getData());
      if (tmp.getRight() != null) {
        s.push(tmp.getRight());
      }
      if (tmp.getLeft() != null) {
        s.push(tmp.getLeft());
      }
    }
    return res;
  }

  public List<Integer> inOrder(BinaryTreeNode root) {
    List<Integer> res = new ArrayList<>();
    if (root == null)
      return res;
    Stack<BinaryTreeNode> s = new Stack<BinaryTreeNode>();
    BinaryTreeNode currentNode = root;
    boolean done = false;
    while (!done) {
      if (currentNode != null) {
        s.push(currentNode);
        currentNode = currentNode.getLeft();
      } else {
        if (s.isEmpty())
          done = true;
        else {
          currentNode = s.pop();
          res.add(currentNode.getData());
          currentNode = currentNode.getRight();
        }
      }
    }
    return res;
  }


  private List<Integer> postOrder(BinaryTreeNode root) {
    List<Integer> res = new ArrayList<>();
    if (root == null)
      return res;
    Stack<BinaryTreeNode> s = new Stack<BinaryTreeNode>();
    s.push(root);
    BinaryTreeNode prev = null;
    while (!s.isEmpty()) {
      BinaryTreeNode curr = s.peek();
      if (prev == null || prev.getLeft() == curr || prev.getRight() == curr) {
        if (curr.getLeft() != null) {
          s.push(curr.getLeft());
        } else if (curr.getRight() != null) {
          s.push(curr.getRight());
        }
      } else if (curr.getLeft() == prev) {
        if (curr.getRight() != null) {
          s.push(curr.getRight());
        }
      } else {
        res.add(curr.getData());
        s.pop();
      }
      prev = curr;
    }
    return res;
  }
}
