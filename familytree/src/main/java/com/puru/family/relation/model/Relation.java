package com.puru.family.relation.model;

import java.util.HashMap;
import java.util.Map;

import com.puru.family.relation.IRelation;
import com.puru.family.relation.IRelationType;

public class Relation implements IRelation {
	
	/**
	 * TODO : This can be loaded from configuration file; For time being its getting populated here. 
	 */
	private static final Map<Integer, IRelationType> relationTypeMap = new HashMap<Integer, IRelationType>(); 
	
	static{
		//relationTypeMap.put(IRelation.ME_RELATION_ID, new RelationType(IRelation.ME_RELATION_ID,"ME","Relation to myself"));
		//relationTypeMap.put(IRelation.ME_SUPER_RELATION_ID, new RelationType(IRelation.ME_SUPER_RELATION_ID,"SUPER","Relation of me with super Person"));
		relationTypeMap.put(IRelation.SPOUSE_RELATION_ID, new RelationType(IRelation.SPOUSE_RELATION_ID,"SPOUSE","Relation to Spouse"));
		relationTypeMap.put(IRelation.FATHER_RELATION_ID, new RelationType(IRelation.FATHER_RELATION_ID,"FATHER","Relation to Father"));
		relationTypeMap.put(IRelation.MOTHER_RELATION_ID, new RelationType(IRelation.MOTHER_RELATION_ID,"MOTHER","Relation to Mother"));
		relationTypeMap.put(IRelation.SIBLINGS_RELATION_ID, new RelationType(IRelation.SIBLINGS_RELATION_ID,"SIBLINGS","Relation to Sublings"));
		relationTypeMap.put(IRelation.GRANDPARENTS_RELATION_ID, new RelationType(IRelation.GRANDPARENTS_RELATION_ID,"GRANDPARENTS","Relation to GrandParents"));
		relationTypeMap.put(IRelation.OTHERS_RELATION_ID, new RelationType(IRelation.OTHERS_RELATION_ID,"OTHERS","Relation to Others"));
		relationTypeMap.put(IRelation.NONES_RELATION_ID, new RelationType(IRelation.NONES_RELATION_ID,"NONES","Relation to Nones"));
	}
	
	@Override
	public IRelationType getRelation(int relationId) {
		return relationTypeMap.get(relationId);
	}

}
