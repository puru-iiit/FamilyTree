package com.puru.family.address.model;

import java.util.Date;

import com.puru.family.address.IAddress;
import com.puru.family.address.IAddressKey;
import com.puru.family.person.IPersonKey;

public class Address implements IAddress{

	private static final long serialVersionUID = 1L;
	
	private IAddressKey addressKey;
	private IPersonKey personKey;
	private String addressPart1;
	private String addressPart2;
	private String city;
	private String state;
	private String country;
	private String createdBy;
	private String lastModifiedBy;
	private Date creationDate;
	private Date lastModifiedDate;
	
	/**
	 * @param addressKey
	 * @param personKey
	 */
	public Address(IAddressKey addressKey, IPersonKey personKey) {
		super();
		this.addressKey = addressKey;
		this.personKey = personKey;
	}

	public String getAddressPart1() {
		return addressPart1;
	}

	public void setAddressPart1(String addressPart1) {
		this.addressPart1 = addressPart1;
	}

	public String getAddressPart2() {
		return addressPart2;
	}

	public void setAddressPart2(String addressPart2) {
		this.addressPart2 = addressPart2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public IAddressKey getAddressKey() {
		return addressKey;
	}
	
	public IPersonKey getPersonKey() {
		return personKey;
	}
		
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((addressKey == null) ? 0 : addressKey.hashCode());
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
		if (!(obj instanceof Address))
			return false;
		Address other = (Address) obj;
		if (addressKey == null) {
			if (other.addressKey != null)
				return false;
		} else if (!addressKey.equals(other.addressKey))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Address [addressKey=");
		builder.append(addressKey);
		builder.append(", personKey=");
		builder.append(personKey);
		builder.append(", addressPart1=");
		builder.append(addressPart1);
		builder.append(", addressPart2=");
		builder.append(addressPart2);
		builder.append(", city=");
		builder.append(city);
		builder.append(", state=");
		builder.append(state);
		builder.append(", country=");
		builder.append(country);
		builder.append(", createdBy=");
		builder.append(createdBy);
		builder.append(", lastModifiedBy=");
		builder.append(lastModifiedBy);
		builder.append(", creationDate=");
		builder.append(creationDate);
		builder.append(", lastModifiedDate=");
		builder.append(lastModifiedDate);
		builder.append("]");
		return builder.toString();
	}

	
}
