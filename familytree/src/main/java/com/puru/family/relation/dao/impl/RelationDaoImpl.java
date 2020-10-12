package com.puru.family.relation.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.transaction.annotation.Transactional;

import com.puru.family.exception.ApplicationException;
import com.puru.family.person.IPerson;
import com.puru.family.person.IPersonKey;
import com.puru.family.person.model.PersonKey;
import com.puru.family.relation.IRelationDetails;
import com.puru.family.relation.IRelationType;
import com.puru.family.relation.constants.RelationConstants;
import com.puru.family.relation.dao.IRelationDao;
import com.puru.family.relation.model.RelationDetails;
import com.puru.family.relation.model.RelationType;

public class RelationDaoImpl implements IRelationDao {
	private DataSource dataSource;

	@Override
	@Transactional
	public boolean addRelation(IPerson firstPerson, IPerson secondPerson, IRelationType relationType)
			throws ApplicationException {
		return this.addRelation(firstPerson.getPersonKey(), secondPerson.getPersonKey(), relationType);
	}

	@Override
	@Transactional
	public boolean addRelation(IPersonKey firstPersonKey, IPersonKey secondPersonKey, IRelationType relationType)
			throws ApplicationException {
		int affectedRows = -1;
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement statement = connection.prepareStatement(RelationConstants.SQL_RELATION_INSERT);

			this.mappingRelationToStatement(statement, firstPersonKey, secondPersonKey, relationType);

			affectedRows = statement.executeUpdate();

			if (affectedRows == 0) {
				throw new ApplicationException("Creating Relation failed.");
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Creating Relation failed.");
		}
		return (affectedRows > 0 ? true : false);
	}

	@Override
	@Transactional
	public boolean updateRelation(IPersonKey firstPersonKey, IPersonKey secondPersonKey, IRelationType oldRelationType, IRelationType newRelationType)
			throws ApplicationException {
		int updated =-1;
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement statement = connection.prepareStatement(RelationConstants.SQL_RELATION_UPDATE);
			statement.setInt(1, newRelationType.getRelationId());
			statement.setInt(2, firstPersonKey.getId());
			statement.setInt(3, secondPersonKey.getId());
			statement.setInt(4, oldRelationType.getRelationId());

			updated = statement.executeUpdate();
			
			if (updated == 0) {
				throw new ApplicationException("No relation updated between Person Key[" + firstPersonKey.getId()
				+ "] and Person Key[" + secondPersonKey.getId() + "] for relation id["+oldRelationType.getRelationId()+"]");
			}
			
		}catch(Exception e){
			e.printStackTrace();
			throw new ApplicationException("Failed relation update between Person Key[" + firstPersonKey.getId()
			+ "] and Person Key[" + secondPersonKey.getId() + "] for relation id["+oldRelationType.getRelationId()+"]");
		}
		return (updated>0?true:false);
	}
	
	@Override
	@Transactional
	public boolean deleteRelation(IPersonKey firstPersonKey, IPersonKey secondPersonKey, IRelationType relationType)
			throws ApplicationException {
		int deleted =-1;
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement statement = connection.prepareStatement(RelationConstants.SQL_RELATION_DELETE_2);
			statement.setInt(1, firstPersonKey.getId());
			statement.setInt(2, secondPersonKey.getId());
			statement.setInt(3, relationType.getRelationId());

			deleted = statement.executeUpdate();
			
			if (deleted == 0) {
				throw new ApplicationException("No relation deleted between Person Key[" + firstPersonKey.getId()
				+ "] and Person Key[" + secondPersonKey.getId() + "] for relation id["+relationType.getRelationId()+"]");
			}
			
		}catch(Exception e){
			e.printStackTrace();
			throw new ApplicationException("Failed relation delete between Person Key[" + firstPersonKey.getId()
			+ "] and Person Key[" + secondPersonKey.getId() + "] for relation id["+relationType.getRelationId()+"]");
		}
		return (deleted>0?true:false);
	}
	
	@Override
	@Transactional
	public boolean deleteRelation(IPersonKey personKey) throws ApplicationException {
		int deleted =-1;
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement statement = connection.prepareStatement(RelationConstants.SQL_RELATION_DELETE_3);
			statement.setInt(1, personKey.getId());
			statement.setInt(2, personKey.getId());

			deleted = statement.executeUpdate();
			
			if (deleted == 0) {
				throw new ApplicationException("No relation deleted for Person Key[" + personKey.getId()+ "]");
			}
			
		}catch(Exception e){
			throw new ApplicationException("No relation deleted for Person Key[" + personKey.getId()+ "]",e);
		}
		return (deleted>0?true:false);
	}
	@Override
	@Transactional
	public boolean deleteRelation(IPersonKey firstPersonKey, IPersonKey secondPersonKey) throws ApplicationException {
		int deleted =-1;
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement statement = connection.prepareStatement(RelationConstants.SQL_RELATION_DELETE);
			statement.setInt(1, firstPersonKey.getId());
			statement.setInt(2, secondPersonKey.getId());

			deleted = statement.executeUpdate();
			
			if (deleted == 0) {
				throw new ApplicationException("No relation deleted between Person Key[" + firstPersonKey.getId()
				+ "] and Person Key[" + secondPersonKey.getId() + "]");
			}
			
		}catch(Exception e){
			e.printStackTrace();
			throw new ApplicationException("Failed relation delete between Person Key[" + firstPersonKey.getId()
			+ "] and Person Key[" + secondPersonKey.getId() + "]");
		}
		return (deleted>0?true:false);
	}

	@Override
	public List<IRelationDetails> getRelation(IPersonKey firstPersonKey, IPersonKey secondPersonKey) throws ApplicationException {
		List<IRelationDetails> relationDetailsList = new ArrayList<IRelationDetails>();
		try {

			Connection connection = dataSource.getConnection();
			PreparedStatement statement = connection.prepareStatement(RelationConstants.SQL_RELATION_SELECT_2);
			statement.setInt(1, firstPersonKey.getId());
			statement.setInt(2, secondPersonKey.getId());

			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				IRelationDetails relationDetails = this.mapResultSetToRelationDetails(rs);
				relationDetailsList.add(relationDetails);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Failed to get relation between Person Key[" + firstPersonKey.getId()
					+ "] and Person Key[" + secondPersonKey.getId() + "]");
		}
		return relationDetailsList;
	}
	
	@Override
	public IRelationDetails getRelation(IPersonKey firstPersonKey, IPersonKey secondPersonKey,IRelationType relationType) throws ApplicationException {
		IRelationDetails relationDetails = null;
		try {

			Connection connection = dataSource.getConnection();
			PreparedStatement statement = connection.prepareStatement(RelationConstants.SQL_RELATION_SELECT_3);
			statement.setInt(1, firstPersonKey.getId());
			statement.setInt(2, secondPersonKey.getId());
			statement.setInt(3, relationType.getRelationId());

			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				relationDetails = this.mapResultSetToRelationDetails(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Failed to get relation between Person Key[" + firstPersonKey.getId()
					+ "] and Person Key[" + secondPersonKey.getId() + "]");
		}
		return relationDetails;
	}

	@Override
	public List<IRelationDetails> getRelation(IPersonKey personKey) throws ApplicationException {
		List<IRelationDetails> relationDetailsList = new ArrayList<IRelationDetails>();
		try {

			Connection connection = dataSource.getConnection();
			PreparedStatement statement = connection.prepareStatement(RelationConstants.SQL_RELATION_SELECT);
			statement.setInt(1, personKey.getId());

			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				IRelationDetails relationDetails = this.mapResultSetToRelationDetails(rs);
				relationDetailsList.add(relationDetails);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Failed to get relation for Key :" + personKey.getId());
		}
		return relationDetailsList;
	}

	private IRelationDetails mapResultSetToRelationDetails(ResultSet rs) throws SQLException {

		RelationDetails relationDetails = new RelationDetails();

		IPersonKey firstPersonKey = new PersonKey(rs.getInt("first_Person_id"));
		IPersonKey secondPersonKey = new PersonKey(rs.getInt("second_person_id"));

		relationDetails.setFirstPersonKey(firstPersonKey);
		relationDetails.setSecondPersonKey(secondPersonKey);

		RelationType type1 = new RelationType();
		type1.setId(rs.getInt("first_to_second_relation_type"));

		RelationType type2 = new RelationType();
		type2.setId(rs.getInt("second_to_first_relation_Type"));

		relationDetails.setFirstToSecondRelationType(type1);
		relationDetails.setSecondToFirstRelationType(type2);

		return relationDetails;
	}

	private void mappingRelationToStatement(PreparedStatement statement, IPersonKey firstPersonKey,
			IPersonKey secondPersonKey, IRelationType relationType) throws SQLException {

		int index = 1;
		statement.setInt(index++, firstPersonKey.getId());
		statement.setInt(index++, secondPersonKey.getId());
		statement.setInt(index++, relationType.getRelationId());
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

}
