package com.intecap.apibooks.response;

import java.util.ArrayList;
import java.util.HashMap;

public class Response {
    /**
     *
     */
    private ArrayList<HashMap<String, String>> metadata = new ArrayList<>();

    public ArrayList<HashMap<String, String>> getMetadata(){
        return this.metadata;
    }

    public void setMetadata(String tipo, String codigo, String data){
        HashMap<String, String> mapa=new HashMap<>();
        mapa.put("tipo", tipo);
        mapa.put("codigo",codigo);
        mapa.put("dato", data);
        this.metadata.add(mapa);
    }

}
