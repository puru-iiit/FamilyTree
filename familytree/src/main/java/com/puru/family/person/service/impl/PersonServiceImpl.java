package com.puru.family.person.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puru.family.exception.ApplicationException;
import com.puru.family.person.IPerson;
import com.puru.family.person.IPersonKey;
import com.puru.family.person.dao.IPersonDao;
import com.puru.family.person.service.IPersonService;

public class PersonServiceImpl implements IPersonService{
	final private static Logger log =  LoggerFactory.getLogger(PersonServiceImpl.class);
	private IPersonDao personDao ;
	
	@Override
	public int addPerson(IPerson person) throws ApplicationException{
		log.info("addPerson started");
		return personDao.addPerson(person);
	}

	@Override
	public int updatePerson(IPerson person) throws ApplicationException{
		log.info("updatePerson started");
		return personDao.updatePerson(person);
		
	}

	@Override
	public int deletePerson(IPersonKey personKey) throws ApplicationException{
		log.info("deletePerson started");
		return personDao.deletePerson(personKey);
	}

	@Override
	public List<IPerson> getAllPerson(List<IPersonKey> personKeyList) throws ApplicationException{
		log.info("getAllPerson started");
		return personDao.getAllPerson(personKeyList);
	}

	@Override
	public IPerson getPerson(IPersonKey personKey) throws ApplicationException{
		log.info("getPerson started");
		return personDao.getPerson(personKey);
	}

	public IPersonDao getPersonDao() {
		return personDao;
	}

	public void setPersonDao(IPersonDao personDao) {
		this.personDao = personDao;
	}

}
