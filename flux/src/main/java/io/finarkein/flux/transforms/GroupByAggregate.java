package io.finarkein.flux.transforms;

import io.finarkein.flux.expr.Expr;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
public class GroupByAggregate implements SingleInOutDsTransform {
	private List<Expr> keys;
	private List<Expr> aggregates;

	public GroupByAggregate(List<Expr> keys, List<Expr> aggregates) {
		this.keys = new ArrayList<>(keys);
		this.aggregates = new ArrayList<>(aggregates);
	}

	@Override
	public String toString() {
		return String.format("GroupByAggregate(keys=%s, Aggregates=%s)", keys.toString(), aggregates.toString());
	}
}
