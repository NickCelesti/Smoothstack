package Utopia.Domain;

import java.io.Serializable;

public abstract class BaseDomain<T> implements Serializable {

    private static final long serialVersionUID = 7481202354841L;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

}