package com.puru.family.relation;

import java.io.Serializable;

import com.puru.family.person.IPersonKey;

public interface IRelationDetails extends Serializable{
	public IPersonKey getFirstPersonKey();
	public IPersonKey getSecondPersonKey();
	public IRelationType getFirstToSecondPersonRelationType();
	public IRelationType getSecondToFirstPersonRelationType();
	
}
