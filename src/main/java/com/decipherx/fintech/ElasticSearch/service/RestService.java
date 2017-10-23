package com.decipherx.fintech.ElasticSearch.service;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestService implements Runnable{

    private static final String elasticSearchURL = "http://search-dol-2dcbis626hy5vivrb5nmejh2x4.us-west-2.es.amazonaws.com";

    private static final String DOL_ENDPOINT = "/dol/data/";
    private Integer id;
    private String data;

    public RestService() {
    }

    public RestService(Integer id, String data) {
        this.id = id;
        this.data = data;
    }

    public static void getDataFromElasticSearch(){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(elasticSearchURL + DOL_ENDPOINT +"1", String.class);
        System.out.println(response.getBody());
    }

    public void createOrUpdateNewDataInElasticSearch( Integer id,  String data){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(data,headers);
        ResponseEntity<String> response = restTemplate.exchange(elasticSearchURL + DOL_ENDPOINT + id, HttpMethod.PUT, entity, String.class);

        System.out.println(response.getBody());
    }


    public void searchByCriteria( String data){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(data,headers);
        ResponseEntity<String> response = restTemplate.exchange(elasticSearchURL + DOL_ENDPOINT + "_search", HttpMethod.POST, entity, String.class);

        System.out.println(response.getBody());
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        createOrUpdateNewDataInElasticSearch(id, data);
    }


//    public static void main(String[] args) {
//        //getDataFromElasticSearch();
//        createOrUpdateNewDataInElasticSearch("{\"ACK_ID\":\"20170720112620P030051192151001\",\"FORM_PLAN_YEAR_BEGIN_DATE\":\"2016-06-01\",\"FORM_TAX_PRD\":\"2017-05-31\",\"TYPE_PLAN_ENTITY_CD\":\"2\",\"INITIAL_FILING_IND\":\"0\",\"AMENDED_IND\":\"0\",\"FINAL_FILING_IND\":\"0\",\"SHORT_PLAN_YR_IND\":\"0\",\"COLLECTIVE_BARGAIN_IND\":\"0\",\"F5558_APPLICATION_FILED_IND\":\"0\",\"EXT_AUTOMATIC_IND\":\"0\",\"DFVC_PROGRAM_IND\":\"0\",\"EXT_SPECIAL_IND\":\"0\",\"PLAN_NAME\":\"BETH GALTON INC PROFIT SHARING PLAN\",\"SPONS_DFE_PN\":\"001\",\"PLAN_EFF_DATE\":\"1985-06-05\",\"SPONSOR_DFE_NAME\":\"BETH GALTON INC.\",\"SPONS_DFE_MAIL_US_ADDRESS1\":\"109 W 27TH ST\",\"SPONS_DFE_MAIL_US_CITY\":\"NEW YORK\",\"SPONS_DFE_MAIL_US_STATE\":\"NY\",\"SPONS_DFE_MAIL_US_ZIP\":\"100016208\",\"SPONS_DFE_LOC_US_ADDRESS1\":\"109 W 27TH ST\",\"SPONS_DFE_LOC_US_CITY\":\"NEW YORK\",\"SPONS_DFE_LOC_US_STATE\":\"NY\",\"SPONS_DFE_LOC_US_ZIP\":\"100016208\",\"SPONS_DFE_EIN\":\"133032716\",\"SPONS_DFE_PHONE_NUM\":\"2122422266\",\"BUSINESS_CODE\":\"541920\",\"ADMIN_SIGNED_DATE\":\"2017-07-20T11:22:20-0500\",\"ADMIN_SIGNED_NAME\":\"JOSEPH MILAZZO\",\"TOT_PARTCP_BOY_CNT\":3,\"TOT_ACTIVE_PARTCP_CNT\":2,\"SUBTL_ACT_RTD_SEP_CNT\":2,\"TOT_ACT_RTD_SEP_BENEF_CNT\":2,\"PARTCP_ACCOUNT_BAL_CNT\":2,\"SEP_PARTCP_PARTL_VSTD_CNT\":2,\"TYPE_PENSION_BNFT_CODE\":\"2E3B3F\",\"FUNDING_INSURANCE_IND\":\"0\",\"FUNDING_SEC412_IND\":\"0\",\"FUNDING_TRUST_IND\":\"1\",\"FUNDING_GEN_ASSET_IND\":\"0\",\"BENEFIT_INSURANCE_IND\":\"0\",\"BENEFIT_SEC412_IND\":\"0\",\"BENEFIT_TRUST_IND\":\"1\",\"BENEFIT_GEN_ASSET_IND\":\"0\",\"SCH_R_ATTACHED_IND\":\"0\",\"SCH_MB_ATTACHED_IND\":\"0\",\"SCH_SB_ATTACHED_IND\":\"0\",\"SCH_H_ATTACHED_IND\":\"0\",\"SCH_I_ATTACHED_IND\":\"1\",\"SCH_A_ATTACHED_IND\":\"0\",\"SCH_C_ATTACHED_IND\":\"0\",\"SCH_D_ATTACHED_IND\":\"0\",\"SCH_G_ATTACHED_IND\":\"0\",\"FILING_STATUS\":\"FILING_RECEIVED\",\"DATE_RECEIVED\":\"2017-07-20\",\"VALID_ADMIN_SIGNATURE\":\"Filed with authorized/valid electronic signature\",\"ADMIN_NAME_SAME_SPON_IND\":\"1\",\"PREPARER_NAME\":\"JOSEPH MILAZZO\",\"PREPARER_FIRM_NAME\":\"CUMMINGS & CARROLL PC\",\"PREPARER_US_ADDRESS1\":\"175 GREAT NECK RD\",\"PREPARER_US_CITY\":\"GREAT NECK\",\"PREPARER_US_STATE\":\"NY\",\"PREPARER_US_ZIP\":\"11021\",\"PREPARER_PHONE_NUM\":\"5164823260\",\"TOT_ACT_PARTCP_BOY_CNT\":2}");
//    }

}
