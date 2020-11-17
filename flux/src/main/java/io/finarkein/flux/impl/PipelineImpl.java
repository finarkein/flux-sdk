package io.finarkein.flux.impl;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import io.finarkein.flux.Dataset;
import io.finarkein.flux.ExecutionResult;
import io.finarkein.flux.Pipeline;
import io.finarkein.flux.ReadTransform;

public class PipelineImpl implements Pipeline {
	protected final PipelineDag dag;
	protected final PipelineRunner runner;

	PipelineImpl(PipelineRunner runner) {
		this.runner = runner;
		this.dag = new PipelineDag();
	}

	public PipelineDag dag() {
		return dag;
	}

	@Override
	public Dataset apply(ReadTransform readTransform) {
		dag.addSource(readTransform);
		return new DatasetImpl(this, readTransform);
	}

	@Override
	public ExecutionResult run() {
		//validate dag
		DagAdjacencyList adjacencyList = dag.toAdjacencyList();

		try (Output output = new Output(10000)) {
			Kryo kryo = new Kryo();
			kryo.writeClassAndObject(output, adjacencyList);
			byte[] buffer = output.getBuffer();

			//Implement http based PIPELINE_RUNNER
			byte[] execResultBytes = runner.runPipeline(buffer);


			Input input = new Input(execResultBytes);
			return kryo.readObject(input, ExecutionResult.class);
		}
	}
}
