package com.nega;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasSize;
import com.vaadin.flow.component.Synchronize;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;

@Tag(value = "nega-paginator")
@HtmlImport(value = "bower_components/nega-paginator/nega-paginator.html")
public class NegaPaginator extends Component implements HasSize {

	Boolean initial = false;

	public void setPropertys(Integer page, Integer size, long total) {
		this.getElement().setProperty("page", (double) page.intValue());
		this.getElement().setProperty("page-size", (double) size.intValue());
		this.getElement().setProperty("total", (double) total);
	}

	public void setInitialPage(Boolean initial) {
		this.initial = initial;
	}

	public void setPage(Integer page) {
		if (this.initial == true) {
			this.getElement().setProperty("page", (double) page.intValue() - 1);
		} else {
			this.getElement().setProperty("page", (double) page.intValue());
		}
		
	}

	public void setSize(Integer size) {
		this.getElement().setProperty("pageSize", (double) size.intValue());
	}

	public void setTotal(long total) {
		this.getElement().setProperty("total", (double) total);
	}

	@Synchronize(value = { "page-changed" })
	public Integer getPage() {
		Integer page;
		if (this.initial == true) {
			page = Integer.parseInt(this.getElement().getProperty("page")) - 1;
			if (page < 0) {
				page = 0;
			}
		} else {
			page = Integer.parseInt(this.getElement().getProperty("page"));
			if (page < 1) {
				page = 1;
			}
		}
		return page;
	}

	public void addPageChangeListener(ValueChangeListener listener) {
		this.getElement().addEventListener("page-changed", args -> listener.valueChange(this.getPage()));
	}

	public static interface ValueChangeListener {
		public void valueChange(Integer var1);
	}

}