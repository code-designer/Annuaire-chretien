package org.epbomi.personne.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.epbomi.personne.dao.DAO;
import org.epbomi.personne.dao.DAOFactory;
import org.epbomi.personne.patterns.Observable;
import org.epbomi.personne.patterns.Observer;

public class ModelData extends AbstractModel implements Observable {
	
	private Set<Personne> liste = new HashSet<>();
	private List<Observer> obs = new ArrayList<>();
	
	public ModelData() {}
	
	public ModelData(String str)
	{
		this.liste = DAOFactory.getPersonneDAO().getList();
	}
	
	public boolean savePersonne(Personne p)
	{
		DAO<Personne> dao = DAOFactory.getPersonneDAO();
		if(p.getPersonneID() == 0)
		{
			if(dao.create(p))
			{
				if(this.liste != null)
				{
					this.liste.add(p);
					this.updateObserverForAdding(p);
					return true;
				}
			}
		}
		else if(p.getPersonneID() > 0)
		{
			dao.update(p);
			this.updateObserverForUpdating(p);
			return true;
		}
		return false;
	}
	
	public void deletePersonne(Personne p)
	{
		DAO<Personne> dao = DAOFactory.getPersonneDAO();
		if(p.getPersonneID() > 0)
		{
			if(dao.delete(p))
				if(this.liste != null)
				{
					this.liste.remove(p);
					this.updateObserverForDeleting(p);
				}
		}
	}
	
	public Set<Personne> findPersonne(String str)
	{
		return liste.stream().filter(x -> x.toString().contains(str)).collect(Collectors.toSet());
	}
	
	public Set<Personne> getAll()
	{
		return this.liste = DAOFactory.getPersonneDAO().getList();
	}
	
	public boolean saveMinistere(Personne p)
	{
		DAO<Ministere> dao = DAOFactory.getMinistereDAO();
		
		if(p.getMinistere().getMinistereID() == 0)
		{
			return dao.create(p.getMinistere());
		}
		else if(p.getMinistere().getMinistereID() > 0)
		{
			return dao.update(p.getMinistere());
		}
		return false;
	}
	
	public boolean deleteMinistere(Personne p)
	{
		DAO<Ministere> dao = DAOFactory.getMinistereDAO();
		
		if(p.getMinistere().getMinistereID() > 0)
		{
			return dao.delete(p.getMinistere());
		}
		return false;
	}
	
	public boolean saveContact(Personne p)
	{
		DAO<Contact> dao = DAOFactory.getContactDAO();
		
		if(p.getContact().getContactID() == 0)
		{
			return dao.create(p.getContact());
		}
		else if(p.getContact().getContactID() > 0)
		{
			return dao.update(p.getContact());
		}
		return false;
	}
	
	public boolean deleteContact(Personne p)
	{
		DAO<Contact> dao = DAOFactory.getContactDAO();
		
		if(p.getContact().getContactID() > 0)
		{
			return dao.delete(p.getContact());
		}
		return false;
	}
	
	public boolean saveProfession(Personne p)
	{
		DAO<Profession> dao = DAOFactory.getProfessionDAO();
		
		if(p.getProfession().getProfessionID() == 0)
		{
			return dao.create(p.getProfession());
		}
		else if(p.getProfession().getProfessionID() > 0)
		{
			return dao.update(p.getProfession());
		}
		return false;
	}
	
	public boolean deleteProfession(Personne p)
	{
		DAO<Profession> dao = DAOFactory.getProfessionDAO();
		
		if(p.getProfession().getProfessionID() > 0)
		{
			return dao.delete(p.getProfession());
		}
		return false;
	}
	
	public boolean saveVieConjugale(Personne p)
	{
		DAO<VieConjugale> dao = DAOFactory.getVieConjugaleDAO();
		
		if(p.getVieConjugale().getVieConjugaleID() == 0)
		{
			return dao.create(p.getVieConjugale());
		}
		else if(p.getVieConjugale().getVieConjugaleID() > 0)
		{
			return dao.update(p.getVieConjugale());
		}
		return false;
	}
	
	public boolean deleteVieConjugale(Personne p)
	{
		DAO<VieConjugale> dao = DAOFactory.getVieConjugaleDAO();
		
		if(p.getVieConjugale().getVieConjugaleID() > 0)
		{
			return dao.delete(p.getVieConjugale());
		}
		return false;
	}
	
	public Set<Personne> getListByMinistere(String departement)
	{
		
		return this.liste.stream()
				.filter(x -> x.getMinistere().getDepartement().equals(departement)).collect(Collectors.toSet());
	}
	
	public Set<Personne> getListBySection(Eglise section)
	{
		return this.liste.stream()
				.filter(x -> x.getMinistere().getSection().equals(section)).collect(Collectors.toSet());
	}
	
	public Set<Personne> getListBySexe(Sexe sexe)
	{
		return this.liste.stream()
				.filter(x -> x.getSexe().equals(sexe)).collect(Collectors.toSet());
	}
	
	public Set<Personne> getListByStatut(Statut statut)
	{
		return this.liste.stream()
				.filter(x -> x.getVieConjugale().getSituation().equals(statut)).collect(Collectors.toSet());
	}
	
	public Set<Personne> getListByEthnie(String ethnie)
	{
		return this.liste.stream()
				.filter(x -> x.getEthnie().equals(ethnie)).collect(Collectors.toSet());
	}
	
	public Set<Personne> getListByBapteme()
	{
		return this.liste.stream()
				.filter(x -> x.getMinistere().getDateBapteme() != null).collect(Collectors.toSet());
	}

	@Override
	public void addObservers(Observer obs) {
		// TODO Auto-generated method stub
		this.obs.add(obs);
	}

	@Override
	public void delObserver(Observer obs) {
		// TODO Auto-generated method stub
		this.obs.remove(obs);
	}

	@Override
	public void updateObserverForAdding(Personne p) {
		// TODO Auto-generated method stub
		this.obs.forEach(x -> x.updateAdding(p));
	}

	@Override
	public void updateObserverForUpdating(Personne p) {
		// TODO Auto-generated method stub
		this.obs.forEach(x -> x.updateUpdating(p));
	}

	@Override
	public void updateObserverForDeleting(Personne p) {
		// TODO Auto-generated method stub
		this.obs.forEach(x -> x.updateDeleting(p));
	}

	@Override
	public void updateObserverForScreen() {
		// TODO Auto-generated method stub
		this.obs.forEach(x -> x.updateScreen());
	}
	
}
