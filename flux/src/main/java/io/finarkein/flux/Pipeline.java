package io.finarkein.flux;

public interface Pipeline {

	Dataset apply(ReadTransform readTransform);

	ExecutionResult run();
}
