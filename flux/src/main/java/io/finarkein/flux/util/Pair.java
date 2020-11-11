package io.finarkein.flux.util;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import java.util.Map;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
public class Pair<T1, T2> implements Map.Entry<T1, T2> {
	public final T1 left;
	public final T2 right;

	public static <T1, T2> Pair<T1, T2> of(T1 left, T2 right) {
		return new Pair<>(left, right);
	}

	public static <K, V> Pair<K, V> of(Map.Entry<K, V> entry) {
		return of(entry.getKey(), entry.getValue());
	}

	public String toString() {
		return "<" + left + ", " + right + ">";
	}

	@Override
	public T1 getKey() {
		return left;
	}

	@Override
	public T2 getValue() {
		return right;
	}

	@Override
	public T2 setValue(T2 value) {
		throw new UnsupportedOperationException();
	}

}