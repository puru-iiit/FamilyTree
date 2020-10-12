package com.puru.family.person.dao;

import java.util.List;

import com.puru.family.exception.ApplicationException;
import com.puru.family.person.IPerson;
import com.puru.family.person.IPersonKey;

public interface IPersonDao {
	public int addPerson(IPerson person) throws ApplicationException;
	public int updatePerson(IPerson person) throws ApplicationException;
	public int deletePerson(IPersonKey personKey) throws ApplicationException;
	public List<IPerson> getAllPerson(List<IPersonKey> personKeyList) throws ApplicationException;
	public IPerson getPerson(IPersonKey personKey) throws ApplicationException;
}
