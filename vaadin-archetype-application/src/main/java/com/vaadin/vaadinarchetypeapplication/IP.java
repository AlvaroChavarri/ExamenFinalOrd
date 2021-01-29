package com.vaadin.vaadinarchetypeapplication;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
public class IP {
    @Id
    @GeneratedValue
    private Long id;

    private String countrycode;

    private String countryname;

    private String regionname;

    private String cityname;

    private String latitude;

    private String longitud;

    private String zipcode;

    private String timezone;

    protected IP() {
    }

    public IP(String countrycode, String countryname,String regionname,String cityname, String latitude, String longitud, String zipcode,String timezone) {
        this.countrycode = countrycode;
        this.countryname= countryname;
        this.regionname = regionname;
        this.cityname=cityname;
        this.latitude=latitude;
        this.longitud=longitud;
        this.zipcode=zipcode;
        this.timezone=timezone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountrycode() {
        return countrycode;
    }

    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode;
    }

    public String getCountryname() {
        return countryname;
    }

    public void setCountryname(String countryname) {
        this.countryname = countryname;
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public String getRegionname() {
        return regionname;
    }

    public void setRegionname(String regionname) {
        this.regionname = regionname;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }
    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }


    @Override
    public String toString() {
        return String.format("IP[id=%d, countrycode='%s', countryname='%s',regionname='%s',cityname='%s',latitude='%s',longitude='%s',zipcode='%s',timezone='%s']", id,
                countrycode, countryname,regionname,cityname,latitude,longitud,zipcode,timezone);

    }
}
