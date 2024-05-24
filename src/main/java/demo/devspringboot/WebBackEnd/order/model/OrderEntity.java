package demo.devspringboot.WebBackEnd.order.model;

import lombok.experimental.UtilityClass;

@UtilityClass
public class OrderEntity {
    public static final String TABLE_NAME = "WBE_ORDER";

    public class Order_User{
        public static final String TABLE_NAME = "WBE_ORDER_USER";
        public static final String ORDER_ID = "ORDER_ID";
        public static final String USER_ID = "USER_ID";
        public static final String ORDER_MAPPED_USER = "user";
    }

    public class Order_Product{
        public static final String TABLE_NAME = "WBE_ORDER_PRODUCT";
        public static final String ORDER_MAPPED_ORDER_PRODUCT = "order"; //why is it sometime uppercase, sometimes lowercase
        public static final String PRODUCT_MAPPED_ORDER_PRODUCT = "product";
    }
}
