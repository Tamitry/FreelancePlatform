package by.tarlikovski.freelance.entities;

import java.util.Calendar;
import java.util.List;

/**
 * @author Tarlikovski
 * @since 16.11.2019
 * @version 1.0
 */
public class Freelancer extends User {
    private List<String> skills;

    public Freelancer() {
    }

    public Freelancer(final String firstName, final String lastName,
                      final Calendar regDate, final String email,
                      final String login, final int password,
                      final short role, final List<String> skls) {
        super(firstName, lastName, regDate, email, login, password, role);
        this.skills = skls;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(final List<String> skls) {
        this.skills = skls;
    }
}
