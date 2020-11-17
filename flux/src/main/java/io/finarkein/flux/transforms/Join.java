package io.finarkein.flux.transforms;

import io.finarkein.flux.expr.Expr;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Join extends DsOp implements MultiInSingleOutDsTransform {
	private JoinType joinType;
	private Expr joinCondition;

	@Override
	public String toString() {
		return String.format("Join(%s, %s)", joinCondition, joinType.toString());
	}
}