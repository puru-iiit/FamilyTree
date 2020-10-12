package com.puru.family.address.model;

import com.puru.family.address.IAddressKey;

public class AddressKey implements IAddressKey{
	private static final long serialVersionUID = 1L;
	private int id;
	/**
	 * @param id
	 */
	public AddressKey(int id) {
		super();
		this.id = id;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		if (!(obj instanceof AddressKey))
			return false;
		AddressKey other = (AddressKey) obj;
		if (id != other.id)
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AddressKey [id=");
		builder.append(id);
		builder.append("]");
		return builder.toString();
	}
	
	
}
