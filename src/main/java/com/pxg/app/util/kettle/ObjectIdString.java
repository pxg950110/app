package com.pxg.app.util.kettle;

import org.pentaho.di.repository.ObjectId;

/**
 * <p>
 * 2019/10/22  23:41
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 * </p>
 * <p>
 * @author pxg
 * @emil pxg950110@163.com
 * @Date 2019/10/22
 * @Version 1.0.0
 * @description </p>
 */
public class ObjectIdString implements ObjectId {

    private long objectid;

    public ObjectIdString() {
    }


    public void setObjectid(long objectid) {
        this.objectid = objectid;
    }

    public ObjectIdString(long objectid) {
        this.objectid = objectid;
    }


    @Override
    public String getId() {
        return String.valueOf(this.objectid);
    }
}
