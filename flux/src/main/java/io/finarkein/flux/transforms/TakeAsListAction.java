package io.finarkein.flux.transforms;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class TakeAsListAction extends DsAction implements SingleInListOutDsTransform {
	private int numOfRows;
	private String name;

	public TakeAsListAction(int numOfRows) {
		this.numOfRows = numOfRows;
	}

	@Override
	public TakeAsListAction outputAs(String name) {
		this.name = name;
		return this;
	}

	@Override
	public String outputName() {
		return name;
	}

	@Override
	public String toString() {
		return String.format("TakeAsList(%d)", numOfRows);
	}
}