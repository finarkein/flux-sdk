package io.finarkein.flux.transforms;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Cache extends DsOp implements SingleInOutDsTransform {
	private String id;

	@Override
	public String toString() {
		return String.format("Cache(id=%s)", id);
	}
}