package io.finarkein.flux.transforms;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Coalesce extends DsOp implements SingleInOutDsTransform {
	private int numOfPartitions;

	@Override
	public String toString() {
		return String.format("Coalesce(%s)", numOfPartitions);
	}
}