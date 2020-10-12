package com.puru.family.address.dao;

import java.util.List;

import com.puru.family.address.IAddress;
import com.puru.family.address.IAddressKey;
import com.puru.family.exception.ApplicationException;
import com.puru.family.person.IPersonKey;

public interface IAddressDao {
	public int addAddress(IAddress address,IPersonKey personKey) throws ApplicationException;
	public int addAddress(List<IAddress> addressList, IPersonKey personKey)throws ApplicationException;
	public int updateAddress(IAddress address) throws ApplicationException;
	public int deleteAddress(IAddressKey addressKey) throws ApplicationException;
	public int deleteAllAddress(List<IAddressKey> addressKeyList) throws ApplicationException;
	public List<IAddress> getAddress(IPersonKey personKey) throws ApplicationException;
	public IAddress getAddress(IAddressKey addressKey) throws ApplicationException;
}
