package ug.co.lion.lionmobile;

/**
 * Created by Eriq on 10/28/2015.
 */
public class ProductItem {

    private String pdtName;
    private int pdtThumbnail;

    public String getName() {
        return pdtName;
    }

    public void setName(String name) {
        this.pdtName = name;
    }

    public int getThumbnail() {
        return pdtThumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.pdtThumbnail = thumbnail;
    }
}
