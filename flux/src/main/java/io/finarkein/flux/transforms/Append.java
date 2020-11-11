package io.finarkein.flux.transforms;

public class Append extends DsOp implements MultiInSingleOutDsTransform {

	@Override
	public String toString() {
		return String.format("Append()");
	}
}