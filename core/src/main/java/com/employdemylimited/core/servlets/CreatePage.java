package com.employdemylimited.core.servlets;

import java.io.IOException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.commons.jcr.JcrConstants;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

@Component(service = Servlet.class,
 property = { "sling.servlet.methods=" + HttpConstants.METHOD_GET,
		 "sling.servlet.paths=" + "/bin/demo/pagecreate" })
public class CreatePage extends SlingSafeMethodsServlet {

	private static final Logger log = LoggerFactory.getLogger(CreatePage.class);

	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {

				try {
					String parentPath = "/content/employdemy-limited/footer-page";
					String template = "/conf/employdemy-limited/settings/wcm/templates/newpage-template";
					
					for(int i=1;i<=6;i++)
					{
						String pageName="sample"+i;
						String title = "Sample"+i;
					ResourceResolver resourceResolver=request.getResourceResolver();
					PageManager pageManager = resourceResolver.adaptTo(PageManager.class);
					Page newpage = pageManager.create(parentPath, pageName, template, title,true);
					resourceResolver.commit();
					response.setContentType("text/plain");
					response.getWriter().write(" "+newpage);
					}	
				} catch (Exception e) {
					log.error(e.getMessage(), e);
				}
}
}