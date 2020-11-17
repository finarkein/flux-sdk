package io.finarkein.flux;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(fluent = true)
@AllArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonSerialize
public class UploadResponse implements Serializable {
	private final StorageRef storageRef;
	private final boolean isSuccess;
	private final String message;
	private final Exception exception;

	public static UploadResponse fail(String message, Exception exp) {
		return new UploadResponse(null, false, message, exp);
	}

	public static UploadResponse success(StorageRef ref) {
		return new UploadResponse(ref, true, null, null);
	}
}
