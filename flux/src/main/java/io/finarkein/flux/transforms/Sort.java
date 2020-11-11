package io.finarkein.flux.transforms;

import io.finarkein.flux.expr.Expr;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
public class Sort extends DsOp implements SingleInOutDsTransform {
	private List<Expr> exprs;

	public Sort(List<Expr> exprs) {
		this.exprs = new ArrayList<>(exprs);
	}


	@Override
	public String toString() {
		return String.format("Sort%s", exprs.toString());
	}
}