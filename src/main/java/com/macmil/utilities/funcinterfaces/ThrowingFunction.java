package com.macmil.utilities.funcinterfaces;

/**
 * Represents a function that accepts one argument and produces a result with ability to throw an exception
 * This is a functional interface whose functional method is apply(Object)
 * @param <T> the type of the input to the function
 * @param <R> the type of the result of the function
 * @param <E> the type of the exception
 */
@FunctionalInterface
public interface ThrowingFunction<T, R, E extends Exception> {

    /**
     * Applies this function to the given argument
     * @param t the function argument
     * @return the function result
     * @throws E exception
     */
    R apply(T t) throws E;
}
