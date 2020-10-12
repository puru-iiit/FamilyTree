package com.puru.family.address;

import java.io.Serializable;

public interface IAddressKey extends Serializable{
	public int hashCode() ;
	public boolean equals(Object obj);
}
