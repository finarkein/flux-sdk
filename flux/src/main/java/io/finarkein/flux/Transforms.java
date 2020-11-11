package io.finarkein.flux;

import io.finarkein.flux.transforms.Join;
import io.finarkein.flux.transforms.JoinType;
import io.finarkein.flux.transforms.Minus;
import io.finarkein.flux.transforms.Union;
import io.finarkein.flux.impl.DatasetImpl;
import io.finarkein.flux.impl.PipelineDag;
import io.finarkein.flux.impl.PipelineImpl;
import io.finarkein.flux.expr.Expr;
import io.finarkein.flux.util.ExceptionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Transforms {

	private static Transform tf(Dataset ds) {
		return ((DatasetImpl) ds).transform();
	}

	private static void validate(Dataset first, Dataset second, String operation) {
		Objects.requireNonNull(first, "First Dataset for " + operation + " should not be null.");
		Objects.requireNonNull(second, "Second Dataset for " + operation + " should not be null.");
		if (!first.pipeline().equals(second.pipeline()))
			throw ExceptionUtils.illegalState("First and Second Datasets for " + operation + " should belong to" +
					" same Pipeline instance");
	}

	private static Dataset mergeInDag(Dataset ds1, Dataset ds2, Transform transform) {
		PipelineImpl pipeline = (PipelineImpl) ds1.pipeline();
		PipelineDag dag = pipeline.dag();
		dag.merge(tf(ds1), tf(ds2), transform);
		return new DatasetImpl(pipeline, transform);
	}

	public static Dataset join(Dataset left, Dataset right, Expr joinCondition, JoinType joinType) {
		validate(left, right, "Join");
		Objects.requireNonNull(joinType, "JoinType for Join should not be null.");
		if (joinType != JoinType.CROSS)
			Objects.requireNonNull(joinCondition, "Join condition for Join should not be null for join type " + joinType.name());
		Join join = new Join(joinType, joinCondition);
		return mergeInDag(left, right, join);
	}

	public static Dataset join(Dataset left, Dataset right, Expr joinCondition) {
		return join(left, right, joinCondition, JoinType.INNER);
	}

	public static Dataset crossJoin(Dataset left, Dataset right) {
		return join(left, right, null, JoinType.CROSS);
	}

	public static Dataset minus(Dataset ds1, Dataset ds2, List<String> columnNames) {
		validate(ds1, ds2, "Minus");
		Minus minus = new Minus(columnNames);
		return mergeInDag(ds1, ds2, minus);
	}

	public static Dataset minus(Dataset ds1, Dataset ds2) {
		return minus(ds1, ds2, Collections.emptyList());
	}

	public static Dataset union(Dataset ds1, Dataset ds2, List<String> columnNames) {
		validate(ds1, ds2, "Union");
		Union union = new Union(columnNames);
		return mergeInDag(ds1, ds2, union);
	}

	public static Dataset union(Dataset ds1, Dataset ds2) {
		return union(ds1, ds2, Collections.emptyList());
	}

}