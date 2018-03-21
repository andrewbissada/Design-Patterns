package csci603.util;

import java.io.*;

/**
 * Utility class that prints the directory structure to standard output showing
 * the composition of nested files and subdirectories.
 */
public class PrintDirectoryStructure {

    /**
     * Prints the structure for the file whose path name is given in arg[0].
     */    
    public static void main(String[] args) {
        if (args.length != 1) { //We say !=1 because we are putting 1 input
            printUsage();
            System.exit(-1);
        }
        String pathName = args[0];
        File file = new File(pathName);
        if (file.exists()) {
            printTree(file);
        } else {
            System.out.println("*** File " + pathName + " does not exist. ***");
        }
    }

    public static void printTree(File file) {
        if(file.isDirectory()) {            
            printDirectory(file, 0);
        } else {            
            printFile(file, 0);
        }
    }

    private static void printDirectory(File dir, int nestingLevel) {
        File[] firstLevelFiles = dir.listFiles();
        for(File file : firstLevelFiles) {   
            if (file.isDirectory()) {                
            	System.out.println(getIndentString(nestingLevel) + "+" + file.getName());
                printDirectory(file, nestingLevel + 1); //this is a recursive call. increment nestingLevel
            } else {
                printFile(file, nestingLevel);
            }            
        }
    }

    private static void printFile(File file, int nestingLevel) {
        System.out.println(getIndentString(nestingLevel) + "-" + file.getName());
    }

    private static String getIndentString(int nestingLevel) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < nestingLevel; i++) {  //if nestingLevel is 0, then this doesn't get entered
            s.append(" ");
        }
        return s.toString();  //this happens whether we enter the For loop or not
    }

    private static void printUsage() {
        System.out.println("Usage: edu.citadel.csis603.(<path>)");
        System.out.println(" where <path> is the path of a file or directory");
        System.out.println();
    }
}

