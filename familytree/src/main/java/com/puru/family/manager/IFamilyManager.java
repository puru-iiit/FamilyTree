package com.puru.family.manager;

import java.util.List;

import com.puru.family.address.IAddress;
import com.puru.family.address.IAddressKey;
import com.puru.family.exception.ApplicationException;
import com.puru.family.person.IPerson;
import com.puru.family.person.IPersonKey;
import com.puru.family.relation.IRelationDetails;
import com.puru.family.relation.IRelationType;

public interface IFamilyManager {

	public int addPerson(IPerson person) throws ApplicationException;

	public int updatePerson(IPerson person) throws ApplicationException;

	public int deletePerson(IPersonKey personKey) throws ApplicationException;

	public List<IPerson> getAllPerson(List<IPersonKey> personKeyList) throws ApplicationException;

	public IPerson getPerson(IPersonKey personKey) throws ApplicationException;

	public int addAddress(IAddress address, IPersonKey personKey) throws ApplicationException;

	public int updateAddress(IAddress address) throws ApplicationException;

	public int deleteAddress(IAddressKey addressKey) throws ApplicationException;

	public List<IAddress> getAddress(IPersonKey personKey) throws ApplicationException;

	public boolean addRelation(IPerson firstPerson, IPerson secondPerson, IRelationType relationType)
			throws ApplicationException;

	public boolean addRelation(IPersonKey firstPersonKey, IPersonKey secondPersonKey, IRelationType relationType)
			throws ApplicationException;

	public boolean updateRelation(IPersonKey firstPersonKey, IPersonKey secondPersonKey, IRelationType oldRelationType,
			IRelationType newRelationType) throws ApplicationException;

	public boolean deleteRelation(IPersonKey firstPersonKey, IPersonKey secondPersonKey) throws ApplicationException;
	public boolean deleteRelation(IPersonKey personKey) throws ApplicationException;

	public List<IRelationDetails> getRelation(IPersonKey firstPersonKey, IPersonKey secondPersonKey)
			throws ApplicationException;

	public IRelationDetails getRelation(IPersonKey firstPersonKey, IPersonKey secondPersonKey,
			IRelationType relationType) throws ApplicationException;

	public List<IRelationDetails> getRelation(IPersonKey firstPersonKey) throws ApplicationException;

	public IPerson getFamilyTree(IPersonKey personKey) throws ApplicationException;

}
