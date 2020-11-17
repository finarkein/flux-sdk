package io.finarkein.flux.transforms;

import io.finarkein.flux.Transform;

public interface SingleInListOutDsTransform extends Transform {

	SingleInListOutDsTransform outputAs(String name);

	String outputName();
}
