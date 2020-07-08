package org.hiber.covid.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hiber.covid.dao.PersonDAO;
import org.hiber.covid.dto.Address;
import org.hiber.covid.dto.Person;
import org.hiber.covid.dto.VisitedPlaces;
@WebServlet(urlPatterns = "/fs", loadOnStartup = 1)
public class CoronaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public CoronaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		PersonDAO dao = new PersonDAO();
		
		Person person = new Person();
		Address addr = new Address(); 
		
		person.setName(request.getParameter("name").toLowerCase());	
		
		person.setAge(Integer.parseInt(request.getParameter("age")));
		
		person.setSex(request.getParameter("gender").charAt(0));
		
		String[] adr = request.getParameter("addr").toLowerCase().split("\n");
		
		for (int i = 0; i < adr.length; i++) {
			adr[i]=adr[i].trim();
		}
		
		addr.setHn(adr[0]);
		
		addr.setStreet(adr[1]);
		
		addr.setPlace(adr[2]);
		
		addr.setPin(Integer.parseInt(adr[3]));

		person.setAddr(addr);

		person.setContactnum(Long.parseLong(request.getParameter("mobile")));

		person.setStatus(request.getParameter("status")!=null?true:false);
		
		if(person.getStatus()) {
			
			List<VisitedPlaces> vps = new ArrayList<VisitedPlaces>();
			for (int i = 1; i <= 4; i++) {
				VisitedPlaces temp = new VisitedPlaces();
				String[] pl = request.getParameterValues("vp"+i);
				pl = pl[0].split(",");
				if(pl.length>=3) {
					temp.setPlace(pl[0].substring(0,1).toUpperCase()+pl[0].substring(1).toLowerCase());
					temp.setDist(pl[1].substring(0,1).toUpperCase()+pl[1].substring(1).toLowerCase());
					temp.setState(pl[2].substring(0,1).toUpperCase()+pl[2].substring(1).toLowerCase());
					if(pl.length==4)
						temp.setId(Integer.parseInt(pl[3]));
					vps.add(temp);
				}
			}
			person.setVisitedPlaces(vps);
		}else {
			person.setVisitedPlaces(null);
		}

		String id = request.getParameter("id");
		if(id!=null) {
			person.setPnid(id);
			person.getAddr().setAid(Integer.parseInt(request.getParameter("aid")));
		}
		
		dao.save(person);
		
		if(id!=null) {
			response.getWriter().print("<html><body><script>alert('Saved Successfully')</script>"
					+ "<meta http-equiv=\"refresh\" content=\"0;url=/ServletHibernatePro/admin\">"
					+ "</body></html>");
		}else {
		response.getWriter().print("<html><body><script>alert('Saved Successfully')</script>"
				+ "<meta http-equiv=\"refresh\" content=\"0;url=/ServletHibernatePro/\">"
				+ "</body></html>");
		}
	}

}
//The web application [ServletHibernatePro] appears to have started a thread named [mysql-cj-abandoned-connection-cleanup] but has failed to stop it. This is very likely to create a memory leak. Stack trace of thread:
