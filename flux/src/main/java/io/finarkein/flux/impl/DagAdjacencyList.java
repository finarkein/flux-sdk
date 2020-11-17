package io.finarkein.flux.impl;

import io.finarkein.flux.Transform;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class DagAdjacencyList implements Serializable {
	private List<Transform> nodes;
	private List<Integer> from;
	private List<Integer> to;
	private List<Integer> edgeIndex;
}
