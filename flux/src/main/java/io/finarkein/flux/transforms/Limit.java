package io.finarkein.flux.transforms;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Limit extends DsOp implements SingleInOutDsTransform {
	private int numOfRows;

	@Override
	public String toString() {
		return String.format("Limit(%d)", numOfRows);
	}
}