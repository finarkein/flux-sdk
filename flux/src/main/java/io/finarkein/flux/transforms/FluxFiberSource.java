package io.finarkein.flux.transforms;

import io.finarkein.flux.ReadTransform;
import io.finarkein.flux.StorageRef;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class FluxFiberSource implements ReadTransform {
	private String format;
	private StorageRef storageRef;
	private Map<String, String> options;

	public FluxFiberSource withFormat(String format) {
		this.format = format;
		return this;
	}

	public FluxFiberSource withOption(String key, String value) {
		if (options == null)
			options = new HashMap<>();
		options.put(key, value);
		return this;
	}
}

