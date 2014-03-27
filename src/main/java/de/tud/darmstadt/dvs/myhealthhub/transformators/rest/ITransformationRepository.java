package de.tud.darmstadt.dvs.myhealthhub.transformators.rest;

public interface ITransformationRepository {

	public Transformation getB64EcodedTransformation(String[] sourceEvents, String destinanationEvent);
}
