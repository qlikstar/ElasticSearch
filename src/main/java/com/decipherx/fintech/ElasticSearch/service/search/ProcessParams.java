package com.decipherx.fintech.ElasticSearch.service.search;

import java.util.StringJoiner;

public class ProcessParams {

    private String dataString =
            "{\n" +
                    "   \"query\": {\n" +
                    "    \"bool\": {\n" +
                    "      \"should\": [\n" +
                    "$DATASTRING$" +
                    "\t      ]\n" +
                    "\t    }\n" +
                    "\t  }\n" +
                    "   ,\n" +
                    "   \"sort\": { \"FORM_PLAN_YEAR_BEGIN_DATE\": { \"order\": \"desc\" } }\n" +
                    //"   \"_source\": [\"SPONSOR_DFE_NAME\", \"PLAN_NAME\"]\n" +
                    "}";

    public String getDataString() {
        return dataString;
    }

    public void setDataString(String dataString) {
        this.dataString = dataString;
    }

    public ProcessParams(String planName, String sponsorName, String sponsorState) {

        StringJoiner dataValues = new StringJoiner(", ");

        if (!planName.trim().equals(""))
            dataValues.add("{ \"match\": { \"PLAN_NAME\": \"" + planName +  "\"} }");

        if (!sponsorName.trim().equals(""))
            dataValues.add("{ \"match\": { \"SPONSOR_DFE_NAME\": \"" + sponsorName +  "\"} }\n");

        if (!sponsorState.trim().equals(""))
            dataValues.add("{ \"match\": { \"SPONS_DFE_MAIL_US_STATE\": \"" + sponsorState +  "\"} }\n");

        dataString = dataString.replace("$DATASTRING$" , dataValues.toString());
        //System.out.println(dataString);


    }
}
