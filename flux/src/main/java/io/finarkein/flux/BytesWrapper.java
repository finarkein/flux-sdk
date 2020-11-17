package io.finarkein.flux;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static io.finarkein.flux.Functions.bytesToStr;
import static io.finarkein.flux.Functions.gzipCompression;
import static io.finarkein.flux.Functions.gzipDecompression;
import static io.finarkein.flux.Functions.strToBytes;

/**
 * Wrapper created for sending/receiving the byte[] from server
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonSerialize
public class BytesWrapper {
	private String encodedBytes;

	public BytesWrapper(byte[] bytesToWrap) {
		encodedBytes = gzipCompression.andThen(bytesToStr).apply(bytesToWrap);
	}

	public byte[] getBytes() {
		return strToBytes.andThen(gzipDecompression).apply(encodedBytes);
	}
}
