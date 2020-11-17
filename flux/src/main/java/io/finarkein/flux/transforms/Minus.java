package io.finarkein.flux.transforms;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Minus extends DsOp implements MultiInSingleOutDsTransform {
	private List<String> columns;

	@Override
	public String toString() {
		return String.format("Minus%s", columns.toString());
	}
}