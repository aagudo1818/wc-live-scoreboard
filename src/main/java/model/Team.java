package model;

import java.util.Objects;

public class Team {
    private String name;
    private String abbreviation;

    public Team(String name, String abbreviation) {
        this.name = name;
        this.abbreviation = abbreviation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Team team)) return false;
        return Objects.equals(getName(), team.getName()) && Objects.equals(getAbbreviation(), team.getAbbreviation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getAbbreviation());
    }

    public void trimProperties() {
        this.name = name.trim();
        this.abbreviation = abbreviation.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }
}
