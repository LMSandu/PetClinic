package com.endava.petclinic.owner;

import com.endava.petclinic.model.Owner;
import com.endava.petclinic.TestBaseClass;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.withArgs;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class GetOwnerListTest extends TestBaseClass {

    @Test
    public void shouldGetOwnerList(){

        //GIVEN
        Owner owner = testDataProvider.getOwner();
        Response createOwnerResponse = ownerClient.createOwner(owner);
        createOwnerResponse.then().statusCode(HttpStatus.SC_CREATED);
        Long ownerID = createOwnerResponse.body().jsonPath().getLong("id");

        //WHEN
        Response getOwnerResponse = ownerClient.getOwnerList();

        //THEN
        getOwnerResponse.then().statusCode(HttpStatus.SC_OK)
                .body("find{ it -> it.id == %s}.firstName", withArgs(ownerID), is(owner.getFirstName()));

        //sau
        Owner actualOwner = getOwnerResponse.body().jsonPath().param("id", ownerID).getObject("find{ it -> it.id == id}", Owner.class);
        assertThat(actualOwner, is(owner));
        //sau
        List<Owner> ownerList = getOwnerResponse.body().jsonPath().getList("", Owner.class);
        assertThat(ownerList, hasItem(owner));
    }

    @Test
    public void shouldGetOwnerByID(){

        fixture.createOwner().getOwnerByID();
        Owner owner = fixture.getOwner();
        // WHEN
        Response response = ownerClient.getOwnerByID(owner.getId());
        // THEN
        response.then().statusCode(HttpStatus.SC_OK);




    }
}
