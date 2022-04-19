package com.endava.petclinic.owner;

import com.endava.petclinic.model.Owner;
import com.endava.petclinic.TestBaseClass;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

public class DeleteOwnerTest extends TestBaseClass {

    @Test
    public void shouldDeleteOwner(){

        //GIVEN
        fixture.createOwner().deleteOwnerByID();
        Owner owner = fixture.getOwner();

        //WHEN
        Response deleteOwnerResponse = ownerClient.getOwnerByID(owner.getId());

        //THEN
        deleteOwnerResponse.then().statusCode(HttpStatus.SC_NOT_FOUND);
    }

}
