package org.hiber.covid;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.hiber.covid.dao.PersonDAO;
import org.hiber.covid.dto.Address;
import org.hiber.covid.dto.Person;
import org.hiber.covid.dto.VisitedPlaces;
import org.hiber.covid.utils.SessionFactoryManger;

public class MainApp {

	public static void main(String[] args) {
		try {
		Scanner scan = new Scanner(System.in);
		
		PersonDAO dao = new PersonDAO();
		
		aa:
		while(true) {
			
			System.out.print("\nWelcome to Covid Survey\n"
					+ "1.Add Person\n"
					+ "2.Show By Id\n"
					+ "3.Show All\n"
					+ "4.Show All Positive Cases\n"
					+ "5.Show Patients in Same Places\n"
					+ "6.Update By Id\n"
					+ "7.Delete By Id\n"
					+ "8.Exit\n\n==>");
			int op = 0;
			try {
				 op = scan.nextInt();
			} catch (Exception e) {
				System.out.println("Invalid Input Try Again");
				scan.nextLine();
				continue aa;
			}
			
			
			switch (op) 
			{
			case 1:
				Person person = new Person();
				Address addr = new Address();
				ArrayList<VisitedPlaces> places = new ArrayList<VisitedPlaces>();
				
				System.out.print("Name : "); 
				scan.nextLine();
				String name = scan.nextLine();
				for(int i=0;i<(10-name.length());i++)
					name+=" ";
				person.setName(name.substring(0,1).toUpperCase()+name.substring(1));	
				
				System.out.print("Address :\nHouse Number/Name : "); 
				addr.setHn(scan.next());
				System.out.print("Street : ");
				addr.setStreet(scan.next());
				System.out.print("Place : ");
				addr.setPlace(scan.next());
				System.out.print("Pin : ");
				addr.setPin(scan.nextInt());
				person.setAddr(addr);
				
				System.out.print("Contact Number : ");
				person.setContactnum(scan.nextLong());
				
				System.out.print("Do you have covid-19 (y/n) ? : ");
				person.setStatus((scan.next().equals("y")?true:false));
				
				placesAdd(scan, person, places);
				
				dao.save(person);
				break;
				
			case 2:
				System.out.print("Enter Id : ");
				Person per2 = dao.getById(scan.next().toUpperCase());
				placesShow(scan, dao, per2);
				break;
				
			case 3:
				List<Person> persons = dao.showAll();
				displayop(scan, dao, persons);
				break;
				
			case 4:
				List<Person> persons2 = dao.showPositive();
				displayop(scan, dao, persons2);
				break;
			case 5:
				List<Map> persons3 = dao.positivePlaces();
				persons3.forEach(pertemp->{
					System.out.println(pertemp.get("place")+" "+pertemp.get("name"));
				});
				break;
			case 6:
				System.out.print("Enter Id : ");
				Person per = dao.getById(scan.next().toUpperCase());
				System.out.print("Is he/she "+(per.getStatus()?"-":"+")+"ve now (y/n) ? : ");
				char temp = scan.next().charAt(0);
				if(temp=='y')
					per.setStatus(!(per.getStatus()));
				
				if(per.getStatus()) {
					ArrayList<VisitedPlaces> placesNew = new ArrayList<VisitedPlaces>();
					placesAdd(scan, per, placesNew);
				}else {
					per.setVisitedPlaces(null);
				}
				dao.save(per);
				System.out.println(per+"\nUpdated");
				scan.nextLine();
				scan.nextLine();
				break;
			
			case 7:
				System.out.print("Enter Id : ");
				//Person temp2=dao.getById((scan.next().toUpperCase()));
				dao.deleteById(scan.next().toUpperCase());
				System.out.println(/*temp2+*/" \nDeleted");
				scan.nextLine();
				scan.nextLine();
				break;
			case 8:
				SessionFactoryManger.close();
				break aa;
			
			default:
				System.out.println("Invalid Option");
				break;
			}
		}
		}finally {
			try {
			SessionFactoryManger.close();
			}catch(Exception e) {
			}
		}

	}

	private static void placesShow(Scanner scan, PersonDAO dao, Person per2) {
		
		while(true) {
		System.out.println(per2.show());
		if(per2.getStatus()) {
		System.out.print("==(Place_id/x)==>");
		char op2 = scan.next().charAt(0);
		if(op2 == 'x') {
			break;
		}
		else {
			VisitedPlaces per = per2.getVisitedPlaces().get(Integer.parseInt(op2+"")-1);
			System.out.println(per!=null?per:"Invalid Input");
		}
		
		}else {
			break;
		}
		scan.nextLine();
		scan.nextLine();
		
		}
	}

	private static void placesAdd(Scanner scan, Person person, ArrayList<VisitedPlaces> places) {
		if(person.getStatus()) {
		System.out.print("Visited Places \n(+) Add\n(x) No Visited Places\n===>");
		while(scan.next().charAt(0)=='+') {
			
			System.out.print("Place : ");
			String place = scan.next();
			
			System.out.print("District : ");
			String dist = scan.next();
			System.out.print("State : ");
			String st = scan.next();
			
			places.add(new VisitedPlaces(place,dist,st));
			
			System.out.println("\n(+) Add\n(x) No Visited Places\n===>");
		}
		if(places.size()!=0)
			person.setVisitedPlaces(places);
		}
	}

	private static void displayop(Scanner scan, PersonDAO dao, List<Person> persons2) {
		while(true) {
		System.out.println("\n   Id         Name     AID     Contact   Covid  Visited Places");
//		for(Person temp : persons2) {
//			System.out.println(temp);
//		}
		persons2.forEach((per)->{System.out.println(per);});
		System.out.print("=(id/x)=> ");
		String op = scan.next().toUpperCase();
		if(op.equals("X"))
			break;
		else {
			Person per = dao.getById(op);
			placesShow(scan, dao, per);
		}
		}
	}
	

}
