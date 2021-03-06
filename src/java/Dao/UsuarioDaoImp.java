/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Usuario;
import Utils.HibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author thayseonofrio
 */
public class UsuarioDaoImp implements UsuarioDao {

    public void save(Usuario usuario) {
               Session session;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
        } catch (HibernateException ex) {
            session = HibernateUtil.getSessionFactory().openSession();
        }
        Transaction t = session.beginTransaction();
        session.save(usuario);
        t.commit();
    }

    @Override
    public Usuario getUsuario(long id) {
       
               Session session;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
        } catch (HibernateException ex) {
            session = HibernateUtil.getSessionFactory().openSession();
        }
        
        Usuario usuario = (Usuario) session.load(Usuario.class, id);
        if (usuario == null) {
            usuario = new Usuario();
        }
        return usuario;
    }

    @Override
    public List<Usuario> list() {
               Session session;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
        } catch (HibernateException ex) {
            session = HibernateUtil.getSessionFactory().openSession();
        }
        Transaction t = session.beginTransaction();
        List lista = session.createQuery("from Usuario").list();
        t.commit();
        return lista;
    }

    @Override
    public void remove(Usuario usuario) {
               Session session;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
        } catch (HibernateException ex) {
            session = HibernateUtil.getSessionFactory().openSession();
        }
        Transaction t = session.beginTransaction();
        session.delete(usuario);
        t.commit();
    }

    @Override
    public void update(Usuario usuario) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.update(usuario);
        t.commit();
    }
    
    	
private Session session;
	public Usuario verificarDados(Usuario usuario) throws Exception {
		Usuario us = null;

		try {
			      
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
        } catch (HibernateException ex) {
            session = HibernateUtil.getSessionFactory().openSession();
        }
			String hql = "FROM Usuario WHERE email = '" + usuario.getEmail()
					+ "' and senha = '" + usuario.getSenha() + "'";
			Query query = session.createQuery(hql);

			if (!query.list().isEmpty()) {
				us = (Usuario) query.list().get(0);
			}

		} catch (Exception e) {
			throw e;
		}

		return us;
	}

}
