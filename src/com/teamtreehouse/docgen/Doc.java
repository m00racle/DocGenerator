package com.teamtreehouse.docgen;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(value = RetentionPolicy.RUNTIME)
public @interface Doc {
    /**
     * Starting the Annotation - Declaration and Retention
     * - we will use Reflection to analyze the presence of our @Doc on classes
     * and methods. In order to use Reflection during the analysis the
     * @Retention policy must be set to RUNTIME (to ensure this @Doc kept available
     * in the JVM runtime)
     *
     * - when we want to declare an interface there are 3 things to do:
     *   > Declare the interface
     *   > Specify what are called retention and targets
     *   > Add elements and/or attributes
     *
     * */

    //Description of class or method
}
