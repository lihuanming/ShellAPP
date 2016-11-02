package com.devin.client.shellapp.model;

import java.util.List;

/**
 * @author devin
 * @Class ProductResponse
 * @Date 16/11/1
 */

public class ProductResponse {

    /**
     * get_product_4_mobile_response {
         code (integer, optional): 响应码，0为成功，1为失败 ,
         success (boolean, optional): 是否成功 ,
         result (Inline Model 1, optional)
     }
     Inline Model 1 {
         id (integer, optional): 商品编号 ,
         name (string, optional): 商品名称 ,
         sell_point (string, optional): 商品卖点 ,
         stock (integer, optional): 库存 ,
         sale_num (integer, optional): 售量（基本售量+基础售量） ,
         price (number, optional): 原价 ,
         sale_price (number, optional): 售价 ,
         pics (Array[string], optional): 图片集合 ,
         dis (string, optional): 商品明细
     }
     *
     */

    /**
     * code : 0
     * success : true
     * result : {"id":0,"name":"string","sell_point":"string","stock":0,"sale_num":0,"price":0,"sale_price":0,"pics":["string"],"dis":"string"}
     */

    private int code;
    private boolean success;
    /**
     * id : 0
     * name : string
     * sell_point : string
     * stock : 0
     * sale_num : 0
     * price : 0
     * sale_price : 0
     * pics : ["string"]
     * dis : string
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
        private int id;
        private String name;
        private String sell_point;
        private int stock;
        private int sale_num;
        private int price;
        private int sale_price;
        private String dis;
        private List<String> pics;

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

        public String getSell_point() {
            return sell_point;
        }

        public void setSell_point(String sell_point) {
            this.sell_point = sell_point;
        }

        public int getStock() {
            return stock;
        }

        public void setStock(int stock) {
            this.stock = stock;
        }

        public int getSale_num() {
            return sale_num;
        }

        public void setSale_num(int sale_num) {
            this.sale_num = sale_num;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getSale_price() {
            return sale_price;
        }

        public void setSale_price(int sale_price) {
            this.sale_price = sale_price;
        }

        public String getDis() {
            return dis;
        }

        public void setDis(String dis) {
            this.dis = dis;
        }

        public List<String> getPics() {
            return pics;
        }

        public void setPics(List<String> pics) {
            this.pics = pics;
        }
    }
}
