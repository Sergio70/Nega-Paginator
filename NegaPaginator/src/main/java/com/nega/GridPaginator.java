package com.nega;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.support.PagedListHolder;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

/**
 * @author: Sergio Alberto Hilerio E.
 */

@SuppressWarnings("serial")
public class GridPaginator<T> extends VerticalLayout {

	private NegaPaginator paginator = new NegaPaginator();
	private Collection<T> items;
	private Grid<T> grid = new Grid<T>();
	private Integer size;
	private Integer page;

	public GridPaginator() {
		defaultConfig();
		add(grid, paginator);
		pageChange();
	}

	private void pageChange() {
		paginator.addPageChangeListener(e -> {
			updateChangePage(getPageList(e.getPage()));
		});
	}

	private List<T> getPageList(Integer page) {
		return getPage(new ArrayList<>(this.items), page, size);
	}

	public void setItems(Collection<T> items) {
		this.items = items;
		this.grid.setItems(getPageList(0));
		setTotal();
	}

	private void updateChangePage(Collection<T> items) {
		this.grid.setItems(items);
	}

	private List<T> getPage(List<T> list, Integer page, Integer size) {
		PagedListHolder<T> pagedListHolder = new PagedListHolder<>(list);
		pagedListHolder.setPage(page);
		pagedListHolder.setPageSize(size);
		return pagedListHolder.getPageList();
	}

	private void defaultConfig() {
		if (this.size == null) {
			this.size = 15;
		}
		if (this.page == null) {
			this.page = 0;
		}
		setSizeFull();
		grid.setHeightByRows(true);
		paginator.setInitialPage(true);
		setAlignItems(Alignment.CENTER);
	}

	public Grid<T> getGrid() {
		return this.grid;
	}

	public NegaPaginator getNegaPaginator() {
		return this.paginator;
	}

	private void setTotal() {
		Integer total = this.items.size();
		this.paginator.setTotal(total);
	}

	public void setPage(Integer page) {
		this.page = page;
		this.paginator.setPage(this.page);
	}

	public void setSize(Integer size) {
		this.size = size;
		this.paginator.setSize(this.size);
	}

}
