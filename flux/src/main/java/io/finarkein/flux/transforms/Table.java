package io.finarkein.flux.transforms;

import io.finarkein.flux.ReadTransform;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Table implements ReadTransform {
	private String tableName;
	private List<Object[]> rows;
}
