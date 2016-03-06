/**
  * Copyright 2016 aTool.org 
  */
package com.ms.meizinewsapplication.features.main.json;
import java.util.List;
/**
 * Auto-generated: 2016-03-06 15:49:9
 *
 * @author aTool.org (i@aTool.org)
 * @website http://www.atool.org/json2javabean.php
 */
public class Stories {

    private List<String> images;
    private int type;
    private int id;
    private String ga_prefix;
    private String title;
    public void setImages(List<String> images) {
         this.images = images;
     }
     public List<String> getImages() {
         return images;
     }

    public void setType(int type) {
         this.type = type;
     }
     public int getType() {
         return type;
     }

    public void setId(int id) {
         this.id = id;
     }
     public int getId() {
         return id;
     }

    public void setGa_prefix(String ga_prefix) {
         this.ga_prefix = ga_prefix;
     }
     public String getGa_prefix() {
         return ga_prefix;
     }

    public void setTitle(String title) {
         this.title = title;
     }
     public String getTitle() {
         return title;
     }

}