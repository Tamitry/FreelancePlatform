package by.tarlikovski.freelance.bean;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

public class User extends Entity {
    private Role role;
    private String firstName;
    private String lastName;
    private Timestamp regDate;
    private String email;
    private String login;
    private String password;

    public User() {
    }

    public User(
            final Role r,
            final int id,
            final String fn,
            final String ln,
            final Timestamp rd,
            final String em,
            final String lg,
            final String pass
    ) {
        super(id);
        role = r;
        firstName = fn;
        lastName = ln;
        regDate = rd;
        email = em;
        login = lg;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(final Role r) {
        this.role = r;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String fn) {
        this.firstName = fn;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String ln) {
        this.lastName = ln;
    }

    public Timestamp getRegDate() {
        return regDate;
    }

    public void setRegDate(final Timestamp rd) {
        this.regDate = rd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String em) {
        this.email = em;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(final String lg) {
        this.login = lg;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String pass) {
        this.password = pass;
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
        User user = (User) o;
        return getRole() == user.getRole() &&
                getFirstName().equals(user.getFirstName()) &&
                getLastName().equals(user.getLastName()) &&
                Objects.equals(getRegDate(), user.getRegDate()) &&
                getEmail().equals(user.getEmail()) &&
                getLogin().equals(user.getLogin()) &&
                getPassword().equals(user.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRole(), getFirstName(),
                getLastName(), getRegDate(), getEmail(),
                getLogin(), getPassword());
    }
}
