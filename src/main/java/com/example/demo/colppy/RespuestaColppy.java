package com.example.demo.colppy;

import top.jfunc.json.impl.JSONObject;

public class RespuestaColppy {
    private String service;
    private String result;
    private String response;


    public void setService(String service) {
        this.service = service;
    }

    public String getService() {
        return this.service;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getResult() {
        return this.result;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getResponse() {
        return this.response;
    }

    @Override
    public String toString(){
        return this.service + this.result + this.response;
    }
    
    public void setRespuesta(JSONObject jsonOject){
        this.service =  jsonOject.getString("service");
        this.result =  jsonOject.getString("result");
        this.response = jsonOject.getString("response");
    }

}
