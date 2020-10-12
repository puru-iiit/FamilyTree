package com.puru.family.person;

import java.io.Serializable;
import java.util.List;

import com.puru.family.address.IAddress;
import com.puru.family.relation.IRelationType;

public interface IPerson extends Serializable{
	
	public IPerson getFamilyTree();
	
	public IPersonKey getPersonKey();
	
	public List<IAddress> getAddresses();
	public  boolean setAddresses(List<IAddress> addressList);
	
	public List<IPerson> getRelatives(IRelationType relationType);
	
	public boolean setRelatives(IRelationType relationType, List<IPerson> personList);
	
	public boolean isMoreRelativeToLoad();
	
	public void setIsMoreRelativeToLoad(boolean isMoreRelativeToLoad);
	
}
