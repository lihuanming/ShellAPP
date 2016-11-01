package com.devin.client.shellapp.model;

import java.util.List;

/**
 * @author devin
 * @Class ShopResponse
 * @Date 16/11/1
 */

public class ShopResponse {

    /**
     * get_shop_response {
         code (integer, optional): 响应码，0为成功，1为失败 ,
         success (boolean, optional): 是否成功 ,
         result (Inline Model 1, optional)
     }
     Inline Model 1 {
         carousels (Array[carousel_list_item], optional),
         category (Array[category_list_item], optional)
     }
     carousel_list_item {
         id (integer, optional): 轮播图id ,
         pic (string, optional): 轮播图片url
     }
     category_list_item {
         id (integer, optional): 分类id ,
         title (string, optional): 分类名称 ,
         icon (string, optional): 分类名称icon
     }
     *
     */

    /**
     * code : 0
     * success : true
     * result : {"carousels":[{"id":0,"pic":"string"}],"category":[{"id":0,"title":"string","icon":"string"}]}
     */

    private int code;
    private boolean success;
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
        /**
         * id : 0
         * pic : string
         */

        private List<CarouselsEntity> carousels;
        /**
         * id : 0
         * title : string
         * icon : string
         */

        private List<CategoryEntity> category;

        public List<CarouselsEntity> getCarousels() {
            return carousels;
        }

        public void setCarousels(List<CarouselsEntity> carousels) {
            this.carousels = carousels;
        }

        public List<CategoryEntity> getCategory() {
            return category;
        }

        public void setCategory(List<CategoryEntity> category) {
            this.category = category;
        }

        public static class CarouselsEntity {
            private int id;
            private String pic;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }
        }

        public static class CategoryEntity {
            private int id;
            private String title;
            private String icon;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }
        }
    }
}
