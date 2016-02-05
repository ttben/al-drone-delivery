package fr.unice.polytech.al.drones.tour;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by user on 31/01/2016.
 */
public class Dependency {

    public Dependency() {
    }

    private String name;
    private LinkedHashMap node;

    private Map<String,Object> other = new LinkedHashMap<String,Object>();

    @Override
    public String toString() {
        return other.toString();
    }


    @JsonAnySetter
    public void anysetter(String key, Object value){
        other.put(key,value);
    }

    @JsonAnyGetter
    public Map<String,Object> any(){
        return other;
    }
}
