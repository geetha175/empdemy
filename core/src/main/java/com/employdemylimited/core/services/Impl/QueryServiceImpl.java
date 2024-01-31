package com.employdemylimited.core.services.Impl;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;
import com.employdemylimited.core.services.QueryService;

@Component(service = QueryService.class)
public class QueryServiceImpl implements QueryService{

    @Inject
    ResourceResolver resolver;

    private String pageRootPath="/content/employdemy-limited/footer-page";

    @Reference
    private QueryBuilder builder;

    private String hitPath;

    private String jcrpath;

    private Resource jcrContent;

    private Node node;

    private String title;

    public String getHitPath() {
        return hitPath;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public void queryMethod() {

        Session session = resolver.adaptTo(Session.class);
        
         Map map = new HashMap();             
         map.put("path", pageRootPath);
         map.put("type", "cq:Page");
         map.put("property", "jcr:content/cq:template");
         map.put("property.value", "/conf/employdemy-limited/settings/wcm/templates/newpage-template");
         map.put("p.limit", "-1");

         Query query = builder.createQuery(PredicateGroup.create(map), session);                    
                     
         SearchResult result = query.getResult();
    
            try {
                for(Hit hit :result.getHits()){
                hitPath = hit.getPath();
                jcrpath  = hitPath +"/jcr:content";
                jcrContent = resolver.getResource(jcrpath);
                node = jcrContent.adaptTo(Node.class);
                title = node.getProperty("jcr:title").getString();
            } 
        }catch (RepositoryException e) {
                e.printStackTrace();
            }
    } 
}
