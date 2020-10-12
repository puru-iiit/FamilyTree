package com.puru.family.relation.service.impl;

import java.util.List;

import com.puru.family.exception.ApplicationException;
import com.puru.family.person.IPerson;
import com.puru.family.person.IPersonKey;
import com.puru.family.relation.IRelationDetails;
import com.puru.family.relation.IRelationType;
import com.puru.family.relation.dao.IRelationDao;
import com.puru.family.relation.service.IRelationService;

public class RelationServiceImpl implements IRelationService{
	private IRelationDao relationDao;
	
	@Override
	public boolean addRelation(IPerson firstPerson, IPerson secondPerson, IRelationType relationType) throws ApplicationException{
		return relationDao.addRelation(firstPerson, secondPerson, relationType) ;
	}

	@Override
	public boolean addRelation(IPersonKey firstPersonKey, IPersonKey secondPersonKey, IRelationType relationType) throws ApplicationException{
		return relationDao.addRelation(firstPersonKey, secondPersonKey, relationType) ;
	}

	@Override
	
	public boolean updateRelation(IPersonKey firstPersonKey, IPersonKey secondPersonKey, IRelationType oldRelationType, IRelationType newRelationType)
			throws ApplicationException{
		return relationDao.updateRelation(firstPersonKey, secondPersonKey, oldRelationType,newRelationType) ;
	}

	@Override
	public boolean deleteRelation(IPersonKey firstPersonKey, IPersonKey secondPersonKey) throws ApplicationException{
		return relationDao.deleteRelation(firstPersonKey, secondPersonKey);
	}
	

	@Override
	public boolean deleteRelation(IPersonKey personKey) throws ApplicationException{
		return relationDao.deleteRelation(personKey);
	}

	
	@Override
	public List<IRelationDetails> getRelation(IPersonKey firstPersonKey, IPersonKey secondPersonKey) throws ApplicationException{
		return relationDao.getRelation(firstPersonKey, secondPersonKey) ;
	}
	
	@Override
	public IRelationDetails getRelation(IPersonKey firstPersonKey, IPersonKey secondPersonKey,IRelationType relationType) throws ApplicationException{
		return relationDao.getRelation(firstPersonKey, secondPersonKey, relationType) ;
	}

	@Override
	public List<IRelationDetails> getRelation(IPersonKey personKey) throws ApplicationException{
		return relationDao.getRelation(personKey) ;
	}

	public IRelationDao getRelationDao() {
		return relationDao;
	}

	public void setRelationDao(IRelationDao relationDao) {
		this.relationDao = relationDao;
	}

}
