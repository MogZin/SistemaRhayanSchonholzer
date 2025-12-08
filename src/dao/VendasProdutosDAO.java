package dao;

import bean.RpsVendas;
import bean.RpsVendasProdutos;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

public class VendasProdutosDAO extends AbstractDAO {

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
     public void deleteProdutos(RpsVendas rpsVendas) {
        //listar todos os produtos do pedido
        List lista = (List) listProdutos(rpsVendas);
        //deleta  a lista acima 
        session.beginTransaction();
        for (int i = 0; i < lista.size(); i++) {
            RpsVendasProdutos rpsVendasProdutos = (RpsVendasProdutos) lista.get(i);
            //delete(pedidosProdutos);
            session.flush();
            session.clear();
            session.delete(rpsVendasProdutos);
        }
        session.getTransaction().commit();
    }

    @Override
    public Object list(int codigo) {
        session.beginTransaction();
        Criteria criteria = session.createCriteria(RpsVendasProdutos.class);
        criteria.add(Restrictions.eq("rpsId", codigo));
        List lista = criteria.list();
        session.getTransaction().commit();
        return lista;
    }

    public Object listProdutos(RpsVendas rpsVendas) {
        session.beginTransaction();
        Criteria criteria = session.createCriteria(RpsVendasProdutos.class);
        criteria.add(Restrictions.eq("rpsVendas", rpsVendas));
        List lista = criteria.list();
        session.getTransaction().commit();
        return lista;
    }

    @Override
    public Object listAll() {
        session.beginTransaction();
        Criteria criteria = session.createCriteria(RpsVendasProdutos.class);
        List lista = criteria.list();
        session.getTransaction().commit();
        return lista;
    }

    public static void main(String[] args) {
        VendasProdutosDAO vendasProdutosDAO = new VendasProdutosDAO();
        vendasProdutosDAO.listAll();
    }

}
