package io.finarkein.flux.transforms;

import io.finarkein.flux.Transform;

public interface SingleInValueOutDsTransform extends Transform {

	SingleInValueOutDsTransform outputAs(String name);

	String outputName();

}
