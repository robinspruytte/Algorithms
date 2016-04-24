package algorithms.PermutationTree;

import java.util.*;

//This class uses TreeNodes to make a tree with the elements from an arrayList.
//Every nodes has as child the elements from the starting array, except the elements that have been use in that branch already
//This tree can be used to make permutations
public class PermutationTree<E> {
    //arraySize is the size of the starting arrayList and is mostly needed for loops
    //The top of the tree is root, which is a TreeNode with no data or parent.
    private int arraySize;
    private TreeNode<E> root = new TreeNode(null);

    /**
     * Constructor for PermutationTree
     * Uses method makeTree() to make a tree of the given array and checks arraysize
     * @param array starting arrayList to make a tree from
     */
    public PermutationTree(ArrayList<E> array) {
        arraySize = array.size();
        makeTree(array, root);
    }

    /**
     * Makes the tree structure
     * Starts with adding all elements to the root
     * Adds for all these elements the remaining elements etc.
     * @param array array to make tree from
     * @param root root of the tree
     */
    private void makeTree(ArrayList<E> array, TreeNode root) {
        for (E e : array) {
            TreeNode<E> temp = new TreeNode(e, root);

            ArrayList<E> copiedArray = new ArrayList<E>();
            copiedArray.addAll(array);
            copiedArray.remove(e);

            if (!copiedArray.isEmpty()) {
                makeTree(copiedArray, temp);
            }
        }
    }

    /**
     * Makes a list of all possible permutations using the tree
     * Is actually just a special case of showCombinationsWithNElements(int n) with n = arraySize
     * @return a list of all permutations
     */
    public String showPermutations() {
        return showCombinationsWithNElements(arraySize);
    }

    /**
     * Collects all TreeNodes on a certain depth.
     * @param depth
     * @return all nodes on given depth
     */
    public ArrayList<TreeNode<E>> getAllElementsOnDepth(int depth) {
        if (depth == 1) {
            return root.getChildren();
        }
        else {
            ArrayList<TreeNode<E>> elementsOnDepth = root.getChildren();
            for (int a = 2; a <= depth; a++) {
                ArrayList temp = new ArrayList<TreeNode<E>>();
                for(TreeNode<E> e : elementsOnDepth) {
                    temp.addAll(e.getChildren());
                }
                elementsOnDepth.clear();
                elementsOnDepth.addAll(temp);
            }
            return elementsOnDepth;
        }
    }

    /**
     * Return a list of all possible collections of n element out of all the elements from the starting array
     * Looks for all elements on certain depth and then goes up the branch.
     * @param n the amount of elements in the collections
     * @return a list of all asked collections
     */
    public String showCombinationsWithNElements(int n) {
        StringBuilder combination = new StringBuilder();
        if (n > arraySize) {
            return "Choose a number between 1 and " + arraySize;
        }
        else{
            for (TreeNode node : getAllElementsOnDepth(n)) {
                StringBuilder string = new StringBuilder();
                TreeNode temp = node;
                for (int a = 0; a < n; a++) {
                    string.append(temp.getData());
                    string.append("");
                    temp = temp.getParent();
                }
                combination.append(string.toString());
                combination.append("\n");
            }
            return combination.toString();
        }
    }

    /**
     * All above in one application
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PermutationTree mainTree = null;
        boolean combination = false;
        ArrayList<String> startingArray = new ArrayList<String>();

        System.out.println("Enter elements for you desired array. Enter r when ready.");

        while(true) {
            String command = scanner.next();

            if(command.equals("r")  && mainTree == null) {
                mainTree = new PermutationTree(startingArray);
                System.out.println("Enter p (permutations) or c (for combinations with n elements)");
                System.out.println("Enter s to stop program");
                combination = false;
            }
            else if(mainTree == null) {
                startingArray.add(command);
                combination = false;
            }
            else if(command.equals("p") && mainTree != null) {
                System.out.println("" + mainTree.showPermutations());
                combination = false;
            }
            else if(command.equals("c") && mainTree != null) {
                System.out.println("Choose a length for your combinations");
                System.out.println("Remember that the length must not be greater than the amount of entered elements");
                combination = true;
            }
            else if(combination) {
                System.out.println("" + mainTree.showCombinationsWithNElements(Integer.parseInt(command)));
                combination = false;
            }
            else if(command.equals("s") && mainTree != null)
                System.exit(0);


        }
    }
}

