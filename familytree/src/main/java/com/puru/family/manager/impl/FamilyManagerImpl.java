package com.puru.family.manager.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puru.family.address.IAddress;
import com.puru.family.address.IAddressKey;
import com.puru.family.address.service.IAddressService;
import com.puru.family.exception.ApplicationException;
import com.puru.family.manager.IFamilyManager;
import com.puru.family.person.IPerson;
import com.puru.family.person.IPersonKey;
import com.puru.family.person.service.IPersonService;
import com.puru.family.relation.IRelation;
import com.puru.family.relation.IRelationDetails;
import com.puru.family.relation.IRelationType;
import com.puru.family.relation.service.IRelationService;

public class FamilyManagerImpl implements IFamilyManager{
	
	final private static Logger log =  LoggerFactory.getLogger(FamilyManagerImpl.class);
	
	/* Can  be configurable at property/DB/Spring config level*/
	private int MAX_ALLOWD_DEPTH =7;
	
	private IPersonService personService;
	private IRelationService relationService;
	private IAddressService addressService;
	private IRelation relation;
	
	@Override
	public IPerson getFamilyTree(IPersonKey personKey) throws ApplicationException{
		
		log.info("getFamilyTree started"); 
		IPerson person = personService.getPerson(personKey);
		
		List<IAddress> addressList = addressService.getAddress(personKey);
		person.setAddresses(addressList);
		
		log.info("fetching relations.."); 
		List<IRelationDetails> relationDetailsList =relationService.getRelation(personKey);
		
		log.info("Populating First level relations..");
		this.populateFirstLevelRelatives(person,relationDetailsList,null);		
		
		int depth =1;
		log.info("Populating next level relations recursively max level["+MAX_ALLOWD_DEPTH+"]"); 
		this.populateRelativeRelatives(person,depth);
		
		log.info("getFamilyTree ended.."); 
		return person;
	}
	
	private void populateRelativeRelatives(IPerson person,int depth) throws ApplicationException{
		
		if(depth<=MAX_ALLOWD_DEPTH){
			
			List<IPerson> spouseList = person.getRelatives(relation.getRelation(IRelation.SPOUSE_RELATION_ID));
			if(spouseList!=null && !spouseList.isEmpty()){
				for(IPerson spousePerson :spouseList){
					List<IRelationDetails> relationDetailsList =relationService.getRelation(spousePerson.getPersonKey());
					if(relationDetailsList!=null && !relationDetailsList.isEmpty()){
						this.populateFirstLevelRelatives(spousePerson,relationDetailsList,person);		
						this.populateRelativeRelatives(spousePerson,depth++);
					}
				}
			}
			
			List<IPerson> fatherList = person.getRelatives(relation.getRelation(IRelation.FATHER_RELATION_ID));
			if(fatherList!=null && !fatherList.isEmpty()){
				for(IPerson fatherPerson :fatherList){
					List<IRelationDetails> relationDetailsList =relationService.getRelation(fatherPerson.getPersonKey());
					if(relationDetailsList!=null && !relationDetailsList.isEmpty()){
						this.populateFirstLevelRelatives(fatherPerson,relationDetailsList,person);		
						this.populateRelativeRelatives(fatherPerson,depth++);
					}
				}
			}
			
			List<IPerson> motherList = person.getRelatives(relation.getRelation(IRelation.MOTHER_RELATION_ID));
			if(motherList!=null && !motherList.isEmpty()){
				for(IPerson motherPerson :motherList){
					List<IRelationDetails> relationDetailsList =relationService.getRelation(motherPerson.getPersonKey());
					if(relationDetailsList!=null && !relationDetailsList.isEmpty()){
						this.populateFirstLevelRelatives(motherPerson,relationDetailsList,person);		
						this.populateRelativeRelatives(motherPerson,depth++);
					}
				}
			}
			
			List<IPerson> grandParentsList = person.getRelatives(relation.getRelation(IRelation.GRANDPARENTS_RELATION_ID));
			if(grandParentsList!=null && !grandParentsList.isEmpty()){
				for(IPerson grandParent :grandParentsList){
					List<IRelationDetails> relationDetailsList =relationService.getRelation(grandParent.getPersonKey());
					if(relationDetailsList!=null && !relationDetailsList.isEmpty()){
						this.populateFirstLevelRelatives(grandParent,relationDetailsList,person);
						this.populateRelativeRelatives(grandParent,depth++);
					}
				}
			}
			
			List<IPerson> siblingsList = person.getRelatives(relation.getRelation(IRelation.SIBLINGS_RELATION_ID));
			if(siblingsList!=null && !siblingsList.isEmpty()){
				for(IPerson sibling :siblingsList){
					List<IRelationDetails> relationDetailsList =relationService.getRelation(sibling.getPersonKey());
					if(relationDetailsList!=null && !relationDetailsList.isEmpty()){
						this.populateFirstLevelRelatives(sibling,relationDetailsList,person);
						this.populateRelativeRelatives(sibling,depth++);
					}
				}
			}
		
			List<IPerson> othersList = person.getRelatives(relation.getRelation(IRelation.OTHERS_RELATION_ID));
			if(othersList!=null && !othersList.isEmpty()){
				for(IPerson other :othersList){
					List<IRelationDetails> relationDetailsList =relationService.getRelation(other.getPersonKey());
					if(relationDetailsList!=null && !relationDetailsList.isEmpty()){
						this.populateFirstLevelRelatives(other,relationDetailsList,person);
						this.populateRelativeRelatives(other,depth++);
					}
				}
			}
			
			List<IPerson> nonesList = person.getRelatives(relation.getRelation(IRelation.NONES_RELATION_ID));
			if(nonesList!=null && !nonesList.isEmpty()){
				for(IPerson none :nonesList){
					List<IRelationDetails> relationDetailsList =relationService.getRelation(none.getPersonKey());
					if(relationDetailsList!=null && !relationDetailsList.isEmpty()){
						this.populateFirstLevelRelatives(none,relationDetailsList,person);
						this.populateRelativeRelatives(none,depth++);
					}
				}
			}
		}else{
			//It signifies that there are still more relative to load if its set to true
			person.setIsMoreRelativeToLoad(true);
		}
	}
	
	private void populateFirstLevelRelatives(IPerson person, List<IRelationDetails> relationDetailsList, IPerson superPerson) throws ApplicationException{
		
		log.info("populateFirstLevelRelatives started"); 
		//Populating self
		List<IPerson> list =null;// new ArrayList<IPerson>(1);
		/*list.add(person);
		person.setRelatives(relation.getRelation(IRelation.ME_RELATION_ID),list );*/
		
		//Populating super Person
		/*if(superPerson==null){
			list = Collections.<IPerson>emptyList();
		}else{
			list = new ArrayList<IPerson>(1);
			list.add(superPerson);
		}
		person.setRelatives(relation.getRelation(IRelation.ME_SUPER_RELATION_ID), list);*/
		
		//Populating  Person's SPOUSE
		list = this.fetchRelativePersons(relation.getRelation(IRelation.SPOUSE_RELATION_ID),relationDetailsList);
		if(list!=null && !list.isEmpty()){
			person.setRelatives(relation.getRelation(IRelation.SPOUSE_RELATION_ID),list);
		}
		
		//Populating  Person's FATHER
		list = this.fetchRelativePersons(relation.getRelation(IRelation.FATHER_RELATION_ID),relationDetailsList);
		if(list!=null && !list.isEmpty()){
			person.setRelatives(relation.getRelation(IRelation.FATHER_RELATION_ID),list);
		}
		
		//Populating  Person's MOTHER
		list = this.fetchRelativePersons(relation.getRelation(IRelation.MOTHER_RELATION_ID),relationDetailsList);
		if(list!=null && !list.isEmpty()){
			person.setRelatives(relation.getRelation(IRelation.MOTHER_RELATION_ID),list);
		}
		
		//Populating  Person's GRANDPARENTS
		list = this.fetchRelativePersons(relation.getRelation(IRelation.GRANDPARENTS_RELATION_ID),relationDetailsList);
		if(list!=null && !list.isEmpty()){
			person.setRelatives(relation.getRelation(IRelation.GRANDPARENTS_RELATION_ID),list);
		}
		
		//Populating  Person's SIBLINGS
		list = this.fetchRelativePersons(relation.getRelation(IRelation.SIBLINGS_RELATION_ID),relationDetailsList);
		if(list!=null && !list.isEmpty()){
			person.setRelatives(relation.getRelation(IRelation.SIBLINGS_RELATION_ID),list);
		}

		//Populating  Person's OTHERS
		list = this.fetchRelativePersons(relation.getRelation(IRelation.OTHERS_RELATION_ID),relationDetailsList);
		if(list!=null && !list.isEmpty()){
			person.setRelatives(relation.getRelation(IRelation.OTHERS_RELATION_ID),list);
		}

		//Populating  Person's NONES
		list = this.fetchRelativePersons(relation.getRelation(IRelation.NONES_RELATION_ID),relationDetailsList);
		if(list!=null && !list.isEmpty()){
			person.setRelatives(relation.getRelation(IRelation.NONES_RELATION_ID),list);
		}
		log.info("populateFirstLevelRelatives ended"); 		

	}
	
	private List<IPerson> fetchRelativePersons(IRelationType relationType, List<IRelationDetails> relationDetailsList) throws ApplicationException{
		
		List<IPersonKey> personKeyList = this.filterRelative(relationType,relationDetailsList);
		List<IPerson> list = personService.getAllPerson(personKeyList);
				
		return list;
	}
	
	private List<IPersonKey> filterRelative(IRelationType relationType, List<IRelationDetails> relationDetailsList){
		
		if(relationDetailsList==null || relationDetailsList.isEmpty()){
			return Collections.<IPersonKey>emptyList();
		}
		List<IPersonKey> personKeyList = new ArrayList<IPersonKey>();
		for(IRelationDetails relationDetails:relationDetailsList){
			if(relationDetails!=null && relationType.getRelationId()==relationDetails.getFirstToSecondPersonRelationType().getRelationId()){
				personKeyList.add(relationDetails.getSecondPersonKey());
			}
		}
		return personKeyList; 
		
	}
	
	@Override
	public int addPerson(IPerson person) throws ApplicationException{
		return personService.addPerson(person);
	}

	@Override
	public int updatePerson(IPerson person) throws ApplicationException{
		return personService.updatePerson(person);
	}

	@Override
	public int deletePerson(IPersonKey personKey) throws ApplicationException{
		return personService.deletePerson(personKey);
	}
	
	@Override
	public List<IPerson> getAllPerson(List<IPersonKey> personKeyList) throws ApplicationException{
		return personService.getAllPerson(personKeyList);
	}

	@Override
	public IPerson getPerson(IPersonKey personKey) throws ApplicationException{
		return personService.getPerson(personKey);
	}

	@Override
	public boolean addRelation(IPerson firstPerson, IPerson secondPerson, IRelationType relationType) throws ApplicationException{
		return relationService.addRelation(firstPerson, secondPerson, relationType);
	}

	@Override
	public boolean addRelation(IPersonKey firstPersonKey, IPersonKey secondPersonKey, IRelationType relationType) throws ApplicationException{
		return relationService.addRelation(firstPersonKey, secondPersonKey, relationType);
	}


	@Override
	public boolean updateRelation(IPersonKey firstPersonKey, IPersonKey secondPersonKey, IRelationType oldRelationType,
			IRelationType newRelationType) throws ApplicationException {
		return relationService.updateRelation(firstPersonKey, secondPersonKey, oldRelationType,newRelationType);
	}

	@Override
	public boolean deleteRelation(IPersonKey personKey) throws ApplicationException{
		return relationService.deleteRelation(personKey);
	}
	
	@Override
	public boolean deleteRelation(IPersonKey firstPersonKey, IPersonKey secondPersonKey) throws ApplicationException{
		return relationService.deleteRelation(firstPersonKey, secondPersonKey);
	}
	
	@Override
	public List<IRelationDetails> getRelation(IPersonKey firstPersonKey) throws ApplicationException{
		return relationService.getRelation(firstPersonKey);
	}
	
	@Override
	public List<IRelationDetails> getRelation(IPersonKey firstPersonKey, IPersonKey secondPersonKey) throws ApplicationException{
		return relationService.getRelation(firstPersonKey, secondPersonKey);
	}
	@Override
	public IRelationDetails getRelation(IPersonKey firstPersonKey, IPersonKey secondPersonKey,
			IRelationType relationType) throws ApplicationException{
		return relationService.getRelation(firstPersonKey, secondPersonKey,relationType);
	}
	
	@Override
	public int addAddress(IAddress address, IPersonKey personKey) throws ApplicationException{
		return addressService.addAddress(address,personKey);
	}

	@Override
	public int updateAddress(IAddress address) throws ApplicationException{
		return addressService.updateAddress(address);
	}

	@Override
	public int deleteAddress(IAddressKey addressKey) throws ApplicationException{
		return addressService.deleteAddress(addressKey);
	}

	@Override
	public List<IAddress> getAddress(IPersonKey personKey) throws ApplicationException{
		return addressService.getAddress(personKey);
	}

	/**
	 * @return the personService
	 */
	public IPersonService getPersonService() {
		return personService;
	}

	/**
	 * @param personService the personService to set
	 */
	public void setPersonService(IPersonService personService) {
		this.personService = personService;
	}

	/**
	 * @return the relationService
	 */
	public IRelationService getRelationService() {
		return relationService;
	}

	/**
	 * @param relationService the relationService to set
	 */
	public void setRelationService(IRelationService relationService) {
		this.relationService = relationService;
	}

	/**
	 * @return the addressService
	 */
	public IAddressService getAddressService() {
		return addressService;
	}

	/**
	 * @param addressService the addressService to set
	 */
	public void setAddressService(IAddressService addressService) {
		this.addressService = addressService;
	}

	/**
	 * @return the relation
	 */
	public IRelation getRelation() {
		return relation;
	}

	/**
	 * @param relation the relation to set
	 */
	public void setRelation(IRelation relation) {
		this.relation = relation;
	}

}
