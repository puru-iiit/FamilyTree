package com.puru.family.relation.model;

import com.puru.family.relation.IRelationType;

public class RelationType implements IRelationType{

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String code;
	private String desc;
	
	public RelationType() {
		super();
	}
	
	public RelationType(int id) {
		super();
		this.id=id;
	}

	public RelationType(int id, String code, String desc) {
		super();
		this.id = id;
		this.code = code;
		this.desc = desc;
	}

	@Override
	public int getRelationId() {
		return this.id;
	}

	@Override
	public String getRelationCode() {
		return this.code;
	}

	@Override
	public String getRelationDesc() {
		return this.desc;
	}

	public int getId() {
		return id;
	}

	public String getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setDesc(String desc) {
		this.desc = desc;
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
		if (!(obj instanceof RelationType))
			return false;
		RelationType other = (RelationType) obj;
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
		builder.append("RelationType [id=");
		builder.append(id);
		builder.append(", code=");
		builder.append(code);
		builder.append(", desc=");
		builder.append(desc);
		builder.append("]");
		return builder.toString();
	}
	
}
