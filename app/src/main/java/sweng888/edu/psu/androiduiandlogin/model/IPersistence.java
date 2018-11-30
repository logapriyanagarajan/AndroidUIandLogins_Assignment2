package sweng888.edu.psu.androiduiandlogin.model;

import java.util.ArrayList;

interface IPersistence<T> {
    public void insert(T t);

    public void delete(T t);

    public void edit (T t);

    public ArrayList<T> getDataFromDB();


}
