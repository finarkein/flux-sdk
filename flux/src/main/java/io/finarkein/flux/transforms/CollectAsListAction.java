package io.finarkein.flux.transforms;

public class CollectAsListAction extends DsAction implements SingleInListOutDsTransform {
	private String name;

	@Override
	public CollectAsListAction outputAs(String name) {
		this.name = name;
		return this;
	}

	@Override
	public String outputName() {
		return name;
	}

	@Override
	public String toString() {
		return "CollectAsList()";
	}
}