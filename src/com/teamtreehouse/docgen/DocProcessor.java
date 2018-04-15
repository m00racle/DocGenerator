package com.teamtreehouse.docgen;

public class DocProcessor {
    /**
     * 1. Analyzes the given class's Doc annotation
     * 2. displaying output for the class and for each of its non-private methods.
     * -- @param clazz Class to analyze
     * -- @return True if Doc annotation is used sufficiently on the class and its methods, false otherwise
     * */

    //we start by making the method that will examine the Class named clazz:
    public static boolean process(Class processedClass){

        //Store the name of the class to be processed (we use simple name):
        String className = processedClass.getSimpleName();

        //Displaying the class name we just acquire (this is more like a gimmick):
        System.out.printf("Analyzing Class called: %s....", className);

        //Initializing the number of errors tracker (we use integer)
        int classError = 0;

        //Examining if @Doc is appears in the Class declaration:
        //NOTE: it will be better if you set the annotation first before making this if statement:
        if (processedClass.isAnnotationPresent(Doc.class)){
            //will do more about this but first let me make the else part first
        } else {
            classError++;
            System.out.printf("%n\t=> Class does not contain the proper documentation");
        }

        //return whether there is no class error meaning True, and there is class error meanaing false
        //we use boolean expression on logical equal sign:
        return classError == 0;
    }
}
