package com.puru.family.relation;

public interface IRelation{
	//public static final int ME_RELATION_ID =1;
	//public static final int ME_SUPER_RELATION_ID =2;
	public static final int SPOUSE_RELATION_ID =3;
	public static final int FATHER_RELATION_ID =4;
	public static final int MOTHER_RELATION_ID =5;
	public static final int SIBLINGS_RELATION_ID =6;
	public static final int GRANDPARENTS_RELATION_ID =7;
	public static final int OTHERS_RELATION_ID =8;
	public static final int NONES_RELATION_ID =9;
	
	public IRelationType getRelation(int relationId);
		
}
