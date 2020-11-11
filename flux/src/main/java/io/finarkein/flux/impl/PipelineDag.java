package io.finarkein.flux.impl;

import com.google.common.graph.ElementOrder;
import com.google.common.graph.MutableValueGraph;
import com.google.common.graph.ValueGraphBuilder;
import io.finarkein.flux.ReadTransform;
import io.finarkein.flux.Transform;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class PipelineDag {
	private final MutableValueGraph<Transform, Integer> dag;

	public PipelineDag() {
		this.dag = ValueGraphBuilder
				.directed()
				.allowsSelfLoops(true)
				.nodeOrder(ElementOrder.insertion())
				.build();
	}

	public synchronized void addSource(ReadTransform source) {
		dag.addNode(source);
	}

	public synchronized void next(Transform current, Transform next) {
		if (this.dag.edgeValue(current, next).isEmpty()) {
			int numOfOuts = (int) this.dag.successors(current).stream().count();
			this.dag.addNode(next);
			this.dag.putEdgeValue(current, next, numOfOuts);
		}
	}

	public synchronized void merge(Transform left, Transform right, Transform next) {
		this.dag.addNode(next);
		this.dag.putEdgeValue(left, next, 0);
		this.dag.putEdgeValue(right, next, 1);
	}

	DagAdjacencyList toAdjacencyList() {
		List<Transform> nodes = new ArrayList<>(dag.nodes());

		Map<Transform, Integer> nodeIndexes = new HashMap<>();
		IntStream.range(0, nodes.size()).forEach(index -> nodeIndexes.put(nodes.get(index), index));

		List<Integer> from = new ArrayList<>();
		List<Integer> to = new ArrayList<>();
		List<Integer> edgeIndex = new ArrayList<>();

		IntStream.range(0, nodes.size()).forEach(index ->
				dag.successors(nodes.get(index))
						.forEach(other -> {
							from.add(index);
							to.add(nodeIndexes.get(other));
							edgeIndex.add(dag.edgeValue(nodes.get(index), other).get());
						})
		);
		return new DagAdjacencyList(nodes, from, to, edgeIndex);
	}
}
