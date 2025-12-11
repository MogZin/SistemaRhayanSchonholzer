package dao;

import bean.RpsVendedor;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

public class VendedorDAO extends AbstractDAO {

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
        Criteria criteria = session.createCriteria(RpsVendedor.class);
        criteria.add(Restrictions.eq("rpsIdvendedor", codigo));
        List lista = criteria.list();
        session.getTransaction().commit();
        return lista;
    }

    public Object listNome(String nome) {
        session.beginTransaction();
        Criteria criteria = session.createCriteria(RpsVendedor.class);
        criteria.add(Restrictions.like("rpsNome", "%" + nome + "%"));
        List lista = criteria.list();
        session.getTransaction().commit();
        return lista;
    }

    public Object listSalario(double salario) {
        session.beginTransaction();
        Criteria criteria = session.createCriteria(RpsVendedor.class);
        criteria.add(Restrictions.ge("rpsSalario", salario));
        List lista = criteria.list();
        session.getTransaction().commit();
        return lista;
    }

    public Object listNomeSalario(String nome, double salario) {
        session.beginTransaction();
        Criteria criteria = session.createCriteria(RpsVendedor.class);
        criteria.add(Restrictions.like("rpsNome", "%" + nome + "%"));
        criteria.add(Restrictions.ge("rpsSalario", salario));
        List lista = criteria.list();
        session.getTransaction().commit();
        return lista;
    }

    @Override
    public Object listAll() {
        session.beginTransaction();
        Criteria criteria = session.createCriteria(RpsVendedor.class);
        List lista = criteria.list();
        session.getTransaction().commit();
        return lista;
    }

    public static void main(String[] args) {
        VendedorDAO vendedorDAO = new VendedorDAO();
        vendedorDAO.listAll();
    }

}
