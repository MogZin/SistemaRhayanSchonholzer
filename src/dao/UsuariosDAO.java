package dao;

import bean.RpsUsuarios;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

public class UsuariosDAO extends AbstractDAO {

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
        Criteria criteria = session.createCriteria(RpsUsuarios.class);
        criteria.add(Restrictions.eq("rpsIdusuarios", codigo));
        List lista = criteria.list();
        session.getTransaction().commit();
        return lista;
    }

    @Override
    public Object listAll() {
        session.beginTransaction();
        Criteria criteria = session.createCriteria(RpsUsuarios.class);
        List lista = criteria.list();
        session.getTransaction().commit();
        return lista;
    }

    public Object listNome(String nome) {
        session.beginTransaction();
        Criteria criteria = session.createCriteria(RpsUsuarios.class);
        criteria.add(Restrictions.like("rpsNome", "%" + nome + "%"));
        List lista = criteria.list();
        session.getTransaction().commit();
        return lista;
    }

    public Object listApelido(String apelido) {
        session.beginTransaction();
        Criteria criteria = session.createCriteria(RpsUsuarios.class);
        criteria.add(Restrictions.like("rpsApelido", "%" + apelido + "%"));
        List lista = criteria.list();
        session.getTransaction().commit();
        return lista;
    }

    public Object listNomeApelido(String nome, String apelido) {
        session.beginTransaction();
        Criteria criteria = session.createCriteria(RpsUsuarios.class);
        criteria.add(Restrictions.like("rpsNome", "%" + nome + "%"));
        criteria.add(Restrictions.like("rpsApelido", "%" + apelido + "%"));
        List lista = criteria.list();
        session.getTransaction().commit();
        return lista;
    }

    public RpsUsuarios verificarLogin(String apelido, String senha) {
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(RpsUsuarios.class);
            criteria.add(Restrictions.eq("rpsApelido", apelido));
            criteria.add(Restrictions.eq("rpsSenha", senha));
            RpsUsuarios usuario = (RpsUsuarios) criteria.uniqueResult();
            session.getTransaction().commit();
            return usuario;
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            throw e;
        }
    }

    public static void main(String[] args) {
        UsuariosDAO usuariosDAO = new UsuariosDAO();
        usuariosDAO.listAll();
    }
}
