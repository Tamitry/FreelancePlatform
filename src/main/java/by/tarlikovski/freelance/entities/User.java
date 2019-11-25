package by.tarlikovski.freelance.entities;

import java.util.Calendar;

/**
 * Order class.
 *
 * @author Tarlikovski
 * @since 16.11.2019
 * @version 1.0
 */
public class User extends Entity {
    private String firstName;
    private String lastName;
    /**
     * Date of registration.
     */
    private Calendar regDate;
    /**
     * E-mail.
     */
    private String email;
    /**
     * Login.
     */
    private String login;
    /**
     * Password.
     */
    private String password;
    /**
     * Roles:
     * 1. Administrator;
     * 2. Freelancer;
     * 3. Client.
     */
    private short role;

    public User() {
    }

    public User(final String first, final String last, final Calendar reg,
                final String e, final String log, final String pass,
                final short r) {
        this.firstName = first;
        this.lastName = last;
        this.regDate = reg;
        this.email = e;
        this.login = log;
        this.password = pass;
        this.role = r;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String first) {
        this.firstName = first;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String last) {
        this.lastName = last;
    }

    public Calendar getRegDate() {
        return regDate;
    }

    public void setRegDate(final Calendar reg) {
        this.regDate = reg;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String e) {
        this.email = e;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(final String log) {
        this.login = log;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String pass) {
        this.password = pass;
    }

    public short getRole() {
        return role;
    }

    public void setRole(final short r) {
        this.role = r;
    }
}
