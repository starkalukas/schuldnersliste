package at.htl.control;

import at.htl.dto.DebtorDto;
import at.htl.dto.EntryDto;
import at.htl.entity.Debtor;
import at.htl.entity.Entry;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@ApplicationScoped
public class DebtorRepository implements PanacheRepository<Debtor> {


    @Inject
    EntryRepository entryRepository;

    @Transactional
    public Debtor save(Debtor debtor) {
        return getEntityManager().merge(debtor);
    }

    public List<DebtorDto> getDebtorsByAccount(Long id) {
        return find("account.id", id).list()
                .stream()
                .map(this::makeDebtorDTO)
                .toList();
    }

    public DebtorDto makeDebtorDTO(Debtor debtor) {
        return new DebtorDto(debtor.id, debtor.name, getAmountByDebtor(debtor.id));
    }

    private Double getAmountByDebtor(Long id) {
        return entryRepository.getAmountByDebtor(id);

    }
}
