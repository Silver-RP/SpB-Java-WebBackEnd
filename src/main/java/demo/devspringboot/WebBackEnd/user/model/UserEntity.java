package demo.devspringboot.WebBackEnd.user.model;

import lombok.experimental.UtilityClass;


@UtilityClass
    // The @UtilityClass annotation is used to designate a class as a utility class (lớp tiện ích).
    /*
        with @UtilityClass, Lombok automatically:
        1.Makes the class final.
        2.Adds a private constructor to prevent instantiation.
        3.Marks all methods and fields within the class as static.
     */

public class UserEntity {
    public static final String TABLE_NAME = "WBE_USER";
    public static final String USERNAME = "USERNAME";
    public static final String PASSWORD = "PASSWORD";
    public static final String AGE = "AGE";
    public static final String GENDER = "GENDER";
}
