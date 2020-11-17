package io.finarkein.flux.transforms;

import io.finarkein.flux.expr.Expr;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Filter extends DsOp implements SingleInOutDsTransform {
	private Expr condition;

	@Override
	public String toString() {
		return String.format("Filter(%s)", condition);
	}
}