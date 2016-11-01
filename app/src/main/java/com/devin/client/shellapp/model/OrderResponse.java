package com.devin.client.shellapp.model;

import java.util.List;

/**
 * @author devin
 * @Class OrderResponse
 * @Date 16/11/1
 */

public class OrderResponse {


    /**
     * get_my_order {
         code (integer, optional): 响应码，0为成功，1为失败 ,
         success (boolean, optional): 是否成功 ,
         result (Inline Model 1, optional)
     }
     Inline Model 1 {
         total (integer, optional): 总记录数 ,
         items (Array[my_order_item], optional)
     }
     my_order_item {
         order_msg (order_msg, optional),
         product_msg (product_msg, optional)
     }
     order_msg {
         id (integer, optional): 订单id ,
         order_no (string, optional): 订单号 ,
         status (integer, optional): 订单状态,1、未付款，2、已付款，3、未发货，4、已发货，5、交易成功，6、交易关闭 ,
         create_time (integer, optional): 订单创建时间 ,
         status_desc (string, optional): 订单状态描述 ,
         shipping_name (string, optional): 物流名称 ,
         shipping_code (string, optional): 物流单号 ,
         payment (number, optional): 实付金额 ,
         buy_qty (integer, optional): 购买总数
     }
     product_msg {
         id (integer, optional): 商品id ,
         name (string, optional): 商品名称 ,
         qty (integer, optional): 数量 ,
         price (number, optional): 原价 ,
         sale_price (number, optional): 售价
     }
     *
     *
     *
     */


    /**
     * code : 0
     * success : true
     * result : {"total":0,"items":[{"order_msg":{"id":0,"order_no":"string","status":0,"create_time":0,"status_desc":"string","shipping_name":"string","shipping_code":"string","payment":0,"buy_qty":0},"product_msg":{"id":0,"name":"string","qty":0,"price":0,"sale_price":0}}]}
     */

    private int code;
    private boolean success;
    /**
     * total : 0
     * items : [{"order_msg":{"id":0,"order_no":"string","status":0,"create_time":0,"status_desc":"string","shipping_name":"string","shipping_code":"string","payment":0,"buy_qty":0},"product_msg":{"id":0,"name":"string","qty":0,"price":0,"sale_price":0}}]
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
         * order_msg : {"id":0,"order_no":"string","status":0,"create_time":0,"status_desc":"string","shipping_name":"string","shipping_code":"string","payment":0,"buy_qty":0}
         * product_msg : {"id":0,"name":"string","qty":0,"price":0,"sale_price":0}
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
            /**
             * id : 0
             * order_no : string
             * status : 0
             * create_time : 0
             * status_desc : string
             * shipping_name : string
             * shipping_code : string
             * payment : 0
             * buy_qty : 0
             */

            private OrderMsgEntity order_msg;
            /**
             * id : 0
             * name : string
             * qty : 0
             * price : 0
             * sale_price : 0
             */

            private ProductMsgEntity product_msg;

            public OrderMsgEntity getOrder_msg() {
                return order_msg;
            }

            public void setOrder_msg(OrderMsgEntity order_msg) {
                this.order_msg = order_msg;
            }

            public ProductMsgEntity getProduct_msg() {
                return product_msg;
            }

            public void setProduct_msg(ProductMsgEntity product_msg) {
                this.product_msg = product_msg;
            }

            public static class OrderMsgEntity {
                private int id;
                private String order_no;
                private int status;
                private int create_time;
                private String status_desc;
                private String shipping_name;
                private String shipping_code;
                private int payment;
                private int buy_qty;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getOrder_no() {
                    return order_no;
                }

                public void setOrder_no(String order_no) {
                    this.order_no = order_no;
                }

                public int getStatus() {
                    return status;
                }

                public void setStatus(int status) {
                    this.status = status;
                }

                public int getCreate_time() {
                    return create_time;
                }

                public void setCreate_time(int create_time) {
                    this.create_time = create_time;
                }

                public String getStatus_desc() {
                    return status_desc;
                }

                public void setStatus_desc(String status_desc) {
                    this.status_desc = status_desc;
                }

                public String getShipping_name() {
                    return shipping_name;
                }

                public void setShipping_name(String shipping_name) {
                    this.shipping_name = shipping_name;
                }

                public String getShipping_code() {
                    return shipping_code;
                }

                public void setShipping_code(String shipping_code) {
                    this.shipping_code = shipping_code;
                }

                public int getPayment() {
                    return payment;
                }

                public void setPayment(int payment) {
                    this.payment = payment;
                }

                public int getBuy_qty() {
                    return buy_qty;
                }

                public void setBuy_qty(int buy_qty) {
                    this.buy_qty = buy_qty;
                }
            }

            public static class ProductMsgEntity {
                private int id;
                private String name;
                private int qty;
                private int price;
                private int sale_price;

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

                public int getQty() {
                    return qty;
                }

                public void setQty(int qty) {
                    this.qty = qty;
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
            }
        }
    }
}
