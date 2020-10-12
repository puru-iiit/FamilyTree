package com.puru.family.relation;

import java.io.Serializable;

public interface IRelationType extends Serializable {
	public int getRelationId();
	public String getRelationCode();
	public String getRelationDesc();
}
