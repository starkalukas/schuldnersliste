package at.htl.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@NamedQueries({
        @NamedQuery(name = "ENTRY.GETAMOUNT", query = "select sum(e.amount) from Entry e where e.debtor.id = :id")
})
@Entity
@Table(name = "BC_ENTRY")
public class Entry extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EN_ID")
    public Long id;

    @Column(name = "EN_date")
    public LocalDate date;

    @Column(name = "EN_AMOUNT")
    public Double amount;

    @Column(name = "EN_REASON")
    public String reason;

    @Column(name = "EN_EDITED")
    public Boolean edited;

    @ManyToOne
    @JoinColumn(name = "EN_DEBTOR")
    public Debtor debtor;

    public Entry(LocalDate date, Double amount, String reason, Boolean edited, Debtor debtor) {
        this.date = date;
        this.amount = amount;
        this.reason = reason;
        this.edited = edited;
        this.debtor = debtor;
    }

    public Entry() {
    }

    @Override
    public String toString() {
        return "Entry{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", amount=" + amount +
                ", reason='" + reason + '\'' +
                ", edited=" + edited +
                ", debtor=" + debtor +
                '}';
    }
}