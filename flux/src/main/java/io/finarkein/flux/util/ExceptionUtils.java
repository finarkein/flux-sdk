package io.finarkein.flux.util;

public class ExceptionUtils {

	public static IllegalArgumentException illegalArgument(String message) {
		return new IllegalArgumentException(message);
	}

	public static IllegalStateException illegalState(String message) {
		return new IllegalStateException(message);
	}

	public static UnsupportedOperationException unsupportedExpr(String message) {
		return new UnsupportedOperationException(message);
	}
}

