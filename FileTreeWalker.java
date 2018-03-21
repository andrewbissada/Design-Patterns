package csci603.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

//This is the executable class
public class FileTreeWalker { //parses input arguments (string array)
	public static void main(String[] args) throws IOException {
		if (args.length != 1) {  //If they don't have 1 argument
			printUsage();
			System.exit(-1);  //signals that something went wrong
		}

		String pathName = args[0];  //set it to the first one
		// Since Files.walkFileTree method takes an input argument of type Path,
		// convert the string argument passed to main method to Path instance
		Path startingDir = Paths.get(pathName);
		printStructure(startingDir);
	}

	private static void printUsage() {
		System.out.println("Usage: java edu.citadel.csci223.util.FileTreeWalker(<path>)");
		System.out.println(" where <path> is the name of a file or directory");
		System.out.println();
	}

	private static void printStructure(Path startingDir) throws IOException {
		// To walk a file tree, create an instance of the FileVisitor you have
		// created, and pass it to the walkFileTree method

		// Since walkFileTree method by specification throws a checked
		// IOException, it must be either caught in the try-catch statement, or
		// it must be declared in the method. I have chosen to declare it in the
		// throws section of method declaration. If this exception is thrown,
		// the method will throw the exception back to main method, and the main
		// method will terminate the execution of the program
		//has static method walkfiletree
		Files.walkFileTree(startingDir, new MyFileVisitor()); //we can use MyFileVisitor because it extends SimpleFileVisitor
		//just look at walkFileTree method, it allows for this
	}
}
