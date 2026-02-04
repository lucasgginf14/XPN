package es.udc.fic.xpn.warehouse;

import java.util.Objects;

public class Warehouse {

    private Integer id;
    private String name;
    private String location;

    public Warehouse(Integer id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }

    public Warehouse() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Warehouse warehouse = (Warehouse) o;
        return warehouse.hashCode() == this.hashCode();
    }

    @Override
    public int hashCode() {return Objects.hash(id, name, location);}
}
