package com.demo.common.fieldutil;

import java.io.Serializable;

@FunctionalInterface
public interface FieldFunction<T, R> extends java.util.function.Function<T, R>, Serializable {}