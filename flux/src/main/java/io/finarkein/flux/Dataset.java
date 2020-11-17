package io.finarkein.flux;

/**
 * Data structure similar SQL table, data in this data structure can be manipulated using provided transformations.<br>
 * See implementations of {@link Transform} for more information.
 */
public interface Dataset {

	Dataset apply(Transform transform);

	Pipeline pipeline();

}
