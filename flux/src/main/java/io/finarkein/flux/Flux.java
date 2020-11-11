package io.finarkein.flux;

import io.finarkein.flux.impl.FluxImpl;

import java.util.Properties;

/**
 * Interface to interact with Flux platform
 */
public interface Flux {

	static Flux getOrCreate(Properties properties) {
		return FluxImpl.getOrCreate(properties);
	}

	/**
	 * @return New pipeline instance
	 */
	Pipeline create();

	/**
	 * @return FilesStore to store files which will be used to prepare Flux DataSet
	 */
	FileStore fileStore();
}
