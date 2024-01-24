package com.employdemylimited.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class)
public class ArticleModel {

    @ValueMapValue
    private String rootpath;

    @ValueMapValue
    private String number;

    public String getRootpath() {
        return rootpath;
    }

    public String getNumber() {
        return number;
    }
    
}
