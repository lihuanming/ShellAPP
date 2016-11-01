package com.devin.client.shellapp.model;

import java.util.List;

/**
 * @author devin
 * @Class CartResponse
 * @Date 16/11/1
 */

public class CartResponse {

    /**
     *
     * get_my_cart_response {
         code (integer, optional): 响应码，0为成功，1为失败 ,
         success (boolean, optional): 是否成功 ,
         result (Inline Model 1, optional)
     }
     Inline Model 1 {
         total (integer, optional): 总记录数 ,
         items (Array[my_cart_item], optional)
     }
     my_cart_item {
         id (integer, optional): 商品id ,
         name (string, optional): 商品名称 ,
         num (integer, optional): 商品数量 ,
         product_image (string, optional): 商品图片 ,
         product_price (number, optional): 商品价格 ,
         is_valid (integer, optional): 商品是否有效(1-有效，0-无效) ,
         price (number, optional): 商品原价 ,
         stock (integer, optional): 库存
     }
     *
     */

    /**
     * code : 0
     * success : true
     * result : {"total":0,"items":[{"id":0,"name":"string","num":0,"product_image":"string","product_price":0,"is_valid":0,"price":0,"stock":0}]}
     */

    private int code;
    private boolean success;
    /**
     * total : 0
     * items : [{"id":0,"name":"string","num":0,"product_image":"string","product_price":0,"is_valid":0,"price":0,"stock":0}]
     */

    private ResultEntity result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ResultEntity getResult() {
        return result;
    }

    public void setResult(ResultEntity result) {
        this.result = result;
    }

    public static class ResultEntity {
        private int total;
        /**
         * id : 0
         * name : string
         * num : 0
         * product_image : string
         * product_price : 0
         * is_valid : 0
         * price : 0
         * stock : 0
         */

        private List<ItemsEntity> items;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<ItemsEntity> getItems() {
            return items;
        }

        public void setItems(List<ItemsEntity> items) {
            this.items = items;
        }

        public static class ItemsEntity {
            private int id;
            private String name;
            private int num;
            private String product_image;
            private int product_price;
            private int is_valid;
            private int price;
            private int stock;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getNum() {
                return num;
            }

            public void setNum(int num) {
                this.num = num;
            }

            public String getProduct_image() {
                return product_image;
            }

            public void setProduct_image(String product_image) {
                this.product_image = product_image;
            }

            public int getProduct_price() {
                return product_price;
            }

            public void setProduct_price(int product_price) {
                this.product_price = product_price;
            }

            public int getIs_valid() {
                return is_valid;
            }

            public void setIs_valid(int is_valid) {
                this.is_valid = is_valid;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public int getStock() {
                return stock;
            }

            public void setStock(int stock) {
                this.stock = stock;
            }
        }
    }
}
