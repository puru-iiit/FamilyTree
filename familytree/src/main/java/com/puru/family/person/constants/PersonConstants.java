package com.puru.family.person.constants;

public class PersonConstants {
	public static final String SQL_PERSON_INSERT = "INSERT INTO person(first_name,middle_name,last_name,gender,dob,dod,mobile_no,landline_no,passport_no,"
			+ "pan_no,created_by,last_modified_by,creation_date,last_modified_date)"
			+ " values(?,?,?,?,?,?,?,?,?,?,?,?,NOW(),NOW()) ";
	
	public static final String SQL_PERSON_UPDATE = "UPDATE person set first_name ? ,middle_name=?,last_name=?,gender=?,dob=?,dod=?,mobile_no=?,landline_no=?,passport_no=?,"
			+ "pan_no=?,last_modified_by=?,last_modified_date=NOW() where id=?";
	
	public static final String SQL_PERSON_SELECT = "select * from person where id=?";
	
	public static final String SQL_PERSON_DELETE = "delete from person where id=?";
	
}
