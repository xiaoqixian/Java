package beans;

/**
 * 数据表到Java类的映射
 * 书籍的字段包括id,书名，价格。最后两列还有编辑和删除两个所有
 */

public class Books {
    public int id;
    public String name;
    public int price;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
