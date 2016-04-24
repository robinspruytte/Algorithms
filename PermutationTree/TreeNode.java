//This class is based on a class found on the internet.
//http://stackoverflow.com/questions/19330731/tree-implementation-in-java-root-parents-and-children

//Class contains implementation for a (tree)Node which can form a tree.
package algorithms.PermutationTree;

import java.util.*;

public class TreeNode<E> {
    //Every TreeNode has a parent, a form of data, and a collection of children who are also TreeNodes
    private ArrayList<TreeNode<E>> children;
    private TreeNode<E> parent;
    private E data;

    /**
     * Basic constructor if no parent needs to be added
     * @param data Adds the date to the TreeNode
     */
    public TreeNode(E data) {
        this.data = data;
    }

    /**
     * This constructor adds data and links the TreeNode to a parent node.
     * @param data Adds data
     * @param parent parent node
     */
    public TreeNode(E data, TreeNode<E> parent) {
        this.data = data;
        this.parent = parent;
        parent.addChild(this);
    }

    /**
     * returns children
     * @return arrayList of children
     */
    public ArrayList<TreeNode<E>> getChildren() {
        return children;
    }

    /**
     * returns parent
     * @return the parent node
     */
    public TreeNode<E> getParent() {
        return parent;
    }

    /**
     * Add a treenode as child
     * @param child
     */
    public void addChild(TreeNode<E> child) {
        if(children == null) {
            children = new ArrayList<TreeNode<E>>();
        }
        this.children.add(child);
    }

    /**
     * returns the data
     * @return data
     */
    public E getData() {
        return this.data;
    }
}