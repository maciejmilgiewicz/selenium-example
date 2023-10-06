package com.macmil.utilities.funcinterfaces;

import java.util.function.Function;

/**
 * A wrapper class for functional interfaces
 */
public class FunctionalInterfaceWrapper {

    /**
     * A wrapper method for a ThrowingFunction functional interface
     * This is a workaround for an issue of checked exception handling within a Stream
     * @param throwingFunction an expression using ThrowingFunction functional interface
     * @return Function functional interface
     * @param <T> the type of the input to the function
     * @param <R> the type of the result of the function
     */
    public static <T, R> Function<T, R> throwingFunctionWrapper(ThrowingFunction<T, R, Exception> throwingFunction) {
        return i -> {
            try {
                return throwingFunction.apply(i);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }
}
