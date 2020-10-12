package com.puru.family.relation.constants;

public final class RelationConstants {
	public static final String SQL_RELATION_SELECT = "select * from relation where first_Person_id=?";

	public static final String SQL_RELATION_SELECT_2 = "select * from relation where first_Person_id=? and second_person_id=?";
	
	public static final String SQL_RELATION_SELECT_3 = "select * from relation where first_Person_id=? and second_person_id=? and first_to_second_relation_type=?";

	public static final String SQL_RELATION_INSERT = "INSERT INTO relation(first_Person_id,second_person_id,first_to_second_relation_type,second_to_first_relation_Type,"
			+ "created_by,last_modified_by,creation_date,last_modified_date) values(?,?,?,null,null,null,now(),now())";

	public static final String SQL_RELATION_DELETE = "delete from relation where first_Person_id=? and second_person_id=?";
	public static final String SQL_RELATION_DELETE_2 = "delete from relation where first_Person_id=? and second_person_id=? and first_to_second_relation_type=?";
	
	public static final String SQL_RELATION_DELETE_3 = "delete from relation where first_Person_id=? or second_person_id=? ";

	public static final String SQL_RELATION_UPDATE = "update relation set first_to_second_relation_type = ? where first_Person_id=? and second_person_id=? "
			+ "and first_to_second_relation_type=?";

}
