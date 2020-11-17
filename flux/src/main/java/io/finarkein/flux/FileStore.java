package io.finarkein.flux;

/**
 * File storage interface
 */
public interface FileStore {

	/**
	 * @param fileToUpload
	 * @return UploadResponse with fail status on failure, it also contains the exception object
	 */
	UploadResponse uploadFile(String fileToUpload);

	/**
	 * @param storageRef
	 * @return true if specified File present in store
	 */
	boolean present(StorageRef storageRef);

	/**
	 * Deletes specified File from FileStore
	 * @param storageRef
	 */
	boolean delete(StorageRef storageRef);
}