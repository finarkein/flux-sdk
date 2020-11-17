package io.finarkein.flux.transforms;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
public enum JoinType {
	INNER(Collections.singletonList("inner")),
	LEFT_OUTER(Arrays.asList("left", "left_outer")),
	RIGHT_OUTER(Arrays.asList("right", "right_outer")),
	FULL_OUTER(Arrays.asList("outer", "full", "full_outer", "fullouter")),
	LEFT_SEMI(Collections.singletonList("left_semi")),
	LEFT_ANTI(Collections.singletonList("left_anti")),
	CROSS(Arrays.asList("cross_join", "cross"));

	private final List<String> names;

	public static JoinType getJoinType(String name) {
		if (name == null || name.isEmpty())
			return null;
		name = name.toLowerCase().trim();
		for (JoinType type : JoinType.values()) {
			if (type.names.contains(name))
				return type;
		}
		return null;
	}
}