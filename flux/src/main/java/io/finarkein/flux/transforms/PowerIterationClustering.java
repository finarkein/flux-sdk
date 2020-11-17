package io.finarkein.flux.transforms;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PowerIterationClustering extends DsOp implements SingleInOutDsTransform {
	private int k;
	private int maxIterations;
	private String initializationMode;
}
