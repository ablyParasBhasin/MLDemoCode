package com.app.recycler.apinetworks;


public interface Constants {

    String DOMAIN_NAME = "https://seedsimpact.uatserver.in"; //development
    String API_VERSION = "/api/v1/";
    String BASE_URL = DOMAIN_NAME + API_VERSION;
    String USER_DATA = "USER_DATA";

    //    API Status
    String API_FAILURE = "400", API_SUCCESS = "200", INVALID_TOKEN = "2", STONE_NOT_FOUND = "3", BELOW_MIN_BID = "2", KYC_PENDING = "4";


}
