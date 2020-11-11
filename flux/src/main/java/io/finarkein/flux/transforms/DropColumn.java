package io.finarkein.flux.transforms;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
public class DropColumn extends DsOp implements SingleInOutDsTransform{
	private List<String> cols;

	public DropColumn(String name) {
		this.cols = new ArrayList<>(1);
		cols.add(name);
	}

	public DropColumn(List<String> names) {
		this.cols = new ArrayList<>(names);
	}
}
