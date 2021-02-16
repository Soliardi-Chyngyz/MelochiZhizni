package com.example.melochizhizni.data.models;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.melochizhizni.data.converter.DataConverter;
import com.google.firebase.firestore.Exclude;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
@Entity(tableName = "Item")
public class Item implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private long itemId;
    @Exclude
    private String title;
    private String article;
    private String photo;
    private String features;
    private String size;
    private String descriptions;
    private String sizeInBox;
    private String weight;
    private String price;
    private String category;

    public Item(long itemId, String title, String article, String photo, String features, String size, String descriptions, String sizeInBox, String weight, String price, String category) {
        this.itemId = itemId;
        this.title = title;
        this.article = article;
        this.photo = photo;
        this.features = features;
        this.size = size;
        this.descriptions = descriptions;
        this.sizeInBox = sizeInBox;
        this.weight = weight;
        this.price = price;
        this.category = category;
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Item(String title, String article, String photo, String features, String size, String descriptions, String sizeInBox, String weight, String price, String cat) {
        this.title = title;
        this.article = article;
        this.photo = photo;
        this.features = features;
        this.size = size;
        this.descriptions = descriptions;
        this.sizeInBox = sizeInBox;
        this.weight = weight;
        this.price = price;
        this.category = cat;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public String getSizeInBox() {
        return sizeInBox;
    }

    public void setSizeInBox(String sizeInBox) {
        this.sizeInBox = sizeInBox;
    }

    @BindingAdapter("profileImage")
    public static void loadImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext())
                .load(imageUrl).apply(new RequestOptions())
                .into(view);
    }

    @Override
    public String toString() {
        return "Item{" +
                "title='" + title + '\'' +
                ", article=" + article +
                ", photo='" + photo + '\'' +
                ", features='" + features + '\'' +
                ", size='" + size + '\'' +
                ", descriptions='" + descriptions + '\'' +
                ", sizeInBox='" + sizeInBox + '\'' +
                ", weight='" + weight + '\'' +
                ", price='" + price + '\'' +
                ", category='" + category + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return article == item.article &&
                Objects.equals(title, item.title) &&
                Objects.equals(photo, item.photo) &&
                Objects.equals(features, item.features) &&
                Objects.equals(size, item.size) &&
                Objects.equals(descriptions, item.descriptions) &&
                Objects.equals(sizeInBox, item.sizeInBox) &&
                Objects.equals(weight, item.weight) &&
                Objects.equals(price, item.price) &&
                Objects.equals(category, item.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, article, photo, features, size, descriptions, sizeInBox, weight, price, category);
    }
}
