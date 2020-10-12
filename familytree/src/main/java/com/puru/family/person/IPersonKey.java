package com.puru.family.person;

import java.io.Serializable;

public interface IPersonKey  extends Serializable{
	public int getId();
	public int hashCode() ;
	public boolean equals(Object obj);
}
