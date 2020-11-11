package io.finarkein.flux.transforms;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Describe extends DsOp implements SingleInOutDsTransform {
	private List<String> columns;

	@Override
	public String toString() {
		return String.format("Describe%s", columns);
	}
}