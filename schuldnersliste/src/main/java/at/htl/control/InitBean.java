package at.htl.control;

import at.htl.entity.Account;
import at.htl.entity.Debtor;
import at.htl.entity.Entry;
import io.quarkus.runtime.StartupEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.time.LocalDate;
import java.time.LocalDateTime;

@ApplicationScoped
public class InitBean {

    @Inject
    AccountRepository accountRepository;

    @Inject
    DebtorRepository debtorRepository;

    @Inject
    EntryRepository entryRepository;

    public void init(@Observes StartupEvent event) {
        Account jonas = new Account("Jonas", "Rosenweg", "+43 664 12345678", "jonas@dorfingerjonas.at");
        Account karl = new Account("Karl", "Hinterholzweg", "+43 664 12345678", "karl@dorfingerjonas.at");
        Account ferdinand = new Account("Ferdinand", "Seeweg", "+43 664 12345678", "ferdinand@dorfingerjonas.at");

        jonas = accountRepository.save(jonas);
        karl = accountRepository.save(karl);
        ferdinand = accountRepository.save(ferdinand);

        Debtor rosi = debtorRepository.save(new Debtor("Rosi", jonas));
        Debtor franz = debtorRepository.save(new Debtor("Franz", jonas));
        Debtor flo = debtorRepository.save(new Debtor("Flo", karl));
        Debtor hannah = debtorRepository.save(new Debtor("Hannah", ferdinand));
        Debtor basti = debtorRepository.save(new Debtor("Basti", ferdinand));

        entryRepository.save(new Entry(
                LocalDate.now(),
                69d,
                "Lebensmittel",
                false,
                hannah
        ));

        entryRepository.save(new Entry(
                LocalDate.now(),
                102d,
                "dildos",
                false,
                rosi
        ));

        entryRepository.save(new Entry(
                LocalDate.now(),
                20d,
                "gartenzwerge",
                false,
                rosi
        ));

        entryRepository.save(new Entry(
                LocalDate.now(),
                2d,
                "muscheln",
                false,
                franz
        ));
    }
}
