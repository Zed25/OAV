package DAO;

import beans.login.search.SearchBean;
import com.sun.rowset.CachedRowSetImpl;

import java.sql.SQLException;

/**
 * Created by andrea on 30/03/17.
 */
public class SearchDAO {

    public CachedRowSetImpl searchObjectInMap(SearchBean bean) {

        String query;

        if (bean.getBand() != 0) {
            query = "SELECT f.\"Valore\", sorg.\"IDSorgente\" " +
                    "FROM \"Afferenza_M_S-S\" AS aff INNER JOIN \"Sorgenti\" AS sorg ON (aff.\"Sorgente\" = sorg.\"IDSorgente\") " +
                            "INNER JOIN \"Flussi\" AS f ON (aff.\"Sorgente\" = f.\"Sorgente\") "+
                            "INNER JOIN \"Bande\" AS b ON (f.\"Banda\" = b.\"IDBanda\") " +
                    "WHERE (\"MappaStellare\" = " + "'" + bean.getMapName() + "' AND " + "b.\"Risoluzione\" = " + "'" + bean.getBand() + "');";
        } else {
            query = "SELECT Sorgenti.IDSorgente, Sorgenti.Luminosità, Sorgenti.Tipo FROM \"Sorgenti\", \"Afferenza_M_S-S\", \"Bande\" " +
                    "WHERE \"Mappe_Stellari.Nome\" = " + "'" + bean.getMapName() + "';";
        }


        System.out.println(query); //DEBUG

        CachedRowSetImpl cachedRowSetImpl = DataBaseManager.getInstance().dbQuery(query);

        if(cachedRowSetImpl == null)
            System.out.println("non ci sono match");

        try {
            while(cachedRowSetImpl.next()){
                System.out.println(cachedRowSetImpl.getString("Valore"));
                return cachedRowSetImpl;

                /*if (cachedRowSetImpl.getString(1).equals(bean.getMapName())){
                    System.out.println("There is a match!");
                }*/
            }

            System.out.println("sono vuoto");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cachedRowSetImpl;
    }
}
