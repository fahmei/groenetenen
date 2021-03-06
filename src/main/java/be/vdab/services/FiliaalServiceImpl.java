package be.vdab.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import be.vdab.dao.FiliaalDAO;
import be.vdab.entities.Filiaal;
import be.vdab.exceptions.FiliaalHeeftNogWerknemersException;
import be.vdab.mail.MailSender;
import be.vdab.valueobjects.PostcodeReeks;

@Service
@ReadOnlyTransactionalService
class FiliaalServiceImpl implements FiliaalService {

	private final FiliaalDAO filiaalDAO;
	private final MailSender mailSender;

	@Autowired
	FiliaalServiceImpl(FiliaalDAO filiaalDAO, MailSender mailSender) {
		this.filiaalDAO = filiaalDAO;
		this.mailSender = mailSender;
	}

	@Override
	@ModifyingTransactionalServiceMethod
	public void create(Filiaal filiaal, String urlAlleFilialen) {
		filiaalDAO.save(filiaal);
		mailSender.nieuwFiliaalMail(filiaal, urlAlleFilialen + '/' + filiaal.getId());
	}

	@Override
	public Filiaal read(long id) {
		return filiaalDAO.findOne(id);
	}

	@Override
	@ModifyingTransactionalServiceMethod
	public void update(Filiaal filiaal) {
		filiaalDAO.save(filiaal);
	}

	@Override
	@ModifyingTransactionalServiceMethod
	public void delete(long id) {
		Filiaal filiaal = filiaalDAO.findOne(id);
		if (filiaal != null) {
			if (!filiaal.getWerknemers().isEmpty()) {
				throw new FiliaalHeeftNogWerknemersException();
			}
			filiaalDAO.delete(id);
		}
	}

	@Override
	public List<Filiaal> findAll() {
		return filiaalDAO.findAll(new Sort("naam"));
	}

	@Override
	public long findAantalFilialen() {
		return filiaalDAO.count();
	}

	@Override
	@PreAuthorize("hasAuthority('manager')")
	public List<Filiaal> findByPostcodeReeks(PostcodeReeks reeks) {
		return filiaalDAO.findByAdresPostcodeBetweenOrderByNaam(reeks.getVanPostcode(), reeks.getTotPostcode());
	}

	@Override
	public List<Filiaal> findNietAfgeschreven() {
		return filiaalDAO.findByWaardeGebouwNot(BigDecimal.ZERO);
	}

	@Override
	@ModifyingTransactionalServiceMethod
	public void afschrijven(Iterable<Filiaal> filialen) {
		for (Filiaal filiaal : filialen) {
			filiaal.afschrijven();
		}
	}

	@Override	
	//@Scheduled(/*cron = "0 0 1 * * *" */ fixedRate=60000)
	public void aantalFilialenMail(long aantal) {
		mailSender.aantalFilialenMail(filiaalDAO.count());
	}

}
