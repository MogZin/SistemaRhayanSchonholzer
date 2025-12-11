package dao;

import bean.RpsClientes;
import bean.RpsVendas;
import bean.RpsVendedor;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

public class VendasDAO extends AbstractDAO {

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
        Criteria criteria = session.createCriteria(RpsVendas.class);
        criteria.add(Restrictions.eq("rpsIdVendas", codigo));
        List lista = criteria.list();
        session.getTransaction().commit();
        return lista;
    }

    public Object listCliente(RpsClientes rpsClientes) {
        session.beginTransaction();
        Criteria criteria = session.createCriteria(RpsVendas.class);
        criteria.add(Restrictions.eq("rpsClientes", rpsClientes));
        List lista = criteria.list();
        session.getTransaction().commit();
        return lista;
    }

    public Object listVendedor(RpsVendedor rpsVendedor) {
        session.beginTransaction();
        Criteria criteria = session.createCriteria(RpsVendas.class);
        criteria.add(Restrictions.eq("rpsVendedor", rpsVendedor));
        List lista = criteria.list();
        session.getTransaction().commit();
        return lista;
    }

    public Object listClienteVendedor(RpsClientes rpsClientes, RpsVendedor rpsVendedor) {
        session.beginTransaction();
        Criteria criteria = session.createCriteria(RpsVendas.class);
        criteria.add(Restrictions.eq("rpsClientes", rpsClientes));
        criteria.add(Restrictions.eq("rpsVendedor", rpsVendedor));
        List lista = criteria.list();
        session.getTransaction().commit();
        return lista;
    }

    @Override
    public Object listAll() {
        session.beginTransaction();
        Criteria criteria = session.createCriteria(RpsVendas.class);
        List lista = criteria.list();
        session.getTransaction().commit();
        return lista;
    }

    public static void main(String[] args) {
        VendasDAO vendasDAO = new VendasDAO();
        vendasDAO.listAll();
    }

}
