package at.htl.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;

@Entity
@Table(name = "BC_ACCOUNT")
@SequenceGenerator(name = "AC_SEQ", initialValue = 1000)
public class Account extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AC_SEQ")
    @Column(name = "AC_ID")
    public Long id;

    @Column(name = "AC_ADDRESS")
    public String address;

    @Column(name = "AC_PHONE")
    public String phone;

    @Column(name = "AC_EMAIL")
    public String email;

    @Column(name = "AC_NAME")
    public String name;

    public Account() {
    }

    public Account(String name, String address, String phone, String email) {
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}