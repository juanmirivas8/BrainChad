package es.iesfranciscodelosrios.model;

public interface CRUD <K,V>{
    Boolean insert(V object);
    Boolean delete(K key);
    Boolean update (V object);
    Boolean find (K key);
}
