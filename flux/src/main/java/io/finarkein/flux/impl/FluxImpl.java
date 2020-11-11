package io.finarkein.flux.impl;

import io.finarkein.flux.FileStore;
import io.finarkein.flux.Flux;
import io.finarkein.flux.Pipeline;

import java.io.IOException;
import java.util.Properties;
import java.util.ServiceLoader;

public class FluxImpl implements Flux {

	private static volatile Flux flux;
	private static final Object mutex = new Object();

	private FileStore fileStore;
	private PipelineRunner runner;
	private FluxClient client;
	private static final String REMOTE = "remote";

	private FluxImpl(Properties properties) throws IOException {
		String userKey = properties.getProperty("user", "testDev");
		String runnerType = properties.getProperty("runner", REMOTE);

		client = new FluxClient();
		fileStore = new FileStoreImpl(userKey, client);

		if (REMOTE.equals(runnerType))
			runner = new RemotePipelineRunner(client);
		else if ("Local".equalsIgnoreCase(runnerType)) {
			ServiceLoader<PipelineRunner> loader = ServiceLoader.load(PipelineRunner.class);
			runner = loader.findFirst().orElseThrow(() -> new UnsupportedOperationException("Local Flux Pipeline runner is " +
					"not supported in current deployment."));
		} else
			throw new IllegalArgumentException("Error in intialization of Flux. The runner type specified in" +
					" properties is unknown: " + runnerType);
	}

	public static Flux getOrCreate(Properties properties) {
		Flux result = flux;
		if (result == null) {
			synchronized (mutex) {
				result = flux;
				if (result == null) {
					try {
						flux = result = new FluxImpl(properties);
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
				}
			}
		}
		return result;
	}

	@Override
	public Pipeline create() {
		return new PipelineImpl(runner);
	}

	@Override
	public FileStore fileStore() {
		return fileStore;
	}
}
