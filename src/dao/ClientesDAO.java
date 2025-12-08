package dao;

import bean.RpsClientes;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

public class ClientesDAO extends AbstractDAO {

    @Override
    public void insert(Object object) {
        session.beginTransaction();
        session.save(object);
        session.getTransaction().commit();
    }

    @Override
    public void update(Object object) {
        session.beginTransaction();
        session.flush();
        session.clear();
        session.update(object);
        session.getTransaction().commit();
    }

    @Override
    public void delete(Object object) {
        session.beginTransaction();
        session.flush();
        session.clear();
        session.delete(object);
        session.getTransaction().commit();
    }

    @Override
    public Object list(int codigo) {
        session.beginTransaction();
        Criteria criteria = session.createCriteria(RpsClientes.class);
        criteria.add(Restrictions.eq("rpsIdclientes", codigo));
        List lista = criteria.list();
        session.getTransaction().commit();
        return lista;
    }

    public Object listNome(String nome) {
        session.beginTransaction();
        Criteria criteria = session.createCriteria(RpsClientes.class);
        criteria.add(Restrictions.like("rpsNome", "%" + nome + "%"));
        List lista = criteria.list();
        session.getTransaction().commit();
        return lista;
    }

    public Object listCidade(String cidade) {
        session.beginTransaction();
        Criteria criteria = session.createCriteria(RpsClientes.class);
        criteria.add(Restrictions.like("rpsCidade", "%" + cidade + "%"));
        List lista = criteria.list();
        session.getTransaction().commit();
        return lista;
    }

    public Object listNomeCidade(String nome, String cidade) {
        session.beginTransaction();
        Criteria criteria = session.createCriteria(RpsClientes.class);
        criteria.add(Restrictions.like("rpsNome", "%" + nome + "%"));
        criteria.add(Restrictions.like("rpsCidade", "%" + cidade + "%"));
        List lista = criteria.list();
        session.getTransaction().commit();
        return lista;
    }

    @Override
    public Object listAll() {
        session.beginTransaction();
        Criteria criteria = session.createCriteria(RpsClientes.class);
        List lista = criteria.list();
        session.getTransaction().commit();
        return lista;
    }

    public static void main(String[] args) {
        ClientesDAO clientesDAO = new ClientesDAO();
        clientesDAO.listAll();
    }

}
