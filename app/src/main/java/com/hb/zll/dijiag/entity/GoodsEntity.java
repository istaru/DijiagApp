package com.hb.zll.dijiag.entity;

import java.util.List;

/**
 * Created by Moon on 2018/4/26.
 */

public class GoodsEntity {

    /**
     * msg : 操作成功!
     * status : 1
     * total : 142
     * data : [{
     "store_type": "0",
     "rating": "3.12",
     "source": "1",
     "zk_final_price": "89.00",
     "num_iid": "546565157071",
     "purchase": "0",
     "is_coupon": "0",
     "is_front": "0",
     "is_sold": "0",
     "updatedAt": "2018-03-22 15:02:01",
     "category": "半身裙",
     "status": "1",
     "id": "336357",
     "share_url": "http://terui.net.cn/shopping_new/Applications/Views/bg_gw/share/share.html?num_iid=546565157071",
     "category_id": "120",
     "favorite_id": "4343847",
     "is_show": "1",
     "score": "49.30",
     "volume": "112",
     "cid": "120",
     "is_new": "1",
     "click": "5",
     "created_date": "2017-06-28",
     "createdAt": "2017-06-28 09:38:38",
     "item_url": "http://item.taobao.com/item.htm?id=546565157071",
     "price": "89.00",
     "title": "2017新款鱼尾裙高腰显瘦半身裙百褶荷叶边短裙蓬蓬裙A字裙",
     "top": "0",
     "pict_url": "http://img3.tbcdn.cn/tfscom/i3/718021754/TB2aDD7iJ0opuFjSZFxXXaDNVXa_!!718021754.jpg",
     "is_board": "0"
     }]
     */

    private String msg;
    private int status;
    private int total;
    private List<DataBean> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * store_type : 0
         * rating : 3.12
         * source : 1
         * zk_final_price : 89.00
         * num_iid : 546565157071
         * purchase : 0
         * is_coupon : 0
         * is_front : 0
         * is_sold : 0
         * updatedAt : 2018-03-22 15:02:01
         * category : 半身裙
         * status : 1
         * id : 336357
         * share_url : http://terui.net.cn/shopping_new/Applications/Views/bg_gw/share/share.html?num_iid=546565157071
         * category_id : 120
         * favorite_id : 4343847
         * is_show : 1
         * score : 49.30
         * volume : 112
         * cid : 120
         * is_new : 1
         * click : 5
         * created_date : 2017-06-28
         * createdAt : 2017-06-28 09:38:38
         * item_url : http://item.taobao.com/item.htm?id=546565157071
         * price : 89.00
         * title : 2017新款鱼尾裙高腰显瘦半身裙百褶荷叶边短裙蓬蓬裙A字裙
         * top : 0
         * pict_url : http://img3.tbcdn.cn/tfscom/i3/718021754/TB2aDD7iJ0opuFjSZFxXXaDNVXa_!!718021754.jpg
         * is_board : 0
         * url : https://taoquan.taobao.com/coupon/unify_apply.htm?sellerId=2892141499&activityId=06a058fcd26e42238aec386952f98e7b
         * nick : 1215守护者
         * reduce : 3
         */

        private String store_type;
        private String rating;
        private String source;
        private String zk_final_price;
        private String num_iid;
        private String purchase;
        private String is_coupon;
        private String is_front;
        private String is_sold;
        private String updatedAt;
        private String category;
        private String status;
        private String id;
        private String share_url;
        private String category_id;
        private String favorite_id;
        private String is_show;
        private String score;
        private String volume;
        private String cid;
        private String is_new;
        private String click;
        private String created_date;
        private String createdAt;
        private String item_url;
        private String price;
        private String title;
        private String top;
        private String pict_url;
        private String is_board;
        private String url;
        private String nick;
        private String reduce;

        public String getStore_type() {
            return store_type;
        }

        public void setStore_type(String store_type) {
            this.store_type = store_type;
        }

        public String getRating() {
            return rating;
        }

        public void setRating(String rating) {
            this.rating = rating;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getZk_final_price() {
            return zk_final_price;
        }

        public void setZk_final_price(String zk_final_price) {
            this.zk_final_price = zk_final_price;
        }

        public String getNum_iid() {
            return num_iid;
        }

        public void setNum_iid(String num_iid) {
            this.num_iid = num_iid;
        }

        public String getPurchase() {
            return purchase;
        }

        public void setPurchase(String purchase) {
            this.purchase = purchase;
        }

        public String getIs_coupon() {
            return is_coupon;
        }

        public void setIs_coupon(String is_coupon) {
            this.is_coupon = is_coupon;
        }

        public String getIs_front() {
            return is_front;
        }

        public void setIs_front(String is_front) {
            this.is_front = is_front;
        }

        public String getIs_sold() {
            return is_sold;
        }

        public void setIs_sold(String is_sold) {
            this.is_sold = is_sold;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getShare_url() {
            return share_url;
        }

        public void setShare_url(String share_url) {
            this.share_url = share_url;
        }

        public String getCategory_id() {
            return category_id;
        }

        public void setCategory_id(String category_id) {
            this.category_id = category_id;
        }

        public String getFavorite_id() {
            return favorite_id;
        }

        public void setFavorite_id(String favorite_id) {
            this.favorite_id = favorite_id;
        }

        public String getIs_show() {
            return is_show;
        }

        public void setIs_show(String is_show) {
            this.is_show = is_show;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public String getVolume() {
            return volume;
        }

        public void setVolume(String volume) {
            this.volume = volume;
        }

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public String getIs_new() {
            return is_new;
        }

        public void setIs_new(String is_new) {
            this.is_new = is_new;
        }

        public String getClick() {
            return click;
        }

        public void setClick(String click) {
            this.click = click;
        }

        public String getCreated_date() {
            return created_date;
        }

        public void setCreated_date(String created_date) {
            this.created_date = created_date;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getItem_url() {
            return item_url;
        }

        public void setItem_url(String item_url) {
            this.item_url = item_url;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTop() {
            return top;
        }

        public void setTop(String top) {
            this.top = top;
        }

        public String getPict_url() {
            return pict_url;
        }

        public void setPict_url(String pict_url) {
            this.pict_url = pict_url;
        }

        public String getIs_board() {
            return is_board;
        }

        public void setIs_board(String is_board) {
            this.is_board = is_board;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getNick() {
            return nick;
        }

        public void setNick(String nick) {
            this.nick = nick;
        }

        public String getReduce() {
            return reduce;
        }

        public void setReduce(String reduce) {
            this.reduce = reduce;
        }
    }
}
