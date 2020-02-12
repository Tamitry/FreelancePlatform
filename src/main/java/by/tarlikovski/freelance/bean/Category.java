package by.tarlikovski.freelance.bean;

import java.util.Objects;

public class Category extends Entity {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(final String n) {
        this.name = n;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Category category = (Category) o;
        return getName().equals(category.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getName());
    }
}
