package com.devin.client.shellapp.model;

import java.util.List;

/**
 * @author devin
 * @Class AddCartRequest
 * @Date 16/11/1
 */

public class AddCartRequest {

    /**
     * product_info {
        cart_sub (Array[cart_sub], optional)
     }
     cart_sub {
         id (integer): 商品id ,
         qty (integer): 数量 ,
         token (string): token ,
         is_select (integer, optional): 是否选中(1-是,0-否。[主要用于移动端下单]) ,
         is_delete (integer, optional): 是否删除(1-是,0-否。[主要用于移动端删除购物车商品])
     }
     *
     *
     */

    /**
     * id : 0
     * qty : 0
     * token : string
     * is_select : 0
     * is_delete : 0
     */

    private List<CartSubEntity> cart_sub;

    public List<CartSubEntity> getCart_sub() {
        return cart_sub;
    }

    public void setCart_sub(List<CartSubEntity> cart_sub) {
        this.cart_sub = cart_sub;
    }

    public static class CartSubEntity {
        private int id;
        private int qty;
        private String token;
        private int is_select;
        private int is_delete;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getQty() {
            return qty;
        }

        public void setQty(int qty) {
            this.qty = qty;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public int getIs_select() {
            return is_select;
        }

        public void setIs_select(int is_select) {
            this.is_select = is_select;
        }

        public int getIs_delete() {
            return is_delete;
        }

        public void setIs_delete(int is_delete) {
            this.is_delete = is_delete;
        }
    }
}
