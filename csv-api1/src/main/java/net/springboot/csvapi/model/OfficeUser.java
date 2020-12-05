package net.springboot.csvapi.model;

import javax.persistence.*;


@Entity
@Table(name = "Office_users")
public class OfficeUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "sign_in_name")
    private String signInName;

    @Column(name = "display_name")
    private String displayName;

    @Column(name = "is_licensed")
    private boolean isLicensed;

    @Column(name = "when_created")
    private String whenCreated;


    public OfficeUser() {
    }

    public OfficeUser(String signInName, String displayName, boolean isLicensed, String whenCreated) {
        this.signInName = signInName;
        this.displayName = displayName;
        this.isLicensed = isLicensed;
        this.whenCreated = whenCreated;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSignInName() {
        return signInName;
    }

    public void setSignInName(String signInName) {
        this.signInName = signInName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public boolean isLicensed() {
        return isLicensed;
    }

    public void setLicensed(boolean licensed) {
        isLicensed = licensed;
    }

    public String getWhenCreated() {
        return whenCreated;
    }

    public void setWhenCreated(String whenCreated) {
        this.whenCreated = whenCreated;
    }
}

