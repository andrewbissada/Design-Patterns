package csci603.util;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

// I created a separate class for FileVisitor implementation. Your teacher has made the main class (executable class) behave like a FileVisitor. 
// However, separation of concerns is one of the principles in programming, so to keep the code modular and clean, I have created this class. 
// If you want the code to be as similar to the teacher's example as possible, let me know, I can rewrite this class, or tell u how u can do it yourself
public class MyFileVisitor extends SimpleFileVisitor<Path> {
	// Always keep values that will not change as constants for readability, and
	// ease of modification (only one place where u need to change the values, u
	// don't have to go through the code)
	private static final String EMPTY_SPACE = " ";  //static references the class. Why don't you say String.DirectoryLabel below? myfilevisitor.Directory_Label.. but we are already here, only use that from another class
	private static final String DIRECTORY_LABEL = "+"; //final just means the value won't change, its FINAL
	private static final String FILE_LABEL = "-";
	// An instance variable initially set to 0. This variable will hold the
	// nested level count, so we can indent properly
	private int dirNestingLevel = 0;

	// I will override three of four methods specified in FileVisitor interface,
	// and already implemented in SimpleFileVisitor class
	@Override
	public FileVisitResult preVisitDirectory(Path path, BasicFileAttributes attr) {
		// Just before visiting the directory, print its name  ////When you say Visit do you mean ENTER the directory?
		// The name will consist of three parts: indentation, directory label,
		// and directory name
		// dirNestingLevel++ this is a 'post increment' operator. It means use
		// the current value, and after it has been used, add 1 to its value
		// So, the first time this has been used (on root directory passed to
		// the main class), getIndentString will get a value of 0, and than, the
		// value will be incremented (for nested directories). Remove this ++ operator, and see what
		// happens
		
		//+= will add 1. ++ adds 1
		System.out.println(getIndentString(dirNestingLevel++) + DIRECTORY_LABEL + path.getFileName());
		//I thought the ++ would increase it from 0 to 1
		//Also, I thought getIndentString.... prints a space (or nothing if its first time?)
		//Where did path come from? and its method getFileName?
		

		//THESE are enum's. Its similar to true or false but its more descriptive
		// After printing the name of the directory, continue walking the file
		// tree
		return FileVisitResult.CONTINUE; // This is an already provided
											// enumeration constant, that tells
											// the FileVisitor to continue
											// walking the file tree
		///////I thought SimpleFileVisitor was what we extended from, not FileVisitResult
		//Is CONTINUE a variable or a method?
	}

	//these are called in pairs for each subdirectory. you indent from the previous so only need dirNestingLevel 1 
	@Override
	public FileVisitResult postVisitDirectory(Path path, IOException exc) {
		dirNestingLevel--; // subtract 1 from dirNestingLevel variable, since we
							// have finished traversing this directory, and are
							// returning to the parent directory.
		
		//I thought we would want MORE spaces not less?

		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult visitFile(Path path, BasicFileAttributes attr) {
		// Print the file name. Do not increment dirNestingLevel, since we are
		// visiting a file, not a directory. Keep the dirNestingLevel the same
		// as in the direstory
		System.out.println(getIndentString(dirNestingLevel) + FILE_LABEL + path.getFileName());

		return FileVisitResult.CONTINUE;
	}

	private static String getIndentString(int nestingLevel) {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < nestingLevel; i++) { // if nestingLevel is 0, then
													// this doesn't get entered
			s.append(EMPTY_SPACE);
		}
		return s.toString(); // this happens whether we enter the For loop or
								// not
	}
}
