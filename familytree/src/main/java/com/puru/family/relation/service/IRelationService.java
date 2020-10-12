package com.puru.family.relation.service;

import java.util.List;

import com.puru.family.exception.ApplicationException;
import com.puru.family.person.IPerson;
import com.puru.family.person.IPersonKey;
import com.puru.family.relation.IRelationDetails;
import com.puru.family.relation.IRelationType;

public interface IRelationService {
	public boolean addRelation(IPerson firstPerson, IPerson secondPerson, IRelationType relationType)
			throws ApplicationException;

	public boolean addRelation(IPersonKey firstPersonKey, IPersonKey secondPersonKey, IRelationType relationType)
			throws ApplicationException;

	public boolean updateRelation(IPersonKey firstPersonKey, IPersonKey secondPersonKey, IRelationType oldRelationType,
			IRelationType newRelationType) throws ApplicationException;

	public boolean deleteRelation(IPersonKey firstPersonKey, IPersonKey secondPersonKey) throws ApplicationException;
	
	public boolean deleteRelation(IPersonKey firstPersonKey) throws ApplicationException;
	
	public List<IRelationDetails> getRelation(IPersonKey firstPersonKey, IPersonKey secondPersonKey)
			throws ApplicationException;

	public IRelationDetails getRelation(IPersonKey firstPersonKey, IPersonKey secondPersonKey,
			IRelationType relationType) throws ApplicationException;

	public List<IRelationDetails> getRelation(IPersonKey personKey) throws ApplicationException;

}
