package io.finarkein.flux.impl;

import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;

class RemotePipelineRunner implements PipelineRunner {
	private final FluxClient client;

	public RemotePipelineRunner(FluxClient client) {
		this.client = client;
	}

	@Override
	public byte[] runPipeline(byte[] pipeline) {
		try {
			return client.pipelineRun(pipeline);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}

