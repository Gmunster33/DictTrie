package edu.up.cs371.dicttrie;

import android.util.Log;

import java.util.Vector;

public class DictionaryTrie {

    public char [] alphabet = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};

    public Vector<TrieNode> top = new Vector<TrieNode>(26); //TODO MAKE THIS PRIVATE!!!!!!


    public void initializeTop() { // whole alphabet for now. Should be just the 16 Boggle characters eventually.
        for (int i = 0; i < alphabet.length; i++) {
            top.add( new TrieNode(alphabet[i], false));
        }
    }

    public void printSubTries(TrieNode node) {
        Log.i("node.letter = ", "" + node.letter);
        String kids = "";
        if(node.children.size() == 0) {
            Log.i("Children: ", "NONE");
            if(!node.isWord) {
                Log.i("ERROR!!!!", "isWord SHOULD HAVE BEEN SET TO TRUE!!!!");
            }
        }
        else { //the node has children...
            for (int i = 0; i < node.children.size(); i++) {
                kids = kids + node.children.get(i).letter + ' ';
            }
            Log.i("Children: ", kids);
        }
        for (int i = 0; i < node.children.size(); i++) {
            printSubTries(node.children.get(i)); //Recurse!!!!!!
        }

        }

    public void addWord(String word) { //breaks a string into chars and then adds to the trie. Smartly...
        char [] chars = word.toCharArray();
        int i = 0;
        //search for top entry point...
        for(i = 0; i < top.size(); i++) {
            if(top.get(i).letter==chars[0]) {
                break;
            }
            else if(i==top.size()) {
                return; // first letter not in dictionary; reject it
            }
        }// found top letter...
        TrieNode nodePointer = top.get(i);

 //two cases: either the child already has been filled, or the spot is empty and needs to be filled.
 //there's some subtlety here; i.e. if we're at the end of the word etc.
        for (int j = 1; j < chars.length; j++) { //for each character in the word...
            int child;

            if(nodePointer.children.size()==0) {
                nodePointer.children.add( new TrieNode(chars[j], false));
                nodePointer = nodePointer.children.get(0);
                
            } else {
                for (child = 0; child < nodePointer.children.size(); child++) {//go through all the children at the current pointer location

                    if (nodePointer.children.get(child).letter == chars[j]) { //see if the proper child already exists...
                        nodePointer = nodePointer.children.get(child); //if so, change the pointer target and process the next char from chars.
                        break;
                    }
                    else if(child == nodePointer.children.size()-1) { //if we've iterated through all children without any luck, it's time to add a new node with this char, change the pointer target to this new node, and repeat.
                        nodePointer.children.add(new TrieNode(chars[j], false));
                        nodePointer.children.get(child+1).parent = nodePointer; //Luke Skywalker learns that Darth Vader is his dad...
                        nodePointer = nodePointer.children.get(child+1); //point to the NEW child...

                        }
                    }
                }
            if (j == chars.length-1) { //if we're at the end of the word, mark it in the trie.
                nodePointer.isWord = true;
                Log.i("Last letter in word is: ", nodePointer.letter + "!!!!!!!!!!!!!!!!");
            }
        }
    }
}
