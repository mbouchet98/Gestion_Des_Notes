package DAO;
/* DAO = Database Access Object*/
public abstract class DAO<T> {

    /**
     * Permet de créer un entrée dan sla base de donnés
     * par rapport à un objet
     */
    public abstract boolean insert(T obj);

    /**
     * Permet de mettre a jour les donnée d'une entrée dan sla base
     */

    public abstract boolean update(T obj);

    /**
     * Permet la suppression d'une entrée de la base
     */

    public abstract boolean delete(T obj);
}
