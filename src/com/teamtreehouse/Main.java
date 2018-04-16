package com.teamtreehouse;

import com.teamtreehouse.docgen.DocProcessor;

public class Main {

    /**
     * This class holds the main method that initialize the whole program
     * */
    public static void main(String[] args) {
	// get class object (at the moment Class called MathUtil is not yet available
        DocProcessor.process(MathUtils.class);
       //TODO: moo: we will create the MathUtils Class later
    }
}
