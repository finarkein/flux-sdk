package io.finarkein.flux.transforms;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class DropDuplicates extends DsOp implements SingleInOutDsTransform {
	private List<String> cols;

	@Override
	public String toString() {
		return String.format("DropDuplicates%s", cols.toString());
	}
}