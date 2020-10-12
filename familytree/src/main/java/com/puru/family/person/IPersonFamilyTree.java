package com.puru.family.person;

import java.util.List;

public interface IPersonFamilyTree{
	/**
	 * it will just reference of given Person(this) whom family tree we are looking for 
	 * @return
	 */
	public IPerson getPerson();
	
	/**
	 * it will just reference of just above Person from which we came to this Person
	 * It will get used for upward movement in family tree from a given person
	 * Its not a part of Relationship, its just get used for upward movement
	 * @return
	 */
	public IPerson getSuperPerson();
	
	/**
	 * It will return given person's Spouse family tree(Spouse will be part of returned family Tree)
	 * @return
	 */
	public IPerson getSpouse();
	
	/**
	 * It will return given person's Mother family tree(Mother will be part of returned family Tree)
	 * @return
	 */
	public IPerson getMother();
	
	/**
	 * It will return given person's Father family tree(Father will be part of returned family Tree)
	 * @return
	 */
	public IPerson getFather();
	
	/**
	 * It will return given person's Siblings family tree list
	 * @return
	 */
	public List<IPerson> getSiblings();
	
	/**
	 * It will return given person's GrandParents family tree list
	 * @return
	 */
	public List<IPerson> getGrandParents();
	
	/**
	 * It will return given person's relatives with relation as Others family tree list
	 * @return
	 */
	public List<IPerson> getOthers();
	

	/**
	 * It will return given person's relatives with relation as Nones family tree list
	 * @return
	 */
	public List<IPerson> getNones();
}
