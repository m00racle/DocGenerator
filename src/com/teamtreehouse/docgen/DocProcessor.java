package com.teamtreehouse.docgen;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

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
            //we'll loop over all methods in the processedClass
            for(Method method : processedClass.getDeclaredMethods()){

                //get method modifiers
                int modifierInt = method.getModifiers();

                //get method name
                String methodName = method.getName();

                //check if the method is non-private
                if(!Modifier.isPrivate(modifierInt)){
                    //start to check if there is error in @Doc inside all method
                    int methodErrors = 0;

                    //display the method name
                    System.out.printf("%n%n\t%s:", methodName);

                    //check if the @Doc is present in the method?
                    if(method.isAnnotationPresent(Doc.class)){
                        //if yes get the reference to the actual annotation
                        Doc doc = method.getAnnotation(Doc.class);

                        //check number of items in parameter description matches the actual parameters
                        int numMissingParameter = getNumMissingParameters(method, doc);
                        if(numMissingParameter > 0){
                            methodErrors++;
                            System.out.printf("%n\t\t=> Missing %s parameter description(s)", numMissingParameter);
                        }

                        //check if there is return description if needed
                        if(!hasReturnDescription(method, doc)){
                            methodErrors++;
                            System.out.printf("%n\t\t=> Misiing description of return value");
                        }
                    } else {
                        //non-private method is missing a @Doc annotation
                        methodErrors++;
                        System.out.printf("%n\t\t=> @Doc annotation missing");
                    }

                    //check for azero errors
                    if(methodErrors == 0){
                        System.out.printf("%n\t\t=> No changes needed");
                    }

                    //add method errors to class errors
                    classError += methodErrors;
                }
            }
        } else {
            classError++;
            System.out.printf("%n\t=> Class does not contain the proper documentation");
        }

        //Display final message
        String yayOrNay = classError == 0 ? "All is Okay" : "Get to documenting";
        System.out.printf("%n%nDocProcessor has found %s error(s) in class %s. %s!%n",
                classError, className, yayOrNay);

        //return whether there is no class error meaning True, and there is class error meanaing false
        //we use boolean expression on logical equal sign:
        return classError == 0;
    }

    /**
     * Determines whether or not a method's return value is missing
     * @param method Method under consideration
     * @param doc annotation to check
     * @return True if method has a void return type or the annotation has non empty return description
     * */
    private static boolean hasReturnDescription(Method method, Doc doc) {
        return method.getReturnType().equals(Void.TYPE) || !doc.returnVal().isEmpty();
    }

    /**
     * Check whether or not the number of descriptions provided in the Doc annotation
     * match the number of parameters in the given method
     * @param method Method Method under consideration
     * @param doc Annotation to check
     * @return Number of description missing.
     * NOTE: this cpuld be negative if too many description are provided
     * */
    private static int getNumMissingParameters(Method method, Doc doc) {
        int numMissing = 0;
        int annotatedParameterCount = doc.parameters().length;
        int actualParameterCount = method.getParameterCount();
        //to ensure only positive number comes up from  numMissing:
        if (annotatedParameterCount<actualParameterCount){
            numMissing = actualParameterCount - annotatedParameterCount;
        }
        return numMissing; //if numMissing < 0 then it will return 0
    }
}
