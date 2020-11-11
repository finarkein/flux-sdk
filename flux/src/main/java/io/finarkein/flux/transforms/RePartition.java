package io.finarkein.flux.transforms;

import io.finarkein.flux.expr.Expr;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RePartition extends DsOp implements SingleInOutDsTransform {
	private int numOfPartitions;
	private List<Expr> exprs;

	@Override
	public String toString() {
		return String.format("RePartition(%d, %s)", numOfPartitions, exprs);
	}
}