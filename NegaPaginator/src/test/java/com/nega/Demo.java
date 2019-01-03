package com.nega;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;

@Route("")
public class Demo extends Div {

	public Demo() {

		// Paginator example

		NegaPaginator paginator = new NegaPaginator();
		paginator.setPage(5);// Page number
		// if initial is true first number of pager will be 0 and the numbers of the
		// pages will be -1 if initial is false the first number of the pager will be 1
		// and the numbers
		// of the pages will be the same as shown in the paged
		paginator.setInitialPage(false); // default is false
		paginator.setTotal(100); // Total records
		paginator.setSize(10); // Total of records to show

		// Returns number of selected page
		paginator.addPageChangeListener(e -> {
			System.out.println("Page: " + e.getPage());
		});

		add(paginator);

		// GridPaginator example

		List<User> list = new ArrayList<>();

		for (int i = 0; i < 1000; i++) {
			list.add(new User(String.valueOf(i), "demo " + i, "443220708" + i));
		}

		GridPaginator<User> gridPaginator = new GridPaginator<>();
		gridPaginator.setItems(list);
		gridPaginator.setSize(10);
		gridPaginator.setPage(1000);
		gridPaginator.getGrid().addColumn(User::getId).setHeader("id");
		gridPaginator.getGrid().addColumn(User::getUsername).setHeader("username");
		gridPaginator.getGrid().addColumn(User::getPhone).setHeader("phone");
		add(gridPaginator);

	}

}
