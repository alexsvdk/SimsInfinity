package ru.temoteam.simsinfinity.data.models;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class User implements Serializable
{

    private String username;
    private long birtday;
    private String pic;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 1501376171939821091L;

    public static User fromMap(Map<String,Object> map){
        User user = new User();
        if (map.containsKey("username"))
            user.setUsername((String)map.get("username"));
        if (map.containsKey("birtday"))
            user.setBirtday((Long)map.get("birtday"));
        if (map.containsKey("pic"))
            user.setPic((String)map.get("pic"));
        return user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public User withUsername(String username) {
        this.username = username;
        return this;
    }

    public long getBirtday() {
        return birtday;
    }

    public void setBirtday(long birtday) {
        this.birtday = birtday;
    }

    public User withBirtday(long birtday) {
        this.birtday = birtday;
        return this;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public User withPic(String pic) {
        this.pic = pic;
        return this;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public User withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}