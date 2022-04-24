package at.htl.control;

import at.htl.dto.EntryDto;
import at.htl.entity.Entry;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class EntryRepository implements PanacheRepository<Entry> {

    @Transactional
    public Entry save(Entry entry) {
        return getEntityManager().merge(entry);
    }

    public List<EntryDto> getEntriesByDebtor(Long id) {
        return find("debtor.id", id)
                .stream()
                .map(this::createEntryDto)
                .toList();
    }

    public Double getAmountByDebtor(Long id) {
        return getEntityManager()
                .createNamedQuery("ENTRY.GETAMOUNT", Double.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    private EntryDto createEntryDto(Entry entry) {
        return new EntryDto(entry.id, entry.amount, entry.reason, entry.edited, entry.date);
    }
}
