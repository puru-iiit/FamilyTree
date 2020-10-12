package com.puru.family.address.service.impl;

import java.util.List;

import com.puru.family.address.IAddress;
import com.puru.family.address.IAddressKey;
import com.puru.family.address.dao.IAddressDao;
import com.puru.family.address.service.IAddressService;
import com.puru.family.exception.ApplicationException;
import com.puru.family.person.IPersonKey;

public class AdressServiceImpl implements IAddressService {
	private IAddressDao addressDao;
	
	
	@Override
	public int addAddress(IAddress address, IPersonKey personKey)  throws ApplicationException {
		return addressDao.addAddress(address, personKey);
	}

	@Override
	public int updateAddress(IAddress address)  throws ApplicationException{
		return addressDao.updateAddress(address);
	}

	@Override
	public int deleteAddress(IAddressKey addressKey) throws ApplicationException {
		return addressDao.deleteAddress(addressKey);
	}

	@Override
	public int deleteAllAddress(List<IAddressKey> addressKeyList) throws ApplicationException {
		return addressDao.deleteAllAddress(addressKeyList);
	}

	@Override
	public List<IAddress> getAddress(IPersonKey personKey) throws ApplicationException {
		return addressDao.getAddress(personKey);
	}

	@Override
	public IAddress getAddress(IAddressKey addressKey) throws ApplicationException {
		return addressDao.getAddress(addressKey);
	}

	@Override
	public int addAddress(List<IAddress> addressList, IPersonKey personKey)  throws ApplicationException{
		return addressDao.addAddress(addressList, personKey);
	}

	/**
	 * @return the addressDao
	 */
	public IAddressDao getAddressDao() {
		return addressDao;
	}

	/**
	 * @param addressDao the addressDao to set
	 */
	public void setAddressDao(IAddressDao addressDao) {
		this.addressDao = addressDao;
	}

}
