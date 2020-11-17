package io.finarkein.flux.transforms;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Alias extends DsOp implements SingleInOutDsTransform {
	private String alias;

	@Override
	public String toString() {
		return String.format("Alias(%s)", alias);
	}
}