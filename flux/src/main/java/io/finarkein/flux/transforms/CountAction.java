package io.finarkein.flux.transforms;

public class CountAction extends DsAction implements SingleInValueOutDsTransform {
	private String name;

	@Override
	public CountAction outputAs(String name) {
		this.name = name;
		return this;
	}

	@Override
	public String outputName() {
		return name;
	}

	@Override
	public String toString() {
		return "Count()";
	}
}