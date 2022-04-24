package at.htl.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.security.User;

import javax.persistence.*;


@Entity
@Table(name = "BC_DEBTOR")
public class Debtor extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DE_ID")
    public Long id;

    @ManyToOne
    @JoinColumn(name = "DE_ACCOUNT")
    public Account account;

    @Column(name = "AC_NAME")
    public String name;

    public Debtor() {
    }

    public Debtor(String name, Account account) {
        this.account = account;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Debtor{" +
                "id=" + id +
                ", account=" + account +
                ", name='" + name + '\'' +
                '}';
    }
}