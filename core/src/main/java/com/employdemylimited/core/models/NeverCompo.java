package com.employdemylimited.core.models;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.employdemylimited.core.services.QueryService;
import com.employdemylimited.core.services.Impl.QueryServiceImpl;

@Model(adaptables = {Resource.class,SlingHttpServletRequest.class},defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class NeverCompo {
    
    @ValueMapValue
    private String rootpath;

    @ValueMapValue
    private String number;

    @ValueMapValue
    private String heading;

    public String getRootpath() {
        return rootpath;
    }

    public String getNumber() {
        return number;
    }

    public String getHeading() {
        return heading;
    }

    @OSGiService
    QueryServiceImpl queryService;

    private String hitPath;

    private String title;

    public String getHitPath() {
        return hitPath;
    }

    public String getTitle() {
        return title;
    }

    @PostConstruct
    public void init()
    {
        this.hitPath = queryService.getHitPath();
        this.title = queryService.getTitle();
    }

}
