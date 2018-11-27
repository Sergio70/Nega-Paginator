package com.nega;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;

@Route("")
public class Demo extends Div {

	public Demo() {
		
	
		NegaPaginator paginator = new NegaPaginator();
		
		paginator.setPage(5);//Page number
		
		//if initial is true first number of pager will be 0 and the numbers of the pages will be -1
        //if initial is false the first number of the pager will be 1 and the numbers of the pages will be the same as shown in the paged
		paginator.setInitialPage(true); //default is false
	
		paginator.setTotal(100); //Total records 
		
		paginator.setSize(10); //Total of records to show
		
		//Returns number of selected page
		paginator.addPageChangeListener(e ->{
			System.out.println("Page: " + e);
		});
		
		add(paginator);
		

	}
}
