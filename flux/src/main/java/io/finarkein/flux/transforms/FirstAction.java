package io.finarkein.flux.transforms;

public class FirstAction extends DsAction implements SingleInValueOutDsTransform {
	private String name;

	@Override
	public FirstAction outputAs(String name) {
		this.name = name;
		return this;
	}

	@Override
	public String outputName() {
		return name;
	}

	@Override
	public String toString() {
		return "First()";
	}
}