package com.puru.family.relation.model;

import com.puru.family.person.IPersonKey;
import com.puru.family.relation.IRelationDetails;
import com.puru.family.relation.IRelationType;

public class RelationDetails implements IRelationDetails {

	private static final long serialVersionUID = 1L;
	
	private IPersonKey firstPersonKey;
	private IPersonKey secondPersonKey;
	
	private IRelationType firstToSecondRelationType;
	private IRelationType secondToFirstRelationType;
	
	@Override
	public IPersonKey getFirstPersonKey() {
		return this.firstPersonKey;
	}
	@Override
	public IPersonKey getSecondPersonKey() {
		return this.secondPersonKey;
	}
	@Override
	public IRelationType getFirstToSecondPersonRelationType() {
		return this.firstToSecondRelationType;
	}
	@Override
	public IRelationType getSecondToFirstPersonRelationType() {
		return this.secondToFirstRelationType;
	}
	public IRelationType getFirstToSecondRelationType() {
		return firstToSecondRelationType;
	}
	public IRelationType getSecondToFirstRelationType() {
		return secondToFirstRelationType;
	}
	public void setFirstPersonKey(IPersonKey firstPersonKey) {
		this.firstPersonKey = firstPersonKey;
	}
	public void setSecondPersonKey(IPersonKey secondPersonKey) {
		this.secondPersonKey = secondPersonKey;
	}
	public void setFirstToSecondRelationType(IRelationType firstToSecondRelationType) {
		this.firstToSecondRelationType = firstToSecondRelationType;
	}
	public void setSecondToFirstRelationType(IRelationType secondToFirstRelationType) {
		this.secondToFirstRelationType = secondToFirstRelationType;
	}
	
	
}
