package com.endava.petclinic.owner;

import com.endava.petclinic.TestBaseClass;
import com.endava.petclinic.model.Owner;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

public class UpdateOwnerTest extends TestBaseClass {

    @Test
    public void shouldUpdateOwnerGivenID(){
        //GIVEN
        fixture.createOwner().updateOwnerByID();
        Owner owner = fixture.getOwner();

        //WHEN
        Response response = ownerClient.getOwnerByID(owner.getId());

        //THEN
        response.then().statusCode(HttpStatus.SC_OK);
    }
}
