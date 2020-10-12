package com.puru.family.manager;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.puru.family.person.IPerson;
import com.puru.family.person.model.Person;
import com.puru.family.person.model.PersonKey;
import com.puru.family.person.model.PersonalDetails;
import com.puru.family.relation.IRelation;
import com.puru.family.relation.model.RelationType;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring-context.xml"})
public class FamilyManagerTest {
	@Autowired    
    private IFamilyManager familyManager;
	
	private static final String JSON_OUTPUT_DIR ="D:\\javaCodes\\pk_ws\\FamilyTree\\src\\main\\output\\";
	
	@Test
    public void testGetFamilyTree() throws Exception {
		
		int createdPersonsCounts = 20;
		
		List<Integer> personsIdList = new ArrayList<Integer>(createdPersonsCounts);
		for(int i=0;i<createdPersonsCounts; i++){
			int id = familyManager.addPerson(this.createPerson());
			personsIdList.add(id);
		}
		
		int start =0; int end = personsIdList.size()-1;
		
		PersonKey personKey = new PersonKey(personsIdList.get(end));

		familyManager.addRelation(new PersonKey(personsIdList.get(end).intValue()),
				new PersonKey(personsIdList.get(start++).intValue()), new RelationType(IRelation.SPOUSE_RELATION_ID));
		
		familyManager.addRelation(new PersonKey(personsIdList.get(end).intValue()),
				new PersonKey(personsIdList.get(start++).intValue()), new RelationType(IRelation.FATHER_RELATION_ID));
		
		familyManager.addRelation(new PersonKey(personsIdList.get(end).intValue()),
				new PersonKey(personsIdList.get(start++).intValue()), new RelationType(IRelation.MOTHER_RELATION_ID));
		
		familyManager.addRelation(new PersonKey(personsIdList.get(end).intValue()),
				new PersonKey(personsIdList.get(start++).intValue()), new RelationType(IRelation.GRANDPARENTS_RELATION_ID));
		
		familyManager.addRelation(new PersonKey(personsIdList.get(end).intValue()),
				new PersonKey(personsIdList.get(start++).intValue()), new RelationType(IRelation.GRANDPARENTS_RELATION_ID));
		
		familyManager.addRelation(new PersonKey(personsIdList.get(end).intValue()),
				new PersonKey(personsIdList.get(start++).intValue()), new RelationType(IRelation.SIBLINGS_RELATION_ID));
		
		familyManager.addRelation(new PersonKey(personsIdList.get(end).intValue()),
				new PersonKey(personsIdList.get(start).intValue()), new RelationType(IRelation.SIBLINGS_RELATION_ID));
		
		familyManager.addRelation(new PersonKey(personsIdList.get(start).intValue()),
				new PersonKey(personsIdList.get(start+1).intValue()), new RelationType(IRelation.SIBLINGS_RELATION_ID));
		
		start = start+1;
						
        Person person=(Person) familyManager.getFamilyTree(personKey);
        assertNotNull(person);
        
        
        serializeIntoFile().writeValue(new File(JSON_OUTPUT_DIR+"family_tree.json"), person);
        
        for(int i=0;i<personsIdList.size(); i++){
			try {
				familyManager.deletePerson(new PersonKey(personsIdList.get(i)));
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				familyManager.deleteRelation(new PersonKey(personsIdList.get(i)));
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		 System.out.println("--Done--");
    }
	
	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}
	
	private IPerson createPerson(){
		Person per = new Person();
		
		PersonalDetails personalDetails = new PersonalDetails();
		personalDetails.setFirstName("Purushottam");
		personalDetails.setMiddleName("Kumar");
		personalDetails.setLastName("Ray");
		personalDetails.setGender("Male");
		personalDetails.setDob(new Date());
		personalDetails.setDod(new Date());
		personalDetails.setMobileNo("1234567891");
		personalDetails.setLandlineNo("040123456");
		personalDetails.setPassportNo("F470TTQ");
		personalDetails.setPanNo("ASRPP123");
		personalDetails.setCreatedBy("pkr");
		personalDetails.setLastModifiedBy("pkr");
		
		per.setPersonalDetails(personalDetails);
		
		return per;
		
	}
	protected <T> ObjectMapper serializeIntoFile() throws IOException {
        ObjectMapper om = new ObjectMapper();
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.OBJECT_AND_NON_CONCRETE);
        om.setSerializationInclusion(Include.NON_NULL);
        om.setSerializationInclusion(Include.NON_EMPTY);
        om.setVisibility(PropertyAccessor.ALL, Visibility.NONE);
        om.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
        om.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        return om;
    }
}
