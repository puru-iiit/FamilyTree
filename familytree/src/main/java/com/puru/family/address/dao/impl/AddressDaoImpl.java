package com.puru.family.address.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import com.puru.family.address.IAddress;
import com.puru.family.address.IAddressKey;
import com.puru.family.address.dao.IAddressDao;
import com.puru.family.exception.ApplicationException;
import com.puru.family.person.IPersonKey;
import org.springframework.transaction.annotation.Transactional;

public class AddressDaoImpl implements IAddressDao{
	private DataSource dataSource;
	
	@Override
	@Transactional
	public int addAddress(IAddress address, IPersonKey personKey) throws ApplicationException{
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	@Transactional
	public int addAddress(List<IAddress> addressList, IPersonKey personKey) throws ApplicationException{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	@Transactional
	public int updateAddress(IAddress address) throws ApplicationException{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	@Transactional
	public int deleteAddress(IAddressKey addressKey) throws ApplicationException{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	@Transactional
	public int deleteAllAddress(List<IAddressKey> addressKeyList) throws ApplicationException{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<IAddress> getAddress(IPersonKey personKey) throws ApplicationException{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IAddress getAddress(IAddressKey addressKey) throws ApplicationException{
		// TODO Auto-generated method stub
		return null;
	}
	public void setDataSource(DataSource dataSource) {
	      this.dataSource = dataSource;
	}
	public DataSource getDataSource() {
		return dataSource;
	}
}
