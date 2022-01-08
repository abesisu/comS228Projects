package edu.iastate.cs228.hw4;

/**
 * This class builds and represents the binary tree used for the character encodings.
 * Each node in the binary tree is its own MsgTree with a root and two children.
 * The methods for printing the codes, decoding the message, and printing the statistics can be found as well.
 *
 * @author Abe Scheideman
 */
public class MsgTree {
    /**
     * The character value stored in the current node.
     */
    public char payloadChar;

    /**
     * The current node's left child
     */
    public MsgTree left;

    /**
     * The current node's right child
     */
    public MsgTree right;

    /**
     * Used for recursive solution to track of the location in the encoding scheme string
     */
    private static int staticCharIdx = 0;

    /**
     * Total number of encoded characters
     */
    private static int encodedChars = 0;

    /**
     * Total number of bits for each character encoded
     */
    private static int totalBits = 0;

    /**
     * Counts the total number of encoded characters
     */
    private static int totalChars = 0;


    /**
     * Constructor used to build the tree from a string.
     * While there are still characters in the string:
     *  parse the current char, call the char constructor then check if it is an internal node
     *  If it is an internal node, recursively add the left and right subtrees
     * Once there are no more characters, simply return I think ****
     * @param encodingString
     */
    public MsgTree(String encodingString) {
        //First, set the payloadChar and left and right children to null as a default.
        this(encodingString.charAt(staticCharIdx));
        staticCharIdx++;
        //If the node is internal, recursively add the left and right children.
        if (payloadChar == '^' && staticCharIdx < encodingString.length()) {
            encodedChars--;
            left = new MsgTree(encodingString);
            right = new MsgTree(encodingString);
        }
    }


    /**
     * Constructor for each character.
     * Sets the children to null for both internal and leaf nodes as a default.
     *
     * @param payloadChar
     */
    public MsgTree(char payloadChar) {
        this.payloadChar = payloadChar;
        left = null;
        right = null;
        encodedChars++;
    }


    /**
     * Method to print characters and their binary codes.
     * Performs recursive preorder traversal of the MsgTree
     * and prints all the characters and their bit codes.
     *
     * If the current node is an internal node, recursively search for a leaf node.
     * The code for each character is recursively built by adding
     * the path taken (0 or 1) to the code when moving on to the next node.
     *
     * @param root
     * @param code
     */
    public static void printCodes(MsgTree root, String code) {
        if (root == null) return;
        //Print the character and the code if it's a leaf or continue searching if it's internal
        if (root.left == null && root.right == null) {
            System.out.println("   " + root.payloadChar + "       "  + code);
            totalBits += code.length();
        } else if (root.payloadChar == '^') {
            printCodes(root.left, code + "0");
            printCodes(root.right, code + "1");
        }
    }


    /**
     * Decodes one character at a time and prints it to the console.
     * Uses the following pseudo-algorithm:
     *
     *  Start at root
     *  Repeat until at leaf
     *      Scan one bit
     *      Go to left child if 0; else go to right child
     *  Print leaf payload
     *
     * @param codes
     * @param msg
     */
    public void decode(MsgTree codes, String msg) {
        MsgTree root = codes;
        int currBit = 0;
        char path;
        while (currBit < msg.length()) {
            //Both children of the current node should be null if it is a leaf
            while (codes.left != null && codes.right != null) {
                path = msg.charAt(currBit);
                if (path == '0') {
                    codes = codes.left;
                } else if (path == '1') {
                    codes = codes.right;
                }
                currBit++;
            }
            System.out.print(codes.payloadChar);
            totalChars++;
            codes = root;
        }
    }


    /**
     * Prints the average number of bits used to encode each character,
     * the total number of characters in the encoded message,
     * and the percentage of space saved as a ration of the average bits used
     * per character and the standard number of bits (16) normally used to encode a character.
     */
    public static void printStatistics() {
        System.out.println("\nSTATISTICS:");
        System.out.print("Avg bits/char:          ");
        double avgBitsPerChar = totalBits / encodedChars;
        System.out.printf("%.1f %n", avgBitsPerChar);

        System.out.println("Total characters:       " + totalChars);
        int uncompressedBits = 16;

        double spaceSavings = (1 - (avgBitsPerChar / uncompressedBits)) * 100;
        System.out.print("Space savings:          ");
        System.out.printf("%.1f%%", spaceSavings);
    }

}
