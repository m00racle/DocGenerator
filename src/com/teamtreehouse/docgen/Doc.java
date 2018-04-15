package com.teamtreehouse.docgen;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value = RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface Doc {
    /**
     * Starting the Annotation - Declaration and Retention
     * - we will use Reflection to analyze the presence of our @Doc on classes
     * and methods. In order to use Reflection during the analysis the
     * Retention policy must be set to RUNTIME (to ensure this @Doc kept available
     * in the JVM runtime)
     *
     * - when we want to declare an interface there are 3 things to do:
     *   > Declare the interface
     *   > Specify what are called retention and targets
     *   > Add elements and/or attributes
     *
     * - Next we will specify where this annotation will target to operate
     *   in our case it will be class (type) and method.
     *   That is why we use @Target({ElementType.TYPE, ElementType.METHOD})
     *
     *   This is will declare elements names as zero parameter non-void methods like below
     *   we don't have to add public since all interface methods are public by default
     *   since this is an interface meaning we will define methods with no returns, although
     *   the dclaration behave more like field which values are set when annotation is added
     *   to a class or method. Therefore we can specify the default value of each element which
     *   also saying that the anotation when added to a class or method doesn't have to include
     *   value for this element*/

    //Description of class or method
   String description() default "";

   //Description of parameters, if annotated lement is a method & has parameters
    String[] parameters() default {};

    //Description of return value, if annotated element is a method & is not void
    String returnVal() default "";
}
