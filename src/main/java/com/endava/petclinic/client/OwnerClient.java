package com.endava.petclinic.client;

import com.endava.petclinic.model.Owner;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class OwnerClient extends BaseClient {
    //api call catre server = client


    public Response createOwner(Owner owner) {

        return getBasicRestConfig()
                .contentType(ContentType.JSON)
                .body(owner)
                .post("/api/owners"); //post intoarce un Response, adica ce intorc eu in metoda

    }

    public Response getOwnerByID(long ownerId) {
        return getBasicRestConfig()
                .pathParam("ownerId", ownerId)
                /*  .when() */ //optional
                .get("api/owners/{ownerId}");
    }

    public Response getOwnerList() {
        return getBasicRestConfig()
                .when()
                .get("api/owners");
    }

    public Response deleteOwnerById(Long ownerId) {
        return getBasicRestConfig()
                .pathParam("ownerId", ownerId)
                .delete("api/owners/{ownerId}");
    }

    public Response updateOwnerById(Long ownerId, Owner owner) {
        return getBasicRestConfig()
                .pathParam("ownerId", ownerId)
                .contentType(ContentType.JSON)
                .body(owner)
                .put("/api/owners/{ownerId}");
    }
}
