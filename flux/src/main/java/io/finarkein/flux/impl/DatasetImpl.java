package io.finarkein.flux.impl;

import io.finarkein.flux.Dataset;
import io.finarkein.flux.Pipeline;
import io.finarkein.flux.Transform;

public class DatasetImpl implements Dataset {
	private final PipelineImpl pipeline;
	private final PipelineDag dag;
	private final Transform current;

	public DatasetImpl(PipelineImpl pipeline, Transform current) {
		this.pipeline = pipeline;
		this.dag = pipeline.dag();
		this.current = current;
	}

	public Transform transform() {
		return current;
	}

	@Override
	public Dataset apply(Transform transform) {
		dag.next(current, transform);
		return new DatasetImpl(pipeline, transform);
	}

	@Override
	public Pipeline pipeline() {
		return pipeline;
	}
}
