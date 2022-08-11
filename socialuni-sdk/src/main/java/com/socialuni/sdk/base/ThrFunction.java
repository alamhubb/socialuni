/*
 * Copyright (c) 2010, 2013, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */
package com.socialuni.sdk.base;

import java.util.Objects;
import java.util.function.Function;

/**
 * Represents a function that accepts two arguments and produces a result.
 * This is the two-arity specialization of {@link Function}.
 *
 * <p>This is a <a href="package-summary.html">functional interface</a>
 * whose functional method is {@link #apply(Object, Object)}.
 *
 * @param <First> the type of the first argument to the function
 * @param <Second> the type of the second argument to the function
 * @param <Third> the type of the second argument to the function
 * @param <R> the type of the result of the function
 * @see Function
 * @since 1.8
 */
@FunctionalInterface
public interface ThrFunction<First, Second, Third, R> {

    /**
     * Applies this function to the given arguments.
     *
     * @param first  the first function argument
     * @param second the second function argument
     * @param third  the third function argument
     * @return the function result
     */
    R apply(First first, Second second, Third third);

    /**
     * Returns a composed function that first applies this function to
     * its input, and then applies the {@code after} function to the result.
     * If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function.
     *
     * @param <V>   the type of output of the {@code after} function, and of the
     *              composed function
     * @param after the function to apply after this function is applied
     * @return a composed function that first applies this function and then
     * applies the {@code after} function
     * @throws NullPointerException if after is null
     */
    default <V> ThrFunction<First, Second, Third, V> andThen(Function<? super R, ? extends V> after) {
        Objects.requireNonNull(after);
        return (First first, Second second, Third third) -> after.apply(apply(first, second, third));
    }
}
