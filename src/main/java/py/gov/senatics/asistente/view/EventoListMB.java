package py.gov.senatics.asistente.view;

import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import org.ticpy.tekoporu.annotation.NextView;
import org.ticpy.tekoporu.annotation.PreviousView;
import org.ticpy.tekoporu.stereotype.ViewController;
import org.ticpy.tekoporu.template.AbstractListPageBean;
import org.ticpy.tekoporu.transaction.Transactional;

import py.gov.senatics.asistente.business.EventoBC;
import py.gov.senatics.asistente.domain.Evento;

@ViewController
@NextView("./evento_edit.jsf")
@PreviousView("./evento_list.jsf")
public class EventoListMB extends AbstractListPageBean<Evento, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private EventoBC eventoBC;
	
	@Override
	protected List<Evento> handleResultList() {
		return this.eventoBC.findAll();
	}
	
	@Transactional
	public String deleteSelection() {
		boolean delete;
		for (Iterator<Long> iter = getSelection().keySet().iterator(); iter.hasNext();) {
			Long id = iter.next();
			delete = getSelection().get(id);
			if (delete) {
				eventoBC.delete(id);
				iter.remove();
			}
		}
		return getPreviousView();
	}

}