package io.finarkein.flux.transforms;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UsingColumnJoin extends DsOp implements MultiInSingleOutDsTransform {
	private String column;

	@Override
	public String toString() {
		return String.format("UsingColumnJoin(%s, %s)", column, JoinType.INNER.name());
	}
}
