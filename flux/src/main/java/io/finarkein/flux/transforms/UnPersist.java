package io.finarkein.flux.transforms;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UnPersist extends DsOp implements SingleInOutDsTransform {
	private boolean blocking;

	@Override
	public String toString() {
		return String.format("UnPersist(%s)", blocking);
	}
}