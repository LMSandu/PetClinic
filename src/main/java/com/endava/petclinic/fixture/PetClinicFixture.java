package com.endava.petclinic.fixture;

import com.endava.petclinic.client.OwnerClient;
import com.endava.petclinic.client.PetClient;
import com.endava.petclinic.model.Owner;
import com.endava.petclinic.testData.TestDataProvider;
import io.restassured.response.Response;
import lombok.Getter;
import org.apache.http.HttpStatus;

public class PetClinicFixture {

    private OwnerClient ownerClient = new OwnerClient();
    private PetClient petClinic = new PetClient();

    private TestDataProvider dataProvider = new TestDataProvider();

    @Getter
    private Owner owner;

    public PetClinicFixture createOwner() {

        owner = dataProvider.getOwner();
        Response response = ownerClient.createOwner(owner);
        response.then().statusCode(HttpStatus.SC_CREATED);

        long id = response.body().jsonPath().getLong("id");
        owner.setId(id);

        return this;
    }

    public PetClinicFixture getOwnerByID() {

        Response response = ownerClient.getOwnerByID(this.owner.getId());
        response.then().statusCode(HttpStatus.SC_OK);

        return this;
    }

    public PetClinicFixture deleteOwnerByID(){

        Response response = ownerClient.deleteOwnerById(this.owner.getId());
        response.then().statusCode(HttpStatus.SC_NO_CONTENT);
        return this;
    }

    public PetClinicFixture updateOwnerByID(){
        Response response = ownerClient.updateOwnerById(this.owner.getId(), this.owner);
        response.then().statusCode(HttpStatus.SC_NO_CONTENT);

        return this;
    }


}
