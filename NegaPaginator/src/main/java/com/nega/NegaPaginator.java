package com.nega;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasSize;
import com.vaadin.flow.component.Synchronize;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;

/**
 * @author: Sergio Alberto Hilerio E.
 */

@SuppressWarnings("serial")
@Tag(value = "nega-paginator")
@HtmlImport(value = "bower_components/nega-paginator/nega-paginator.html")
public class NegaPaginator extends Component implements HasSize {

	private Boolean initial = false;

	public NegaPaginator() {
	}

	/**
	 * Sets propertys.
	 *
	 * @param page page @param size size @param total total
	 */
	public void setPropertys(Integer page, Integer size, long total) {
		this.getElement().setProperty("page", (double) page.intValue());
		this.getElement().setProperty("page-size", (double) size.intValue());
		this.getElement().setProperty("total", (double) total);
	}

	public void setInitialPage(Boolean initial) {
		this.initial = initial;
	}

	/**
	 * Sets the current page.
	 *
	 * @param page page
	 */
	public void setPage(Integer page) {
		if (page == null) {
			page = 0;
		}
		if (this.initial == true) {
			this.getElement().setProperty("page", (double) page.intValue() - 1);
		} else {
			this.getElement().setProperty("page", (double) page.intValue());
		}
	}

	/**
	 * Sets items per page.
	 *
	 * @param pageSize pageSize
	 */
	public void setSize(Integer size) {
		if (size == null) {
			size = 15;
		}
		this.getElement().setProperty("pageSize", (double) size.intValue());
	}

	/**
	 * Sets total count of items to paginate.
	 *
	 * @param total total
	 */
	public void setTotal(long total) {
		this.getElement().setProperty("total", (double) total);
	}

	/**
	 * Fired when the page property changes.
	 */
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
		this.getElement().addEventListener("page-changed", args -> listener.valueChange(this));
	}

	public static interface ValueChangeListener {
		public void valueChange(NegaPaginator negaPaginator);
	}

}