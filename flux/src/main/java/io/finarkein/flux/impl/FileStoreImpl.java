package io.finarkein.flux.impl;

import io.finarkein.flux.FileStore;
import io.finarkein.flux.StorageRef;
import io.finarkein.flux.UploadResponse;
import lombok.Data;

import java.util.Objects;

@Data
class FileStoreImpl implements FileStore {

	private final String userKey;
	private final FluxClient fluxClient;

	FileStoreImpl(String userKey, FluxClient fluxClient) {
		this.userKey = userKey;
		this.fluxClient = fluxClient;
	}

	@Override
	public UploadResponse uploadFile(String fileToUpload) {
		try {
			return fluxClient.uploadFile(fileToUpload);
		} catch (Exception e) {
			return UploadResponse.fail(e.getMessage(), e);
		}
	}

	@Override
	public boolean present(StorageRef storageRef) {
		Objects.requireNonNull(storageRef, "storageRef cannot be null");
		return fluxClient.isFilePresent(storageRef);
	}

	@Override
	public boolean delete(StorageRef storageRef) {
		Objects.requireNonNull(storageRef, "storageRef cannot be null");
		return fluxClient.deleteFile(storageRef);
	}
}
