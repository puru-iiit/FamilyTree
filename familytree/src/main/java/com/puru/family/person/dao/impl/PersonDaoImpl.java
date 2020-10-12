package com.puru.family.person.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.transaction.annotation.Transactional;

import com.puru.family.exception.ApplicationException;
import com.puru.family.person.IPerson;
import com.puru.family.person.IPersonKey;
import com.puru.family.person.constants.PersonConstants;
import com.puru.family.person.dao.IPersonDao;
import com.puru.family.person.model.Person;
import com.puru.family.person.model.PersonKey;
import com.puru.family.person.model.PersonalDetails;

public class PersonDaoImpl implements IPersonDao {
	
	private DataSource dataSource;
	
	@Override
	@Transactional
	public int addPerson(IPerson person) throws ApplicationException{
		int id =-1;
		try {
			
			Connection connection = dataSource.getConnection();
			PreparedStatement statement = connection.prepareStatement(PersonConstants.SQL_PERSON_INSERT,
					Statement.RETURN_GENERATED_KEYS);
			
			this.mappingPersonToStatement(statement,person);

			int affectedRows = statement.executeUpdate();

			if (affectedRows == 0) {
				throw new ApplicationException("Creating person failed, no rows affected.");
			}
			ResultSet generatedKeys = statement.getGeneratedKeys();
			if (generatedKeys.next()) {
				id = generatedKeys.getInt(1);
			} else {
				throw new ApplicationException("Creating person failed, no ID obtained.");
			}
		}catch(Exception e){
			e.printStackTrace();
			throw new ApplicationException("Creating person failed, no ID obtained.");
		}
		return id;
	}

	@Override
	@Transactional
	public int updatePerson(IPerson person) throws ApplicationException{
		int updated =-1;
		try {
			
			Connection connection = dataSource.getConnection();
			PreparedStatement statement = connection.prepareStatement(PersonConstants.SQL_PERSON_UPDATE);
			
			this.mappingPersonToUpdateStatement(statement,person);

			updated = statement.executeUpdate();

			if (updated == 0) {
				throw new ApplicationException("No Person record found to update for Key :"+person.getPersonKey().getId());
			}
			
		}catch(Exception e){
			e.printStackTrace();
			throw new ApplicationException("Updating person failed for Key :"+person.getPersonKey().getId());
		}
		return updated;
	}

	@Override
	@Transactional
	public int deletePerson(IPersonKey personKey) throws ApplicationException{
		int deleted =-1;
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement statement = connection.prepareStatement(PersonConstants.SQL_PERSON_DELETE);
			statement.setInt(1, personKey.getId());

			deleted = statement.executeUpdate();
			
			if (deleted == 0) {
				throw new ApplicationException("No Person record found to be deleted for Key :"+personKey.getId());
			}
			
		}catch(Exception e){
			e.printStackTrace();
			throw new ApplicationException("Deleting person failed for key :"+personKey.getId());
		}
		return deleted;
	}

	@Override
	public List<IPerson> getAllPerson(List<IPersonKey> personKeyList) throws ApplicationException{
		List<IPerson> personList = new ArrayList<IPerson>();
		for(IPersonKey personKey :personKeyList){
			IPerson person = this.getPerson(personKey);
			if(person!=null){
				personList.add(person);
			}
		}
		return personList;
		
	}

	@Override
	public IPerson getPerson(IPersonKey personKey) throws ApplicationException{
		IPerson person = null;
		try {
			PersonKey  key = (PersonKey)personKey;
			
			Connection connection = dataSource.getConnection();
			PreparedStatement statement = connection.prepareStatement(PersonConstants.SQL_PERSON_SELECT);
			statement.setInt(1, key.getId());

			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				person = this.mapResultSetToPerson(rs);
			}
		}catch(Exception e){
			e.printStackTrace();
			throw new ApplicationException("Fetching person failed for key :"+personKey.getId());
		}
		return person;
	}
	
	private IPerson mapResultSetToPerson(ResultSet rs) throws SQLException{
		
		
		PersonKey key = new PersonKey(rs.getInt("id"));
		Person per = new Person(key);
		
		PersonalDetails personalDetails = new PersonalDetails();
		per.setPersonalDetails(personalDetails);
		
		personalDetails.setFirstName(rs.getString("first_name"));
		personalDetails.setMiddleName(rs.getString("middle_name"));
		personalDetails.setLastName(rs.getString("last_name"));
		personalDetails.setGender(rs.getString("gender"));
		personalDetails.setDob(rs.getDate("dob"));
		personalDetails.setDod(rs.getDate("dod"));
		personalDetails.setMobileNo(rs.getString("mobile_no"));
		personalDetails.setLandlineNo(rs.getString("landline_no"));
		personalDetails.setPassportNo(rs.getString("passport_no"));
		personalDetails.setPanNo(rs.getString("pan_no"));
		personalDetails.setCreatedBy(rs.getString("created_by"));
		personalDetails.setLastModifiedBy(rs.getString("last_modified_by"));
		personalDetails.setLastModifiedDate(rs.getDate("last_modified_date"));
		personalDetails.setCreationDate(rs.getDate("creation_date"));
		
		return per;
	}
	
	private void mappingPersonToStatement(PreparedStatement statement, IPerson person) throws SQLException{
		
		Person per = (Person)person;
		int index =1;
		statement.setString(index++, per.getPersonalDetails().getFirstName());
		statement.setString(index++, per.getPersonalDetails().getMiddleName());
		statement.setString(index++, per.getPersonalDetails().getLastName());
		statement.setString(index++, per.getPersonalDetails().getGender());
		statement.setDate(index++, new java.sql.Date(per.getPersonalDetails().getDob().getTime()));
		statement.setDate(index++, new java.sql.Date(per.getPersonalDetails().getDod().getTime()));
		statement.setString(index++, per.getPersonalDetails().getMobileNo());
		statement.setString(index++, per.getPersonalDetails().getLandlineNo());
		statement.setString(index++, per.getPersonalDetails().getPassportNo());
		statement.setString(index++, per.getPersonalDetails().getPanNo());
		statement.setString(index++, per.getPersonalDetails().getCreatedBy());
		statement.setString(index++, per.getPersonalDetails().getLastModifiedBy());
	}
	
	private void mappingPersonToUpdateStatement(PreparedStatement statement, IPerson person) throws SQLException{
		
		Person per = (Person)person;
		int index =1;
		statement.setString(index++, per.getPersonalDetails().getFirstName());
		statement.setString(index++, per.getPersonalDetails().getMiddleName());
		statement.setString(index++, per.getPersonalDetails().getLastName());
		statement.setString(index++, per.getPersonalDetails().getGender());
		statement.setDate(index++, new java.sql.Date(per.getPersonalDetails().getDob().getTime()));
		statement.setDate(index++, new java.sql.Date(per.getPersonalDetails().getDod().getTime()));
		statement.setString(index++, per.getPersonalDetails().getMobileNo());
		statement.setString(index++, per.getPersonalDetails().getLandlineNo());
		statement.setString(index++, per.getPersonalDetails().getPassportNo());
		statement.setString(index++, per.getPersonalDetails().getPanNo());
		statement.setString(index++, per.getPersonalDetails().getLastModifiedBy());
		
		statement.setInt(index++, per.getPersonKey().getId());
	}
	 
	public void setDataSource(DataSource dataSource) {
	      this.dataSource = dataSource;
	}
	public DataSource getDataSource() {
		return dataSource;
	}
	
}
