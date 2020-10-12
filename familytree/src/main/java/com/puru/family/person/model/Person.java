package com.puru.family.person.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.puru.family.address.IAddress;
import com.puru.family.person.IPerson;
import com.puru.family.person.IPersonKey;
import com.puru.family.relation.IRelationType;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "personKey")
public class Person implements IPerson{

	private static final long serialVersionUID = 1L;
	
	private IPersonKey personKey;
	
	private PersonalDetails personalDetails;
	
	private List<IAddress> addressList;
	
	private Map<IRelationType, List<IPerson>> relativesMaps;
	
	private boolean isMoreRelativeToLoad=false;
	
	public Person(){
		this.relativesMaps = new HashMap<IRelationType, List<IPerson>>();
	}
	public Person(IPersonKey personKey){
		this.personKey = personKey;
		this.relativesMaps = new HashMap<IRelationType, List<IPerson>>();
	}
	/**
	 * @param personKey the personKey to set
	 */
	public void setPersonKey(IPersonKey personKey) {
		this.personKey = personKey;
	}
	@Override
	public IPerson getFamilyTree() {
		return this;
	}
	
	@Override
	public List<IPerson> getRelatives(IRelationType relationType) {
		return this.relativesMaps.get(relationType);
	}
	
	@Override
	public boolean setRelatives(IRelationType relationType, List<IPerson> personList) {
		this.relativesMaps.remove(relationType);
		this.relativesMaps.put(relationType, personList);
		return true;
	}
	
	@Override
	public List<IAddress> getAddresses() {
		return this.getAddressList();
	}
	@Override
	public boolean setAddresses(List<IAddress> addressList) {
		this.setAddressList(addressList);
		return true;
	}
	
	@Override
	public boolean isMoreRelativeToLoad() {
		return this.isMoreRelativeToLoad;
	}
	
	@Override
	public void setIsMoreRelativeToLoad(boolean isMoreRelativeToLoad) {
		this.isMoreRelativeToLoad = isMoreRelativeToLoad;
	}
	public IPersonKey getPersonKey() {
		return personKey;
	}
	public PersonalDetails getPersonalDetails() {
		return personalDetails;
	}

	public void setPersonalDetails(PersonalDetails personalDetails) {
		this.personalDetails = personalDetails;
	}

	public List<IAddress> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<IAddress> addressList) {
		this.addressList = addressList;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((personKey == null) ? 0 : personKey.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Person))
			return false;
		Person other = (Person) obj;
		if (personKey == null) {
			if (other.personKey != null)
				return false;
		} else if (!personKey.equals(other.personKey))
			return false;
		return true;
	}
}
